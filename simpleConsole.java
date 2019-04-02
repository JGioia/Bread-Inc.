import java.util.Scanner;
public class simpleConsole{

    public static void clearScreen(){
        for(int i=0;i<100;i++)
            System.out.println();
    }

    public static int getIntInput(String message){
        Scanner scan = new Scanner(System.in);
        int result=0;
        while(true){
            System.out.println(message);
            String input = scan.nextLine();
            try{
                result=Integer.parseInt(input);
                break;
            }catch(Exception e){
            }
        }
        return result;
    }
    public static int getIntInput(int low, String message){
        int result=0;
        while(true){
            result=getIntInput(message);
            if(result>=low)
                break;
        }
        return result;
    }
    public static int getIntInput(int high, int low, String message){
        int result=0;
        while(true){
            result=getIntInput(message);
            if(result>=low&&result<=high)
                break;
        }
        return result;
    }
    public static void enterContinue(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        scan.nextLine();
    }
}