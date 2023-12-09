package G2_Capstone.WORDLE;

import G2_Capstone.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordleGame extends JPanel {
    JButton btnHome;
    JButton btnEnter;
    JLabel lblTitle;
    TilePanel lpWordle;
    JFrame frame = null;
    Player current = null;
    KeyTypedReader k = null;

    public void startGame(){
        lpWordle.requestFocusInWindow();

    }
    public WordleGame(WordleLandingPage fatherFrame) {
        this.setSize(1000,800);
        this.setLayout(null);
        btnHome = new JButton("⌂");
        lblTitle = new JLabel("WORDLE");
        lpWordle = new TilePanel();
        btnEnter = new JButton("Enter");

        buildComponents();

        k = new KeyTypedReader(fatherFrame,lpWordle);

        frame = fatherFrame;
    }

    public void buildComponents(){
        // Home Button
        btnHome.setOpaque(true);
        btnHome.setBounds(0,0,50,30);
        add(btnHome);

        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    int i = JOptionPane.showConfirmDialog(null, "Go back to home and discard Game?","Confirming exit...",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

                    if (i == JOptionPane.YES_OPTION){
                        endGame();
                        ((WordleLandingPage) frame).displayLanding();
                    }
            }
        });

        // JLabel Title
        lblTitle.setForeground(Color.MAGENTA);
        lblTitle.setFont(new Font("Ravie", Font.PLAIN, 36));
        lblTitle.setOpaque(true);
        lblTitle.setBounds(300,40,400,100);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle);

        // Panel for Wordle tiles
        lpWordle.setBounds(275,150,lpWordle.width,lpWordle.height);
        add(lpWordle);

        // Button for "Enter"
        btnEnter.setOpaque(true);
        btnEnter.setBounds(400,700,200,30);
        add(btnEnter);

        // set position back to 0
        TilePositionTracker.setCOL(0);
        TilePositionTracker.setROW(0);
    }

    public void endGame(){
        lpWordle.refresh();
        TilePositionTracker.setROW(0);
        TilePositionTracker.setCOL(0);
    }
}
