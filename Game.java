import java.util.*;
//TODO: Add music
//Declare a new class called Game which uses JFrame and KeyListener
public class Game {

    private ArrayList<Sprite> images= new ArrayList<Sprite>();;
    private int nextId=0, xOffset=0, yOffset=0;;
    private final int backgroundLayer = 0, playerLayer = 1, UILayer=2, menuLayer=3;
    private EscapeMenu escapeMenu;
    
    public Game(){
        String img = "postapocalypse1.png";
        Sprite background = new Sprite(img, 0, 0, 640, 480, backgroundLayer, true, 0);

        String[][] imgs ={{"character.png"}};
        MainCharacter image = new MainCharacter(imgs, 640/2-60/2, 480/2-60/2, 50, 50, playerLayer, true, 0, 12, this);
        
        img = "IMGS/UI/9-Slice/Colored/green.png";
        int[] moveTo={-300,0};
        Button button = new Button(img, 100, 200, 100, 50, UILayer, true, 1, moveTo, this);

        escapeMenu = new EscapeMenu(menuLayer, this);

        addSprite(image);
        addSprite(button);
        createGrass(300,300);
    }

    public ArrayList<Sprite> tick(boolean[] boolInput, int[] intInput){
        for(int i=0;i<images.size();i++){
            images.get(i).tick(boolInput,intInput);
        }
        escapeMenu.tick(boolInput, intInput);
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
    public void setXOffset(int xOffset){
        this.xOffset=xOffset;
    }
    public void setYOffset(int yOffset){
        this.yOffset=yOffset;
    }

    public void createGrass(int xSize, int ySize){
        String img="IMGS/Pack2/grass.png";
        for(int x=0;x<=xSize;x+=16){
            for(int y=0;y<=ySize;y+=16){
                Sprite grass = new Sprite(img, x, y, 16, 16, backgroundLayer, true, 0);
                addSprite(grass);
            }
        }
    }
}