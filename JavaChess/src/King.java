import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class King extends Piece implements IFirstMove {

    private boolean firstMove = true;
    private final List<Piece> piecesCheckingKing;

    public King(Position pos, ChessBoard chessBoard, PlayerColor player) {
        super(pos, chessBoard, player);
        piecesCheckingKing = new ArrayList<>();
    }

    @Override
    public char getChar() {
        return 'K';
    }

    @Override
    public void update() {
//        getDefending().clear();
//        this.setMoves(new HashSet<>());
//        this.setCaptures(new HashSet<>());
//        addKingMoves();
//        addKingCaptures();

        addMovesAndCapturesAtOffset(1,1);
        addMovesAndCapturesAtOffset(1,0);
        addMovesAndCapturesAtOffset(1,-1);
        addMovesAndCapturesAtOffset(-1,1);
        addMovesAndCapturesAtOffset(-1,0);
        addMovesAndCapturesAtOffset(-1,-1);
        addMovesAndCapturesAtOffset(0,-1);
        addMovesAndCapturesAtOffset(0,1);

        if (!getTryToValidate()) return;
        validateMoves();
    }

    @Override
    public Piece copy(ChessBoard newBoard) {
        King copied = new King(getPosition(), newBoard, getPlayerColor());
        copied.setIsFirstMove(getIsFirstMove());
        return copied;
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

//        removeKingAndUpdate();

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
                    getCb().getPieceAt(pos) == null //&&
                    //!getCb().isPositionAttacked(pos, this.getPlayerColor())
            ) {
                validKingMoves.add(pos);
            }
        }
//
//        addKingAndUpdate();

        this.setMoves(validKingMoves);
    }

    private void addKingCaptures() {
        HashSet<Position> possibleKingCaptures = new HashSet<>();
        HashSet<Position> validKingCaptures = new HashSet<>();
        HashSet<Piece> kingDefending = new HashSet<>();

//        possibleKingCaptures.add(getPosition().getAdjacent(1, 1));
//        possibleKingCaptures.add(getPosition().getAdjacent(1, 0));
//        possibleKingCaptures.add(getPosition().getAdjacent(1, -1));
//        possibleKingCaptures.add(getPosition().getAdjacent(0, 1));
//        possibleKingCaptures.add(getPosition().getAdjacent(0, -1));
//        possibleKingCaptures.add(getPosition().getAdjacent(-1, 1));
//        possibleKingCaptures.add(getPosition().getAdjacent(-1, 0));
//        possibleKingCaptures.add(getPosition().getAdjacent(-1, -1));
//
//        for (Position pos : possibleKingCaptures) {
//            if (getCb().isValidPosition(pos) && getCb().getPieceAt(pos) != null) {
//                if (
//                        getCb().getPieceAt(pos).getPlayerColor() != getPlayerColor() //&&
//                        //!getCb().isPositionAttacked(pos, getPlayerColor()) &&
//                        //!getCb().getPieceAt(pos).isDefended()
//                ) {
//                    validKingCaptures.add(pos);
//                }
////                else {
////                    kingDefending.add(getCb().getPieceAt(pos));
////                }
//            }
//        }

        setCaptures(validKingCaptures);
        //setDefending(kingDefending);
    }

    private void removeKingAndUpdate() {
        getCb().removePieceFromBoard(this);
        if (getPlayerColor() == PlayerColor.White) {
            getCb().updatePieces(PlayerColor.Black, getCb().getKing(PlayerColor.Black));
        }
        else {
            getCb().updatePieces(PlayerColor.White, getCb().getKing(PlayerColor.White));
        }
    }

    private void addKingAndUpdate() {
        getCb().addPieceToBoard(this);
        if (getPlayerColor() == PlayerColor.White) {
            getCb().updatePieces(PlayerColor.Black, getCb().getKing(PlayerColor.Black));
        }
        else {
            getCb().updatePieces(PlayerColor.White, getCb().getKing(PlayerColor.White));
        }
    }

    public boolean isCheckmated() {
        if (!isInCheck() || !getMoves().isEmpty() || !getCaptures().isEmpty()) {
            return false;
        }

        List<Piece> piecesCheckingKingList = new ArrayList<>();
        for (Piece p : getCb().getPieceList()) {
            if (p.getCaptures().contains(getPosition())) {
                piecesCheckingKingList.add(p);
            }
        }

        Piece checkingPiece;
        if (piecesCheckingKingList.size() > 1) {
            return true;
        }
        else {
            checkingPiece = piecesCheckingKingList.getFirst();
        }

        for (Piece p : getCb().getPieceList()) {
            if (p.getPlayerColor() == getPlayerColor()) {
//                if (p.getCaptures().contains(checkingPiece.getPosition())) {
//                    return false;
//                }
                if (!p.getCaptures().isEmpty() || !p.getMoves().isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

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

    public List<Piece> getPiecesCheckingKing() {
        return piecesCheckingKing;
    }
}
