package com.JavaGame;

// new laptop who dis?

import com.JavaGame.Planets.Earth;
import com.JavaGame.Planets.Moon;

public class Main {
    static void main() {
        Simulator solarSim = new Simulator(true);
        solarSim.setCircularOrbits();
        solarSim.runSimulation();
    }
}
