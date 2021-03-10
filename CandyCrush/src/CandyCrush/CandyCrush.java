package CandyCrush;

import GameTemplate.*;
import java.util.ArrayList;
import java.util.Random;

import java.util.Scanner;

//6 colors in CandyCrush, represented by 1, 2, 3, 4, 5, 6
// Move-based gameplay e.g. 30 moves before gameover, NOT timer based

//implement method to check if there are valid moves

public class CandyCrush extends GameTemplate {

    private int row; // initially set to 9
    private int col; // initially set to 9
    private int turnCounter; // initially set to 30
    private int turnScore;

    public CandyCrush(int row, int col) {
        super(row, col);
        this.row = row;
        this.col = col;
        turnCounter = 30;
        turnScore = 0;
    }

    public void matching() {
        ArrayList<String> toChange = new ArrayList<String>();
        matchHorizontal(toChange);
        matchVertical(toChange);
        changeValues(toChange);
        turnScore = toChange.size();
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

    public boolean move(int row1, int col1, int row2, int col2) {
        //check if blocks are next to each other
        if (!(row1 == row2+1 && col1 == col2) &&
                !(row1 == row2-1 && col1 == col2) &&
                !(row1 == row2 && col1 == col2+1) &&
                !(row1 == row2 && col1 == col2-1)) {
            System.out.println("Blocks not next to eachother");
            return false;
        }
        //check if given rows and cols are within the board
        if ((row1 < 0 || row1 > row-1) || 
                (col1 < 0 || col1 > col-1) ||
                (row2 < 0 || row2 > row-1) ||
                (col2 < 0 || col2 > col-1)) {
            System.out.println("Invalid row/column");
            return false;
        }
        int val1 = board.tileMap[row1][col1];
        int val2 = board.tileMap[row2][col2];
        board.tileMap[row1][col1] = val2;
        board.tileMap[row2][col2] = val1;
        turnCounter--;
        return true;
    }

    public int returnScore() {
        return turnScore;
    }
    
    //Check the same color grids in Horiztonal, store the match line points in Map.
    private void matchHorizontal(ArrayList<String> toChange) {
        int counter = 1;
        for (int i=0; i<row; i++) {
            for (int j=0; j<col-1; j++) {
                if (board.tileMap[i][j] == board.tileMap[i][j+1] && j+1 == col-1) {
                    counter++;
                    if (counter >= 3) {
                        toChange.add(i + " " + (j-1));
                        toChange.add(i + " " + j);
                        toChange.add(i + " " + (j+1));
                    }
                    counter = 1;
                }
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
                if (board.tileMap[j][i] == board.tileMap[j+1][i] && j+1 == row-1) {
                    counter++;
                    if (counter >= 3) {
                        toChange.add((j-1) + " " + i);
                        toChange.add(j + " " + i);
                        toChange.add((j+1) + " " + i);
                    }
                    counter = 1;
                }
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
        if (checkValidHorizontal() && checkValidVertical()) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean checkValidHorizontal() {
        for (int i=0; i<row-0; i++) {
            for (int j=0; j<col-1; j++) {
                if (board.tileMap[i][j] ==  board.tileMap[i][j+1]) {
                    if (checkSurroundingH(i, j, i, j+1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkValidVertical(){
        for (int i=0; i<row-1; i++) {
            for (int j=0; j<col; j++){
                if (board.tileMap[i][j] ==  board.tileMap[i+1][j]){
                    if (checkSurroundingV(i, j, i+1, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkSurroundingH(int row1, int col1, int row2, int col2){
        // top left
        try {
            if (board.tileMap[row1][col1] == board.tileMap[row1 - 1][col1 - 1]){
                return true;
            }
        }
        catch (IndexOutOfBoundsException e) {}
        // top right
        try {
            if (board.tileMap[row1][col1] == board.tileMap[row1 + 1][col1 + 1]){
                return true;
            }
        }
        catch (IndexOutOfBoundsException e) {}
        // left
        try{
            if (board.tileMap[row1][col1] == board.tileMap[row1 ][col1 - 2]){
                return true;
            }
        }
        catch (IndexOutOfBoundsException e) {}
        // right
        try{
             if (board.tileMap[row2][col2] == board.tileMap[row2 ][col2 + 2]){
              return true;
            }
        }
        catch (IndexOutOfBoundsException e) {}
        // bottom left
        try {
            if (board.tileMap[row2][col2] == board.tileMap[row2 + 1][col2 - 1]){
                return true;
            }
        }
        catch (IndexOutOfBoundsException e) {}
        // bottom right
        try {
            if (board.tileMap[row2][col2] == board.tileMap[row2 + 1][col2 + 1]){
                return true;
            }
        }
        catch (IndexOutOfBoundsException e) {}
        // check immediate left (3 in a row) 
        try {
            if (board.tileMap[row1][col1] == board.tileMap[row1][col1-1]) {
                return true;
            }
        }
        catch (IndexOutOfBoundsException e) {}
        // check immediate right (3 in a row)
        try{
            if (board.tileMap[row2][col2] == board.tileMap[row2][col2+1]) {
                return true;
            }
        }
        catch (IndexOutOfBoundsException e) {}
        
        return false;
    }

    private boolean checkSurroundingV(int row1, int col1, int row2, int col2) {
        //top left
        try {
            if (board.tileMap[row1-1][col1-1] == board.tileMap[row1][col1]) {
                return true;
            }
        }
        catch (IndexOutOfBoundsException e) {}
        //top right
        try {
            if (board.tileMap[row1-1][col1+1] == board.tileMap[row1][col1]) {
                return true;
            }
        }
        catch (IndexOutOfBoundsException e) {}
        //top
        try {
            if (board.tileMap[row1-2][col1] == board.tileMap[row1][col1]) {
                return true;
            }
        }
        catch (IndexOutOfBoundsException e) {}
        //bottom left
        try {
            if (board.tileMap[row2+1][col2-1] == board.tileMap[row2][col2]) {
                return true;
            }
        }
        catch (IndexOutOfBoundsException e) {}
        //bottom right
        try {
            if (board.tileMap[row2+1][col2+1] == board.tileMap[row2][col2]) {
                return true;
            }
        }
        catch (IndexOutOfBoundsException e) {}
        //bottom
        try {
            if (board.tileMap[row2+2][col2] == board.tileMap[row2][col2]) {
                return true;
            }
        }
        catch (IndexOutOfBoundsException e) {}
        //immediate top (3 in a row)
        try {
            if (board.tileMap[row1][col1] == board.tileMap[row1-1][col1]) {
                return true;
            }
        }
        catch (IndexOutOfBoundsException e) {}
        //immediate bottom (3 in a row)
        try {
            if (board.tileMap[row2][col2] == board.tileMap[row2+1][col2]) {
                return true;
            }
        }
        catch (IndexOutOfBoundsException e) {}

        return false;
    }
    
    public static void main(String[] args) {
        CandyCrush test = new CandyCrush(10,10);
        // test.board.printBoard();
        test.createNewBoard();
        test.board.printBoard();

        // test.board.tileMap[0][0] = 1;
        // test.board.tileMap[0][1] = 2;
        // test.board.tileMap[0][2] = 3;
        // test.board.tileMap[0][3] = 4;
        // test.board.tileMap[1][0] = 8;
        // test.board.tileMap[1][1] = 6;
        // test.board.tileMap[1][2] = 7;
        // test.board.tileMap[1][3] = 8;
        // test.board.tileMap[2][0] = 1;
        // test.board.tileMap[2][1] = 3;
        // test.board.tileMap[2][2] = 2;
        // test.board.tileMap[2][3] = 1;
        // test.board.tileMap[3][0] = 1;
        // test.board.tileMap[3][1] = 7;
        // test.board.tileMap[3][2] = 6;
        // test.board.tileMap[3][3] = 5;

        // test.board.printBoard();

        // System.out.println("-------------");
        // test.board.tileMap[1][2] = 1;
        // test.board.tileMap[1][3] = 1;
        // test.board.tileMap[1][4] = 1;
        // test.board.tileMap[1][5] = 1;
        // test.board.tileMap[1][6] = 1;

        // test.board.tileMap[0][2] = 8;
        // test.board.tileMap[1][2] = 1;
        // test.board.tileMap[2][2] = 1;
        // test.board.tileMap[3][2] = 1;
        // test.board.tileMap[4][2] = 1;
        // test.matching();
        // test.board.printBoard();

        System.out.println("horizontal checking: " + test.checkValidHorizontal());
        System.out.println("vertical checking: " + test.checkValidVertical());
        
        // test.matching();
        // test.board.printBoard();
        // Scanner in = new Scanner(System.in);


        // while (!test.isGameOver()) {
        //     System.out.println("Enter move: ");
        //     String move = in.nextLine();
        //     System.out.println(move);
        //     int row1 = Integer.parseInt(String.valueOf(move.charAt(0)));
        //     System.out.println("row is " + row1);
        //     int col1 = Integer.parseInt(String.valueOf(move.charAt(2)));
        //     int row2 = Integer.parseInt(String.valueOf(move.charAt(4)));
        //     int col2 = Integer.parseInt(String.valueOf(move.charAt(6)));
        //     test.move(row1, col1, row2,col2);
        //     test.matching();
        //     test.board.printBoard();
        //     //in.close();
        // }
        // in.close();

    }
}
// 6 6 3 4 1
// 3 5 2 6 6
// 1 3 5 2 4
// 6 2 1 6 4
// 4 1 4 6 6

// 4 5 4 3 4 
// 6 1 5 1 6
// 2 3 1 1 5
// 1 2 5 2 6
// 3 2 6 6 6

// WORKS
// 1 2 3 6 4 
// 2 4 1 1 1
// 6 3 3 6 4
// 3 2 1 6 3
// 2 4 6 3 1