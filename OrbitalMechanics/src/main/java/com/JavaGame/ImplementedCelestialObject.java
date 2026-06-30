package com.JavaGame;

import java.awt.*;
import java.util.List;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class ImplementedCelestialObject implements CelestialObjectInterface{
    private final double mass; // in kg (use double to hold large astronomical masses)
    private final double radius; // in m
    private ImplementedVector position;  // A 2D vector representation of the position of this object relative to "world origin" (center of screen), in metres
    private ImplementedVector velocity;  // 2D vector representation of direction of travel (m) per second
    public static final double G = 6.67430e-11; // gravitational constant (m^3 kg^-1 s^-2)
    public static final double AU = 1.496e11;  // Astronomical Unit (m)
    public static final double sphereVolMultiplier = (4.0/3.0)*Math.PI;

    private final String name;  // only used for printing
    private boolean affectedByGravity = true;  // for skipping gravity calculations for mostly static objects (magnitudes more massive objects)

    public ImplementedCelestialObject(double mass, double radius, VectorInterface position, String name){ // mass (kg), radius (m)
        // Constructor: mass in kg, radius in m, position in m
        this.mass = mass;
        this.radius = radius;
        this.position = (ImplementedVector) position;
        this.name = name;
        this.velocity = new ImplementedVector();
    }

    public double getGravityForce(final CelestialObjectInterface other){
        // returns value in Newton
        return G * this.mass * other.getMass() / pow(this.position.getDistance(other.getPosition()), 2);
    }

    public double getSurfaceGravity(){
        // return value in m/s^2
        return G * this.mass / pow(this.radius, 2);
    }

    public double getDensity(){
        // return value in kg/m^3 (kilograms per cubic meter). Divide by 1000 to get g/cm^3 (normal density units).
        return (this.mass / this.getVolume()) / 1000.0;
    }

    @Override
    public double getVolume() {
        return (sphereVolMultiplier * pow(this.radius, 3));
    }

    public void updatePosition(int timestep){
        // Based on the velocity and timestep
        this.position = this.position.vectorSum(this.velocity.scalarMult(timestep));
    }

    public double getCircularSpeed(double otherMass, double distanceToOther){
        return sqrt((G * (this.mass + otherMass)) / distanceToOther);
    }

    public double getEscapeSpeed(double distance){
        return sqrt((2*G*this.mass) / distance);
    }

    public double getOrbitalSpeed(double currDistance, double targetHeight){
        // assumes distance to body center
        // targetHeight is above the planet surface
        // Check that position is above body surface
        if (currDistance <= this.radius){
            System.out.println("Distance is inside the body radius");
            return 0;
        }

        double grav = G * this.mass;
        double periapsis = this.radius + targetHeight;

        return sqrt(
                grav * (2 / currDistance - 2 / (currDistance + periapsis))
        );
    }

    // region DO NOT EDIT

    public void setCircularOrbitVelocity(final CelestialObjectInterface parentObject) {
        // Sets the velocity to orbit the other object in a perfect circle counterclockwise,
        // then adds the other objects own velocity, such that this stays in a relative circular orbit
        if (this.getAffectedByGravity() && parentObject != null){
            // calculate a normal velocity based on position and gravitational force between this and parent object
            VectorInterface toParent = this.getPosition().vectorTo(parentObject.getPosition());
            VectorInterface normal = toParent.getNormal().normalize();
            // https://en.wikipedia.org/wiki/Circular_orbit
            this.velocity = (ImplementedVector) normal.scalarMult(this.getCircularSpeed(parentObject.getMass(), toParent.getAmplitude()));
            // include parent's velocity so orbit is correct in world coordinates (not only relative to parent)
            this.velocity = this.velocity.vectorSum(parentObject.getVelocity());
        }
    }

    public void update(int timestep, List<CelestialObjectInterface> bodies){
        // assuming timestep is in seconds
        if (!this.getAffectedByGravity()){
            // position I guess can be updated if velocity is set manually
            this.updatePosition(timestep);
            return;
        }
        // 1. calculate the cumulative forces of gravity to all objects
        // 2. update the position
        ImplementedVector velocityChange = new ImplementedVector();

        for (CelestialObjectInterface obj : bodies){
            if (obj.equals(this)){
                continue;
            }
            double force = this.getGravityForce(obj);
            VectorInterface direction = this.position.vectorTo(obj.getPosition()).normalize();
            // System.out.println("Direction of gravity: " + direction.toString());
            double directionMult = force * timestep / this.mass;
            // System.out.println("Directionmult: " + directionMult);
            velocityChange = (ImplementedVector) velocityChange.vectorSum(direction.scalarMult(directionMult));
            // System.out.println("Current velocity change..." + velocityChange);
        }
        this.velocity = (ImplementedVector) this.velocity.vectorSum(velocityChange);
        this.updatePosition(timestep);
    }

    // endregion

    // region GETTERS-SETTERS

    public boolean getAffectedByGravity() { return this.affectedByGravity; }
    public void setAffectedByGravity(boolean b){this.affectedByGravity = b;}

    public VectorInterface getPosition() {
        return this.position;
    }

    public VectorInterface getVelocity(){
        return this.velocity;
    }

    public double getMass() {
        return this.mass;
    }

    @Override
    public Color getColor() {
        return new Color(246, 149, 99);
    }

    public String getMovementStats() {
        return String.format("%s: Position: %s, Velocity: %s", this.name, this.position.toString(), this.velocity.toString());
    }

    public String getName() {
        return this.name;
    }

    public double getRadius() {
        return this.radius;
    }

    public void setPosition(VectorInterface vector) {
        this.position = (ImplementedVector) vector;
    }

    // endregion
}
