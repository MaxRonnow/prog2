package com.JavaGame;

import java.awt.*;

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

    @Override
    public Color getColor() {
        return new Color(239, 43, 43);
    }
}
