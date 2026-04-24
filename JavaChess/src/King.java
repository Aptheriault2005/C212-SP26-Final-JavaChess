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

        possibleKingMoves.add(getPosition().getAdjacent(1, 1));
        possibleKingMoves.add(getPosition().getAdjacent(1, 0));
        possibleKingMoves.add(getPosition().getAdjacent(1, -1));
        possibleKingMoves.add(getPosition().getAdjacent(0, 1));
        possibleKingMoves.add(getPosition().getAdjacent(0, -1));
        possibleKingMoves.add(getPosition().getAdjacent(-1, 1));
        possibleKingMoves.add(getPosition().getAdjacent(-1, 0));
        possibleKingMoves.add(getPosition().getAdjacent(-1, -1));

        for (Position pos : possibleKingMoves) {
            if (getCb().isValidPosition(pos) && !getCb().isPositionAttacked(pos, this.getPlayerColor())) {
                validKingMoves.add(pos);
            }
        }

        this.setMoves(validKingMoves);
    }

    private void addKingCaptures() {

    }

    public boolean isCheckmated() {
        if (!isInCheck() || !getMoves().isEmpty()) {
            return false;
        }

        HashSet<Position> kingMoves = getMoves();
        HashSet<Piece> piecesCheckingKingSet = new HashSet<>();


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
