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

        gameUI.closeScanner();
    }
}