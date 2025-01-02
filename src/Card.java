enum Color {
    //Patters for BONUS/MALUS Cards and to reset output
    RESET("\033[0m"),   //Takes the output back to original settings
    RED("\033[41m"),     // RED
    GREEN("\033[42m"),   // GREEN

    //Usable patterns for non special cards start from here (index=3)
    //UNDERLINED
    RED_U("\033[4;31m"),       // RED
    GREEN_U("\033[4;32m"),     // GREEN
    YELLOW_U("\033[4;33m"),    // YELLOW
    BLUE_U("\033[4;34m"),      // BLUE
    MAGENTA_U("\033[4;35m"),   // MAGENTA
    CYAN_U("\033[4;36m"),      // CYAN
    WHITE_U("\033[4;37m");     // WHITE

    /*Index of starting color and ending color are specified as constants in class Grid
     *if any further changes are made, modify those constants too
    */

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
    private static final double MAX_SPECIALS = 5;

    char symbol;
    Color color;
    boolean uncovered;
    Coordinate position;
    CardType cardType;
    
    
    // ----------------CONSTRUCTORS-----------------

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

    // ----------------METHODS-----------------

    /**
     * Method:isSame
     * Description: checks if two Cards have the same content but have different positions in the grid 
     * (so that the user can't pick the same card twice)
     * @param c
     * @return true if same in content but in different positions
     */
    boolean isSame(Card c){
        if(this.symbol==c.symbol && this.cardType.equals(c.cardType) && this.color.equals(c.color) && !this.position.isSame(c.position)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Method: randomBonus
     * Description: randomly estracts a number between 0 and MAX_SPECIALS, and returns the bonus in that position
     *@return a random Bonus
     */
    Bonus randomBonus(){
        int i=(int) (Math.random()*MAX_SPECIALS);
        return Bonus.values()[i];
    }

    /**
     * Method: randomMalus
     * Description: randomly estracts a number between 0 and MAX_SPECIALS, and returns the malus in that position
     *@return a random Malus
     */
    Malus randomMalus(){
        int i=(int) (Math.random()*MAX_SPECIALS);
        return Malus.values()[i];
    }

    /**
     * Method: foundCard
     * Description: changes the content of the card if that card has been found
     */
    void foundCard(){
        this.symbol=' ';
        this.color=Color.RESET;
        this.uncovered=true;
    }
    
    //------------PRINT METHODS--------------

    /**
     * Method: print
     * Description: prints content of a card
    */
    void print(){
        System.out.print(color.toString());
        System.out.print(" "+this.symbol+" ");
        System.out.print(Color.RESET);
    }

    /*VIENE UTILIZZATO?
    void printInfo(){
        System.out.println("Symbol: " + this.symbol);
        System.out.println("Status: " + (this.uncovered ? "Uncovered" : "Covered"));
        System.out.println("Coordinates:");
        System.out.println(" > Col: " + this.position.col);
        System.out.println(" > Row: " + this.position.row);
        System.out.println("Type:   " + this.cardType);
        System.out.println("Color:  " + this.color);
    }        
    */
}
