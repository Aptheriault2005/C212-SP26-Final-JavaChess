import java.util.HashSet;

public class Knight extends Piece{
    public Knight(Position pos, ChessBoard chessBoard, PlayerColor player) {
        super(pos, chessBoard, player);
    }

    @Override
    public char getChar() {
        return 'N';
    }

    @Override
    public void update() {
        this.setMoves(new HashSet<>());
        this.setCaptures(new HashSet<>());
        this.addMovesAndCapturesAtOffset(1,2);
        this.addMovesAndCapturesAtOffset(-1,2);
        this.addMovesAndCapturesAtOffset(1,-2);
        this.addMovesAndCapturesAtOffset(-1,-2);
        this.addMovesAndCapturesAtOffset(2,1);
        this.addMovesAndCapturesAtOffset(2,-1);
        this.addMovesAndCapturesAtOffset(-2,1);
        this.addMovesAndCapturesAtOffset(-2,-1);
    }
}
