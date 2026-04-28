import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI implements ActionListener {

    private final Game game;
    private Piece selectedPiece = null;
    private final PlayerGUI playerGUI;
    private final ChessBoardGUI chessBoardGUI;

    public GameGUI(Game game) {
        this.game = game;
        JFrame frame = new JFrame();

        JPanel guiPanel = new JPanel();
        guiPanel.setLayout(new BorderLayout());
        playerGUI = new PlayerGUI(game);
        chessBoardGUI = new ChessBoardGUI(game, this);
        guiPanel.add(playerGUI, BorderLayout.NORTH);
        guiPanel.add(chessBoardGUI, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,900);
        frame.add(guiPanel);
        frame.setVisible(true);
    }

    public void UpdateGUI() {
        playerGUI.UpdateInfo();
        chessBoardGUI.UpdateLayout();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof BoardSquareGUI) {
            Position selectedPos = ((BoardSquareGUI) e.getSource()).getPosition();

            if (selectedPiece != null) {
                chessBoardGUI.UpdateLayoutWithSelection(selectedPiece);
                if (selectedPiece.getMoves().contains(selectedPos) ||
                        selectedPiece.getCaptures().contains(selectedPos)) {
                    game.makeMove(selectedPiece, selectedPos);
                    game.getGameGUI().UpdateGUI();
                    System.out.println(selectedPiece.getChar() + selectedPos.getChessNotation());
                    selectedPiece = null;
                }
                else {
                    selectedPiece = game.selectValidPieceAt(selectedPos);
                    if (selectedPiece != null) {
                        chessBoardGUI.UpdateLayoutWithSelection(selectedPiece);
                    }
                }
            }
            else {
                selectedPiece = game.selectValidPieceAt(selectedPos);
                if (selectedPiece != null) {
                    chessBoardGUI.UpdateLayoutWithSelection(selectedPiece);
                }
            }
        }
    }
}
