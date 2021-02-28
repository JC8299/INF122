package CandyCrush;

import TMGE.GameTemplate;
import java.util.ArrayList;
import java.util.Random;

//6 colors in CandyCrush, represented by 1, 2, 3, 4, 5, 6
// Move-based gameplay e.g. 30 moves before gameover, NOT timer based

public class CandyCrush extends GameTemplate {

    private int row;
    private int col;

    public CandyCrush(int row, int col) {
        super(row, col);
        this.row = row;
        this.col = col;
    }

    public void matching() {
        ArrayList<String> toChange = new ArrayList<String>();
        matchHorizontal(toChange);
        //matchVertical(toChange);
        changeValues(toChange);
    }

    public void update(int time) {
        return;
    }

    public boolean isGameOver() {
        return false;
    }

    public void createNewBoard() {
        Random ran = new Random();
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                board.tileMap[i][j] = ran.nextInt(6) + 1;
            }
        }
    }
    
    //Check the same color grids in Horiztonal, store the match line points in Map.
    private void matchHorizontal(ArrayList<String> toChange) {
        //ArrayList<String> <- "0 2"
        int counter = 1;
        for (int i=0; i<row; i++) {
            for (int j=0; j<col-1; j++) {
                if (board.tileMap[i][j] == board.tileMap[i][j+1] && board.tileMap[i][j] != 0) {
                    counter++;
                    if (counter >= 3) {
                        toChange.add(i + " " + (j-1));
                    }
                }
                else {
                    if (counter >= 3) {
                        toChange.add(i + " " + (j-1));
                        toChange.add(i + " " + j);
                    }
                    counter = 1;
                }             
            }       
        }
    }

    private void matchVertical(ArrayList<String> toChange) {

    }

    private void changeValues(ArrayList<String> toChange) {
        for (String element : toChange) {
            String[] result = element.split(" ");
            board.tileMap[Integer.parseInt(result[0])][Integer.parseInt(result[1])] = -1;
        }
    }
    
    public static void main(String[] args) {
        CandyCrush test = new CandyCrush(9, 9);
        test.board.printBoard();
        test.createNewBoard();
        test.board.printBoard();
        // System.out.println("-------------");
        // test.board.tileMap[0][2] = 1;
        // test.board.tileMap[0][3] = 1;
        // test.board.tileMap[0][4] = 1;
        // test.board.tileMap[0][5] = 1;
        // test.board.tileMap[0][6] = 1;
        // test.matching();
        // test.board.printBoard();

    }
}
