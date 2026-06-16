package Tiling;

import java.util.List;

public class SquareTile implements Tile {

    // TOP LEFT CORNER OF TILE (0, 0) IS POSITION (0, 0)

    private final int row;
    private final int col;
    public static final double IN_RADIUS = 20;
    private boolean[] walls;

    public SquareTile(final int row, final int col){
        this.row = row;
        this.col = col;
        this.walls = new boolean[]{true, true, true, true};
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
        return 2 * IN_RADIUS;
    }

    @Override
    public double getHeight() {
        return 2 * IN_RADIUS;
    }

    @Override
    public double getInRadius() {
        return IN_RADIUS;
    }

    @Override
    public double getPosX() {
        return this.col * this.getWidth();
    }

    @Override
    public double getPosY() {
        return this.row * this.getHeight();
    }

    @Override
    public List<Tile> getNeighbors() {
        return List.of();
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
        double topLeftX = this.getPosX();
        double topLeftY = this.getPosY();
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
}
