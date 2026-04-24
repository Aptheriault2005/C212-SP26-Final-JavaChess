import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    @Test
    void kingMovement() {

    }

    @Test
    void backrankCheckmate() {
        ChessBoard cb = new ChessBoard(false);
        King whiteKing = new King(Position.at(0,4), cb, Piece.PlayerColor.White);
        Rook blackRook1 = new Rook(Position.at(1, 0), cb, Piece.PlayerColor.Black);
        Rook blackRook2 = new Rook(Position.at(7, 7), cb, Piece.PlayerColor.Black);
        cb.addPiece(whiteKing);
        cb.addPiece(blackRook1);
        cb.addPiece(blackRook2);
        cb.updatePieces();

        cb.printPieceMoves(whiteKing);

        assertTrue(cb.movePiece(blackRook2, Position.at(0,7)));

        cb.printPieceMoves(blackRook1);
        cb.printPieceMoves(blackRook2);
        cb.printPieceMoves(whiteKing);

        assertTrue(cb.isPositionAttacked(Position.at(0,3), Piece.PlayerColor.White));
        assertTrue(cb.isPositionAttacked(Position.at(0,4), Piece.PlayerColor.White));
        assertTrue(cb.isPositionAttacked(Position.at(0,5), Piece.PlayerColor.White));
        assertTrue(cb.isPositionAttacked(Position.at(1,3), Piece.PlayerColor.White));
        assertTrue(cb.isPositionAttacked(Position.at(1,4), Piece.PlayerColor.White));
        assertTrue(cb.isPositionAttacked(Position.at(1,5), Piece.PlayerColor.White));
        assertEquals(0, whiteKing.getMoves().size());
        assertTrue(whiteKing.isInCheck());
        assertTrue(whiteKing.isCheckmated());
    }

    @Test
    void isInCheck() {
    }
}