public class simpleCardGroup{
    private card [] cards = new card[0];
    private deck d1;
    public simpleCardGroup(deck d1){
        this.d1=d1;
    }
    
    public void addCard(card tempCard){
        card [] tempCards = new card [cards.length+1];
        for(int i=0;i<cards.length;i++){
            tempCards[i]=cards[i];
        }
        tempCards[cards.length]=tempCard;
        cards=tempCards;
    }
    public void takeCard(){
        addCard(d1.takeTopCard());
    }
    public void takeCards(int numCards){
        for(int i=0;i<numCards;i++)
            takeCard();
    }
    
    public void clearCards(){
        cards = new card[0];
    }
    public card[] getCards(){
        return cards;
    }
    public void returnCards(){
        d1.addCards(cards);
        clearCards();
        d1.shuffle();
    }

    public void printDeck(){
        d1.printDeck();
    }
    public void shuffleDeck(){
        d1.shuffle();
    }

    public String toString(){
        String result="";
        if(cards.length==0){
            return "Empty";
        }
        for(int i=0;i<cards.length;i++){
            result+=cards[i]+"\t";
        }
        return result;
    }
}