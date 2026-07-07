package com.JavaGame;

import java.awt.*;

public enum Planets {

    SUN,
    MERCURY,
    VENUS,
    EARTH,
    MOON,
    MARS;

    // TODO:
    //  - Lookup the mass, radius of the above planets and the semi-major axis distance around the sun
    //  (or in the case of the moon, the semi-major axis around the earth)
    //  - Three significant digits is enough
    //  - Do not edit the other methods

    public double getMass(){
        // TODO: change these values:

        return switch (this){
            case MERCURY -> 0.0;
            case VENUS -> 0.0;
            case EARTH -> 0.0;
            case MOON -> 0.0;
            case MARS -> 0.0;
            case SUN -> 0.0;
        };
    }

    public double getRadius(){

        // TODO: change these values

        return switch (this){
            case MERCURY -> 0.0;
            case VENUS -> 0.0;
            case EARTH -> 0.0;
            case MOON -> 0.0;
            case MARS -> 0.0;
            case SUN -> 0.0;
        };

    }

    public double getSemiMajorAxis(){

        // TODO: change these values, the semi-major axis of the moon is relative to earth

        return switch (this){
            case EARTH -> 0.0;
            case MERCURY -> 0.0;
            case VENUS -> 0.0;
            case MOON -> 0.0;
            case MARS -> 0.0;
            case SUN -> 0.0;
        };

    }

    // region DO NOT EDIT

    public Color getColor(){
        return switch (this){
            case EARTH -> new Color(56, 166, 251);
            case MERCURY -> new Color(153, 171, 188);
            case VENUS -> new Color(103, 101, 71);
            case MOON -> new Color(128, 128, 128);
            case MARS -> new Color(179, 45, 69);
            case SUN -> new Color(255, 213, 75);
        };
    }

    public String getName(){
        return switch (this){
            case EARTH -> "Earth";
            case MERCURY -> "Mercury";
            case VENUS -> "Venus";
            case MOON -> "Moon";
            case MARS -> "Mars";
            case SUN -> "Sun";
        };
    }

    public CelestialObject getNew(){
        if (this == MOON){
            // Special case for moon, needs have semi-major axis relative to earth position
            return new CelestialObject(
                    this.getMass(),
                    this.getRadius(),
                    new Vector(
                            EARTH.getSemiMajorAxis() + this.getSemiMajorAxis(),
                            0
                    ),
                    this.getName()
            );
        }
        return new CelestialObject(this.getMass(), this.getRadius(), new Vector(this.getSemiMajorAxis(), 0), this.getName());
    }

    public CelestialObject getNew(Vector position){
        return new CelestialObject(this.getMass(), this.getRadius(), position, this.getName());
    }

    // endregion
}
