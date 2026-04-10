import java.util.Objects;

public class Position {
    public final int row;
    public final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Position getOffset(int rowOffset, int colOffset) {
        return new Position(row + rowOffset, col + colOffset);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position)) {
            return false;
        }
        Position othPos = (Position) obj;
        return this.row == othPos.row && this.col == othPos.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
