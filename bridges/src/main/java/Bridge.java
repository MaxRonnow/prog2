public class Bridge {
    private final Island island1;
    private final Island island2;
    private final float length;

    public Bridge(Island island1, Island island2) {
        this.island1 = island1;
        this.island2 = island2;
        length = (float) Math.sqrt( Math.pow((island2.x() - island1.x()), 2) + Math.pow((island2.y() - island1.y()), 2) );
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

    public String toString() {
        return "Island1: (x: " + island1.x() + " y: " + island1.y() + ") Island2: (x: " + island2.x() + " y: " + island2.y() + ")";
    }


}
