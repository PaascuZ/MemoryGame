/**
 * Description: 
 * 
 * @author Pascal Galli
 * @author Claudia Grigorean
 * @version 13.12.2024
 */

import java.util.Scanner;
import java.io.IOException;


class Constants {
    static final int MAX_NR_CELLS = 186;
}

public class MemoryTest {
    /**
     * Method: printMenu
     * Description: Prints the initial menu
     */
    public static void printMenu(){
        System.out.println();
        System.out.println();

        System.out.println("    -----------------   ");
        System.out.println("    |  MEMORY GAME  |   ");
        System.out.println("    -----------------   ");
        System.out.println();
        System.out.println("  Press any key to begin");

        System.out.println();
        System.out.println();
    }

    /**
     * Method: clearScreen
     * Description: Using ProcessBuilder to clear the terminal during the game
     */
    public static void clearScreen(){
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method: checkInterval
     * Description: See if the value inserted is in the interval marked by a min and a max value,
     *              if it is, return true. If not, return false.
     * @param value
     * @param min
     * @param max
     * @return
     */
    public static boolean checkInterval(int value, int min, int max){
        if(value >= min && value <= max){
            return true;
        }else{
            return false;
        }
    }

    public static boolean checkIfIsIntegerAndValidInterval(Scanner input, int min, int max){
        boolean isInputValid = false;
        int value = 0;
        if(input.hasNextInt()){
            value = input.nextInt();
            if(checkInterval(value, min, max)){
                clearScreen();
                return true;
            }else{
                System.out.println("Value out of the interval. Retry");
                return false;
            }
        }else{
            System.out.println("Input not valid. Retry");
            return false;
        }
    }

    public static String checkIfIsStringAndValidInput(String string){
        return "uetti";
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Vars
        int numPlayers = 0; // Number of players
        String playerName = "";
        int numRows = 0; // Number of grid rows 
        int numCols = 0; // Number of grid columns


        // -------------------------------
        // ---------- GAME FLOW ----------
        // -------------------------------

        // Print the menu to begin
        printMenu();
        input.nextLine(); // Once any key is pressed, the game begins
        clearScreen();

        System.out.println();
        System.out.println();
        System.out.print("How many players are playing? (2-6)");
        // Controlla se l'input é corretto: DA FARE
        ConsoleInteractionUtils ui= new ConsoleInteractionUtils();
        ui.readIntegerInRange(2,6);
        
        // Array created to store all the players
        Player[] players = new Player[numPlayers];

        clearScreen();

        System.out.println();
        System.out.println();
        for(int i = 0; i < players.length; i++){
            System.out.println("Player " + i + " name: ");
            // Inserire controllo per il nome
        }


        input.close();
        /*
         * Cliccare invio per iniziare:             FATTO
         * 1. Chiedere il numero di giocatori:      ON GOING
         * 2. Chiedere i nomi dei giocatori:        ON GOING
         * 3. Chiedere le dimensioni della griglia: DA INIZIARE
         */
    }
}