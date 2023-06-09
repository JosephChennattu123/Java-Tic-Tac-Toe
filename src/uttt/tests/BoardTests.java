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
    public void testSetMarkAt() {
        b = board.setMarkAt(Symbol.CIRCLE, 4);
        assertTrue(b);
        MarkInterface[] marks = board.getMarks();
        assertEquals(Symbol.CIRCLE, marks[4].getSymbol());
    }

    @Test
    public void testIsClosed() {
        // test when the board is not closed
        assertFalse(board.isClosed());

        // ... Set the board to a closed state

        // test when the board is closed
        assertTrue(board.isClosed());
    }

    @Test
    public void testIsMovePossible() {
        // set 0 to empty
        board.setMarkAt(Symbol.EMPTY, 0);
        assertTrue(board.isMovePossible(0));

        // mark 0 with cross
        board.setMarkAt(Symbol.CROSS, 0);
        assertFalse(board.isMovePossible(0));
    }

    @Test
    public void testGetWinner() {
        p = new MarkInterface[81];
        board.setMarks(p);
        assertEquals(Symbol.EMPTY, board.getWinner());

        // Set the board to a winning state for CROSS
        for (int i = 0; i < 81; i++) {
            b = board.setMarkAt(Symbol.CROSS, i);
            assertTrue(b);
        }
        assertEquals(Symbol.CROSS, board.getWinner());
    }

}
