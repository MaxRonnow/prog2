package Tiling;

public enum TileType {
    
    TRIANGLE,
    SQUARE,
    HEXAGON;

    public Tile newTile(int row, int col){
        switch (this) {
            case TRIANGLE:
                throw new NullPointerException();
            case SQUARE:
                return new SquareTile(row, col);
            case HEXAGON:
                return new HexTile(row, col);
            default:
                throw new NullPointerException();
        }
    }
    
    public int[][] getClockWiseDirections(){
        // row, col
        switch (this) {
            case TRIANGLE:
                // TODO: fix
                return new int[][]{
                        {1, 0},
                        {0, -1},
                        {-1, 0},
                };
            case SQUARE:
                return new int[][]{
                        {-1, 0},
                        {0, 1},
                        {1, 0},
                        {0, -1}
                };
            case HEXAGON:
                return new int[][]{
                        {-1, 1},
                        {0, 2},
                        {1, 1},
                        {1, -1},
                        {0, -2},
                        {-1, -1}
                };
            default:
                return new int[][]{};
        }
    }

    public String getName(){
        return this.name();
    }

    public int getNrEdges(){
        switch (this){
            case TRIANGLE:
                return 3;
            case SQUARE:
                return 4;
            case HEXAGON:
                return 6;
            default:
                return 0;
        }
    }

    public int getOppositeEdge(int directionIndex, int row) {
        switch (this) {
            case TRIANGLE:
                return (directionIndex + 1 + (row % 2)) % 3;
            case SQUARE:
                return (directionIndex + 2) % 4;
            case HEXAGON:
                return (directionIndex + 3) % 6;
            default:
                return 0;
        }
    }
}
