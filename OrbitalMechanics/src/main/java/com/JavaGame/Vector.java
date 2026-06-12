package com.JavaGame;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Vector {
    private final double x;
    private final double y;

    public Vector(){
        this.x = 0;
        this.y = 0;
    }

    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getDistance(final Vector other){
        // hypothenuse
        return sqrt(pow(this.x - other.x, 2) + pow(this.y - other.y, 2));
    }

    public Vector add(final Vector other){
        return new Vector(this.x + other.x, this.y + other.y);
    }

    public Vector to(final Vector other){
        return new Vector(other.x - this.x, other.y - this.y);
    }

    public Vector mult(final double z){
        return new Vector(this.x * z, this.y * z);
    }

    public Vector normalize(){
        return this.mult(1 / getAmplitude());
    }

    public double getAmplitude(){
        return sqrt(pow(this.x, 2) + pow(this.y, 2));
    }

    public double dot(final Vector other){
        return this.x * other.x + this.y * other.y;
    }

    public Vector getNormal(){
        return new Vector(-this.y, this.x);
    }

    public Vector flip() { return new Vector(-this.x, -this.y); }

    public String toString(){
        return String.format("(%.3f; %.3f)", this.x, this.y);
    }
}
