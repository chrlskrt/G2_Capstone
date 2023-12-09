package G2_Capstone;

import G2_Capstone.WordleGameProper.WordleGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static G2_Capstone.Main.playSound;

public class WordleLandingPage extends JFrame{
    private JPanel jpLandingMenu;
    private JButton btnHome;
    private JLabel lblPlayerName;
    private JButton btnPlayGame;
    private JButton btnViewLeaderboards;
    private JButton btnTip;
    private JButton btnHowToPlay;
    private JPanel jpHowToPlay;
    private JButton btnHowHome;
    private JTextArea taHowToPlay;
    private JTextField tfFirst;
    private JTextField tfSecond;
    private JTextField tfThird;
    private JTextField tfFourth;
    private JTextField tfFifth;
    private JPanel jpLeaderboards;
    private JPanel jpGameProper;
    private JButton btnGameHome;
    private JButton btnLeadHome;
    private JLabel lblFirst;
    private JLabel lblSecond;
    private JLabel lblThird;
    private JLabel lblFourth;
    private JLabel lblFifth;
    private Player currPlayer = null;
    private final ArrayList<String> tips = new ArrayList<>();
    private final ArrayList<String> howToPlay = new ArrayList<>();
    private final HandlePlayers handler = HandlePlayers.getInstance();
    public WordleLandingPage(Player currPlayer){
        this.setTitle("Wordle Game");
        this.setSize(800,800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        createActionListeners();
        loadFiles();
        this.currPlayer = currPlayer;
        lblPlayerName.setText(currPlayer.username);
        displayLanding();
    }

    public void createActionListeners(){
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("src/G2_Capstone/Audio/click.wav");
                new Game(currPlayer);
                dispose();
            }
        });

        btnHowToPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("src/G2_Capstone/Audio/click.wav");
                displayHowToPlay();
            }
        });

        btnHowHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("src/G2_Capstone/Audio/click.wav");
                displayLanding();
            }
        });

        btnViewLeaderboards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("src/G2_Capstone/Audio/click.wav");
                displayLeaderboards();
            }
        });

        btnLeadHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("src/G2_Capstone/Audio/click.wav");
                displayLanding();
            }
        });

        btnTip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("src/G2_Capstone/Audio/click.wav");
                Random r = new Random();
                String tip = tips.get(r.nextInt(tips.size()));
                JOptionPane.showMessageDialog(null, tip);
            }
        });

        btnPlayGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("src/G2_Capstone/Audio/click.wav");
                displayGameProper();
            }
        });

        btnGameHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("src/G2_Capstone/Audio/click.wav");
                int i = JOptionPane.showConfirmDialog(null, "Go back to home and discard Game?","Confirming exit...",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

                if (i == JOptionPane.YES_OPTION){
                    displayLanding();
                }
            }
        });

    }

    public void loadFiles(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/G2_Capstone/TextFiles/todaystip.txt"));
            String line;
            while ((line = br.readLine()) != null){
                tips.add(line);
            }
        } catch (FileNotFoundException f){
            JOptionPane.showMessageDialog(null, "Error: File not found.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading file.");
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/G2_Capstone/TextFiles/howtoplay.txt"));
            String line;
            while ((line = br.readLine()) != null){
                howToPlay.add(line);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error loading file.");
        }
    }
    public void displayLanding(){
        this.setContentPane(jpLandingMenu);
        revalidate();
        repaint();
    }

    public void displayHowToPlay(){
        this.setContentPane(jpHowToPlay);
        revalidate();
        repaint();

        taHowToPlay.setText("");
        for (String line: howToPlay){
            taHowToPlay.append(line + "\n");
        }
    }

    public void displayLeaderboards(){
        this.setContentPane(jpLeaderboards);
        revalidate();
        repaint();

        JTextField[] tops = {tfFirst, tfSecond, tfThird, tfFourth, tfFifth};
        JLabel[] lblScores = {lblFirst, lblSecond, lblThird, lblFourth, lblFifth};

        handler.sort();
        int i = 0;
        for (Player p : handler.playerslist){
            if (i < 5){
                tops[i].setText(p.getUsername());
                lblScores[i].setText(String.valueOf(p.getScore()));
                i++;
            }
        }
    }

    public void displayGameProper(){
        this.dispose();
        new WordleGame(currPlayer);
    }
}
