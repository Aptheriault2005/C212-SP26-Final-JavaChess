import java.util.HashSet;

public class Queen extends Piece{

    public Queen(Position pos, ChessBoard chessBoard, PlayerColor player) {
        super(pos, chessBoard, player);
    }

    @Override
    public char getChar() {
        return 'Q';
    }

    @Override
    public void update() {
        this.setMoves(new HashSet<>());
        this.setCaptures(new HashSet<>());
        this.addMovesAndCapturesInLine(1,1);
        this.addMovesAndCapturesInLine(1,-1);
        this.addMovesAndCapturesInLine(-1,1);
        this.addMovesAndCapturesInLine(-1,-1);
        this.addMovesAndCapturesInLine(0,1);
        this.addMovesAndCapturesInLine(0,-1);
        this.addMovesAndCapturesInLine(1,0);
        this.addMovesAndCapturesInLine(-1,0);

        if (!getTryToValidate()) return;
        validateMoves();
    }

    @Override
    public Piece copy(ChessBoard newBoard) {
        Queen copied = new Queen(getPosition(), newBoard, getPlayerColor());
        return copied;
    }
}
