package Player;

public class Player {
    public String name;
    public int score1;
    public int score2;
    public Player(String n){
        name = n;
        score1 = 0;
        score2 = 0;
    }

    public String getName() {
        return name;
    }
    
    public void setScoreOne(int s) {
        score1 = s;
    }
    
    public int getScoreOne() {
        return score1;
    }
    
    public void setScoreTwo(int s) {
        score2 = s;
    }
    
    public int getScoreTwo() {
        return score2;
    }
}
