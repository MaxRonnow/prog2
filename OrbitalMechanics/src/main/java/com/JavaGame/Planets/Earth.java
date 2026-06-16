package com.JavaGame.Planets;

import com.JavaGame.CelestialObject;
import com.JavaGame.Vector;

import static java.lang.Math.pow;

public class Earth extends CelestialObject {
    public static final double earthMass = 5.972e24;
    public static final double earthRadius = 6371e3;

    public Earth() {
        super(earthMass, earthRadius, new Vector(149098450e3, 0), "Earth");
    }

    public Earth(Vector position) {
        super(earthMass, earthRadius, position, "Earth");
    }

    public Earth(Vector position, CelestialObject parent) {
        super(earthMass, earthRadius, position, parent, "Earth");
    }

    public Earth(CelestialObject parent) {
        super(earthMass, earthRadius, new Vector(149098450e3, 0), parent, "Earth");
    }
}
