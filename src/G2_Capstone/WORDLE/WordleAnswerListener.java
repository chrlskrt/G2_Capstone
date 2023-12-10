package G2_Capstone.WORDLE;

import javax.swing.*;


public abstract class WordleAnswerListener implements WordleAnswerHandler {
    WordleGame wordleFrame;
    TilePanel wordleTiles;

    public WordleAnswerListener(WordleGame wordleFrame, TilePanel wordleTiles) {
        this.wordleFrame = wordleFrame;
        this.wordleTiles = wordleTiles;
    }

    protected void pressedEnter(){
        int currentCol = TilePositionTracker.getCOL();
        int currentRow = TilePositionTracker.getROW();
        if (currentCol == wordleTiles.COLS){
            int flag = WordleAnswerHandler.isWinning(wordleTiles.getRow(currentRow));
                String[] options = {"Play again", "Exit"};
            switch (flag){
                case 0:
                    if (currentRow == wordleTiles.ROWS){
                        int result = JOptionPane.showOptionDialog(wordleFrame,
                                "GAME OVER!!!","Game over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                                null, options, options[0]);

                        if (result == JOptionPane.YES_OPTION){
                            wordleFrame.endGame();
                            wordleFrame.startGame();
                        } else if (result == JOptionPane.NO_OPTION){
                            wordleFrame.exit();
                        }
                    } else {
                        JOptionPane.showMessageDialog(wordleFrame, "TRY AGAIN!!");
                        TilePositionTracker.setCOL(0);
                        TilePositionTracker.setROW(currentRow+1);
                    }
                    break;
                case 1:
                    int result = JOptionPane.showOptionDialog(wordleFrame,
                            "You won!! after " + (currentRow + 1) + " try/tries","Winning...", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                            null, options, options[0]);

                    if (result == JOptionPane.YES_OPTION){
                        wordleFrame.endGame();
                        wordleFrame.startGame();
                    } else if (result == JOptionPane.NO_OPTION){
                        wordleFrame.exit();
                    }
                    break;
                case 2:
                    JOptionPane.showMessageDialog(wordleFrame, "Word not in word list");
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(wordleFrame, "Not enough letters!");
        }
    }

}
