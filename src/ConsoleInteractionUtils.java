import java.io.IOException;
import java.util.Scanner;
import java.lang.Math;

public class ConsoleInteractionUtils {
    Scanner scanner = new Scanner(System.in);

    int readIntegerInRange(int min, int max) {
        int input = 0;
        boolean correctInput = false;
        while (!correctInput) {
            System.out.println("Please enter a number between " + min + " and " + max + ": ");
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

    int[] getGridDimensions() {
        int height = 0;
        int width = 0;
        boolean isValidInputs = false;
        int maxWidth = 0;

        String message = """
                Insert the height of the grid such that:
                > Height: [0, %d]
                """.formatted(Constants.MAX_NR_CELLS);

        System.out.println(message);
        // Check for height
        do {
            System.out.println("Insert height");
            height = readIntegerInRange(1, Constants.MAX_NR_ROWS);

            // Get the max width of the grid
            maxWidth = getMaxGridWidth(height);

            //modificare il isValidInputs
            isValidInputs = isValidGridDimensions(height, maxWidth);

            if (!isValidInputs) {
                System.out.println("One or more constraints are not met.");
            }

        } while (!isValidInputs);

        // Check for width
        do {
            System.out.println("Insert width: ");
            width = readIntegerInRange(1, maxWidth);

            isValidInputs = isValidGridDimensions(height, width);

            if (!isValidInputs) {
                System.out.println("One or more constraints are not met.");
            }

        } while (!isValidInputs);
        return new int[] { height, width };
    }

    Coordinate getCoordinate(int gridHeight, int gridWidth) {
        String message = """
                Insert row and column of the coordinate such that:
                > Row: [0, %d]
                > Col: [0, %d]
                """.formatted(gridHeight, gridWidth);

        System.out.println(message);
        System.out.println("Insert row: ");
        int row = readIntegerInRange(0, gridHeight - 1);

        System.out.println("Insert column: ");
        int col = readIntegerInRange(0, gridWidth - 1);

        return new Coordinate(row, col);
    }

    Player[] getNumberAndNamesPlayers(){
        int numPlayers = 0;
        // Insert number of players
        System.out.println("How many players are playing?");
        numPlayers = readIntegerInRange(2,6);

        clearScreen();

        // Asking players name
        String playerName = "";
        Player[] players = new Player[numPlayers];

        for(int i = 0; i < numPlayers; i++){
            System.out.println("Player " + (i+1) + " name");
            playerName = readStringAndEnsureIsNotEmptyOrWhiteSpaces();
            players[i].name = playerName;
        }

        return players;
    }

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