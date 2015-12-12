package game.server;

public class Board {
	private int my_board[][];
	private int size_h;
	private int size_v;
	public Board(int cols, int rows, int init_value) {
		my_board= new int [rows][cols];
		for(int i=0; i< my_board.length; i++)
		{
			for(int j=0; j<my_board[i].length; j++)
			{
				my_board[i][j]=init_value;
			}
		}
		size_v = rows;
		size_h = cols;
	}
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
	
	public int getElement(int col, int row){
		if(row>=my_board.length)
			return -1;
		else if(col>=my_board[row].length)
			return -1;
		else
			return my_board[row][col];
	}
	
	public boolean setElement(int col, int row, int set_to){
		if(row>=my_board.length)
			return false;
		else if(col>=my_board[row].length)
			return false;
		else
			my_board[row][col] = set_to;
		return true;
	}
	
	public int getSize_H(){
		return size_h;
	}
	
	public int getSize_V(){
		return size_v;
	}	
}
