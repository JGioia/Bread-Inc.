import java.awt.*;  
import java.io.*;
import javax.imageio.*;

public class Character extends Sprite{
    public Image[][] imgs;
    public int spriteNum=0;
    public int cycleNum=0;
    public int speed;
    public Character(String[][] imgNames, int xPos, int yPos, int xSize, int ySize, 
        int layer, int id, boolean visibility, int speed){
        super(imgNames[0][0], xPos, yPos, xSize, ySize, layer, id, visibility);
        for(int i=0;i<imgNames.length;i++)
            for(int j=0;j<imgNames[i].length;j++){
                try {
                    img= ImageIO.read( new File(imgNames[i][j]) );
                } catch( IOException f ) {
                    f.printStackTrace();
                }
            }
        this.speed=speed;
    }
    /*
    public Image getImage(){
        return imgs[cycleNum][spriteNum];
    }
    */
}