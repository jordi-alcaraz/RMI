package game.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

//Our game class
public class Game {
	//Create a Board for our Game
	private Board my_Board = new Board(3, 3, 0);
	//Checks if the move is valid or not
	public boolean check_move(int row, int col, int player){
		if(my_Board.getElement(col, row)==0)
		{
			return my_Board.setElement(col, row, player);
		}
		return false;
	}
	
	//Checks if the player wins with the new move
	public boolean check_winner(int row, int col, int player){
		//Counter that checks if the value 3 is reached
		//because if it is reached, the player wins
		int count=0;
		//Check by row
		for(int i=0; i<my_Board.getSize_H(); i++)
		{
			if(player==my_Board.getElement(i, row))
				count++;
		}
		if(count ==3)
			return true;
		//Check by col,
		count = 0;
		for(int i=0; i<my_Board.getSize_V(); i++)
		{
			if(player==my_Board.getElement(col, i))
				count++;
		}
		if(count == 3)
			return true;
		//Check by diagonal
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
	
	//New move by the server
	public int new_move(){
		int move=-1;
		//Try random moves until one is valid
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
	
	//Shows the board calling the print function of the board
	public void show_board(){
		my_Board.printBoard();
	}
	
	//Gets the board
	public int[][] game_getBoard(){
		return my_Board.getBoard();
	}
}
