public class Player {
    String name;
    int points;
    boolean nowPlaying;

    Player(String name, int points, boolean nowPlaying){
        // Se 
        if(name.isEmpty()){
            this.name = "Unknown";
        }else{
            this.name = name;
        }
        this.points = points < 0 ? 0 : points;
        this.nowPlaying = nowPlaying;
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
    }
}
