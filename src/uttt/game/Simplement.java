package uttt.game;

import uttt.utils.Move;
import uttt.utils.Symbol;

public class Simplement implements SimulatorInterface {

    private BoardInterface[] boards;
    private Symbol currentPlayerSymbol;
    private int indexNextBoard;

    public Simplement(BoardInterface[] boards, Symbol currentPlayerSymbol, int indexNextBoard) {
        this.boards = boards;
        this.currentPlayerSymbol = currentPlayerSymbol;
        this.indexNextBoard = indexNextBoard;
    }

    @Override
    public BoardInterface[] getBoards() {
        return boards;
    }

    @Override
    public void setBoards(BoardInterface[] boards) throws IllegalArgumentException {
        if (boards == null) {
            throw new IllegalArgumentException("Boards cannot be null.");
        }
        this.boards = boards;
    }

    @Override
    public Symbol getCurrentPlayerSymbol() {
        return currentPlayerSymbol;
    }

    @Override
    public void setCurrentPlayerSymbol(Symbol symbol) throws IllegalArgumentException {
        if (symbol == null) {
            throw new IllegalArgumentException("Symbol cannot be null.");
        }
        this.currentPlayerSymbol = symbol;
    }

    @Override
    public boolean setMarkAt(Symbol symbol, int boardIndex, int markIndex) throws IllegalArgumentException {
        if (symbol == null) {
            throw new IllegalArgumentException("Symbol cannot be null.");
        }

        if (boardIndex < 0 || boardIndex >= 9) {
            throw new IllegalArgumentException("Invalid board index.");
        }

        BoardInterface board = boards[boardIndex];
        if (board.isMovePossible(markIndex)) {
            board.setMarkAt(symbol, markIndex);
            indexNextBoard = markIndex;
            return true;
        }

        return false;
    }

    @Override
    public int getIndexNextBoard() {
        return indexNextBoard;
    }

    @Override
    public void setIndexNextBoard(int index) throws IllegalArgumentException {
        if (index < -1 || index >= boards.length) {
            throw new IllegalArgumentException("Invalid index for the next board.");
        }
        indexNextBoard = index;
    }

    @Override
    public boolean isGameOver() {
        if (getWinner() != Symbol.EMPTY) {
            return true;
        }

        for (BoardInterface board : boards) {
            if (!board.isClosed()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean isMovePossible(int boardIndex) throws IllegalArgumentException {
        if (boardIndex < 0 || boardIndex >= boards.length) {
            throw new IllegalArgumentException("Invalid board index.");
        }

        if (indexNextBoard != -1 && indexNextBoard != boardIndex) {
            return false;
        }

        return !boards[boardIndex].isClosed();
    }

    @Override
    public boolean isMovePossible(int boardIndex, int markIndex) throws IllegalArgumentException {
        if (boardIndex < 0 || boardIndex >= boards.length) {
            throw new IllegalArgumentException("Invalid board index.");
        }

        if (indexNextBoard != -1 && indexNextBoard != boardIndex) {
            return false;
        }

        return boards[boardIndex].isMovePossible(markIndex);
    }

    @Override
    public Symbol getWinner() {
        for (BoardInterface board : boards) {
            Symbol winner = board.getWinner();
            if (winner != Symbol.EMPTY) {
                return winner;
            }
        }
        return Symbol.EMPTY;
    }

   
    @Override
public void run(PlayerInterface playerOne, PlayerInterface playerTwo, UserInterface ui)
        throws IllegalArgumentException {
    if (playerOne == null || playerTwo == null) {
        throw new IllegalArgumentException("Players cannot be null.");
    }

    Symbol currentPlayerSymbol = playerOne.getSymbol();
    boolean gameOver = false;

    while (!gameOver) {
        Move playerMove;
        if (currentPlayerSymbol == playerOne.getSymbol()) {
            playerMove = playerOne.getPlayerMove(this, ui);
        } else {
            playerMove = playerTwo.getPlayerMove(this, ui);
        }

        int boardIndex = playerMove.getBoardIndex();
        int markIndex = playerMove.getMarkIndex();

        if (isMovePossible(boardIndex, markIndex)) {
            setMarkAt(currentPlayerSymbol, boardIndex, markIndex);
            ui.updateScreen(this);

            if (isBoardWon(boardIndex)) {
                int nextBoardIndex = chooseNextBoardIndex();
                setIndexNextBoard(nextBoardIndex);
            } else if (isBoardFull(boardIndex)) {
                setIndexNextBoard(-1);
            } else {
                setIndexNextBoard(markIndex);
            }

            if (isGameOver()) {
                Symbol winner = getWinner();
                ui.showGameOverScreen(winner);
                gameOver = true;
            }

            currentPlayerSymbol = (currentPlayerSymbol == playerOne.getSymbol()) ? playerTwo.getSymbol()
                    : playerOne.getSymbol();
        }
    }
}

/**
 * Checks if a board is won.
 *
 * @param boardIndex The index of the board to check.
 * @return True if the board is won, false otherwise.
 */
private boolean isBoardWon(int boardIndex) {
    BoardInterface board = boards[boardIndex];
    return board.getWinner() != Symbol.EMPTY;
}

/**
 * Checks if a board is full.
 *
 * @param boardIndex The index of the board to check.
 * @return True if the board is full, false otherwise.
 */
private boolean isBoardFull(int boardIndex) {
    BoardInterface board = boards[boardIndex];
    return board.isClosed();
}

/**
 * Chooses the index of the next board based on the rules of Ultimate Tic Tac Toe.
 *
 * @return The index of the next board.
 */
private int chooseNextBoardIndex() {
    int nextBoardIndex = getIndexNextBoard();
    if (nextBoardIndex != -1 && !isBoardFull(nextBoardIndex)) {
        return nextBoardIndex;
    }

    for (int i = 0; i < boards.length; i++) {
        if (!isBoardWon(i) && !isBoardFull(i)) {
            return i;
        }
    }

    return -1;
}

}
