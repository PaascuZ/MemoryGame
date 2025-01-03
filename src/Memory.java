/**
 * Description: Classe principale 
 * 
 * @author Pascal Galli
 * @author Claudia Grigorean
 * @version 02.01.2025
 */


class Constants {
    static final int MAX_NR_CELLS = 186;
    static final int MAX_NR_ROWS = 26;
}

public class Memory {

    public static void main(String[] args) {
        // Objects init
        ConsoleInteractionUtils gameUI = new ConsoleInteractionUtils();
        Player[] players;
        Grid grid;
        // vArs
        int[] gDimension = new int[2];

        // -------------------------------
        // ---------- GAME FLOW ----------
        // -------------------------------

        // Clear the screen to begin
        gameUI.clearScreen();
        // Print the menu to begin
        gameUI.printMenu();
        gameUI.scanner.nextLine(); // Once any key is pressed, the game begins
        gameUI.clearScreen();
        
        // Ask for players
        players = gameUI.getNumberAndNamesPlayers();

        gameUI.clearScreen();

        //Ask for grid dimension
        gDimension = gameUI.getGridDimensions();

        gameUI.clearScreen();
        
        grid = new Grid(gDimension[0], gDimension[1]); 

        Game gamePlay = new Game(players, grid, gameUI);  
        
        

        // Ciclo while per la durata del gioco
        do {
            // For cycle to let all players play
            for(int i = 0; i < gamePlay.players.length; i++){
                System.out.println(gamePlay.players[i].name + "'s turn. Play!");
                System.out.println("\n");
                i = gamePlay.playTurn(players[i], i);
                gameUI.clearScreen();
                if(gamePlay.grid.normals + gamePlay.grid.specials == 0){
                    System.out.println("Game Over!");
                    break;
                }
                
            }
        }while(gamePlay.grid.normals + gamePlay.grid.specials != 0);
        
        // Print game results
        gamePlay.printGameResults();
        
        gameUI.closeScanner();
    }
}