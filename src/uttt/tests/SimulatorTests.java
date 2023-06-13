package uttt.tests;
import uttt.game.*;
import uttt.utils.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import uttt.utils.Symbol;
import uttt.UTTTFactory;
import uttt.tests.BoardTests;

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

    @Test
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

    @Test
    public void testSetCurrentPlayerSymbol() {
       simulator = simer(simulator);
       simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
       Symbol sss= simulator.getCurrentPlayerSymbol();
       assertNotNull(sss);
       assertEquals(Symbol.CIRCLE,sss);
    }

    @Test
    public void testSetCurrentPlayerSymbol_InvalidSymbol() {
        Symbol invalidSymbol = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            simulator.setCurrentPlayerSymbol(invalidSymbol);
        });

        assertEquals("Invalid symbol", exception.getMessage());
    }

    @Test
    public void testSetMarkAt() { 
        simulator = simer(simulator);
        simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
        assertTrue(simulator.setMarkAt(simulator.getCurrentPlayerSymbol(), 0, 0)); 
        //check if overwrite not allowed
        simulator.setCurrentPlayerSymbol(Symbol.CROSS);
        assertFalse(simulator.setMarkAt(simulator.getCurrentPlayerSymbol(), 0, 0)); 
        //exception
        simulator = simer(simulator);
        Symbol q = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            simulator.setMarkAt(q, 0,0);
        });
        assertEquals("Invalid symbol", exception.getMessage());
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
        assertFalse(simulator.isGameOver());
        //check if returning -1 to a closed board
        simulator = simer(simulator);
        simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
        simulator.setIndexNextBoard(0);
        simulator.setMarkAt(Symbol.CIRCLE, 0, 2);
        simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
        simulator.setIndexNextBoard(0);
        simulator.setMarkAt(Symbol.CIRCLE, 0, 1);
        simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
        simulator.setIndexNextBoard(0);
        simulator.setMarkAt(Symbol.CIRCLE, 0, 0);
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
        assertNull(lawda);
        }

    @Test(expected = IllegalArgumentException.class)
    public void testSetIndexNextBoard() {
        simulator.setIndexNextBoard(99); 
        assertEquals(99, simulator.getIndexNextBoard());
    }
    @Test
    public void testSetIndexNextBoard1() {
        simulator.setIndexNextBoard(0); 
        assertEquals(0, simulator.getIndexNextBoard());
    }
    @Test
    public void testIsMovePossible_BoardIndex() {
        simulator = simer(simulator);
        int boardIndex = 0;
        simulator.setCurrentPlayerSymbol(Symbol.CROSS);        // Call the method and assert the return value
        simulator.setMarkAt(Symbol.CROSS, 0, 0);
        boolean result = simulator.isMovePossible(boardIndex);
        assertEquals(true, result);
    }

    @Test
    public void testIsMovePossible_InvalidBoardIndex() {
        simulator = simer(simulator);
        int boardIndex = 0;
        simulator.setCurrentPlayerSymbol(Symbol.CROSS);        // Call the method and assert the return value
        simulator.setMarkAt(Symbol.CROSS, 0, 0)
        int invalidBoardIndex = -1;

        // Use assertThrows to check for IllegalArgumentException
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            simulator.isMovePossible(invalidBoardIndex);
        });

        assertEquals("Invalid board index", exception.getMessage());
    }

    @Test
    public void testIsMovePossible_BoardIndexAndMarkIndex() {
        simulator = simer(simulator);
        simulator.setCurrentPlayerSymbol(Symbol.CROSS);        // Call the method and assert the return value
        simulator.setMarkAt(Symbol.CROSS, 0, 0);
        int boardIndex = 0;
        int markIndex = 1;     

        // Call the method and assert the return value
        boolean result = simulator.isMovePossible(boardIndex, markIndex);
        assertEquals(true, result);
    }

    @Test
    public void testIsMovePossible_InvalidBoardIndexAndMarkIndex() {
        // Perform necessary setup steps before the test case
        simulator = simer(simulator);
        simulator.setCurrentPlayerSymbol(Symbol.CROSS);        // Call the method and assert the return value
        simulator.setMarkAt(Symbol.CROSS, 0, 0);
        int invalidBoardIndex = -1;
        int markIndex = 0;
        simulator.setCurrentPlayerSymbol(Symbol.CROSS);        
        simulator.setMarkAt(Symbol.CROSS, 0, 0);
        // Use assertThrows to check for IllegalArgumentException
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            simulator.isMovePossible(invalidBoardIndex, markIndex);
        });

        assertEquals("Invalid board index", exception.getMessage());
    }

    @Test
    public void testIsMovePossible_BoardIndexAndInvalidMarkIndex() {
        // Perform necessary setup steps before the test case
        simulator = simer(simulator);
        simulator.setCurrentPlayerSymbol(Symbol.CROSS);        // Call the method and assert the return value
        simulator.setMarkAt(Symbol.CROSS, 0, 0);
        int boardIndex = 0;
        int invalidMarkIndex = -1;

        // Use assertThrows to check for IllegalArgumentException
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            simulator.isMovePossible(boardIndex, invalidMarkIndex);
        });

        assertEquals("Invalid mark index", exception.getMessage());
    }
}
    @Test
    public void testGetWinner() {
        simulator = simer(simulator);
        assertEquals(Symbol.EMPTY, simulator.getWinner());
        BoardInterface[] boards = simulator.getBoards();
        BoardInterface baba;       
        for(int i = 0;i<9;i++)
        {
            baba = boards[i];
            for(int j =0;j<3;j++)
        {
        boolean b = baba.setMarkAt(Symbol.EMPTY, j);
        assertTrue(b);
        }   
            boolean k1;
            k1 = baba.setMarkAt(Symbol.CROSS,0) && baba.setMarkAt(Symbol.CROSS,1) && baba.setMarkAt(Symbol.CROSS,2);
            assertTrue(k1);            
        }
        assertEquals(Symbol.CROSS,simulator.getWinner());
        assertTrue(simulator.isGameOver());
        //columns
        simulator = simer(simulator);
        boards = simulator.getBoards();
        for(int i = 0;i<9;i++)
        {
            baba = boards[i];
            for(int j =0;j<7;j=j+3)
        {
        boolean b = baba.setMarkAt(Symbol.EMPTY, j);
        assertTrue(b);
        }   
            boolean k1;
            k1 = baba.setMarkAt(Symbol.CROSS,0) && baba.setMarkAt(Symbol.CROSS,1) && baba.setMarkAt(Symbol.CROSS,2);
            assertTrue(k1);            
        }
        assertEquals(Symbol.CROSS,simulator.getWinner());
        assertTrue(simulator.isGameOver());
        //diagonal1
        simulator = simer(simulator);
        boards = simulator.getBoards();
        for(int i = 0;i<9;i++)
        {
            baba = boards[i];
            for(int j =0;j<9;j=j+4)
        {
        boolean b = baba.setMarkAt(Symbol.EMPTY, j);
        assertTrue(b);
        }   
            boolean k1;
            k1 = baba.setMarkAt(Symbol.CROSS,0) && baba.setMarkAt(Symbol.CROSS,1) && baba.setMarkAt(Symbol.CROSS,2);
            assertTrue(k1);            
        }
        assertEquals(Symbol.CROSS,simulator.getWinner());
        assertTrue(simulator.isGameOver());
        //diagonal2
        simulator = simer(simulator);
        boards = simulator.getBoards();
        for(int i = 0;i<9;i++)
        {
            baba = boards[i];
            for(int j =2;j<7;j=j+2)
        {
        boolean b = baba.setMarkAt(Symbol.EMPTY, j);
        assertTrue(b);
        }   
            boolean k1;
            k1 = baba.setMarkAt(Symbol.CROSS,0) && baba.setMarkAt(Symbol.CROSS,1) && baba.setMarkAt(Symbol.CROSS,2);
            assertTrue(k1);            
        }
        assertEquals(Symbol.CROSS,simulator.getWinner());
        assertTrue(simulator.isGameOver());
        //tier
        simulator = simer(simulator);
        boards = simulator.getBoards();
        for(int i = 0;i<9;i++)
        {
            baba = boards[i];
            boards[i] = tiemaker(baba);      
        }
        assertEquals(Symbol.EMPTY,simulator.getWinner());
        assertTrue(simulator.isGameOver());
        }
public SimulatorInterface simer (SimulatorInterface simulator)
{
    BoardInterface[] boards = simulator.getBoards();
    for(int i = 0;i<9;i++)
    {
        boards[i] = UTTTFactory.createBoard();
        for(int j = 0;j<9;j++)
        {
            boards[i].setMarkAt(Symbol.EMPTY, j);
        }
    }
    return simulator;
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
    public BoardInterface mtboard (BoardInterface bird)
    {
        boolean b;
        BoardInterface newBoard =UTTTFactory.createBoard();
        for(int i =0;i<9;i++)
        {
        b = bird.setMarkAt(Symbol.EMPTY, i);
        assertTrue(b);
        }      
        return newBoard;
    }
    
}
