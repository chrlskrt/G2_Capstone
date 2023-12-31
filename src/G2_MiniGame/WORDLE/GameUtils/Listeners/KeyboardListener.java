package G2_MiniGame.WORDLE.GameUtils.Listeners;

import G2_MiniGame.WORDLE.GameComponents.Tile;
import G2_MiniGame.WORDLE.GameComponents.TilePanel;
import G2_MiniGame.WORDLE.GameScreen.WordleGame;
import G2_MiniGame.WORDLE.GameUtils.TilePositionTracker;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener extends AnswerListener implements KeyListener {
    Tile tile;

    public KeyboardListener(WordleGame wordleFrame, TilePanel wordleTiles){
        super(wordleFrame, wordleTiles);
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
    }
}

