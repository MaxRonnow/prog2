package com.JavaGame;

import java.awt.*;

public class ImplementedVenus extends ImplementedCelestialObject {
    public static final double venusMass = 4.8675e24;
    public static final double venusRadius = 6051.8e3;

    public ImplementedVenus() {
        super(venusMass, venusRadius, new ImplementedVector(108208000e3, 0), "Venus");
    }

    public ImplementedVenus(VectorInterface position) {
        super(venusMass, venusRadius, position, "Venus");
    }

    @Override
    public Color getColor() {
        return new Color(255, 255, 0);
    }
}
