public class UI{
    Sprite[] foreground;
    Sprite[] background;
    Game game;
    boolean fixed;
    int[][] position;
    public UI(Sprite[] foreground, Sprite[] background, Game game, boolean fixed){
        this.foreground=foreground;
        this.background=background;
        this.game=game;
        this.fixed=fixed;
        initializeSprites();
    }
    
    public void initializeSprites(){
        position = new int[foreground.length+background.length][2];
        for(int i=0;i<foreground.length;i++){
            game.addSprite(foreground[i]);
            position[i][0]=foreground[i].getXPos();
            position[i][1]=foreground[i].getYPos();
        }
        for(int i=0;i<background.length;i++){
            game.addSprite(background[i]);
            position[i+foreground.length][0]=background[i].getXPos();
            position[i+foreground.length][1]=background[i].getYPos();
        }
    }
    public void makeVisible(){
        for(Sprite sprite : foreground){
            sprite.setVisibility(true);
        }
        for(Sprite sprite : background){
            sprite.setVisibility(true);
        }
    }
    public void makeInvisible(){
        for(Sprite sprite : foreground){
            sprite.setVisibility(false);
        }
        for(Sprite sprite : background){
            sprite.setVisibility(false);
        }
    }

    public void tick(boolean[] boolInput, int[] intInput){
        if(fixed){
            for(int i=0;i<foreground.length;i++){
                foreground[i].setXPos(position[i][0]+game.getXOffset());
                foreground[i].setYPos(position[i][1]+game.getYOffset());
            }
            for(int i=0;i<background.length;i++){
                background[i].setXPos(position[i+foreground.length][0]+game.getXOffset());
                background[i].setYPos(position[i+foreground.length][1]+game.getYOffset());
            }
        }
    }
}