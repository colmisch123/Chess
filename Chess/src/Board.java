//Board.java Cole Mische, misch123
public class Board {
    // difference is the isGameOver

    // Instance variables
    private Piece[][] board;

    public Piece[][] getBoard(){
        return board;
    }


    //TODO:
    // Construct an object of type Board using given arguments.
    public Board() {
        board = new Piece[8][8];
    }

    // Accessor Methods

    //TODO:
    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    //TODO:
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;

    }

    // Game functionality methods

    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    // This method calls all necessary helper functions to determine if a move
    // is legal, and to execute the move if it is. Your Game class should not
    // directly call any other method of this class.
    // Hint: this method should call isMoveLegal() on the starting piece.
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        if (board[startRow][startCol].isMoveLegal(this, endRow, endCol) && board[startRow][startCol] != null) {
            // branch to move the piece to new row,col
            setPiece(endRow, endCol, getPiece(startRow, startCol));
            board[startRow][startCol].setPosition(endRow, endCol);
            board[startRow][startCol] = null;


            return true;
        } else {
            return false;
        }
    }

    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        boolean whiteKingFound = false;
        boolean blackKingFound = false;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
                if (piece != null) {
                    char character = piece.getCharacter();
                    if (character == '♔') {
                        whiteKingFound = true;
                    } else if (character == '♚') {
                        blackKingFound = true;
                    }
                }
            }
        }
        return !(whiteKingFound && blackKingFound);
    }


    // Constructs a String that represents the Board object's 2D array.
    // Returns the fully constructed String.
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for (int i = 0; i < 8; i++) {
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for (int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for (int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2003|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }

    //TODO:
    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = null;
            }
        }
    }


    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        // Check if 'start' and 'end' positions fall within the array's bounds
        if (startRow >= 0 && startRow < 8 && startCol >= 0 && startCol < 8 &&
                endRow >= 0 && endRow < 8 && endCol >= 0 && endCol < 8) {
            if (getPiece(startRow, startCol) != null) {
                if (getPiece(startRow, startCol).getIsBlack() == isBlack) {
                    // Check if end contains either no Piece or a Piece of the opposite color
                    if (getPiece(endRow, endCol) == null || getPiece(endRow, endCol).getIsBlack() != isBlack) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other
    // Like a king
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        int rowDiff = Math.abs(startRow - endRow);
        int colDiff = Math.abs(startCol - endCol);

        // Check if the move is adjacent, meaning it's one square away in either direction
        return rowDiff <= 1 && colDiff <= 1;
    }


    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow && startCol == endCol) {
            return true; // Move is from a position to itself
        }

        if (startRow == endRow) {
            int colDiff = Math.abs(startCol - endCol);
            if (colDiff > 0) {
                int colStep = (startCol < endCol) ? 1 : -1;
                // Check if all spaces directly between start and end are null
                for (int col = startCol + colStep; col != endCol; col += colStep) {
                    if (getPiece(startRow, col) != null) {
                        return false; // There is a piece in the way
                    }
                }

                return true; // Horizontal move is valid
            }
        }

        return false; // Move is not a valid horizontal move
    }



    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.

    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        // Check if the entire move takes place on one column
        if (startRow == endRow && startCol == endCol){
            return true;
        }

        if (startCol == endCol) {
            int rowDiff = Math.abs(startRow - endRow);

            if (rowDiff > 0) {
                int rowStep = (startRow < endRow) ? 1 : -1;

                // Check if all spaces directly between start and end are null
                for (int row = startRow + rowStep; row != endRow; row += rowStep) {
                    if (getPiece(row, startCol) != null) {
                        return false; // invalid move
                    }
                }

                return true; // valid move
            }
        }

        return false; // not valid move
    }
















    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.

    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        int rowDiff = Math.abs(startRow - endRow);
        int colDiff = Math.abs(startCol - endCol);

        if (rowDiff == colDiff) {
            // Determine the direction of the move
            int rowStep = (startRow < endRow) ? 1 : (startRow > endRow) ? -1 : 0;
            int colStep = (startCol < endCol) ? 1 : (startCol > endCol) ? -1 : 0;

            // Check if all spaces directly between 'start' and 'end' are empty (null)
            for (int row = startRow + rowStep, col = startCol + colStep;
                 row != endRow && col != endCol;
                 row += rowStep, col += colStep) {
                if (getPiece(row, col) != null) {
                    return false; // Invalid move
                }
            }

            return true;
        }

        return false; // Invalid move
    }



}
