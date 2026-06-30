package com.JavaGame;

import java.awt.*;
import java.util.List;

public class CelestialObject implements CelestialObjectInterface{

    // region Class Fields & Constructor
    private final double mass; // in kg (use double to hold large astronomical masses)
    private final double radius; // in m
    private Vector position;  // A 2D vector representation of the position of this object relative to "world origin" (center of screen), in metres
    private Vector velocity;  // 2D vector representation of direction of travel (m) per second
    public static final double G = 6.67430e-11; // gravitational constant (m^3 kg^-1 s^-2)
    public static final double AU = 1.496e11;  // Astronomical Unit (m)

    private final String name;  // only used for printing
    private boolean affectedByGravity = true;  // for skipping gravity calculations for mostly static objects (magnitudes more massive objects)

    public CelestialObject(double mass, double radius, Vector position, String name){ // mass (kg), radius (m)
        // Constructor: mass in kg, radius in m, position in m
        this.mass = mass;
        this.radius = radius;
        this.position = position;
        this.name = name;
        this.velocity = new Vector();
    }

    // endregion

    // region IMPLEMENT THESE

    public double getGravityForce(final CelestialObjectInterface other){
        // TODO: get the force of gravity between this and the other object
        // return value is in Newton
        return 0;
    }

    public double getSurfaceGravity(){
        // TODO: get the surface acceleration of this object (assuming a perfect sphere)
        // return value in m/s^2
        return 0;
    }

    public double getDensity(){
        // TODO: get the density of this object assuming a perfect sphere
        // return value in g/cm^3 (normal density units).
        return 0;
    }

    @Override
    public double getVolume() {
        // TODO: return the volume of this object assuming a perfect sphere
        return 0;
    }

    public double getCircularSpeed(double otherMass, double distanceToOther){
        // TODO: get the amplitude of the velocity required to orbit another object in a perfect circle
        return 0;
    }

    public double getEscapeSpeed(double distance){
        // TODO: get the minimum velocity required to escape the gravitational influence of this object,
        //  at the provided distance (to the center of mass)
        return 0;
    }

    public double getOrbitalSpeed(double currDistance, double targetHeight){
        // TODO: calculate the speed required to orbit this object between the currDistance to center of mass,
        //  and the targetHeight above this object (assuming perfect sphere)
        //  - return 0 if the orbit is impossible

        return 0;
    }

    // endregion

    // region DO NOT EDIT

    public void updatePosition(int timestep){
        // Based on the velocity and timestep
        this.position = this.position.vectorSum(this.velocity.scalarMult(timestep));
    }

    public void setCircularOrbitVelocity(final CelestialObjectInterface parentObject) {
        // Sets the velocity to orbit the other object in a perfect circle counterclockwise,
        // then adds the other objects own velocity, such that this stays in a relative circular orbit
        if (this.getAffectedByGravity() && parentObject != null){
            // calculate a normal velocity based on position and gravitational force between this and parent object
            Vector toParent = this.position.vectorTo(parentObject.getPosition());
            Vector normal = toParent.getNormal().normalize();
            // https://en.wikipedia.org/wiki/Circular_orbit
            this.velocity = normal.scalarMult(getCircularSpeed(parentObject.getMass(), toParent.getAmplitude()));
            // include parent's velocity so orbit is correct in world coordinates (not only relative to parent)
            this.velocity = this.velocity.vectorSum(parentObject.getVelocity());
        }
    }

    public void update(int timestep, List<CelestialObjectInterface> bodies){
        // assuming timestep is in seconds
        if (!this.getAffectedByGravity()){
            // Update position based on manually set velocity
            this.updatePosition(timestep);
            return;
        }
        // 1. calculate the cumulative forces of gravity to all objects
        // 2. update the position
        Vector velocityChange = new Vector();

        for (CelestialObjectInterface obj : bodies){
            if (obj.equals(this)){
                continue;
            }
            double force = this.getGravityForce(obj);
            Vector direction = this.position.vectorTo(obj.getPosition()).normalize();
            // System.out.println("Direction of gravity: " + direction.toString());
            double directionMult = force * timestep / this.mass;
            // System.out.println("Directionmult: " + directionMult);
            velocityChange = velocityChange.vectorSum(direction.scalarMult(directionMult));
            // System.out.println("Current velocity change..." + velocityChange);
        }
        this.velocity = this.velocity.vectorSum(velocityChange);
        this.updatePosition(timestep);
    }

    // endregion

    // region GETTERS-SETTERS

    public boolean getAffectedByGravity() { return this.affectedByGravity; }

    public void setAffectedByGravity(boolean b){this.affectedByGravity = b;}

    public Vector getPosition() {
        return this.position;
    }

    public Vector getVelocity(){
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
        this.position = (Vector) vector;
    }

    // endregion
}
