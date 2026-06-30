package com.JavaGame;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class ImplementedVector implements VectorInterface{
    private final double x;
    private final double y;

    public ImplementedVector(){
        this.x = 0;
        this.y = 0;
    }

    public ImplementedVector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getDistance(final VectorInterface other){
        // hypothenuse
        return sqrt(pow(this.x - other.getX(), 2) + pow(this.y - other.getY(), 2));
    }

    public ImplementedVector vectorSum(final VectorInterface other){
        return new ImplementedVector(this.x + other.getX(), this.y + other.getY());
    }

    public ImplementedVector vectorTo(final VectorInterface other){
        return new ImplementedVector(other.getX() - this.x, other.getY() - this.y);
    }

    public ImplementedVector scalarMult(final double z){
        return new ImplementedVector(this.x * z, this.y * z);
    }

    public ImplementedVector normalize(){
        return this.scalarMult(1 / getAmplitude());
    }

    public double getAmplitude(){
        return sqrt(pow(this.x, 2) + pow(this.y, 2));
    }

    public double dotProduct(final VectorInterface other){
        return this.x * other.getX() + this.y * other.getY();
    }

    public ImplementedVector getNormal(){
        return new ImplementedVector(-this.y, this.x);
    }

    public ImplementedVector flip() { return new ImplementedVector(-this.x, -this.y); }

    public String toString(){
        return String.format("(%.3f; %.3f)", this.x, this.y);
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }
}
