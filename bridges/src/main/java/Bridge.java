/**
 * This class represents a bridge between two islands.
 * It also calculates the distance between the islands, which is the length of the bridge
 */
import java.util.Objects;

public class Bridge {
    private final Island island1;
    private final Island island2;
    private final float length;

    public Bridge(Island island1, Island island2) {
        this.island1 = island1;
        this.island2 = island2;
        length = (float) Math.sqrt( Math.pow((island2.getX() - island1.getX()), 2) + Math.pow((island2.getY() - island1.getY()), 2) );
    }

    public Island getIsland1() {
        return island1;
    }

    public Island getIsland2() {
        return island2;
    }

    public float getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Island1: (x: " + island1.getX() + " y: " + island1.getY() + ") Island2: (x: " + island2.getX() + " y: " + island2.getY() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bridge bridge)) return false;
        return (island1.equals(bridge.island1) && island2.equals(bridge.island2))
                || (island1.equals(bridge.island2) && island2.equals(bridge.island1));
    }

    @Override
    public int hashCode() {
        return island1.hashCode() + island2.hashCode();
    }

}
