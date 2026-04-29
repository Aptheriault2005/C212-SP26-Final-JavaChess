import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    @Test
    void backrankCheckmate() {
        ChessBoard cb = new ChessBoard(false);
        King whiteKing = new King(Position.at(0,4), cb, Piece.PlayerColor.White);
        King blackKing = new King(Position.at(7,4), cb, Piece.PlayerColor.Black);
        Rook blackRook1 = new Rook(Position.at(1, 0), cb, Piece.PlayerColor.Black);
        Rook blackRook2 = new Rook(Position.at(7, 7), cb, Piece.PlayerColor.Black);
        cb.addNewPiece(whiteKing);
        cb.addNewPiece(blackKing);
        cb.addNewPiece(blackRook1);
        cb.addNewPiece(blackRook2);
        cb.updatePieces();

        cb.printPieceMoves(whiteKing);

        assertTrue(cb.movePiece(blackRook2, Position.at(0,7)));

        cb.printPieceMoves(blackRook1);
        cb.printPieceMoves(blackRook2);
        cb.printPieceMoves(whiteKing);

        assertEquals(0, whiteKing.getMoves().size());
        assertTrue(whiteKing.isInCheck());
        assertTrue(whiteKing.isCheckmated());
    }

    @Test
    void KingAndQueenCheckmate() {
        ChessBoard cb = new ChessBoard(false);
        King blackKing = new King(Position.at(7,7), cb, Piece.PlayerColor.Black);
        King whiteKing = new King(Position.at(5,5), cb, Piece.PlayerColor.White);
        Queen whiteQueen = new Queen(Position.at(4,6), cb, Piece.PlayerColor.White);
        cb.addNewPiece(blackKing);
        cb.addNewPiece(whiteKing);
        cb.addNewPiece(whiteQueen);
        cb.updatePieces();

        assertFalse(blackKing.isCheckmated());
        cb.printPieceMoves(blackKing);
        cb.movePiece(blackKing, Position.at(6,7));

        cb.movePiece(whiteQueen, Position.at(6,6));
        cb.printPieceMoves(blackKing);
        assertTrue(blackKing.isCheckmated());
    }

    @Test
    void SmotheredCheckmate() {
        ChessBoard cb = new ChessBoard(false);
        King whiteKing = new King(Position.at(0,0), cb, Piece.PlayerColor.White);
        King blackKing = new King(Position.at(7,7), cb, Piece.PlayerColor.Black);
        Pawn blackPawn1 = new Pawn(Position.at(6,6), cb, Piece.PlayerColor.Black);
        Pawn blackPawn2 = new Pawn(Position.at(6,7), cb, Piece.PlayerColor.Black);
        Rook blackRook = new Rook(Position.at(7,6), cb, Piece.PlayerColor.Black);
        Knight whiteKnight = new Knight(Position.at(4,4), cb, Piece.PlayerColor.White);
        cb.addNewPiece(whiteKing);
        cb.addNewPiece(blackKing);
        cb.addNewPiece(blackPawn1);
        cb.addNewPiece(blackPawn2);
        cb.addNewPiece(blackRook);
        cb.addNewPiece(whiteKnight);
        cb.updatePieces();

        cb.printPieceMoves(blackKing);

        assertFalse(blackKing.isCheckmated());
        cb.movePiece(whiteKnight, Position.at(6, 5));

        cb.printPieceMoves(blackKing);

        assertTrue(blackKing.isCheckmated());
    }

    @Test
    void NotACheckmateKingCanCaptureQueen() {
        ChessBoard cb = new ChessBoard(false);
        King blackKing = new King(Position.at(7,7), cb, Piece.PlayerColor.Black);
        King whiteKing = new King(Position.at(0,0), cb, Piece.PlayerColor.White);
        Queen whiteQueen = new Queen(Position.at(4,6), cb, Piece.PlayerColor.White);
        cb.addNewPiece(blackKing);
        cb.addNewPiece(whiteKing);
        cb.addNewPiece(whiteQueen);
        cb.updatePieces();

        assertFalse(blackKing.isCheckmated());
        cb.printPieceMoves(blackKing);
        cb.movePiece(blackKing, Position.at(6,7));

        cb.movePiece(whiteQueen, Position.at(6,6));
        cb.printBoard();

        cb.printPieceMoves(blackKing);
        assertEquals(1, blackKing.getCaptures().size());
        assertEquals(0, blackKing.getMoves().size());
        assertFalse(blackKing.isCheckmated());
        assertTrue(cb.movePiece(blackKing, whiteQueen.getPosition()));

        cb.printBoard();
    }

    @Test
    void NotACheckmateKingCanMove() {
        ChessBoard cb = new ChessBoard(false);
        King whiteKing = new King(Position.at(0,4), cb, Piece.PlayerColor.White);
        King blackKing = new King(Position.at(7,4), cb, Piece.PlayerColor.Black);
        Rook blackRook1 = new Rook(Position.at(0, 0), cb, Piece.PlayerColor.Black);
        Rook blackRook2 = new Rook(Position.at(7, 7), cb, Piece.PlayerColor.Black);
        Bishop blackBishop1 = new Bishop(Position.at(4, 0), cb, Piece.PlayerColor.Black);
        Bishop blackBishop2 = new Bishop(Position.at(2, 6), cb, Piece.PlayerColor.Black);
        cb.addNewPiece(blackKing);
        cb.addNewPiece(whiteKing);
        cb.addNewPiece(blackRook1);
        cb.addNewPiece(blackRook2);
        cb.addNewPiece(blackBishop1);
        cb.addNewPiece(blackBishop2);
        cb.updatePieces();

        cb.printPieceMoves(whiteKing);

        assertTrue(cb.movePiece(blackRook2, Position.at(0,7)));

        cb.printPieceMoves(whiteKing);

        assertEquals(1, whiteKing.getMoves().size());
        assertTrue(whiteKing.isInCheck());
        assertFalse(whiteKing.isCheckmated());
    }

    @Test
    void NotACheckmateBishopCanCapture() {
        ChessBoard cb = new ChessBoard(false);
        King blackKing = new King(Position.at(7,7), cb, Piece.PlayerColor.Black);
        Bishop blackBishop = new Bishop(Position.at(7, 5), cb, Piece.PlayerColor.Black);
        King whiteKing = new King(Position.at(5,5), cb, Piece.PlayerColor.White);
        Queen whiteQueen = new Queen(Position.at(4,6), cb, Piece.PlayerColor.White);
        cb.addNewPiece(blackKing);
        cb.addNewPiece(blackBishop);
        cb.addNewPiece(whiteKing);
        cb.addNewPiece(whiteQueen);
        cb.updatePieces();

        assertFalse(blackKing.isCheckmated());
        cb.printPieceMoves(blackKing);
        cb.movePiece(blackKing, Position.at(6,7));

        cb.movePiece(whiteQueen, Position.at(6,6));
        cb.printPieceMoves(blackBishop);

        // the black king is in check so the only valid move is to capture the attacking piece
        assertTrue(blackKing.isInCheck());
        assertFalse(blackKing.isCheckmated());
        assertEquals(1, blackBishop.getCaptures().size());
        assertEquals(0, blackBishop.getMoves().size());
        assertTrue(cb.movePiece(blackBishop, Position.at(6,6)));

        assertFalse(blackKing.isInCheck());
        assertFalse(blackKing.isCheckmated());
        assertTrue(whiteKing.isInCheck());
        assertFalse(whiteKing.isCheckmated());
    }

    @Test
    void NotACheckmateQueenCanBlock() {
        ChessBoard cb = new ChessBoard(false);
        King whiteKing = new King(Position.at(0,4), cb, Piece.PlayerColor.White);
        Queen whiteQueen = new Queen(Position.at(2, 3), cb, Piece.PlayerColor.White);
        King blackKing = new King(Position.at(7,4), cb, Piece.PlayerColor.Black);
        Rook blackRook1 = new Rook(Position.at(1, 0), cb, Piece.PlayerColor.Black);
        Rook blackRook2 = new Rook(Position.at(7, 7), cb, Piece.PlayerColor.Black);
        Pawn whitePawn1 = new Pawn(Position.at(3,2), cb, Piece.PlayerColor.White);
        Pawn whitePawn2 = new Pawn(Position.at(3,3), cb, Piece.PlayerColor.White);
        Pawn whitePawn3 = new Pawn(Position.at(3,4), cb, Piece.PlayerColor.White);
        cb.addNewPiece(whiteKing);
        cb.addNewPiece(whiteQueen);
        cb.addNewPiece(whitePawn1);
        cb.addNewPiece(whitePawn2);
        cb.addNewPiece(whitePawn3);
        cb.addNewPiece(blackKing);
        cb.addNewPiece(blackRook1);
        cb.addNewPiece(blackRook2);
        cb.updatePieces();

        assertFalse(whiteKing.isInCheck());
        assertFalse(blackKing.isInCheck());

        assertTrue(cb.movePiece(blackRook2, Position.at(0,7)));

        cb.printPieceMoves(whiteKing);
        cb.printPieceMoves(whiteQueen);

        // The white king is in check and can't move but the white queen can block
        assertEquals(0, whiteKing.getCaptures().size());
        assertEquals(1, whiteQueen.getMoves().size());
        assertTrue(whiteKing.isInCheck());
        assertFalse(whiteKing.isCheckmated());

        assertTrue(cb.movePiece(whiteQueen, Position.at(0,5)));
        assertFalse(whiteKing.isInCheck());
        assertFalse(whiteKing.isCheckmated());

        cb.printBoard();

        // The black rook captures the queen but the king can capture back
        assertTrue(cb.movePiece(blackRook2, whiteQueen.getPosition()));
        assertTrue(whiteKing.isInCheck());
        assertFalse(whiteKing.isCheckmated());

        cb.printBoard();

        // The white king's only move is to capture the black rook to avoid check
        assertEquals(1, whiteKing.getCaptures().size());
        assertEquals(0, whiteKing.getMoves().size());
        assertTrue(cb.movePiece(whiteKing, blackRook2.getPosition()));
        assertFalse(whiteKing.isInCheck());
        assertFalse(whiteKing.isCheckmated());

        cb.printBoard();
    }

    @Test
    void kingSideCastle() {
        ChessBoard cb = new ChessBoard(true);

        King whiteKing = cb.getKing(Piece.PlayerColor.White);
        assertTrue(cb.movePiece(cb.getPieceAt(Position.at(1,4)), Position.at(3,4)));
        assertTrue(cb.movePiece(cb.getPieceAt(Position.at(0,5)), Position.at(1,4)));
        assertTrue(cb.movePiece(cb.getPieceAt(Position.at(0,6)), Position.at(2,5)));
        assertTrue(whiteKing.getMoves().contains(Position.at(0,6)));
        cb.printPieceMoves(whiteKing);

        assertTrue(cb.movePiece(whiteKing, Position.at(0,6)));
        cb.printBoard();

        assertInstanceOf(Rook.class, cb.getPieceAt(Position.at(0, 5)));
        assertInstanceOf(King.class, cb.getPieceAt(Position.at(0, 6)));

        King blackKing = cb.getKing(Piece.PlayerColor.Black);
        assertTrue(cb.movePiece(cb.getPieceAt(Position.at(6,4)), Position.at(4,4)));
        assertTrue(cb.movePiece(cb.getPieceAt(Position.at(7,5)), Position.at(6,4)));
        assertTrue(cb.movePiece(cb.getPieceAt(Position.at(7,6)), Position.at(5,5)));
        assertTrue(blackKing.getMoves().contains(Position.at(7,6)));
        cb.printPieceMoves(blackKing);

        assertTrue(cb.movePiece(blackKing, Position.at(7,6)));
        cb.printBoard();

        assertInstanceOf(Rook.class, cb.getPieceAt(Position.at(7, 5)));
        assertInstanceOf(King.class, cb.getPieceAt(Position.at(7, 6)));
    }

    @Test
    void queenSideCastle() {
        ChessBoard cb = new ChessBoard(true);
        King whiteKing = cb.getKing(Piece.PlayerColor.White);
        cb.capturePiece(cb.getPieceAt(Position.at(0,1)));
        cb.capturePiece(cb.getPieceAt(Position.at(0,2)));
        cb.capturePiece(cb.getPieceAt(Position.at(0,3)));
        King blackKing = cb.getKing(Piece.PlayerColor.Black);
        cb.capturePiece(cb.getPieceAt(Position.at(7,1)));
        cb.capturePiece(cb.getPieceAt(Position.at(7,2)));
        cb.capturePiece(cb.getPieceAt(Position.at(7,3)));
        cb.updatePieces();

        cb.printPieceMoves(whiteKing);
        cb.printPieceMoves(blackKing);

        assertTrue(whiteKing.getMoves().contains(Position.at(0,2)));
        assertTrue(blackKing.getMoves().contains(Position.at(7,2)));

        assertTrue(cb.movePiece(whiteKing, Position.at(0,2)));
        cb.printBoard();
        assertInstanceOf(Rook.class, cb.getPieceAt(Position.at(0, 3)));
        assertInstanceOf(King.class, cb.getPieceAt(Position.at(0, 2)));

        assertTrue(cb.movePiece(blackKing, Position.at(7,2)));
        cb.printBoard();
        assertInstanceOf(Rook.class, cb.getPieceAt(Position.at(7, 3)));
        assertInstanceOf(King.class, cb.getPieceAt(Position.at(7, 2)));
    }

    @Test
    void invalidCastleNoCastlingThroughCheck() {
        ChessBoard cb = new ChessBoard(true);
        King whiteKing = cb.getKing(Piece.PlayerColor.White);
        King blackKing = cb.getKing(Piece.PlayerColor.Black);
        cb.capturePiece(cb.getPieceAt(Position.at(0,1)));
        cb.capturePiece(cb.getPieceAt(Position.at(0,2)));
        cb.capturePiece(cb.getPieceAt(Position.at(0,3)));
        cb.capturePiece(cb.getPieceAt(Position.at(1,3)));
        cb.capturePiece(cb.getPieceAt(Position.at(6,3)));
        cb.capturePiece(cb.getPieceAt(Position.at(7,6)));
        cb.capturePiece(cb.getPieceAt(Position.at(7,5)));
        cb.capturePiece(cb.getPieceAt(Position.at(6,5)));
        cb.forceMovePiece(cb.getPieceAt(Position.at(0,7)), Position.at(3,5));
        cb.updatePieces();

        cb.printPieceMoves(whiteKing);
        assertEquals(0, whiteKing.getMoves().size());
        assertFalse(cb.movePiece(whiteKing, Position.at(0,2)));

        cb.printPieceMoves(blackKing);
        assertEquals(1, blackKing.getMoves().size());
        assertFalse(cb.movePiece(blackKing, Position.at(0,6)));
    }

    @Test
    void invalidCastleNoCastlingResultingInCheck() {
        ChessBoard cb = new ChessBoard(true);
        King blackKing = cb.getKing(Piece.PlayerColor.Black);
        cb.capturePiece(cb.getPieceAt(Position.at(7,1)));
        cb.capturePiece(cb.getPieceAt(Position.at(7,2)));
        cb.capturePiece(cb.getPieceAt(Position.at(7,3)));
        cb.forceMovePiece(cb.getPieceAt(Position.at(0,3)), Position.at(6,1));
        cb.updatePieces();

        cb.printPieceMoves(blackKing);
        assertEquals(1, blackKing.getMoves().size());
        assertFalse(cb.movePiece(blackKing, Position.at(0,2)));
    }
}