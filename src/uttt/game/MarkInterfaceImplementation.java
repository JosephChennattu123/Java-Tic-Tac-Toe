package uttt.game;

import uttt.utils.Symbol;

public class MarkInterfaceImplementation implements MarkInterface {
    private int position;
    private Symbol symbol;
    
    public MarkInterfaceImplementation(int position)
    {
        this.symbol = Symbol.EMPTY;
        this.position = position;
    }

    @Override
    public Symbol getSymbol() {
       return symbol;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setSymbol(Symbol symbol) throws IllegalArgumentException {
        this.symbol = symbol;
    }
    
}
