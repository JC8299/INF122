package Tetris;

import java.util.ArrayList;
import java.util.Random;

import GameTemplate.*;

public class Tetris extends GameTemplate {
	private int height; //Top row: [0], bottom row: [height-1], DEFAULT = 40
	private int width; //DEFAULT = 10

	private Random rand;
	private int score;
	private int[][] currentPiece = new int[4][2]; //4 arrays of 2 ints representing (x,y) board coordinates
	private int currentColor; //Value between 1-7 representing the current piece type & color
	private boolean gameOver;


	public Tetris(int row, int col) {
		super(row, col);
		height = row;
		width = col;

		rand = new Random();
		score = 0;
		gameOver = false;
		newPiece();
	}

	public void matching() {
		//Find full rows
		ArrayList<Integer> matches = new ArrayList<Integer>();
		for (int i = 0; i < height; i++) {
			int occupied = 0;
			for (int col : board.tileMap[i]) {
				if (col > 0) {
					occupied++;
				}
			}
			if (occupied == width) {
				matches.add(i);
			}
		}
		//Drop full rows
		for (int row : matches) {
			for (int i = row; i > 0; i--) {
				board.tileMap[i] = board.tileMap[i-1];
			}
			//Add empty top row
			for (int i = 0; i < width; i++) {
				board.tileMap[0][i] = 0;
			}
		}
		switch (matches.size()) {
			case 0:
				score = 0;
				break;
			case 1:
				score = 100;
				break;
			case 2:
				score = 300;
				break;
			case 3:
				score = 500;
				break;
			case 4:
				score = 1000;
				break;
		}
	}

	@Override
	public void update(int time) {
		unplacePiece();
		//handleInput()
//		rotatePieceRight();
		if(dropPiece()) {
			placePiece();
		}
		else {
			matching();
		}
		return;
	}

	@Override
	public boolean isGameOver() {
		return gameOver;
	}

	@Override
	public void createNewBoard() {
		board.createBoard();
	}

	@Override
	public boolean move(int row1, int col1, int row2, int col2) {
		unplacePiece();

		if(row1 == 1){
			movePieceLeft();
		}
		else if(row1 == 2){
			movePieceRight();
		}
		else if(row1 == 3){
			rotatePieceRight();
		}
		else if(row1 == 4){
			dropPiece();
		}
		placePiece();
		return true;
	}

	@Override
	public int returnScore() {
		return score;
	}

	private void newPiece() {
		int id = rand.nextInt(7)+1; //Generates values between 1-7
		int mid = width/2 - 1;
		currentColor = id;
		switch(id) {
			case 1:
				//horizontal
				for (int i = 0; i < currentPiece.length; i++) {
					currentPiece[i][0] = mid-1+i;
					currentPiece[i][1] = -1;
				}
				break;
			case 2:
				//square
				currentPiece[0][0] = mid;
				currentPiece[1][0] = mid+1;
				currentPiece[2][0] = mid;
				currentPiece[3][0] = mid+1;
				currentPiece[0][1] = -2;
				currentPiece[1][1] = -2;
				currentPiece[2][1] = -1;
				currentPiece[3][1] = -1;
				break;
			case 3:
				//T-shape
				currentPiece[0][0] = mid;
				currentPiece[1][0] = mid-1;
				currentPiece[2][0] = mid;
				currentPiece[3][0] = mid+1;
				currentPiece[0][1] = -2;
				currentPiece[1][1] = -1;
				currentPiece[2][1] = -1;
				currentPiece[3][1] = -1;
				break;
			case 4:
				//S-shape
				currentPiece[0][0] = mid;
				currentPiece[1][0] = mid-1;
				currentPiece[2][0] = mid;
				currentPiece[3][0] = mid+1;
				currentPiece[0][1] = -2;
				currentPiece[1][1] = -1;
				currentPiece[2][1] = -1;
				currentPiece[3][1] = -2;
				break;
			case 5:
				//Z-shape
				currentPiece[0][0] = mid-1;
				currentPiece[1][0] = mid;
				currentPiece[2][0] = mid;
				currentPiece[3][0] = mid+1;
				currentPiece[0][1] = -2;
				currentPiece[1][1] = -2;
				currentPiece[2][1] = -1;
				currentPiece[3][1] = -1;
				break;
			case 6:
				//J-shape
				currentPiece[0][0] = mid-1;
				currentPiece[1][0] = mid-1;
				currentPiece[2][0] = mid;
				currentPiece[3][0] = mid+1;
				currentPiece[0][1] = -2;
				currentPiece[1][1] = -1;
				currentPiece[2][1] = -1;
				currentPiece[3][1] = -1;
				break;
			case 7:
				//L-shape
				currentPiece[0][0] = mid+1;
				currentPiece[1][0] = mid-1;
				currentPiece[2][0] = mid;
				currentPiece[3][0] = mid+1;
				currentPiece[0][1] = -2;
				currentPiece[1][1] = -1;
				currentPiece[2][1] = -1;
				currentPiece[3][1] = -1;
				break;
		}
		if (currentColor > 1 && canMove(0,1)) {
			for (int[] point : currentPiece) {
				point[1]++;
			}
		}
		dropPiece();
	}

	//Right: dx=-1,dy=0; down: dx=0,dy=1; left: dx=1,dy=0
	private boolean canMove(int dx, int dy) {
		for (int[] point : currentPiece) {
			//Check if space is in range
			if (point[0]+dx < 0 || point[0]+dx >= width) {
				return false;
			}
			if (point[1]+dy >= height) {
				return false;
			}
			//Check if space is occupied
			if (point[1] >= 0) {
				if (board.tileMap[point[1]+dy][point[0]+dx] > 0) {
					return false;
				}
			}
		}
		return true;
	}
	private boolean canRotateRight() {
		if (currentColor == 2) {
			return true;
		}
		int[][] tempPoints = new int[4][2];
		int[] pivot = currentPiece[2];
		int i = 0;
		for (int[] point : currentPiece) {
			int xn = -point[1] + pivot[0] + pivot[1];
			int yn = point[0] - pivot[0] + pivot[1];
			tempPoints[i][0] = xn;
			tempPoints[i][1] = yn;
			i++;
		}
		for (int[] point : tempPoints) {
			if (point[0] < 0 || point[0] >= width) {
				return false;
			}
			if (point[1] < 0 || point[1] >= height) {
				return false;
			}
			if (board.tileMap[point[1]][point[0]] > 0) {
				return false;
			}
		}
		return true;
	}
	private boolean canRotateLeft() {
		if (currentColor == 2) {
			return true;
		}
		int[][] tempPoints = new int[4][2];
		int[] pivot = currentPiece[2];
		int i = 0;
		for (int[] point : currentPiece) {
			int xn = point[1] + pivot[0] - pivot[1];
			int yn = -point[0] + pivot[0] + pivot[1];
			tempPoints[i][0] = xn;
			tempPoints[i][1] = yn;
			i++;
		}
		for (int[] point : tempPoints) {
			if (point[0] < 0 || point[0] >= width) {
				return false;
			}
			if (point[1] < 0 || point[1] >= height) {
				return false;
			}
			if (board.tileMap[point[1]][point[0]] > 0) {
				return false;
			}
		}
		return true;
	}
	//Set piece in place, set game over to true if any points are above the top row
	private void setPiece() {
		for (int[] point : currentPiece) {
			if (point[1] >= 0) {
				board.tileMap[point[1]][point[0]] = currentColor;
			}
			else {
				gameOver = true;
			}
		}
		if (!gameOver) {
			newPiece();
		}
	}
	//Temporarily place piece on board between updates
	private void placePiece() {
		for (int[] point : currentPiece) {
			if (point[1] >= 0) {
				board.tileMap[point[1]][point[0]] = currentColor;
			}
		}
	}
	private void unplacePiece() {
		for (int[] point : currentPiece) {
			if (point[1] >= 0) {
				board.tileMap[point[1]][point[0]] = 0;
			}
		}
	}
	private void printPiece() {
		System.out.println("Color: " + currentColor);
		for (int[] point : currentPiece) {
			System.out.println(point[0] + ", " + point[1]);
		}
	}
	//Drop current piece one row if able to, else set in place
	public boolean dropPiece() {
		if (canMove(0,1)) {
			for (int[] point : currentPiece) {
				point[1]++;
			}
			return true;
		}
		else {
			setPiece();
			return false;
		}
	}
	public void movePieceLeft() {
		if (canMove(-1,0)) {
			for (int[] point : currentPiece) {
				point[0]--;
			}
		}
	}
	public void movePieceRight() {
		if (canMove(1,0)) {
			for (int[] point : currentPiece) {
				point[0]++;
			}
		}
	}
	public void rotatePieceRight() {
		if (canRotateRight()) {
			System.out.println("ROTATE RIGHT " + currentColor);
			if (currentColor == 2) {
				return;
			}
			int[] pivot = currentPiece[2];
			for (int[] point : currentPiece) {
				int xn = -point[1] + pivot[0] + pivot[1];
				int yn = point[0] - pivot[0] + pivot[1];
				point[0] = xn;
				point[1] = yn;
			}
		}
	}
	public void rotatePieceLeft() {
		if (canRotateLeft()) {
			System.out.println("ROTATE LEFT " + currentColor);
			if (currentColor == 2) {
				return;
			}
			int[] pivot = currentPiece[2];
			for (int[] point : currentPiece) {
				int xn = point[1] + pivot[0] - pivot[1];
				int yn = -point[0] + pivot[0] + pivot[1];
				point[0] = xn;
				point[1] = yn;
			}
		}
	}
	public static void main(String[] args) {
		Tetris test = new Tetris(40,10);
		while(!test.isGameOver()) {
			test.update(0);
			test.board.printBoard();
		}

	}

}