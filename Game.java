import java.util.*;

// Declare a new class called Game which uses JFrame and KeyListener
public class Game {

    private ArrayList<Sprite> images= new ArrayList<Sprite>();;
    private int nextId=0, xOffset=0, yOffset=0;;
    private final int backgroundLayer = 0, playerLayer = 1;
    
    public Game(){
        String img = "postapocalypse1.png";
        Sprite background = new Sprite(img, 400, 0, 600, 400, backgroundLayer, true, 1);

        String[][] imgs ={{"character.png"}};
        MainCharacter image = new MainCharacter(imgs, 100, 100, 60, 60, playerLayer, true, 0, 12, this);
        addSprite(image);
        addSprite(background);
    }

    public ArrayList<Sprite> tick(boolean[] input){
        for(int i=0;i<images.size();i++){
            images.get(i).tick(input);
        }
        return images;
    }

    public void addSprite(Sprite s){
        boolean added=false;
        for(int i=0;i<images.size();i++){
            if(s.getLayer()<images.get(i).getLayer()){
                images.add(i,s);
                s.setId(nextId);
                nextId++;
                added=true;
                break;
            }
        }
        if(!added){
            images.add(s);
            s.setId(nextId);
            nextId++;
            added=true;
        }
    }

    public boolean hitsBox(int[][] hitbox){
        for(int i=0;i<images.size();i++){
            if(images.get(i).hitsBox(hitbox))
                return true;
        }
        return false;
    }

    public int getXOffset(){
        return xOffset;
    }
    public int getYOffset(){
        return yOffset;
    }

    public void addXOffset(int xAdd){
        xOffset+=xAdd;
    }
    public void addYOffset(int yAdd){
        yOffset+=yAdd;
    }
}