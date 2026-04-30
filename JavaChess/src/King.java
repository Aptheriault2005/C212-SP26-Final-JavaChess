import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class King extends Piece {

    private boolean canCastleKingSide = false;
    private boolean canCastleQueenSide = false;
    private boolean firstMove = true;
    private final List<Piece> piecesCheckingKing;

    public King(Position pos, ChessBoard chessBoard, PlayerColor player) {
        super(pos, chessBoard, player);
        piecesCheckingKing = new ArrayList<>();
    }

    /**
     * Gets the char representation of this
     * @return char representation of this
     */
    @Override
    public char getChar() {
        return 'K';
    }

    /**
     * Sets moves and captures then validates them if trying to validate
     * also determines if this piece can castle
     */
    @Override
    public void update() {
        this.setMoves(new HashSet<>());
        this.setCaptures(new HashSet<>());
        addMovesAndCapturesAtOffset(1,1);
        addMovesAndCapturesAtOffset(1,0);
        addMovesAndCapturesAtOffset(1,-1);
        addMovesAndCapturesAtOffset(-1,1);
        addMovesAndCapturesAtOffset(-1,0);
        addMovesAndCapturesAtOffset(-1,-1);
        addMovesAndCapturesAtOffset(0,-1);
        addMovesAndCapturesAtOffset(0,1);

        determineCastleAvailability();

        if (!getTryToValidate()) return;
        validateMoves();

        removeCastleIfCastlesThroughCheck();
    }

    /**
     * Creates a copy of this piece to a given board
     * @param newBoard given board
     * @return copy of this piece
     */
    @Override
    public Piece copy(ChessBoard newBoard) {
        King copied = new King(getPosition(), newBoard, getPlayerColor());
        copied.firstMove = firstMove;
        return copied;
    }

    /**
     * Called after this piece moves from start position to end position before it is updated on the ChessBoard
     * If the move made is a castling move, moves the corresponding rook to position
     * @param start given start position
     * @param end given end position
     */
    @Override
    public void onMove(Position start, Position end) {
        firstMove = false;
        if (getPlayerColor() == PlayerColor.White) {
            if (canCastleKingSide && end.equals(Position.at(0,6))) {
                Rook kingsideCastleRook = (Rook) getCb().getPieceAt(Position.at(0,7));
                getCb().forceMovePiece(kingsideCastleRook, Position.at(0,5));
            }
            if (canCastleQueenSide && end.equals(Position.at(0,2))) {
                Rook queensideCastleRook = (Rook) getCb().getPieceAt(Position.at(0,0));
                getCb().forceMovePiece(queensideCastleRook, Position.at(0,3));
            }
        }
        else {
            if (canCastleKingSide && end.equals(Position.at(7,6))) {
                Rook kingsideCastleRook = (Rook) getCb().getPieceAt(Position.at(7,7));
                getCb().forceMovePiece(kingsideCastleRook, Position.at(7,5));
            }
            if (canCastleQueenSide && end.equals(Position.at(7,2))) {
                Rook queensideCastleRook = (Rook) getCb().getPieceAt(Position.at(7,0));
                getCb().forceMovePiece(queensideCastleRook, Position.at(7,3));
            }
        }

    }

    /**
     * Removes castle option if it results in castling through a check
     */
    private void removeCastleIfCastlesThroughCheck() {
        if (canCastleKingSide) {
            if (getPlayerColor() == PlayerColor.White) {
                if (getMoves().contains(Position.at(0,6)) && !getMoves().contains(Position.at(0,5))) {
                    getMoves().remove(Position.at(0,6));
                }
            }
            else {
                if (getMoves().contains(Position.at(7,6)) && !getMoves().contains(Position.at(7,5))) {
                    getMoves().remove(Position.at(7,6));
                }
            }
        }
        if (canCastleQueenSide) {
            if (getPlayerColor() == PlayerColor.White) {
                if (getMoves().contains(Position.at(0,2)) && !getMoves().contains(Position.at(0,3))) {
                    getMoves().remove(Position.at(0,2));
                }
            }
            else {
                if (getMoves().contains(Position.at(7,2)) && !getMoves().contains(Position.at(7,3))) {
                    getMoves().remove(Position.at(7,2));
                }
            }
        }
    }

    /**
     * Adds kingside and queenside castle moves if legal
     */
    private void determineCastleAvailability() {
        canCastleKingSide = false;
        canCastleQueenSide = false;
        if (!firstMove) return;
        determineKingsideCastle();
        determineQueensideCastle();

        if (canCastleKingSide) {
            if (getPlayerColor() == PlayerColor.White) {
                getMoves().add(Position.at(0,6));
            }
            else {
                getMoves().add(Position.at(7,6));
            }
        }
        if (canCastleQueenSide) {
            if (getPlayerColor() == PlayerColor.White) {
                getMoves().add(Position.at(0,2));
            }
            else {
                getMoves().add(Position.at(7,2));
            }
        }
    }

    /**
     * Determines if this King can kingside castle
     */
    private void determineKingsideCastle() {
        if (getPlayerColor() == PlayerColor.White) {
            if (!(getCb().getPieceAt(Position.at(0,7)) instanceof Rook)) return;
            if (getCb().getPieceAt(Position.at(0,6)) != null) return;
            if (getCb().getPieceAt(Position.at(0,5)) != null) return;
            canCastleKingSide = true;
        }
        else {
            if (!(getCb().getPieceAt(Position.at(7,7)) instanceof Rook)) return;
            if (getCb().getPieceAt(Position.at(7,6)) != null) return;
            if (getCb().getPieceAt(Position.at(7,5)) != null) return;
            canCastleKingSide = true;
        }
    }

    /**
     * Determines if this King can queenside castle
     */
    private void determineQueensideCastle() {
        if (getPlayerColor() == PlayerColor.White) {
            if (!(getCb().getPieceAt(Position.at(0,0)) instanceof Rook)) return;
            if (getCb().getPieceAt(Position.at(0,1)) != null) return;
            if (getCb().getPieceAt(Position.at(0,2)) != null) return;
            if (getCb().getPieceAt(Position.at(0,3)) != null) return;
            if (!(getMoves().contains(Position.at(0,3)))) return;
            canCastleQueenSide = true;
        }
        else {
            if (!(getCb().getPieceAt(Position.at(7,0)) instanceof Rook)) return;
            if (getCb().getPieceAt(Position.at(7,1)) != null) return;
            if (getCb().getPieceAt(Position.at(7,2)) != null) return;
            if (getCb().getPieceAt(Position.at(7,3)) != null) return;
            if (!(getMoves().contains(Position.at(7,3)))) return;
            canCastleQueenSide = true;
        }
    }

    /**
     * Determines if this King is currently checkmated
     * @return true if checkmated, otherwise false
     */
    public boolean isCheckmated() {
        if (!isInCheck() || !getMoves().isEmpty() || !getCaptures().isEmpty()) {
            return false;
        }

        for (Piece p : getCb().getPieceList()) {
            if (p.getPlayerColor() == getPlayerColor()) {
                if (!p.getCaptures().isEmpty() || !p.getMoves().isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Determines if this King is currently in check
     * @return true if in check, otherwise false
     */
    public boolean isInCheck() {
        piecesCheckingKing.clear();

        for (Piece p : getCb().getPieceList()) {
            if (p.getPlayerColor() != getPlayerColor()) {
                if (p.getCaptures().contains(getPosition())) {
                    piecesCheckingKing.add(p);
                }
            }
        }
        return !piecesCheckingKing.isEmpty();
    }
}
