import Tiling.Setup;
import Tiling.Tile;
import Tiling.TileType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;

import static java.lang.Math.max;

public class Main extends JFrame implements KeyListener {

    // TODO:
    //  - auto-increment solve() (simulate an "e" press endlessly with 0.1s delay) + toggle on/off keybind
    //  - triangles?
    //  - draw darker color of solution path if overlap
    //  - save the mazes somehow (+ solution path)
    //  - tests for everything
    //  - IMPORTANT: what should students be doing?
    //      - Only the "solve()" method?
    //      - Maybe the drawing of tiles
    //  - Non-perfect mazes (remove more random walls)

    private LabyrinthPanel labPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Labyrinth lab = new Labyrinth(Setup.ROWS, Setup.COLS, TileType.HEXAGON);
            lab.createMaze();
            // lab.removeRandomWalls(max(Setup.ROWS, Setup.COLS) * 1);  // creates a non-perfect maze
            lab.setRandomStartEnd();

            MazeSolver solver = new MazeSolver(lab);
            // solver.solve();

            Main frame = new Main();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            LabyrinthPanel labPanel = new LabyrinthPanel(lab, solver);
            frame.labPanel = labPanel;
            frame.add(labPanel);
            frame.setSize(WIDTH, HEIGHT);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.addKeyListener(frame);
            frame.setFocusable(true);
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        if (key == 'e') {
            labPanel.nextStep();
            labPanel.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    static class LabyrinthPanel extends JPanel {
        private final Labyrinth labyrinth;
        private final MazeSolver solver;

        public LabyrinthPanel(Labyrinth labyrinth, MazeSolver solver) {
            this.labyrinth = labyrinth;
            this.solver = solver;
            setBackground(Color.BLACK);
        }

        public void nextStep(){
            solver.solve();
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

            // draw start end
            g.setColor(Color.GREEN);
            int x = (int) labyrinth.getStartTile().getPosX();
            int y = (int) labyrinth.getStartTile().getPosY();
            g.drawOval(x, y, 5, 5);

            g.setColor(Color.RED);
            x = (int) labyrinth.getEndTile().getPosX();
            y = (int) labyrinth.getEndTile().getPosY();
            g.drawOval(x, y, 5, 5);
        }
    }
}
