package com.JavaGame;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public interface VectorInterface {

    double getDistance(final VectorInterface other);

    VectorInterface vectorSum(final VectorInterface other);
    VectorInterface vectorTo(final VectorInterface other);

    VectorInterface scalarMult(final double z);

    VectorInterface normalize();

    double getAmplitude();

    double dotProduct(final VectorInterface other);

    VectorInterface getNormal();

    VectorInterface flip();

    String toString();

    double getX();

    double getY();


}
