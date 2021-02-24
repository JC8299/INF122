package TMGE;

public abstract class GameTemplate {

    public GameBoard board;

    public GameTemplate(int row, int col) {
        board = new GameBoard(row, col);
    }

    public abstract void matching();

    public abstract void update(int time);

    public abstract boolean isGameOver();
    
}
