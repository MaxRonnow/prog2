package Tiling;

import java.util.List;

public interface Tile {
    int getRow();
    int getCol();

    double getWidth();
    double getHeight();
    double getInRadius();

    double getPosX();
    double getPosY();

    List<Tile> getNeighbors();

    List<Boolean> getClockwiseWalls();  // starting from 12 o'clock

    void removeWall(int index);  // starting from 12 o'clock

    default int[][] getDirections() {
        return getTileType().getClockWiseDirections();
    }

    Double[][] getCorners();

    TileType getTileType();

    default boolean isValidCoordinate(int rows, int cols){
        return 0 <= this.getRow() && this.getRow() < rows
                && 0 <= this.getCol() && this.getCol() < cols;
    }
}
