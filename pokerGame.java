import java.util.*;
public class pokerGame{
    static table t1;
    public static void main(String [] args){
        initialization();
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
        System.out.println(t1);
        simpleConsole.enterContinue();
        for(int i=0;i<t1.getPlayers().length;i++){
            simpleConsole.clearScreen();
            System.out.println(t1.toStringPlayerView(i));
            simpleConsole.enterContinue();
        }
    }
}