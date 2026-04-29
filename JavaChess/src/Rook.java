import java.util.HashSet;

public class Rook extends Piece {

    public Rook(Position pos, ChessBoard chessBoard, PlayerColor player) {
        super(pos, chessBoard, player);
    }

    @Override
    public char getChar() {
        return 'R';
    }

    @Override
    public void update() {
        getDefending().clear();
        this.setMoves(new HashSet<>());
        this.setCaptures(new HashSet<>());
        this.addMovesAndCapturesInLine(0,1);
        this.addMovesAndCapturesInLine(0,-1);
        this.addMovesAndCapturesInLine(1,0);
        this.addMovesAndCapturesInLine(-1,0);

        if (!getTryToValidate()) return;
        validateMoves();
//        removeIllegalMovesIfInCheck();
//        removeIllegalMovesIfPinned();
    }

    @Override
    public Piece copy(ChessBoard newBoard) {
        Rook copied = new Rook(getPosition(), newBoard, getPlayerColor());
        return copied;
    }
}
