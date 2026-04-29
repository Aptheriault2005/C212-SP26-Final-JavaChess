import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI extends JPanel implements ActionListener {

    private final Game game;
    private Piece selectedPiece = null;
    private final PlayerGUI playerGUI;
    private final ChessBoardGUI chessBoardGUI;

    public GameGUI(Game game) {
        this.game = game;

        setLayout(new BorderLayout());
        playerGUI = new PlayerGUI(game, this);
        chessBoardGUI = new ChessBoardGUI(game, this);
        add(playerGUI, BorderLayout.NORTH);
        add(chessBoardGUI, BorderLayout.CENTER);
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
        else if (e.getSource().equals(playerGUI.getResignButton())) {
            if (FrameGUI.frameGUIInstance.isEmpty()) return;
            if (game.getCurrentPlayer() == Piece.PlayerColor.White) {
                FrameGUI.frameGUIInstance.get().toGameOver("Black wins by resignation");
            }
            else {
                FrameGUI.frameGUIInstance.get().toGameOver("White wins by resignation");
            }
        }
        else if (e.getSource().equals(playerGUI.getDrawButton())) {
            if (FrameGUI.frameGUIInstance.isEmpty()) return;
            FrameGUI.frameGUIInstance.get().toGameOver("Draw declared");
        }
    }
}
