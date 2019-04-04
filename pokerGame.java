import java.util.*;
public class pokerGame{
    static table t1;
    static boolean won=false;
    static boolean wonRound;
    public static void main(String [] args){
        initialization();
        while(!won){
            round();
        }
        turn();

    }
    private static void initialization(){
        Scanner scan = new Scanner(System.in);
        deck d1 = new deck();
        String numPlayersMessage = "Number of players: ";
        int numPlayers = simpleConsole.getIntInput(2, numPlayersMessage);
        String [] names=new String[numPlayers];
        for(int i=0;i<numPlayers;i++){
            System.out.println("What is player "+(i+1)+"'s name?");
            names[i]=scan.nextLine();
        }

        simpleConsole.clearScreen();
        t1 = new table(d1, names, 50);
    }

    public static void turn(){
        t1.incrTurn();
        simpleConsole.clearScreen();
        System.out.println(t1);
        simpleConsole.enterContinue();
        for(int i=0;i<t1.getPlayers().length;i++){
            int playersAlive=0;
            for(int j=0;j<t1.getPlayers().length;j++){
                if(!t1.playerHasFeld(j))
                    playersAlive++;
            }
            if(playersAlive<2){
                wonRound=true;
                break;
            }

            if(!t1.playerHasFeld(i)){
                simpleConsole.clearScreen();
                System.out.println(t1.toStringPlayerView(i));
                simpleConsole.enterContinue();//get what player wants to do
                t1.refreshPot();
            }
        }
    }

    public static void round(){
        wonRound=false;
        t1.givePlayersXCards(2);
        turn();
        if(!wonRound){
            t1.takeCards(3);
            turn();
        }
        if(!wonRound){
            t1.takeCard();
            turn();
        }
        if(!wonRound){
            t1.takeCard();
            turn();
        }
        int winner = pokerHands.getWinner(t1);
        System.out.println(t1.toStringPlayerView(winner));
        simpleConsole.enterContinue();
    }
}