package com.JavaGame;

import com.JavaGame.Planets.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame implements KeyListener {

    private static final double SCALE = 1e6;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private static final int DELAY = 10;  // milliseconds between frames
    
    private SimulationPanel simulationPanel;

    static class SimulationPanel extends JPanel {
        private final java.util.List<CelestialObject> bodies;
        private final CelestialObject centerBody; // optional center (e.g., Earth)
        private double metersPerPixel = 1e9; // tune to zoom
        private int SECS_PER_FRAME = 3600;  // seconds of simulation per frame,
        // upper recommended limit 3600 (1h per frame), otherwise orbits become wonky
        private final int trailLength = 400; // keep last 100 positions
        private final java.util.Map<CelestialObject, java.util.ArrayDeque<Vector>> trails = new java.util.LinkedHashMap<>();

        public SimulationPanel(java.util.List<CelestialObject> bodies, CelestialObject centerBody){
            this.bodies = bodies;
            this.centerBody = centerBody;
            setBackground(Color.BLACK);
            // initialize trails with current positions
            for (CelestialObject b : bodies){
                java.util.ArrayDeque<Vector> dq = new java.util.ArrayDeque<>(trailLength);
                dq.add(b.getPosition());
                trails.put(b, dq);
            }
        }

        private int wx(double worldX){
            double cx = getWidth()/2.0;
            double centerWorldX = centerBody.getPosition().getX();
            centerWorldX = 0;
            // tmp override, center is always 0
            return (int)Math.round(cx + (worldX - centerWorldX)/metersPerPixel);
        }
        private int wy(double worldY){
            double cy = getHeight()/2.0;
            double centerWorldY = centerBody.getPosition().getY();
            centerWorldY = 0;
            // tmp override, center is always 0
            return (int)Math.round(cy - (worldY - centerWorldY)/metersPerPixel); // invert Y
        }

        public void updateZoom(boolean in){
            if (in) {
                metersPerPixel *= 0.8;
            } else {
                metersPerPixel /= 0.8;
            }
        }

        public void updateSimSpeed(double mult){
            SECS_PER_FRAME = (int) (SECS_PER_FRAME * mult);
        }
        
        public double getMetersPerPixel() {
            return metersPerPixel;
        }
        
        public void setMetersPerPixel(double value) {
            metersPerPixel = value;
        }

        // call this once per frame after updating physics to capture the current position
        public void pushTrailPositions(){
            for (CelestialObject b : bodies){
                java.util.ArrayDeque<Vector> dq = trails.get(b);
                if (dq == null) continue;
                dq.addLast(b.getPosition());
                while (dq.size() > trailLength) dq.removeFirst();
            }
        }

        @Override
        protected void paintComponent(Graphics g0){
            super.paintComponent(g0);
            Graphics2D g = (Graphics2D)g0;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // draw trails (oldest -> newest)
            for (CelestialObject b : bodies){
                java.util.ArrayDeque<Vector> dq = trails.get(b);
                if (dq == null || dq.isEmpty()) continue;
                int size = dq.size();
                int idx = 0;
                Color baseColor = getPlanetColor(b.getName());
                for (Vector pos : dq){
                    int x = wx(pos.getX());
                    int y = wy(pos.getY());
                    float alpha = (float)(idx + 1) / (float)size; // 0..1 (older points are more transparent)
                    int a = Math.max(10, Math.round(alpha * 255));
                    Color c = new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), a);
                    g.setColor(c);
                    g.fillRect(x, y, 1, 1);
                    idx++;
                }
            }

            // draw bodies on top
            for (CelestialObject b : bodies){
                double rx = b.getRadius(); // meters
                int rpx = Math.max(4, (int)Math.round(rx / metersPerPixel));
                int x = wx(b.getPosition().getX());
                int y = wy(b.getPosition().getY());
                // color by name
                g.setColor(getPlanetColor(b.getName()));

                g.fillOval(x - rpx, y - rpx, rpx*2, rpx*2);
            }
        }

        public int getSECS_PER_FRAME() {
            return SECS_PER_FRAME;
        }

        public void setSECS_PER_FRAME(int SECS_PER_FRAME) {
            this.SECS_PER_FRAME = SECS_PER_FRAME;
        }
    }

    public static Color getPlanetColor(String name){
        return switch (name) {
            case "Earth" -> (Color.BLUE);
            case "Moon", "Mercury" -> (Color.LIGHT_GRAY);
            case "Venus" -> (Color.YELLOW);
            case "Mars" -> (Color.RED);
            case null, default -> (Color.ORANGE);
        };
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e){
        char key = e.getKeyChar();
        if (key == 'z' || key == 'Z'){
            simulationPanel.updateZoom(true);
        } else if (key == 'x' || key == 'X') {
            simulationPanel.updateZoom(false);
        } else if (key == 'v' || key == 'V'){
            simulationPanel.updateSimSpeed(0.5);
        } else if (key == 'b' || key == 'B'){
            simulationPanel.updateSimSpeed(2);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    static void main() {
        SwingUtilities.invokeLater(() -> {
            List<CelestialObject> bodies = CelestialSystems.TRIPLE_SUN_SYSTEM();

            Main frame = new Main();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            SimulationPanel panel = new SimulationPanel(bodies, bodies.getFirst());
            frame.simulationPanel = panel;

            frame.add(panel);
            frame.setSize(WIDTH, HEIGHT);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.addKeyListener(frame);
            frame.setFocusable(true);

            System.out.println("Simulation speed: " + ((1000 / DELAY) * panel.getSECS_PER_FRAME()) / 3600 + " hours simulated per second");

            new Timer(DELAY, e -> {
                // update model (run on EDT for simplicity — thread-safety)
                for (CelestialObject b : bodies) b.update(panel.getSECS_PER_FRAME(), bodies);
                panel.pushTrailPositions();
                panel.repaint();


            }).start();
        });
    }
}
