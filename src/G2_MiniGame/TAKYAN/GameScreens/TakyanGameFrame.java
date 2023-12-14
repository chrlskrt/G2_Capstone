package G2_MiniGame.TAKYAN.GameScreens;

import G2_MiniGame.TAKYAN.Entities.Ball;

import javax.swing.*;

public class TakyanGameFrame extends JFrame {
    TakyanGameScreen takyan;
    public TakyanGameFrame(){
        setSize(800,600);
        setTitle("Takyan");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        ImageIcon logo = new ImageIcon("src/G2_MiniGame/TAKYAN/Assets/ball.png");
        setIconImage(logo.getImage());
//        add(takyan);
        takyan = new TakyanGameScreen(this);
        takyan.startGame();
        Ball.GameOver = false;
    }
}
