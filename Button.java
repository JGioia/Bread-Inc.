public class Button extends Sprite{
    public Button(String imgName, int xPos, int yPos, int xSize, int ySize, 
            int layer, boolean visibility, int hasHitbox){
        super(imgName,xPos,yPos,xSize,ySize,layer,visibility,hasHitbox);
    }
    public Button(String imgName, int xPos, int yPos, int xSize, int ySize, 
            int layer, boolean visibility, int hasHitbox, int[][] hitbox){
        super(imgName,xPos,yPos,xSize,ySize,layer,visibility,hasHitbox,hitbox);
    }

    public void buttonAction(){
        visibility=false;
    }
    public void tick(boolean[] boolInput, int[] intInput){
        if(visibility&&boolInput[4]&&wouldHitBox(intInput))
            buttonAction();
    }
}