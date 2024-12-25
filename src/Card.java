enum Color {
    //Takes the output back to original settings
    RESET("\033[0m"),

    BLACK("\033[1;30m"),   // BLACK
    RED("\033[41m"),     // RED
    GREEN("\033[42m"),   // GREEN
    YELLOW("\033[1;33m"),  // YELLOW
    BLUE("\033[1;34m"),    // BLUE
    MAGENTA("\033[1;35m"), // MAGENTA
    CYAN("\033[1;36m"),    // CYAN
    WHITE("\033[1;37m");   // WHITE

    String code="";

    Color(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

}

enum Bonus{
    //IMPLEMENTARE OPZIONI CARTE BONUS
}

enum Malus{
    //IMPLEMENTARE OPZIONI CARTE MALUS
}

enum CardType {
    NORMAL, BONUS, MALUS;
}

public class Card {
    char symbol;
    boolean uncovered;
    Coordinate position;
    CardType cardType;

    Card(char symbol, boolean uncovered, Coordinate position, CardType cardType){
        this.symbol = symbol;
        this.uncovered = uncovered;
        this.position = position;
        this.cardType = cardType;
    }



    // ---------- METHODS ----------
    void print(){
        if(this.cardType.equals(CardType.BONUS)){
            System.out.print(Color.GREEN);//Changes color of the output
            System.out.print(" "+this.symbol+" ");
            System.out.print(Color.RESET);//Resets to original settings
        }
        if(this.cardType.equals(CardType.MALUS)){
            System.out.print(Color.RED);//Changes color of the output
            System.out.print(" "+this.symbol+" ");
            System.out.print(Color.RESET);//Resets to original settings
        }
        if(this.cardType.equals(CardType.NORMAL)){
            System.out.print(" "+this.symbol+" ");
        }
        
    }
}