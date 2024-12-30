public class Game {
    Player[] players;
    Grid grid;

    Game(Player[] players, Grid grid){
        this.players = players;
        this.grid = grid;
    }

    void changeOrder(int i){
        //DA IMPLEMENTARE

    }

    /*if carta estratta==Bonus
    int index=grid[i][j].returnBonus
    switch () {
        ADD100: players[i].addPoints();
        X2: players[i].doublePoints();
        SHOWGRID: grid.print();
        JOLLY: players[i].hasJolly();
               players[i].addGuessed();
        BRINGTO0:
        
    }
    */


    /*if carta estratta==Malus
    grid[i][j].returnMalus
    switch () {
        STOP: players[i].stopOnce();
        LOSEALL: players[i].loseAll();
        CHANGEORDER: changeOrder();
        SUB100: player[i].subPoints();
        DONATION: 
        
    }
    */

    
    //SERVE SCANNER 
    int returnPlayer(Player p){
        //Chiedi nome del giocatore 
        int index=-1;

        return index;

    }

    

}
