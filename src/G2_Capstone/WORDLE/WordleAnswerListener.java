package G2_Capstone.WORDLE;

import G2_Capstone.Player;

import javax.swing.*;


public abstract class WordleAnswerListener implements WordleAnswerHandler {
    WordleGame wordleFrame;
    TilePanel wordleTiles;
    Player player;

    public WordleAnswerListener(WordleGame wordleFrame, TilePanel wordleTiles, Player player) {
        this.wordleFrame = wordleFrame;
        this.wordleTiles = wordleTiles;
        this.player = player;
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
                    updatePlayerScore();
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

    private void updatePlayerScore(){
        int score = wordleTiles.ROWS - TilePositionTracker.getROW() + 1;
        System.out.println("score: " + score);
        player.updateWordleScore(score);
    }

}
