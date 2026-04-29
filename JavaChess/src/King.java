import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class King extends Piece {

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

        if (!getTryToValidate()) return;
        validateMoves();
    }

    @Override
    public Piece copy(ChessBoard newBoard) {
        King copied = new King(getPosition(), newBoard, getPlayerColor());
        copied.firstMove = firstMove;
        return copied;
    }

    @Override
    public void onMove(Position start, Position end) {
        firstMove = false;
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
