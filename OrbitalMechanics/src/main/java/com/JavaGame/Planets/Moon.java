package com.JavaGame.Planets;

import com.JavaGame.CelestialObject;
import com.JavaGame.Vector;

import static java.lang.Math.pow;

public class Moon extends CelestialObject {
    public Moon(Vector position) {
        double mass = 7.346e22;
        double radius = 1737e3;
        super(mass, radius, position, "Moon");
    }

    public Moon(Vector position, CelestialObject parent) {
        double mass = 7.346e22;
        double radius = 1737e3;
        super(mass, radius, position, parent, "Moon");
    }

    public Moon(CelestialObject parent) {
        double mass = 7.346e22;
        double radius = 1737e3;
        // assuming parent is earth and we want position correct
        super(mass, radius, parent.getPosition().add(new Vector(3.84 * pow(10, 8), 0)), parent, "Moon");
    }
}
