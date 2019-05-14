public class DraggableButton extends Button{
    int xMin, xMax, yLevel;
    public DraggableButton(int xMin, int xMax, int yLevel, boolean visibility, int layer){
        super(xMin, yLevel-15, 50, 30, layer, visibility, 0);
        this.xMin=xMin;
        this.xMax=xMax;
        this.yLevel=yLevel;
    }
    public void buttonAction(){
        int xMouse = getIntInput()[0]-25;
        if(xMouse<xMin){
            setXPos(xMin);
        }else if(xMouse>xMax){
            setXPos(xMax);
        }else{
            setXPos(xMouse);
        }
    }
    public double getPercentAcross(){
        return ((double)getXPos()-xMin)/(xMax-xMin);
    }
}