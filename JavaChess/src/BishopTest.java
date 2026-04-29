import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    @Test
    void bishopMovement() {
        ChessBoard cb = new ChessBoard();
        Piece bishopW1 = new Bishop(new Position(0,2), cb, Piece.PlayerColor.White);
        Piece bishopW2 = new Bishop(new Position(0,5), cb, Piece.PlayerColor.White);
        Piece bishopB2 = new Bishop(new Position(7,2), cb, Piece.PlayerColor.Black);
        Piece bishopB1 = new Bishop(new Position(7,5), cb, Piece.PlayerColor.Black);
        cb.addNewPiece(bishopW1);
        cb.addNewPiece(bishopW2);
        cb.addNewPiece(bishopB1);
        cb.addNewPiece(bishopB2);
        cb.updatePieces();

        assertEquals(7, bishopW1.getMoves().size());
        assertEquals(0, bishopW1.getCaptures().size());
        assertTrue(cb.movePiece(bishopW1, Position.at(5,7)));

        assertEquals(6, bishopB1.getMoves().size());
        assertEquals(1, bishopB1.getCaptures().size());
        assertTrue(cb.movePiece(bishopB1, Position.at(5,7)));

        cb.printBoard();
    }
}