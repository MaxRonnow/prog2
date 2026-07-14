package Tiling;

import static java.lang.Math.sqrt;

public class HexTile implements Tile {
    // TOP LEFT CORNER OF TILE (0, 0) IS POSITION (0, 0)

    // https://www.redblobgames.com/grids/hexagons/
    // Pointy top "odd-r" horizontal layout, with doubled coordinates (interlaced/double width)

    private final int row;
    private final int col;
    private final boolean[] walls;
    private boolean isVisited;

    public HexTile(int row, int col) {
        this.row = row;
        this.col = col;
        this.walls = new boolean[]{true, true, true, true, true, true};
        this.isVisited = false;
    }

    @Override
    public boolean isValidCoordinate(int rows, int cols){
        return rows > row && row >= 0 &&
                cols > col && col >= 0 &&
                (row + col) % 2 == 0;
    }

    @Override
    public int getRow() {
        return this.row;
    }

    @Override
    public int getCol() {
        return this.col;
    }

    @Override
    public double getWidth() {
        return sqrt(3) * getOuterRadius();
    }

    @Override
    public double getHeight() {
        return 2 * getOuterRadius();
    }

    @Override
    public double getInRadius() {
        return Setup.IN_RADIUS;
    }

    private static double getOuterRadius(){
        return 2 * Setup.IN_RADIUS / sqrt(3);
    }

    @Override
    public double getPosX() {
        if (this.row % 2 == 0){
            // even rows
            return ((col / 2.0) * getHorizontalSpacing()) + (getWidth() / 2) + getOffset();
        } else {
            // odd rows
            return (((col - 1) / 2.0) * getHorizontalSpacing()) + getWidth() + getOffset();
        }
    }

    private static double getHorizontalSpacing(){
        return 2 * Setup.IN_RADIUS;
    }

    private static double getVerticalSpacing(){
        return 1.5 * getOuterRadius();
    }

    @Override
    public double getPosY() {
        return (row * getVerticalSpacing()) + (getHeight() / 2) + getOffset();
    }

    @Override
    public boolean[] getClockwiseWalls() {
        return this.walls;
    }

    @Override
    public void removeWall(int index) {
        this.walls[index] = false;
    }

    @Override
    public double[][] getCorners() {
        double x =  getPosX();
        double y = getPosY();
        double[][] corners = new double[6][2];
        for (int i = 0; i<6; i++){
            corners[i] = pointyHexCorner(x, y, i);
        }
        return corners;
    }

    private static double[] pointyHexCorner(double x, double y, int i){
        double angleDeg = 60 * i - 90;
        double angleRad = Math.toRadians(angleDeg);
        return new double[]{
                x + getOuterRadius() * Math.cos(angleRad),
                y + getOuterRadius() * Math.sin(angleRad)
        };
    }

    @Override
    public TileType getTileType() {
        return TileType.HEXAGON;
    }

    @Override
    public boolean isVisited() {
        return this.isVisited;
    }

    @Override
    public void setVisited(boolean b) {
        this.isVisited = b;
    }

    @Override
    public void addWall(int i) {
        this.walls[i] = true;
    }

    @Override
    public String toString() {
        return this.getTileType().toString() + " at (" + this.getRow() + ", " + this.getCol() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HexTile hexTile = (HexTile) o;
        // TODO: do we skip checking if walls are equal?
        return this.getRow() == hexTile.getRow() && this.getCol() == hexTile.getCol();
    }
}
