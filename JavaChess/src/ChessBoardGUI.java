import javax.swing.*;
import java.awt.*;

public class ChessBoardGUI extends JPanel {

    private ChessBoard chessBoard;

    public ChessBoardGUI(ChessBoard cb) {
        chessBoard = cb;
        setSize(800, 800);
        UpdateLayout();
    }

    public void UpdateLayoutWithSelection(Piece selection) {
        removeAll();
        char[][] boardState = chessBoard.getPieceMovesCharArray(selection);
        setLayout(new GridLayout(8,8));
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                add(new BoardSquareGUI(""+boardState[i][j], checkerboardColor(i,j)));
            }
        }
    }

    public void UpdateLayout() {
        removeAll();
        char[][] boardState = chessBoard.getBoardStateCharArray();
        setLayout(new GridLayout(8,8));
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                add(new BoardSquareGUI(""+boardState[i][j], checkerboardColor(i,j)));
            }
        }
    }

    private Color checkerboardColor(int row, int col) {
        if (row % 2 == 0) {
            if (col % 2 == 0) {
                return Color.GRAY;
            }
            else {
                return Color.WHITE;
            }
        }
        else {
            if (col % 2 == 0) {
                return Color.WHITE;
            }
            else {
                return Color.GRAY;
            }
        }
    }
}
