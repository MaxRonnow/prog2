package com.JavaGame;

import static java.lang.Math.pow;


public class CelestialObject {
    private final int mass; // in kg
    private final int radius; // in m
    private Vector position;
    private Vector velocity;
    public static final double G = 6.7 * pow(10, -11);
    // private final float angularVelocity;

    public CelestialObject(int mass, int radius, Vector position){
        this.mass = mass;
        this.radius = radius;
        this.position = position;
    }

    public void gravity(CelestialObject other){
        double force = G * (this.mass * other.mass) / this.position.getDistance(other.position);
    }

    public double getDensity(){
        return this.mass / ((4.0 / 3.0) * 3.14 * pow(this.radius, 3));
    }

    public void updatePosition(){
        this.position = this.position.add(this.velocity);
    }

}
