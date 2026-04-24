public class Main {

    public static void main(String[] args) {
        ChessBoard cb = new ChessBoard(true);

        cb.printPieceMoves(cb.getPieceAt(Position.at(1,4)));
        cb.movePiece(cb.getPieceAt(Position.at(1,4)), Position.at(3,4));
        cb.printPieceMoves(cb.getPieceAt(Position.at(3,4)));

        cb.printPieceMoves(cb.getPieceAt(Position.at(6,3)));
        cb.movePiece(cb.getPieceAt(Position.at(6,3)), Position.at(4,3));
        cb.printPieceMoves(cb.getPieceAt(Position.at(4,3)));

        cb.printPieceMoves(cb.getPieceAt(Position.at(3,4)));
        cb.movePiece(cb.getPieceAt(Position.at(3,4)), Position.at(4,3));
        cb.printPieceMoves(cb.getPieceAt(Position.at(4,3)));

        cb.printPieceMoves(cb.getPieceAt(Position.at(7,3)));
        cb.movePiece(cb.getPieceAt(Position.at(7,3)), Position.at(4,3));
        cb.printPieceMoves(cb.getPieceAt(Position.at(4,3)));
    }
}
