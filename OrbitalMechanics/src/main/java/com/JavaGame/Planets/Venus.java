package com.JavaGame.Planets;

import com.JavaGame.CelestialObject;
import com.JavaGame.Vector;

public class Venus extends CelestialObject {
    public static final double mass = 4.867e24;
    public static final double radius = 6051e3;
    public static final String name = "Venus";
    public static final double semiMajorAxis = 0.723332 * AU;

    public Venus() {
        super(mass, radius, new Vector(semiMajorAxis, 0), name);
    }

    public Venus(Vector position) {
        super(mass, radius, position, name);
    }

    public Venus(Vector position, CelestialObject parent) {
        super(mass, radius, position, parent, name);
    }

    public Venus(CelestialObject parent) {
        super(mass, radius, new Vector(semiMajorAxis, 0), parent, name);
    }

}
