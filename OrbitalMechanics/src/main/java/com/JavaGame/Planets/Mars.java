package com.JavaGame.Planets;

import com.JavaGame.CelestialObject;
import com.JavaGame.Vector;

public class Mars extends CelestialObject {
    public static final double mass = 6.4171e23;
    public static final double radius = 3389.5e3;
    public static final String name = "Mars";
    public static final double semiMajorAxis = 1.523 * AU;

    public Mars() {
        super(mass, radius, new Vector(semiMajorAxis, 0), name);
    }

    public Mars(Vector position) {
        super(mass, radius, position, name);
    }

    public Mars(Vector position, CelestialObject parent) {
        super(mass, radius, position, parent, name);
    }

    public Mars(CelestialObject parent) {
        super(mass, radius, new Vector(semiMajorAxis, 0), parent, name);
    }

}
