package com.JavaGame;

import java.awt.*;

public class Moon extends CelestialObject {
    public static final double moonMass = 7.346e22;
    public static final double moonRadius = 1737e3;

    public Moon() {
        super(moonMass, moonRadius, new Vector(384399e3, 0), "Moon");
    }

    public Moon(Vector position) {
        super(moonMass, moonRadius, position, "Moon");
    }

    @Override
    public Color getColor() {
        return new Color(110, 103, 103);
    }
}
