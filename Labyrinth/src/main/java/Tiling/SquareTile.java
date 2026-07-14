package Tiling;

public class SquareTile implements Tile {

    // TOP LEFT CORNER OF TILE (0, 0) IS POSITION (0, 0)

    private final int row;
    private final int col;
    private final boolean[] walls;
    private boolean isVisited;

    public SquareTile(final int row, final int col){
        this.row = row;
        this.col = col;
        this.walls = new boolean[]{true, true, true, true};
        this.isVisited = false;
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
        return 2 * Setup.IN_RADIUS;
    }

    @Override
    public double getHeight() {
        return 2 * Setup.IN_RADIUS;
    }

    @Override
    public double getInRadius() {
        return Setup.IN_RADIUS;
    }

    @Override
    public double getPosX() {
        return this.col * this.getWidth() + getWidth() / 2 + getOffset();
    }

    @Override
    public double getPosY() {
        return this.row * this.getHeight() + getHeight() / 2 + getOffset();
    }

    @Override
    public boolean[] getClockwiseWalls() {
        return this.walls;
    }

    @Override
    public void removeWall(int index) {
        // TODO: assert index exists
        this.walls[index] = false;
    }

    @Override
    public double[][] getCorners() {
        double halfWidth = this.getWidth() / 2;
        double halfHeight = this.getHeight() / 2;
        double topLeftX = this.getPosX() - halfWidth;
        double topLeftY = this.getPosY() - halfHeight;
        return new double[][]{
                {topLeftX, topLeftY},
                {topLeftX + this.getWidth(), topLeftY},
                {topLeftX + this.getWidth(), topLeftY + this.getHeight()},
                {topLeftX, topLeftY + this.getHeight()}
        };
    }

    @Override
    public TileType getTileType() {
        return TileType.SQUARE;
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
    public String toString() {
        return this.getTileType().toString() + " at (" + this.getRow() + ", " + this.getCol() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SquareTile squareTile = (SquareTile) o;
        // TODO: do we skip checking if walls are equal?
        return this.getRow() == squareTile.getRow() && this.getCol() == squareTile.getCol();
    }
}
