import javax.swing.*;
import java.awt.*;

public class BoardSquareGUI extends JButton {

    private String text;
    private ChessBoardGUI chessBoardGUI;
    private Position position;

    public BoardSquareGUI(String text, ChessBoardGUI chessBoardGUI, Position position) {
        super(text);
        this.text = text;
        this.chessBoardGUI = chessBoardGUI;
        this.position = position;
        addActionListener(chessBoardGUI);
        setFont(new Font(Font.MONOSPACED, Font.BOLD, 48));
        setBackground(checkerboardColor(position.getRank(), position.getFile()));
    }

    public void Update(String state) {
        this.text = state;
        setText(text);
    }

    public Position getPosition() {
        return position;
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
