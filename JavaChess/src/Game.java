import java.util.HashSet;

public class Game {

    private GameGUI gameGUI;
    private final ChessBoard cb;
    private int turn = 0;

    public Game() {
        cb = new ChessBoard(true);
        gameGUI = new GameGUI(this);
    }

    public Game(boolean hasGUI) {
        cb = new ChessBoard(true);
        if (hasGUI) {
            gameGUI = new GameGUI(this);
        }
        else {
            gameGUI = null;
        }
    }

    public Piece.PlayerColor getCurrentPlayer() {
        if (turn % 2 == 0) {
            return Piece.PlayerColor.White;
        }
        else {
            return Piece.PlayerColor.Black;
        }
    }

    public Piece selectValidPieceAt(Position pos) {
        Piece piece = cb.getPieceAt(pos);
        if (piece == null || piece.getPlayerColor() != getCurrentPlayer()) return null;
        return piece;
    }

    public ChessBoard getChessBoard() {
        return cb;
    }

    public boolean makeMove(Piece pieceToMove, Position targetPosition) {
        if (pieceToMove != null && pieceToMove.getPlayerColor() == getCurrentPlayer() && cb.movePiece(pieceToMove, targetPosition)) {
            if (getCurrentPlayer() == Piece.PlayerColor.White) {
                if (cb.getKing(Piece.PlayerColor.Black).isInCheck()) {
                    if (cb.getKing(Piece.PlayerColor.Black).isCheckmated()) {
                        System.out.println("Checkmate Black");
                    }
                    else {
                        System.out.println("Check Black");
                    }

                }
            }
            else {
                if (cb.getKing(Piece.PlayerColor.White).isInCheck()) {
                    if (cb.getKing(Piece.PlayerColor.White).isCheckmated()) {
                        System.out.println("Checkmate White");
                    }
                    else {
                        System.out.println("Check White");
                    }
                }
            }
            turn++;
            return true;
        }
        return false;
    }

    public GameGUI getGameGUI() {
        return gameGUI;
    }
}
