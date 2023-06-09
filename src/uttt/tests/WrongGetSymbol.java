package uttt.tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uttt.UTTTFactory;
import uttt.game.MarkInterface;
import uttt.game.BoardInterface;
import uttt.game.SimulatorInterface;
import uttt.utils.Symbol;

public class WrongGetSymbol {
      MarkInterface p, p1,p2;
      BoardInterface b;
      
    @Before
    public void setUp() throws Exception{
        b = UTTTFactory.createBoard();
        p = UTTTFactory.createMark(Symbol.CROSS, 5);
        p1 = UTTTFactory.createMark(Symbol.CIRCLE, 6);
        p2 = UTTTFactory.createMark(Symbol.EMPTY, 5);
    }
    
    
    @Test
    public void testGetSymbol(){
        Symbol symbol = p.getSymbol();
        assertEquals(Symbol.CROSS,symbol);
    }
    @Test
    public void testSetSymbol(){
        p.setSymbol(Symbol.CROSS);
        p1.setSymbol(Symbol.CIRCLE);
        p2.setSymbol(Symbol.EMPTY);
        Symbol symbol = p.getSymbol();
        assertEquals(Symbol.CROSS,symbol);
        Symbol symbol1 = p1.getSymbol();
        assertEquals(Symbol.CIRCLE,symbol1);
        Symbol symbol2 = p2.getSymbol();
        assertEquals(Symbol.EMPTY,symbol2);
    }
    @Test
    public void testgetPosition(){
        int r = p.getPosition();
        assertEquals(5,r)
    }
}
