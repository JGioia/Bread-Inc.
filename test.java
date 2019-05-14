import java.io.*;
import javax.sound.sampled.*;

public class test{
    public static void main(String[] args){
        System.out.println('A'==65);
        /*
        File yourFile = new File("Music/BeepBox6.wav");
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;
        Clip clip;
        try{
            stream = AudioSystem.getAudioInputStream(yourFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();

            Clip clip2;
            yourFile= new File("Music/BeepBox12.wav");
            stream = AudioSystem.getAudioInputStream(yourFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip2 = (Clip) AudioSystem.getLine(info);
            clip2.open(stream);
            clip2.start();

            System.out.println("hello");
            Thread.sleep(100000);
        }
        catch (Exception e) {
            //whatevers
        }
        */
    }
}