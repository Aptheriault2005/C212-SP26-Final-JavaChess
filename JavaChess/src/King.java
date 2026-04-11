public class King extends Piece {

    public King(Position pos, ChessBoard chessBoard, PlayerColor player) {
        super(pos, chessBoard, player);
    }

    @Override
    public char getChar() {
        return 'K';
    }

    @Override
    public void update() {

    }
}
