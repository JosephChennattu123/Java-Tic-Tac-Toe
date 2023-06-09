package uttt.tests;
import uttt.game.*;
import uttt.utils.Symbol;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import junit.*;
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
        /*/
        //test when board not closed
        assertFalse(board.isClosed());
        //set board to a closed state
        assertTrue(board.isClosed());
        */

    }
    @Test
    public void TestisMovePossible(){
        
    }
    @Test
    public void TestgetWinner()
    {

    }
}
