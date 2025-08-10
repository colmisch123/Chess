//Game.java, Cole Misch, misch123

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        boolean isWhiteTurn = true;
        String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        Fen.load(fen,board);

        while (true) {
            System.out.println(board);

            String player = isWhiteTurn ? "White" : "Black";
            System.out.println(player + "'s turn.");
            System.out.print("Choose a move (format: [start row] [start col] [end row] [end col]: ");

            //Creating array to choose moves
            String pick = scanner.nextLine();
            String[] moveVals = pick.split(" ");

            int startRow = Integer.parseInt(moveVals[0]);
            int startCol = Integer.parseInt(moveVals[1]);
            int endRow = Integer.parseInt(moveVals[2]);
            int endCol = Integer.parseInt(moveVals[3]);


            if (board.movePiece(startRow, startCol, endRow, endCol)) {
                isWhiteTurn = !isWhiteTurn;
            } else {
                System.out.println("Invalid move. Try again.");
            }

            Piece piece = board.getPiece(endRow, endCol);

            if ((board.getPiece(startRow,startCol) != null) || (endRow == 0 && !piece.getIsBlack())){
                if (piece != null) {
                    piece.promotePawn(endRow, piece.getIsBlack());
                }
            }




            if (board.isGameOver()) {
                System.out.println(board);
                System.out.println(player + " wins!");
                break;
            }

        }

        scanner.close();
    }


}
