public class card{
    private String suit;
    private String suitShorthand;
    private String face;
    private String faceShorthand;
    private int faceValue;

    public card(String suit, String face){
        this.suit=suit;
        this.face=face;
        faceValue=faceToValue(face);
        setFaceShorthand();
        setSuitShorthand();
    }
    public card(int faceValue){
        suit="Spades";
        this.faceValue=faceValue;
    }

    private int faceToValue(String face){
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
    }
    private void setFaceShorthand(){
        if(faceValue<11){
            faceShorthand=Integer.toString(faceValue);
        }else{
            switch(face){
                case "Jack":
                    faceShorthand="J";
                    break;
                case "Queen":
                    faceShorthand="Q";
                    break;
                case "King":
                    faceShorthand="K";
                    break;
                case "Ace":
                    faceShorthand="A";
                    break;
            }
        }
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
}