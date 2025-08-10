//Bishop.java, Cole Mische, misch123

public class Bishop {

    //Attributes
    private int row, col;
    private boolean isBlack;
    private char character = '\u2657';

    //Constructor
    public Bishop(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    //Checks for move legality
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)){
            return board.verifyDiagonal(this.row, this.col, endRow, endCol);
        }
        return false; //Piece is occupied by teammate, or out of board
    }
}

