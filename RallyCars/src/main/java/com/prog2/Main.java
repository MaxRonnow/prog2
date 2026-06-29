package com.prog2;

import com.prog2.roads.Road;
import com.prog2.vehicles.Vehicle;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 200;

    static class RallyPanel extends JPanel {
        private final Race race;
        private static final double pixelsPerMeter = 2;
        private static final Color BLACK = new Color(0, 0, 0);
        private static final Color WHITE = new Color(255, 255, 255);


        public RallyPanel(Race race){
            this.race = race;
            this.race.start();
        }

        @Override
        protected void paintComponent(Graphics g0){
            super.paintComponent(g0);
            Graphics2D g = (Graphics2D) g0;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int roadY = Main.HEIGHT / 2;
            int roadGirth = 80;
            // drawing a start line
            g.setColor(new Color(255, 0, 0));
            g.fillRect(0, roadY, 10, roadGirth);
            // draw roads
            Road currRoad = this.race.getStartRoad();
            int currX = 10;
            int vehicleX = 20;
            int vehicleY = 8;
            int nrVehicles = this.race.getVehicles().size();
            while (currRoad != null){
                int roadLength = (int) (currRoad.getLength() * pixelsPerMeter);
                Color c = currRoad.getColor();
                g.setColor(c);
                g.fillRect(currX, roadY, roadLength, roadGirth);
                for (int k=0; k<currRoad.getLength(); k += 10){
                    com.prog2.roads.Turn t = currRoad.getTurnAt(k);
                    if (t != null){
                        int turnX = (int) (currX + t.getAtRoadDistance() * pixelsPerMeter);
                        g.setColor(new Color(240, 245, 46));
                        int turnLength = (int) (t.getDistanceRequired() * pixelsPerMeter);
                        g.fillRect(turnX, roadY, turnLength, roadGirth);

                        k += t.getDistanceRequired();
                    }
                }
                currRoad = currRoad.getNextRoad();
                currX += roadLength;
            }
            // drawing a finish line at the end
            g.setColor(new Color(255, 0, 0));
            g.fillRect(currX, roadY, 10, roadGirth);

            // loop again to draw the cars on top of everything
            currRoad = this.race.getStartRoad();
            currX = 10;
            while (currRoad != null){
                int roadLength = (int) (currRoad.getLength() * pixelsPerMeter);
                for (int i=0;i<nrVehicles; i++){
                    Vehicle veh = this.race.getVehicles().get(i);
                    // TODO: might need to override Road equals method
                    if (veh.getCurrentRoad() != null && veh.getCurrentRoad() == currRoad){
                        int currPos = (int) (currX + veh.getRoadDistanceTravelled() * pixelsPerMeter);
                        g.setColor(veh.getColor());
                        g.fillRect(currPos, roadY + i * 2 * vehicleY, vehicleX, vehicleY);
                    }
                }
                currRoad = currRoad.getNextRoad();
                currX += roadLength;
            }


        }
    }

    static void main() {
        SwingUtilities.invokeLater(() -> {
            Race race = new Race();
            race.start();

            JFrame frame = new JFrame("Race cars");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            RallyPanel panel = new RallyPanel(race);

            frame.add(panel);
            frame.setSize(WIDTH, HEIGHT);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            new Timer(333, e -> {
                race.update();
                panel.repaint();
            }).start();
        });
    }
}
