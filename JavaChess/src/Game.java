import java.util.HashSet;

public class Game {

    private GameGUI gameGUI;
    private final ChessBoard cb;
    private int turn = 0;

    public Game() {
        System.out.println("New Game");
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
            System.out.print(turn + 1 + ". ");
            if (!(pieceToMove instanceof Pawn)) {
                System.out.print(pieceToMove.getChar());
            }
            System.out.print(targetPosition.getChessNotation());
            if (getCurrentPlayer() == Piece.PlayerColor.White) {
                if (cb.getKing(Piece.PlayerColor.Black).isInCheck()) {
                    if (cb.getKing(Piece.PlayerColor.Black).isCheckmated()) {
                        System.out.print("#");
                        if (FrameGUI.frameGUIInstance.isEmpty()) return true;
                        FrameGUI.frameGUIInstance.get().toGameOver("White wins by checkmate");
                    }
                    else {
                        System.out.print("+");
                    }
                }
            }
            else {
                if (cb.getKing(Piece.PlayerColor.White).isInCheck()) {
                    if (cb.getKing(Piece.PlayerColor.White).isCheckmated()) {
                        System.out.print("#");
                        if (FrameGUI.frameGUIInstance.isEmpty()) return true;
                        FrameGUI.frameGUIInstance.get().toGameOver("Black wins by checkmate");
                    }
                    else {
                        System.out.print("+");
                    }
                }
            }
            System.out.println();
            turn++;
            return true;
        }
        return false;
    }

    public GameGUI getGameGUI() {
        return gameGUI;
    }
}
