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
        for(int i=0;i<t1.getPlayers().length||!t1.readyToContinue();i++){
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
                String[] options = new String[3];
                options[0]="Fold";
                options[2]="Raise";
                if(t1.getPlayerBet(i)==t1.getHighestBet()){
                    options[1]="Check";
                }else{
                    options[1]="Call";
                }
                //side pots?
                String command = simpleConsole.getInputFromOptions(options);
                if(command.toUpperCase().equals("FOLD")){
                    t1.foldPlayer(i);
                }else if(command.toUpperCase().equals("RAISE")){
                    String message="How much do you want to bet:";
                    t1.raisePlayer(simpleConsole.getIntInput(t1.getPlayerMoney(i), 0, message));
                }else if(command.toUpperCase().equals("CALL")){
                    t1.callPlayer(i);
                }
                t1.refreshPot();
            }
        }
    }

    public static void round(){
        t1.incrRound();
        t1.setTurn(0);
        wonRound=false;
        t1.givePlayersXCards(2);
        t1.makeHandsInvisible();
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
        simpleConsole.clearScreen();
        int winner = pokerHands.getWinner(t1);
        System.out.println(t1.toStringPlayerWon(winner));
        simpleConsole.enterContinue();
    }
}