import static org.junit.jupiter.api.Assertions.*;

class ChessBoardTest {

    @org.junit.jupiter.api.Test
    void addPiece() {
        ChessBoard cb = new ChessBoard(true);
        //cb.addPiece(new Piece(new Position(cb,0,0)));
        //cb.addPiece(new Piece(new Position(cb,0,1)));
        cb.printBoard();
    }
}