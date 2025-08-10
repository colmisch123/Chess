//Knight.java, Cole Mische, misch123

public class Knight {

    //Attributes
    private int row, col;
    private boolean isBlack;
    private char character = '\u2658';

    //Constructor
    public Knight(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    //Checks for move legality
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) {
            int rowDiff = Math.abs(this.row - endRow);
            int colDiff = Math.abs(this.col - endCol);
            //Checking for Knight's movement shape
            if ((rowDiff == 1 && colDiff == 2) || (rowDiff == 2 && colDiff == 1)) {
                // Check if the destination square is either empty or occupied by an opponent's piece
                return board.getPiece(endRow, endCol) == null || board.getPiece(endRow, endCol).getIsBlack() != this.isBlack;
            }
        }
        return false; //Piece is occupied by teammate, or out of board
    }


}
