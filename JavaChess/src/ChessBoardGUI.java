import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardGUI extends JPanel implements ActionListener {

    private Game game;
    private Map<Position, BoardSquareGUI> boardSquares;

    public ChessBoardGUI(Game game) {
        this.game = game;
        boardSquares = new HashMap<>();
        setSize(800, 800);
        char[][] boardState = game.getChessBoard().getBoardStateCharArray();
        setLayout(new GridLayout(8,8));
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                BoardSquareGUI boardSquare = new BoardSquareGUI(""+boardState[i][j], this, Position.at(i,j));
                add(boardSquare);
                boardSquares.put(Position.at(i,j), boardSquare);
            }
        }
    }

    public void UpdateLayoutWithSelection(Piece selection) {
        char[][] boardState = game.getChessBoard().getPieceMovesCharArray(selection);
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                BoardSquareGUI boardSquare = boardSquares.get(Position.at(i,j));
                boardSquare.Update(""+boardState[i][j]);
            }
        }
    }

    public void UpdateLayout() {
        char[][] boardState = game.getChessBoard().getBoardStateCharArray();
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                BoardSquareGUI boardSquare = boardSquares.get(Position.at(i,j));
                boardSquare.Update(""+boardState[i][j]);
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof BoardSquareGUI) {
            Position selectionPos = ((BoardSquareGUI) e.getSource()).getPosition();
            if (game.getChessBoard().getPieceAt(selectionPos) != null) {
                Piece selectedPiece = game.getChessBoard().getPieceAt(selectionPos);
                UpdateLayoutWithSelection(selectedPiece);
            }

            System.out.println(selectionPos.getChessNotation());
        }
    }
}
