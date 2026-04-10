import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    private static final char[][] EMPTY_BOARD = new char[][] {
            {'-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-'}
    };
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private char[][] board;
    private Map<Position, Piece> pieces = new HashMap<>();

    public ChessBoard() {
        //board = EMPTY_BOARD;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                pieces.put(new Position(i,j), null);
            }
        }
    }

    public void addPiece(Piece piece) {
        pieces.put(piece.position, piece);
        board[piece.position.row][piece.position.col] = piece.getChar();
    }

    public void movePiece(Piece piece, Position pos) {

    }

    public Piece getPiece(Position position) {
        if (pieces.containsKey(position)) {
            return pieces.get(position);
        }
        return null;
    }

    public boolean isValidPosition(Position pos) {
        return pos.row > 0 && pos.row < board.length && pos.col > 0 && pos.col < board[0].length;
    }

    public void printBoard() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                //if (pieces.get(new Position(i,j)))
            }
            System.out.println();
        }
    }
}
