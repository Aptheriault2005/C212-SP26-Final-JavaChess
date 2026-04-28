import javax.swing.*;
import java.awt.*;

public class PlayerGUI extends JPanel {

    private final Game game;
    private JLabel playerLabel;
    private JButton resignButton;
    private JButton offerDrawButton;

    public PlayerGUI(Game game) {
        this.game = game;
        createPlayerLabel(game);
        createResignButton(game);
        createOfferDrawButton(game);

        setLayout(new BorderLayout());

        setBackground(Color.DARK_GRAY);
        setSize(800, 100);

        add(playerLabel, BorderLayout.CENTER);
        add(resignButton, BorderLayout.EAST);
        add(offerDrawButton, BorderLayout.WEST);
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

    private void createResignButton(Game game) {
        resignButton = new JButton("Resign");
        resignButton.setBackground(Color.LIGHT_GRAY);
        resignButton.setFont(new Font(Font.SERIF, Font.BOLD, 30));
    }

    private void createOfferDrawButton(Game game) {
        offerDrawButton = new JButton("Offer Draw");
        offerDrawButton.setBackground(Color.LIGHT_GRAY);
        offerDrawButton.setFont(new Font(Font.SERIF, Font.BOLD, 30));
    }
}
