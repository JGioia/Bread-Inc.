public class MainCharacter extends Character{
    public MainCharacter(String[][] imgNames, int xPos, int yPos, int xSize, int ySize, 
        int layer, boolean visibility, int hasHitbox, int speed, Game game){
        super(imgNames, xPos, yPos, xSize, ySize, layer, visibility,hasHitbox, speed, game);
    }

    public boolean moveUp(){
        if(super.moveUp()){
            game.addYOffset(-getSpeed());
            return true;
        }
        return false;
    }
    public boolean moveDown(){
        if(super.moveDown()){
            game.addYOffset(getSpeed());
            return true;
        }
        return false;
    }
    public boolean moveLeft(){
        if(super.moveLeft()){
            game.addXOffset(-getSpeed());
            return true;
        }
        return false;
    }
    public boolean moveRight(){
        if(super.moveRight()){
            game.addXOffset(getSpeed());
            return true;
        }
        return false;
    }

    public void tick(boolean[] boolInput, int[] intInput){
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