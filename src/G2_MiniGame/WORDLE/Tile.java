package G2_MiniGame.WORDLE;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Tile extends JLabel{
    private static final Color _green = new Color(108,169,101);
    private static final Color _yellow = new Color(200,182,83);
    private static final Color _gray = new Color	(120,124,127);
    private static final Color _border = new Color(5,5,5);

    public Tile(){
        Border border = BorderFactory.createLineBorder(_border, 2);
        this.setText("");
        this.setSize(85,85);
        this.setFont(new Font("DialogInput",Font.PLAIN, 36));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setForeground(Color.black);
        this.setBackground(Color.white);
        this.setOpaque(true);
        this.setBorder(border);
    }

    public void correctPlace(){
        this.setBackground(_green);
    }

    public void incorrectPlace(){
        this.setBackground(_yellow);
    }

    public void incorrect(){
        this.setBackground(_gray);
    }

    public void refresh(){
        this.setText("");
        this.setBackground(Color.white);
    }


}
