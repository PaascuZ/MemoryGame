public class Player {
    String name;
    int points;
    boolean nowPlaying;
    boolean jolly;
    boolean stop;

    Player(String name, int points, boolean nowPlaying){
        // Se 
        if(name.isEmpty()){
            this.name = "Unknown";
        }else{
            this.name = name;
        }
        this.points = points < 0 ? 0 : points;
        this.nowPlaying = nowPlaying;
        this.jolly = false;
        this.stop = false;
    }

    // Second constructor, to insert only the name when preparing the game
    Player(String name){
        if(name.isEmpty()){
            this.name = "Unknown";
        }else{
            this.name = name;
        }
        this.points = 0;
        this.nowPlaying = false;
        this.jolly = false;
        this.stop = false;
    }

    //-------------------------------------------------------------------------------------------
    //                                    BONUS METHODS
    //-------------------------------------------------------------------------------------------

    void addPoints(){
        this.points+=100;
    }

    void doublePoints(){
        this.points*=2;
    }

    void addDonation(int i){
        this.points+=i;
    }

    void hasJolly(){
        this.jolly=true;
    }

    void removeJolly(){
        this.jolly=false;
    }


    //-------------------------------------------------------------------------------------------
    //                                    MALUS METHODS
    //-------------------------------------------------------------------------------------------

    int subDonation(){
        int points=10;
        if(this.points-points<10){
            points=this.points;
            this.points=0;
        }else{
            this.points-=points;
        }
        return points;
    }

    void subPoints(){
        this.points-=100;
        if(this.points<0){
            this.points=0;
        }
    }

    void loseAll(){
        this.points=0;
    }

    void stopOnce(){
        this.stop=true;
    }

    //-------------------------------------------------------------------------------------------
    //                                    GENERAL METHODS
    //-------------------------------------------------------------------------------------------
    void print(){
        System.out.println("Name:    " + this.name);
        System.out.println("Points:  " + this.points);
        System.out.println("--- STATUS ---");
        System.out.println((this.nowPlaying ? "Playing" : "Not Playing"));
        System.out.println("Jolly:   " + (this.jolly ? "Yes" : "No"));
        System.out.println("Stopped: " + (this.stop ? "Yes" : "No"));
        // sout to take some space
        System.out.println();
    }

}
