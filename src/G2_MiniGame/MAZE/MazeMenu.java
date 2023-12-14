package G2_MiniGame.MAZE;

import G2_MiniGame.MAZE.Maze.MenuGamePanel;
import G2_MiniGame.Player;
import G2_MiniGame.PlayersHandler;

import javax.swing.*;

import static G2_MiniGame.Main.playSound;

public class MazeMenu extends JFrame {

    private JPanel jpMazeMenu;
    private JPanel jpLeaderboards;
    private JButton btnLeadHome;
    private JTextField tfFirst;
    private JLabel lblFirst;
    private JTextField tfSecond;
    private JLabel lblSecond;
    private JTextField tfThird;
    private JLabel lblThird;
    private JTextField tfFourth;
    private JLabel lblFourth;
    private JTextField tfFifth;
    private JLabel lblFifth;
    PlayersHandler handler = PlayersHandler.getInstance();

    public MazeMenu(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,800);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Maze");

        createButtonListeners();
        displayMenu();
    }

    private void createButtonListeners(){
        btnLeadHome.addActionListener(e -> {
            playSound("src/G2_MiniGame/Audio/click.wav");
            displayMenu();
        });
    }
    public void displayMenu(){
        setContentPane(jpMazeMenu);
        revalidate();
        repaint();
    }

    public void displayLeaderboards(){
        setContentPane(jpLeaderboards);
        revalidate();
        repaint();

        JTextField[] tops = {tfFirst, tfSecond, tfThird, tfFourth, tfFifth};
        JLabel[] lblScores = {lblFirst, lblSecond, lblThird, lblFourth, lblFifth};

        handler.sortWordleScore();
        int i = 0;
        for (Player p : handler.getPlayersList()){
            if (i < 5){
                tops[i].setText(p.getUsername());
                lblScores[i].setText(String.valueOf(p.getMazeScore()));
                i++;
            }
        }
    }

    private void createUIComponents() {
        jpMazeMenu = new MenuGamePanel(this);
    }
}
