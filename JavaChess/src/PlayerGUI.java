import javax.swing.*;
import java.awt.*;

public class PlayerGUI extends JPanel {

    private final Game game;
    private final GameGUI gameGUI;
    private JLabel playerLabel;
    private JButton resignButton;
    private JButton drawButton;

    public PlayerGUI(Game game, GameGUI gameGUI) {
        this.game = game;
        this.gameGUI = gameGUI;
        createPlayerLabel(game);
        createResignButton();
        createDrawButton();

        setLayout(new BorderLayout());

        setBackground(Color.DARK_GRAY);
        setSize(800, 100);

        add(playerLabel, BorderLayout.CENTER);
        add(resignButton, BorderLayout.EAST);
        add(drawButton, BorderLayout.WEST);
    }

    public JButton getResignButton() {
        return resignButton;
    }

    public JButton getDrawButton() {
        return drawButton;
    }

    public void UpdateInfo() {
        playerLabel.setText(game.getCurrentPlayer().toString() + "'s move");
        playerLabel.setForeground(game.getCurrentPlayer() == Piece.PlayerColor.White ? Color.WHITE : Color.BLACK);
    }

    private void createPlayerLabel(Game game) {
        playerLabel = new JLabel(game.getCurrentPlayer().toString() + "'s move");
        playerLabel.setFont(new Font(Font.SERIF, Font.BOLD, 50));
        playerLabel.setForeground(game.getCurrentPlayer() == Piece.PlayerColor.White ? Color.WHITE : Color.BLACK);
        playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void createResignButton() {
        resignButton = new JButton("Resign");
        resignButton.setBackground(Color.LIGHT_GRAY);
        resignButton.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        resignButton.addActionListener(gameGUI);
    }

    private void createDrawButton() {
        drawButton = new JButton("Declare Draw");
        drawButton.setBackground(Color.LIGHT_GRAY);
        drawButton.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        drawButton.addActionListener(gameGUI);
    }
}
