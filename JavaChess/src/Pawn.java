import java.util.HashSet;

public class Pawn extends Piece implements IFirstMove{

    private boolean firstMove = true;

    public Pawn(Position pos, ChessBoard chessBoard, PlayerColor player) {
        super(pos, chessBoard, player);
    }

    @Override
    public char getChar() {
        return 'P';
    }

    @Override
    public void update() {
        addPawnMoves();
        addPawnCaptures();
    }

    @Override
    public boolean getIsFirstMove() {
        return firstMove;
    }

    @Override
    public void setIsFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    private void addPawnMoves() {
        HashSet<Position> pawnMoves = new HashSet<>();
        Position pos = (getPlayerColor() == PlayerColor.White) ? getPosition().getAdjacent(1,0) : getPosition().getAdjacent(-1,0);
        if (getCb().isValidPosition(pos) && getCb().getPiece(pos) == null) {
            pawnMoves.add(pos);
        }

        if (getIsFirstMove()) {
            pos = (getPlayerColor() == PlayerColor.White) ? getPosition().getAdjacent(2,0) : getPosition().getAdjacent(-2,0);
            if (getCb().isValidPosition(pos) && getCb().getPiece(pos) == null) {
                pawnMoves.add(pos);
            }
        }

        this.setMoves(pawnMoves);
    }

    private void addPawnCaptures() {
        HashSet<Position> pawnCaptures = new HashSet<>();
        Position pos = getPosition().getAdjacent(1, 1);
        if (getCb().isValidPosition(pos) && getCb().getPiece(pos) != null && getCb().getPiece(pos).getPlayerColor() != this.getPlayerColor()) {
            pawnCaptures.add(pos);
        }
        pos = getPosition().getAdjacent(1, -1);
        if (getCb().isValidPosition(pos) && getCb().getPiece(pos) != null && getCb().getPiece(pos).getPlayerColor() != this.getPlayerColor()) {
            pawnCaptures.add(pos);
        }

        this.setCaptures(pawnCaptures);
    }
}
