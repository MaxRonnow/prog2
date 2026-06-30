package com.JavaGame;

import java.awt.*;

public class ImplementedEarth extends ImplementedCelestialObject {
    public static final double earthMass = 5.972e24;
    public static final double earthRadius = 6371e3;

    public ImplementedEarth() {
        super(earthMass, earthRadius, new ImplementedVector(149098450e3, 0), "Earth");
    }

    public ImplementedEarth(VectorInterface position) {
        super(earthMass, earthRadius, position, "Earth");
    }

    @Override
    public Color getColor() {
        return new Color(109, 154, 255);
    }
}
