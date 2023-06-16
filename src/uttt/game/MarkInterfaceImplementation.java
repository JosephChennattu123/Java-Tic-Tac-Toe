package uttt.game;

import uttt.utils.Symbol;

public class MarkInterfaceImplementation implements MarkInterface {
    private int position;
    private Symbol symbol;
    
    public MarkInterfaceImplementation(Symbol simbol, int position)
    {
        this.symbol = simbol;
        this.position = position;
    }

    @Override
    public Symbol getSymbol() {
       return this.symbol;
    }

    @Override
    public int getPosition() throws IllegalArgumentException {
        if(position<9 && position>-1)
        return this.position;
        else 
        throw new IllegalArgumentException("wrong bye bye");
    }

    @Override
    public void setSymbol(Symbol simbol) throws IllegalArgumentException {
        
        if(simbol== null)
        throw new IllegalArgumentException("null hai");
        if(simbol!= Symbol.CIRCLE || simbol!=Symbol.CROSS || simbol!=Symbol.EMPTY)
        throw new IllegalArgumentException("wrong symbol");
        this.symbol = simbol;
    }
    
}
