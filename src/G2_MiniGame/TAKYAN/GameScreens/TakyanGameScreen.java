package G2_MiniGame.TAKYAN.GameScreens;

import G2_MiniGame.TAKYAN.ElementsGUI.Background;
import G2_MiniGame.TAKYAN.ElementsGUI.ScoreDisplay;
import G2_MiniGame.TAKYAN.Entities.Ball;
import G2_MiniGame.TAKYAN.Entities.Paddle;
import G2_MiniGame.TAKYAN.GameUtils.Game;
import G2_MiniGame.TAKYAN.GameUtils.Sound;
import G2_MiniGame.TAKYAN.Popups.GameOverPopup;
import G2_MiniGame.TAKYAN.SoundEffects.GameSound;

import javax.sound.sampled.Clip;
import javax.swing.*;

public class TakyanGameScreen extends JPanel {
    public static JFrame window;
    public static final int WINDOW_SIZE_X = 800;
    public static final int WINDOW_SIZE_Y = 600;
    public static ScoreDisplay currentScore = new ScoreDisplay("Score: ", 0);
    public static int currentHighScore = ScoreDisplay.getHighScore();
    public static ScoreDisplay highScore = new ScoreDisplay("High Score: ", currentHighScore, 40, 100);
    public static Clip bgMusic;
    public static Game game;
    public static GameOverPopup gameOverPopup = new GameOverPopup();

    public TakyanGameScreen(JFrame TakyanMenu){
        window = TakyanMenu;
    }

    public void startGame(){
        game = new Game(window, WINDOW_SIZE_X, WINDOW_SIZE_Y);
        game.add(new Background());
        game.add(currentScore);
        game.add(highScore);
        game.add(new Paddle());
        game.add(new Ball());
        Thread musicThread = new Thread(() -> {
            bgMusic = Sound.play(GameSound.backgroundMusic);
        });

        musicThread.start();


        game.start();
    }
}
