package com.JavaGame;

import static java.lang.Math.*;

public class Vector implements VectorInterface{

    /*
    THIS CLASS SHOULD HAVE THE METHODS IMPLEMENTED
    GO TO EACH "TO-DO" AND IMPLEMENT ACCORDING TO INSTRUCTIONS

    The class represents a 2D vector in the real coordinate system as x and y doubles.
    This is an immutable class, meaning it could be converted to a "record" class easily.
    Therefore, any calculation that returns a Vector should either return "this" or a new Vector
     */

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

    public double getDistance(final VectorInterface other){
        // TODO: implement the distance between two vectors (the hypotenuse)
        return 0;
    }

    public Vector vectorSum(final VectorInterface other){
        // TODO: sum of two vectors
        return new Vector();
    }

    public Vector vectorTo(final VectorInterface other){
        // TODO: what is the vector that connects this to the other vector?
        return new Vector();
    }

    public Vector scalarMult(final double z){
        // TODO: scale each vector dimension with z
        return new Vector();
    }

    public Vector normalize(){
        // TODO: return a vector in the same direction as this but with length 1
        return new Vector();
    }

    public double getAmplitude(){
        // TODO: what is the length of the vector?
        return 0;
    }

    public double dotProduct(final VectorInterface other){
        // TODO: get the dot product of two vectors
        return 0;
    }

    public Vector getNormal(){
        // TODO: Return a new vector of this that is rotated 90 degrees counter-clockwise
        return new Vector();
    }

    // region DO NOT EDIT

    public Vector flip() {
        return new Vector(-this.x, -this.y);
    }

    public String toString(){
        return String.format("(%.3f; %.3f)", this.x, this.y);
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    // endregion
}
