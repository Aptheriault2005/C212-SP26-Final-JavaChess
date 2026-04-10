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
        this.setMoves(new HashSet<>());
        this.setCaptures(new HashSet<>());
        this.addMovesAndCapturesInLine(0,1);
        this.addMovesAndCapturesInLine(0,-1);
        this.addMovesAndCapturesInLine(1,0);
        this.addMovesAndCapturesInLine(-1,0);
    }
}
