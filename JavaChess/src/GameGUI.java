import javax.swing.*;
import java.awt.*;

public class GameGUI {

    public GameGUI() {
        JFrame frame = new JFrame();
        ChessBoard cb = new ChessBoard(true);
        ChessBoardGUI panel = new ChessBoardGUI(cb);

        cb.movePiece(cb.getPieceAt(Position.at(1,0)), Position.at(3,0));

        panel.UpdateLayout();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GameGUI();
    }
}
