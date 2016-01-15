package game.server;


public class Board {
	//Variable with the board
	private int my_board[][];
	//Board sizes
	private int size_h;
	private int size_v;
	//Initialize all the game board with a value
	public Board(int cols, int rows, int init_value) {
		//Create the board of size rows*cols
		my_board= new int [rows][cols];
		for(int i=0; i< my_board.length; i++)
		{
			for(int j=0; j<my_board[i].length; j++)
			{
				my_board[i][j]=init_value;
			}
		}
		//Save the size of the board
		size_v = rows;
		size_h = cols;
	}
	//Prints the Board by rows
	public void printBoard(){
		System.out.println(my_board.length);
		for(int i=0; i< my_board.length; i++)
		{
			for(int j=0; j<my_board[i].length; j++)
			{
				System.out.print(my_board[i][j]+" ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	//Get an element of the Board, if it doesn't exists, returns -1
	public int getElement(int col, int row){
		if(row>=my_board.length)
			return -1;
		else if(col>=my_board[row].length)
			return -1;
		else
			return my_board[row][col];
	}
	//Set an element of the Board, if it doesn't exists, returns false
	public boolean setElement(int col, int row, int set_to){
		if(row>=my_board.length)
			return false;
		else if(col>=my_board[row].length)
			return false;
		else
			my_board[row][col] = set_to;
		return true;
	}
	//Gets the number of columns
	public int getSize_H(){
		return size_h;
	}
	//Gets the number of rows	
	public int getSize_V(){
		return size_v;
	}
	//Returns the Board matrix
	public int[][] getBoard(){
		return this.my_board;
	}
}
