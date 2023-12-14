package G2_MiniGame.MAZE.Maze;

import javax.swing.*;
public class MapFrame extends JFrame {
    MapFrameGamePanel mapGamePanel = new MapFrameGamePanel(this);

    public void start(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Map Frame");
        setContentPane(mapGamePanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        new Thread(mapGamePanel).start();
    }

}
