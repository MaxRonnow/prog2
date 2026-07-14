
import Tiling.Tile;
import Tiling.TileType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Labyrinth {
    private final Tile[][] tileMap;
    private final int rows;
    private final int cols;
    private Tile startTile;
    private Tile endTile;

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
        // Hexagon used doubled coordinates, so we need double the cols
        int cols = tileType == TileType.HEXAGON ? this.cols * 2 : this.cols;
        Tile[][] tileMap = new Tile[this.rows][cols];

        for (int row=0; row<rows; row++){
            for (int col=0; col < cols; col++){
                Tile newTile = tileType.newTile(row, col);
                if (newTile.isValidCoordinate(rows, cols)){
                    tileMap[row][col] = newTile;
                }
            }
        }
        return tileMap;
    }

    private boolean isValidCoord(int row, int col, TileType tileType){
        int cols =  tileType == TileType.HEXAGON ? this.cols * 2 : this.cols;
        return 0 <= row && row < this.rows && 0 <= col && col < cols;
    }

    public Tile getTileAt(int row, int col, TileType tileType){
        assert isValidCoord(row, col, tileType);
        return this.tileMap[row][col];
    }

    public Tile getNeighbor(Tile currTile, int directionIndex){
        assert directionIndex < currTile.getTileType().getNrEdges();
        int[] direction = currTile.getDirections()[directionIndex];
        int newRow = currTile.getRow() + direction[0];
        int newCol = currTile.getCol() + direction[1];

        if (isValidCoord(newRow, newCol, currTile.getTileType())){
            return getTileAt(newRow, newCol, currTile.getTileType());
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
        // https://en.wikipedia.org/wiki/Maze_generation_algorithm
        // 1. Mark one cell as the start, set as current cell
        // 2. Set the current cell as visited
        // 3. While current cell has unvisited neighbors:  (else step 7)
        // 4. Choose one neighbor randomly
        // 5. Remove the wall
        // 6. New cell is current, go to step 2
        // 7. Set one cell as end, finished!
        // start tile is always (0, 0)
        Tile initTile = this.tileMap[0][0];
        this.startTile = initTile;
        this.startTile.removeWall(startTile.getTileType().getNrEdges() - 1);
        depthFirstWBacktracking(initTile);
        int cols = initTile.getTileType() == TileType.HEXAGON ? this.cols * 2 : this.cols;
        this.endTile = this.tileMap[this.rows - 1][cols - 1];
        // removes outside wall in the lower right corner of map, index 1 works
        this.endTile.removeWall(1);
    }

    public Tile getStartTile() {
        return this.startTile;
    }

    public Tile getEndTile() {
        return this.endTile;
    }

    private void depthFirstWBacktracking(Tile currentTile) {
        currentTile.setVisited(true);

        List<Integer> randomDirection = getRandomDirections(currentTile);

        for (int dir : randomDirection) {
            Tile neighbor = this.getNeighbor(currentTile, dir);
            if (neighbor != null && !neighbor.isVisited()){
                removeWall(currentTile, dir);
                depthFirstWBacktracking(neighbor);
            }
        }
    }

    private List<Integer> getRandomDirections(Tile tile) {
        int nr = tile.getTileType().getNrEdges();
        List<Integer> dirList = new ArrayList<>();
        for (int i = 0; i < nr; i++){
            dirList.add(i);
        }
        Collections.shuffle(dirList);
        return dirList;
    }

    private Tile getRandomTile(){
        List<Tile> tiles = new ArrayList<>(Arrays.stream(this.tileMap)  //'array' is two-dimensional
                .flatMap(Arrays::stream)
                .toList());
        Collections.shuffle(tiles);
        return tiles.getFirst();
    }

    public void removeRandomWalls(int nr){
        int removedWalls = 0;
        int randomDirection;
        while (removedWalls < nr){
            Tile randomTile = getRandomTile();
            if (randomTile != null){
                randomDirection = (int) Math.floor(Math.random() * randomTile.getTileType().getNrEdges());
                if (!isOutsideWall(randomTile, randomDirection) && randomTile.getClockwiseWalls()[randomDirection]){
                    removeWall(randomTile, randomDirection);
                    removedWalls++;
                }
            }
        }
    }

    private boolean isOutsideWall(Tile tile, int dir) {
        return tile.getTileType().isOutsideWall(tile.getRow(), tile.getCol(), dir);
    }

    public void setRandomStartEnd() {
        Tile randomStart = getRandomTile();
        this.startTile.addWall(startTile.getTileType().getNrEdges() - 1);
        while (randomStart == null){
            randomStart = getRandomTile();
        }
        this.startTile = randomStart;

        Tile randomEnd = getRandomTile();
        this.endTile.addWall(1);
        while (randomEnd == null){
            randomEnd = getRandomTile();
        }
        this.endTile = randomEnd;
    }
}
