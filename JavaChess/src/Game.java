import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private static final String GAMES_PLAYED_PATH = "C:/Users/apthe/Game Projects/C212-SP26-Final-JavaChess/JavaChess/src/GamesPlayed.txt";
    private static final String GAME_RECORD_PATH = "C:/Users/apthe/Game Projects/C212-SP26-Final-JavaChess/JavaChess/src/Game";

    private GameGUI gameGUI;
    private final ChessBoard cb;
    private int turn = 0;
    private int gameNumber;
    private String currentRecordPath;
    private final List<String> gameRecord;

    private int lastPieceCount;

    public Game(boolean hasGUI, boolean recordsToFile) {
        cb = new ChessBoard(true);
        gameRecord = new ArrayList<>();
        lastPieceCount = cb.getPieceList().size();
        if (recordsToFile) {
            readAndSetGameNumber();
            currentRecordPath = GAME_RECORD_PATH + gameNumber + ".txt";
            gameRecord.add("=== GAME " + gameNumber + " ===");
        }
        if (hasGUI) {
            gameGUI = new GameGUI(this);
        }
        else {
            gameGUI = null;
        }
    }

    public List<String> getGameRecord() {
        return gameRecord;
    }

    /**
     * Gets the current player
     * @return current player
     */
    public Piece.PlayerColor getCurrentPlayer() {
        if (turn % 2 == 0) {
            return Piece.PlayerColor.White;
        }
        else {
            return Piece.PlayerColor.Black;
        }
    }

    /**
     * Gets piece at given position if it belongs to the current player
     * @param pos given position
     * @return piece at given position if it belongs to the current player, otherwise null
     */
    public Piece selectValidPieceAt(Position pos) {
        Piece piece = cb.getPieceAt(pos);
        if (piece == null || piece.getPlayerColor() != getCurrentPlayer()) return null;
        return piece;
    }

    public ChessBoard getChessBoard() {
        return cb;
    }

    /**
     * Makes a move, updates GUI, and writes to file if checkmate
     * @param pieceToMove given piece
     * @param targetPosition given position
     * @return true if valid move, otherwise false
     */
    public boolean makeMove(Piece pieceToMove, Position targetPosition) {
        lastPieceCount = cb.getPieceList().size();
        Position startPos = pieceToMove.getPosition();
        if (pieceToMove != null && pieceToMove.getPlayerColor() == getCurrentPlayer() && cb.movePiece(pieceToMove, targetPosition)) {

            StringBuilder moveRecord = new StringBuilder();
            moveRecord.append(turn + 1);
            moveRecord.append(".");

            moveRecord.append(pieceToMove.getClass().toString().substring(5));
            moveRecord.append(" at ");
            moveRecord.append(startPos.getChessNotation());

            int currentPieceCount = cb.getPieceList().size();
            if (currentPieceCount < lastPieceCount) {
                moveRecord.append(" captures on ");
            }
            else {
                moveRecord.append(" moves to ");
            }

            moveRecord.append(targetPosition.getChessNotation());

            if (getCurrentPlayer() == Piece.PlayerColor.White) {
                if (cb.getKing(Piece.PlayerColor.Black).isInCheck()) {
                    if (cb.getKing(Piece.PlayerColor.Black).isCheckmated()) {
                        moveRecord.append(" resulting in checkmate");
                        if (FrameGUI.frameGUIInstance.isPresent()) {
                            FrameGUI.frameGUIInstance.get().toGameOver("White wins by checkmate");
                            gameRecord.add(moveRecord.toString());
                            gameRecord.add("White wins by checkmate");
                            writeGameRecord();
                        }
                    }
                    else {
                        moveRecord.append(" resulting in check");
                    }
                }
            }
            else {
                if (cb.getKing(Piece.PlayerColor.White).isInCheck()) {
                    if (cb.getKing(Piece.PlayerColor.White).isCheckmated()) {
                        moveRecord.append(" resulting in checkmate");
                        if (FrameGUI.frameGUIInstance.isPresent()) {
                            FrameGUI.frameGUIInstance.get().toGameOver("Black wins by checkmate");
                            gameRecord.add(moveRecord.toString());
                            gameRecord.add("Black wins by checkmate");
                            writeGameRecord();
                        }
                    }
                    else {
                        moveRecord.append(" resulting in check");
                    }
                }
            }
            gameRecord.add(moveRecord.toString());
            System.out.println(moveRecord);
            turn++;
            return true;
        }
        return false;
    }

    public GameGUI getGameGUI() {
        return gameGUI;
    }

    /**
     * Writes the game record to a file
     */
    public void writeGameRecord() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(currentRecordPath))) {
            for (String s : gameRecord) {
                bw.write(s + "\n");
            }
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Sets the current game number by reading the GamesPlayed.txt file, then increments the file value it by 1
     */
    private void readAndSetGameNumber() {
        List<String> lines = readFileLines(GAMES_PLAYED_PATH);
        gameNumber = Integer.parseInt(lines.getFirst());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(GAMES_PLAYED_PATH))) {
            bw.write("" + (gameNumber + 1));
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Reads all lines of a file
     * @param path given file path
     * @return List of Strings representing the read lines from the path
     */
    private static List<String> readFileLines(String path) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return lines;
    }
}
