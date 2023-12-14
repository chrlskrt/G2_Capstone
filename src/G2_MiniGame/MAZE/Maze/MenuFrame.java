package G2_MiniGame.MAZE.Maze;

import javax.swing.*;

public class MenuFrame extends JFrame {

    MenuGamePanel menu_panel = new MenuGamePanel(this);
//    public MenuFrame(){
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setTitle("Maze Menu Frame");
//        setContentPane(menu_panel);
//        pack();
//        setLocationRelativeTo(null);
//        setVisible(true);
//        new Thread( menu_panel).start();
//
//    }
    public void start(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Maze Menu Frame");
        setContentPane(menu_panel);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        new Thread( menu_panel).start();
    }

    public static void main(String[] args) {

        MenuFrame menu_frame = new MenuFrame();
        menu_frame.start();
    }
}
