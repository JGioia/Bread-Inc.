import java.util.Scanner;

public class SimpleConsole{

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

    public static String getInputFromOptions(String[] options){
        Scanner scan = new Scanner(System.in);
        String input="";
        boolean badInput=true;
        while(badInput){
            System.out.print("You can ");
            for(int i=0;i<options.length;i++){
                System.out.print(options[i]);
                if(i<options.length-1)
                    System.out.print(", ");
            }
            System.out.println();
            input=scan.nextLine();
            for(int i=0;i<options.length;i++)
                if(input.toUpperCase().equals(options[i].toUpperCase()))
                    badInput=false;
        }
        return input;
    }
}