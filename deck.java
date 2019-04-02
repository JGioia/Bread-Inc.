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
        card [] temp = new card[cards.length];
        for(int i=0;i<cards.length;i++)
            temp[i]=cards[i];
        int [] sequence = new int[cards.length];
        for(int i=0;i<sequence.length;i++){
            int futureValue=(int)(Math.random()*(sequence.length-i));
            for(int j=0;j<i;j++)
                if(futureValue>=sequence[i])
                    futureValue++;
            sequence[i]=futureValue;
        }
        for(int i=0;i<sequence.length;i++)
            cards[i]=temp[sequence[i]];
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
}