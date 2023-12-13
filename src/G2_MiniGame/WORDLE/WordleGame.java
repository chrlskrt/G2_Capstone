package G2_MiniGame.WORDLE;

import javax.swing.*;
import java.awt.*;

public class WordleGame extends JPanel {
    JButton btnHome;
    JButton btnEnter;
    JLabel lblTitle;
    TilePanel lpWordle;
    JFrame frame = null;
    KeyboardListener k = null;
    EnterButtonListener e = null;
    WordHandler wordHandler = null;
    Color compBG = new Color(24,57,43);

    public void startGame(){
        lpWordle.requestFocusInWindow();
        wordHandler.generateAnswer();
        System.out.println(wordHandler.getAnswer());
    }
    public WordleGame(WordleMenu fatherFrame) {
        this.setBackground(new Color(236,236,163));
        this.setLayout(null);
        btnHome = new JButton("⌂");
        lblTitle = new JLabel("WORDLE");
        lpWordle = new TilePanel();
        btnEnter = new JButton("Enter");

        buildComponents();

        k = new KeyboardListener(this,lpWordle);
        e = new EnterButtonListener(this,lpWordle, btnEnter);
        wordHandler = WordHandler.getInstance();
        frame = fatherFrame;
    }

    public void buildComponents(){
        // Home Button
        btnHome.setBackground(compBG);
        btnHome.setForeground(Color.white);
        btnHome.setOpaque(true);
        btnHome.setBounds(0,0,50,30);
        add(btnHome);

        btnHome.addActionListener(e -> {
                int i = JOptionPane.showConfirmDialog(null, "Go back to home and discard Game?","Confirming exit...",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

                if (i == JOptionPane.YES_OPTION){
                    exit();
                }
        });

        // JLabel Title
        lblTitle.setForeground(new Color(32,76,57));
        lblTitle.setFont(new Font("Ravie", Font.PLAIN, 36));
        lblTitle.setOpaque(false);
        lblTitle.setBounds(300,40,400,100);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setVerticalAlignment(SwingConstants.CENTER);
        add(lblTitle);

        // Panel for Wordle tiles
        lpWordle.setBounds(275,150,lpWordle.width,lpWordle.height);
        lpWordle.setOpaque(false);
        add(lpWordle);

        // Button for "Enter"
        btnEnter.setBackground(compBG);
        btnEnter.setForeground(Color.white);
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

    public void exit(){
        endGame();
        ((WordleMenu) frame).displayLanding();
    }
}
