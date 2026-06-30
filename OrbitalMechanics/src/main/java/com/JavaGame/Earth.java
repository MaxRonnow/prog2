package com.JavaGame;

import java.awt.*;

public class Earth extends CelestialObject {
    public static final double earthMass = 5.972e24;
    public static final double earthRadius = 6371e3;

    public Earth() {
        super(earthMass, earthRadius, new Vector(149098450e3, 0), "Earth");
    }

    public Earth(Vector position) {
        super(earthMass, earthRadius, position, "Earth");
    }

    @Override
    public Color getColor() {
        return new Color(109, 154, 255);
    }
}
