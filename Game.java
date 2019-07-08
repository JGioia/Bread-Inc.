import java.util.*;
//TODO: Add music
//Declare a new class called Game which uses JFrame and KeyListener
public class Game {

    private ArrayList<Sprite> images= new ArrayList<Sprite>();
    private int nextId=0, xOffset=0, yOffset=0;
    private final int backgroundLayer = 0, playerLayer = 1, UILayer=2, menuLayer=3;
    private EscapeMenu escapeMenu;
    private Button b;
    private PokerGame p;
    
    public Game(){
        /*
        String[][] imgs ={{"character.png"}};
        MainCharacter image = new MainCharacter(imgs, 640/2-60/2, 480/2-60/2, 50, 50, playerLayer, true, 0, 12, this);
        */

        escapeMenu = new EscapeMenu(menuLayer, this);

        /*
        deck d = new deck(5, this);

        String[] names = {"Ay","Him","them", "Player"};
        table t = new table(d, this, names, 50, 5);
        t.givePlayersXCards(2);
        t.takeCard();
        t.setSprites();

        b = new DraggableButton(30, 400, 100, true, 30);
        b.initialize(this);

        createGrass(1920,1080);
        */
        p = new PokerGame(4,this);
    }

    public ArrayList<Sprite> tick(boolean[] boolInput, int[] intInput){
        for(int i=0;i<images.size();i++){
            images.get(i).tick(boolInput,intInput);
        }
        escapeMenu.tick(boolInput, intInput);
        p.tick(boolInput, intInput);
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

    public void addSprites(Sprite[] sprites){
        for(Sprite sprite : sprites){
            addSprite(sprite);       
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
        int spriteSize=32;
        for(int x=0;x<=xSize;x+=spriteSize){
            for(int y=0;y<=ySize;y+=spriteSize){
                Sprite grass = new Sprite(img, x, y, spriteSize, spriteSize, backgroundLayer, true, 0);
                addSprite(grass);
            }
        }
    }
}