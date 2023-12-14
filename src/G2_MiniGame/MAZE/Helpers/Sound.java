package G2_MiniGame.MAZE.Helpers;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

    static Clip clip;

    public static void play(File sndfile) {
        play(sndfile,1f);
    }

    public static void play(File sndfile, float volume){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(sndfile);
            clip = AudioSystem.getClip();
            clip.open(ais);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopSound(){
        if (clip != null && clip.isRunning()){
            clip.stop();
        }
    }
}