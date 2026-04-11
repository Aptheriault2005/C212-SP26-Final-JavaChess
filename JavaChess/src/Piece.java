import java.util.HashSet;

public abstract class Piece {

    public enum PlayerColor {White, Black}

    private final ChessBoard cb;
    private final PlayerColor playerColor;
    private Position position;
    private HashSet<Position> moves;
    private HashSet<Position> captures;

    public Piece(Position pos, ChessBoard chessBoard, PlayerColor player) {
        position = pos;
        cb = chessBoard;
        playerColor = player;
        setMoves(new HashSet<>());
        setCaptures(new HashSet<>());
    }

    protected void addMovesAndCapturesInLine(int rankOffset, int fileOffset) {
        Position acc = position.getAdjacent(rankOffset, fileOffset);
        while (cb.isValidPosition(acc)) {
            if (cb.getPiece(acc) != null)  {
                if (cb.getPiece(acc).playerColor != this.playerColor) {
                    captures.add(acc);
                }
                break;
            }
            else {
                moves.add(acc);
            }
            acc = acc.getAdjacent(rankOffset, fileOffset);
        }
    }

    protected void addMovesAndCapturesAtOffset(int rankOffset, int fileOffset) {
        Position pos = position.getAdjacent(rankOffset, fileOffset);
        if (cb.isValidPosition(pos)) {
            if (cb.getPiece(pos) != null)  {
                if (cb.getPiece(pos).playerColor != this.playerColor) {
                    captures.add(pos);
                }
            }
            else {
                moves.add(pos);
            }
        }
    }

    protected ChessBoard getCb() {
        return cb;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public boolean isValidMove(Position newPos) {
        return this.getMoves().contains(newPos);
    }

    public boolean isValidCapture(Position newPos) {
        return this.getCaptures().contains(newPos);
    }

    public char getChar() {
        return '?';
    }

    public abstract void update();

    public HashSet<Position> getMoves() {
        return moves;
    }

    public HashSet<Position> getCaptures() {
        return captures;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position newPos) {
        position = newPos;
    }

    public void setMoves(HashSet<Position> newMoves) {
        moves = newMoves;
    }

    public void setCaptures(HashSet<Position> newCaptures) {
        captures = newCaptures;
    }
}
