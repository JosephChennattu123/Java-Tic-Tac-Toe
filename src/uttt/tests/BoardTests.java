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
    MarkInterface[] p;
    p = board.getMarks();
    assertNotNull(p);
    assertEquals(9,p.length);
}   
    @Test
    public void TestsetMarks()
    {
        p = new MarkInterface[9];
        for(int i =0;i<9;i++)
        p[i].setSymbol(Symbol.CROSS);
        board.setMarks(p);
        MarkInterface[] neueMarks = board.getMarks();
        for(int i = 0;i<9;i++)
        assertEquals(p[i],neueMarks[i]);
        }
    

    @Test
    public void TestsetMarkAt(){
        assertFalse(board.isClosed());
       b = board.setMarkAt(Symbol.CIRCLE,4);
        assertTrue(b);
        MarkInterface[] marks = board.getMarks();
        assertEquals(Symbol.CIRCLE,marks[4].getSymbol());
        assertFalse(board.setMarkAt(Symbol.CROSS,4));
    }
    @Test
    public void TestisClosed(){
        
        //test when board not closed
        if(board.getWinner()==Symbol.EMPTY)
        {
        int ninja=0;
        p = new MarkInterface[9];
        p = board.getMarks();
        for(int i = 0;i<9;i++){
        if(p[i].getSymbol()==Symbol.CROSS || p[i].getSymbol()==Symbol.CIRCLE)
        ninja++;
        }
        if(ninja<9)
        assertFalse(board.isClosed());
        else
        assertTrue(board.isClosed());
        }
        if(board.getWinner()==Symbol.CROSS || board.getWinner()==Symbol.CIRCLE)
        assertTrue(board.isClosed()); 
    }
    @Test
    public void TestisMovePossible(){
        assertFalse(board.isClosed());
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
        for(int i =0;i<9;i++)
        {
        b = board.setMarkAt(Symbol.EMPTY, i);
        assertTrue(b);
        }      
        assertEquals(Symbol.EMPTY,board.getWinner());
        //Set board to winning state for CROSS
        for(int i =0;i<3;i++)
        {
        b = board.setMarkAt(Symbol.CROSS, i);
        assertTrue(b);
        }
        assertEquals(Symbol.CROSS,board.getWinner());
    }

}
