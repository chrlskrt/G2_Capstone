package G2_MiniGame.MAZE.Maze;

import G2_MiniGame.MAZE.MazeMenu;
import G2_MiniGame.Main;
import G2_MiniGame.MiniGame_MainMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import static G2_MiniGame.Main.playSound;

public class MenuGamePanel extends GamePanel implements Runnable {
    JFrame menu_frame;
    BackgroundPanel bg = new BackgroundPanel.BackgroundPanelBuilder().build();
    JButton homeButton = new JButton("⌂"); // Added JButton
    JButton playButton = new JButton(); // Added JButton
    JButton leaderboardsButton = new JButton(); // Added JButton

    public MenuGamePanel(JFrame menu_frame) {
        try {
            bg.setBackgroundImage("src/G2_MiniGame/MAZE/Animations/Screens/menu1.png");
        } catch (Exception e) {
            System.out.println("No file found");
        }

        this.menu_frame = menu_frame;
        // Set the layout manager to null for absolute positioning
        setLayout(null);

        // Set the position and size of the
        homeButton.setBounds(0,0,50,30);
        playButton.setBounds(300, 380, 200, 60);
        leaderboardsButton.setBounds(170, 460, 480, 65);

        // Make the buttons transparent
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        leaderboardsButton.setOpaque(false);
        leaderboardsButton.setContentAreaFilled(false);
        homeButton.setOpaque(false);
        homeButton.setContentAreaFilled(false);

        // Set 'Home' button text-color to white
        homeButton.setForeground(Color.white);

        // Add the buttons to the panel
        add(playButton);
        add(leaderboardsButton);
        add(homeButton);

        // Add action listeners to the buttons
        playButton.addActionListener(new PlayButtonListener());
        leaderboardsButton.addActionListener(new LeaderboardsButtonListener());
        homeButton.addActionListener(new HomeButtonListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        bg.paintComponent(g);
    }

    @Override
    public void run() {
        // Your run logic goes here
    }

    // ActionListener for the "Play" button
    private class PlayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            playSound("src/G2_MiniGame/Audio/click.wav");

            // Create a new JFrame for the game
            MapFrame map_frame = new MapFrame();
            map_frame.start();

            // Close the current JFrame (MenuFrame)
            Main.stopSound();
            menu_frame.dispose();
        }
    }

    // ActionListener for the "Leaderboards" button
    private class LeaderboardsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            playSound("src/G2_MiniGame/Audio/click.wav");
            ((MazeMenu) menu_frame).displayLeaderboards();
        }
    }


    // ActionListener for the "Home" button
    private class HomeButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            playSound("src/G2_MiniGame/Audio/click.wav");
            menu_frame.dispose();
            new MiniGame_MainMenu(true);
        }
    }

    public static class BackgroundPanel extends DynamicEntity {
        BackgroundPanel(BackgroundPanelBuilder builder) {
            super(builder);
        }

        public static class BackgroundPanelBuilder extends DynamicEntityBuilder {
            public BackgroundPanelBuilder() {
                super();
            }

            @Override
            public BackgroundPanel build() {
                return new BackgroundPanel(this);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            if (backgroundImage != null) {
                g2d.drawImage(backgroundImage, 0, 0, 800, 800, null);
            }
        }
    }
}
