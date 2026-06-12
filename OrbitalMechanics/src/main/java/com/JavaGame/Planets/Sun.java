package com.JavaGame.Planets;

import com.JavaGame.CelestialObject;
import com.JavaGame.Vector;

public class Sun extends CelestialObject {
    public Sun() {
        double mass = 1.988e30;
        double radius = 695700e3;

        super(mass, radius, new Vector(), "Sun");  // position 0.0 by default
    }

    @Override
    public boolean affectedByGravity() { return false; }
}
