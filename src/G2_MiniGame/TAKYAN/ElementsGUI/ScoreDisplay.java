package G2_MiniGame.TAKYAN.ElementsGUI;

import G2_MiniGame.MiniGame_MainMenu;
import G2_MiniGame.PlayersHandler;
import G2_MiniGame.TAKYAN.GameUtils.RenderObj;

import java.awt.*;
import java.io.*;

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
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/G2_MiniGame/TAKYAN/scores.txt", true))) {
//            writer.write(getScore() + "");
//            writer.newLine();
//            System.out.println("Score successfully added to the file.");
//        } catch (IOException e) {
//            System.out.println("Error recording takyan score.");
//        }
//        resetScore();

        MiniGame_MainMenu.currentPlayer.setTakyanScore(getScore());
        handler.updatePlayersFile();
        resetScore();
    }

    public static int getHighScore(){
//        int highScore = 0;
//        try (BufferedReader reader = new BufferedReader(new FileReader("src/G2_MiniGame/TAKYAN/scores.txt"))) {
//            String score;
//
//            while ((score = reader.readLine()) != null) {
//                System.out.println(score);
//                highScore = Math.max(highScore, Integer.parseInt(score));
//            }
//        } catch (IOException e) {
//            System.out.println("Error in loading Takyan score into file.");
//        }
//
//        return highScore;
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
