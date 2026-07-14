package Tiling;

import java.awt.*;

import static java.lang.Math.min;

public class Setup {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private static final int WIDTH = screenSize.width;
    private static final int HEIGHT = screenSize.height - 10;  // some pixels used by window bar

    public static final int ROWS = 10;
    public static final int COLS = 20;

    public static final double IN_RADIUS = min((WIDTH / (COLS + 1)), HEIGHT / (ROWS + 1)) / 2.0;
}
