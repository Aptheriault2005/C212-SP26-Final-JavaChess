import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChessBoardTest {

    @Test
    void copy() {
        ChessBoard cb = new ChessBoard(true);
        ChessBoard cbCopy = cb.copy();

        for (int i = 0; i < cb.getPieceList().size(); i++) {
            assertNotEquals(cb.getPieceList().get(i), cbCopy.getPieceList().get(i));
        }
    }

    @Test
    void addNewPiece() {
        ChessBoard cb = new ChessBoard(true);
        cb.printBoard();
    }

//    @Test
//    void isPositionAttacked() {
//        ChessBoard cb = new ChessBoard(true);
//        cb.printBoard();
//
//        assertTrue(cb.isPositionAttacked(Position.at(2,0), Piece.PlayerColor.Black));
//        assertTrue(cb.isPositionAttacked(Position.at(5,0), Piece.PlayerColor.White));
//
//        //assertFalse(cb.isPositionAttacked(Position.at(1,4), Piece.PlayerColor.White));
//        //assertFalse(cb.isPositionAttacked(Position.at(6,3), Piece.PlayerColor.Black));
//
//        cb.printBoard();
//
//        cb.movePiece(cb.getPieceAt(Position.at(1, 4)), Position.at(3,4));
//        cb.movePiece(cb.getPieceAt(Position.at(6, 3)), Position.at(4,3));
//
//        cb.printBoard();
//
//        assertTrue(cb.isPositionAttacked(Position.at(3,4), Piece.PlayerColor.White));
//        assertTrue(cb.isPositionAttacked(Position.at(4,3), Piece.PlayerColor.Black));
//    }

    @Test
    void isPieceDefended() {
        ChessBoard cb = new ChessBoard(true);
        cb.printBoard();

        // all pieces and pawns are defended at start except for rooks
        for (Piece p : cb.getPieceList()) {
            if (p instanceof Rook) {
                assertFalse(p.isDefended());
            }
            else {
                assertTrue(p.isDefended());
            }
        }

        Piece pawnBW = cb.getPieceAt(Position.at(1,1));
        assertTrue(cb.movePiece(pawnBW, Position.at(3,1)));
        cb.printBoard();
        assertFalse(pawnBW.isDefended());
    }

    @Test
    void isPieceRemovedOnCapture() {
        ChessBoard cb = new ChessBoard();
        Piece knightW1 = new Knight(new Position(1,2), cb, Piece.PlayerColor.White);
        Piece knightW2 = new Knight(new Position(2,1), cb, Piece.PlayerColor.White);
        Piece bishopB1 = new Bishop(new Position(6,4), cb, Piece.PlayerColor.Black);
        Piece rookB1 = new Rook(new Position(0,0), cb, Piece.PlayerColor.Black);
        Piece rookB2 = new Rook(new Position(7,0), cb, Piece.PlayerColor.Black);
        Piece pawnW1 = new Pawn(Position.at(5,5), cb, Piece.PlayerColor.White);
        cb.addNewPiece(rookB1);
        cb.addNewPiece(rookB2);
        cb.addNewPiece(knightW1);
        cb.addNewPiece(knightW2);
        cb.addNewPiece(bishopB1);
        cb.addNewPiece(pawnW1);
        cb.updatePieces();

        cb.printBoard();

        assertEquals(6, cb.getPieceList().size());

        assertTrue(cb.movePiece(pawnW1, bishopB1.getPosition()));
        assertEquals(5, cb.getPieceList().size());
        assertFalse(cb.getPieceList().contains(bishopB1));

        cb.printBoard();

        assertTrue(cb.movePiece(knightW1, rookB1.getPosition()));
        assertEquals(4, cb.getPieceList().size());
        assertFalse(cb.getPieceList().contains(rookB1));

        cb.printBoard();

        assertTrue(cb.movePiece(rookB2, knightW1.getPosition()));
        assertEquals(3, cb.getPieceList().size());
        assertFalse(cb.getPieceList().contains(knightW1));

        cb.printBoard();

        assertTrue(cb.movePiece(knightW2, rookB2.getPosition()));
        assertEquals(2, cb.getPieceList().size());
        assertFalse(cb.getPieceList().contains(rookB2));

        cb.printBoard();
    }
}