public class player extends simpleCardGroup{
    private int money, bet, layer=5;
    private boolean visibleHand, feld, isHuman;
    private String name;
    private Text[] texts = new Text[0];
    private Game g;
    private int[] position = {0,0};
    public player(deck d1, Game g, int money, String name, int layer){
        super(d1);
        this.money=money;
        bet=0;
        visibleHand=false;
        this.name=name;
        feld=false;
        isHuman=true;
        this.g=g;
    }
    public player(deck d1, Game g, int money, String name, int layer, boolean isHuman){
        super(d1);
        this.money=money;
        bet=0;
        visibleHand=false;
        this.name=name;
        feld=false;
        this.isHuman=isHuman;
        this.g=g;
    }

    public boolean betValidity(int bet){
        if(bet>money)
            return false;
        return true;
    }
    public void removeBet(){
        bet=0;
    }
    public void setBet(int bet){
        decreaseMoney(bet-this.bet);
        this.bet=bet;
    }
    public void raiseBet(int raiseAmount){
        setBet(bet+raiseAmount);
    }
    public int getBet(){
        return bet;
    }

    public void setMoney(int money){
        this.money=money;
    }
    public void increaseMoney(int increaseAmount){
        money+=increaseAmount;
    }
    public void decreaseMoney(int decreaseAmount){
        money-=decreaseAmount;
    }
    public int getMoney(){
        return money;
    }

    public void changeHandVisibility(){
        setHandVisibility(!visibleHand);
    }
    public void setHandVisibility(boolean visibleHand){
        this.visibleHand=visibleHand;
        for(card temp : this.getCards()){
            temp.setOnFront(visibleHand);
        }
    }
    public boolean getHandVisibility(){
        return visibleHand;
    }

    public void setPosition(int[] position){
        this.position=position;
        setSprites();
    }
    public int[] getPosition(){
        return position;
    }

    public void fold(){
        feld=true;
    }
    public void unfold(){
        feld=false;
    }
    public boolean hasFeld(){
        return feld;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public void setLayer(int layer){
        this.layer=layer;
    }
    public int getLayer(){
        return layer;
    }

    public boolean isHuman(){
        return isHuman;
    }

    public String toString(){
        String result=name+":\nMoney: "+money+"\nBet: "+bet+"\nFeld: "+feld+"\n";
        if(visibleHand)
            result+="Hand: "+super.toString();
        else
            result+="Hand: ??";
        return result;
    }
    public String toStringHandVisible(){
        return "\nMoney: "+money+"\nBet: "+bet+"\nFeld: "+feld+"\n"+"Hand: "+super.toString();
    }

    public void setTexts(){
        for(Text text: texts)
            text.setVisibility(false);
        
        texts = new Text[4];
        texts[0]=new Text("Name: "+name, layer, position);
        int [] point = {position[0], position[1]+24};
        texts[1]=new Text("Money: "+money, layer, point);
        point[1]+=12;
        texts[2]=new Text("Bet: "+bet, layer, point);
        point[1]+=12;
        if(feld)
            texts[3]=new Text("Feld",layer,point);
        else
            texts[3]=new Text("Has not feld",layer,point);

        int textLength=0;
        for(Text text : texts)
            textLength+=text.getLength();

        Sprite[] sprites = new Sprite[textLength];

        int j=0;
        for(Text text : texts){
            for(int k=0;k<text.getLength();k++){
                sprites[j]=text.getSprite(k);
                j++;
            }
        }
        g.addSprites(sprites);
    }
    public void setCards(){
        card[] cards = this.getCards();
        for(int i=0;i<cards.length;i++){
            cards[i].setXPos(position[0]+(i*82)+100);
            cards[i].setYPos(position[1]);
        }
        if(isHuman)
            for(card c : cards)
                c.setOnFront(true);
    }
    public void setCardsVisible(){
        card[] cards = this.getCards();
        for(int i=0;i<cards.length;i++){
            cards[i].setXPos(position[0]+(i*82)+100);
            cards[i].setYPos(position[1]);
        }
        for(card c : cards)
            c.setOnFront(true);
    }

    public void setCardsInvisible(){
        card[] cards = this.getCards();
        for(int i=0;i<cards.length;i++){
            cards[i].setXPos(position[0]+(i*82)+100);
            cards[i].setYPos(position[1]);
        }
        for(card c : cards)
            c.setOnFront(false);
    }

    public void setSprites(){
        setTexts();
        setCards();
    }

    public void setSpritesVisible(){
        setTexts();
        setCardsVisible();
    }
}