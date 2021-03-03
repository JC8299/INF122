package CandyCrush;

import TMGE.GameTemplate;
import java.util.ArrayList;
import java.util.Random;

//6 colors in CandyCrush, represented by 1, 2, 3, 4, 5, 6
// Move-based gameplay e.g. 30 moves before gameover, NOT timer based

//implement method to check if there are valid moves

public class CandyCrush extends GameTemplate {

    private int row; // initially set to 9
    private int col; // initially set to 9
    private int turnCounter; // initially set to 30

    public CandyCrush() {
        super(9, 9);
        this.row = 9;
        this.col = 9;
        turnCounter = 30;
    }

    public void matching() {
        ArrayList<String> toChange = new ArrayList<String>();
        matchHorizontal(toChange);
        matchVertical(toChange);
        changeValues(toChange);
        dropTiles();
        addRandomTilesToTop();
    }

    public void update(int time) {
        //handleInput()
        return;
    }

    public boolean isGameOver() {
        if (turnCounter == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    // Create a new board with randomized tiles
    public void createNewBoard() {
        Random ran = new Random();
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                board.tileMap[i][j] = ran.nextInt(6) + 1;
            }
        }
    }

    public void move(int row1, int col1, int row2, int col2) {
        if ((row1 != row2+1 && col1 != col2) ||
                (row1 != row2-1 && col1 != col2) ||
                (row1 != row2 && col1 != col2+1) ||
                (row1 != row2 && col1 != col2-1)) {
            System.out.println("Not a valid move");
            return;
        }
        int val1 = board.tileMap[row1][col1];
        int val2 = board.tileMap[row2][col2];
        board.tileMap[row1][col1] = val2;
        board.tileMap[row2][col2] = val1;
        turnCounter--;
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
        int counter = 1;
        for (int i=0; i<col; i++) {
            for (int j=0; j<row-1; j++) {
                if (board.tileMap[j][i] == board.tileMap[j+1][i] && board.tileMap[j][i] != 0) {
                    counter++;
                    if (counter >= 3) {
                        toChange.add((j-1) + " " + i);
                    }
                }
                else {
                    if (counter >= 3) {
                        toChange.add((j-1) + " " + i);
                        toChange.add(j + " " + i);
                    }
                    counter = 1;
                }             
            }       
        }

    }

    private void changeValues(ArrayList<String> toChange) {
        for (String element : toChange) {
            String[] result = element.split(" ");
            board.tileMap[Integer.parseInt(result[0])][Integer.parseInt(result[1])] = -1;
        }
    }

    private void dropTiles() {       
        for (int j=0; j<col; j++) {
            for (int i=0; i<row; i++) {
                if (board.tileMap[i][j] == -1) {
                    //shift all tiles above this position down one
                    moveDown(i, j);
                }
            }
        }
    }

    private void moveDown(int row, int col){
        if (row != 0){
            int temp;
            temp = board.tileMap[row-1][col];
            board.tileMap[row][col]= temp;
            moveDown(row-1,col);
        }
        else{
            board.tileMap[row][col] = -1;
            return;
        }    
    }

    private void addRandomTilesToTop() {
        Random ran = new Random();
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (board.tileMap[i][j] == -1) {
                    board.tileMap[i][j] = ran.nextInt(6) + 1;
                }
            }
        }
    }

    private boolean checkIfValidMoveExists() {
        //check for two common elements in a row
        //check the surrounding
        //check horizontal, then check vertical
        
        //special cases?
        // if (row1 == 0 && col1 == col-2 && row2 == 0 && col2 == col-1) {
            //check if valid move exists
            //return true or false

        //check for two not in a row 
        return true;
    }

    private boolean checkValidHorizontal() {
        for (int i=0; i<row-1; i++) {
            for (int j=0; j<col-2; j++) {
                if (board.tileMap[i][j] ==  board.tileMap[i][j+1]) {
                    checkSurrounding(i, j, i, j+1);
                }
            }
        }

        return true;
    }

    private boolean checkValidVertical(){
        for (int i=0; i<row-1; i++) {
            for (int j=0; j<col-1; j++){
                if (board.tileMap[i][j] ==  board.tileMap[i+1][j]){
                    checkSurrounding(i, j, i, j+1);
                }
            }
        }
        return true;
    }

    private boolean checkSurroundingH(int row1, int col1, int row2, int col2){
        // top left
        board.tileMap[row1][col1] = board.tileMap[row1 - 1][col1 - 1];
        // top right
      
        // left
       
        // right
        // bottom left
        // bottom right
        // if (topleft, topright, left, right, bottomleft, bottomright) == [row1][col1]
        // else if somewhere in the middle
        return true;
    }

    private boolean checkSurroundingV(int row1, int col1, int row2, int col2) {
        //top left
        if (board.tileMap[row1-1][col1-1] == board.tileMap[row1][col1]) {
            return true;
        }
        //top right
        if (board.tileMap[row1-1][col1+1] == board.tileMap[row1][col1]) {
            return true;
        }
        //top
        //bottom left
        //bottom right
        //bottom
        return false;
    }
    
    public static void main(String[] args) {
        CandyCrush test = new CandyCrush();
        test.board.printBoard();
        // test.createNewBoard();
        // test.board.printBoard();
        System.out.println("-------------");
        test.board.tileMap[1][2] = 1;
        test.board.tileMap[1][3] = 1;
        test.board.tileMap[1][4] = 1;
        test.board.tileMap[1][5] = 1;
        test.board.tileMap[1][6] = 1;

        // test.board.tileMap[0][2] = 8;
        // test.board.tileMap[1][2] = 1;
        // test.board.tileMap[2][2] = 1;
        // test.board.tileMap[3][2] = 1;
        // test.board.tileMap[4][2] = 1;
        test.matching();
        test.board.printBoard();

    }
}
