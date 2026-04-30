import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverGUI extends JPanel implements ActionListener {

    private final JButton mainMenuButton;

    public GameOverGUI(String message) {
        JLabel titleLabel = new JLabel(message);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        titleLabel.setFont(new Font(Font.SERIF, Font.BOLD, 50));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        mainMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainMenuButton.setBackground(Color.LIGHT_GRAY);
        mainMenuButton.addActionListener(this);

        add(titleLabel);
        add(mainMenuButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(mainMenuButton)) {
            if (FrameGUI.frameGUIInstance.isEmpty()) return;
            FrameGUI.frameGUIInstance.get().toMainMenu();
        }
    }
}
