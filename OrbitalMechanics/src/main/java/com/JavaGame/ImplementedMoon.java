package com.JavaGame;

import java.awt.*;

public class ImplementedMoon extends ImplementedCelestialObject {
    public static final double moonMass = 7.346e22;
    public static final double moonRadius = 1737e3;

    public ImplementedMoon() {
        super(moonMass, moonRadius, new ImplementedVector(384399e3, 0), "Moon");
    }

    public ImplementedMoon(VectorInterface position) {
        super(moonMass, moonRadius, position, "Moon");
    }

    @Override
    public Color getColor() {
        return new Color(110, 103, 103);
    }
}
