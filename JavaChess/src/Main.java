public class Main {

    public static void main(String[] args) {
        ChessBoard cb = new ChessBoard();
        Piece rook = new Rook(new Position(7,0), cb, Piece.color.White);
        cb.addPiece(rook);
        cb.printBoard();
        System.out.println(rook.move(new Position(5,0)));
        System.out.println(rook.move(new Position(6,0)));
        cb.printBoard();
    }
}
