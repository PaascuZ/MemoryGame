public class Grid {

    Card[][] grid;
    int[] BonusMalus={0,0};//Contains how many bonus and malus cards are in the grid, index 0 -> Bonus, index 1 -> Malus
    int specials;//Contains number of special cards present in this grid

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
        this.grid=new Card[height][width];
        numberOfSpecialCards();
        calculateBonusMalus(this.specials);


    }

    /**
     * Method: numberOfSpecialCards
     * Description: algorithm that calculates how many special cards to insert in the Grid, based on width and lenght
     */
    void numberOfSpecialCards(){
        int cells=grid.length*grid[0].length, percentage=15;//Definire percentage come costante(?)

        if(cells%2==0){
            if((int)(cells/100*percentage)%2==0){
                specials= (int) cells/100*percentage;
            }else{
                specials= (int) cells/100*percentage-1;
            }
        }else{
            if((int)(cells/100*percentage)%2==0){
                specials= (int) cells/100*percentage+1;
            }else{
                specials= (int) cells/100*percentage;
            }
        }
    } 

    /**
     * Method: calculateBonusMalus
     * Description: algorithm that calculates how many bonus and malus cards to put inside the grid
     */
    void calculateBonusMalus(int specials){
        int temp=specials;

        if(temp==1){
            BonusMalus[1]+=1;
            return;
        }

        if(temp/2>0){
            BonusMalus[0]+=2;
            temp-=2;
            if(temp>0){
                BonusMalus[1]+=1;
                temp--;
                calculateBonusMalus(temp);
            }
        }
        return;
    }

    /**
     * Method: loadGrid
     * Description: method that loads the symbols inside the grid, starts by loading the specials first, and then the rest
     */

     
    
    void loadGrid(){
        //DA IMPLEMENTARE
    }

    void print() {
        // Print indices for columns
        System.out.print("    "); // initial space for row indices
        for (int i = 0; i < grid[0].length; i++) {
            System.out.printf(" %2d ", i);
        }
        System.out.println();

        // Print grid content
        for (int i = 0; i < grid.length; i++) {
            // Print top border for row
            System.out.print("    "); // initial space for row indices
            for (int j = 0; j < grid[i].length * 4 + 1; j++) {
                System.out.print("-");
            }
            System.out.println();

            // Print row index
            System.out.printf(" %2d |", i);

            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != null) {
                    grid[i][j].print();
                } else {
                    System.out.print("   ");
                }
                System.out.print("|");
            }
            System.out.println();
        }

        // Print bottom border for last row
        System.out.print("    "); // initial space for row indices
        for (int i = 0; i < grid[0].length * 4 + 1; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

}