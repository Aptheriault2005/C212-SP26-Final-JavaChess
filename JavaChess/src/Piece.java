import java.util.HashSet;

public abstract class Piece {

    public enum color {White, Black}
    public Position position;
    public ChessBoard cb;

    public Piece(Position pos, ChessBoard chessBoard, color player) {
        position = pos;
        cb = chessBoard;
    }

    public boolean move(Position newPos) {
        if (this.getMoves().contains(newPos)) {
            position = newPos;
            return true;
        }
        return false;
    }

    public char getChar() {
        return 'X';
    }

    public abstract HashSet<Position> getMoves();

    public abstract HashSet<Position> getCaptures();
}
