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

    public String getChessNotation() {
        String output;
        if (file == 0) {
            output = "a";
        }
        else if (file == 1) {
            output = "b";
        }
        else if (file == 2) {
            output = "c";
        }
        else if (file == 3) {
            output = "d";
        }
        else if (file == 4) {
            output = "e";
        }
        else if (file == 5) {
            output = "f";
        }
        else if (file == 6) {
            output = "g";
        }
        else if (file == 7) {
            output = "h";
        }
        else {
            output = "INVALID FILE";
        }
        output += rank + 1;
        return output;
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

    @Override
    public String toString() {
        return rank + ", " + file;
    }
}
