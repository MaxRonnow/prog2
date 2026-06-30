package com.JavaGame;

import java.awt.*;

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

    @Override
    public Color getColor() {
        return new Color(181, 130, 130);
    }
}
