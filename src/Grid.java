public class Grid {
    private static final double PERCENTAGE = 15;
    private static final int STARTING_CHAR = 34;
    private static final int STARTING_COLOR = 3;
    private static final int ENDING_COLOR = 9;

    Card[][] cardsGrid;
    //Contains how many bonus and malus cards are in the grid, index 0 -> Bonus, index 1 -> Malus
    int[] BonusMalus={0,0};
    //Contains number of special cards present in this grid
    int specials;
    //Contains number of normal cards present in this grid
    int normals;

    //------------------CONSTRUCTORS----------------------

    Grid(int height, int width) {
        /*
         * Printable unicode characters are 95.
         * We use:
         * - the space (decimal 32) to indicate empty cell,
         * - '!' (decimal 33) the back of a card.
         * This leaves 93 characters available.
         * As each character is used for two cards, we have 186 possible cards.
         * Sqrt(186) = 13.64, so maximum square grid is 13 x 13.
         */
        if (height <= 0 || width <= 0 || height * width > Constants.MAX_NR_CELLS) {
            height = 13;
            width = 13;
        }
        this.cardsGrid=new Card[height][width];
        this.normals=(height*width);
        
        numberOfSpecialCards();
        calculateBonusMalus(this.specials);
        loadSpecials();
        loadGrid();   
    }

    //------------------METHODS-----------------------

    /**
     * Method: numberOfSpecialCards
     * Description: algorithm that calculates how many special cards to insert in the Grid, based on width and lenght.
     */
    void numberOfSpecialCards(){
        double cells=cardsGrid.length*cardsGrid[0].length;

        if(cells%2==0){//checks if the total number of cells is even

            if((int)(cells/100*PERCENTAGE)%2==0){//calculates if 15% (casted as int) of the total number of cells is an even number
                this.specials=(int) ((cells/100)*PERCENTAGE);//if it's even assignes to specials
            }else{
                this.specials=(int) ((cells/100)*PERCENTAGE)-1;//if it's not even, subtracts 1 and then assignes to specials
            }
            
        }else{//if the total number of cells isn't even

            if((int)(cells/100*PERCENTAGE)%2==0){//calculates if 15% (casted as int) of the total number of cells is an even number
                this.specials=(int) ((cells/100)*PERCENTAGE)+1;//if it's even adds 1 and then assignes to specials
            }else{
                this.specials=(int) ((cells/100)*PERCENTAGE);//if it's not even assignes to specials
            }

        }
        //In this way we're always left with an even number of remaining cells
    } 

    /**
     * Method: calculateBonusMalus
     * Description: algorithm that calculates how many bonus and malus cards to put inside the grid, 
     * based on the total number of special cards previously calculated. 
     * Malus cards come 1 by 1, Bonus cards come in pair; 
     * The algorithm will always try to put 2 bonus cards first, followed by a Malus card in a recursive way
     */
    void calculateBonusMalus(int specials){
        int temp=specials;

        if( temp == 1 ){//exiting condition: if number of specials equals to 1 we can only put a Malus card inside
            BonusMalus[1]+=1;
            return;
        }

        if( (temp/2) > 0){//If this condition is satisfied it means there's room for 2 bonus cards

            BonusMalus[0]+=2;
            temp-=2;

            if(temp>0){//If this condition is satisfied it means there's room for 1 malus card
                BonusMalus[1]+=1;
                temp--;
                calculateBonusMalus(temp);
            }
        }
        return;
    }

    /**
     * Method: loadSpecials
     * Description: method that loads the special cards inside the grid 
     */
    void loadSpecials(){
        Coordinate c;

        int start=STARTING_CHAR;
        for(int j=0;j<BonusMalus[0];j++){
            if( (j%2) == 0 && j != 0){//Changes character only after putting two of the same card inside the grid
                start++;
            }
            c=randomPosition();//checks for an empty position in the Grid
            cardsGrid[c.row][c.col] = new Card((char)start, c, CardType.BONUS,"GREEN");//Creates a card to insert inside the given coordinates 
            this.normals--;//diminuishes the remaining number of empty cells
            
        }
        
        start=STARTING_CHAR;//restarts from first character

        //does same thing as above just for 1 card
        for(int j=0;j<BonusMalus[1];j++){
            c=randomPosition();
            cardsGrid[c.row][c.col]= new Card((char)start, c, CardType.MALUS, "RED");
            start++;
            this.normals--;

        }

    }

    /**
     * Method: loadGrid
     * Description: method that loads the remaining cards
     */
    void loadGrid(){
        Coordinate c=new Coordinate();
        int start=STARTING_CHAR,temp=normals,j=0;

        while(j<temp){//while there are still empty cells

            int i=STARTING_COLOR;//starts the index from the third position of the Color enum in Class Card
            do{
                
                c=randomPosition();//checks for an empty position in the grid 
                cardsGrid[c.row][c.col]= new Card((char)start, c, CardType.NORMAL,i);
                j++;//loaded cells
                if( (j%2==0) && (j!=0) ){//changes color only after it has loaded two of the same card inside the grid
                    i++;    
                }
            }while(j<temp && i<=ENDING_COLOR);
            start++;//changes character
        }
    }

    
    /**
     * Method: randomPosition
     * Description: creates a Coordinate object so it can give a random position
     * where to put the card
     * @return Coordinate 
     */
    Coordinate randomPosition(){
        Coordinate c;
        do{
            c= new Coordinate((int) (Math.random()*this.cardsGrid.length),(int) (Math.random()*this.cardsGrid[0].length));           
        }while(cardsGrid[c.row][c.col]!=null);

        return c;
    }
  
    char getSymbolFromCoordinate(Coordinate c){
        Card card = cardsGrid[c.row][c.col]; // Ottieni la carta dalla matrice
        if (card != null) { // Controlla se esiste una carta in quella posizione
            return card.symbol; // Ritorna il simbolo
        }else{
            return '?';
        }
    }
    
    //-------------------PRINT METHODS---------------------------

    /**
     * Method: print
     * Description: prints the grid showing all the cards uncovered
     */
    void print() {
        // Print indices for columns
        System.out.print("    "); // initial space for row indices
        for (int i = 0; i < cardsGrid[0].length; i++) {
            System.out.printf(" %2d ", (i+1));
        }
        System.out.println();

        // Print grid content
        for (int i = 0; i < cardsGrid.length; i++) {
            // Print top border for row
            System.out.print("    "); // initial space for row indices
            for (int j = 0; j < cardsGrid[i].length * 4 + 1; j++) {
                System.out.print("-");
            }
            System.out.println();

            // Print row index
            System.out.print(" " + ((char)(i+65)) + " |");

            for (int j = 0; j < cardsGrid[i].length; j++) {
                if (cardsGrid[i][j] != null) {
                    cardsGrid[i][j].print();
                } else {
                    System.out.print("   ");
                }
                System.out.print("|");
            }
            System.out.println();
        }

        // Print bottom border for last row
        System.out.print("    "); // initial space for row indices
        for (int i = 0; i < cardsGrid[0].length * 4 + 1; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Method: printHidden
     * Description: prints the grid hiding the covered cards
     */
    void printHidden() {
        // Print indices for columns
        System.out.print("    "); // initial space for row indices
        for (int i = 0; i < cardsGrid[0].length; i++) {
            System.out.printf(" %2d ", (i+1));
        }
        System.out.println();

        // Print grid content
        for (int i = 0; i < cardsGrid.length; i++) {
            // Print top border for row
            System.out.print("    "); // initial space for row indices
            for (int j = 0; j < cardsGrid[i].length * 4 + 1; j++) {
                System.out.print("-");
            }
            System.out.println();

            // Print row index
            System.out.print(" " + ((char)(i+65)) + " |");

            for (int j = 0; j < cardsGrid[i].length; j++) {
                if(cardsGrid[i][j].uncovered){
                    cardsGrid[i][j].print();
                }else{
                    System.out.print(" ! ");
                }
                System.out.print("|");
            }
            System.out.println();
        }

        // Print bottom border for last row
        System.out.print("    "); // initial space for row indices
        for (int i = 0; i < cardsGrid[0].length * 4 + 1; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

}
