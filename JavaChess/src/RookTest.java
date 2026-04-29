import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {

    @Test
    void RookMovement() {
        ChessBoard cb = new ChessBoard();
        Piece rookW1 = new Rook(new Position(0,0), cb, Piece.PlayerColor.White);
        Piece rookW2 = new Rook(new Position(0,1), cb, Piece.PlayerColor.White);
        Piece rookB1 = new Rook(new Position(7,0), cb, Piece.PlayerColor.Black);
        cb.addNewPiece(rookW1);
        cb.addNewPiece(rookW2);
        cb.addNewPiece(rookB1);
        cb.updatePieces();

        assertEquals(6, rookW1.getMoves().size());
        assertEquals(1, rookW1.getCaptures().size());
        assertTrue(rookW1.getCaptures().contains(rookB1.getPosition()));

        cb.movePiece(rookW2, Position.at(0,7));

        assertEquals(12, rookW1.getMoves().size());
        assertEquals(1, rookW1.getCaptures().size());
        assertTrue(rookW1.getCaptures().contains(rookB1.getPosition()));

        cb.movePiece(rookW2, Position.at(7,7));

        assertEquals(12, rookB1.getMoves().size());
        assertEquals(2, rookB1.getCaptures().size());
        assertTrue(rookB1.getCaptures().contains(rookW1.getPosition()));
        assertTrue(rookB1.getCaptures().contains(rookW2.getPosition()));
    }

    @Test
    void rookAttacksPositions() {
        ChessBoard cb = new ChessBoard();
        Piece rookW1 = new Rook(new Position(0,0), cb, Piece.PlayerColor.White);
        Piece rookW2 = new Rook(new Position(0,1), cb, Piece.PlayerColor.White);
        Piece rookB1 = new Rook(new Position(7,0), cb, Piece.PlayerColor.Black);
        cb.addNewPiece(rookW1);
        cb.addNewPiece(rookW2);
        cb.addNewPiece(rookB1);
        cb.updatePieces();

        cb.printBoard();
//        assertTrue(cb.isPositionAttacked(rookB1.getPosition(), rookB1.getPlayerColor()));
//        assertTrue(cb.isPositionAttacked(Position.at(6,0), Piece.PlayerColor.Black));
    }

}