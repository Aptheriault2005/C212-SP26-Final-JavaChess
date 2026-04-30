import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void noInvalidMovesMade() {
        Game testGame = new Game(false, false);
        testGame.getChessBoard().printBoard();

        assertFalse(testGame.makeMove(testGame.getChessBoard().getPieceAt(Position.at(4, 4)), Position.at(3,4)));
        assertEquals(Piece.PlayerColor.White, testGame.getCurrentPlayer());
        assertFalse(testGame.makeMove(testGame.getChessBoard().getPieceAt(Position.at(1, 4)), Position.at(1,4)));
        assertEquals(Piece.PlayerColor.White, testGame.getCurrentPlayer());
        assertFalse(testGame.makeMove(testGame.getChessBoard().getPieceAt(Position.at(6, 4)), Position.at(4,4)));
        assertEquals(Piece.PlayerColor.White, testGame.getCurrentPlayer());
        assertFalse(testGame.makeMove(testGame.getChessBoard().getPieceAt(Position.at(0, 0)), Position.at(4,0)));
        assertEquals(Piece.PlayerColor.White, testGame.getCurrentPlayer());

        assertTrue(testGame.makeMove(testGame.getChessBoard().getPieceAt(Position.at(1, 4)), Position.at(3,4)));
        testGame.getChessBoard().printBoard();

        assertFalse(testGame.makeMove(testGame.getChessBoard().getPieceAt(Position.at(4, 4)), Position.at(3,4)));
        assertEquals(Piece.PlayerColor.Black, testGame.getCurrentPlayer());
        assertFalse(testGame.makeMove(testGame.getChessBoard().getPieceAt(Position.at(6, 4)), Position.at(6,4)));
        assertEquals(Piece.PlayerColor.Black, testGame.getCurrentPlayer());
        assertFalse(testGame.makeMove(testGame.getChessBoard().getPieceAt(Position.at(3, 4)), Position.at(4,4)));
        assertEquals(Piece.PlayerColor.Black, testGame.getCurrentPlayer());
        assertFalse(testGame.makeMove(testGame.getChessBoard().getPieceAt(Position.at(7, 7)), Position.at(4,7)));
        assertEquals(Piece.PlayerColor.Black, testGame.getCurrentPlayer());

        assertTrue(testGame.makeMove(testGame.getChessBoard().getPieceAt(Position.at(6, 4)), Position.at(4,4)));
        testGame.getChessBoard().printBoard();
    }

    @Test
    void scholarsMateTest() {
        Game testGame = new Game(false, false);
        testGame.getChessBoard().printBoard();

        assertEquals(Piece.PlayerColor.White, testGame.getCurrentPlayer());
        assertEquals(2, testGame.getChessBoard().getPieceAt(new Position(1, 4)).getMoves().size());
        assertEquals(0, testGame.getChessBoard().getPieceAt(new Position(1, 4)).getCaptures().size());
        assertTrue(testGame.makeMove(testGame.getChessBoard().getPieceAt(new Position(1, 4)), new Position(3, 4)));

        testGame.getChessBoard().printBoard();

        assertEquals(Piece.PlayerColor.Black, testGame.getCurrentPlayer());
        assertEquals(2, testGame.getChessBoard().getPieceAt(new Position(6, 4)).getMoves().size());
        assertEquals(0, testGame.getChessBoard().getPieceAt(new Position(6, 4)).getCaptures().size());
        assertTrue(testGame.makeMove(testGame.getChessBoard().getPieceAt(new Position(6, 4)), new Position(4, 4)));

        testGame.getChessBoard().printBoard();

        assertEquals(Piece.PlayerColor.White, testGame.getCurrentPlayer());
        assertEquals(4, testGame.getChessBoard().getPieceAt(new Position(0, 3)).getMoves().size());
        assertEquals(0, testGame.getChessBoard().getPieceAt(new Position(0, 3)).getCaptures().size());
        assertTrue(testGame.makeMove(testGame.getChessBoard().getPieceAt(new Position(0, 3)), new Position(4, 7)));

        testGame.getChessBoard().printBoard();

        assertEquals(Piece.PlayerColor.Black, testGame.getCurrentPlayer());
        assertEquals(2, testGame.getChessBoard().getPieceAt(new Position(7, 1)).getMoves().size());
        assertEquals(0, testGame.getChessBoard().getPieceAt(new Position(7, 1)).getCaptures().size());
        assertTrue(testGame.makeMove(testGame.getChessBoard().getPieceAt(new Position(7, 1)), new Position(5, 2)));

        testGame.getChessBoard().printBoard();

        assertEquals(Piece.PlayerColor.White, testGame.getCurrentPlayer());
        assertEquals(5, testGame.getChessBoard().getPieceAt(new Position(0, 5)).getMoves().size());
        assertEquals(0, testGame.getChessBoard().getPieceAt(new Position(0, 5)).getCaptures().size());
        assertTrue(testGame.makeMove(testGame.getChessBoard().getPieceAt(new Position(0, 5)), new Position(3, 2)));

        testGame.getChessBoard().printBoard();

        assertEquals(Piece.PlayerColor.Black, testGame.getCurrentPlayer());
        assertEquals(3, testGame.getChessBoard().getPieceAt(new Position(7, 6)).getMoves().size());
        assertEquals(0, testGame.getChessBoard().getPieceAt(new Position(7, 6)).getCaptures().size());
        assertTrue(testGame.makeMove(testGame.getChessBoard().getPieceAt(new Position(7, 6)), new Position(5, 5)));

        testGame.getChessBoard().printBoard();

        assertEquals(Piece.PlayerColor.White, testGame.getCurrentPlayer());
        assertEquals(10, testGame.getChessBoard().getPieceAt(new Position(4, 7)).getMoves().size());
        assertEquals(3, testGame.getChessBoard().getPieceAt(new Position(4, 7)).getCaptures().size());
        assertTrue(testGame.makeMove(testGame.getChessBoard().getPieceAt(new Position(4, 7)), new Position(6, 5)));

        testGame.getChessBoard().printBoard();

        King blackKing = testGame.getChessBoard().getKing(Piece.PlayerColor.Black);
        assertTrue(blackKing.isInCheck());
        assertTrue(blackKing.isCheckmated());
    }
}