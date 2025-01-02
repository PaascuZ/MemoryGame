public class Coordinate {
    int row;
    int col;

    //--------------CONSTRUCTORS-------------------

    Coordinate(int row, int col){
        this.row = row;
        this.col = col;
    }

    Coordinate(){
    }

    //------------------METHODS------------------------
    
    /**
     * Method: isSame
     * Description: returns true if the coordinates given are the same as the ones of the object who uses this method, false otherwise
     * @param c
     * @return boolean
     */
    boolean isSame(Coordinate c){
        if(this.row==c.row && this.col==c.col){
            return true;
        }else{
            return false;
        }
    }
}
