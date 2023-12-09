package G2_Capstone.WORDLE;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Tile extends JLabel{
    private static final Color _gray = new Color(220,220,220);
    private static final Color _border = new Color(5,5,5);
    private Border border = null;

    public Tile(){
        border = BorderFactory.createLineBorder(_border, 2);
        this.setText("");
        this.setSize(85,85);
        this.setFont(new Font("DialogInput",Font.PLAIN, 36));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setForeground(Color.black);
        this.setBackground(Color.white);
        this.setOpaque(true);
    }

    public void refresh(){
        this.setText("");
        this.setBackground(_gray);
    }


}
