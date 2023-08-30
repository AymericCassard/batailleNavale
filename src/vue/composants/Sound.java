/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue.composants;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author acassard
 */
public class Sound {

    private static final int BUFFER_SIZE = 4096;

    public static void playExplosion() {
        InputStream inputStream = Sound.class.getResourceAsStream("/resources/son/explosion.wav");
        playInputStream(inputStream);
    }

    public static void playSplash() {
        InputStream inputStream = Sound.class.getResourceAsStream("/resources/son/splash.wav");
        playInputStream(inputStream);
    }

    private static void playInputStream(InputStream inputStream) {
        try {
            InputStream bufferedIn = new BufferedInputStream(inputStream);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
            AudioFormat audioFormat = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);            
            try {
                SourceDataLine sourceDataLine = AudioSystem.getSourceDataLine(audioFormat);
                sourceDataLine.open(audioFormat);
                sourceDataLine.start();
                byte[] bufferBytes = new byte[BUFFER_SIZE];
                int readBytes = -1;
                while ((readBytes = audioStream.read(bufferBytes)) != -1) {
                    sourceDataLine.write(bufferBytes, 0, readBytes);
                }
                sourceDataLine.drain();
                sourceDataLine.close();
                audioStream.close();
            } catch (LineUnavailableException e) {
                System.out.println(e);
            }

        } catch (UnsupportedAudioFileException | IOException e) {
            System.out.println(e);
        }
    }

    public static void displayMixerInfo() {
        Mixer.Info[] mixersInfo = AudioSystem.getMixerInfo();

        for (Mixer.Info mixerInfo : mixersInfo) {
            System.out.println("Mixer: " + mixerInfo.getName());

            Mixer mixer = AudioSystem.getMixer(mixerInfo);

            Line.Info[] sourceLineInfo = mixer.getSourceLineInfo();
            for (Line.Info info : sourceLineInfo) {
                showLineInfo(info);
            }

            Line.Info[] targetLineInfo = mixer.getTargetLineInfo();
            for (Line.Info info : targetLineInfo) {
                showLineInfo(info);
            }
        }
    }

    private static void showLineInfo(final Line.Info lineInfo) {
        System.out.println("  " + lineInfo.toString());

        if (lineInfo instanceof DataLine.Info) {
            DataLine.Info dataLineInfo = (DataLine.Info) lineInfo;

            AudioFormat[] formats = dataLineInfo.getFormats();
            for (final AudioFormat format : formats) {
                System.out.println("    " + format.toString());
            }
        }
    }
}
