package WordlePackage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordleGUI extends JFrame{
    private JTabbedPane tabbedpane;
    private JButton leaderBoardsButton;
    private JButton changePlayerButton;
    private JButton todaySTipButton;
    private JPanel welcomepanel;
    private JPanel gamepanel;
    private JPanel leaderboardspanel;
    private JPanel panel;
    private JPanel playerspanel;
    private JComboBox comboboxPlayers;
    private JTextField tfUsername;
    private JTextField tfPassword;
    private JButton enterbutton;
    private JButton letsplayButton;
    private JLabel playersnamelabel;
    private JTextArea taHowtoPlay;
    private JPanel howtoplaypanel;
    private JButton howtoplaybutton;
    private JButton howtoplayhomebutton;
    private JButton gamehomebutton;
    private JButton leadbhomebutton;
    private JButton cphomebutton;
    private JPanel homepanel;
    private Players player = new Players();
    private List<String> tips = new ArrayList<>();

    WordleGUI() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 700);
        this.setLocationRelativeTo(null);
        this.setTitle("Wordle Game");
        this.setResizable(false);
        this.setVisible(true);
        loadPlayersFromFile();
        handleButtonsActionListener();
        displaywelcome();
    }

    public void handleButtonsActionListener(){
        // welcome panel
        todaySTipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tips.isEmpty()) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(tips.size());
                    String tip = tips.get(randomIndex);
                    JOptionPane.showMessageDialog(null, "Tip: " + tip);
                } else {
                    JOptionPane.showMessageDialog(null, "No tips available.");
                }
            }
        });

        leaderBoardsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayleaderboard();
            }
        });
        changePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayplayerslist();
            }
        });
        letsplayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaygamepanel();
            }
        });
        howtoplaybutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayhowtoplay();
            }
        });

        // how to play panel
        howtoplayhomebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaywelcome();
            }
        });

        // display game panel
        gamehomebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaywelcome();
            }
        });

        // leaderboards panel
        leadbhomebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaywelcome();
            }
        });

        // create player panel
        enterbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                String name, password;
                try {
                    name = tfUsername.getText().replace("\n", "").replace("\r", ""); // Remove newline characters
                    password = tfPassword.getText();
                    // Check if the name is not empty after removing newline characters
                    if (!name.isEmpty()) {
                        if (password.length() < 8 && (!password.matches(".*[a-zA-Z].*") || !password.matches(".*\\d.*"))) {
                            JOptionPane.showMessageDialog(null,"Password must be more than 8.\nPassword must have at least one letter and one number.");
                        }else if(password.length() < 8){
                            JOptionPane.showMessageDialog(null,"Password must be more than 8.");
                        }else if((!password.matches(".*[a-zA-Z].*") || !password.matches(".*\\d.*"))) {
                            JOptionPane.showMessageDialog(null,"Password must have at least one letter and one number.");
                        }else{
                            int ifadded = player.addtoList(name, password);
                            if(ifadded==1){
                                comboboxPlayers.addItem(name);
                                comboboxPlayers.setSelectedItem(name);
                            }
                            tfUsername.setText("");
                            tfPassword.setText("");
                            displaywelcome();
                        }
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        cphomebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaywelcome();
            }
        });
    }
    public void loadPlayersFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Acer\\Downloads\\WORDLE\\WORDLE\\players.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                comboboxPlayers.addItem(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Acer\\Downloads\\WORDLE\\WORDLE\\todaystip.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                tips.add(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void displaywelcome(){
        this.setContentPane(this.welcomepanel);
    }
    public void displayplayerslist(){
        this.setContentPane(this.playerspanel);
        revalidate();
        repaint();
    }
    public void displayleaderboard(){
        this.setContentPane(this.leaderboardspanel);
        revalidate();
        repaint();
    }
    public void displaygamepanel(){
        this.setContentPane(this.gamepanel);
        revalidate();
        repaint();
        String selectedName = (String) comboboxPlayers.getSelectedItem();
        playersnamelabel.setText(selectedName);
    }
    public void displayhowtoplay(){
        this.setContentPane(this.howtoplaypanel);
        revalidate();
        repaint();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Acer\\Downloads\\WORDLE\\WORDLE\\howtoplay.txt"))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }

            // Set the content to the JTextArea
            taHowtoPlay.setText(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
