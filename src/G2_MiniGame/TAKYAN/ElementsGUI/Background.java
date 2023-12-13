package G2_MiniGame.TAKYAN.ElementsGUI;

import G2_MiniGame.TAKYAN.GameUtils.RenderObj;

import javax.swing.*;
import java.awt.*;

public class Background extends RenderObj {
    private final Image background = new ImageIcon("src/G2_MiniGame/TAKYAN/Assets/background.jpg").getImage();
    @Override
    public void paintComponent(Graphics2D g) {
        g.drawImage(background, 0, 0, null);
    }
}
