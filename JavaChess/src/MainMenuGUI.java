import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuGUI extends JPanel implements ActionListener {

    private final JButton newGameButton;

    public MainMenuGUI() {
        JLabel titleLabel = new JLabel("JAVA CHESS");
        titleLabel.setFont(new Font(Font.SERIF, Font.BOLD, 50));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        newGameButton.setBackground(Color.LIGHT_GRAY);
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameButton.addActionListener(this);

        add(titleLabel);
        add(newGameButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(newGameButton)) {
            if (FrameGUI.frameGUIInstance.isEmpty()) return;
            FrameGUI.frameGUIInstance.get().createNewGame();
        }
    }
}
