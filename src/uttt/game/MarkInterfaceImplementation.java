package uttt.game;

import uttt.utils.Symbol;

public class MarkInterfaceImplementation implements MarkInterface {

    private Symbol symbol;
    private int position;

    /**
     * Constructs a Mark object with the specified symbol and position.
     * 
     * @param symbol   The symbol to assign to the mark.
     * @param position The position of the mark on its board.
     */
    public MarkInterfaceImplementation(Symbol symbol, int position) {
        this.symbol = symbol;
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
        if (symbol == null) {
            throw new IllegalArgumentException("Symbol cannot be null.");
        }
        this.symbol = symbol;
    }
}
