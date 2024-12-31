enum Color {
    //Patters for BONUS/MALUS Cards and to reset output
    RESET("\033[0m"),   //Takes the output back to original settings
    RED("\033[41m"),     // RED
    GREEN("\033[42m"),   // GREEN

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

    @Override
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
    boolean found;
    Coordinate position;
    CardType cardType;
    Color color;

    //Constructor used to create Bonus/Malus Cards
    Card(char symbol, Coordinate position, CardType cardType, String s){
        this.symbol = symbol;
        this.uncovered = false;
        this.position = position;
        this.cardType = cardType;
        this.color=Color.valueOf(s);
    }

    //Constructor used to create all other cards
    Card(char symbol, Coordinate position, CardType cardType, int i){
        this.symbol = symbol;
        this.position = position;
        this.cardType = cardType;
        this.color=Color.values()[i];
    }

    // ---------- METHODS ----------
    
    void print(){
        System.out.print(color.toString());
        System.out.print(" "+this.symbol+" ");
        System.out.print(Color.RESET);
    }

    boolean isSame(Card c){
        if(this.symbol==c.symbol && this.cardType==c.cardType && this.color==c.color && this.position.isSame(c.position)){
            return true;
        }else{
            return false;
        }
    }

    //DEFINIRE COSTANTI
    Bonus randomBonus(){
        int i=(int) (Math.random()*5);
        return Bonus.values()[i];
    }

    //DEFINIRE COSTANTI
    Malus randomMalus(){
        int i=(int) (Math.random()*5);
        return Malus.values()[i];
    }

    void foundCard(){
        this.symbol=' ';
        this.color=Color.RESET;
        this.uncovered=true;
    }
    
    void printInfo(){
        System.out.println("Symbol: " + this.symbol);
        System.out.println("Status: " + (this.uncovered ? "Uncovered" : "Covered"));
        System.out.println("Coordinates:");
        System.out.println(" > Col: " + this.position.col);
        System.out.println(" > Row: " + this.position.row);
        System.out.println("Type:   " + this.cardType);
        System.out.println("Color:  " + this.color);
    }
}