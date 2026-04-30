import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class FrameGUI extends JFrame {

    public static Optional<FrameGUI> frameGUIInstance = Optional.empty();
    private final Container container;

    public FrameGUI() {
        frameGUIInstance = Optional.of(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,900);
        container = getContentPane();

        MainMenuGUI mainMenuGUI = new MainMenuGUI();
        setFramePanel(mainMenuGUI);

        setVisible(true);
    }

    /**
     * Sets current displayed panel on this frame
     * @param panel given JPanel
     */
    public void setFramePanel(JPanel panel) {
        container.removeAll();
        container.add(panel);
        setVisible(true);
    }

    /**
     * Creates a new game and sets it to current panel
     */
    public void createNewGame() {
        Game game = new Game(true, true);
        GameGUI gameGUI = game.getGameGUI();
        setFramePanel(gameGUI);
    }

    /**
     * Creates main menu GUI and sets it to current panel
     */
    public void toMainMenu() {
        MainMenuGUI mainMenuGUI = new MainMenuGUI();
        setFramePanel(mainMenuGUI);
    }

    /**
     * Creates game over GUI and sets it to current panel, displays given message
     * @param message given message
     */
    public void toGameOver(String message) {
        GameOverGUI gameOverGUI = new GameOverGUI(message);
        setFramePanel(gameOverGUI);
    }
}
