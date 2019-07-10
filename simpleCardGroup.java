public abstract class SimpleCardGroup{
    private Card [] cards = new Card[0];
    private Deck d1;
    public SimpleCardGroup(Deck d1){
        this.d1=d1;
    }
    
    public void addCard(Card tempCard){
        Card [] tempCards = new Card [cards.length+1];
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
        cards = new Card[0];
    }
    public Card[] getCards(){
        return cards;
    }
    public Card getCard(int cardNum){
        return cards[cardNum];
    }
    public int getLength(){
        return cards.length;
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
    public Deck getDeck(){
        return d1;
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