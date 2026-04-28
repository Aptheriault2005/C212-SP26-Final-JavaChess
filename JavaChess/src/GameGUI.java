import javax.swing.*;
import java.awt.*;

public class GameGUI {

    public GameGUI(Game game) {
        JFrame frame = new JFrame();
        ChessBoard cb = game.getChessBoard();
        JPanel guiPanel = new JPanel();
        guiPanel.setLayout(new BorderLayout());

        PlayerGUI playerGUI = new PlayerGUI(game);
        ChessBoardGUI chessBoardGUI = new ChessBoardGUI(cb);

        cb.movePiece(cb.getPieceAt(Position.at(1,4)), Position.at(3,4));

        chessBoardGUI.UpdateLayoutWithSelection(cb.getPieceAt(Position.at(6,4)));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,900);

        guiPanel.add(playerGUI, BorderLayout.NORTH);
        guiPanel.add(chessBoardGUI, BorderLayout.CENTER);
        frame.add(guiPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GameGUI(new Game());
    }
}
