package G2_MiniGame.MAZE.Maze;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Map extends GamePanel {
    ArrayList<Integer> enemy_steps = new ArrayList<>();

    Wall enemy;
    int[] map= {
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
            1,5,1,0,0,0,0,1,0,0,0,0,1,0,0,1,
            1,0,1,0,0,1,0,1,0,1,0,0,1,0,0,1,
            1,0,1,0,0,1,0,1,0,1,0,0,1,0,0,1,
            1,0,1,0,0,1,0,0,0,1,0,0,0,0,0,1,
            1,0,1,0,0,1,1,1,1,1,1,1,0,1,1,1,
            1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,
            1,0,1,1,0,1,1,1,1,1,1,0,1,1,1,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,1,1,1,0,0,1,0,0,1,1,1,1,0,1,
            1,0,1,0,0,0,0,1,0,0,1,0,0,0,0,1,
            1,0,1,0,0,0,0,1,0,0,1,0,0,0,0,1,
            1,0,1,1,1,0,0,0,0,0,1,0,0,0,0,2,
            1,0,0,0,0,0,0,1,0,0,1,0,0,1,1,1,
            1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
    };

    public ArrayList<Wall> walls;

    public Map(){
        walls = new ArrayList<>();
        enemy_steps.add(33);
        enemy_steps.add(49);
        enemy_steps.add(65);

        initializeWalls();
    }


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Wall wall : walls){
            wall.paintComponent(g);
        }
    }



    public void initializeWalls(){
        walls.clear();
        int running_x = 0;
        int running_y = 0;
        for(int i = 0; i < map.length; i++){
            Wall w;
            if(map[i] == 1){
                w = (Wall) new Wall.WallBuilder().index_in_map(i).position(running_x,running_y).size(50, 50).build();
            } else if (map[i] == 2){
                w = (Wall) new Wall.WallBuilder().index_in_map(i).type(Type_Wall.DOOR).position(running_x,running_y).size(50, 50).build();
            } else if (map[i] == 5){
                w = (Wall) new Wall.WallBuilder().index_in_map(i).type(Type_Wall.KILLERWALL).position(running_x,running_y).size(50, 50).build();
                w.state = false;
                w.animation_speed = 180;
                w.animation_counter = 0;
                enemy = w;
            } else {
                w = (Wall) new Wall.WallBuilder().index_in_map(i).type(Type_Wall.NONSOLID).setColor(Color.BLACK).size(0, 0).position(running_x,running_y).build();
            }

            walls.add(w);

            running_x += (50);
            if( (i+1) % 16 == 0){
                running_x = 0;
                running_y += (50);
            }

        }
    }

    public void update_map(int mc_index){
        Random random = new Random();
        int randomNumber = random.nextInt(4) + 1;

        if(enemy.state){
        } else {
            move(enemy_steps.get(0));
            enemy_steps.remove(0);
        }
    }

    public void move(int new_index){
        int current = enemy.index_in_map;

        //swap in map
        map[enemy.index_in_map] = 0;
        map[new_index] = 5;

        //swap sa attribute na index
        enemy.index_in_map = new_index;
        walls.get(new_index).index_in_map = current;

        //swap in array
        Collections.swap(walls, current, new_index);
    }

    public void printMap() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                int index = i * 16 + j;
                System.out.print(map[index] + " ");
            }
            System.out.println();
        }
    }


}
