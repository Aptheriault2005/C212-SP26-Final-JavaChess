import java.util.HashSet;

public class Knight extends Piece{
    public Knight(Position pos, ChessBoard chessBoard, PlayerColor player) {
        super(pos, chessBoard, player);
    }

    /**
     * Gets the char representation of this
     * @return char representation of this
     */
    @Override
    public char getChar() {
        return 'N';
    }

    /**
     * Sets moves and captures then validates them if trying to validate
     */
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

        if (!getTryToValidate()) return;
        validateMoves();
    }

    /**
     * Creates a copy of this piece to a given board
     * @param newBoard given board
     * @return copy of this piece
     */
    @Override
    public Piece copy(ChessBoard newBoard) {
        Knight copied = new Knight(getPosition(), newBoard, getPlayerColor());
        return copied;
    }
}
