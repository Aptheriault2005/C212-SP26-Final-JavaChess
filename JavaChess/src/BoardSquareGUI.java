import javax.swing.*;
import java.awt.*;

public class BoardSquareGUI extends JButton {

    private final ChessBoard cb;
    private String text;
    private final Position position;

    public BoardSquareGUI(String text, ChessBoard cb, GameGUI gameGUI, Position position) {
        super(text);
        this.cb = cb;
        this.text = text;
        this.position = position;
        addActionListener(gameGUI);
        setFont(new Font(Font.MONOSPACED, Font.BOLD, 48));
        setBackground(checkerboardColor(position.getRank(), position.getFile()));
    }

    public void Update(String state) {
        this.text = state;
        setPlayerColor();
        setText(text);
    }

    public Position getPosition() {
        return position;
    }

    private void setPlayerColor() {
        Piece piece = cb.getPieceAt(position);
        if (piece != null) {
            if (piece.getPlayerColor() == Piece.PlayerColor.White) {
                setForeground(Color.WHITE);
            }
            else {
                setForeground(Color.BLACK);
            }
        }
        else {
            setForeground(Color.RED);
        }
    }

    private Color checkerboardColor(int row, int col) {
        if (row % 2 == 0) {
            if (col % 2 == 0) {
                return Color.DARK_GRAY;
            }
            else {
                return Color.LIGHT_GRAY;
            }
        }
        else {
            if (col % 2 == 0) {
                return Color.LIGHT_GRAY;
            }
            else {
                return Color.DARK_GRAY;
            }
        }
    }
}
