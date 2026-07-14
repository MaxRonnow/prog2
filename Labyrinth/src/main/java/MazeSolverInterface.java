import Tiling.Tile;
import Tiling.TileType;

import java.util.List;

public interface MazeSolverInterface {

    int getFacingDirection();
    TileType getTileType();
    void turnAntiClockwise();
    void turnAround();
    void turnClockwise();
    boolean hasWallInFront();
    void moveForward();
    Tile getCurrentTile();
    Tile getGoalTile();
    default boolean hasSolvedMaze(){
        return getCurrentTile().equals(getGoalTile());
    }
    List<Tile> getPath();
    Labyrinth getLabyrinth();
    void solve();
}
