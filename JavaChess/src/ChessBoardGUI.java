import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardGUI extends JPanel {

    private final Game game;
    private final Map<Position, BoardSquareGUI> boardSquares;

    public ChessBoardGUI(Game game, GameGUI gameGUI) {
        this.game = game;
        boardSquares = new HashMap<>();
        setSize(800, 800);
        char[][] boardState = game.getChessBoard().getBoardStateCharArray();
        setLayout(new GridLayout(8,8));
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                BoardSquareGUI boardSquare = new BoardSquareGUI(""+boardState[i][j], game.getChessBoard(), gameGUI, Position.at(i,j));
                boardSquare.Update(""+boardState[i][j]);
                add(boardSquare);
                boardSquares.put(Position.at(i,j), boardSquare);
            }
        }
    }

    /**
     * Updates board square GUI with given piece selection
     * @param selection given piece
     */
    public void UpdateLayoutWithSelection(Piece selection) {
        char[][] boardState = game.getChessBoard().getPieceMovesCharArray(selection);
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                BoardSquareGUI boardSquare = boardSquares.get(Position.at(i,j));
                boardSquare.Update(""+boardState[i][j]);
            }
        }
    }

    /**
     * Updates board square GUI
     */
    public void UpdateLayout() {
        char[][] boardState = game.getChessBoard().getBoardStateCharArray();
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                BoardSquareGUI boardSquare = boardSquares.get(Position.at(i,j));
                boardSquare.Update(""+boardState[i][j]);
            }
        }
    }
}
