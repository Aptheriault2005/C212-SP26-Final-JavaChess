import javax.swing.*;
import java.awt.*;

public class ChessBoardGUI extends JPanel {

    private ChessBoard chessBoard;

    public ChessBoardGUI(ChessBoard cb) {
        chessBoard = cb;
        UpdateLayout();
    }

    public void UpdateLayout() {
        removeAll();
        char[][] boardState = chessBoard.getBoardStateCharArray();
        setLayout(new GridLayout(8,8));
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                add(new BoardSquareGUI(""+boardState[i][j]));
            }
        }
    }
}
