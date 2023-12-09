package G2_Capstone.WordleGameProper;

import G2_Capstone.Player;
import G2_Capstone.WordleLandingPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordleGame extends JFrame {
    JButton btnHome;
    JButton btnEnter;
    JLabel lblTitle;
    TilePanel lpWordle;
    Player current;
    KeyTypedReader k;
    public WordleGame(Player player) {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600,700);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);
        btnHome = new JButton("⌂");
        lblTitle = new JLabel("WORDLE");
        lpWordle = new TilePanel();
        btnEnter = new JButton("Enter");

        buildComponents();

        k = new KeyTypedReader(this,lpWordle);

        current = player;
    }

    public void buildComponents(){
        // Home Button
        btnHome.setOpaque(true);
        btnHome.setBounds(0,0,50,30);
        add(btnHome);

        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new WordleLandingPage(current);
            }
        });

        // JLabel Title
        lblTitle.setForeground(Color.MAGENTA);
        lblTitle.setFont(new Font("Ravie", Font.PLAIN, 36));
        lblTitle.setOpaque(true);
        lblTitle.setBounds(100,40,400,100);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle);

        // Panel for Wordle tiles
        lpWordle.setBounds(125,150,lpWordle.width,lpWordle.height);
        add(lpWordle);
        lpWordle.requestFocusInWindow();

        // Button for "Enter"
        btnEnter.setOpaque(true);
        btnEnter.setBounds(200,590,200,30);
        add(btnEnter);

        // set position back to 0
        TilePositionTracker.setCOL(0);
        TilePositionTracker.setROW(0);
    }
}
