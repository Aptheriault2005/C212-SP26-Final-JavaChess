import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public class Position {
    private final int rank;
    private final int file;
    private final static Map<Integer, String> CHESS_NOTATION_FILE_MAP = Map.of(
            0, "a",
            1, "b",
            2, "c",
            3, "d",
            4, "e",
            5, "f",
            6, "g",
            7, "h"
    );

    public Position(int rank, int file) {
        this.rank = rank;
        this.file = file;
    }

    /**
     * Creates a Position at a given rank and file
     * @param rank given rank
     * @param file given file
     * @return
     */
    public static Position at(int rank, int file) {
        return new Position(rank, file);
    }

    /**
     * Gets an adjacent Position to this
     * @param rankOffset rank offset value
     * @param fileOffset file offset value
     * @return adjacent Position to this based on offset
     */
    public Position getAdjacent(int rankOffset, int fileOffset) {
        return new Position(rank + rankOffset, file + fileOffset);
    }

    /**
     * Gets the chess notation of this
     * @return chess notation of this
     */
    public String getChessNotation() {
        return CHESS_NOTATION_FILE_MAP.get(file) + (rank + 1);
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
