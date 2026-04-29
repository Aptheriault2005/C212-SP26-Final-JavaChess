import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @Test
    void pawnMovement() {
        ChessBoard cb = new ChessBoard(true);

        Piece aPawnW = cb.getPieceAt(Position.at(1,0));
        assertEquals(2, aPawnW.getMoves().size());
        assertEquals(0, aPawnW.getCaptures().size());
        assertTrue(cb.movePiece(aPawnW, Position.at(3,0)));

        Piece bPawnB = cb.getPieceAt(Position.at(6,1));
        assertEquals(2, bPawnB.getMoves().size());
        assertEquals(0, bPawnB.getCaptures().size());
        assertTrue(cb.movePiece(bPawnB, Position.at(4,1)));

        cb.printPieceMoves(aPawnW);

        assertEquals(1, bPawnB.getMoves().size());
        assertEquals(1, aPawnW.getMoves().size());
        assertEquals(1, bPawnB.getCaptures().size());
        assertEquals(1, aPawnW.getCaptures().size());
        assertTrue(cb.movePiece(aPawnW, bPawnB.getPosition()));

        assertTrue(cb.movePiece(cb.getPieceAt(Position.at(0,1)), Position.at(2,2)));

        Piece cPawnW = cb.getPieceAt(Position.at(1,2));
        assertEquals(0, cPawnW.getMoves().size());
        assertEquals(0, cPawnW.getCaptures().size());
        assertFalse(cb.movePiece(cPawnW, Position.at(3,2)));

        cb.printBoard();
    }

    @Test
    void enPessant() {
        ChessBoard cb = new ChessBoard(true);

        Pawn aPawnW = (Pawn) cb.getPieceAt(Position.at(1,0));
        assertEquals(2, aPawnW.getMoves().size());
        assertEquals(0, aPawnW.getCaptures().size());
        assertTrue(cb.movePiece(aPawnW, Position.at(3,0)));
        assertTrue(aPawnW.isCanBeCapturedEnPessant());

        assertEquals(1, aPawnW.getMoves().size());
        assertEquals(0, aPawnW.getCaptures().size());
        assertTrue(cb.movePiece(aPawnW, Position.at(4,0)));
        assertFalse(aPawnW.isCanBeCapturedEnPessant());

        cb.printPieceMoves(aPawnW);

        Pawn bPawnB = (Pawn) cb.getPieceAt(Position.at(6,1));
        assertEquals(2, bPawnB.getMoves().size());
        assertEquals(0, bPawnB.getCaptures().size());
        assertTrue(cb.movePiece(bPawnB, Position.at(4,1)));
        assertTrue(bPawnB.isCanBeCapturedEnPessant());

        cb.printPieceMoves(aPawnW);

        assertEquals(1, aPawnW.getMoves().size());
        assertEquals(1, aPawnW.getCaptures().size());
        assertTrue(cb.movePiece(aPawnW, Position.at(5,1)));

        cb.printPieceMoves(aPawnW);
    }

    @Test
    void notEnPessant() {
        ChessBoard cb = new ChessBoard(true);

        Pawn ePawnW = (Pawn) cb.getPieceAt(Position.at(1,4));
        assertTrue(cb.movePiece(ePawnW, Position.at(3, 4)));

        Pawn dPawnB = (Pawn) cb.getPieceAt(Position.at(6,3));
        assertTrue(cb.movePiece(dPawnB, Position.at(4,3)));

        cb.printBoard();

        assertTrue(cb.movePiece(ePawnW, Position.at(4, 4)));

        cb.printBoard();

        assertFalse(dPawnB.isCanBeCapturedEnPessant());

        cb.printBoard();
    }
}