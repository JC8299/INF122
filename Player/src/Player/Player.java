package Player;

public class Player {
    public string name;
    public int scoreOne;
    public int scoreTwo;
    public Player(string n){
        name = n;
        score1 = 0;
        score2 = 0;
    }

    public string getName() {
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
