import java.util.HashSet;

public class Game {

    private final ChessBoard cb;
    private int turn = 0;

    public Game() {
        cb = new ChessBoard(true);
    }

    public Game(boolean isEmpty) {
        cb = new ChessBoard(!isEmpty);
    }

    public Piece.PlayerColor getCurrentPlayer() {
        if (turn % 2 == 0) {
            return Piece.PlayerColor.White;
        }
        else {
            return Piece.PlayerColor.Black;
        }
    }

    public ChessBoard getChessBoard() {
        return cb;
    }

    public boolean makeMove(Piece pieceToMove, Position targetPosition) {
        if (pieceToMove.getPlayerColor() == getCurrentPlayer() && cb.movePiece(pieceToMove, targetPosition)) {
            turn++;
            return true;
        }
        return false;
    }
}
