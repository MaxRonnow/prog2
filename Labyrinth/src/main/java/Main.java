import Tiling.Setup;
import Tiling.Tile;
import Tiling.TileType;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Labyrinth lab = new Labyrinth(Setup.ROWS, Setup.COLS, TileType.HEXAGON);
            lab.createMaze();
            MazeSolver solver = new MazeSolver(lab);
            solver.solve();

            JFrame frame = new JFrame("Labyrinth");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            LabyrinthPanel labPanel = new LabyrinthPanel(lab, solver);
            frame.add(labPanel);
            frame.setSize(WIDTH, HEIGHT);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    static class LabyrinthPanel extends JPanel {
        private final Labyrinth labyrinth;
        private final MazeSolver solver;

        public LabyrinthPanel(Labyrinth labyrinth, MazeSolver solver) {
            this.labyrinth = labyrinth;
            this.solver = solver;
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
                    if (t != null){
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
            // draw solver path
            for (int i=0; i<solver.getPath().size() - 1; i++){
                Tile startTile =  solver.getPath().get(i);
                Tile endTile =  solver.getPath().get(i + 1);
                double startX = startTile.getPosX();
                double startY = startTile.getPosY();
                double endX = endTile.getPosX();
                double endY = endTile.getPosY();
                double thickness = 4;
                g.setColor(Color.YELLOW);
                g.setStroke(new BasicStroke((float) thickness));
                g.drawLine((int)startX, (int)startY, (int)endX, (int)endY);
            }
        }
    }
}
