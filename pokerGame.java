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
        int i=0;
        while(i<t1.getPlayers().length||!t1.readyToContinue()){//doesn't end at the right time
            if(i>=t1.getPlayers().length)
                i=0;
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
                if(t1.playerCanRaise(i))
                    options[2]="Raise";
                if(t1.getPlayerBet(i)==t1.getHighestBet()){
                    options[1]="Check";
                }else{
                    if(t1.playerCanCall(i))
                        options[1]="Call";
                }
                for(int j=0;j<options.length;j++){
                    if(options[j]==null){
                        String[] temp=new String[options.length-1];
                        for(int k=0;k<temp.length;k++){
                            if(i<j)
                                temp[k]=options[k];
                            else
                                temp[k]=options[k+1];
                        }
                        options=temp;
                    }
                }
                //side pots?
                String command;
                if(t1.playerIsHuman(i)){
                    command = simpleConsole.getInputFromOptions(options);
                }else{
                    command = pokerAI.getInput(options);
                }
                if(command.toUpperCase().equals("FOLD")){
                    t1.foldPlayer(i);
                }else if(command.toUpperCase().equals("RAISE")){
                    String message="How much do you want to bet:";
                    t1.raisePlayer(i,simpleConsole.getIntInput(t1.playerMaxRaise(i), 0, message));
                }else if(command.toUpperCase().equals("CALL")){
                    t1.callPlayer(i);
                }
                t1.refreshPot();
            }
            i++;
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