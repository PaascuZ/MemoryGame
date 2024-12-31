/**
 * Description: Classe principale 
 * 
 * @author Pascal Galli
 * @author Claudia Grigorean
 * @version 30.12.2024
 */


class Constants {
    static final int MAX_NR_CELLS = 186;
    static final int MAX_NR_ROWS = 26;
}

public class MemoryTest {

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
        grid = new Grid(gDimension[0], gDimension[1]); 
        

        gameUI.clearScreen();
        grid.print();

        Game gamePlay = new Game(players, grid, gameUI);

        int i = 0; // Indice iniziale

        do {
            System.out.println(players[i].name + "'s turn. Play!");

            // Gioca il turno
            gamePlay.playTurn(players[i], i);

            // Controllo fine partita
            if (gamePlay.grid.emptyCells + gamePlay.grid.specials == 0) {
                System.out.println("Fine Gioco!");
                break;
            }

            // Controlla la direzione direttamente dalla classe Game
            if (gamePlay.isClockwise()) { // Metodo getter per la direzione
                i = (i + 1) % players.length; // Orario
            } else {
                i = (i - 1 + players.length) % players.length; // Antiorario
            }

        } while (gamePlay.grid.emptyCells + gamePlay.grid.specials != 0);

        // Ciclo while per la durata del gioco
        /*do {
            // For cycle to let all players play
            for(int i = 0; i < gamePlay.players.length; i++){
                System.out.println(gamePlay.players[i].name + "'s turn. Play!");
                i = gamePlay.playTurn(players[i], i);
                if(gamePlay.grid.emptyCells + gamePlay.grid.specials == 0){
                    System.out.println("Fine Gioco!");
                    break;
                }
            }
        }while(gamePlay.grid.emptyCells + gamePlay.grid.specials != 0);
        */

        //gamePlay.printGameResults();

        gameUI.closeScanner();
    }
}