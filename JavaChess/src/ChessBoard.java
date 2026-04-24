import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    public static final int ROWS = 8;
    public static final int COLUMNS = 8;

    private final Map<Position, Piece> pieceMap = new HashMap<>();
    private final List<Piece> pieceList = new ArrayList<>();
    private King whiteKing = null;
    private King blackKing = null;

    public ChessBoard(boolean startingPieces) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                pieceMap.put(new Position(i,j), null);
            }
        }

        if (!startingPieces) {
            return;
        }

        addPiece(new Rook(Position.at(0,0), this, Piece.PlayerColor.White));
        addPiece(new Knight(Position.at(0,1), this, Piece.PlayerColor.White));
        addPiece(new Bishop(Position.at(0,2), this, Piece.PlayerColor.White));
        addPiece(new Queen(Position.at(0,3), this, Piece.PlayerColor.White));
        addPiece(new King(Position.at(0,4), this, Piece.PlayerColor.White));
        addPiece(new Bishop(Position.at(0,5), this, Piece.PlayerColor.White));
        addPiece(new Knight(Position.at(0,6), this, Piece.PlayerColor.White));
        addPiece(new Rook(Position.at(0,7), this, Piece.PlayerColor.White));
        for (int i = 0; i < ROWS; i++) {
            addPiece(new Pawn(Position.at(1, i), this, Piece.PlayerColor.White));
        }

        addPiece(new Rook(Position.at(7,0), this, Piece.PlayerColor.Black));
        addPiece(new Knight(Position.at(7,1), this, Piece.PlayerColor.Black));
        addPiece(new Bishop(Position.at(7,2), this, Piece.PlayerColor.Black));
        addPiece(new Queen(Position.at(7,3), this, Piece.PlayerColor.Black));
        addPiece(new King(Position.at(7,4), this, Piece.PlayerColor.Black));
        addPiece(new Bishop(Position.at(7,5), this, Piece.PlayerColor.Black));
        addPiece(new Knight(Position.at(7,6), this, Piece.PlayerColor.Black));
        addPiece(new Rook(Position.at(7,7), this, Piece.PlayerColor.Black));
        for (int i = 0; i < ROWS; i++) {
            addPiece(new Pawn(Position.at(6, i), this, Piece.PlayerColor.Black));
        }

        updatePieces();
    }

    public ChessBoard() {
        this(false);
    }

    public void updatePieces() {
        for (Piece p : pieceList) {
            p.update();
        }
    }

    public void addPiece(Piece piece) {
        pieceMap.put(piece.getPosition(), piece);
        pieceList.add(piece);
        if (piece instanceof King) {
            if (piece.getPlayerColor() == Piece.PlayerColor.White) {
                whiteKing = (King) piece;
            }
            else {
                blackKing = (King) piece;
            }
        }
    }

    public boolean movePiece(Piece piece, Position pos) {
        if (piece.isValidCapture(pos)) {
            pieceMap.put(piece.getPosition(), null);
            piece.setPosition(pos);
            pieceMap.put(piece.getPosition(), piece);
            updatePieces();
            return true;
        }
        else if (piece.isValidMove(pos)) {
            pieceMap.put(piece.getPosition(), null);
            piece.setPosition(pos);
            pieceMap.put(piece.getPosition(), piece);
            if (piece instanceof IFirstMove) {
                ((IFirstMove) piece).setIsFirstMove(false);
            }
            updatePieces();
            return true;
        }
        return false;
    }

    public boolean isPositionAttacked(Position pos, Piece.PlayerColor defenderColor) {
        for (Piece piece : pieceList) {
            if (piece.getPlayerColor() != defenderColor && (piece.getMoves().contains(pos) || piece.getCaptures().contains(pos))) {
                return true;
            }
        }
        return false;
    }

    public King getKing(Piece.PlayerColor player) {
        if (player == Piece.PlayerColor.White) {
            return whiteKing;
        }
        else {
            return blackKing;
        }
    }

    public List<Piece> getPieceList() {
        return pieceList;
    }

    public Piece getPieceAt(Position position) {
        if (pieceMap.containsKey(position)) {
            return pieceMap.get(position);
        }
        return null;
    }

    public boolean isValidPosition(Position position) {
        return pieceMap.containsKey(position);
    }

    public void printBoard() {
        for (int i = ROWS - 1; i >= 0; i--) {
            for (int j = 0; j < COLUMNS; j++) {
                Position pos = new Position(i,j);
                if (pieceMap.get(pos) != null)  {
                    System.out.print(pieceMap.get(pos).getChar());
                }
                else {
                    System.out.print("-");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printPieceMoves(Piece piece) {
        for (int i = ROWS - 1; i >= 0; i--) {
            for (int j = 0; j < COLUMNS; j++) {
                Position pos = new Position(i,j);
                if (pieceMap.get(pos) != null)  {
                    if (piece.getCaptures().contains(pos)) {
                        System.out.print("x");
                    }
                    else {
                        System.out.print(pieceMap.get(pos).getChar());
                    }
                }
                else {
                    if (piece.getMoves().contains(pos)) {
                        System.out.print("o");
                    }
                    else {
                        System.out.print("-");
                    }
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
