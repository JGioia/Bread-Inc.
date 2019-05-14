import java.awt.*;  
import java.io.*;
import javax.imageio.*;
public class card extends Sprite{
    private String suit;
    private String suitShorthand;
    private String face;
    private String faceShorthand;
    private int faceValue;
    private Image[] imgs;
    private boolean onFront;

    public card(String suit, String face, int layer){
        super(getImageName(suit, face), 0, 0, 82, 118, layer, true, -1);
        this.suit=suit;
        this.face=face;
        setFaceValue();
        setFaceShorthand();
        setSuitShorthand();
        imgs = new Image[2];
        try {
            imgs[0] = ImageIO.read( new File(getImageName(suit, face)) );
            imgs[1] = ImageIO.read(new File("IMGS/Cards/backRed.png"));
        } catch( IOException f ) {
            f.printStackTrace();
        }
        onFront=false;
    }

    public card(int cardValue){
        super(getImageName("Spades", "Ace"), 0, 0, 82, 118, -1, false, -1);
        this.faceValue=cardValue;
    }

    private static String getImageName(String suit, String face){
        int faceValue=faceToValue(face);
        return "IMGS/Cards/"+setStaticFaceShorthand(faceValue)+setStaticSuitShorthand(suit)+".png";
    }

    private void setFaceValue(){
        faceValue=faceToValue(face);
    }
    private static int faceToValue(String face){
        int value=0;
        switch(face){
            case "Two":
                value=2;
                break;
            case "Three":
                value=3;
                break;
            case "Four":
                value=4;
                break;
            case "Five":
                value=5;
                break;
            case "Six":
                value=6;
                break;
            case "Seven":
                value=7;
                break;
            case "Eight":
                value=8;
                break;
            case "Nine":
                value=9;
                break;
            case "Ten":
                value=10;
                break;
            case "Jack":
                value=11;
                break;
            case "Queen":
                value=12;
                break;
            case "King":
                value=13;
                break;
            case "Ace":
                value=14;
                break;
        }
        return value;
    }

    private void setSuitShorthand(){
        suitShorthand=setStaticSuitShorthand(suit);
    }
    private static String setStaticSuitShorthand(String suit){
        String suitShorthand="";
        switch(suit){
            case "Spades":
                suitShorthand="S";
                break;
            case "Diamonds":
                suitShorthand="D";
                break;
            case "Hearts":
                suitShorthand="H";
                break;
            case "Clubs":
                suitShorthand="C";
                break;
        }
        return suitShorthand;
    }


    private void setFaceShorthand(){
        faceShorthand=setStaticFaceShorthand(faceValue);
    }
    private static String setStaticFaceShorthand(int faceValue){
        String faceShorthand="";
        if(faceValue<11){
            faceShorthand=Integer.toString(faceValue);
        }else{
            switch(faceValue){
                case 11:
                    faceShorthand="J";
                    break;
                case 12:
                    faceShorthand="Q";
                    break;
                case 13:
                    faceShorthand="K";
                    break;
                case 14:
                    faceShorthand="A";
                    break;
            }
        }
        return faceShorthand;
    }

    public void changeOnFront(){
        onFront=!onFront;
    }
    public void setOnFront(boolean onFront){
        this.onFront=onFront;
    }
    public boolean getOnFront(){
        return onFront;
    }

    public int compareTo(Object c1){
        int result=0;
        if(faceValue>((card)c1).getFaceValue())
            result=1;
        else if(faceValue<((card)c1).getFaceValue())
            result=-1;
        else
            result=0;
        return result;
    }
    public boolean isSuitEqual(card c1){
        boolean result = false;
        if(suit.equals(c1.getSuit()))
            result=true;
        return result;
    }
    public boolean isCardOneGreater(card c1){
        boolean result=false;
        if(c1.getFaceValue()+1==faceValue)
            result=true;
        return result;
    } 
    public boolean isCardOneLess(card c1){
        boolean result=false;
        if(c1.getFaceValue()-1==faceValue)
            result=true;
        return result;
    }

    public int getFaceValue(){
        return faceValue;
    }
    public String getSuit(){
        return suit;
    }

    public String toString(){
        return faceShorthand + suitShorthand;
    }

    public Image getImage(){
        if(onFront)
            return imgs[0];
        return imgs[1];
    }
}