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

    public void tick(boolean[] input){
        if(input[0])
            moveUp();
        if(input[1])
            moveDown();
        if(input[2])
            moveRight();
        if(input[3])
            moveLeft();
    }
}