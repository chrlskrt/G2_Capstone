package G2_Capstone.WordleGameProper;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyTypedReader implements KeyListener {
    JFrame wordleFrame = null;
    TilePanel wordleTiles = null;

    public KeyTypedReader(JFrame wordleFrame, TilePanel wordleTiles){
        this.wordleFrame = wordleFrame;
        this.wordleTiles = wordleTiles;
        wordleTiles.addKeyListener(this);
    }


    // to respond to user typing a character
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        wordleTiles.getLetterBox(TilePositionTracker.getROW(), TilePositionTracker.getCOL()).setText(String.valueOf(c));
        System.out.println(c + " at row: " + TilePositionTracker.getROW() + " col: " + TilePositionTracker.getCOL());
        TilePositionTracker.setROW(TilePositionTracker.getROW() + 1);
        TilePositionTracker.setCOL(TilePositionTracker.getCOL() + 1);
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public int checkKey(String key){
        return 0;
    }
}
