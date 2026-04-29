import java.util.HashSet;

public class Bishop extends Piece{

    public Bishop(Position pos, ChessBoard chessBoard, PlayerColor player) {
        super(pos, chessBoard, player);
    }

    @Override
    public char getChar() {
        return 'B';
    }

    @Override
    public void update() {
        this.setMoves(new HashSet<>());
        this.setCaptures(new HashSet<>());
        this.addMovesAndCapturesInLine(1,1);
        this.addMovesAndCapturesInLine(1,-1);
        this.addMovesAndCapturesInLine(-1,1);
        this.addMovesAndCapturesInLine(-1,-1);

        if (!getTryToValidate()) return;
        validateMoves();
    }

    @Override
    public Piece copy(ChessBoard newBoard) {
        Bishop copied = new Bishop(getPosition(), newBoard, getPlayerColor());
        return copied;
    }
}
