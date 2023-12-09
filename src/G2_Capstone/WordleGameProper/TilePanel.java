package G2_Capstone.WordleGameProper;

import javax.swing.*;
import java.awt.*;

public class TilePanel extends JPanel {
    Tile[][] wordlePanel = new Tile[6][5];
    public final int height = 400;
    public final int width = 350;
    public final int ROWS = 6;
    public final int COLS = 5;

    public TilePanel(){
        this.setLayout(new GridLayout(ROWS,COLS,5,5));
        this.setSize(width, height);
        for (int i = 0; i < wordlePanel.length; i++){
            for (int j = 0; j < wordlePanel[i].length; j++){
                wordlePanel[i][j] = new Tile();
                this.add(wordlePanel[i][j]);
            }
        }
    }


    public Tile[] getRow(int row){
        return wordlePanel[row];
    }

    public Tile getLetterBox(int row, int col){
        return wordlePanel[row][col];
    }
//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setLayout(null);
//        frame.setSize(500,500);
//        frame.setVisible(true);
//
//        frame.add(new LettersPanel());
//    }
}
