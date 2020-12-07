package uet.oop.bomberman.entities.mobile;

import javax.sound.sampled.*;
import java.io.IOException;

public class Sound {
    public static String EXPLORE = "/Sound/Explosion.wav";
    public static String footStep1 = "/Sound/footstep1.wav";
    public static String footStep2 = "/Sound/footstep2.wav";
    public static String DEAD = "/Sound/dead.wav";
    public static String DEAD1 = "/Sound/lose.wav";
    public static String LEVELUP = "Sound/levelup.wav";
    public static String BACKGROUND = "/Sound/background.wav";
    public static String BACKGROUND1 = "/Sound/bg.wav";
    public static synchronized void play(final String fileName, int loop_Timer)
    {
        // Note: use .wav files
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            Sound.class.getResourceAsStream(fileName));
                    clip.open(inputStream);
                    clip.start();
                    clip.getFrameLength();
                    clip.loop(loop_Timer);
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                    System.out.println("play sound error: " + e.getMessage() + " for " + fileName);
                }
            }
        }).start();
    }
}
