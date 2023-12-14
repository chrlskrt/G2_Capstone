package G2_MiniGame;

import G2_MiniGame.MAZE.MazeMenu;
import G2_MiniGame.TAKYAN.TakyanMenu;
import G2_MiniGame.WORDLE.WordleMenu;

import javax.swing.*;
import java.awt.event.*;

import static G2_MiniGame.Main.playSound;

public class MiniGame_MainMenu extends JFrame {
    private JButton btnWelcomePlay;
    private JPanel jpWelcome;
    private JPanel jpLogIn;
    private JPanel jpSignUp;
    private JTextField tfLogUsername;
    private JPasswordField pfLogPassword;
    private JButton btnLogPanelLogIn;
    private JButton btnLogPanelSignUp;
    private JTextField tfSignUsername;
    private JPasswordField pfSignPassword;
    private JButton btnCreateAcc;
    private JButton btnSignUpLogIn;
    private JPanel jpGameMenu;
    private JButton btnPlayWordle;
    private JButton btnPlayMaze;
    private JButton btnLogOut;
    private JLabel jlPlayerName;
    private JLabel jlLogPanelUserErr;
    private JLabel jlLogPanelPWErr;
    private JLabel jlblSignUserErr;
    private JLabel jlblSignPassErr;
    private JButton btnPlayTakyan;
    PlayersHandler handler = PlayersHandler.getInstance();
    public static Player currentPlayer;

    public void setUp(){
        this.setTitle("Game");
        this.setSize(1000,800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        createButtonListeners();
    }

    public MiniGame_MainMenu(Boolean fromOtherFrame){
        if (fromOtherFrame){
            setUp();
            displayMenu();
        }
    }

    public MiniGame_MainMenu(){
        setUp();
        displayWelcome();

        // set
//        setCurrPlayer(handler.getPlayersList().get(0));
//        displayMenu();
    }

    public void createButtonListeners(){
        // Welcome Panel Button
        btnWelcomePlay.addActionListener(e -> {
            playSound("src/G2_MiniGame/Audio/click.wav");
            displayLogIn();
        });

        // Log-in panel Button
        btnLogPanelLogIn.addActionListener(e -> {
            playSound("src/G2_MiniGame/Audio/click.wav");
            String user = tfLogUsername.getText();
            char[] pass = pfLogPassword.getPassword();

            // gets index of the player in the list
            int indexP = handler.handleLogIn(user, pass);

            // if index >= 0 -> player exist, thus log-in and set currPlayer
            if (indexP >= 0){
                setCurrPlayer(handler.getPlayersList().get(indexP));
                tfLogUsername.setText("");
                pfLogPassword.setText("");
                displayMenu();
            }

            if (indexP == -1){
                jlLogPanelPWErr.setText("Wrong password! Try again.");
            }

            if (indexP == -2){
                jlLogPanelUserErr.setText("Account does not exist.");
            }
        });

        btnLogPanelSignUp.addActionListener(e -> {
            playSound("src/G2_MiniGame/Audio/click.wav");
            jlLogPanelPWErr.setText("");
            jlLogPanelUserErr.setText("");
            tfLogUsername.setText("");
            pfLogPassword.setText("");
            displaySignUp();
        });

        // Sign-up panel
        tfSignUsername.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB){
                    checkSignUpUsername();
                }
            }
        });

        pfSignPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                checkSignUpUsername();
            }
        });

        btnCreateAcc.addActionListener(e -> {
            playSound("src/G2_MiniGame/Audio/click.wav");
            String username = tfSignUsername.getText();
            char[] password = pfSignPassword.getPassword();
            String pw = String.valueOf(password);

            if (username == null){
                jlblSignUserErr.setText("Please enter username.");
                return;
            }

            if (pw == null){
                jlblSignPassErr.setText("Please enter password.");
                return;
            }

            if (password.length < 8 && (!pw.matches(".*[a-zA-Z].*") || !pw.matches(".*\\d.*"))) {
                JOptionPane.showMessageDialog(null,"Password must be more than 8.\nPassword must have at least one letter and one number.");
            }else if(password.length < 8){
                JOptionPane.showMessageDialog(null,"Password must be more than 8.");
            }else if((!pw.matches(".*[a-zA-Z].*") || !pw.matches(".*\\d.*"))) {
                JOptionPane.showMessageDialog(null,"Password must have at least one letter and one number.");
            } else {
                handler.handleCreateUser(username, password);

                int indexP = handler.getPlayersList().size() - 1;
                setCurrPlayer(handler.getPlayersList().get(indexP));

                jlblSignUserErr.setText("");
                jlblSignPassErr.setText("");
                tfSignUsername.setText("");
                pfSignPassword.setText("");
                displayMenu();
            }
        });

        btnSignUpLogIn.addActionListener(e -> {
            playSound("src/G2_MiniGame/Audio/click.wav");
            jlblSignUserErr.setText("");
            jlblSignPassErr.setText("");
            tfSignUsername.setText("");
            pfSignPassword.setText("");
            displayLogIn();
        });

        // Home Panel buttons
        btnLogOut.addActionListener(e -> {
            playSound("src/G2_MiniGame/Audio/click.wav");
            int i = JOptionPane.showConfirmDialog(null, "Are you sure?","Confirming log-out request...",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

            if (i == JOptionPane.YES_OPTION){
                unsetCurrPlayer();
                handler.updatePlayersFile();
                displayWelcome();
            }
        });

        btnPlayWordle.addActionListener(e -> {
            playSound("src/G2_MiniGame/Audio/click.wav");
            new WordleMenu();
            dispose();
        });

        btnPlayMaze.addActionListener(e -> {
            playSound("src/G2_MiniGame/Audio/click.wav");
            new MazeMenu();
            dispose();
        });
        btnPlayTakyan.addActionListener(e -> {
            playSound("src/G2_MiniGame/Audio/click.wav");
            new TakyanMenu();
            dispose();
        });
    }

    private void unsetCurrPlayer(){
        currentPlayer = null;
    }
    private void setCurrPlayer(Player p){
        currentPlayer = p;
    }

    private void checkSignUpUsername(){
        String username = tfSignUsername.getText();

        if (username.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please enter a username");
        } else if (handler.isUsernameTaken(username)){
            JOptionPane.showMessageDialog(null, "Account already exists. Choose another name or log in.");
        }
    }

    public void displayWelcome(){
        this.setContentPane(jpWelcome);
        this.revalidate();
        this.repaint();
    }

    public void displayLogIn(){
        this.setContentPane(jpLogIn);
        this.revalidate();
        this.repaint();
    }

    public void displaySignUp(){
        this.setContentPane(jpSignUp);
        this.revalidate();
        this.repaint();
    }

    public void displayMenu(){
        this.setContentPane(jpGameMenu);
        this.revalidate();
        this.repaint();
        jlPlayerName.setText(currentPlayer.getUsername());
    }
}
