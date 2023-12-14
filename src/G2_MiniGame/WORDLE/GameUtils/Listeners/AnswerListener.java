package G2_MiniGame.WORDLE.GameUtils.Listeners;

import G2_MiniGame.MiniGame_MainMenu;
import G2_MiniGame.data.PlayersHandler;
import G2_MiniGame.WORDLE.GameUtils.AnswerHandler;
import G2_MiniGame.WORDLE.GameComponents.TilePanel;
import G2_MiniGame.WORDLE.GameScreen.WordleGame;
import G2_MiniGame.WORDLE.GameUtils.TilePositionTracker;


import javax.swing.*;


public abstract class AnswerListener implements AnswerHandler {
    WordleGame wordleFrame;
    TilePanel wordleTiles;
    PlayersHandler handler;

    public AnswerListener(WordleGame wordleFrame, TilePanel wordleTiles) {
        this.wordleFrame = wordleFrame;
        this.wordleTiles = wordleTiles;
        handler = PlayersHandler.getInstance();
    }

    protected void pressedEnter(){
        int currentCol = TilePositionTracker.getCOL();
        int currentRow = TilePositionTracker.getROW();
        if (currentCol == wordleTiles.COLS){
            int flag = AnswerHandler.isWinning(wordleTiles.getRow(currentRow));
                String[] options = {"Play again", "Exit"};
            switch (flag){
                case 0:
                    if (currentRow == wordleTiles.ROWS){
                        int result = JOptionPane.showOptionDialog(wordleFrame,
                                "GAME OVER!!!","OH NO NO NO GWENCHANA GWENCHANA", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
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
                    int score = updatePlayerScore();
                    int result = JOptionPane.showOptionDialog(wordleFrame,
                            "You won!! ( + " + score + " )","YOU WONN!!!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
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

    private int updatePlayerScore(){
        int score = wordleTiles.ROWS - TilePositionTracker.getROW() + 1;
        MiniGame_MainMenu.currentPlayer.updateWordleScore(score);
        handler.updatePlayersFile();
        return score;
    }
}
