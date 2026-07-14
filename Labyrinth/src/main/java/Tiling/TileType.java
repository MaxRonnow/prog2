package Tiling;

import java.util.Set;

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

    public boolean isUpDir(int dir) {
        return switch (this) {
            case TRIANGLE ->
                // TODO: this
                    false;
            case SQUARE -> dir == 0;
            case HEXAGON -> dir == 0 || dir == 5;
        };
    }

    public boolean isDownDir(int dir) {
        return switch (this) {
            case TRIANGLE ->
                // TODO: this
                    false;
            case SQUARE -> dir == 2;
            case HEXAGON -> dir == 2 || dir == 3;
        };
    }

    public boolean isRightDir(int dir) {
        return switch (this) {
            case TRIANGLE ->
                // TODO: this
                    false;
            case SQUARE -> dir == 1;
            case HEXAGON -> dir == 0 || dir == 1 || dir == 2;
        };
    }

    public boolean isLeftDir(int dir) {
        return switch (this) {
            case TRIANGLE ->
                // TODO: this
                    false;
            case SQUARE -> dir == 3;
            case HEXAGON -> dir == 3 || dir == 4 || dir == 5;
        };
    }

    public boolean isOutsideWall(int row, int col, int dir) {
        return switch (this) {
            case SQUARE -> ((row == 0 && dir == 0) || (row == Setup.ROWS - 1 && dir == 2)
                    || (col == 0 && dir == 3) || (col == Setup.COLS - 1 && dir == 1));
            case HEXAGON -> {
                if (col == 0) {
                    if (row == 0){
                        // top left tile
                        yield !(dir == 1 || dir == 2);
                    }
                    yield isLeftDir(dir);
                } else if (col == 1) {
                    if  (row == Setup.ROWS - 1) {
                        // bottom left tile
                        yield !(dir == 1 || dir == 2);
                    }
                    yield dir == 4;
                } else if (col == Setup.COLS * 2 - 2) {
                    // even rows
                    if  (row == 0){
                        // top right tile
                        yield !(dir == 4 || dir == 3);
                    }
                    yield dir == 1;
                } else if (col == Setup.COLS * 2 - 1) {
                    if  (row == Setup.ROWS - 1) {
                        // bottom right tile
                        yield !(dir == 5 || dir == 4);
                    }
                    yield isRightDir(dir);
                }  else if (row == 0) {
                    yield isUpDir(dir);
                } else if (row == Setup.ROWS - 1) {
                    yield isDownDir(dir);
                }
                yield false;
            }
            case TRIANGLE ->
                // TODO: this
                    false;
        };
    }
}
