import java.util.HashSet;

public class Pawn extends Piece{

    private boolean firstMove = true;
    private boolean canBeCapturedEnPessant = false;
    private boolean enPessantTurnOver = false;

    public Pawn(Position pos, ChessBoard chessBoard, PlayerColor player) {
        super(pos, chessBoard, player);
    }

    @Override
    public char getChar() {
        return 'P';
    }

    @Override
    public void update() {
        if (enPessantTurnOver) {
            canBeCapturedEnPessant = false;
        }
        if (canBeCapturedEnPessant) {
            enPessantTurnOver = true;
        }

        this.setMoves(new HashSet<>());
        this.setCaptures(new HashSet<>());
        addPawnMoves();
        addPawnCaptures();

        if (!getTryToValidate()) return;
        validateMoves();
    }

    @Override
    public void onMove(Position start, Position end) {
        canBeCapturedEnPessant = false;
        firstMove = false;

        if (getCaptures().contains(end)) {
            if (getPlayerColor() == PlayerColor.White) {
                Piece pieceBehind = getCb().getPieceAt(getPosition().getAdjacent(-1,0));
                if (pieceBehind instanceof Pawn && ((Pawn) pieceBehind).canBeCapturedEnPessant) {
                    getCb().capturePiece(pieceBehind);
                }

            }
            else {
                Piece pieceBehind = getCb().getPieceAt(getPosition().getAdjacent(1,0));
                if (pieceBehind instanceof Pawn && ((Pawn) pieceBehind).canBeCapturedEnPessant) {
                    getCb().capturePiece(pieceBehind);
                }
            }
        }

        if (Math.abs(start.getRank() - end.getRank()) == 2) {
            canBeCapturedEnPessant = true;
        }
    }

    @Override
    public Piece copy(ChessBoard newBoard) {
        Pawn copied = new Pawn(getPosition(), newBoard, getPlayerColor());
        copied.firstMove = firstMove;
        return copied;
    }

    private void addPawnMoves() {
        HashSet<Position> pawnMoves = new HashSet<>();
        Position move1 = (getPlayerColor() == PlayerColor.White) ? getPosition().getAdjacent(1,0) : getPosition().getAdjacent(-1,0);
        if (getCb().isValidPosition(move1) && getCb().getPieceAt(move1) == null) {
            pawnMoves.add(move1);
        }

        if (firstMove) {
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

        addEnPessantCaptures(pawnCaptures);

        this.setCaptures(pawnCaptures);
    }

    private void addEnPessantCaptures(HashSet<Position> pawnCaptures) {
        Position captureLeftPos = getPosition().getAdjacent(0,-1);
        Position captureRightPos = getPosition().getAdjacent(0,1);
        if (getCb().isValidPosition(captureLeftPos)
                && getCb().getPieceAt(captureLeftPos) instanceof Pawn
                && ((Pawn) getCb().getPieceAt(captureLeftPos)).canBeCapturedEnPessant) {
            if (getPlayerColor() == PlayerColor.White) {
                pawnCaptures.add(captureLeftPos.getAdjacent(1,0));
            }
            else {
                pawnCaptures.add(captureLeftPos.getAdjacent(-1,0));
            }

        }
        if (getCb().isValidPosition(captureRightPos)
                && getCb().getPieceAt(captureRightPos) instanceof Pawn
                && ((Pawn) getCb().getPieceAt(captureRightPos)).canBeCapturedEnPessant) {
            if (getPlayerColor() == PlayerColor.White) {
                pawnCaptures.add(captureRightPos.getAdjacent(1,0));
            }
            else {
                pawnCaptures.add(captureRightPos.getAdjacent(-1,0));
            }
        }
    }

    public boolean isCanBeCapturedEnPessant() {
        return canBeCapturedEnPessant;
    }
}
