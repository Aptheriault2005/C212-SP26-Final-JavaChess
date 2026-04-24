import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChessBoardTest {

    @Test
    void addPiece() {
        ChessBoard cb = new ChessBoard(true);
        cb.printBoard();
    }

    @Test
    void isPositionAttacked() {
        ChessBoard cb = new ChessBoard(true);
        cb.printBoard();

        assertTrue(cb.isPositionAttacked(Position.at(2,0), Piece.PlayerColor.Black));
        assertTrue(cb.isPositionAttacked(Position.at(5,0), Piece.PlayerColor.White));

        assertFalse(cb.isPositionAttacked(Position.at(1,4), Piece.PlayerColor.White));
        assertFalse(cb.isPositionAttacked(Position.at(6,3), Piece.PlayerColor.Black));

        cb.movePiece(cb.getPieceAt(Position.at(1, 4)), Position.at(3,4));
        cb.movePiece(cb.getPieceAt(Position.at(6, 3)), Position.at(4,3));

        cb.printBoard();

        assertTrue(cb.isPositionAttacked(Position.at(3,4), Piece.PlayerColor.White));
        assertTrue(cb.isPositionAttacked(Position.at(4,3), Piece.PlayerColor.Black));
    }
}