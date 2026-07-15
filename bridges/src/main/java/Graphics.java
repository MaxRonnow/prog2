import javax.swing.*;
import java.awt.*;

public class Graphics extends JFrame {

    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;

    IslandPanel islandPanel;

    public Graphics(Archipelago a) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bridges");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            islandPanel = new IslandPanel(a);
            frame.add(islandPanel);
            frame.setSize(WIDTH, HEIGHT);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        });
    }

    public void drawBridge() {
        SwingUtilities.invokeLater(() -> {
            if (islandPanel != null) {
                islandPanel.repaint();
            }
        });
    }

    static class IslandPanel extends JPanel {
        private final Archipelago archipelago;

        public IslandPanel(Archipelago archipelago) {
            this.archipelago = archipelago;
            setBackground(Color.BLACK);
        }

        @Override
        protected void paintComponent(java.awt.Graphics g0){
            super.paintComponent(g0);
            Graphics2D g = (Graphics2D) g0;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.WHITE);

            for (Island i: archipelago.getIslands()) {
                g.drawOval((int) i.x() - 2, (int) i.y() - 2, 4, 4);
            }

            for (Bridge b: new java.util.ArrayList<>(archipelago.getBridges())) {
                if (b == null) continue;
                int x1 = (int) b.getIsland1().x();
                int y1 = (int) b.getIsland1().y();
                int x2 = (int) b.getIsland2().x();
                int y2 = (int) b.getIsland2().y();

                g.drawLine(x1, y1, x2, y2);
            }

        }
    }
}
