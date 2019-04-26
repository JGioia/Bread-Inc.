import java.awt.*;  
import java.io.*;
import javax.imageio.*;

public class Character extends Sprite{
    public Image[][] imgs;
    public int spriteNum=0;
    public int cycleNum=0;
    public int speed;
    public Game game;
    public Character(String[][] imgNames, int xPos, int yPos, int xSize, int ySize, 
        int layer, boolean visibility, int hasHitbox, int speed, Game game){
        super(imgNames[0][0], xPos, yPos, xSize, ySize, layer, visibility, hasHitbox);
        for(int i=0;i<imgNames.length;i++)
            for(int j=0;j<imgNames[i].length;j++){
                try {
                    img= ImageIO.read( new File(imgNames[i][j]) );
                } catch( IOException f ) {
                    f.printStackTrace();
                }
            }
        this.speed=speed;
        this.game=game;
    }
    
    /*
    public Image getImage(){
        return imgs[cycleNum][spriteNum];
    }
    */
    public int getSpeed(){
        return speed;
    }
    
    public boolean moveUp(){
        addYPos(-speed);
        if(game.hitsBox(hitbox)){
            addYPos(speed);
            return false;
        }
        return true;
    }
    public boolean moveDown(){
        addYPos(speed);
        if(game.hitsBox(hitbox)){
            addYPos(-speed);
            return false;
        }
        return true;
    }
    public boolean moveLeft(){
        addXPos(-speed);
        if(game.hitsBox(hitbox)){
            addXPos(speed);
            return false;
        }
        return true;
    }
    public boolean moveRight(){
        addXPos(speed);
        if(game.hitsBox(hitbox)){
            addXPos(-speed);
            return false;
        }
        return true;
    }
}