package G2_MiniGame.MAZE;

import G2_MiniGame.MAZE.Maze.MapFrame;
import G2_MiniGame.MAZE.Maze.MapFrameGamePanel;
import G2_MiniGame.Main;
import G2_MiniGame.MiniGame_MainMenu;
import G2_MiniGame.Player;
import G2_MiniGame.PlayersHandler;

import javax.swing.*;

import static G2_MiniGame.Main.playSound;

public class MazeMenu extends JFrame {

    private JPanel jpMazeMenu;
    private JPanel jpLeaderboards;
    private JButton btnLeadHome;
    private JTextField tfFirst;
    private JLabel lblFirst;
    private JTextField tfSecond;
    private JLabel lblSecond;
    private JTextField tfThird;
    private JLabel lblThird;
    private JTextField tfFourth;
    private JLabel lblFourth;
    private JTextField tfFifth;
    private JLabel lblFifth;
    private JButton btnHome;
    private JButton btnPlayGame;
    private JButton btnViewLeaderboards;
    private JPanel jpMazeGame;
    private JLabel lblPlayer;
    PlayersHandler handler = PlayersHandler.getInstance();

    public MazeMenu(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,800);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Maze");

        createButtonListeners();
        displayMenu();
    }

    private void createButtonListeners(){
        btnHome.addActionListener(e -> {
            playSound("src/G2_MiniGame/Audio/click.wav");
            new MiniGame_MainMenu(true);
        });

        btnViewLeaderboards.addActionListener(e -> {
            playSound("src/G2_MiniGame/Audio/click.wav");
            displayLeaderboards();
        });

        btnLeadHome.addActionListener(e -> {
            playSound("src/G2_MiniGame/Audio/click.wav");
            displayMenu();
        });

        btnPlayGame.addActionListener(e -> {
            playSound("src/G2_MiniGame/Audio/click.wav");
//            displayMazeGame();
            MapFrame mapFrame = new MapFrame();
            mapFrame.start();
        });
    }

    public void displayMazeGame(){
        setContentPane(jpMazeGame);
        revalidate();
        repaint();

        Main.stopSound();
        new Thread((MapFrameGamePanel) jpMazeGame).start();
    }
    public void displayMenu(){
        setContentPane(jpMazeMenu);
        revalidate();
        repaint();

        lblPlayer.setText(MiniGame_MainMenu.currentPlayer.getUsername());
    }

    public void displayLeaderboards(){
        setContentPane(jpLeaderboards);
        revalidate();
        repaint();

        JTextField[] tops = {tfFirst, tfSecond, tfThird, tfFourth, tfFifth};
        JLabel[] lblScores = {lblFirst, lblSecond, lblThird, lblFourth, lblFifth};

        handler.sortWordleScore();
        int i = 0;
        for (Player p : handler.getPlayersList()){
            if (i < 5){
                tops[i].setText(p.getUsername());
                lblScores[i].setText(String.valueOf(p.getMazeScore()));
                i++;
            }
        }
    }

    private void createUIComponents() {
        jpMazeGame = new MapFrameGamePanel();
    }
}
