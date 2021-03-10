package Player;

public class Player {
    public String name;
    public int scoreOne;
    public int scoreTwo;
    public Player(String n){
        name = n;
        score1 = 0;
        score2 = 0;
    }

    public String getName() {
        return name;
    }
    
    public void setScoreOne(int s) {
        scoreOne = s;
    }
    
    public int getScoreOne() {
        return scoreOne;
    }
    
    public int setScoreTwo(int s) {
        scoreTwo = s;
    }
    
    public int getScoreTwo() {
        return scoreTwo;
    }
}
