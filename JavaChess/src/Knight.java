public class Knight extends Piece{
    public Knight(Position pos, ChessBoard chessBoard, PlayerColor player) {
        super(pos, chessBoard, player);
    }

    @Override
    public char getChar() {
        return 'H';
    }

    @Override
    public void update() {

    }
}
