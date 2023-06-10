package uttt.tests;
import uttt.game.*;
import uttt.utils.Symbol;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import uttt.UTTTFactory;

public class BoardTests {
    MarkInterface[] p;
    boolean b;
    BoardInterface board;

    @Before
    public void setUp() throws Exception {
        board = UTTTFactory.createBoard();
    
    }

    @Test
    public void TestgetMarks()
{
    p= board.getMarks();
    assertNotNull(p);
    assertEquals(81,p.length);
}   
    @Test
    public void TestsetMarks()
    {
        p = new MarkInterface[81];
        board.setMarks(p);
        MarkInterface[] neueMarks = board.getMarks();
        assertArrayEquals(p,neueMarks);
        }
    

    @Test
    public void TestsetMarkAt(){
       b = board.setMarkAt(Symbol.CIRCLE,4);
        assertTrue(b);
        MarkInterface[] marks = board.getMarks();
        assertEquals(Symbol.CIRCLE,marks[4].getSymbol());
    }
    @Test
    public void TestisClosed(){
        
        //test when board not closed
        assertFalse(board.isClosed());
        //set board to a closed state
        assertTrue(board.isClosed());
        

    }
    @Test
    public void TestisMovePossible(){
        //set 0 to empty
        board.setMarkAt(Symbol.EMPTY, 0);
        assertTrue(board.isMovePossible(0));
        //mark 0 with cross
        board.setMarkAt(Symbol.CROSS,0);
        assertFalse(board.isMovePossible(0));
    }
    @Test
    public void TestgetWinner()
    {
        p = new MarkInterface[9];          
        board.setMarks(p);
        assertEquals(Symbol.EMPTY,board.getWinner());
        //Set board to winning state for CROSS
        for(int i =0;i<9;i++)
        {
        b = board.setMarkAt(Symbol.CROSS, i);
        assertTrue(b);
        }
        assertEquals(Symbol.CROSS,board.getWinner());
    }

}
