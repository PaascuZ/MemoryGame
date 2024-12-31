public class Coordinate {
    int row;
    int col;

    Coordinate(int row, int col){
        this.row = row;
        this.col = col;
    }

    Coordinate(){
    }

    boolean isSame(Coordinate c){
        if(this.row==c.row && this.col==c.col){
            return true;
        }else{
            return false;
        }
    }
}
