package G2_MiniGame.TAKYAN.Popups;

import javax.swing.*;
import java.awt.*;

public class GameOverPopup {
    public JFrame frame;
    public JPanel panel;
    public GameOverPopup(){
        frame = new JFrame("Game Over");

        // Create a panel to hold the content
        panel = new JPanel(new BorderLayout());

        // Add your logo/image to a JLabel
        ImageIcon logoIcon = new ImageIcon("src/G2_MiniGame/TAKYAN/Assets/gameover_logo.png");
        JLabel logoLabel = new JLabel(logoIcon);

        // Add the logo to the panel
        panel.add(logoLabel, BorderLayout.NORTH);

        // Add other components or messages to the panel
        JLabel messageLabel = new JLabel("Oops, game over! Do you want to try again?");
        panel.add(messageLabel, BorderLayout.CENTER);
    }
}
