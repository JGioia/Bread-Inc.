import java.awt.*; 
import java.io.*;
import javax.imageio.*; 

public class Sprite{
    public Image img;
    private int xPos, yPos, xSize, ySize, layer, id, hasHitbox;
    public int[][] hitbox;
    public boolean visibility;

    public Sprite(String imgName, int xPos, int yPos, int xSize, int ySize, 
        int layer, boolean visibility, int hasHitbox){
        try {
            img= ImageIO.read( new File(imgName) );
        } catch( IOException i ) {
            i.printStackTrace();
        }
        this.xPos=xPos;
        this.yPos=yPos;
        this.xSize=xSize;
        this.ySize=ySize;
        this.layer=layer;
        this.visibility=visibility;
        this.hasHitbox=hasHitbox;
        hitbox = new int [2][2];
        if(hasHitbox!=-1){
            hitbox[0][0]=xPos;
            hitbox[0][1]=xPos+xSize;
            hitbox[1][0]=yPos;
            hitbox[1][1]=yPos+ySize;
        }else{
            hitbox[0][0]=-10000;
            hitbox[0][1]=-10000;
            hitbox[1][0]=-10000;
            hitbox[1][1]=-10000;
        }
    }
    public Sprite(Image img, int xPos, int yPos, int xSize, int ySize, 
        int layer, boolean visibility, int hasHitbox){
        this.img=img;
        this.xPos=xPos;
        this.yPos=yPos;
        this.xSize=xSize;
        this.ySize=ySize;
        this.layer=layer;
        this.visibility=visibility;
        this.hasHitbox=hasHitbox;
        hitbox = new int [2][2];
        if(hasHitbox!=-1){
            hitbox[0][0]=xPos;
            hitbox[0][1]=xPos+xSize;
            hitbox[1][0]=yPos;
            hitbox[1][1]=yPos+ySize;
        }else{
            hitbox[0][0]=-10000;
            hitbox[0][1]=-10000;
            hitbox[1][0]=-10000;
            hitbox[1][1]=-10000;
        }
    }
    public Sprite(String imgName, int xPos, int yPos, int xSize, int ySize, 
        int layer, boolean visibility, int hasHitbox, int[][] hitbox){
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
        this.visibility=visibility;
        this.hasHitbox=hasHitbox;
        this.hitbox=hitbox;
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
    public int getLayer(){
        return layer;
    }
    public int getId(){
        return id;
    }
    public String getHitboxToString(){
        return hitbox[0][0]+", "+hitbox[0][1]+", "+hitbox[1][0]+", "+hitbox[1][1];
    }
    
    public void setImage(Image img){
        this.img=img;
    }
    public void setId(int id){
        this.id=id;
    }

    public boolean getVisibility(){
        return visibility;
    }
    public void setVisibility(boolean visibility){
        this.visibility=visibility;
    }

    public void addXPos(int xAdd){
        xPos+=xAdd;
        hitbox[0][0]+=xAdd;
        hitbox[0][1]+=xAdd;
    }
    public void setXPos(int xPos){
        addXPos(xPos-this.xPos);
    }
    public void addYPos(int yAdd){
        yPos+=yAdd;
        hitbox[1][0]+=yAdd;
        hitbox[1][1]+=yAdd;
    }
    public void setYPos(int yPos){
        addYPos(yPos-this.yPos);
    }

    public boolean wouldHitBox(int [][]hitbox){
        if((!(hitbox[0][0]>this.hitbox[0][1]||hitbox[0][1]<this.hitbox[0][0])
        &&!(hitbox[1][0]>this.hitbox[1][1]||hitbox[1][1]<this.hitbox[1][0]))
        &&visibility)
            return true;
        return false;
    }
    public boolean wouldHitBox(int[] point){
        int[][]hitbox=new int[2][2];
        hitbox[0][0]=point[0];
        hitbox[0][1]=point[0];
        hitbox[1][0]=point[1];
        hitbox[1][1]=point[1];
        return wouldHitBox(hitbox);
    }
    public boolean hitsBox(int [][] hitbox){
        if(hasHitbox==1&&wouldHitBox(hitbox)){
            return true;
        }
        return false;
    }
    public boolean hitsBox(int[] point){
        int[][]hitbox=new int[2][2];
        hitbox[0][0]=point[0];
        hitbox[0][1]=point[0];
        hitbox[1][0]=point[1];
        hitbox[1][1]=point[1];
        return hitsBox(hitbox);
    }

    public void tick(boolean[] boolInput, int[] intInput){

    }
}