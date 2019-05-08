public class EscapeMenu extends Menu{
    public EscapeMenu(int menuLayer, Game game){
        super(createButtons(menuLayer), createBackground(menuLayer), game, true);
    }
    public static Button[] createButtons(int menuLayer){
        String imgName="IMGS/UI/9-Slice/Colored/green.png";
        Button[] buttons = new Button[1];
        buttons[0] = new Button(imgName, 210, 160, 80, 30, menuLayer+1, false, 0);
        return buttons;
    }
    public static Sprite[] createBackground(int menuLayer){
        Sprite[] background = new Sprite[1];
        String imgName="IMGS/UI/9-Slice/Ancient/tan_inlay.png";
        background [0] = new Sprite(imgName, 200, 150, 100, 200, menuLayer, false, -1);
        return background;
    }
    public void tick(boolean[] boolInput, int[] intInput){
        if(boolInput[5])
            makeVisible();
        else
            makeInvisible();
        super.tick(boolInput, intInput);
    }
}