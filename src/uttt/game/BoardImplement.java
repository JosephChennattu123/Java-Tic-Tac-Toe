package uttt.game;

import uttt.utils.Symbol;

public class BoardImplement implements BoardInterface {
    
    private MarkInterface[] marks = new MarkInterface[9]; // assuming a 3x3 board
    public BoardImplement() {
        // Initialize all marks as empty
        for (int i = 0; i < 9; i++) {
            marks[i] = new MarkInterfaceImplementation(i);
        }
    }
    
    @Override
    public MarkInterface[] getMarks() {
        return marks;
    }

    @Override
    public void setMarks(MarkInterface[] newMarks) throws IllegalArgumentException {
        if(newMarks.length != 9) {
            throw new IllegalArgumentException("Wrong number of marks, expected 9.");
        }
        marks = newMarks;
    }

    @Override
    public boolean setMarkAt(Symbol symbol, int markIndex) throws IllegalArgumentException {
        if(markIndex < 0 || markIndex > 8) {
            throw new IllegalArgumentException("Mark index out of bounds, should be between 0 and 8.");
        }
        if(marks[markIndex].getSymbol() != Symbol.EMPTY) {
            return false; // mark already set
        }
        marks[markIndex].setSymbol(symbol);
        return true;
    }

    @Override
    public boolean isClosed() {
        //rows
        for(int i =0;i<3;i++)
        {           
                if (marks[3*i].getSymbol() == marks[3*i+1].getSymbol() && marks[3*i].getSymbol() == marks[3*i+2].getSymbol() && marks[3*i].getSymbol()!=Symbol.EMPTY)
                return true;
        }
        //columns
        for(int i =0;i<3;i++)
        {           
                if (marks[i].getSymbol() == marks[i+3].getSymbol() && marks[i].getSymbol() == marks[i+6].getSymbol() && marks[i].getSymbol()!=Symbol.EMPTY)
                return true;
        }
        //diagonal 1 &2                 
        if (marks[0].getSymbol() == marks[4].getSymbol() && marks[0].getSymbol() == marks[8].getSymbol() && marks[0].getSymbol()!=Symbol.EMPTY)
         return true;
        if (marks[2].getSymbol() == marks[4].getSymbol() && marks[2].getSymbol() == marks[6].getSymbol()&& marks[2].getSymbol()!=Symbol.EMPTY)
         return true; 
         //tied board
         int check = 0;
         for(int i =0;i<9;i++){
         if(marks[i].getSymbol()==Symbol.EMPTY)
         check++;
         }
         if(check==0)
         return true;
        return false;
    }

    @Override
    public boolean isMovePossible(int markIndex) throws IllegalArgumentException {
        if(markIndex < 0 || markIndex > 8) {
            throw new IllegalArgumentException("Mark index out of bounds, should be between 0 and 8.");
        }
        return marks[markIndex].getSymbol() == Symbol.EMPTY;
    }

    @Override
    public Symbol getWinner() {
        //rows
        for(int i =0;i<3;i++)
        {           
                if (marks[3*i].getSymbol() == marks[3*i+1].getSymbol() && marks[3*i].getSymbol() == marks[3*i+2].getSymbol() && marks[3*i].getSymbol()!=Symbol.EMPTY)
                return marks[3*i].getSymbol();
        }
        //columns
        for(int i =0;i<3;i++)
        {           
                if (marks[i].getSymbol() == marks[i+3].getSymbol() && marks[i].getSymbol() == marks[i+6].getSymbol() && marks[i].getSymbol()!=Symbol.EMPTY)
                return marks[i].getSymbol();
        }
        //diagonal 1 &2                 
        if (marks[0].getSymbol() == marks[4].getSymbol() && marks[0].getSymbol() == marks[8].getSymbol() && marks[0].getSymbol()!=Symbol.EMPTY)
         return marks[0].getSymbol();
        if (marks[2].getSymbol() == marks[4].getSymbol() && marks[2].getSymbol() == marks[6].getSymbol()&& marks[2].getSymbol()!=Symbol.EMPTY)
         return marks[2].getSymbol(); 
         //tied board
         /* should be egal
         int check = 0;
         for(int i =0;i<9;i++){
         if(marks[i].getSymbol()==Symbol.EMPTY)
         check++;
         }
         if(check==0)
         return Symbol.EMPTY;
         */
        return Symbol.EMPTY;
        

    }

    @Override
    public void run(PlayerInterface playerOne, PlayerInterface playerTwo, UserInterface ui) throws IllegalArgumentException {
        // do nothing
    }
}
