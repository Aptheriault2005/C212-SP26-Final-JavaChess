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
        cb.addNewPiece(whiteKing);
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
        King blackKing = new King(Position.at(7,7), cb, Piece.PlayerColor.Black);
        Pawn blackPawn1 = new Pawn(Position.at(6,6), cb, Piece.PlayerColor.Black);
        Pawn blackPawn2 = new Pawn(Position.at(6,7), cb, Piece.PlayerColor.Black);
        Rook blackRook = new Rook(Position.at(7,6), cb, Piece.PlayerColor.Black);
        Knight whiteKnight = new Knight(Position.at(4,4), cb, Piece.PlayerColor.White);
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
    void NotACheckmateKingCanCapture() {
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
        cb.printPieceMoves(blackKing);
        assertFalse(blackKing.isCheckmated());
    }

    @Test
    void NotACheckmateKingCanMove() {

    }
}