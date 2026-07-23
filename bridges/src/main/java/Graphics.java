/**
 * This class is responsible to draw the window displaying the islands and bridges,
 * as well as drawing the islands and bridges.
 *
 * DO NOT MODIFY THIS CLASS
 *
 * @author Max Rönnow
 */

import javax.swing.*;
import java.awt.*;

public class Graphics extends JFrame {

    /// Width and height of the window
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private static final float MULTIPLIER = 7f;

    IslandPanel islandPanel;

    public Graphics(Archipelago a, Traveler t) {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bridges");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            islandPanel = new IslandPanel(a, t);
            frame.add(islandPanel);
            frame.setSize(WIDTH, HEIGHT);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // Start a Swing Timer to poll the archipelago and repaint periodically.
            new javax.swing.Timer(20, e -> {
                if (islandPanel != null) {
                    islandPanel.repaint();
                }
            }).start();

        });

    }


    /**
     * Draws an island to the screen
     * @param g the graphics context
     * @param island the island to be drawn
     * @param color the color of the island to be drawn
     */
    public static void drawIsland(java.awt.Graphics2D g, Island island, Color color) {
        g.setColor(color);
        g.fillOval((int) ((island.getX() * MULTIPLIER) - 3), (int) ((island.getY() * MULTIPLIER) - 3), 6, 6);
        g.setColor(Color.WHITE);
    }


    /**
     * Draw logic
     */
    static class IslandPanel extends JPanel {
        private final Archipelago archipelago;

        public IslandPanel(Archipelago archipelago,  Traveler traveler) {
            this.archipelago = archipelago;
            setBackground(Color.BLACK);
        }

        /**
         * draws the islands and bridges, draws the islands and bridges that are part of the traveled path a different color.
         */
        @Override
        protected void paintComponent(java.awt.Graphics g0){
            super.paintComponent(g0);
            Graphics2D g = (Graphics2D) g0;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            for (Island i: archipelago.getIslands()) {
                if(!i.isPartOfPath()){
                    drawIsland(g, i, Color.WHITE);
                }
                else {
                    drawIsland(g, i, Color.RED);
                }
            }

            for (Bridge b: new java.util.ArrayList<>(archipelago.getBridges())) {
                if (b == null) continue;
                int x1 = (int) (b.getIsland1().getX() * MULTIPLIER);
                int y1 = (int) (b.getIsland1().getY() * MULTIPLIER);
                int x2 = (int) (b.getIsland2().getX() * MULTIPLIER);
                int y2 = (int) (b.getIsland2().getY() * MULTIPLIER);

                if (b.getIsland1().isPartOfPath() && b.getIsland2().isPartOfPath()) {
                    g.setColor(Color.GREEN);
                }
                g.drawLine(x1, y1, x2, y2);
                g.setColor(Color.WHITE);
            }
        }
    }
}
