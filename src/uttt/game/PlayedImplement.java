package uttt.game;

import uttt.utils.Move;
import uttt.utils.Symbol;

public class PlayedImplement implements PlayerInterface {

    private Symbol symbol;

    public PlayedImplement(Symbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public Move getPlayerMove(SimulatorInterface game, UserInterface ui) throws IllegalArgumentException {
        if (game == null) {
            throw new IllegalArgumentException("Game cannot be null.");
        }

        if (ui != null) {
            // Human player
            Move mover = ui.getUserMove();
            return mover;
        } 
        else{
            //random shit
            //ai implementation will go here
        }
        
        return null;
    }
}
