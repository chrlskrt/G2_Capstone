package WORDLE_PROJ;

import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class WordleGUI extends JFrame {

    private JButton btnWelcPlay;
    private JPasswordField pfLogPassword;
    private JButton btnLogLog;
    private JButton btnLogSign;
    private JTextField tfLogUsername;
    private JTextField tfSignUsername;
    private JPasswordField pfSignPassword;
    private JButton btnCreateAcc;
    private JButton btnPlayGame;
    private JButton btnHowToPlay;
    private JButton btnViewLeaderboards;
    private JButton btnTip;
    private JLabel jlblPlayerName;
    private JButton btnLogOut;
    private JPanel jpGameProper;
    private JButton btnGameHome;
    private JPanel jpHomePage;
    private JPanel jpUserSign;
    private JPanel jpUserLog;
    private JPanel jpWelcome;
    private JButton btnSignLog;
    private JPanel jpLeaderboards;
    private JButton btnLHome;
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
    private JPanel jpHow;
    private JButton btnHowHome;
    private JTextArea taHowToPlay;
    private Players players = new Players();
    private ArrayList <String> tips = new ArrayList<>();
    private ArrayList <String> howtoplay = new ArrayList<>();
    private Player currPlayer = null;
    WordleGUI(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 700);
        this.setLocationRelativeTo(null);
        this.setTitle("Wordle Game");
        this.setResizable(false);
        this.setVisible(true);
        loadFiles();
        createButtonListeners();
        displayWelcome();
    }

    public void createButtonListeners(){
        // welcome panel
        btnWelcPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayUserLogIn();
            }
        });

        // log-in panel
        btnLogLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = tfLogUsername.getText();
                char[] pass = pfLogPassword.getPassword();

                int indexP = players.handleLogIn(user, pass);
                if (indexP >= 0){
                    currPlayer = players.playerslist.get(indexP);
                    tfLogUsername.setText("");
                    pfLogPassword.setText("");
                    displayHomePage();
                }

            }
        });

        btnLogSign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfLogUsername.setText("");
                pfLogPassword.setText("");
                displayUserSignUp();
            }
        });

        // sign-up panel
        tfSignUsername.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB){
                    String username = tfSignUsername.getText();
                    if (username.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Please enter a username");
                    } else if (players.isUsernameTaken(username)){
                        JOptionPane.showMessageDialog(null, "Account already exists. Choose another name or log in.");
                    }
                }
            }
        });

        pfSignPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = tfSignUsername.getText();

                if (username.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please enter a username");
                } else if (players.isUsernameTaken(username)){
                    JOptionPane.showMessageDialog(null, "Account already exists. Choose another name or log in.");
                }
            }
        });

        btnCreateAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = tfSignUsername.getText();
                char[] password = pfSignPassword.getPassword();
                String pw = String.valueOf(password);

                if (password.length < 8 && (!pw.matches(".*[a-zA-Z].*") || !pw.matches(".*\\d.*"))) {
                    JOptionPane.showMessageDialog(null,"Password must be more than 8.\nPassword must have at least one letter and one number.");
                }else if(password.length < 8){
                    JOptionPane.showMessageDialog(null,"Password must be more than 8.");
                }else if((!pw.matches(".*[a-zA-Z].*") || !pw.matches(".*\\d.*"))) {
                    JOptionPane.showMessageDialog(null,"Password must have at least one letter and one number.");
                } else {
                    players.handleCreateUser(user, password);

                    int indexP = players.playerslist.size() - 1;
                    currPlayer = players.playerslist.get(indexP);

                    tfSignUsername.setText("");
                    pfSignPassword.setText("");
                    displayHomePage();
                }
            }
        });

        btnSignLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfSignUsername.setText("");
                pfSignPassword.setText("");
                displayUserLogIn();
            }
        });
        
        // game panel
        btnGameHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Go back to home and discard Game?","Confirming exit...",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

                if (i == JOptionPane.YES_OPTION){
                    displayHomePage();
                }
            }
        });


        // leaderboards panel
        btnLHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayHomePage();
            }
        });

        // home page panel
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayWelcome();
            }
        });

        btnPlayGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayGameProper();
            }
        });

        btnHowToPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayHowToPlay();
            }
        });

        btnHowHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayHomePage();
            }
        });

        btnViewLeaderboards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayLeaderboards();
            }
        });

        btnTip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random r = new Random();
                int indtip = r.nextInt(tips.size());
                String tip = tips.get(indtip);
                JOptionPane.showMessageDialog(null,tip);
            }
        });
    }



    public void loadFiles(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Acer\\Desktop\\capstone\\src\\WORDLE_PROJ\\players.txt"));
            String playerInfo;
            while ((playerInfo = br.readLine()) != null) {
                String [] info = playerInfo.split(" / ");
                players.playerslist.add(new Player(info));
            }
        } catch (FileNotFoundException e) {
            System.out.println("nO fiLe cannot open program");
        } catch (IOException e) {
            System.out.println("incorrect file");
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Acer\\Desktop\\capstone\\src\\WORDLE_PROJ\\todaystip.txt"));
            String line;
            while ((line = br.readLine()) != null){
                tips.add(line);
            }
        } catch (FileNotFoundException f){
            System.out.println("file cannot be found huhu");
        } catch (IOException e) {
            System.out.println("huhuhhu");
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Acer\\Desktop\\capstone\\src\\WORDLE_PROJ\\howtoplay.txt"));
            String line;
            while ((line = br.readLine()) != null){
                howtoplay.add(line);
            }
        } catch (Exception e){
            System.out.println("huhuh");
        }
    }

    public void displayWelcome(){
        this.setContentPane(jpWelcome);
    }

    public void displayUserLogIn(){
        this.setContentPane(jpUserLog);
        revalidate();
        repaint();
    }

    public void displayUserSignUp(){
        this.setContentPane(jpUserSign);
        revalidate();
        repaint();
    }

    public void displayHomePage(){
        this.setContentPane(jpHomePage);
        revalidate();
        repaint();
        jlblPlayerName.setText(currPlayer.getUsername());
    }

    public void displayGameProper(){
        this.setContentPane(jpGameProper);
        revalidate();
        repaint();
    }

    private void displayHowToPlay() {
        this.setContentPane(jpHow);
        revalidate();
        repaint();

        taHowToPlay.setText("");
        for (String s : howtoplay) {
            taHowToPlay.append(s + "\n");
        }
    }
    public void displayLeaderboards(){
        this.setContentPane(jpLeaderboards);
        revalidate();
        repaint();

        JTextField[] tops = {tfFirst, tfSecond, tfThird, tfFourth, tfFifth};
        JLabel[] scoreslbl = {lblFirst, lblSecond, lblThird, lblFourth, lblFifth};

        players.sort();
        int i = 0;
        for (Player p : players.playerslist){
            if (i < 5){
                tops[i].setText(p.getUsername());
                scoreslbl[i].setText(String.valueOf(p.getScore()));
                i++;
            }
        }
    }
}
