import java.util.ArrayList;
import java.util.List;

public class Bridge {
    private final Island island1;
    private final Island island2;
    private final float length;

    public Bridge(Island island1, Island island2) {
        this.island1 = island1;
        this.island2 = island2;
        length = (float) Math.sqrt( Math.pow((island2.getX() - island1.getX()), 2) + Math.pow((island2.getY() - island1.getY()), 2) );
    }

    public List<Island> getIslands() {
        List<Island> islands = new ArrayList<>();
        islands.add(island1);
        islands.add(island2);
        return islands;
    }

    public float getLength() {
        return length;
    }

    public String toString() {
        return "Island1: (x: " + island1.getX() + " y: " + island1.getY() + ") Island2: (x: " + island2.getX() + " y: " + island2.getY() + ")";
    }


}
