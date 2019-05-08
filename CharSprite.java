import java.awt.image.*; 
import java.awt.*;
import java.io.*;
import javax.imageio.*; 
public class CharSprite extends Sprite{
    public CharSprite(char c){
        super(getCharImage(c), xPos, yPos, xSize, ySize, layer, visibility, hasHitbox)
    }
    public static Image getCharImage(char c){
        BufferedImage image;
        int charSize=8;
        int startX,startY;
        try {
            image= ImageIO.read( new File("Pack2/font.png") );
        } catch( IOException i ) {
            i.printStackTrace();
        }
        if(c>=65&&c<=90){

        }
        BufferedImage img = image.getSubimage(startX, startY, startX+charSize, startY+charSize); //fill in the corners of the desired crop location here
        BufferedImage croppedImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        return croppedImage;
    }
}