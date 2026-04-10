public class Main {

    public static void main(String[] args) {
        ChessBoard cb = new ChessBoard();
        Piece rookw1 = new Rook(new Position(0,0), cb, Piece.PlayerColor.White);
        Piece rookw2 = new Rook(new Position(0,1), cb, Piece.PlayerColor.White);
        Piece rookb1 = new Rook(new Position(7,0), cb, Piece.PlayerColor.Black);

        cb.addPiece(rookw1);
        cb.addPiece(rookw2);
        cb.addPiece(rookb1);
        cb.printBoard();
        cb.updatePieces();

        System.out.println(rookw1.getMoves().size());

    }
}
