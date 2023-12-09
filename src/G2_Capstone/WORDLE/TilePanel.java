package G2_Capstone.WORDLE;

import G2_Capstone.WORDLE.Tile;

import javax.swing.*;
import java.awt.*;

public class TilePanel extends JPanel {
    Tile[][] wordlePanel = new Tile[6][5];
    public final int height = 500;
    public final int width = 450;
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

    public Tile getTile(int row, int col){
        return wordlePanel[row][col];
    }

    public void refresh(){
        for(int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLS; j++){
                wordlePanel[i][j].refresh();
            }
        }
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
