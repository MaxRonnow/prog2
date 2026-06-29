import javax.swing.*;
import java.awt.*;

public class Graphics extends JFrame {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;

    public Graphics(Archipelago a) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bridges");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            IslandPanel islandPanel = new IslandPanel(a);
            frame.add(islandPanel);
            frame.setSize(WIDTH, HEIGHT);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

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
                g.drawOval((int) i.getX() - 2, (int) i.getY() - 2, 4, 4);
            }

            for (Bridge b: archipelago.getBridges()) {
                int x1 = (int) b.getIslands().get(0).getX();
                int y1 = (int) b.getIslands().get(0).getY();
                int x2 = (int) b.getIslands().get(1).getX();
                int y2 = (int) b.getIslands().get(1).getY();

                g.drawLine(x1, y1, x2, y2);
            }

        }
    }
}
