package G2_MiniGame.WORDLE;

import G2_MiniGame.MiniGame_MainMenu;
import G2_MiniGame.HandlePlayers;
import G2_MiniGame.Player;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import static G2_MiniGame.Main.playSound;

public class WordleMenu extends JFrame{
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
    private JButton btnLeadHome;
    private JLabel lblFirst;
    private JLabel lblSecond;
    private JLabel lblThird;
    private JLabel lblFourth;
    private JLabel lblFifth;
    private JPanel jpWordleGame;

    private final ArrayList<String> tips = new ArrayList<>();
    private final ArrayList<String> howToPlay = new ArrayList<>();
    HandlePlayers handler = HandlePlayers.getInstance();

    public WordleMenu(){
        this.setTitle("Wordle Game");
        this.setSize(1000,800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        createActionListeners();
        loadFiles();
        lblPlayerName.setText(MiniGame_MainMenu.currPlayer.getUsername());
        displayLanding();
    }

    public void createActionListeners(){
        btnHome.addActionListener(e -> {
            playSound("src/G2_MiniGame/Audio/click.wav");
            new MiniGame_MainMenu(true);
            dispose();
        });

        btnHowToPlay.addActionListener(e -> {
            playSound("src/G2_MiniGame/Audio/click.wav");
            displayHowToPlay();
        });

        btnHowHome.addActionListener(e -> {
            playSound("src/G2_MiniGame/Audio/click.wav");
            displayLanding();
        });

        btnViewLeaderboards.addActionListener(e -> {
            playSound("src/G2_MiniGame/Audio/click.wav");
            displayLeaderboards();
        });

        btnLeadHome.addActionListener(e -> {
            playSound("src/G2_MiniGame/Audio/click.wav");
            displayLanding();
        });

        btnTip.addActionListener(e -> {
            playSound("src/G2_MiniGame/Audio/click.wav");
            Random r = new Random();
            String tip = tips.get(r.nextInt(tips.size()));
            JOptionPane.showMessageDialog(null, tip);
        });

        btnPlayGame.addActionListener(e -> {
            playSound("src/G2_MiniGame/Audio/click.wav");
            displayGameProper();
        });
    }

    public void loadFiles(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/G2_MiniGame/TextFiles/todaystip.txt"));
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
            BufferedReader br = new BufferedReader(new FileReader("src/G2_MiniGame/TextFiles/howtoplay.txt"));
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
        for (Player p : handler.getPlayersList()){
            if (i < 5){
                tops[i].setText(p.getUsername());
                lblScores[i].setText(String.valueOf(p.getWordleScore()));
                i++;
            }
        }
    }

    public void displayGameProper(){
        this.setContentPane(jpWordleGame);
        this.revalidate();
        this.repaint();

        ((WordleGame) jpWordleGame).startGame();
    }

    private void createUIComponents() {
        jpWordleGame = new WordleGame(this);
    }
}
