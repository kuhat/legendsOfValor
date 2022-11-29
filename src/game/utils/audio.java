package game.utils;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


/**
 *
 * A utility for audios and sounds
 *
 * reference: https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
 *
 */


public class audio {

    private static final String dir = System.getProperty("user.dir") + "/configs/";

    public static final String GAME_INTRODUCTION_MUSIC="game_introduction_music.wav";

    public synchronized static void playMusic(String musicFileName, boolean loop ) {
        Thread thread=new Thread(() -> {
            try
            {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File( dir + musicFileName));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);

                if(loop) {
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }

                clip.start();

            } catch (
                    Exception exception) {
                exception.printStackTrace();
            }
        });
        thread.start();

    }

}

