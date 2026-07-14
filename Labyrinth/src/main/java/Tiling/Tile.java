package Tiling;

import java.util.ArrayList;
import java.util.List;

public interface Tile {
    int getRow();
    int getCol();

    double getWidth();
    double getHeight();
    double getInRadius();

    double getPosX();
    double getPosY();

    default List<Tile> getNeighbors(Tile[][] allTiles, int rows, int cols){
        int[][] dirs = this.getTileType().getClockWiseDirections();
        List<Tile> neighbors = new ArrayList<>();
        for (int[] dir : dirs){
            int newRow = this.getRow() + dir[0];
            int newCol = this.getCol() + dir[1];
            if (0 <= newRow && newRow < rows && 0 <= newCol && newCol < cols){
                Tile neighborTile = allTiles[newRow][newCol];
                if (neighborTile != null && neighborTile.isValidCoordinate(rows, cols)){
                    neighbors.add(neighborTile);
                }
            }
        }
        return neighbors;
    }

    boolean[] getClockwiseWalls();  // starting from 12 o'clock

    void removeWall(int index);  // starting from 12 o'clock

    default int[][] getDirections() {
        return getTileType().getClockWiseDirections();
    }

    double[][] getCorners();

    TileType getTileType();

    default boolean isValidCoordinate(int rows, int cols){
        return 0 <= this.getRow() && this.getRow() < rows
                && 0 <= this.getCol() && this.getCol() < cols;
    }

    boolean isVisited();
    void setVisited(boolean b);

    default double getOffset(){ return 10.0; }
}
