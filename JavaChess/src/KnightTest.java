import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

    @Test
    void knightMovement() {
        ChessBoard cb = new ChessBoard();
        Knight whiteKnight = new Knight(Position.at(3,3), cb, Piece.PlayerColor.White);
        Knight blackKnight = new Knight(Position.at(5,3), cb, Piece.PlayerColor.Black);
        cb.addNewPiece(whiteKnight);
        cb.addNewPiece(blackKnight);
        cb.updatePieces();

        cb.printPieceMoves(whiteKnight);
        assertEquals(8, whiteKnight.getMoves().size());
        assertEquals(0, whiteKnight.getCaptures().size());

        assertTrue(cb.movePiece(whiteKnight, Position.at(4,1)));
        cb.printPieceMoves(whiteKnight);

        assertEquals(5, whiteKnight.getMoves().size());
        assertEquals(1, whiteKnight.getCaptures().size());

        assertTrue(cb.movePiece(blackKnight, Position.at(4,1)));
        cb.printPieceMoves(blackKnight);

        assertEquals(6, blackKnight.getMoves().size());
        assertEquals(0, blackKnight.getCaptures().size());
    }
}