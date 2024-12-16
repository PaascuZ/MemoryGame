public class Player {
    String name;
    int points;
    boolean nowPlaying;

    Player(String name, int points, boolean nowPlaying){
        // Se 
        if(name.isEmpty()){
            this.name = "Unknown";
        }
        this.points = points < 0 ? 0 : points;
        this.nowPlaying = nowPlaying;
    }
}
