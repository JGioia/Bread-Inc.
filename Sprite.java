import java.awt.*; 
import java.io.*;
import javax.imageio.*; 

public class Sprite{
    public Image img;
    public int xPos;
    public int yPos;
    public int xSize;
    public int ySize;
    public int layer;
    public int id;
    public boolean visibility;

    public Sprite(String imgName, int xPos, int yPos, int xSize, int ySize, int layer, int id, boolean visibility){
        try {
            img= ImageIO.read( new File(imgName) );
        } catch( IOException i ) {
            i.printStackTrace();
        }
        this.xPos=xPos;
        this.yPos=yPos;
        this.xSize=-xSize;
        this.ySize=ySize;
        this.layer=layer;
        this.id=id;
        this.visibility=visibility;
    }
    public Image getImage(){
        return img;
    }
    public int getXPos(){
        return xPos;
    }
    public int getYPos(){
        return yPos;
    }
    public int getXSize(){
        return xSize;
    }
    public int getYSize(){
        return ySize;
    }
}