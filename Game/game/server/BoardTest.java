package game.server;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

	@Test
	public void test() {
		Board testBoard = new Board(3,3,1);
		testBoard.printBoard();
	}

}
