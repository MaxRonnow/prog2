package com.JavaGame;

import java.awt.*;

public class ImplementedMercury extends ImplementedCelestialObject {
    public static final double mercuryMass = 3.2837e23;
    public static final double mercuryRadius = 2439.7e3;

    public ImplementedMercury() {
        super(mercuryMass, mercuryRadius, new ImplementedVector(57909050e3, 0), "Mercury");
    }

    public ImplementedMercury(VectorInterface position) {
        super(mercuryMass, mercuryRadius, position, "Mercury");
    }

    @Override
    public Color getColor() {
        return new Color(128, 128, 128);
    }
}
