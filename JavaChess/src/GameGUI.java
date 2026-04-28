import javax.swing.*;
import java.awt.*;

public class GameGUI {

    public GameGUI(Game game) {
        JFrame frame = new JFrame();

        JPanel guiPanel = new JPanel();
        guiPanel.setLayout(new BorderLayout());
        PlayerGUI playerGUI = new PlayerGUI(game);
        ChessBoardGUI chessBoardGUI = new ChessBoardGUI(game);
        guiPanel.add(playerGUI, BorderLayout.NORTH);
        guiPanel.add(chessBoardGUI, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,900);
        frame.add(guiPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GameGUI(new Game());
    }
}
