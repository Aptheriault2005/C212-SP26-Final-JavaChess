public class Pawn extends Piece{

    public Pawn(Position pos, ChessBoard chessBoard, PlayerColor player) {
        super(pos, chessBoard, player);
    }

    @Override
    public char getChar() {
        return 'P';
    }

    @Override
    public void update() {

    }
}
