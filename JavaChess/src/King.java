import java.util.HashSet;

public class King extends Piece implements IFirstMove {

    private boolean firstMove = true;

    public King(Position pos, ChessBoard chessBoard, PlayerColor player) {
        super(pos, chessBoard, player);
    }

    @Override
    public char getChar() {
        return 'K';
    }

    @Override
    public void update() {
        this.setMoves(new HashSet<>());
        this.setCaptures(new HashSet<>());
        addKingMoves();
        addKingCaptures();
    }

    @Override
    public boolean getIsFirstMove() {
        return firstMove;
    }

    @Override
    public void setIsFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    private void addKingMoves() {
        HashSet<Position> possibleKingMoves = new HashSet<>();
        HashSet<Position> validKingMoves = new HashSet<>();

        removeKingAndUpdate();

        possibleKingMoves.add(getPosition().getAdjacent(1, 1));
        possibleKingMoves.add(getPosition().getAdjacent(1, 0));
        possibleKingMoves.add(getPosition().getAdjacent(1, -1));
        possibleKingMoves.add(getPosition().getAdjacent(0, 1));
        possibleKingMoves.add(getPosition().getAdjacent(0, -1));
        possibleKingMoves.add(getPosition().getAdjacent(-1, 1));
        possibleKingMoves.add(getPosition().getAdjacent(-1, 0));
        possibleKingMoves.add(getPosition().getAdjacent(-1, -1));

        for (Position pos : possibleKingMoves) {
            if (
                    getCb().isValidPosition(pos) &&
                    getCb().getPieceAt(pos) == null &&
                    !getCb().isPositionAttacked(pos, this.getPlayerColor())
            ) {
                validKingMoves.add(pos);
            }
        }

        addKingAndUpdate();

        this.setMoves(validKingMoves);
    }

    private void addKingCaptures() {
        HashSet<Position> possibleKingCaptures = new HashSet<>();
        HashSet<Position> validKingCaptures = new HashSet<>();

        possibleKingCaptures.add(getPosition().getAdjacent(1, 1));
        possibleKingCaptures.add(getPosition().getAdjacent(1, 0));
        possibleKingCaptures.add(getPosition().getAdjacent(1, -1));
        possibleKingCaptures.add(getPosition().getAdjacent(0, 1));
        possibleKingCaptures.add(getPosition().getAdjacent(0, -1));
        possibleKingCaptures.add(getPosition().getAdjacent(-1, 1));
        possibleKingCaptures.add(getPosition().getAdjacent(-1, 0));
        possibleKingCaptures.add(getPosition().getAdjacent(-1, -1));

        for (Position pos : possibleKingCaptures) {
            if (
                    getCb().isValidPosition(pos) &&
                    getCb().getPieceAt(pos) != null &&
                    getCb().getPieceAt(pos).getPlayerColor() != getPlayerColor() &&
                    !getCb().isPositionAttacked(pos, getPlayerColor())
            ) {
                    validKingCaptures.add(pos);
            }
        }

        setCaptures(validKingCaptures);
    }

    private void removeKingAndUpdate() {
        getCb().removePieceFromBoard(this);
        HashSet<Piece> otherKingSet = new HashSet<>();
        if (getPlayerColor() == PlayerColor.White) {
            otherKingSet.add(getCb().getKing(PlayerColor.Black));
            getCb().updatePieces(PlayerColor.Black, otherKingSet);
        }
        else {
            otherKingSet.add(getCb().getKing(PlayerColor.White));
            getCb().updatePieces(PlayerColor.White, otherKingSet);
        }
    }

    private void addKingAndUpdate() {
        getCb().addPieceToBoard(this);
        HashSet<Piece> otherKingSet = new HashSet<>();
        if (getPlayerColor() == PlayerColor.White) {
            otherKingSet.add(getCb().getKing(PlayerColor.Black));
            getCb().updatePieces(PlayerColor.Black, otherKingSet);
        }
        else {
            otherKingSet.add(getCb().getKing(PlayerColor.White));
            getCb().updatePieces(PlayerColor.White, otherKingSet);
        }
    }

    public boolean isCheckmated() {
        if (!isInCheck() || !getMoves().isEmpty() || !getCaptures().isEmpty()) {
            return false;
        }

//        HashSet<Position> kingMoves = getMoves();
//        HashSet<Piece> piecesCheckingKingSet = new HashSet<>();

        return true;
    }

    public boolean isInCheck() {
        for (Piece piece : getCb().getPieceList()) {
            if (piece.getPlayerColor() != getPlayerColor()) {
                if (piece.getCaptures().contains(getPosition())) {
                    return true;
                }
            }
        }
        return false;
    }
}
