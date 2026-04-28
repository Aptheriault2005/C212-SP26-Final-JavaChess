import javax.swing.*;
import java.awt.*;

public class BoardSquareGUI extends JButton {

    public BoardSquareGUI(String text, Color color) {
        super(text);
        setFont(new Font(Font.MONOSPACED, Font.BOLD, 48));
        setBackground(color);
    }
}
