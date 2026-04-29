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

    public void setFramePanel(JPanel panel) {
        container.removeAll();
        container.add(panel);
        setVisible(true);
    }

    public void createNewGame() {
        Game game = new Game(true);
        GameGUI gameGUI = game.getGameGUI();
        setFramePanel(gameGUI);
    }

    public void toMainMenu() {
        MainMenuGUI mainMenuGUI = new MainMenuGUI();
        setFramePanel(mainMenuGUI);
    }

    public void toGameOver(String message) {
        GameOverGUI gameOverGUI = new GameOverGUI(message);
        setFramePanel(gameOverGUI);
    }
}
