/**
A class representing an island.
Simply contains the x and y coordinates for the island and a boolean for visited status
 */
import java.util.Objects;

public class Island {

    float x;
    float y;
    /// true if island is part of the traversed path, false otherwise
    boolean partOfPath;

    public Island(float x, float y, boolean visited) {
        this.x = x;
        this.y = y;
        this.partOfPath = visited;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isPartOfPath() {
        return partOfPath;
    }

    public void setPartOfPath(boolean partOfPath) {
        this.partOfPath = partOfPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Island island)) return false;
        return Float.compare(island.x, x) == 0 && Float.compare(island.y, y) == 0 && partOfPath == island.partOfPath;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
