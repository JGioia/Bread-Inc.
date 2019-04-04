public class deck{
    private card [] cards=new card[52];
    private final String [] suitList = {"Hearts", "Spades", "Diamonds", "Clubs"};
    private final String [] faceList = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
    public deck(){
        cards=initializeDeck();
        shuffle();
    }

    public card [] initializeDeck(){
        card [] result = new card[52];
        for(int i=0;i<suitList.length;i++)
            for(int j=0;j<faceList.length;j++)
                result[i*13+j]=new card(suitList[i],faceList[j]);
        return result;
    }
    
    public void shuffle(){
        for(int i=0;i<cards.length;i++){
            int newPos = (int)(Math.random()*cards.length);
            card temp = cards[i];
            cards[i]=cards[newPos];
            cards[newPos]=temp;
        }
    }
    
    public card takeTopCard(){
        card result = new card("Hearts","Two");
        if(cards.length>0){
            result=cards[0];
            card [] temp = new card[cards.length-1];
            for(int i=1;i<temp.length+1;i++){
                temp[i-1]=cards[i];
            }
            cards=temp;
        }
        return result;
    }

    public void addCard(card newCard){
        card[] temp = new card[cards.length+1];
        for(int i=0;i<cards.length;i++)
            temp[i]=cards[i];
        temp[cards.length]=newCard;
        cards=temp;
    }
    public void addCards(card[] newCards){
        for(int i=0;i<newCards.length;i++)
            addCard(newCards[i]);
    }

    public void printDeck(){
        for(int i=0;i<cards.length;i++){
            System.out.print(cards[i]+"\t");
        }
    }

    public int getDeckSize(){
        return cards.length;
    }
}