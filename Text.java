public class Text{
    Sprite[] sprites;
    String text;
    int[][] hitbox;
    int layer;
    Game game;
    public Text(String text, int layer, int[][] hitbox, Game game){
        this.text=text;
        this.layer=layer;
        this.hitbox=hitbox;
        this.game=game;
        createText();
        initializeSprites();
    }
    public void createText(){

    }
    public void initializeSprites(){

    }
}