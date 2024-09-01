
package PackageMain;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class sound {

    Jpanel pn;
    public Clip clip;
    URL sound[]=new URL[10];
    
    public sound(Jpanel pn)    
    {
        this.pn=pn;
        sound[1]=getClass().getResource("/music3.wav");
        sound[2]=getClass().getResource("/music2.wav");
        sound[3]=getClass().getResource("/jump.wav");
    }
    
    public void setFile(int i)
    {
        try {
            //InputStream inps=getClass().getResourceAsStream(soundURL[i]);
            AudioInputStream ads = AudioSystem.getAudioInputStream(sound[i]);
            clip = AudioSystem.getClip();
            clip.open(ads);
        } catch (LineUnavailableException ex) {
            System.out.println("aaaaaaaa");
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("File sound not support by Java");
        } catch (IOException ex) {
            System.out.println("Load file fail");
        }
    }
    
    public void play()
    {
        clip.start();
    }
    public void soundLoop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop()
    {
        clip.stop();
    }
}
