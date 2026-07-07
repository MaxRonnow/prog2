package com.JavaGame;

import java.awt.*;
import java.util.List;

public interface CelestialObjectInterface {
    double getGravityForce(final CelestialObjectInterface other);
    double getSurfaceGravity();
    double getDensity();
    double getVolume();
    double getCircularSpeed(double otherMass, double distanceToOther);
    double getEscapeSpeed(double distance);
    double getOrbitalSpeed(double currDistance, double targetHeight);
    void updatePosition(int timestep);
    void setCircularOrbitVelocity(final CelestialObjectInterface parentObject);

    VectorInterface getPosition();
    VectorInterface getVelocity();
    double getRadius();
    double getMass();
    Color getColor();
    void setAffectedByGravity(boolean b);
    boolean getAffectedByGravity();
    void setPosition(VectorInterface vector);
}
