package G2_MiniGame.TAKYAN.Entities;
import G2_MiniGame.Main;
import G2_MiniGame.TAKYAN.GameScreens.TakyanGameScreen;
import G2_MiniGame.TAKYAN.GameUtils.RenderObj;
import G2_MiniGame.TAKYAN.GameUtils.Sound;
import G2_MiniGame.TAKYAN.GameUtils.Updater;
import G2_MiniGame.TAKYAN.SoundEffects.GameSound;
import G2_MiniGame.TAKYAN.TakyanMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class Ball extends RenderObj implements Updater{
    private int xCoordinate = TakyanGameScreen.WINDOW_SIZE_X / 2;
    // private int xCoordinate = Main.WINDOW_SIZE_X / 2;
    private final int xInitial = xCoordinate;
    private int yCoordinate = 40;
    private final int yInitial = yCoordinate;
    private final int radius = 10;
    private final double maxVelocity = 18;
    private double xVelocity = 0;
    private double yVelocity = 5;
    private BufferedImage ballImage;
    public static boolean GameOver = false;

    public Ball(){
        try {
            ballImage = ImageIO.read(new File("src/G2_MiniGame/TAKYAN/Assets/ball.png"));
        } catch (IOException e) {
            System.out.println("Error in loading ballImage");
        }
    }

    private void calculateXVelocity(){
        double xMiddle = Paddle.xCoordinate;
        double xDiff = xMiddle - xCoordinate;
        double reductionFactor = (double) (Paddle.width / 2) / maxVelocity;
        double tempXVel = xDiff / reductionFactor;
        xVelocity = (int) Math.round(-1 * tempXVel);
    }

    private void resetBallPosition(){
        xCoordinate = xInitial;
        yCoordinate = yInitial;
        xVelocity = 0;
        yVelocity = 5;
    }

    private void moveBall(){
        xCoordinate += (int) Math.round(xVelocity);
        yCoordinate += (int) Math.round(yVelocity);
        handleCollision();
    }

    private void handleCollision(){
        if(xCoordinate + radius >= TakyanGameScreen.WINDOW_SIZE_X || xCoordinate - radius <= 0){
            xVelocity *= -1;
            Sound.play(GameSound.wallBounceSoundEffect);
        }

        if(yCoordinate - radius <= 0){
            yVelocity *= -1;
            Sound.play(GameSound.wallBounceSoundEffect);
        }

        if(yCoordinate + radius >= TakyanGameScreen.WINDOW_SIZE_Y){
            GameOver = true;
            Sound.play(GameSound.gameOverSoundEffect);
            Sound.stop(TakyanGameScreen.bgMusic);

            xVelocity = 0;
            yVelocity = 0;
            xCoordinate = xInitial;
            yCoordinate = yInitial;

            int choice = JOptionPane.showConfirmDialog(TakyanGameScreen.window,
                    TakyanGameScreen.gameOverPopup.panel,
                    "Game Over",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            int temp = TakyanGameScreen.currentScore.getScore();
            TakyanGameScreen.highScore.setScore(Math.max(temp, TakyanGameScreen.highScore.getScore()));
            if(TakyanGameScreen.currentHighScore < temp){
                TakyanGameScreen.currentScore.recordScore();
                TakyanGameScreen.currentHighScore = temp;
            }

            if(choice == JOptionPane.YES_OPTION){
                TakyanGameScreen.currentScore.resetScore();
                Sound.replay(TakyanGameScreen.bgMusic);
                resetBallPosition();
                GameOver = false;

                TakyanGameScreen.game.run();
            }else{
//                TakyanGameScreen.game.stop();
                TakyanGameScreen.window.dispose();
                new TakyanMenu();


                TakyanGameScreen.currentScore.resetScore();
            }
        }

        if(yCoordinate + radius >= Paddle.yCoordinate + Paddle.height / 2){
            if(xCoordinate - radius >= Paddle.xCoordinate - (Paddle.width / 2) && xCoordinate + radius <= Paddle.xCoordinate + (Paddle.width / 2)){
                yVelocity *= -1;
                Sound.play(GameSound.bounceSoundEffect);
                TakyanGameScreen.currentScore.setScore(TakyanGameScreen.currentScore.getScore() + 1);
                calculateXVelocity();
            }
        }
    }

    @Override
    public void paintComponent(Graphics2D g) {
//        g.setColor(Color.RED);
//        g.fillOval(xCoordinate - radius, yCoordinate - radius, radius * 2, radius * 2);
        if (ballImage != null) {
            // Draw the ball's image at the specified coordinates
            g.drawImage(ballImage, xCoordinate - radius, yCoordinate - radius, null);
        } else {
            // If the image couldn't be loaded, draw a default circle
            g.setColor(Color.RED);
            g.fillOval(xCoordinate - radius, yCoordinate - radius, radius * 2, radius * 2);
        }
    }


    @Override
    public void update() {
        moveBall();
    }
}
