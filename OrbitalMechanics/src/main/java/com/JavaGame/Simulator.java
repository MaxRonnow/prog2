package com.JavaGame;

import com.JavaGame.Planets.Earth;
import com.JavaGame.Planets.Moon;
import com.JavaGame.Planets.Sun;

import java.util.ArrayList;
import java.util.List;

public class Simulator {
    public static final int secondsPerTimestep = 3600 * 24;  // 1d

    private final List<CelestialObject> bodies;

    public Simulator(boolean solarSystem){
        this.bodies = new ArrayList<>();
        if (solarSystem){
            bodies.add(new Sun());
            bodies.add(new Earth(bodies.getFirst()));
            bodies.add(new Moon(bodies.getLast()));
        }
    }

    public void setCircularOrbits(){
        for (CelestialObject obj : bodies){
            obj.setCircularOrbitVelocity();
        }
    }

    public void runSimulation(){
        int timeSteps = 0;

        while (timeSteps < 1000){
            for (CelestialObject obj : this.bodies){
                obj.update(secondsPerTimestep);
                System.out.println(obj.getMovementStats());
                // check that the orbital distance is reasonable
                double orbitalDistance = obj.getOrbitalDistance();
                // TODO: check
                if (orbitalDistance < obj.getInitOrbitalDistance() / 2 || orbitalDistance > obj.getInitOrbitalDistance() * 2){
                    System.out.println(obj.getName() + "Orbital distance is unreasonable");
                }
            }

            // show the system somehow


            timeSteps++;
        }

        System.out.println("Simulated " + secondsPerTimestep * timeSteps + " seconds of the solar system");
    }
}
