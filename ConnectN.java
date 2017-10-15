
import java.util.Scanner;

public class ConnectN
{
	private int gameState[][];
	private int rows;
	private int columns;
	private int winningNumber;
	private int lastRow, lastColumn;

	private static Scanner scanner = new Scanner(System.in);

	public ConnectN()
	{
		//initialize
			//initialize class variables to default parameters
		this.rows = 6;
		this.columns = 7;
		this.winningNumber = 4;
		this.gameState = new int[this.rows][this.columns];//initialize game state
		
		this.lastRow = -1; //initialize lastRow and lastColumn
		this.lastColumn = -1;
	}

	public ConnectN(int rows, int columns, int winningNumber)
	{
		//initialize
		//initialize class variables based on input parameters
		this.rows = rows;
		this.columns = columns;
		this.winningNumber = winningNumber;
		this.gameState = new int[this.rows][this.columns];//initialize game state
		
		this.lastRow = -1;	//initialize lastRow and lastColumn
		this.lastColumn = -1;
	}

	public void clearGame()
	{
		//set all positions to empty
		
		for (int row = 0; row < this.rows; row++) {
			for (int column = 0; column < this.columns; column++) {
				this.gameState[row][column] = 0;
			}
		}
	}

	public boolean insertChip(int playerNumber, int columnNumber)
	{
		boolean inserted = false;

		//try to insert
			//if the top position is empty
				//start at row 0 (the top row)
				//loop through the rows until a non-empty position is found
				
				//set game state position to the player number
				//inserted = true
				//set last row and last column to the inserted position
		int row = 0;
		
		if (this.gameState[row][columnNumber] == 0) {
			
			while ((row < this.rows) && (this.gameState[row][columnNumber] == 0)) {
				row++;
			}
			row--;
			this.gameState[row][columnNumber] = playerNumber;
			inserted = true;
			this.lastRow = row;
			this.lastColumn = columnNumber;
		}
		
		return inserted;
	}

	public void outputGameState()
	{
		//create a string builder
		StringBuilder sb=new StringBuilder("");
		
		for (int row = 0; row < this.rows; row++) { //loop through all rows
			for (int column = 0; column < this.columns; column++) { //loop through all columns
			
				sb.append(this.gameState[row][column] + "  ");
				
				//append gameState[row][column] to the output string builder
				//output the game state string
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

	private boolean isGameFull()
	{
		boolean isFull = true;

		//check if game board is full
			//loop through all columns
				//if the top position is empty then the game is not full
		
		for (int column = 0; column < this.columns ; column++){
			if (this.gameState[0][column] == 0) {
				isFull = false;
			}
		}

		return isFull;
	}

	public int detectWin()
	{
		int winner = -1;

		//detect win rows
		//detect win columns
		//detect win diagonals
		
		winner = this.detectWinRows();
		if (winner != 0) {
			return winner;
		}
		winner = this.detectWinColumns(); 
		if (winner != 0) {
			return winner;
		}
		winner = this.detectWinDiagonals();
		
		return winner;
	}

	private int detectWinColumns()
	{
		//return the player who won or 0 if no player has won

		int winColumns = 0;
		
		//start at last posotion
		//loop down the column as far as needed
		//set winColumn if count of number in a row is greater than winning number
		
		int count = 1;
		int lastPlayer = this.gameState[this.lastRow][this.lastColumn];
		for (int i = this.lastRow + 1; i < this.rows; i++) {
			if ( lastPlayer == this.gameState[i][this.lastColumn]) {
				count++;
			} else {
				break;
			}
		}
		
		if (count >= this.winningNumber) {
			winColumns = lastPlayer;
		}
		
		return winColumns;
	}

	private int detectWinRows()
	{
		int winRows = -1;
		int lastPlayer = gameState[lastRow][lastColumn];
		int count = 1; //count of chips in a row
		boolean stopLeft = false; //stop searching to the left
		boolean stopRight = false; //stop searching to the right

		//left size
		//loop starting at lastColumn-1 until out of bounds or stopLeft = false
			//if current position == player
				//increment count
			//else
				//stopLeft = true
		
		for (int i = this.lastColumn - 1; (i >= 0 && !stopLeft); i--) {
			if (lastPlayer == this.gameState[this.lastRow][i]) {
				count++;
			} else {
				stopLeft = true;
			}
		}

		
		//right side (similiar to left side)
		
		for (int i = this.lastColumn + 1; (i < this.columns && !stopRight); i++) {
			if (lastPlayer == this.gameState[this.lastRow][i]) {
				count++;
			} else {
				stopRight = true;
			}
		}
		
		//if count >= winningNumber
			//set winRows to the last player
		
		winRows = count >= this.winningNumber ? lastPlayer : 0;

		return winRows;
	}


	private int detectWinDiagonals()
	{
		
		int winDiagonals = -1;
		int lastPlayer = gameState[lastRow][lastColumn];
		int countLeftToRight = 1; //count in a row for diagonals from left to right
		int countRightToLeft = 1;  //count in a row for diagonals from right to left
		boolean stopLeft1 = false; //stop searching to the left (diagonal left to right)
		boolean stopRight1 = false; //stop searhcing to the right (diagonal right to left)
		boolean stopLeft2 = false; //stop searching to the left (diagonal left to right)
		boolean stopRight2 = false; //stop searching to the right (diagonal right to left)

		//diagonal left to right
			//use winRows code and modify to work with the changing rows
		for (int i = this.lastRow - 1, j = this.lastColumn - 1; (i >= 0 && j >= 0 && !stopLeft1); i--,j--) {
			if (lastPlayer == this.gameState[i][j]) {
				countRightToLeft++;
			} else {
				stopLeft1 = true;
			}
		}

		for (int i = this.lastRow + 1, j = this.lastColumn + 1; (i < this.rows && j < this.columns && !stopLeft2); i++,j++) {
			if (lastPlayer == this.gameState[i][j]) {
				countRightToLeft++;
			} else {
				stopLeft2 = true;
			}
		}		
		

		//diagonal rightToLeft
			//use winRows code and modify to work with the changing rows
		
		for (int i = this.lastRow + 1, j = this.lastColumn - 1; (i < this.rows && j >= 0 && !stopRight1); i++,j--) {
			if (lastPlayer == this.gameState[i][j]) {
				countLeftToRight++;
			} else {
				stopRight1 = true;
			}
		}

		
		for (int i = this.lastRow - 1, j = this.lastColumn + 1; (i >= 0 && j < this.columns && !stopRight2); i--,j++) {
			if (lastPlayer == this.gameState[i][j]) {
				countLeftToRight++;
			} else {
				stopRight2 = true;
			}
		}

		//if countLeftToRight or countRightToLeft >= winningNumber
			//setwinDiagonals to the last player
				
		winDiagonals = (countLeftToRight >= this.winningNumber || countRightToLeft >= this.winningNumber) ? lastPlayer : 0;

		return winDiagonals;
	}

	public void playGame()
	{
		int turn = 1;
		boolean gameOn = true;

		outputGameState();

		while(gameOn)
		{
			//output players turn
			System.out.println("Its Player " + turn + "'s turn now!!\n\n");
			
			//while input is not valid
				//ask for user input
			int input;
			while (true) {
				input = scanner.nextInt();
				if ( input >= this.columns || input < -1 ) { 
					//tell user input is not valid
					System.out.println("Enter a valid input.\n Column should be less than "+ this.columns + " and greater than or eqaul to  0\n Enter -1 to quit the game.");
				} else {
					break;
				}
				
			}
			
			if (input == -1) {
				gameOn = false;
			} else {
				boolean inserted = this.insertChip(turn, input); //try to insert chip
				if (inserted) { //if chip was inserted
					int winner = this.detectWin(); //check for winner
					if (winner !=0) {
						//output winner
						System.out.println("Winner is Player " + turn + " !! Congratulations!!\n");
						gameOn = false;
					} else if (this.isGameFull()) { //check if game is over
						System.out.println("All the cells are filled!! Nobody Wins!!\n");
						gameOn = false;
					} else {
						//switch turn and output game state
						turn = turn == 1 ? 2 : 1;
						this.outputGameState();
					}
				} else {
					//let user know the chip cannot be inserted there
					System.out.println("Sorry!! The chip cannot be inserted there. Please try a different column");
				}
			}				
		}
	}

	public static void main(String args[])
	{
		int rows = 6;
		int columns = 7;
		int winningNumber = 4;

		if(args.length == 3)
		{
			//set rows, columns, and winningNumber to the input arguments
			rows = Integer.parseInt(args[0]);
			columns = Integer.parseInt(args[1]);
			winningNumber = Integer.parseInt(args[2]);
		}

		
		boolean keepPlaying = true;

		while(keepPlaying)
		{
			ConnectN connectN = new ConnectN(rows, columns, winningNumber);
			connectN.playGame();

			boolean validInput = false;
			while (!validInput) {
			//ask if user wants to play again
			//continue loop if he/she does
				System.out.println("Do you want to play again ?\nPress 1 to play again\nPress 0 to exit.");
				int  choice = 0;
				choice = scanner.nextInt();
				
				validInput = (choice == 1 || choice == 0);
				if (!validInput) {
					System.out.println("Please Enter a valid Input!");
				} else if (choice == 0) {
					System.out.println("Come back again!!");
					keepPlaying = false;
				} else {
					keepPlaying = true;
				}
			}
		}

		scanner.close();
	}
	
}