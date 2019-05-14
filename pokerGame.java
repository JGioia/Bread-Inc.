import com.sun.beans.editors.BooleanEditor;

public class pokerGame{
    table t1;
    boolean won=false, lost=false, wonRound=false, continuing=true, raising=false, inputting=false, botting=false, startOfRound=true, betweenRound=false;
    int layer, playerMove=-1, lastRaise=0, winner;
    Game g;
    Sprite[] sprites;
    Text[] texts;

    public pokerGame(int layer, Game g){
        this.layer=layer;
        this.g=g;
        deck d1 = new deck(layer, g);
        String[] names = {"Bot 1", "Bot 2", "Bot 3", "Player"};
        t1 = new table(d1, g, names, 50, layer);
        sprites = new Sprite[7];
        texts = new Text[1];
        sprites[0] =  new TextButton(20, 990, 150,52, layer, false, 0, "Check");
        sprites[1] =  new TextButton(20, 990, 150,52, layer, false, 0, "Call");
        sprites[2] =  new TextButton(410,990,150,52, layer, false, 0, "Raise");
        sprites[3] =  new TextButton(820,990,150,52,layer,false,0,"Fold");
        sprites[4] =  new TextButton(1230,990,150,52,layer,false,0,"Continue");
        sprites[5] =  new DraggableButton(50, 900, 1005, false, layer);
        sprites[6] = new Sprite("IMGS/UI/9-Slice/Ancient/tan.png", 0, 980, 1920, 1080, layer-1, true, -1);
        int[] point = {0,0};
        texts[0]= new Text("", layer, point);
        for(Sprite s : sprites)
            s.initialize(g);
        for(Text t : texts){}
            //t.initialize(g);
        startRound();
    }

    /*
    boolInput[0]=check;
    boolInput[1]=raise;
    boolInput[2]=fold;
    boolInput[3]=continue;
    boolInput[4]=quit;
    intInput=raiseAmount;
    */
    public void tick(boolean[] rawBoolInput, int[] rawIntInput){
        boolean[] boolInput = new boolean[5];
        for(int i=0 ; i<5;i++)
            boolInput[i] = sprites[i].tick(rawBoolInput, rawIntInput);
        for(int i=5;i<sprites.length;i++){
            sprites[i].tick(rawBoolInput, rawIntInput);
        }
        DraggableButton d = (DraggableButton) sprites[5];
        int intInput = (int)((d.getPercentAcross())*t1.playerMaxRaise(3));

        t1.setSprites();
        t1.refreshPot();

        if(won){
            //TODO:setSpritesWon();
        }else if(lost){
            //TODO:setSpritesLost();
        }else if(wonRound){
            t1.setSpritesPlayerWon(winner);
            setSpritesContinuing();
            if(boolInput[4]){
                wonRound=false;
                betweenRound=true;
            }
        }else if(betweenRound){
            inBetweenRound();
            t1.setSpritesPlayerWon(winner);
            setSpritesContinuing();
            if(boolInput[4]){
                betweenRound=false;
                startOfRound=true;
                nextMove();
            }
        }else if(continuing){
            setSpritesContinuing();
            if(boolInput[4]){
                nextMove();
                continuing=false;
            }
        }else if(raising){
            setSpritesRaising();
            if(boolInput[4]){
                t1.raisePlayer(3, intInput);
                nextMove();
            }
        }else if(inputting){
            setSpritesInputing();//check which actions are possible
            if(boolInput[0]||boolInput[1]){
                check(3);
                nextMove();
            }else if(boolInput[2]){
                raising=true;
                inputting=false;
            }else if(boolInput[3]){
                fold(3);
                nextMove();
            }
        }else if(botting){
            String botInput = pokerAI.getInput(getStringInputs(playerMove));
            check(playerMove);
            continuing=true;
        }

        /*
        won=t1.checkWon();
        lost=t1.checkLost(3);
        */

    }

    public void fold(int playerNum){
        t1.foldPlayer(playerNum);
    }

    public void check(int playerNum){
        if(t1.playerCanCall(playerNum))
            t1.callPlayer(playerNum);
    }

    public void hideSprites(){
        for(Sprite s : sprites)
            s.setVisibility(false);
        for(Text t: texts)
            t.setVisibility(false);
    }

    public void setSpritesContinuing(){
        hideSprites();
        sprites[4].setVisibility(true);
    }

    public void setSpritesInputing(){
        boolean[] posInputs = getPossibleInputs(3);
        for(int i=0;i<4;i++)
            sprites[i].setVisibility(posInputs[i]);
        sprites[5].setVisibility(false);
        sprites[6].setVisibility(false);
        texts[0].setVisibility(false);
    }

    public void setSpritesRaising(){
        for(int i=0;i<4;i++){
            sprites[i].setVisibility(false);
        }
        sprites[5].setVisibility(true);
        //sprites[6].setVisibility(true);
        try{
            texts[0].setVisibility(false);
        }catch(NullPointerException e){

        }
        int[] point={1000, 990};
        DraggableButton d = (DraggableButton) sprites[5];
        int raise = (int)((d.getPercentAcross())*t1.playerMaxRaise(3));
        texts[0]=new Text("Raise: "+raise, layer, point);
        texts[0].setVisibility(true);
    }

    public boolean[] getPossibleInputs(int playerNum){
        boolean[] result = new boolean[4];
        for(boolean temp : result)
            temp=false;
        result[3]=true;
        if((t1.getPlayerBet(playerNum)!=t1.getHighestBet())&&t1.playerCanRaise(playerNum)){
            result[1]=true;
        }else{
            result[0]=true;
        }
        if(t1.playerCanRaise(playerNum)){
            result[2]=true;
        }
        return result;
    }

    public String[] getStringInputs(int playerNum){
        boolean[] inputs = getPossibleInputs(playerNum);
        int resultLength =0;
        for(boolean temp : inputs)
            if(temp)
                resultLength++;
        String[] result = new String[resultLength];
        int i=0;
        if(inputs[0]){
            result[i]="Check";
            i++;
        }
        if(inputs[1]){
            result[i]="Call";
            i++;
        }
        if(inputs[2]){
            result[i]="Raise";
            i++;
        }
        if(inputs[3]){
            result[i]="Fold";
            i++;
        }
        return result;
    }

    public void nextMove(){
        playerMove++;
        if(playerMove>=4){
            playerMove=0;
        }
        while(t1.playerHasFeld(playerMove)){
            playerMove++;
            if(playerMove>=4){
                playerMove=0;
            }
        }

        if(t1.getTurn()==0){
            startRound();
        }else if(playerMove==lastRaise){
            if(startOfRound){
                startOfRound=false;
            }else{
                incrTurn();
            }
        }

        won=false;
        lost=false;
        continuing=false;
        raising=false;
        inputting=false;
        botting=false;

        if(playerMove<3){
            botting=true;
        }else{
            inputting=true;
        }
    }

    public void incrTurn(){
        System.out.println("what");
        t1.incrTurn();
        if(t1.getTurn()==1)
            t1.takeCards(3);
        else if(t1.getTurn()>=4){
            endRound();
        }else{
            t1.takeCards(1);
        }
    }
    
    public void endRound(){
        winner = pokerHands.getWinner(t1);
        t1.givePotToPlayer(winner);
        wonRound=true;
        t1.setTurn(0);
    }

    public void inBetweenRound(){
        t1.incrRound();
        playerMove=-1;
        lastRaise=0;
        t1.returnCardsToDeck();
        t1.unfoldPlayers();
        t1.shuffleDeck();
    }

    public void startRound(){
        incrTurn();
        startOfRound=true;
        t1.givePlayersXCards(2);
        for(int i=0;i<4;i++){
            t1.betPlayer(i, 2);
        }
        nextMove();
    }
}