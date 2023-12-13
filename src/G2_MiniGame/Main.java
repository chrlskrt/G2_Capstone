package G2_MiniGame;

import javax.sound.sampled.*;
import java.io.File;


public class Main {
    public static Clip clip;
    public static void main(String[] args) {
        new MiniGame_MainMenu();
        playSound("src/G2_MiniGame/Audio/fish in the pool花屋敷.wav");
    }
    public static void playSound(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    event.getLine().close();
                }
            });
            clip.start();
        } catch (Exception e) {
            System.out.println("Error in getting music file.");
        }
    }

    public static void stopSound(){
        if (clip != null && clip.isRunning()){
            clip.stop();
        }
    }
}
