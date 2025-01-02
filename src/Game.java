public class Game {
    Player[] players;
    Grid grid;
    ConsoleInteractionUtils gameUI;

    //--------------------CONSTRUCTOR----------------------------

    Game(Player[] players, Grid grid, ConsoleInteractionUtils gameUI){
        this.players = players;
        this.grid = grid;
        this.gameUI=gameUI; 
    }

    //-------------------------METHODS-------------------------------

    int playTurn(Player p, int index) {
        int changeOrderIndex=index;
        grid.printHidden();

        if(p.stop==true){
            System.out.println("Skipped "+p.name+"'s turn for Stop Card previously drawn");
            System.out.println("\n----------------------------------------------------");
            p.stop=false; 
        }else{
            Coordinate c1=getCoordinateAndVerify();
            grid.cardsGrid[c1.row][c1.col].uncovered=true;
            System.out.println();
            grid.printHidden();
            if(grid.cardsGrid[c1.row][c1.col].cardType==CardType.MALUS){
                changeOrderIndex=doWhenMalus(grid.cardsGrid[c1.row][c1.col], p, index);             
            }else{
                Coordinate c2=getCoordinateAndVerify();
                grid.cardsGrid[c2.row][c2.col].uncovered=true;
                System.out.println();
                grid.printHidden();
                if(grid.cardsGrid[c2.row][c2.col].cardType==CardType.MALUS){
                    changeOrderIndex=doWhenMalus(grid.cardsGrid[c2.row][c2.col], p, index);             
                }else{
                    if(grid.cardsGrid[c1.row][c1.col].isSame(grid.cardsGrid[c2.row][c2.col])) {
                
                        //Check only on first card cause they're the same in content
                        if(grid.cardsGrid[c1.row][c1.col].cardType==CardType.BONUS){
                            doWhenBonus(grid.cardsGrid[c1.row][c1.col], p); 
                        }else{
                            System.out.println("You've earned 10 points for discovering a pair!");
                            p.addPoints();
                            System.out.println("Correct! Your points are now: "+p.points);     
                            grid.normals-=2;          
                        }
                        grid.cardsGrid[c1.row][c1.col].foundCard();
                        grid.cardsGrid[c2.row][c2.col].foundCard(); 
    
                    }else{
                        if(c1.isSame(c2)){
                            System.out.println("You've chosen the same card twice, you loose your turn!");
                            grid.cardsGrid[c1.row][c1.col].uncovered=false;
                        }else{
                            System.out.println("Cards don't match");
                            grid.cardsGrid[c1.row][c1.col].uncovered=false;
                            grid.cardsGrid[c2.row][c2.col].uncovered=false;
                        }
                    }                
                }
            }
        
        }
        System.out.println("Press enter to go to next turn");
        gameUI.scanner.nextLine();
        return changeOrderIndex;
    }

    /**
     * Method: getCoordinateAndVerify
     * Descrption: Check if coordinates inserted from the player are valid or not
     * @return
     */
    Coordinate getCoordinateAndVerify(){
        Coordinate c = null;
        String coords = "";
        int row = 0;
        int col = 0;
        boolean isValidInputs = false;

        
        // Check for coordinate
        do {
            System.out.println();
            System.out.print("Insert your coordinate (i.e: A2): ");
            coords = gameUI.scanner.nextLine();

            //Controllo se nel range delle dimensioni della Grid
            row = coords.toUpperCase().charAt(0) - 'A';
            col = Integer.parseInt(coords.substring(1))-1;
            
            // Check if coordinates are between grid dimension
            if(row >= grid.getGridHeight() || col >= grid.getGridWidth()){
                isValidInputs = false;
            }else{
                c = new Coordinate(row, col);
                //Check if it is an empty cell
                if(grid.getSymbolFromCoordinate(c) == ' '){
                    isValidInputs = false;
                }else{
                    isValidInputs = true;
                }
            }           

            if (!isValidInputs) {
                System.out.println("One or more constraints are not met.");
            }

        } while (!isValidInputs);

        return c;
    }
    
    //--------------------SPECIAL CARDS METHODS---------------------

    /**
     * Method: doWhenBonus
     * Description: Handles the case in which a pair of Bonus Cards were drawn
     * @param c picked Bonus card
     * @param p currently playing Player
     */
    void doWhenBonus(Card c, Player p){
        System.out.println("You've earned 10 points for discovering a pair!");
        p.addPoints();
        Bonus b = c.randomBonus();
        bonusOption(b, p);
        grid.specials-=2;
    }

    /**
     * Method: doWhenMalus
     * Description: Handles the case in which a pair of Bonus Cards were drawn
     * @param c picked Bonus card
     * @param p currently playing Player
     * @param index index of the player in the players array (in case the change order malus is picked)
     */
    int doWhenMalus(Card c, Player p, int index){

        
        //Malus m=grid.cardsGrid[c1.row][c1.col].randomMalus();
        Malus m=Malus.CHANGEORDER;
        boolean applyMalus=true;
        if(p.jolly==true){
            System.out.println("Do you want to use the Jolly?");
            String s;
            do{
                System.out.println("Insert yes or no");
                s=gameUI.readStringAndEnsureIsNotEmptyOrWhiteSpaces();
            }while(!s.equalsIgnoreCase("yes")&&!s.equalsIgnoreCase("no"));
            
            if(s.equalsIgnoreCase("yes")){
                p.removeJolly();
                applyMalus=false;
            }
        }
        if(applyMalus){
            malusOption(m, p, index);
            if(m==Malus.CHANGEORDER){
                return -1;
            }
        }                  
        c.foundCard();
        grid.specials--;            
        return index;
    }

    /**
     * Description: Method that given the bonus extracted randomly and the player, 
     *              applies the conditions specified by the bonus card
     * @param b
     * @param p
     */
    void bonusOption(Bonus b, Player p){
        System.out.println("Bonus card"+b+" was drawn");

        switch (b) {
            case ADD100:                   
                p.addBonus();
                System.out.println("Added 100 points! Your points are now: "+p.points);
            break;
            case X2:
                p.doublePoints();
                System.out.println("Your points have been doubled!");
            break;
            case SHOWGRID:
                System.out.println("You can see the uncovered table for 5 seconds!");
                grid.print();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("Exception");
                }
                gameUI.clearScreen();
                System.out.println("End of bonus");
            break;
            case JOLLY:
                p.hasJolly();
                System.out.println("You've earned a JOLLY");
            break;
            case BRINGTO0:
                System.out.println("You can choose a player who's going to loose all the points");
                Player p2=chooseGiocatore(p);
                System.out.println(p2.name+"'s points before: "+p2.points);
                p2.loseAll();
                System.out.println(p2.name+"'s points are now 0");
            break;
        }
    }
    
    /**
     * Description: Method that given the malus extracted randomly and the player, 
     *              applies the conditions specified by the malus card
     * @param m -> malus extracted randomly
     * @param p -> currently playing Player 
     * @param index -> index of the currently playing Player (used in method changeOrder)
     */
    void malusOption(Malus m, Player p, int index){
        System.out.println("Malus card "+m+" was drawn");
    
        switch (m) {
            case STOP:                     
                p.stopOnce();
                System.out.println("You'll skip next turns!");
            break;
            case LOSEALL:
                p.loseAll();
                System.out.println("You loose all your points!");
            break;
            case CHANGEORDER:
                changeOrder(index);
                System.out.println("Reversed turn order");
            break;
            case SUB100:
                p.subMalus();
                System.out.println("You loose 100 points");
            break;
            case DONATION:
                System.out.println("Choose a player to donate half of your points");
                Player p2=chooseGiocatore(p);
                int donation=p.subDonation();
                p2.addDonation(donation);
            break;
        }
    }

    /**
     * Method: chooseGiocatore
     * Description: Used in Bonus card "BRINGTO0" and in Malus card "DONATION". 
     *              Makes the user choose a player between the others
     * @return chosen Player
     */
    Player chooseGiocatore(Player p){
        String input;
        boolean found=false;

        System.out.println("Choose a Player:");
        for (Player player : players) {
            System.out.print("-"+player.printGameInfo());
        }

        do{
            input=gameUI.scanner.nextLine();
            for(int i=0;i<players.length;i++){
                if(players[i].name.equals(input)){
                    if(p.name.equalsIgnoreCase(input)){
                        System.out.println("YOU CAN'T CHOOSE YOURSELF!");
                    }else{
                        found=true;
                        return players[i];
                    }
                    
                }
            }
            System.out.println("Insert a valid name!");
        }while(found==false);
        
        return null;
    }
    
    /**
     * Method: changeOrder
     * Description: Method used to change the order of the players, when the malus card
     *              "change order" has been picked.
     * @param currentPlayer
     */
    void changeOrder(int currentPlayer){

        int newIndex = 0; // Index for the new array
        int n = this.players.length;
        Player[] temp = new Player[n];
        // Copy all the elements in inverted order starting from previous player
        for (int i = currentPlayer - 1; i >= 0; i--) {
            temp[newIndex++] = this.players[i];
        }
        for (int i = n - 1; i > currentPlayer; i--) {
            temp[newIndex++] = this.players[i];
        }
        // ADd currentPlayer at the bottom of the array
        temp[newIndex] = this.players[currentPlayer];
        // Copy the result in the original array
        for (int i = 0; i < n; i++) {
            this.players[i] = temp[i];
        }
        
    }

    //------------------PRINT METHODS------------------------

    void printGameResults() {
        // Riordino tutti i players dal punteggio maggiore al minore
        for (int i = 0; i < this.players.length - 1; i++) {
            for (int j = 0; j < this.players.length - 1 - i; j++) {
                if (this.players[j].points < this.players[j + 1].points) { // Decrescente
                    // Scambia i giocatori
                    Player temp = this.players[j];
                    this.players[j] = this.players[j + 1];
                    this.players[j + 1] = temp;
                }
            }
        }

        // Stampo la classifica
        for(int i = 0; i < this.players.length; i++){
            System.out.println((i+1) + players[i].printGameInfo());
        }

    }
}
