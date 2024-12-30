public class Game {
    Player[] players;
    Grid grid;

    Game(Player[] players, Grid grid){
        this.players = players;
        this.grid = grid;
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