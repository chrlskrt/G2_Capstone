package G2_Capstone.WordleGameProper;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Tile extends JLabel{
    private static final Color _gray = new Color(220,220,220);
    private static final Color _border = new Color(5,5,5);
    private Border border = null;

    public Tile(){
        border = BorderFactory.createLineBorder(_border, 2);
        this.setText(" ");
        this.setSize(50,50);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setForeground(Color.black);
        this.setBackground(Color.white);
        this.setOpaque(true);
    }


}
