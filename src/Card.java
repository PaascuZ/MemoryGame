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
        // DA FARE
        System.out.println();
    }
}