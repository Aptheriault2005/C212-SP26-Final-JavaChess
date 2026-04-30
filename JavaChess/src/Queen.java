import java.util.HashSet;

public class Queen extends Piece{

    public Queen(Position pos, ChessBoard chessBoard, PlayerColor player) {
        super(pos, chessBoard, player);
    }

    /**
     * Gets the char representation of this
     * @return char representation of this
     */
    @Override
    public char getChar() {
        return 'Q';
    }

    /**
     * Sets moves and captures then validates them if trying to validate
     */
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

    /**
     * Creates a copy of this piece to a given board
     * @param newBoard given board
     * @return copy of this piece
     */
    @Override
    public Piece copy(ChessBoard newBoard) {
        Queen copied = new Queen(getPosition(), newBoard, getPlayerColor());
        return copied;
    }
}
