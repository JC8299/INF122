package TMGE;

public class GameBoard {

    //private ArrayList<ArrayList<Integer>> tileMap;
    public int[][] tileMap;
    private int row;
    private int col;

    public GameBoard(int row, int col) {
        this.row = row;
        this.col = col;
        tileMap = new int[row][col];
    }

    // Create an empty board of zeroes
    public void createBoard() {
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                tileMap[i][j] = 0;
            }
        }
    }

    // For testing without a GUI 
    public void printBoard() {
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                System.out.print(tileMap[i][j] + " ");
            }
            System.out.println();
        }
    }
    
}
