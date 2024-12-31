public class Game {
    Player[] players;
    Grid grid;
    ConsoleInteractionUtils gameUI;

    Game(Player[] players, Grid grid, ConsoleInteractionUtils gameUI){
        this.players = players;
        this.grid = grid;
        this.gameUI=gameUI;
        
    }

    /**
     * Name: changeOrder
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

    Coordinate getCoordinateAndVerify(){
        Coordinate c;
        String coords = "";
        int row = 0;
        int col = 0;
        boolean isValidInputs = false;

        grid.printHidden();
        // Check for coordinate
        do {
            System.out.print("Insert your coordinate: ");
            coords = gameUI.scanner.nextLine();

            //Controllo se nel range delle dimensioni della Grid
            row = coords.toUpperCase().charAt(0) - 'A';
            col = Integer.parseInt(coords.substring(1))-1;
            
            //System.out.println("Coordinate: "+row+col);
            c = new Coordinate(row, col);

            //Check if it is a empty cell
            if(grid.getSymbolFromCoordinate(c) == ' '){
                isValidInputs = false;
            }else{
                isValidInputs = true;
            }

            if (!isValidInputs) {
                System.out.println("One or more constraints are not met.");
            }

        } while (!isValidInputs);

        return c;
    }

    Player chooseGiocatore(ConsoleInteractionUtils gameUI){
        String input;
        boolean found=false;

        System.out.println("Scegli un giocatore:");
        for (Player player : players) {
            player.printGameInfo();
        }

        do{
            input=gameUI.scanner.nextLine();
            for(int i=0;i<players.length;i++){
                if(players[i].name==input){
                    found=true;
                    return players[i];
                }
            }
            System.out.println("Inserisci un nome valido");
        }while(found==false);
        
        return null;
    }
    
    void playTurn(Player p, int index) {

        Coordinate c1=getCoordinateAndVerify();

        if(grid.cardsGrid[c1.row][c1.col].cardType==CardType.MALUS){
            Malus m=grid.cardsGrid[c1.row][c1.col].randomMalus();
            malusOption(m, p, index);
            grid.cardsGrid[c1.row][c1.col].foundCard();
        }else{
            grid.cardsGrid[c1.row][c1.col].uncovered=true;
            Coordinate c2=getCoordinateAndVerify();
            grid.cardsGrid[c2.row][c2.col].uncovered=true;
            grid.printHidden();
            
            if(grid.cardsGrid[c1.row][c1.col].isSame(grid.cardsGrid[c2.row][c2.col])) {
            
                //Controllo soltanto la prima carta perchè so che sono uguali
                if(grid.cardsGrid[c1.row][c1.col].cardType==CardType.BONUS){
                    Bonus b = grid.cardsGrid[c1.row][c1.col].randomBonus();
                    bonusOption(b, p);
                }else{
                    p.addPoints();
                    System.out.println("Hai indovinato! Punteggio: "+p.points);   
                    grid.cardsGrid[c1.row][c1.col].foundCard();
                    grid.cardsGrid[c2.row][c2.col].foundCard();                 
                }
            }else{
                if(c1.isSame(c2)){
                    System.out.println("Hai scelto due volte la stessa carta, perdi il turno!");
                    grid.cardsGrid[c1.row][c1.col].uncovered=false;
                }else{
                    System.out.println("Carte non uguali");
                    grid.cardsGrid[c1.row][c1.col].uncovered=false;
                    grid.cardsGrid[c2.row][c2.col].uncovered=false;
                }
            }

        }

    }

    void bonusOption(Bonus b, Player p){
        System.out.println(b);
        switch (b) {
            case ADD100:                     
                p.addBonus();
                System.out.println("Aggiunti 100 punti!");
                break;
            case X2:
                p.doublePoints();
                System.out.println("Punti raddoppiati!");
                break;
            case SHOWGRID:
                System.out.println("Visualizza tabella per 5 secondi!");
                grid.print();
                break;
            case JOLLY:
                System.out.println("Hai ottenuto un JOLLY");
                p.hasJolly();
                break;
            case BRINGTO0:
                System.out.println("Scegli un giocatore a cui togliere i punti");
                Player p2=chooseGiocatore(gameUI);
                p2.loseAll();
                break;
        }
    }

    void malusOption(Malus m, Player p, int index){
        System.out.println(m);
            switch (m) {
                case STOP:                     
                    p.stopOnce();
                    System.out.println("Fermo un turno!");
                    break;
                case LOSEALL:
                    p.loseAll();
                    System.out.println("Perdi tutti punti!");
                    break;
                case CHANGEORDER:
                    changeOrder(index);
                    System.out.println("Ordine di gioco invertito");
                    break;
                case SUB100:
                    System.out.println("Perdi 100 punti");
                    p.subMalus();
                    break;
                case DONATION:
                    System.out.println("Scegli un giocatore a cui donare metà dei tuoi punti");
                    Player p2=chooseGiocatore(gameUI);
                    int donation=p.subDonation();
                    p2.addDonation(donation);
            }
    }
    /**
     * Name: getNumberOfEmptyCells()
     * Description: this method return the number of empty cells. So the game have a
     *              constant reminder of how many cells are empty or to be discovered
     * @return
     */
    int getNumberOfEmptyCells(){
        return this.grid.emptyCells;
    }

    /*
     * void printGameResults() {}
     */
}

/*
0 1 2 3 4 5

Posizione 3 prende carta "cambia giro" 

2 1 0 5 4 3
*/