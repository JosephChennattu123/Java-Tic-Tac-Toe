package uttt.tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uttt.UTTTFactory;
import uttt.game.MarkInterface;
import uttt.game.BoardInterface;
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
        p.setSymbol(Symbol.EMPTY);
        try{
            p.setSymbol(Symbol.CROSS);
        }catch(Exception e)
        {
            fail("yeh idhar kya kar rha hai?");
        }

    }
    @Test
    public void testgetPosition(){
        int r = p.getPosition();
        assertEquals(5,r);
    }
    
}
