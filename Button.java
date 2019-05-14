public class Button extends Sprite{
    private boolean moves, hasText;
    private int[] moveTo, textPos, intInput;
    private Game game;
    private Text text;
    private static final String imgName = "IMGS/UI/9-Slice/Colored/green.png";
    //TODO: move text with stuff
    public Button( int xPos, int yPos, int xSize, int ySize, 
            int layer, boolean visibility, int hasHitbox){
        super(imgName,xPos,yPos,xSize,ySize,layer,visibility,hasHitbox);
        moves=false;
    }
    public Button(int xPos, int yPos, int xSize, int ySize, 
            int layer, boolean visibility, int hasHitbox, int[][] hitbox){
        super(imgName,xPos,yPos,xSize,ySize,layer,visibility,hasHitbox,hitbox);
        moves=false;
    }
    public Button(int xPos, int yPos, int xSize, int ySize, 
            int layer, boolean visibility, int hasHitbox, int[] moveTo, Game game){
        super(imgName,xPos,yPos,xSize,ySize,layer,visibility,hasHitbox);
        moves=true;
        this.moveTo=moveTo;
        this.game=game;
    }
    public Button(int xPos, int yPos, int xSize, int ySize, 
            int layer, boolean visibility, int hasHitbox, int[][] hitbox, 
            int[] moveTo, Game game){
        super(imgName,xPos,yPos,xSize,ySize,layer,visibility,hasHitbox,hitbox);
        moves=true;
        this.moveTo=moveTo;
        this.game=game;
    }

    public void buttonAction(){
        if(moves){
            game.setXOffset(moveTo[0]);
            game.setYOffset(moveTo[1]);
        }
    }
    public boolean tick(boolean[] boolInput, int[] intInput){
        this.intInput=intInput;
        if(visibility&&boolInput[4]&&wouldHitBox(intInput)){
            buttonAction();
            return true;
        }
        return false;
    }


    public void addText(Text text){//TODO: fix to a position
        hasText=true;
        this.text=text;
        textPos = new int[2];
    }

    public int[] getIntInput(){
        return intInput;
    }
}