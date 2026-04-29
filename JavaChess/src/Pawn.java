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
        this.setMoves(new HashSet<>());
        this.setCaptures(new HashSet<>());
        addPawnMoves();
        addPawnCaptures();

        if (!getTryToValidate()) return;
        validateMoves();
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
        Position move1 = (getPlayerColor() == PlayerColor.White) ? getPosition().getAdjacent(1,0) : getPosition().getAdjacent(-1,0);
        if (getCb().isValidPosition(move1) && getCb().getPieceAt(move1) == null) {
            pawnMoves.add(move1);
        }

        if (getIsFirstMove()) {
            Position move2 = (getPlayerColor() == PlayerColor.White) ? getPosition().getAdjacent(2,0) : getPosition().getAdjacent(-2,0);
            if (getCb().isValidPosition(move2) && getCb().getPieceAt(move1) == null && getCb().getPieceAt(move2) == null) {
                pawnMoves.add(move2);
            }
        }

        this.setMoves(pawnMoves);
    }

    private void addPawnCaptures() {
        HashSet<Position> pawnCaptures = new HashSet<>();
        Position pos = (getPlayerColor() == PlayerColor.White) ? getPosition().getAdjacent(1, 1) : getPosition().getAdjacent(-1, 1);

        if (getCb().isValidPosition(pos) && getCb().getPieceAt(pos) != null) {
            if (getCb().getPieceAt(pos).getPlayerColor() != this.getPlayerColor()) {
                pawnCaptures.add(pos);
            }
        }

        pos = (getPlayerColor() == PlayerColor.White) ? getPosition().getAdjacent(1, -1) : getPosition().getAdjacent(-1, -1);

        if (getCb().isValidPosition(pos) && getCb().getPieceAt(pos) != null) {
            if (getCb().getPieceAt(pos).getPlayerColor() != this.getPlayerColor()) {
                pawnCaptures.add(pos);
            }
        }

        this.setCaptures(pawnCaptures);
    }
}
