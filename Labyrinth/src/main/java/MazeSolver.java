import Tiling.Tile;
import Tiling.TileType;

import java.util.ArrayList;
import java.util.List;

public class MazeSolver implements MazeSolverInterface {
    private int facingDirection;
    private Tile currentTile;
    private final Labyrinth labyrinth;
    private final List<Tile> path;

    public MazeSolver(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
        this.currentTile = labyrinth.getStartTile();
        this.path = new ArrayList<>();
        path.add(currentTile);
        this.facingDirection = currentTile.getTileType().getNrEdges() - 1;
    }

    @Override
    public int getFacingDirection() {
        return this.facingDirection;
    }

    @Override
    public TileType getTileType() {
        return this.currentTile.getTileType();
    }

    @Override
    public void turnAntiClockwise() {
        int nr = getTileType().getNrEdges();
        this.facingDirection = (this.facingDirection + nr - 1) % nr;
    }

    @Override
    public void turnAround() {
        this.facingDirection = getTileType().getOppositeEdge(facingDirection, this.currentTile.getRow());
    }

    @Override
    public void turnClockwise() {
        int nr = getTileType().getNrEdges();
        this.facingDirection = (this.facingDirection + 1) % nr;
    }

    @Override
    public boolean hasWallInFront() {
        return currentTile.getClockwiseWalls()[this.facingDirection];
    }

    @Override
    public void moveForward() {
        Tile newTile = labyrinth.getNeighbor(currentTile, facingDirection);
        if (newTile == null) {
            throw new NullPointerException("Tile in facing direction doesn't exist!");
        }
        currentTile = newTile;
        path.add(newTile);
    }

    @Override
    public Tile getCurrentTile() {
        return this.currentTile;
    }

    @Override
    public Tile getGoalTile() {
        return this.labyrinth.getEndTile();
    }

    @Override
    public List<Tile> getPath() {
        return this.path;
    }

    @Override
    public Labyrinth getLabyrinth() {
        return this.labyrinth;
    }

    @Override
    public void solve() {
        // 1. Rotate clockwise until there isn't a wall
        // 2. Move forward, add new current tile to path
        // 3. Turn around and rotate clockwise once
        // 4. Repeat from step 1 until we are at the end
        if (hasSolvedMaze()){
            IO.println("The maze has been solved!");
            return;
        }
        do {
            turnClockwise();
        } while (hasWallInFront());
        // no wall in front, we move forward
        moveForward();
        turnAround();
        // solve();
    }
}
