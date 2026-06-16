import Tiling.Tile;
import Tiling.TileType;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int ROWS = 19;
    private static final int COLS = 19;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Labyrinth lab = new Labyrinth(ROWS, COLS, TileType.SQUARE);
            // lab.removeWall(lab.getTileAt(0, 0), 1);
            // lab.removeWall(lab.getTileAt(0, 1), 2);
            lab.createMaze();

            JFrame frame = new JFrame("Labyrinth");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            LabyrinthPanel labPanel = new LabyrinthPanel(lab);
            frame.add(labPanel);
            frame.setSize(WIDTH, HEIGHT);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    static class LabyrinthPanel extends JPanel {
        private final Labyrinth labyrinth;

        public LabyrinthPanel(Labyrinth labyrinth){
            this.labyrinth = labyrinth;
            setBackground(Color.BLACK);
        }

        @Override
        protected void paintComponent(Graphics g0){
            super.paintComponent(g0);
            Graphics2D g = (Graphics2D) g0;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // draw tiles
            for (Tile[] tileRow : labyrinth.getTileMap()){
                for (Tile t : tileRow){
                    double[][] corners = t.getCorners();
                    int nrC = t.getTileType().getNrEdges();
                    for (int i=0; i<nrC; i++){
                        double[] currCorner = corners[i];
                        double[] nextCorner = corners[(i+1) % nrC];
                        // TODO: assert proper index
                        boolean isWall = t.getClockwiseWalls()[i];
                        if (isWall){
                            g.setColor(Color.WHITE);
                            g.drawLine((int)currCorner[0], (int)currCorner[1], (int)nextCorner[0], (int)nextCorner[1]);
                        }
                    }
                }
            }
        }
    }
}
