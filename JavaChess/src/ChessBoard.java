import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    public static final int ROWS = 8;
    public static final int COLUMNS = 8;

    private Map<Position, Piece> pieces = new HashMap<>();

    public ChessBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                pieces.put(new Position(i,j), null);
            }
        }
    }

    public void updatePieces() {
        for (Piece p : pieces.values()) {
            if (p != null) {
                p.update();
            }
        }
    }

    public void addPiece(Piece piece) {
        pieces.put(piece.getPosition(), piece);
    }

    public void movePiece(Piece piece, Position pos) {
        if (piece.isValidCapture(pos)) {
            pieces.put(piece.getPosition(), null);
            piece.setPosition(pos);
            pieces.put(piece.getPosition(), piece);
            updatePieces();
        }
        else if (piece.isValidMove(pos)) {
            pieces.put(piece.getPosition(), null);
            piece.setPosition(pos);
            pieces.put(piece.getPosition(), piece);
            updatePieces();
        }
    }

    public Piece getPiece(Position position) {
        if (pieces.containsKey(position)) {
            return pieces.get(position);
        }
        return null;
    }

    public boolean isValidPosition(Position position) {
        return pieces.containsKey(position);
    }

    public void printBoard() {
        for (int i = ROWS - 1; i >= 0; i--) {
            for (int j = 0; j < COLUMNS; j++) {
                Position pos = new Position(i,j);
                if (pieces.get(pos) != null)  {
                    System.out.print(pieces.get(pos).getChar());
                }
                else {
                    System.out.print("-");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
