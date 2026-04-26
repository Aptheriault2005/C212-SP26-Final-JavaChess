import java.util.HashSet;

public abstract class Piece {

    public enum PlayerColor {White, Black}

    private final ChessBoard cb;
    private final PlayerColor playerColor;
    private Position position;
    private HashSet<Position> moves;
    private HashSet<Position> captures;
    private HashSet<Piece> defending;

    public Piece(Position pos, ChessBoard chessBoard, PlayerColor player) {
        position = pos;
        cb = chessBoard;
        playerColor = player;
        setMoves(new HashSet<>());
        setCaptures(new HashSet<>());
        setDefending(new HashSet<>());
    }

    protected void addMovesAndCapturesInLine(int rankOffset, int fileOffset) {
        Position acc = position.getAdjacent(rankOffset, fileOffset);
        while (cb.isValidPosition(acc)) {
            if (cb.getPieceAt(acc) != null)  {
                if (cb.getPieceAt(acc).playerColor != this.playerColor) {
                    captures.add(acc);
                }
                else {
                    defending.add(cb.getPieceAt(acc));
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
                else {
                    defending.add(cb.getPieceAt(pos));
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

    public boolean isDefended() {
        for (Piece p : cb.getPieceList()) {
            if (p.defending.contains(this)) {
                return true;
            }
        }
        return false;
    }

    protected void removeIllegalMovesIfInCheck() {
        King king = (cb.getKing(playerColor));
        if (king == null) return;

        if (king.isInCheck()) {
            HashSet<Position> validMoves = new HashSet<>();
            HashSet<Position> validCaptures = new HashSet<>();
            for (Position pos : captures) {
                if (pos.equals(king.getPiecesCheckingKing().getFirst().getPosition())) {
                    validCaptures.add(pos);
                }
            }
            for (Position pos : moves) {
                if (getCb().moveBlocksCheck(pos, playerColor)) {
                    validMoves.add(pos);
                }
            }
            captures = validCaptures;
            moves = validMoves;
        }
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

    public HashSet<Piece> getDefending() {
        return defending;
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

    public void setDefending(HashSet<Piece> newDefending) {
        defending = newDefending;
    }
}
