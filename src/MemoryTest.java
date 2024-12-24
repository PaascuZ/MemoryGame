/**
 * Description: Classe principale 
 * 
 * @author Pascal Galli
 * @author Claudia Grigorean
 * @version 13.12.2024
 */

import java.util.Scanner;


class Constants {
    static final int MAX_NR_CELLS = 186;
    static final int MAX_NR_ROWS = 26;
}

public class MemoryTest {

    public static void main(String[] args) {
        // Objects init
        ConsoleInteractionUtils gameUI = new ConsoleInteractionUtils();
        // Oggetto game 
        Player[] players;


        // -------------------------------
        // ---------- GAME FLOW ----------
        // -------------------------------

        // Print the menu to begin
        gameUI.printMenu();
        gameUI.scanner.nextLine(); // Once any key is pressed, the game begins
        gameUI.clearScreen();
        
        players = gameUI.getNumberAndNamesPlayers();

        gameUI.closeScanner();
        /*
         * Cliccare invio per iniziare:             FATTO
         * 1. Chiedere il numero di giocatori:      FATTO
         * 2. Chiedere i nomi dei giocatori:        ON GOING
         * 3. Chiedere le dimensioni della griglia: DA INIZIARE
         */
    }
}