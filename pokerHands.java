import java.util.ArrayList;

public class pokerHands{
    public static void main(String[] args){//test method
        card c1 = new card("Spades","Seven");
        card c2 = new card("Spades", "Six");
        card c3 = new card("Spades","Eight");
        card c4 = new card("Spades", "Nine");
        card c5 = new card("Spades","Jack");
        card[] cards = {c1,c2,c3,c4,c5};
        System.out.println(getCombinationValue(cards));
    }

    //doesn't work for ties
    public static int getWinner(table t){
        int winner=1;
        double winningScore=0;
        for(int i=0;i<t.getNumPlayers();i++){
            pokerHands playerHand = new pokerHands(t.getPlayer(i),t);
            double bestValue=playerHand.getBestValue();
            if(bestValue>winningScore){
                winningScore=bestValue;
                winner=i;
            }
        }
        return winner;
    }
    
    public card[] cards;
    public card[][] combinations;
    public player p;
    public table t;
    
    //doesn't work if the game ends early by fold
    public pokerHands(player p, table t){
        this.p=p;
        this.t=t;
        card[] playerCards = p.getCards();
        card[] tableCards = t.getCards();
        cards = new card[playerCards.length+tableCards.length];
        for(int i=0;i<playerCards.length;i++){
            cards[i]=playerCards[i];
        }
        for(int i=playerCards.length;i<cards.length;i++){
            cards[i]=tableCards[i-playerCards.length];
        }
        int[][] combinationOrder=new int[0][0];
        if(cards.length==7){
            combinationOrder=nCr75();
            combinations=new card[combinationOrder.length][combinationOrder[0].length];
        }else{
            combinations= new card[0][0];
        }
        for(int i=0;i<combinations.length;i++)
            for(int j=0;j<combinations[i].length;j++)
                combinations[i][j]=cards[combinationOrder[i][j]-1];

    }
    
    public int[][] nCr75(){
        int [][]result={{1,2,3,4,5},
                        {1,2,3,4,6},
                        {1,2,3,4,7},
                        {1,2,3,5,6},
                        {1,2,3,5,7},
                        {1,2,3,6,7},
                        {1,2,4,5,6},
                        {1,2,4,5,7},
                        {1,2,4,6,7},
                        {1,2,5,6,7},
                        {1,3,4,5,6},
                        {1,3,4,5,7},
                        {1,3,4,6,7},
                        {1,3,5,6,7},
                        {1,4,5,6,7},
                        {2,3,4,5,6},
                        {2,3,4,5,7},
                        {2,3,4,6,7},
                        {2,3,5,6,7},
                        {2,4,5,6,7},
                        {3,4,5,6,7}};
            return result;
    }
    
    public double getBestValue(){
        if(p.hasFeld()){
            return 0;
        }
        double result=0;
        for(int i=0;i<combinations.length;i++){
            double temp= getCombinationValue(combinations[i]);
            if(temp>result)
                result=temp;
        }
        return result;
    }
    
    /*
        Hand values:
            Straight Flush: 10,000,000,000,000
            4 of a kind: 1,000,000,000,000
            Full House: 100,000,000,000
            Flush: 10,000,000,000
            Straight: 1,000,000,000
            3 of a kind: 100,000,000
            2 pair: 10,000,000
            1 pair: 1,000,000
            High Card: 20,000-140,000
            2nd High Card: 2,000-14,000
            3rd High Card: 200-1,400
            4th High Card: 20-140
            5th High Card: 2-14
    */

    public static double getCombinationValue(card[] combination){
        double result=0;
        result+=hasStraightFlush(combination);
        result+=has4OfAKind(combination);
        result+=hasFullHouse(combination);
        result+=hasFlush(combination);
        result+=hasStraight(combination);
        result+=has3OfAKind(combination);
        result+=has2Pair(combination);
        result+=hasPair(combination);
        result+=getHighCardValue(combination);
        return result;
    }
    
    public static double getHighCard(card[] combination){
        double result=2;
        for(int i=0;i<combination.length;i++){
            if(combination[i].getFaceValue()>result)
                result=combination[i].getFaceValue();
        }
        return result;
    }
    public static double getHighCard(ArrayList<card> combination){
        double result=2;
        for(int i=0;i<combination.size();i++){
            if(combination.get(i).getFaceValue()>result)
                result=combination.get(i).getFaceValue();
        }
        return result;
    }

    public static double hasStraightFlush(card[] combination){
        if(hasStraight(combination)>0&&hasFlush(combination)>0)
            return hasStraight(combination)*10000;
        return 0;
    }
    public static double has4OfAKind(card[] combination){
        double result=0;
        for(int i=0;i<combination.length;i++){
            if(findCardNumber(combination,combination[i]).size()>3){
                result+=1000000000000.0*combination[i].getFaceValue();
                break;
            }
        }
        return result;
    }
    public static double hasFullHouse(card[] combination){
        double result=0;
        if(has3OfAKind(combination)>0){
            card[] comCopy = new card[combination.length];
            for(int i=0;i<comCopy.length;i++){
                comCopy[i]=combination[i];
            }
            int pair1Value=(int)has3OfAKind(combination)/100000000;
            card temp = new card(pair1Value);
            ArrayList<Integer> cardsToRemove =  findCardNumber(combination, temp);
            for(int i=cardsToRemove.size()-1;i>=0;i--){
                comCopy=removeCard(comCopy, cardsToRemove.get(i));
            }
            if(hasPair(comCopy)>0){
                int pair2Value=(int)hasPair(comCopy)/1000000;;
                result+=pair1Value*100000000000.0;
                result+=pair2Value*10000000000.0;
            }
        }
        return result;
    }
    public static double hasFlush(card[] combination){
        double result=0;
        boolean hasFlush=true;
        String prevSuit="";
        if(combination.length>0)
            prevSuit=combination[0].getSuit();
        for(int i=1;i<combination.length;i++){
            if(!prevSuit.equals(combination[i].getSuit())){
                hasFlush=false;
                break;
            }
        }
        if(hasFlush)
            result+=10000000000.0*getHighCard(combination);
        return result;
    }
    public static double hasStraight(card[] combination){
        if(findCardNumber(combination, 14).size()==1&&findCardNumber(combination, 5).size()==1
            &&findCardNumber(combination, 4).size()==1&&findCardNumber(combination, 3).size()==1
            &&findCardNumber(combination, 2).size()==1){
            return 5000000000.0;
        }
        for(int i=14;i>5;i--){
            if(findCardNumber(combination, i).size()==1&&findCardNumber(combination, i-1).size()==1
                &&findCardNumber(combination, i-2).size()==1&&findCardNumber(combination, i-3).size()==1
                &&findCardNumber(combination, i-4).size()==1){
                return 1000000000.0*i;
            }
        }
        return 0.0;
    }
    public static double has3OfAKind(card[] combination){
        double result=0;
        for(int i=0;i<combination.length;i++){
            if(findCardNumber(combination,combination[i]).size()>2){
                result+=100000000.0*combination[i].getFaceValue();
                break;
            }
        }
        return result;
    }
    public static double has2Pair(card[] combination){//remove card method doesn't work
        double result=0;
        if(hasPair(combination)>0){
            card[] comCopy = new card[combination.length];
            for(int i=0;i<comCopy.length;i++){
                comCopy[i]=combination[i];
            }
            int pair1Value=(int)hasPair(combination)/1000000;
            card temp = new card(pair1Value);
            ArrayList<Integer> cardsToRemove =  findCardNumber(combination, temp);
            for(int i=cardsToRemove.size()-1;i>=0;i--){
                comCopy=removeCard(comCopy, cardsToRemove.get(i));
            }
            if(hasPair(comCopy)>0){
                int pair2Value=(int)hasPair(comCopy)/1000000;;
                if(pair1Value>pair2Value){
                    result+=pair1Value*10000000;
                    result+=pair2Value*1000000;
                }else{
                    result+=pair2Value*10000000;
                    result+=pair1Value*1000000;
                }
            }
        }
        return result;
    }
    public static double hasPair(card[] combination){
        double result=0;
        for(int i=0;i<combination.length;i++){
            if(findCardNumber(combination,combination[i]).size()>1){
                result+=1000000*combination[i].getFaceValue();
                break;
            }
        }
        return result;
    }

    public static double getHighCardValue(card[] combination){
        ArrayList<card> comCopy= new ArrayList<card>();
        for(int i=0;i<combination.length;i++){
            comCopy.add(combination[i]);
        }
        double result=0;
        for(int i=0;i<comCopy.size();i++){
            double tempHighCard=getHighCard(comCopy);
            result+=Math.pow(10,comCopy.size()-1-i)*tempHighCard;
            for(int j=0;j<comCopy.size();j++){
                if(comCopy.get(j).getFaceValue()==tempHighCard){
                    comCopy.remove(j);
                    i--;
                    break;
                }
            }
        }
        return result;
    }

    public static card[] removeCard(card[] cards, int cardPos){
        card[] temp = new card[cards.length-1];
        for(int i=0;i<cards.length;i++){
            if(i<cardPos)
                temp[i]=cards[i];
            else if(i>cardPos)
                temp[i-1]=cards[i];
        }
        return temp;
    }
    public static ArrayList<Integer> findCardNumber(card[]cards, card c){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i=0;i<cards.length;i++){
            if(c.getFaceValue()==cards[i].getFaceValue())
                result.add(i);
        }
        return result;
    }
    public static ArrayList<Integer> findCardNumber(card[]cards, int cardValue){
        card c = new card(cardValue);
        ArrayList<Integer> result= new ArrayList<Integer>();
        for(int i=0;i<cards.length;i++){
            if(c.getFaceValue()==cards[i].getFaceValue())
                result.add(i);
        }
        return result;
    }
}