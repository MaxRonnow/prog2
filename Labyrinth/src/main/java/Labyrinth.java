import Tiling.SquareTile;
import Tiling.Tile;
import Tiling.TileType;

public class Labyrinth {
    private final Tile[][] tileMap;
    private final int rows;
    private final int cols;

    public Labyrinth(final int rows, final int cols, TileType tileType){
        this.rows = rows;
        this.cols = cols;
        this.tileMap = generateMap(tileType);
    }

    public Tile[][] getTileMap(){
        return this.tileMap;
    }

    public int getRows(){
        return this.rows;
    }

    public int getCols(){
        return this.cols;
    }

    private Tile[][] generateMap(TileType tileType) {
        Tile[][] tileMap = new Tile[this.rows][this.cols];
        for (int row=0; row<this.rows; row++){
            for (int col=0; col < this.cols; col++){
                Tile newTile = tileType.newTile(row, col);
                if (newTile.isValidCoordinate(rows, cols)){
                    tileMap[row][col] = newTile;
                }
            }
        }
        return tileMap;
    }

    private boolean isValidCoord(int row, int col){
        return 0 <= row && row < this.rows && 0 <= col && col < this.cols;
    }

    public Tile getTileAt(int row, int col){
        assert isValidCoord(row, col);
        return this.tileMap[row][col];
    }

    private Tile getNeighbor(Tile currTile, int directionIndex){
        assert directionIndex < currTile.getTileType().getNrEdges();
        int[] direction = currTile.getDirections()[directionIndex];
        int newRow = currTile.getRow() + direction[0];
        int newCol = currTile.getCol() + direction[1];

        if (isValidCoord(newRow, newCol)){
            return getTileAt(newRow, newCol);
        }

        return null;
    }

    public void removeWall(Tile currTile, int directionIndex){
        currTile.removeWall(directionIndex);
        // remove the opposite wall on the neighbor
        Tile neighbor = getNeighbor(currTile, directionIndex);
        // int edges = currTile.getTileType().getNrEdges();
        if (neighbor != null){
            neighbor.removeWall(currTile.getTileType().getOppositeEdge(directionIndex, currTile.getRow()));
        }
    }

    public void createMaze() {
        // TODO: Do the algorithm
        // https://en.wikipedia.org/wiki/Maze_generation_algorithm

    }
}
