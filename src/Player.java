public class Player {
    String name;
    int points;
    boolean jolly;
    boolean stop;

    //--------CONSTRUCTORS------------------

     // Constructor to insert only the name when preparing the game
    Player(String name){
        if(name.isEmpty()){
            this.name = "Unknown";
        }else{
            this.name = name;
        }
        this.points = 0;
        this.jolly = false;
        this.stop = false;
    }

    //----------------BONUS METHODS---------------------

    /**
     * Method: addPoints
     * Description: adds points when a pair of cards is discovered
     */
    void addPoints(){
        this.points+=10;
    }
    
    /**
     * Method: addBonus
     * Description: adds bonus points when discovering a pair of Bonus Special Cards of type "ADD100"
     */
    void addBonus(){
        this.points+=100;
    }

    /**
     * Method: doublePoints
     * Description: doubles the points of the user when discovering a pair of Bonus Cards of type "X2"
     */
    void doublePoints(){
        this.points*=2;
    }

    /**
     * Method: hasJolly
     * Description: adds the jolly if a pair of Bonus Cards of type "JOLLY" is discovered
     */    
    void hasJolly(){
        this.jolly=true;
    }
    
    /**
     * Method: removeJolly
     * Description: removes the Jolly after use
     */ 
    void removeJolly(){
        this.jolly=false;
    }

    //----------------------MALUS METHODS---------------------------

    /**
     * Method: subDonation
     * Description: removes half of the points to donate them to another player
     */ 
    int subDonation(){
        int temp=this.points/2;
        this.points-=temp;
        return temp;
    }

    /**
     * Method: subMalus
     * Description: removes points if the Malus Card "SUB100" is picked, if points are less then 100, 
     * number of points returns to zero
     */ 
    void subMalus(){
        this.points-=100;
        if(this.points<0){
            this.points=0;
        }
    }

    /**
     * Method: loseAll
     * Description: brings points of the player back to 0
     */ 
    void loseAll(){
        this.points=0;
    }

    /**
     * Method: stopOnce
     * Description: adds the stop flag to skip next turn
     */ 
    void stopOnce(){
        this.stop=true;
    }

    /**
     * Method: removeStop
     * Description: removes the Stop after one turn
     */ 
    void removeStop(){
        this.stop=false;
    }

    /**
     * Method: addDonation
     * Description: adds the points subtracted from the player who picked the Malus "Donation" card
     */
    void addDonation(int i){
        this.points+=i;
    }

    //------------------------GENERAL METHODS-------------------------------

    /**
     * Method: printGameInfo
     * Description: returns a string with the name and points of the player
     */
    String printGameInfo(){
        return this.name + " | " + this.points + " pts.";
    }

}
