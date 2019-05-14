public class TextButton extends Button{
    Text text;
    public TextButton(int xPos, int yPos, int xSize, int ySize, 
    int layer, boolean visibility, int hasHitbox, String textString){
        super(xPos, yPos, xSize, ySize, layer, visibility, hasHitbox);
        int[][] hitbox = {{xPos+20,xPos+xSize-20},{yPos+20,yPos+ySize-20}};
        text = new Text(textString, layer+1, hitbox);
    }
    public void initialize(Game g){
        super.initialize(g);
        for(Sprite sprite : text.sprites){
            sprite.initialize(g);
        }
    }
    
    public void moveTo(int[] position){
        super.setXPos(position[0]);
        super.setYPos(position[1]);
        text.moveTo(position);
    }

    public void buttonAction(){
        
    }

    public void setVisibility(boolean visibility){
        super.setVisibility(visibility);
        text.setVisibility(visibility);
    }
}