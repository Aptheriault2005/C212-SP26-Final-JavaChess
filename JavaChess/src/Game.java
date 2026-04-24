import java.util.HashSet;

public class Game {

    private final ChessBoard cb;
    private int turn = 0;

    public Game() {
        cb = new ChessBoard(true);
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

    public boolean isKingCheckmated(Piece.PlayerColor player) {
        return true;
    }

    public boolean isKingInCheck(Piece.PlayerColor player) {
        King king = cb.getKing(player);
        for (Piece piece : cb.getPieceList()) {
            if (piece.getPlayerColor() != player) {
                if (piece.getCaptures().contains(king.getPosition())) {
                    return true;
                }
            }
        }
        return false;
    }
}
