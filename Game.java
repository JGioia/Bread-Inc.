import java.util.*;

// Declare a new class called Game which uses JFrame and KeyListener
public class Game {

    private ArrayList<Sprite> images= new ArrayList<Sprite>();;
    private int nextId=0, xOffset=0, yOffset=0;;
    private final int backgroundLayer = 0, playerLayer = 1, UILayer=2;
    
    public Game(){
        String img = "postapocalypse1.png";
        Sprite background = new Sprite(img, 400, 0, 600, 400, backgroundLayer, true, 1);

        String[][] imgs ={{"character.png"}};
        MainCharacter image = new MainCharacter(imgs, 100, 100, 60, 60, playerLayer, true, 0, 12, this);
        
        img = "IMGS/UI/9-Slice/Colored/green.png";
        Button button = new Button(img, 0, 0, 60, 20, UILayer, true, 0);

        addSprite(image);
        addSprite(background);
        addSprite(button);
    }

    public ArrayList<Sprite> tick(boolean[] boolInput, int[] intInput){
        for(int i=0;i<images.size();i++){
            images.get(i).tick(boolInput,intInput);
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