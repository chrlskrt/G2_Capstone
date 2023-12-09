package G2_Capstone;

import javax.sound.sampled.*;
import java.io.File;


public class Main {
    public static void main(String[] args) {
        new Game();
        playSound("src/G2_Capstone/Audio/fish in the pool花屋敷.wav");
    }
    public static void playSound(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        event.getLine().close();
                    }
                }
            });
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
