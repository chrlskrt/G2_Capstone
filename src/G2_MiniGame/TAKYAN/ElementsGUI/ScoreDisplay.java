package G2_MiniGame.TAKYAN.ElementsGUI;

import G2_MiniGame.MiniGame_MainMenu;
import G2_MiniGame.data.PlayersHandler;
import G2_MiniGame.TAKYAN.GameUtils.RenderObj;

import java.awt.*;

public class ScoreDisplay extends RenderObj {
    private int scoreDisplay;
    private final String text;
    private int xPos = 40;
    private int yPos = 50;
    private PlayersHandler handler = PlayersHandler.getInstance();

    public ScoreDisplay(String text, int initialScoreDisplay){
        this.text = text;
        scoreDisplay = initialScoreDisplay;
    }

    public ScoreDisplay(String text, int initialScoreDisplay, int x, int y){
        this.text = text;
        scoreDisplay = initialScoreDisplay;
        xPos = x;
        yPos = y;
    }

    public void setScore(int score){
        scoreDisplay = score;
    }

    public int getScore(){
        return scoreDisplay;
    }

    public void resetScore(){
        scoreDisplay = 0;
    }

    public void recordScore(){
        MiniGame_MainMenu.currentPlayer.setTakyanScore(getScore());
        handler.updatePlayersFile();
        resetScore();
    }

    public static int getHighScore(){
        return MiniGame_MainMenu.currentPlayer.getTakyanScore();
    }


    @Override
    public void paintComponent(Graphics2D g) {
        g.setColor(Color.WHITE);
        Font font = new Font("Arial", Font.BOLD, 30);
        g.setFont(font);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.drawString(text + scoreDisplay, xPos, yPos);
    }
}
