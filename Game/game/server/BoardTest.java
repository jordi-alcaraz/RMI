package game.server;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

	@Test
	public void testGet() {
		Board testBoard = new Board(3,3,1);
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				assertTrue(testBoard.getElement(i,j)== 1);
		for(int i=3; i<10; i++)
			for(int j=0; j<10; j++)
				assertTrue(testBoard.getElement(i,j)== -1);
	}

}
