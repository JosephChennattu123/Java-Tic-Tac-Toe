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
      MarkInterface p;
      BoardInterface b;
      
    @Before
    public void setUp() throws Exception{
        p = UTTTFactory.createmark(Symbol.CROSS, 5);
    }
    
    
    @Test
    public void testGetSymbol(){
        Symbol symbol = p.getSymbol();
        assertEquals(Symbol.CROSS,symbol);
    }
    @Test
    public void testSetSymbol(){
        p.setSymbol(Symbol.CIRCLE);
        Symbol symbol = p.getSymbol();
        assertEquals(Symbol.CIRCLE,symbol);
    }
}
