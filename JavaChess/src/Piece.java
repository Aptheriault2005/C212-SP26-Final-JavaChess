import java.util.HashSet;

public abstract class Piece {

    public enum PlayerColor {White, Black}

    private boolean tryToValidate;
    private final ChessBoard cb;
    private final PlayerColor playerColor;
    private Position position;
    private HashSet<Position> moves;
    private HashSet<Position> captures;

    public Piece(Position pos, ChessBoard chessBoard, PlayerColor player) {
        position = pos;
        cb = chessBoard;
        playerColor = player;
        tryToValidate = true;
        setMoves(new HashSet<>());
        setCaptures(new HashSet<>());
    }

    protected void addMovesAndCapturesInLine(int rankOffset, int fileOffset) {
        Position acc = position.getAdjacent(rankOffset, fileOffset);
        while (cb.isValidPosition(acc)) {
            if (cb.getPieceAt(acc) != null)  {
                if (cb.getPieceAt(acc).playerColor != this.playerColor) {
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
            if (cb.getPieceAt(pos) != null)  {
                if (cb.getPieceAt(pos).playerColor != this.playerColor) {
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

    protected void validateMoves() {
        HashSet<Position> validMoves = new HashSet<>();
        HashSet<Position> validCaptures = new HashSet<>();
        for (Position move : moves) {
            if (cb.validateMove(this, move)) {
                validMoves.add(move);
            }
        }
        for (Position capture : captures) {
            if (cb.validateMove(this, capture)) {
                validCaptures.add(capture);
            }
        }
        setMoves(validMoves);
        setCaptures(validCaptures);
    }

    public boolean getTryToValidate() {
        return tryToValidate;
    }

    public void setTryToValidate(boolean active) {
        tryToValidate = active;
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

    public abstract Piece copy(ChessBoard newBoard);

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
