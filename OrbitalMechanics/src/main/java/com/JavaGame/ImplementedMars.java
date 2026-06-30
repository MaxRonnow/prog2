package com.JavaGame;

import java.awt.*;

public class ImplementedMars extends ImplementedCelestialObject {
    public static final double marsMass = 6.4171e23;
    public static final double marsRadius = 3389.5e3;

    public ImplementedMars() {
        super(marsMass, marsRadius, new ImplementedVector(227909200e3, 0), "Mars");
    }

    public ImplementedMars(VectorInterface position) {
        super(marsMass, marsRadius, position, "Mars");
    }

    @Override
    public Color getColor() {
        return new Color(255, 0, 0);
    }
}
