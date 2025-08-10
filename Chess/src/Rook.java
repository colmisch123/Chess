//Rook.java, Cole Mische, misch123

public class Rook {

    //Attributes
    private int row, col;
    private boolean isBlack;
    private char character = '\u2656';

    //Constructor
    public Rook(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    //Checks for move legality
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        //Seeing if the rook is moving horizontally or vertically
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) {
            if (this.row == endRow) {
                //Horizontal move
                return board.verifyHorizontal(this.row, this.col, endRow, endCol);
            }
            else{
                //Vertical move
                return board.verifyVertical(this.row, this.col, endRow, endCol);

            }
        }
        return false; //Piece is occupied by teammate, or out of board
    }
}

