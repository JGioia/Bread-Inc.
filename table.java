public class table extends simpleCardGroup{
    private player [] players;
    private int pot,turnNum=0, roundNum=0, layer;
    private Game g;
    private Text[] texts = new Text[0];

    public table(deck d1, Game g, String[] names, int money, int layer){
        super (d1);
        this.layer = layer;
        this.g=g;
        players=new player[names.length];
        for(int i=0;i<names.length;i++){
            if(i==4)
                players[i]= new player(d1, g , money, names[i], layer);
            else
                players[i]= new player(d1, g , money, names[i], layer, false);
        }
        refreshPot();
        setSprites();
    }

    public void refreshPot(){
        pot=0;
        for(int i=0;i<players.length;i++)
            pot+=players[i].getBet();
    }
    public void givePotToPlayer(int playerNum){
        players[playerNum].increaseMoney(pot);
        removePot();
    }
    public void removePot(){
        for(int i=0;i<players.length;i++)
            players[i].removeBet();
        refreshPot();
    }
    public int getPot(){
        return pot;
    }

    public void removePlayer(int playerNum){
        if(playerNum>=0&&playerNum<players.length){
            player[] temp= new player[players.length-1];
            for(int i=0;i<players.length;i++){
                if(i<playerNum)
                    temp[i]=players[i];
                else if(i>playerNum)
                    temp[i-1]=players[i];
            }
            players= new player[temp.length];
            for(int i=0;i<players.length;i++){
                players[i]=temp[i];
            }
        }
    }
    public void addPlayer(player pNew){
        player[] temp=new player[players.length];
        for(int i=0;i<players.length;i++){
            temp[i]=players[i];
        }
        players= new player[players.length+1];
        for(int i=0;i<players.length-1;i++){
            players[i]=temp[i];
        }
        players[players.length-1]=pNew;
    }
    public player [] getPlayers(){
        return players;
    }
    public player getPlayer(int playerNum){
        return players[playerNum];
    }
    public int getNumPlayers(){
        return players.length;
    }

    public void givePlayersXCards(int x){
        for(int i=0;i<players.length;i++){
            for(int j=0;j<x;j++)
                players[i].takeCard();
        }
    }
    public void removePlayersCards(){
        for(int i=0;i<players.length;i++){
            players[i].clearCards();
        }
    }

    public boolean playerHasFeld(int playerNum){
        return players[playerNum].hasFeld();
    }

    public boolean playerCanBet(int playerNum, int betAmount){
        if(players[playerNum].getMoney()>=betAmount)
            return true;
        return false;
    }
    public void betPlayer(int playerNum, int betAmount){
        players[playerNum].raiseBet(betAmount);
    }
    public boolean playerCanCall(int playerNum){
        if(playerCanBet(playerNum, getHighestBet()-players[playerNum].getBet()))
            return true;
        return false;
    }
    public void callPlayer(int playerNum){
        betPlayer(playerNum, getHighestBet()-players[playerNum].getBet());
    }//make
    public boolean playerCanRaise(int playerNum){
        if(playerCanBet(playerNum, getHighestBet()-players[playerNum].getBet()+1))
            return true;
        return false;
    }
    public int playerMaxRaise(int playerNum){
        return players[playerNum].getMoney()-getHighestBet()+players[playerNum].getBet();
    }
    public void raisePlayer(int playerNum, int raiseAmount){
        betPlayer(playerNum, getHighestBet()-players[playerNum].getBet()+raiseAmount);
    }

    public int getPlayerBet(int playerNum){
        return players[playerNum].getBet();
    }
    public int getHighestBet(){
        int highBet=0;
        for(int i=0;i<players.length;i++){
            if(players[i].getBet()>highBet){
                highBet=players[i].getBet();
            }
        }
        return highBet;
    }

    public boolean playerIsHuman(int playerNum){
        return players[playerNum].isHuman();
    }

    public int getPlayerMoney(int playerNum){
        return players[playerNum].getMoney();
    }

    public void foldPlayer(int playerNum){
        players[playerNum].fold();
    }
    public void unfoldPlayers(){
        for(int i=0;i<players.length;i++){
            players[i].unfold();
        }
    }

    public boolean readyToContinue(){
        boolean result=true;
        for(int i=0;i<players.length;i++)
            if(!(players[i].hasFeld()||players[i].getBet()==getHighestBet())){
                result=false;
            }
        return result;
    }

    public void returnCardsToDeck(){
        returnCards();
        for(int i=0;i<players.length;i++)
            players[i].returnCards();
    }
    
    public void makeHandsVisible(){
        for(int i=0;i<players.length;i++){
            players[i].setHandVisibility(true);
        }
    }
    public void makeHandsInvisible(){
        for(int i=0;i<players.length;i++){
            players[i].setHandVisibility(false);
        }
    }

    public void incrTurn(){
        turnNum++;
    }
    public int getTurn(){
        return turnNum;
    }
    public void setTurn(int turnNum){
        this.turnNum=turnNum;
    }

    public void incrRound(){
        roundNum++;
    }
    public int getRound(){
        return roundNum;
    }
    public void setRound(int roundNum){
        this.roundNum=roundNum;
    }

    public String toString(){
        String result="Round: "+roundNum+"\nTurn: "+turnNum;
        for(int i=0;i<players.length;i++){
            result+="\n\n"+players[i]+"\n";
        }
        result+="\nPot: "+pot+"\nTable: "+super.toString()+"\n";
        return result;
    }
    public String toStringPlayerView(int playerNum){
        String result="Player's "+players[playerNum].getName()+"'s turn: \nRound: "+roundNum+"\nTurn: "+turnNum;
        for(int i=0;i<players.length;i++){
            if(i!=playerNum)
                result+="\n\n"+players[i];
        }
        result+="\n\nPot: "+pot+"\nTable: "+super.toString();
        result+="\n\nYour Hand: "+players[playerNum].toStringHandVisible()+"\n";
        return result;
    }
    public String toStringPlayerWon(int playerNum){
        makeHandsVisible();
        givePotToPlayer(playerNum);
        String result=toString()+"\n\nWinner: "+players[playerNum].getName()+"\n";
        returnCardsToDeck();
        unfoldPlayers();
        return result;
    }

    public void setTexts(boolean playerWon, int playerNum){
        for(Text text: texts)
            text.setVisibility(false);
        
        texts = new Text[3];
        if(playerWon)
            texts = new Text[4];
        int[] position = {500,400};
        texts[0]=new Text("Round: "+roundNum, layer, position);
        position[1]+=12;
        texts[1]=new Text("Turn: "+turnNum, layer, position);
        position[1]+=12;
        texts[2]=new Text("Pot: "+pot, layer, position);
        position[1]+=12;
        if(playerWon)
            texts[3] = new Text(players[playerNum].getName()+" won this hand!", layer, position);

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
    public void setPlayers(){
        int[] position = {800,30};
        if(players.length>0)
            players[0].setPosition(position);
        position[0]=10;
        position[1]=400;
        if(players.length>1)
            players[1].setPosition(position);
        position[0]=1600;
        position[1]=400;
        if(players.length>2)
            players[2].setPosition(position);
        position[0]=800;
        position[1]=800;
        if(players.length>3){
            players[3].setPosition(position);
            players[3].setCardsVisible();
        }
    }
    public void setCards(){
        int[] position = {600,400};
        getDeck().setPosition(position);
        card[] cards = this.getCards();
        for(int i=0;i<cards.length;i++){
            cards[i].setOnFront(true);
            cards[i].setXPos(700+(i*82));
            cards[i].setYPos(400);
        }
    }

    public void setSprites(){
        for(player p : players){
            p.setCardsInvisible();
        }
        setTexts(false, 0);
        setPlayers();
        setCards();
    }
    public void setSpritesPlayerWon(int playerNum){
        for(player p : players){
            p.setCardsVisible();
        }
        setTexts(true, playerNum);
        setPlayers();
        setCards();
    }
}