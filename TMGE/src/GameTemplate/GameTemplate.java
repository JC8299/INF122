package GameTemplate;

public abstract class GameTemplate {

    public GameBoard board;
    public int[] temp1;
    public int[] temp2;
    public GameTemplate(int row, int col) {
        board = new GameBoard(row, col);
        temp1 = new int[]{0, 0};
        temp2 = new int[]{-2,-2};
    }


    public abstract void matching();

    public abstract void update(int time);

    public abstract boolean isGameOver();

    public abstract void createNewBoard();

    public abstract boolean move(int row1, int col1, int row2, int col2);

    public abstract int returnScore();

    //public abstract void handleInput();
    
}
