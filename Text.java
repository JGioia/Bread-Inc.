import java.util.*;
public class Text{
    ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    String text;
    int[][] hitbox;
    int layer;
    public Text(String text, int layer, int[][] hitbox){
        this.text=text;
        this.layer=layer;
        this.hitbox=hitbox;
        createText();
    }
    public Text(String text, int layer, int[] point){
        this.text=text;
        this.layer=layer;
        hitbox=new int[2][2];
        hitbox[0][0]=point[0];
        hitbox[1][0]=point[1];
        hitbox[0][1]=9999999;
        hitbox[1][1]=9999999;
        createText();
    }

    public void createText(){
        for(int i=0;i<text.length();i++){
            char c = text.charAt(i);
            int xPos = ((i)%((hitbox[0][0]-hitbox[0][1])/8))*8+hitbox[0][0];
            int yPos = ((i)/((hitbox[0][0]-hitbox[0][1])/8))*-12+hitbox[1][0];
            CharSprite charSprite = new CharSprite(c, xPos, yPos, 8, 12, layer, true, -1);
            sprites.add(charSprite);
        }
    }

    public Sprite[] getSprites(){
        Sprite[] result =  new Sprite[sprites.size()];
        for(int i=0;i<result.length;i++)
            result[i]=sprites.get(i);
        return result;
    }

    public int getLength(){
        return sprites.size();
    }
    public Sprite getSprite(int spriteNum){
        return sprites.get(spriteNum);
    }

    public void setVisibility(boolean visibility){
        for(Sprite s : sprites)
            s.setVisibility(visibility);
    }

    public void moveTo(int[] position){
        int[] difference = new int[2];
        difference[0]=position[0]-hitbox[0][0];
        difference[1]=position[1]-hitbox[1][0];
        for(Sprite s : sprites){
            s.addXPos(difference[0]);
            s.addYPos(difference[1]);
        }
    }
}