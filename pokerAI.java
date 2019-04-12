public class pokerAI{
    player p;
    table t;
    public pokerAI(player p, table t){
        this.p=p;
        this.t=t;
    }
    public static String getInput(String[] options){
        for(int i=0;i<options.length;i++){
            if(options[i].equals("Check"))
                return "Check";
            else if(options[i].equals("Call"))
                return "Call";
        }
        return "Fold";
    }
}