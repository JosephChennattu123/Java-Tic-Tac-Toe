package uttt.tests;
import uttt.game.*;
import uttt.utils.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import uttt.utils.Symbol;
import uttt.UTTTFactory;

public class SimulatorTests {

     SimulatorInterface simulator;
     BoardInterface board;

    @Before
    public void setUp() {
     simulator = UTTTFactory.createSimulator(); 
     
    }

    @Test
    public void testGetBoards() {
        BoardInterface[] boards = simulator.getBoards();
        assertNotNull(boards);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetBoards() {
        BoardInterface[] boards = simulator.getBoards();
        simulator.setBoards(boards);
        BoardInterface[] check = simulator.getBoards();
        assertNotNull(check);
        for(int i =0;i<9;i++)
        assertEquals(boards[i], check[i]);
    }

    @Test
    public void testGetCurrentPlayerSymbol() {
        Symbol symbol = simulator.getCurrentPlayerSymbol();
        assertNotNull(symbol);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCurrentPlayerSymbol() {
        simulator.setCurrentPlayerSymbol(Symbol.CROSS); // replace with the test symbol
        assertEquals(Symbol.CROSS, simulator.getCurrentPlayerSymbol());
    }

    @Test
    public void testSetMarkAt() {
        assertTrue(simulator.setMarkAt(Symbol.CROSS, 0, 0)); 
    }

    @Test
    public void testGetIndexNextBoard() {
        int index = simulator.getIndexNextBoard();
        assertTrue(index >= -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetIndexNextBoard() {
        simulator.setIndexNextBoard(0); 
        assertEquals(0, simulator.getIndexNextBoard());
    }

    @Test
    public void testIsGameOver() {
        assertFalse(simulator.isGameOver());
        // Additional tests when game state changes
    }

    @Test
    public void testIsMovePossible_boardIndex() {
        assertTrue(simulator.isMovePossible(0)); 
        
    }

    @Test
    public void testIsMovePossible_boardIndex_markIndex() {
        assertTrue(simulator.isMovePossible(0, 0)); 
    }

    @Test
    public void testGetWinner() {

        assertEquals(Symbol.EMPTY, simulator.getWinner());
        // Additional tests when game state changes
        BoardInterface[] boards = simulator.getBoards();
        BoardInterface baba;       
        for(int i = 0;i<9;i++)
        {
            baba = boards[i];
            for(int j =0;j<9;j++)
        {
        boolean b = baba.setMarkAt(Symbol.EMPTY, i);
        assertTrue(b);
        }   
            boolean k1;
            k1 = baba.setMarkAt(Symbol.CROSS,0) && baba.setMarkAt(Symbol.CROSS,1) && baba.setMarkAt(Symbol.CROSS,2);
            assertTrue(k1);            
        }
        assertEquals(Symbol.CROSS,simulator.getWinner());
        }

    
}
