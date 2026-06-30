package com.JavaGame;

import java.awt.*;

public class Sun extends CelestialObject {
    public Sun() {
        double mass = 1.988e30;
        double radius = 695700e3;

        super(mass, radius, new Vector(), "Sun");  // position 0.0 by default
        setAffectedByGravity(false);
    }

    @Override
    public Color getColor() {
        return new Color(243, 187, 44);
    }
}
