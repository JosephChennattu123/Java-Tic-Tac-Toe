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
        assertEquals(9,boards.length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetBoards() {
        simulator = simer(simulator);
        BoardInterface[] boards = simulator.getBoards();
        simulator.setBoards(boards);
        BoardInterface[] check = simulator.getBoards();
        assertNotNull(check);
        for(int i =0;i<9;i++)
        assertEquals(boards[i], check[i]);
    }

    @Test
    public void testGetCurrentPlayerSymbol() {
        simulator = simer(simulator);
        simulator.setCurrentPlayerSymbol(Symbol.CROSS);
        Symbol symbol = simulator.getCurrentPlayerSymbol();
        assertNotNull(symbol);
        assertEquals(symbol, Symbol.CROSS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCurrentPlayerSymbol() {
       simulator = simer(simulator);
       boolean b = simulator.setMarkAt(Symbol.CIRCLE, 0, 0);
       assertTrue(b);
       assertEquals(Symbol.CROSS, simulator.getCurrentPlayerSymbol());
    }

    @Test
    public void testSetMarkAt() { 
        simulator = simer(simulator);
        assertTrue(simulator.setMarkAt(simulator.getCurrentPlayerSymbol(), 0, 0)); 
    }

    @Test
    public void testGetIndexNextBoard() {
        //basic true case
        simulator = simer(simulator);
        simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
        boolean b = simulator.setMarkAt(Symbol.CIRCLE, 0, 0);
        assertTrue(b);
        int bindex = simulator.getIndexNextBoard();
        assertTrue(bindex<9);
        assertTrue(bindex>-1);
        assertEquals(bindex, 0);
        //check if returning -1 to a closed board
        simulator = simer(simulator);
        simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
        simulator.setIndexNextBoard(0);
        simulator.setMarkAt(Symbol.CIRCLE, 0, 3);
        simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
        simulator.setIndexNextBoard(0);
        simulator.setMarkAt(Symbol.CIRCLE, 0, 2);
        simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
        simulator.setIndexNextBoard(0);
        simulator.setMarkAt(Symbol.CIRCLE, 0, 1);
        assertEquals(simulator.getIndexNextBoard(), -1);
        //all boards closed
        simulator = simer(simulator);
        for(int i = 0;i<9;i++)
        {
            for(int j = 0;j<3;j++){
            simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
            simulator.setIndexNextBoard(i);
            simulator.setMarkAt(Symbol.CIRCLE, i, j);
            }
        }
        int lawda = simulator.getIndexNextBoard();
        assertFalse(simulator.isMovePossible(lawda));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetIndexNextBoard() {
        simulator.setIndexNextBoard(0); 
        assertEquals(0, simulator.getIndexNextBoard());
    }

    @Test
    public void testIsGameOver() {
        int p =0;
        BoardInterface[] boards = simulator.getBoards();

        for(int i = 0;i<9;i++){
        if(boards[i].isClosed()==false)
        p = 1;
        }
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
public SimulatorInterface simer (SimulatorInterface simulator)
{
    BoardInterface[] boards = simulator.getBoards();
    for(int i = 0;i<9;i++)
    {
        for(int j = 0;j<9;j++)
        {
            boards[i].setMarkAt(Symbol.EMPTY, 9);
        }
    }
    return simulator;
}
    
}
