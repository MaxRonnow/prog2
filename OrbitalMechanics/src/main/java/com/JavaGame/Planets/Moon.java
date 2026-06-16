package com.JavaGame.Planets;

import com.JavaGame.CelestialObject;
import com.JavaGame.Vector;

import static java.lang.Math.pow;

public class Moon extends CelestialObject {
    public static final double moonMass = 7.346e22;
    public static final double moonRadius = 1737e3;

    public Moon(Vector position) {
        super(moonMass, moonRadius, position, "Moon");
    }

    public Moon(Vector position, CelestialObject parent) {
        super(moonMass, moonRadius, position, parent, "Moon");
    }

    public Moon(CelestialObject parent) {
        // assuming parent is earth and we want position correct
        super(moonMass, moonRadius, parent.getPosition().add(new Vector(3.84 * pow(10, 8), 0)), parent, "Moon");
    }
}
