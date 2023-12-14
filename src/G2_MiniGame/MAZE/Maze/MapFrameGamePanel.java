package G2_MiniGame.MAZE.Maze;

import G2_MiniGame.MAZE.Helpers.Sound;
import G2_MiniGame.MAZE.MazeMenu;
import G2_MiniGame.Main;
import G2_MiniGame.MiniGame_MainMenu;
import G2_MiniGame.Player;
import G2_MiniGame.PlayersHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MapFrameGamePanel extends GamePanel implements Runnable, MouseListener {

    //<-------------GAME LOOP OPTIONS------------>
    private static final int TARGET_FPS = 60;
    private static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
    private static final double MAX_DISTANCE = 100000.00;
    private static final int TOTAL_RAYS = 200;
    private static final int TOTAL_TILES = 16*16;
    boolean GameOver = false;
    boolean Win = false;
    private static final boolean isRunning = true;
    public int option_ctr = 0;


    //<--------------GAME OBJECTS----------------->
    private JFrame frame;
    PlayersHandler handler = PlayersHandler.getInstance();
    private Map map = new Map();
    private final Character mainCharacter = (Character) new Character.CharacterBuilder().position(75, 325).angle(4.9).build();
    private final screen3D screen = new screen3D(TOTAL_RAYS);
    public int time_score = 0;
    private Type_Wall[] current_vertical_pixels = new Type_Wall[200];
    private Sound sd;
    private BulletsPanel bp = new BulletsPanel.BulletsPanelBuilder().build();
    private GameStatePanel gsp = new GameStatePanel.GameStatePanelBuilder().build();
    private int counter_instance = 0;

    //<------STORE KEYS TO RESPECT GAME LOOP----->
    private static Set<Integer> pressedKeys = new HashSet<>();

    //<--------------CONSTRUCTOR----------------->
    public MapFrameGamePanel(JFrame frame) {
        setupKeyBindings();
        setPreferredSize(getPreferredSize());
        setFocusable(true);
        requestFocusInWindow();
        addMouseListener(this);
        this.frame = frame;
    }

    //<------------RENDERING OF OBJECTS IN SCREEN---------->
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        screen.paintComponents(g);
        drawrays3D(g);
        mainCharacter.gun.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(400,400,10,10);
        bp.paintComponent(g);
        gsp.paintComponent(g);
    }


    //<--------------GAME LOOP------------------->
    public void run() {
        Sound.playBg(new File("src/G2_MiniGame/MAZE/Audio/bg_music.wav"), 1.5f);
        long lastLoopTime = System.nanoTime();
        long lastFpsTime = 0;
        int current_fps = 1;

        while (isRunning) {
            long now = System.nanoTime();
            long elapsedTime = now - lastLoopTime;
            lastLoopTime = now;


            if(GameOver && option_ctr == 0){
                option_ctr++;
                int choice = JOptionPane.showConfirmDialog(null, "Final Score: " + time_score + "\nDo you Want to Play Again?", "Question", JOptionPane.YES_NO_OPTION);
                updatePlayerScore();

                if (choice == JOptionPane.YES_OPTION) {
                    Sound.stopSound();
                    restart_game();
                } else {
                    JOptionPane.showMessageDialog(null, "Too bad!");
                    Sound.stopSound();
                    Sound.stopBg();

                    frame.dispose();
                    new MazeMenu();
                    Main.playBg();
                }
            }

            if(map.enemy_steps.size() != 0 && map.enemy_steps.get(map.enemy_steps.size() - 1) != mainCharacter.calculate_index() && !GameOver){
                map.enemy_steps.add(mainCharacter.calculate_index());
            }

            lastFpsTime += elapsedTime;
            if (lastFpsTime >= 1000000000) {
                lastFpsTime = 0;
                current_fps = 1;
                if(!GameOver){
                    map.update_map(mainCharacter.calculate_index());
                    time_score++;
                    System.out.println("Current Score: " + time_score);
                }

            }

            if(!GameOver){
                updateGameLogic(current_fps);
                processInput(elapsedTime);
            }

            repaint();

            try {
                long sleepTime = Math.max(0, (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            current_fps += 1;
        }
    }

    private void updatePlayerScore() {
        Player p = MiniGame_MainMenu.currentPlayer;

        if (Win){
            time_score = 1000 - time_score;
        }
        if (p.getMazeScore() < time_score){
            p.setMazeScore(time_score);
            handler.updatePlayersFile();
        }

        Win = false;
    }


    //<--------------GAME LOGIC------------------->
    public void updateGameLogic(int curr_frame){
        if(map.enemy.state){
            map.enemy.animation_counter += 1;

            if(map.enemy.animation_counter >= map.enemy.animation_speed){
                map.enemy.state = false;
                map.enemy.animation_counter = 0;
            }
        }

        if(!mainCharacter.gun.getIdleState()){
            if(mainCharacter.gun.animation_counter % mainCharacter.gun.animation_speed == 0){
                mainCharacter.gun.current_animation_frame += 1;

                if(mainCharacter.gun.current_animation_frame == mainCharacter.gun.TOTAL_ANIMATION_PICTURES + 1){
                    mainCharacter.gun.animation_counter = 0;
                    mainCharacter.gun.current_animation_frame = 1;
                    mainCharacter.gun.setIdleState(true);
                }

            }

            mainCharacter.gun.animation_counter = mainCharacter.gun.animation_counter % mainCharacter.gun.animation_speed + 1;
        } else {
            mainCharacter.gun.current_animation_frame = 1;
        }


        try {
            bp.setBackgroundImage("src/G2_MiniGame/MAZE/Animations/BulletSprites/bullet" + mainCharacter.gun.getBullets() + ".png");
            mainCharacter.gun.setBackgroundImage("src/G2_MiniGame/MAZE/Animations/GunSprites/gun_" + mainCharacter.gun.current_animation_frame + ".png");
        } catch (IOException e){
            System.out.println("Frame is " + curr_frame + "index is " + mainCharacter.gun.current_animation_frame);
            System.out.println("Not found");
        }

        //GAME_OVER, WIN, LOSE HERE
        int game_changer = map.map[mainCharacter.calculate_index()];
        if( (game_changer == 5 || game_changer == 2 )&& !map.enemy.state && counter_instance == 0){
            GameOver = true;

            if(game_changer == 2){
                //WIN
                Win = true;
                try {
                    gsp.setBackgroundImage("src/G2_MiniGame/MAZE/Animations/Screens/sc1.png");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Sound.play(new File("src/G2_MiniGame/MAZE/Audio/Victory.wav"), 1.5f);
                System.out.println("Final score is " + time_score);
            } else {
                //LOSE
                time_score = 0;
                try {
                    gsp.setBackgroundImage("src/G2_MiniGame/MAZE/Animations/Screens/sc2.png");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


                Sound.play(new File("src/G2_MiniGame/MAZE/Audio/jumpscare.wav"), 1.5f);
                System.out.println("Final score is " + time_score);
            }

            gsp.show_panel();
            counter_instance++;
        }

    }

    private boolean collidesWithWalls(int deltaX, int deltaY) {

        int characterCenterX = deltaX + mainCharacter.pos_x + 5;
        int characterCenterY = deltaY + mainCharacter.pos_y + 5;

        for (Wall wall : map.walls) {
            if(wall.type != Type_Wall.SOLIDWALL){
                continue;
            }
            int wallCenterX = wall.pos_x + wall.width / 2;
            int wallCenterY = wall.pos_y + wall.height / 2;

            int halfCharacterWidth = mainCharacter.width / 2;
            int halfCharacterHeight = mainCharacter.height / 2;
            int halfWallWidth = wall.width / 2;
            int halfWallHeight = wall.height / 2;


            if (Math.abs(characterCenterX - wallCenterX) < (halfCharacterWidth +halfWallWidth) &&
                    Math.abs(characterCenterY - wallCenterY) < (halfCharacterHeight +halfWallHeight)) {
                return true;
            }
        }

        return false;
    }


    private void drawrays3D(Graphics g) {
        // Assuming each tile is 50 pixels wide and 50 pixels high
        int tileWidth = 50;
        int tileHeight = 50;
        Type_Wall final_casted_type;
        Type_Wall vertical_casted_type = Type_Wall.NONSOLID;
        Type_Wall horizontal_casted_type = Type_Wall.NONSOLID;

        int  ray_posx = 0, ray_posy = 0;
        int dof, x_offset = 1, y_offset = 1, mp;
        double ray_space = 0.0174533 / 5.0;
        double ray_angle;
        double lineDistance;

        ray_angle = mainCharacter.angle - ray_space * 150;

        if(ray_angle < 0){
            ray_angle += 2 * Math.PI;
        }
        if(ray_angle > Math.PI * 2){
            ray_angle -= Math.PI * 2;
        }


        for (int r = 0; r < TOTAL_RAYS; r++) {
            //VERTICAL
            dof = 0;

            double distV = MAX_DISTANCE;
            int vx = mainCharacter.pos_x;
            int vy = mainCharacter.pos_y;
            double nTan = -Math.tan(ray_angle);

            //looking left
            if (ray_angle > Math.PI / 2 && ray_angle < 3 * Math.PI / 2) {
                ray_posx = (((mainCharacter.getVertex_posx() / tileWidth) * tileWidth) - 1);
                ray_posy = (int) ((mainCharacter.getVertex_posx() - ray_posx) * nTan + mainCharacter.getVertex_posy());
                x_offset = -tileWidth;
                y_offset = (int) Math.round( -x_offset * nTan);
            }

            //looking right
            if (ray_angle < Math.PI / 2 || ray_angle > 3 * Math.PI / 2) {
                ray_posx =  (((mainCharacter.getVertex_posx() / tileWidth) * tileWidth) + tileWidth);
                ray_posy = (int) ((mainCharacter.getVertex_posx() - ray_posx) * nTan + mainCharacter.getVertex_posy());
                x_offset = tileWidth;
                y_offset = (int) Math.round(-x_offset * nTan);
            }

            //looking staright up or down
            if (ray_angle == Math.PI || ray_angle == Math.PI*2) {
                ray_posx = mainCharacter.getVertex_posx();
                ray_posy = mainCharacter.getVertex_posy();
                dof = 15;
            }


            while (dof < 16) {
                mp = getIndexofPoints(ray_posx, ray_posy, x_offset, y_offset);
                if(mp < 0){
                    break;
                }

                if (mp >= 0 && mp < (TOTAL_TILES) && map.walls.get(mp).type != Type_Wall.NONSOLID) {
                    vertical_casted_type = map.walls.get(mp).type;
                    vx=ray_posx;
                    vy=ray_posy;
                    distV = getDistance(mainCharacter.pos_x, mainCharacter.pos_y, vx,vy,ray_angle);
                    break;
                } else {
                    ray_posx += x_offset;
                    ray_posy += y_offset;
                    dof += 1;
                }

            }

            //HORIZONTAL
            dof = 0;
            double distH = MAX_DISTANCE;
            int hx = mainCharacter.pos_x;
            int hy = mainCharacter.pos_y;

            double aTan = -1.0 / Math.tan(ray_angle);

            //looking up
            if (ray_angle > Math.PI) {
                ray_posy =  ((( mainCharacter.getVertex_posy() / tileHeight) * tileHeight) - 1);
                ray_posx = (int) ((mainCharacter.getVertex_posy() - ray_posy) * aTan + mainCharacter.getVertex_posx());
                y_offset = -tileHeight;
                x_offset = (int) Math.round (-y_offset * aTan);
            }

            //looking down
            if (ray_angle < Math.PI) {
                ray_posy =  (((mainCharacter.getVertex_posy() / tileHeight) * tileHeight) + tileHeight);
                ray_posx = (int) ((mainCharacter.getVertex_posy() - ray_posy) * aTan + mainCharacter.getVertex_posx());
                y_offset = tileHeight;
                x_offset = (int) Math.round( -y_offset * aTan);
            }

            //looking straight left or right
            if (ray_angle == 0 || ray_angle == Math.PI) {
                ray_posx = mainCharacter.getVertex_posx();
                ray_posy = mainCharacter.getVertex_posy();
                break;
            }

            while (dof < 16) {
                mp = getIndexofPoints(ray_posx, ray_posy, x_offset,y_offset);
                if(mp < 0){
                    break;
                }


                if (mp >= 0 && mp < TOTAL_TILES && map.walls.get(mp).type != Type_Wall.NONSOLID) {
                    horizontal_casted_type = map.walls.get(mp).type;
                    hx=ray_posx;
                    hy=ray_posy;
                    distH = getDistance(mainCharacter.pos_x, mainCharacter.pos_y, hx,hy,ray_angle);
                    break;
                } else {
                    ray_posx += x_offset;
                    ray_posy += y_offset;
                    dof += 1;
                }

            }

            //<------------------------------------------>
            //COMPARE HORIZONTAL - VERTICAL
            int winner;
            if(distV < distH){
                ray_posx=vx;
                ray_posy=vy;
                lineDistance = distV;
                winner = 1;
                final_casted_type = vertical_casted_type;
            } else {
                ray_posx=hx;
                ray_posy=hy;
                lineDistance = distH;
                winner = 2;
                final_casted_type = horizontal_casted_type;
            }

            current_vertical_pixels[r] = final_casted_type;
            //<---------------------------------------------->

            //<------------------------------------------------------------------------------->
            //DRAW 3D
            //Fix Fisheye effect
            double cast_angle = mainCharacter.angle - ray_angle;
            if (cast_angle < 0) {
                cast_angle += Math.PI * 2;
            }

            if (cast_angle > Math.PI * 2) {
                cast_angle -= Math.PI * 2;
            }

            lineDistance = lineDistance * Math.cos(cast_angle);

            double lineH = (50 * screen.screen_height) / lineDistance;

            double ty_step =  50.0 / lineH;
            double ty_off = 0;

            if (lineH > screen.screen_height) {
                ty_off = (lineH - screen.screen_height) / 2.0;
                lineH = screen.screen_height;
            }
            double line_Offset = (screen.screen_height - lineH) / 2;
            double percent_shade = lineH / screen.screen_height;

            screen.drawVerticalPixel(g, r * (screen_width/TOTAL_RAYS), (int) line_Offset, (int) lineH, winner, lineDistance, ty_step, ty_off, ray_posx, ray_posy, ray_angle, percent_shade, final_casted_type, map.enemy.state);
            //<------------------------------------------------------------------------------->


            //Prepare next ray
            ray_angle += ray_space;
            if(ray_angle < 0){
                ray_angle += 2 * Math.PI;
            }
            if(ray_angle > Math.PI * 2){
                ray_angle -= Math.PI * 2;
            }


        }
    }

    private int getIndexofPoints(int ray_x, int ray_y, int x_offset, int y_offset){
        if(x_offset != 0 && y_offset != 0){
            ray_x = ray_x + (x_offset / Math.abs(x_offset));
            ray_y = ray_y + (y_offset / Math.abs(y_offset));
        }

        int multi_y = ray_x / 50;
        int multi_x = ray_y / 50;

        return multi_x * 16 + multi_y;
    }



    private double getDistance(int ax, int ay, int bx, int by, double angle){
        double dx = bx - ax;
        double dy = by - ay;

        double rotatedX = dx * Math.cos(angle) - dy * Math.sin(angle);
        double rotatedY = dx * Math.sin(angle) + dy * Math.cos(angle);

        return Math.sqrt(rotatedX * rotatedX + rotatedY * rotatedY);
    }

    private boolean checkHit(){
        for(int r = 95; r <= 105; r++){
            if(current_vertical_pixels[r] == Type_Wall.KILLERWALL){
                return true;
            }
        }

        return false;
    }




    //<-------MAIN CHARACTER KEY LISTENERS-------->

    private void processInput(long elapsedTime) {
        double seconds = elapsedTime / 1_000_000_000.0;
        int new_x = (int) (mainCharacter.delta_x * seconds);
        int new_y = (int) (mainCharacter.delta_y * seconds);
        float rotation_speed = 0.04F;

        if (pressedKeys.contains(KeyEvent.VK_W)) {
            if (!collidesWithWalls(new_x, new_y)) {
                mainCharacter.move_forward(seconds);
            }

        }

        if (pressedKeys.contains(KeyEvent.VK_A)) {
            mainCharacter.update_deltas(-rotation_speed);
        }

        if (pressedKeys.contains(KeyEvent.VK_S)) {
            if (!collidesWithWalls(-new_x, -new_y)) {
                mainCharacter.move_backward(seconds);
            }
        }

        if (pressedKeys.contains(KeyEvent.VK_D)) {
            mainCharacter.update_deltas(rotation_speed);
        }
    }



    private void setupKeyBindings() {
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        // Key binding for W
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "W pressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "W released");
        actionMap.put("W pressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                pressedKeys.add(KeyEvent.VK_W);
            }
        });
        actionMap.put("W released", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                pressedKeys.remove(KeyEvent.VK_W);
            }
        });

        // Key binding for A
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "A pressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "A released");
        actionMap.put("A pressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                pressedKeys.add(KeyEvent.VK_A);
            }
        });
        actionMap.put("A released", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                pressedKeys.remove(KeyEvent.VK_A);
            }
        });

        // Key binding for S
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "S pressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "S released");
        actionMap.put("S pressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                pressedKeys.add(KeyEvent.VK_S);
            }
        });
        actionMap.put("S released", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                pressedKeys.remove(KeyEvent.VK_S);
            }
        });

        // Key binding for D
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "D pressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "D released");
        actionMap.put("D pressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                pressedKeys.add(KeyEvent.VK_D);
            }
        });
        actionMap.put("D released", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                pressedKeys.remove(KeyEvent.VK_D);
            }
        });
    }


    //<-------GUN KEY LISTENERS-------->
    @Override
    public void mouseClicked(MouseEvent e) {
        if(!GameOver){
            boolean result = mainCharacter.gun.shoot(checkHit(), map.enemy);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    //<----------------------------RESTART GAME----------------------->
    public void restart_game(){
        pressedKeys = new HashSet<>();
        option_ctr = 0;
        map = new Map();
        mainCharacter.gun.setBullets(5);
        mainCharacter.setPosition(75, 325);
        mainCharacter.angle = 4.9;
        GameOver = false;
        time_score = 0;
        counter_instance = 0;
        gsp.hide_panel();
    }
}
