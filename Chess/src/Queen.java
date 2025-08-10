//Queen.java, Cole Mische, misch123

public class Queen {

    //Attributes
    private int row, col;
    private boolean isBlack;
    private char character = '\u2655';

    //Constructor
    public Queen(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    //Checks for move legality
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)){
            if (this.col == endCol){
                //Vertical
                return board.verifyVertical(this.row, this.col, endRow, endCol);
            }
            else if (this.row == endRow){
                //Horizontal
                return board.verifyHorizontal(this.row, this.col, endRow, endCol);
            }
            else {
                //Diagonal
                return board.verifyDiagonal(this.row, this.col, endRow, endCol);
            }
        }
        return false; //Piece is occupied by teammate, or out of board
    }
}
