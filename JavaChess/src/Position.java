import java.util.HashSet;
import java.util.Objects;

public class Position {
    private final int rank;
    private final int file;

    public Position(int rank, int file) {
        this.rank = rank;
        this.file = file;
    }

    public static Position at(int rank, int file) {
        return new Position(rank, file);
    }

    public Position getAdjacent(int rankOffset, int fileOffset) {
        return new Position(rank + rankOffset, file + fileOffset);
    }

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position)) {
            return false;
        }
        Position othPos = (Position) obj;
        return this.rank == othPos.rank && this.file == othPos.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
