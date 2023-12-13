package G2_MiniGame.TAKYAN.GameUtils;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    public static Clip play(File sndfile) {
        return play(sndfile,1f);
    }

    public static Clip play(File sndfile, float volume){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(sndfile);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
            clip.start();
            return clip;
        } catch (Exception e) {
            System.out.println("Error in playing sound. <Takyan>");
        }

        return null;
    }

    public static void replay(Clip currClip){
        currClip.setFramePosition(0);
        currClip.start();
    }

    public static void stop(Clip currClip){
        if(currClip != null && currClip.isRunning()){
            currClip.stop();
        }
    }
}