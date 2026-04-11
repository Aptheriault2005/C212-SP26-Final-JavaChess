import java.util.HashSet;

public class King extends Piece implements IFirstMove {

    private boolean firstMove = true;

    public King(Position pos, ChessBoard chessBoard, PlayerColor player) {
        super(pos, chessBoard, player);
    }

    @Override
    public char getChar() {
        return 'K';
    }

    @Override
    public void update() {
        this.setMoves(new HashSet<>());
        this.setCaptures(new HashSet<>());
        this.addMovesAndCapturesAtOffset(1,1);
        this.addMovesAndCapturesAtOffset(1,-1);
        this.addMovesAndCapturesAtOffset(-1,1);
        this.addMovesAndCapturesAtOffset(-1,-1);
        this.addMovesAndCapturesAtOffset(0,1);
        this.addMovesAndCapturesAtOffset(0,-1);
        this.addMovesAndCapturesAtOffset(1,0);
        this.addMovesAndCapturesAtOffset(-1,0);
    }

    @Override
    public boolean getIsFirstMove() {
        return firstMove;
    }

    @Override
    public void setIsFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }
}
