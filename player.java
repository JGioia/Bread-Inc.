public class player extends simpleCardGroup{
    private int money, bet;
    private boolean visibleHand, feld;
    private String name;
    public player(deck d1, int money, String name){
        super(d1);
        this.money=money;
        bet=0;
        visibleHand=false;
        this.name=name;
        feld=false;
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
        visibleHand=!visibleHand;
    }
    public void setHandVisibility(boolean visibleHand){
        this.visibleHand=visibleHand;
    }
    public boolean getHandVisibility(){
        return visibleHand;
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
}