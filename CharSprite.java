import java.awt.image.*; 
import java.awt.*;
import java.io.*;
import javax.imageio.*; 
public class CharSprite extends Sprite{
    private static BufferedImage image;
    public CharSprite(char c, int xPos, int yPos, int xSize, int ySize, int layer, boolean visibility, int hasHitbox){
        super(getCharImage(c), xPos, yPos, xSize, ySize, layer, visibility, hasHitbox);
    }
    public static Image getCharImage(char c){//TODO: add :
        String imgString = "IMGS/Pack2/Font/";
        if(c>=65&&c<=90){//uppercase
            imgString+=c;
        }else if(c>=97&&c<=122){//lowercase
            imgString+=c+"L";
        }else if(c==46){//period
            imgString+="Pd";
        }else if(c==44){//comma
            imgString+=c;
        }else if(c==33){//exclammation mark
            imgString+=c;
        }else if(c==63){//question mark
            imgString+="QM";
        }else if(c>=48&&c<=57){//numbers
            imgString+=c;
        }else{//space
            imgString+="Sp";
        }
        imgString+=".png";

        try {
            image = ImageIO.read( new File(imgString) );
        } catch( IOException i ) {
            i.printStackTrace();
        }
        return image;
    }
}