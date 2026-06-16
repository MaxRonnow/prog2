package com.JavaGame.Planets;

import com.JavaGame.CelestialObject;
import com.JavaGame.Vector;

public class Mercury extends CelestialObject {
    public static final double mercuryMass = 3.3011e23;
    public static final double mercuryRadius = 2439e3;
    public static final String name = "Mercury";
    public static final double semiMajorAxis = 0.387098 * AU;

    public Mercury() {
        super(mercuryMass, mercuryRadius, new Vector(semiMajorAxis, 0), name);
    }

    public Mercury(Vector position) {
        super(mercuryMass, mercuryRadius, position, name);
    }

    public Mercury(Vector position, CelestialObject parent) {
        super(mercuryMass, mercuryRadius, position, parent, name);
    }

    public Mercury(CelestialObject parent) {
        super(mercuryMass, mercuryRadius, new Vector(semiMajorAxis, 0), parent, name);
    }

}
