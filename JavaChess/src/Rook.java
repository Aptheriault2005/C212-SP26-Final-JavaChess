import java.util.HashSet;

public class Rook extends Piece {

    public Rook(Position pos, ChessBoard chessBoard, color player) {
        super(pos, chessBoard, player);
    }

    @Override
    public char getChar() {
        return 'R';
    }

    @Override
    public HashSet<Position> getMoves() {
        HashSet<Position> moves = new HashSet<>();
        moves.add(this.position.getOffset(0,1));
        moves.add(this.position.getOffset(0,-1));
        moves.add(this.position.getOffset(1,0));
        moves.add(this.position.getOffset(-1,0));
        return moves;
    }

    @Override
    public HashSet<Position> getCaptures() {
        return null;
    }
}
