public class table extends simpleCardGroup{
    private player [] players;
    private int pot,turnNum=0, roundNum=0;;

    public table(deck d1, String[] names, int money){
        super (d1);
        players=new player[names.length];
        for(int i=0;i<names.length;i++){
            players[i]= new player(d1, money, names[i]);
        }
        refreshPot();
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

    

    public void incrTurn(){
        turnNum++;
    }
    public int getTurn(){
        return turnNum;
    }
    public void setTurn(int turnNum){
        this.turnNum=turnNum;
    }

    public String toString(){
        String result="Round: "+turnNum;
        for(int i=0;i<players.length;i++){
            result+="\n\n"+players[i]+"\n";
        }
        result+="\nPot: "+pot+"\nTable: "+super.toString()+"\n";
        return result;
    }

    public String toStringPlayerView(int playerNum){
        String result="Player's "+players[playerNum].getName()+"'s turn: \nRound: "+turnNum;
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
        //return cards
        String result=toString()+"\n\nWinner:"+players[playerNum].getName();
    }
}