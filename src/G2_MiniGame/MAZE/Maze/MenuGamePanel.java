package G2_MiniGame.MAZE.Maze;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuGamePanel extends GamePanel implements Runnable {
    BackgroundPanel bg = new BackgroundPanel.BackgroundPanelBuilder().build();
    JButton playButton = new JButton(); // Added JButton
    JButton leaderboardsButton = new JButton(); // Added JButton

    public MenuGamePanel() {
        try {
            bg.setBackgroundImage("src/G2_MiniGame/MAZE/Animations/Screens/menu1.png");
        } catch (Exception e) {
            System.out.println("No file found");
        }

        // Set the layout manager to null for absolute positioning
        setLayout(null);

        // Set the position and size of the buttons
        playButton.setBounds(300, 380, 200, 60);
        leaderboardsButton.setBounds(170, 460, 480, 65);

        // Make the buttons transparent
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        leaderboardsButton.setOpaque(false);
        leaderboardsButton.setContentAreaFilled(false);

        // Add the buttons to the panel
        add(playButton);
        add(leaderboardsButton);

        // Add action listeners to the buttons
        playButton.addActionListener(new PlayButtonListener());
        leaderboardsButton.addActionListener(new LeaderboardsButtonListener());
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

            // Create a new JFrame for the game
            MapFrame map_frame = new MapFrame();
            map_frame.start();

            // Close the current JFrame (MenuFrame)
            SwingUtilities.getWindowAncestor(MenuGamePanel.this).dispose();
        }
    }

    // ActionListener for the "Leaderboards" button
    private class LeaderboardsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Add logic for the "Leaderboards" button click
            System.out.println("Leaderboards button clicked");
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
