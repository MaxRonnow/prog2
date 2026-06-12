package com.JavaGame.Planets;

import com.JavaGame.CelestialObject;
import com.JavaGame.Vector;

import static java.lang.Math.pow;

public class Earth extends CelestialObject {
    public Earth() {
        double mass = 6e24;
        double radius = 6371e3;
        super(mass, radius, new Vector(149098450e3, 0), "Earth");
    }

    public Earth(Vector position) {
        double mass = 6e24;
        double radius = 6371e3;
        super(mass, radius, position, "Earth");
    }

    public Earth(Vector position, CelestialObject parent) {
        // Custom position and set parent
        double mass = 6e24;
        double radius = 6371e3;
        super(mass, radius, position, parent, "Earth");
    }

    public Earth(CelestialObject parent) {
        double mass = 6e24;
        double radius = 6371e3;
        super(mass, radius, new Vector(149098450e3, 0), parent, "Earth");
    }
}
