package com.JavaGame;

import java.awt.*;

public class Venus extends CelestialObject {
    public static final double venusMass = 4.867e24;
    public static final double venusRadius = 6051e3;
    public static final String venusName = "Venus";
    public static final double semiMajorAxis = 0.723332 * AU;

    public Venus() {
        super(venusMass, venusRadius, new Vector(semiMajorAxis, 0), venusName);
    }

    public Venus(Vector position) {
        super(venusMass, venusRadius, position, venusName);
    }

    @Override
    public Color getColor() {
        return new Color(154, 154, 79);
    }
}
