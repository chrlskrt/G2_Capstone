package G2_Capstone.WORDLE;

import G2_Capstone.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WordleKeyboardListener extends WordleAnswerListener implements KeyListener {
    Tile tile;

    public WordleKeyboardListener(WordleGame wordleFrame, TilePanel wordleTiles, Player player){
        super(wordleFrame, wordleTiles, player);
        wordleTiles.addKeyListener(this);
    }

    // to respond to user typing a character
    @Override
    public void keyTyped(KeyEvent e) {
        char c = Character.toUpperCase(e.getKeyChar());
        int result = checkKey(c);

        if (result == 1){
            typeLetter(c);
        }

        if (result == 2){
            eraseLetter();
        }

        if (result == 3){
            typeEnter();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    // checkKey(char key) - checks whether @param key is a letter or not
    public int checkKey(char key){
        if (Character.isLetter(key)){
            return 1; // letter
        }
        if (key == 8){
            return 2; // backspace
        }

        if (key == 10){
            return 3; // enter
        }

        return 0;
    }

    public void typeLetter(char letter){
        if (TilePositionTracker.getCOL() == 5){
            return;
        }

        tile = wordleTiles.getTile(TilePositionTracker.getROW(), TilePositionTracker.getCOL());
        tile.setText(String.valueOf(letter));

        TilePositionTracker.setCOL(TilePositionTracker.getCOL()+1);
    }

    public void eraseLetter(){
        if (TilePositionTracker.getCOL() == 0){
            return;
        }

        TilePositionTracker.setCOL(TilePositionTracker.getCOL() - 1);

        tile = wordleTiles.getTile(TilePositionTracker.getROW(), TilePositionTracker.getCOL());
        tile.refresh();
    }

    public void typeEnter(){
        super.pressedEnter();
//        System.out.println("enter type");
//        int currentCol = TilePositionTracker.getCOL();
//        int currentRow = TilePositionTracker.getROW();
//
//        if (currentCol < 5){
//            JOptionPane.showMessageDialog(null,"Not enough letters");
//        }
    }
}

