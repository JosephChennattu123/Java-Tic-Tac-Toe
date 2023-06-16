package uttt.tests;
import uttt.game.*;
import uttt.utils.Symbol;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import uttt.UTTTFactory;
/* the entire template of this file was made using chatgpt. while some individual functions were made by myself they were again run thru chatgpt to get a better debugging. AGAIN THERE WAS NO WAY I COULD CHECK IF MY FILE WAS CORRECT WITHOUT USING CHATGPT.
*/
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
        MarkInterface[] r = new MarkInterface[9];
        for(int i =0;i<9;i++)
        {
        r[i] = UTTTFactory.createMark(Symbol.EMPTY, i);
        }
        board.setMarks(r);
        MarkInterface[] neueMarks = board.getMarks();
        for(int i = 0;i<9;i++)
        assertEquals(r[i],neueMarks[i]);
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
    public void TestisMovePossible(){
        board = mtboard(board);
        assertFalse(board.isClosed());
        //set 0 to empty
        board.setMarkAt(Symbol.EMPTY, 0);
        assertTrue(board.isMovePossible(0));
        //mark 0 with cross
        board.setMarkAt(Symbol.CROSS,0);
        assertFalse(board.isMovePossible(0));
    }
    @Test
    public void WinnerAndClosed()
    {
        board = mtboard(board);
        board.setMarkAt(Symbol.CROSS, 0);
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
        assertTrue(board.isClosed());
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
            assertTrue(board.isClosed());
    }
    //diagonal1
    board = mtboard(board);
    for(int i= 0;i<9;i = i+4){
        b = board.setMarkAt(Symbol.CROSS, i);
        assertTrue(b);
    }
        assertEquals(Symbol.CROSS,board.getWinner());
        assertTrue(board.isClosed());
    //diagonal2    
    board = mtboard(board);
    for(int i= 2;i<7;i = i+2){
        b = board.setMarkAt(Symbol.CROSS, i);
        assertTrue(b);
    }
    assertEquals(Symbol.CROSS,board.getWinner());
    assertTrue(board.isClosed());
    //tie setting the board to a tie state.
    board = mtboard(board);
    board = tiemaker(board);
    assertEquals(Symbol.EMPTY,board.getWinner());
    assertTrue(board.isClosed());
    //set board to an incomplete board
    board = mtboard(board);
    board.setMarkAt(Symbol.CROSS, 0);
    board.setMarkAt(Symbol.CIRCLE, 1);
    board.setMarkAt(Symbol.CROSS, 2);
    assertEquals(Symbol.EMPTY,board.getWinner());
    assertFalse(board.isClosed());
}
    public BoardInterface mtboard (BoardInterface bird)
    {
        BoardInterface newBoard =UTTTFactory.createBoard();
        for(int i =0;i<9;i++)
        {
        b = bird.setMarkAt(Symbol.EMPTY, i);
        assertTrue(b);
        }      
        return newBoard;
    }
    public BoardInterface tiemaker(BoardInterface board1)
    {
    board1 = mtboard(board1);
    board1.setMarkAt(Symbol.CROSS, 0);
    board1.setMarkAt(Symbol.CIRCLE, 1);
    board1.setMarkAt(Symbol.CROSS, 2);
    board1.setMarkAt(Symbol.CIRCLE, 3);
    board1.setMarkAt(Symbol.CROSS, 4);
    board1.setMarkAt(Symbol.CIRCLE, 5);
    board1.setMarkAt(Symbol.CIRCLE, 6);
    board1.setMarkAt(Symbol.CROSS,7);
    board1.setMarkAt(Symbol.CIRCLE,8);
    return board1;

    }
}
