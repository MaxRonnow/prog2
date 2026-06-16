package com.JavaGame;

import com.JavaGame.Planets.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {

    private static final double SCALE = 1e6;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int SECS_PER_FRAME = 3600 * 24;  // seconds of simulation per frame,
    // upper recommended limit 3600 (1h per frame), otherwise orbits become wonky
    private static final boolean EARTH_MOON_SYSTEM = false;  // false --> solar system (no moons)

    static class SimulationPanel extends JPanel {
        private final java.util.List<CelestialObject> bodies;
        private final CelestialObject centerBody; // optional center (e.g., Earth)
        private static final double metersPerPixel = EARTH_MOON_SYSTEM ? 2e6 : 2e9; // tune to zoom
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

    static void main() {
        SwingUtilities.invokeLater(() -> {
            List<CelestialObject> bodies = getCelestialObjects();

            JFrame frame = new JFrame("Orbital sim");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            SimulationPanel panel = new SimulationPanel(bodies, bodies.getFirst());

            frame.add(panel);
            frame.setSize(WIDTH, HEIGHT);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            new Timer(40, e -> {         // ~25 FPS
                // update model (run on EDT for simplicity — thread-safety)
                for (CelestialObject b : bodies) b.update(SECS_PER_FRAME, bodies);
                panel.pushTrailPositions();
                panel.repaint();
            }).start();
        });
    }

    private static List<CelestialObject> getCelestialObjects() {
        List<CelestialObject> bodies = new ArrayList<>();

        // TODO: remove temp override
        Sun sun1 = new Sun();
        Sun sun2 = new Sun();
        sun2.setParent(sun1);
        sun2.setPosition(new Vector(219098450e3, -49098450e3));
        sun2.setAffectedByGravity(true);
        sun1.setAffectedByGravity(true);
        sun2.setCircularOrbitVelocity();
        bodies.add(sun1);
        bodies.add(sun2);

        Sun sun3 = new Sun();
        sun3.setParent(sun1);
        sun3.setPosition(new Vector(-179098450e3, 0));
        sun3.setAffectedByGravity(true);
        sun3.setCircularOrbitVelocity();
        bodies.add(sun3);

        Mercury merc = new Mercury(sun1);
        // merc.setParent(sun1);
        merc.setCircularOrbitVelocity();
        bodies.add(merc);

        Mars mars = new Mars(sun2);
        mars.setCircularOrbitVelocity();
        bodies.add(mars);

        return bodies;
/*
        if (EARTH_MOON_SYSTEM){
            Earth earth = new Earth();
            Moon moon = new Moon(earth);
            moon.setCircularOrbitVelocity();
            bodies.add(earth);
            bodies.add(moon);
        } else {
            Sun sun = new Sun();
            Earth earth = new Earth(sun);
            earth.setCircularOrbitVelocity();
            bodies.add(sun);
            bodies.add(earth);
            // add other planets here :)
            Mercury merc = new Mercury(sun);
            merc.setCircularOrbitVelocity();
            bodies.add(merc);

            Venus ven = new Venus(sun);
            ven.setCircularOrbitVelocity();
            bodies.add(ven);

            Mars mars = new Mars(sun);
            mars.setCircularOrbitVelocity();
            bodies.add(mars);

        }
        return bodies;

 */
    }
}
