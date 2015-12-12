package game.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Game {
	private Board my_Board = new Board(3, 3, 0);
	
	public boolean check_move(int row, int col, int player){
		if(my_Board.getElement(col, row)==0)
		{
			return my_Board.setElement(col, row, player);
		}
		return false;
	}
	
	public boolean check_winner(int row, int col, int player){
		int count=0;
		//check row
		for(int i=0; i<my_Board.getSize_H(); i++)
		{
			if(player==my_Board.getElement(i, row))
				count++;
		}
		if(count ==3)
			return true;
		//check col
		count = 0;
		for(int i=0; i<my_Board.getSize_V(); i++)
		{
			if(player==my_Board.getElement(col, i))
				count++;
		}
		if(count ==3)
			return true;
		//check diagonal
		if(col==row){
			count = 0;
			for(int i=0; i<my_Board.getSize_H(); i++)
			{
				if(player==my_Board.getElement(i, i))
					count++;
			}
			if(count ==3)
				return true;
		}		
		return false;
	}
	
	public int new_move(){
		int move=-1;
		while(move==-1){
			Random ran = new Random();
			int x = ran.nextInt(3);
			int y = ran.nextInt(3);
			if(check_move(y, x, 2))
			{
				move = y+x*10;
			}
		}
		return move;
	}
	
	public void show_board(){
		my_Board.printBoard();
	}
	/*public static void main(String [] args) throws NumberFormatException, IOException
	{
		boolean no_end = true;
		int x, y;
		Game Test_game = new Game();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(no_end){
			do{
				System.out.println("Put x");
				x = Integer.parseInt(br.readLine());
				System.out.println("Put y");
				y = Integer.parseInt(br.readLine());
				System.out.println(x +" " +y);
			}while(!Test_game.check_move(y, x, 1));
			no_end = !Test_game.check_winner(y, x, 1);
			if(no_end)
			{
				System.out.println( Test_game.new_move());
				no_end = !Test_game.check_winner(y, x, 1);
				Test_game.my_Board.printBoard();
				if(!no_end)
					System.out.println("Wins O");
			}
			else
			{
				Test_game.my_Board.printBoard();
				System.out.println("Wins X");
				
			}	
		}
	}*/
}
