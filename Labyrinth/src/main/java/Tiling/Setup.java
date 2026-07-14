package Tiling;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Setup {

    private static final int WIDTH = 1024;
    private static final int HEIGHT = 920;
    public static final int ROWS = 30;
    public static final int COLS = 40;
    public static final double IN_RADIUS = (double) min((WIDTH / (COLS + 1)) / 2, HEIGHT / (ROWS + 1));

}
