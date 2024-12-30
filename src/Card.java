enum Color {
    //Patters for BONUS/MALUS Cards and to reset output
    RESET("\033[0m"),   //Takes the output back to original settings
    RED("\033[41m"),     // RED BACKGROUND
    GREEN("\033[42m"),   // GREEN BACKGROUND

    //Usable patterns for non special cards start from here (index=3)
    //UNDERLINED
    BLACK_U("\033[4;30m"),     // BLACK
    RED_U("\033[4;31m"),       // RED
    GREEN_U("\033[4;32m"),     // GREEN
    YELLOW_U("\033[4;33m"),    // YELLOW
    BLUE_U("\033[4;34m"),      // BLUE
    MAGENTA_U("\033[4;35m"),   // MAGENTA
    CYAN_U("\033[4;36m"),      // CYAN
    WHITE_U("\033[4;37m"),     // WHITE

    //HIGH INTENSITY
    H_BLACK("\033[1;30m"),   // BLACK
    H_RED("\033[1;31m"),     // RED
    H_GREEN("\033[1;32m"),   // GREEN
    H_YELLOW("\033[1;33m"),  // YELLOW
    H_BLUE("\033[1;34m"),    // BLUE
    H_MAGENTA("\033[1;35m"), // MAGENTA
    H_CYAN("\033[1;36m"),    // CYAN
    H_WHITE("\033[1;37m");   // WHITE

    String code="";

    Color(String code) {
        this.code = code;
    }

    public String toString() {
        return code;
    }

}

enum Bonus{
    ADD100,X2,SHOWGRID,JOLLY,BRINGTO0
}

enum Malus{
    STOP,LOSEALL,CHANGEORDER,SUB100,DONATION
}

enum CardType {
    NORMAL, BONUS, MALUS;
}

public class Card {
    char symbol;
    boolean uncovered;
    Coordinate position;
    CardType cardType;
    Color color;
    boolean Bonus;
    boolean Malus;

    //Constructor used to create Bonus/Malus Cards
    Card(char symbol, boolean uncovered, Coordinate position, CardType cardType, String s){
        this.symbol = symbol;
        this.uncovered = uncovered;
        this.position = position;
        this.cardType = cardType;
        this.color=Color.valueOf(s);
    }

    //Constructor used to create all other cards
    Card(char symbol, boolean uncovered, Coordinate position, CardType cardType, int i){
        this.symbol = symbol;
        this.uncovered = uncovered;
        this.position = position;
        this.cardType = cardType;
        this.color=Color.values()[i];
    }

    // ---------- METHODS ----------
    
    //returnBonus

    //returnMalus


    void print(){
        System.out.print(color.toString());
        System.out.print(" "+this.symbol+" ");
        System.out.print(Color.RESET);
    }
}