import java.util.*;

public class ChessBoard {

    public static final int ROWS = 8;
    public static final int COLUMNS = 8;

    private final Map<Position, Piece> pieceMap = new HashMap<>();
    private final List<Piece> pieceList = new ArrayList<>();
    private King whiteKing = null;
    private King blackKing = null;
    private ChessBoard validationBoard;

    public ChessBoard(boolean startingPieces) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                pieceMap.put(new Position(i,j), null);
            }
        }

        if (!startingPieces) {
            return;
        }

        addNewPiece(new Rook(Position.at(0,0), this, Piece.PlayerColor.White));
        addNewPiece(new Knight(Position.at(0,1), this, Piece.PlayerColor.White));
        addNewPiece(new Bishop(Position.at(0,2), this, Piece.PlayerColor.White));
        addNewPiece(new Queen(Position.at(0,3), this, Piece.PlayerColor.White));
        addNewPiece(new King(Position.at(0,4), this, Piece.PlayerColor.White));
        addNewPiece(new Bishop(Position.at(0,5), this, Piece.PlayerColor.White));
        addNewPiece(new Knight(Position.at(0,6), this, Piece.PlayerColor.White));
        addNewPiece(new Rook(Position.at(0,7), this, Piece.PlayerColor.White));
        for (int i = 0; i < ROWS; i++) {
            addNewPiece(new Pawn(Position.at(1, i), this, Piece.PlayerColor.White));
        }

        addNewPiece(new Rook(Position.at(7,0), this, Piece.PlayerColor.Black));
        addNewPiece(new Knight(Position.at(7,1), this, Piece.PlayerColor.Black));
        addNewPiece(new Bishop(Position.at(7,2), this, Piece.PlayerColor.Black));
        addNewPiece(new Queen(Position.at(7,3), this, Piece.PlayerColor.Black));
        addNewPiece(new King(Position.at(7,4), this, Piece.PlayerColor.Black));
        addNewPiece(new Bishop(Position.at(7,5), this, Piece.PlayerColor.Black));
        addNewPiece(new Knight(Position.at(7,6), this, Piece.PlayerColor.Black));
        addNewPiece(new Rook(Position.at(7,7), this, Piece.PlayerColor.Black));
        for (int i = 0; i < ROWS; i++) {
            addNewPiece(new Pawn(Position.at(6, i), this, Piece.PlayerColor.Black));
        }

        updatePieces();
    }

    public ChessBoard() {
        this(false);
    }

    public void updatePieces(Piece.PlayerColor player, Piece excluded) {
        final List<Piece> finalPieceList = List.copyOf(pieceList);
        for (Piece p : finalPieceList) {
            if (p.getPlayerColor() == player && p != excluded) {
                p.update();
            }
        }
    }

    public void updatePieces() {
        if (whiteKing != null) {
            whiteKing.isInCheck();
        }
        if (blackKing != null) {
            blackKing.isInCheck();
        }

        final List<Piece> finalPieceList = List.copyOf(pieceList);
        for (Piece p : finalPieceList) {
            p.update();
        }

        if (whiteKing != null) {
            whiteKing.update();
        }
        if (blackKing != null) {
            blackKing.update();
        }
    }

    private void setPiecesTryToValidate(boolean active) {
        for (Piece p : pieceList) {
            p.setTryToValidate(active);
        }
    }

    public void addNewPiece(Piece piece) {
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

    public void capturePiece(Piece piece) {
        removePieceFromBoard(piece);
        pieceList.remove(piece);
    }

    public boolean movePiece(Piece piece, Position pos) {
        if (piece.isValidCapture(pos)) {
            Position start = piece.getPosition();
            pieceMap.put(piece.getPosition(), null);
            if (getPieceAt(pos) != null) {
                capturePiece(getPieceAt(pos));
            }
            piece.setPosition(pos);
            pieceMap.put(piece.getPosition(), piece);
            piece.onMove(start, pos);
            updatePieces();
            return true;
        }
        else if (piece.isValidMove(pos)) {
            Position start = piece.getPosition();
            pieceMap.put(piece.getPosition(), null);
            piece.setPosition(pos);
            pieceMap.put(piece.getPosition(), piece);
            piece.onMove(start, pos);
            updatePieces();
            return true;
        }
        return false;
    }

    public void removePieceFromBoard(Piece piece) {
        pieceMap.put(piece.getPosition(), null);
    }

    public void addPieceToBoard(Piece piece) {
        pieceMap.put(piece.getPosition(), piece);
    }

    public boolean validateMove(Piece piece, Position move) {
        validationBoard = copy();
        validationBoard.setPiecesTryToValidate(false);
        validationBoard.movePiece(validationBoard.getPieceAt(piece.getPosition()), move);
        if (validationBoard.getKing(piece.getPlayerColor()) != null) {
            return !validationBoard.getKing(piece.getPlayerColor()).isInCheck();
        }
        return true;
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

    public ChessBoard copy() {
        validationBoard = new ChessBoard(false);
        for (Piece p : pieceList) {
            validationBoard.addNewPiece(p.copy(validationBoard));
        }
        validationBoard.setPiecesTryToValidate(false);
        validationBoard.updatePieces();
        return validationBoard;
    }

    public boolean isValidPosition(Position position) {
        return pieceMap.containsKey(position);
    }

    public char[][] getBoardStateCharArray() {
        char[][] boardState = new char[8][8];
        for (int i = ROWS - 1; i >= 0; i--) {
            for (int j = 0; j < COLUMNS; j++) {
                Position pos = new Position(i,j);
                if (pieceMap.get(pos) != null)  {
                    boardState[i][j] = pieceMap.get(pos).getChar();
                }
                else {
                    boardState[i][j] = ' ';
                }
            }
        }
        return boardState;
    }

    public char[][] getPieceMovesCharArray(Piece piece) {
        char[][] boardState = new char[8][8];
        for (int i = ROWS - 1; i >= 0; i--) {
            for (int j = 0; j < COLUMNS; j++) {
                Position pos = new Position(i,j);
                if (piece.getCaptures().contains(pos)) {
                    boardState[i][j] = 'x';
                }
                else if (pieceMap.get(pos) != null)  {
                    boardState[i][j] = pieceMap.get(pos).getChar();
                }
                else {
                    if (piece.getMoves().contains(pos)) {
                        boardState[i][j] = 'o';
                    }
                    else {
                        boardState[i][j] = ' ';
                    }
                }
            }
        }
        return boardState;
    }

    public void printBoard() {
        char[][] boardState = getBoardStateCharArray();
        for (int i = boardState.length - 1; i >= 0; i--) {
            for (int j = 0; j < boardState[0].length; j++) {
                if (boardState[i][j] == ' ') {
                    System.out.print("-");
                }
                else {
                    System.out.print(boardState[i][j]);
                }

                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printPieceMoves(Piece piece) {
        char[][] boardState = getPieceMovesCharArray(piece);
        for (int i = boardState.length - 1; i >= 0; i--) {
            for (int j = 0; j < boardState[0].length; j++) {
                if (boardState[i][j] == ' ') {
                    System.out.print("-");
                }
                else {
                    System.out.print(boardState[i][j]);
                }

                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
