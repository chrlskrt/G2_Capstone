package G2_Capstone;

import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Game extends JFrame {
    private JButton btnWelcPlay;
    private JPanel jpWelcome;
    private JPanel jpLogIn;
    private JPanel jpSignUp;
    private JTextField tfLogUsername;
    private JPasswordField pfLogPassword;
    private JButton btnLogLog;
    private JButton btnLogSign;
    private JTextField tfSignUsername;
    private JPasswordField pfSignPassword;
    private JButton btnCreateAcc;
    private JButton btnSignLog;
    private JPanel jpHomePage;
    private JButton btnPlayWordle;
    private JButton btnPlayMaze;
    private JButton btnLogOut;
    private JLabel jlblPlayerName;
    private JLabel jlblLogUserErr;
    private JLabel jlblLogPassErr;
    private JLabel jlblSignUserErr;
    private JLabel jlblSignPassErr;
    private JButton btnViewLeaderboards;
    HandlePlayers handler = HandlePlayers.getInstance();
    Player currPlayer = null;

    public void setUp(){
        this.setTitle("Game");
        this.setSize(600,700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        createButtonListeners();

        // para ditso ranis wordlegame panel
//        currPlayer = handler.playerslist.get(0);
//        new WordleGame(currPlayer);
//        dispose();
    }
    Game(Player currPlayer){
        this.currPlayer = currPlayer;
        setUp();
        displayHome();
    }
    Game(){
        setUp();
        loadFiles();
        displayWelcome();
    }

    public void loadFiles(){
        try {

            BufferedReader br = new BufferedReader(new FileReader("players.txt"));
            String playerInfo;

            while((playerInfo = br.readLine()) != null){
                String[] info = playerInfo.split(" / ");
                handler.playerslist.add(new Player(info));
            }
        } catch (FileNotFoundException e) {
            System.out.println("no file. cannot continue program run.");
        } catch (IOException e) {
            System.out.println("incorrect");
        }
    }

    public void createButtonListeners(){
        // Welcome Panel btn
        btnWelcPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayLogIn();
            }
        });

        // Log-in panel btn
        btnLogLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = tfLogUsername.getText();
                char[] pass = pfLogPassword.getPassword();

                int indexP = handler.handleLogIn(user, pass);
                if (indexP >= 0){
                    currPlayer = handler.playerslist.get(indexP);
                    tfLogUsername.setText("");
                    pfLogPassword.setText("");
                    displayHome();
                }

                if (indexP == -1){
                    jlblLogPassErr.setText("Wrong password! Try again.");
                }

                if (indexP == -2){
                    jlblLogUserErr.setText("Account does not exist.");
                }
            }
        });

        btnLogSign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlblLogPassErr.setText("");
                jlblLogUserErr.setText("");
                tfLogUsername.setText("");
                pfLogPassword.setText("");
                displaySignUp();
            }
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

        btnCreateAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                    int indexP = handler.playerslist.size() - 1;
                    currPlayer = handler.playerslist.get(indexP);

                    jlblSignUserErr.setText("");
                    jlblSignPassErr.setText("");
                    tfSignUsername.setText("");
                    pfSignPassword.setText("");
                    displayHome();
                }
            }
        });

        btnSignLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlblSignUserErr.setText("");
                jlblSignPassErr.setText("");
                tfSignUsername.setText("");
                pfSignPassword.setText("");
                displayLogIn();
            }
        });

        // home panel
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Are you sure?","Confirming log-out request...",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

                if (i == JOptionPane.YES_OPTION){
                    currPlayer = null;
                    displayWelcome();
                }
            }
        });

        btnPlayWordle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WordleGame(currPlayer);
                dispose();
            }
        });

        btnPlayMaze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnViewLeaderboards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void checkSignUpUsername(){
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

    public void displayHome(){
        this.setContentPane(jpHomePage);
        this.revalidate();
        this.repaint();
        jlblPlayerName.setText(currPlayer.getUsername());
    }
}
