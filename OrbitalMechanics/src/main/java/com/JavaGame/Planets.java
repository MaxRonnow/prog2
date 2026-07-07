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

        return switch (this){
            case MERCURY -> 3.2837e23;
            case VENUS -> 4.8675e24;
            case EARTH -> 5.972e24;
            case MOON -> 7.346e22;
            case MARS -> 6.4171e23;
            case SUN -> 1.988e30;
        };

        /*
        return switch (this){
            case MERCURY -> 0.0;
            case VENUS -> 0.0;
            case EARTH -> 0.0;
            case MOON -> 0.0;
            case MARS -> 0.0;
            case SUN -> 0.0;
        };

         */
    }

    public double getRadius(){

        return switch (this){
            case MERCURY -> 2439.7e3;
            case VENUS -> 6051.8e3;
            case EARTH -> 6371e3;
            case MOON -> 1737e3;
            case MARS -> 3389.5e3;
            case SUN -> 695700e3;
        };
        /*
        return switch (this){
            case MERCURY -> 0.0;
            case VENUS -> 0.0;
            case EARTH -> 0.0;
            case MOON -> 0.0;
            case MARS -> 0.0;
            case SUN -> 0.0;
        };
         */
    }

    public double getSemiMajorAxis(){

        return switch (this){
            case EARTH -> 149098450e3;
            case MERCURY -> 57909050e3;
            case VENUS -> 108208000e3;
            case MOON -> 384399e3;
            case MARS -> 227909200e3;
            case SUN -> 0.0;
        };
        /*
        return switch (this){
            case EARTH -> 0.0;
            case MERCURY -> 0.0;
            case VENUS -> 0.0;
            case MOON -> 0.0;
            case MARS -> 0.0;
            case SUN -> 0.0;
        };

         */
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
        return new CelestialObject(this.getMass(), this.getRadius(), new Vector(this.getSemiMajorAxis(), 0), this.getName());
    }

    public CelestialObject getNew(Vector position){
        return new CelestialObject(this.getMass(), this.getRadius(), position, this.getName());
    }

    // endregion
}
