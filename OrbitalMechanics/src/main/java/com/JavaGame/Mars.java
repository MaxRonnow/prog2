package com.JavaGame;

import java.awt.*;

public class Mars extends CelestialObject {
    public static final double marsMass = 6.4171e23;
    public static final double marsRadius = 3389.5e3;
    public static final String marsName = "Mars";
    public static final double semiMajorAxis = 1.523 * AU;

    public Mars() {
        super(marsMass, marsRadius, new Vector(semiMajorAxis, 0), marsName);
    }

    public Mars(Vector position) {
        super(marsMass, marsRadius, position, marsName);
    }

    @Override
    public Color getColor() {
        return new Color(239, 43, 43);
    }
}
