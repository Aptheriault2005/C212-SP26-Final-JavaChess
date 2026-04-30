import java.util.HashSet;

public class Pawn extends Piece{

    private boolean firstMove = true;
    private boolean canBeCapturedEnPessant = false;
    private boolean enPessantTurnOver = false;
    private Piece promotion;

    public Pawn(Position pos, ChessBoard chessBoard, PlayerColor player) {
        super(pos, chessBoard, player);
        promotion = new Queen(pos, chessBoard, player);
    }

    /**
     * Gets the char representation of this
     * @return char representation of this
     */
    @Override
    public char getChar() {
        return 'P';
    }

    /**
     * Sets moves and captures then validates them if trying to validate
     * also determines if this piece can be captures en pessant
     */
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

    /**
     * Called after this piece moves from start position to end position before it is updated on the ChessBoard
     * If the move made is an en pessant move, captures pawn accordingly
     * If the move made is a promotion, promotes this pawn
     * @param start given start position
     * @param end given end position
     */
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

        if (getPlayerColor() == PlayerColor.White) {
            if (end.getRank() == 7) {
                promote(promotion);
            }
        }
        else {
            if (end.getRank() == 0) {
                promote(promotion);
            }
        }
    }

    /**
     * Creates a copy of this piece to a given board
     * @param newBoard given board
     * @return copy of this piece
     */
    @Override
    public Piece copy(ChessBoard newBoard) {
        Pawn copied = new Pawn(getPosition(), newBoard, getPlayerColor());
        copied.firstMove = firstMove;
        copied.canBeCapturedEnPessant = canBeCapturedEnPessant;
        copied.enPessantTurnOver = enPessantTurnOver;
        return copied;
    }

    /**
     * Promotes this pawn to given promotion piece
     * @param piece given promotion piece
     */
    public void promote(Piece piece) {
        if (piece instanceof Knight
                || piece instanceof Bishop
                || piece instanceof Rook
                || piece instanceof Queen) {

            Position pos = getPosition();
            getCb().capturePiece(this);
            piece.setPosition(pos);
            getCb().addNewPiece(piece);
        }
        else {
            throw new RuntimeException("Invalid piece for pawn promotion");
        }
    }

    /**
     * Sets the promotion of this pawn to given piece
     * @param piece given piece
     */
    public void setPromotion(Piece piece) {
        promotion = piece;
    }

    /**
     * Adds valid pawn moves
     */
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

    /**
     * Adds valid pawn captures
     */
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

    /**
     * Adds en pessant captures to given pawn captures position hashset
     * @param pawnCaptures given pawn captures position hashset
     */
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
