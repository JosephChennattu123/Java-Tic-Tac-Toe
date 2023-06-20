package uttt.game;

import uttt.utils.Symbol;
//created by putting the entire header file into chatgpt4 premium with the prompt:
//make a logical implementation. The made some minor/logical corrections to its code

public class MarkInterfaceImplementation implements MarkInterface {

    private Symbol symbol;
    private int position;

    /**
     * Constructs a Mark object with the specified symbol and position.
     * 
     * @param symbol   The symbol to assign to the mark.
     * @param position The position of the mark on its board.
     */
    public MarkInterfaceImplementation(int position) {
        if (position < 0 || position > 8) {
            throw new IllegalArgumentException("Position must be between 0 and 8");
        }
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
        if (symbol == null) {
            throw new IllegalArgumentException("Symbol cannot be null.");
        }
        this.symbol = symbol;
    }
}
