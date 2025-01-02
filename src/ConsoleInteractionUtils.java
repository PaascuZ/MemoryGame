import java.io.IOException;
import java.util.Scanner;
import java.lang.Math;

public class ConsoleInteractionUtils {
    Scanner scanner = new Scanner(System.in);

    /**
     * Method: readIntegerInRange
     * Description: used to check if an input is valid and between a certain interval
     * @param min
     * @param max
     * @return
     */
    int readIntegerInRange(int min, int max) {
        int input = 0;
        boolean correctInput = false;
        while (!correctInput) {
            System.out.print("Please enter a number between " + min + " and " + max + ": ");
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input < min || input > max)
                    System.out.println("Error: number not in range.");
                else
                    correctInput = true;
            } else {
                System.out.println("Error: input is not a number.");
                emptyTheScanner();
            }
        }
        emptyTheScanner();
        return input;
    }

    /**
     * Method: readStringAndEnsureIsNotEmptyOrWhiteSpaces
     * Descrption: Used to check if a string is valid and not empty
     * @return
     */
    String readStringAndEnsureIsNotEmptyOrWhiteSpaces() {
        String input = "";
        boolean correctInput = false;
        while (!correctInput) {
            System.out.print("Please enter a string: ");
            input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("Error: string is empty or contains only white spaces.");
            } else {
                correctInput = true;
            }
        }
        return input;
    }

    int getMaxGridWidth(int height){
        return (int)(Math.floor(Constants.MAX_NR_CELLS / height));
    }

    boolean isValidGridDimensions(int height, int width) {
        //return height > 0 && width > 0 && height * width <= Constants.MAX_NR_CELLS && height * width % 2 == 0;
        return height > 0 && width > 0 && height * width <= Constants.MAX_NR_CELLS && !(height == 1 && width == 1);
    }

    /**
     * Method: getGridDimensions
     * Description: Used to get the dimension of the game grid, checkin input valuse
     *              and returning an array of integers
     * @return
     */
    int[] getGridDimensions() {
        int height = 0;
        int width = 0;
        boolean isValidInputs = false;
        int maxWidth = 0;
        
        // Check for height
        do {
            System.out.println("INSERT HEIGHT");
            height = readIntegerInRange(1, Constants.MAX_NR_ROWS);

            // Get the max width of the grid
            maxWidth = getMaxGridWidth(height);

            //modificare il isValidInputs
            isValidInputs = isValidGridDimensions(height, maxWidth);

            if (!isValidInputs) {
                System.out.println("One or more constraints are not met.");
            }

        } while (!isValidInputs);

        // Clear screen
        clearScreen();

        // Check for width
        do {
            System.out.println("INSERT WIDTH");
            width = readIntegerInRange(1, maxWidth);

            //modificare il isValidInputs
            isValidInputs = isValidGridDimensions(height, width);

            if (!isValidInputs) {
                System.out.println("One or more constraints are not met.");
            }

        } while (!isValidInputs);
        return new int[] { height, width };
    }

    /**
     * Method: getNumberAndNamesPlayers
     * Descrption: This method is used to initialize the array of players and their names
     * @return
     */
    Player[] getNumberAndNamesPlayers(){
        int numPlayers = 0;
        // Insert number of players
        System.out.println("How many players are playing?");
        numPlayers = readIntegerInRange(2,6);

        clearScreen();

        // Asking players name
        Player[] players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(""); // new Player initialized
            String playerName;
            boolean isDuplicate;
    
            do {
                printUpperSpace();
                System.out.println("PLAYER " + (i + 1) + " NAME");
                playerName = readStringAndEnsureIsNotEmptyOrWhiteSpaces();
    
                // Check for duplicates
                isDuplicate = false;
                for (int j = 0; j < i; j++) {
                    if (playerName.equals(players[j].name)) {
                        System.out.println("This name already exists. Choose another name.");
                        isDuplicate = true; // Flag the name as duplicate
                        break; // Exit the inner loop
                    }
                }
            } while (isDuplicate); // Repeat until name is unique
    
            players[i].name = playerName; // Assign the valid name
        }

        return players;
    }

    void printMenu(){
        String[] art = {
            "     __  ___                       ",
            "    /  |/  /__ __ _  ___  ______ __",
            "   / /|_/ / -_)  ' \\/ _ \\/ __/ // /",
            "  /_/  /_/\\__/_/_/_/\\___/_/  \\_, / ",
            "                            /___/ "
        };

        // Print the menu
        System.out.println("\n\n");
        // Print the ASCII
        for (String line : art) {
            System.out.println(line);
        }
        System.out.println("\n\n");
        System.out.println("     Press ENTER key to begin");
        System.out.println("\n\n");
    }

    /**
     * Method: clearScreen
     * Description: Using ProcessBuilder to clear the terminal during the game
     */
    void clearScreen(){
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

    void printUpperSpace() {
        System.out.println();
        System.out.println();
    }

    void closeScanner() {
        scanner.close();
    }

    void emptyTheScanner() {
        scanner.nextLine();
    }
}