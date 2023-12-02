package G2_Capstone;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordleGame extends JFrame{
    private JPanel jpLanding;
    private JLabel jlblPlayerName;
    private JButton btnHome;
    private JLabel jlblPlayer;
    Player currPlayer = null;
    WordleGame(Player currPlayer){
        this.setTitle("Wordle Game");
        this.setSize(600,700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(jpLanding);
        createActionListeners();
        this.currPlayer = currPlayer;
        jlblPlayer.setText(currPlayer.username);
    }

    public void createActionListeners(){
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Game(currPlayer);
                dispose();
            }
        });
    }
}
