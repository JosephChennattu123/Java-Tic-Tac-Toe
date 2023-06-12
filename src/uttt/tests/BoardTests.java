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
    public void TestgetMarks ()
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
        {
        p[i].setSymbol(Symbol.CROSS);
        }
        board.setMarks(p);
        MarkInterface[] neueMarks = board.getMarks();
        for(int i = 0;i<9;i++)
        assertEquals(p[i],neueMarks[i]);
        }
    

    @Test
    public void TestsetMarkAt() throws Exception{
        assertFalse(board.isClosed());
        board= mtboard(board);
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
        board = mtboard(board);
        assertEquals(Symbol.EMPTY,board.getWinner());
        //Set board to winning state for CROSS
    //rows
        for(int j=0;j<3;j++){
        board = mtboard(board);
        for(int i =0;i<3;i++)
        {
        b = board.setMarkAt(Symbol.CROSS, (3*j)+i);
        assertTrue(b);
        }
        assertEquals(Symbol.CROSS,board.getWinner());
    }
    //columns
        for(int j=0;j<3;j++){
            board = mtboard(board);
            for(int i =0;i<3;i++)
            {
            b = board.setMarkAt(Symbol.CROSS, (3*i)+j);
            assertTrue(b);
            }
            assertEquals(Symbol.CROSS,board.getWinner());
    }
    //diagonal1
    board = mtboard(board);
    for(int i= 0;i<9;i = i+4){
        b = board.setMarkAt(Symbol.CROSS, i);
        assertTrue(b);
    }
        assertEquals(Symbol.CROSS,board.getWinner());
    //diagonal2    
    board = mtboard(board);
    for(int i= 2;i<7;i = i+2){
        b = board.setMarkAt(Symbol.CROSS, i);
        assertTrue(b);
    }
    assertEquals(Symbol.CROSS,board.getWinner());
}
    public BoardInterface mtboard (BoardInterface bird)
    {
        for(int i =0;i<9;i++)
        {
        b = bird.setMarkAt(Symbol.EMPTY, i);
        assertTrue(b);
        }      
        return bird;
    }
}
