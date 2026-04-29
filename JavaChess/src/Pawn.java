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
        getDefending().clear();
        addPawnMoves();
        addPawnCaptures();

        if (!getTryToValidate()) return;
        validateMoves();
//        removeIllegalMovesIfInCheck();
//        removeIllegalMovesIfPinned();
    }

    @Override
    public boolean getIsFirstMove() {
        return firstMove;
    }

    @Override
    public void setIsFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    @Override
    public Piece copy(ChessBoard newBoard) {
        Pawn copied = new Pawn(getPosition(), newBoard, getPlayerColor());
        copied.setIsFirstMove(getIsFirstMove());
        return copied;
    }

    private void addPawnMoves() {
        HashSet<Position> pawnMoves = new HashSet<>();
        Position pos = (getPlayerColor() == PlayerColor.White) ? getPosition().getAdjacent(1,0) : getPosition().getAdjacent(-1,0);
        if (getCb().isValidPosition(pos) && getCb().getPieceAt(pos) == null) {
            pawnMoves.add(pos);
        }

        if (getIsFirstMove()) {
            pos = (getPlayerColor() == PlayerColor.White) ? getPosition().getAdjacent(2,0) : getPosition().getAdjacent(-2,0);
            if (getCb().isValidPosition(pos) && getCb().getPieceAt(pos) == null) {
                pawnMoves.add(pos);
            }
        }

        this.setMoves(pawnMoves);
    }

    private void addPawnCaptures() {
        HashSet<Position> pawnCaptures = new HashSet<>();
        HashSet<Piece> pawnDefending = new HashSet<>();
        Position pos = (getPlayerColor() == PlayerColor.White) ? getPosition().getAdjacent(1, 1) : getPosition().getAdjacent(-1, 1);

        if (getCb().isValidPosition(pos) && getCb().getPieceAt(pos) != null) {
            if (getCb().getPieceAt(pos).getPlayerColor() != this.getPlayerColor()) {
                pawnCaptures.add(pos);
            }
            else {
                pawnDefending.add(getCb().getPieceAt(pos));
            }
        }

        pos = (getPlayerColor() == PlayerColor.White) ? getPosition().getAdjacent(1, -1) : getPosition().getAdjacent(-1, -1);

        if (getCb().isValidPosition(pos) && getCb().getPieceAt(pos) != null) {
            if (getCb().getPieceAt(pos).getPlayerColor() != this.getPlayerColor()) {
                pawnCaptures.add(pos);
            }
            else {
                pawnDefending.add(getCb().getPieceAt(pos));
            }
        }

        this.setCaptures(pawnCaptures);
        this.setDefending(pawnDefending);
    }
}
