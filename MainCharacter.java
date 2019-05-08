
public class MainCharacter extends Character{
    private boolean controllable;
    private int xOffset,yOffset;
    public MainCharacter(String[][] imgNames, int xPos, int yPos, int xSize, int ySize, 
        int layer, boolean visibility, int hasHitbox, int speed, Game game){
        super(imgNames, xPos, yPos, xSize, ySize, layer, visibility,hasHitbox, speed, game);
        controllable=true;
        xOffset=xPos;
        yOffset=yPos;
    }

    public boolean moveUp(){
        if(controllable&&super.moveUp()){
            game.addYOffset(-getSpeed());
            return true;
        }
        return false;
    }
    public boolean moveDown(){
        if(controllable&&super.moveDown()){
            game.addYOffset(getSpeed());
            return true;
        }
        return false;
    }
    public boolean moveLeft(){
        if(controllable&&super.moveLeft()){
            game.addXOffset(-getSpeed());
            return true;
        }
        return false;
    }
    public boolean moveRight(){
        if(controllable&&super.moveRight()){
            game.addXOffset(getSpeed());
            return true;
        }
        return false;
    }

    public void tick(boolean[] boolInput, int[] intInput){
        setXPos(game.getXOffset()+xOffset);
        setYPos(game.getYOffset()+yOffset);
        if(boolInput[0])
            moveUp();
        if(boolInput[1])
            moveDown();
        if(boolInput[2])
            moveRight();
        if(boolInput[3])
            moveLeft();
    }
}