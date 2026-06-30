package com.JavaGame;

import java.awt.*;

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

    @Override
    public Color getColor() {
        return new Color(154, 154, 79);
    }
}
