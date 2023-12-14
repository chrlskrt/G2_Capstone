package G2_MiniGame.TAKYAN;

import G2_MiniGame.Main;
import G2_MiniGame.MiniGame_MainMenu;
import G2_MiniGame.Player;
import G2_MiniGame.PlayersHandler;
import G2_MiniGame.TAKYAN.Entities.Ball;
import G2_MiniGame.TAKYAN.GameScreens.TakyanGameScreen;

import javax.swing.*;

public class TakyanMenu extends JFrame{
    private JPanel jpTakyanMenu;
    private JPanel jpHowToPlay;
    private JPanel jpLeaderboards;
    private JPanel jpTakyanGame;
    private JButton btnHome;
    private JLabel lblPlayer;
    private JButton btnViewLeaderboards;
    private JButton btnHowToPlay;
    private JButton btnPlayGame;
    private JButton btnHowHome;
    private JTextArea taHowToPlay;
    private JButton btnLeadHome;
    private JTextField tfFirst;
    private JTextField tfSecond;
    private JLabel lblSecond;
    private JTextField tfThird;
    private JLabel lblThird;
    private JTextField tfFourth;
    private JLabel lblFourth;
    private JTextField tfFifth;
    private JLabel lblFifth;
    private JLabel lblFirst;
    PlayersHandler handler = PlayersHandler.getInstance();

    public TakyanMenu(){
        setSize(800,600);
        setTitle("Takyan");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        ImageIcon logo = new ImageIcon("src/G2_MiniGame/TAKYAN/Assets/ball.png");
        setIconImage(logo.getImage());

        addButtonListeners();
        displayMenu();
    }

    private void addButtonListeners(){
        // Menu Tab
        btnHome.addActionListener(e -> {
            new MiniGame_MainMenu(true);
            dispose();
        });

        btnViewLeaderboards.addActionListener(e -> displayLeaderboards());

        btnHowToPlay.addActionListener(e -> displayHowToPlay());

        btnPlayGame.addActionListener(e -> displayTakyanGameScreen());

        // HowToPlay tab
        btnHowHome.addActionListener(e -> displayMenu());

        // Leaderboards tab
        btnLeadHome.addActionListener(e -> {
            Main.clip.start();
            displayMenu();
        });
    }

    public void displayMenu(){
        setContentPane(jpTakyanMenu);
        revalidate();
        repaint();

        lblPlayer.setText(MiniGame_MainMenu.currentPlayer.getUsername());
    }

    private void displayHowToPlay(){
        setContentPane(jpHowToPlay);
        revalidate();
        repaint();
    }

    private void displayLeaderboards(){
        setContentPane(jpLeaderboards);
        revalidate();
        repaint();

        JTextField[] tops = {tfFirst, tfSecond, tfThird, tfFourth, tfFifth};
        JLabel[] lblScores = {lblFirst, lblSecond, lblThird, lblFourth, lblFifth};

        handler.sortTakyanScore();
        int i = 0;
        for (Player p: handler.getPlayersList()){
            if (i < 5){
                tops[i].setText(p.getUsername());
                lblScores[i].setText(String.valueOf(p.getTakyanScore()));
                i++;
            }
        }
    }

    private void displayTakyanGameScreen(){
        setContentPane(jpTakyanGame);
        revalidate();
        repaint();

        Main.stopSound();
        ((TakyanGameScreen) jpTakyanGame).startGame();
        Ball.GameOver = false;
    }

    private void createUIComponents() {
        jpTakyanGame = new TakyanGameScreen(this);
    }
}
