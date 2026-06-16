package com.JavaGame;

import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


public class CelestialObject {
    private final double mass; // in kg (use double to hold large astronomical masses)
    private final double radius; // in m
    private Vector position;
    private Vector velocity;
    public static final double G = 6.67430e-11; // gravitational constant (m^3 kg^-1 s^-2)
    public static final double AU = 1.496e11;  // Astronomical Unit (m)
    public static final double sphereVolMultiplier = (4.0/3.0)*Math.PI;
    // private final float angularVelocity;
    private CelestialObject parent;
    private final String name;
    private double initOrbitalDistance;
    private boolean affectedByGravity = true;

    public CelestialObject(double mass, double radius, Vector position, String name){ // mass (kg), radius (m)
        // mass in kg, radius in m, position in m
        this.mass = mass;
        this.radius = radius;
        this.position = position;
        this.parent = null;
        this.name = name;
        this.velocity = new Vector();
    }

    public CelestialObject(double mass, double radius, Vector position, CelestialObject parent, String name){ // mass (kg), radius (m)
        this.mass = mass;
        this.radius = radius;
        this.position = position;
        this.parent = parent;
        this.name = name;
        this.velocity = new Vector();
        this.initOrbitalDistance = this.getOrbitalDistance();
    }

    public double getOrbitalDistance(){
        if (this.getParent() != null){
            return this.position.to(this.getParent().getPosition()).getAmplitude();
        }
        return 0;
    }

    public void setParent(CelestialObject obj){
        this.parent = obj;
        this.initOrbitalDistance = this.getOrbitalDistance();
    }

    public CelestialObject getParent(){
        return this.parent;
    }

    public double getGravityForce(CelestialObject other){
        // returns value in Newton
        return G * this.mass * other.mass / pow(this.position.getDistance(other.position), 2);
    }

    public double getSurfaceGravity(){
        // return value in m/s^2
        return G * this.mass / pow(this.radius, 2);
    }

    public double getDensity(){
        // return value in kg/m^3 (kilograms per cubic meter). Divide by 1000 to get g/cm^3 (normal density units).
        return (this.mass / (sphereVolMultiplier * pow(this.radius, 3))) / 1000.0;
    }

    public void updatePosition(int timestep){
        this.position = this.position.add(this.velocity.mult(timestep));
    }

    public boolean getAffectedByGravity() { return this.affectedByGravity; }
    public void setAffectedByGravity(boolean b){this.affectedByGravity = b;}

    public Vector getPosition() {
        return this.position;
    }

    public void addParentVelocity(){
        if (this.getParent() != null) {
            this.velocity = this.velocity.add(this.parent.velocity);
            // assuming only one level of parent, and grandfather doesn't move
        }
    }

    public void setCircularOrbitVelocity() {
        if (this.getAffectedByGravity() && this.getParent() != null){
            // calculate a normal velocity based on position and gravitational force between this and parent object
            Vector toParent = this.position.to(this.parent.position);
            Vector normal = toParent.getNormal().normalize();
            // https://en.wikipedia.org/wiki/Circular_orbit
            this.velocity = normal.mult(sqrt((G * (this.mass + parent.mass)) / toParent.getAmplitude()));
            // include parent's velocity so orbit is correct in world coordinates (not only relative to parent)
            this.addParentVelocity();
        }
    }

    public double getEscapeVelocity(double distance){
        return sqrt((2*G*this.mass) / distance);
    }

    public Vector getVelocity(){
        return this.velocity;
    }

    public double getMass() {
        return this.mass;
    }

    public void addVelocity(Vector dv) {
        this.velocity = this.velocity.add(dv);
    }

    public void update(int timestep){
        // assuming timestep is in seconds
        if (!this.getAffectedByGravity()){
            // position I guess can be updated if velocity is set manually
            this.updatePosition(timestep);
            return;
        }
        // 1. calculate the cumulative forces of gravity from parent (and parents parent etc.)
        // 2. update the position
        Vector velocityChange = new Vector();
        CelestialObject currObj = this.getParent();
        while (currObj != null){
            double force = this.getGravityForce(currObj);
            Vector direction = this.position.to(currObj.getPosition()).normalize();
            // System.out.println("Direction of gravity: " + direction.toString());
            double directionMult = force * timestep / this.mass;
            // System.out.println("Directionmult: " + directionMult);
            velocityChange = velocityChange.add(direction.mult(directionMult));
            // System.out.println("Current velocity change..." + velocityChange);

            currObj = currObj.getParent();
        }
        this.velocity = this.velocity.add(velocityChange);
        this.updatePosition(timestep);
    }

    public void update(int timestep, List<CelestialObject> bodies){
        // assuming timestep is in seconds
        if (!this.getAffectedByGravity()){
            // position I guess can be updated if velocity is set manually
            this.updatePosition(timestep);
            return;
        }
        // 1. calculate the cumulative forces of gravity to all objects
        // 2. update the position
        Vector velocityChange = new Vector();

        for (CelestialObject obj : bodies){
            if (obj.equals(this)){
                continue;
            }
            double force = this.getGravityForce(obj);
            Vector direction = this.position.to(obj.getPosition()).normalize();
            // System.out.println("Direction of gravity: " + direction.toString());
            double directionMult = force * timestep / this.mass;
            // System.out.println("Directionmult: " + directionMult);
            velocityChange = velocityChange.add(direction.mult(directionMult));
            // System.out.println("Current velocity change..." + velocityChange);
        }
        this.velocity = this.velocity.add(velocityChange);
        this.updatePosition(timestep);
    }

    public String getMovementStats() {
        return String.format("%s: Position: %s, Velocity: %s", this.name, this.position.toString(), this.velocity.toString());
    }

    public double getInitOrbitalDistance() {
        return this.initOrbitalDistance;
    }

    public String getName() {
        return this.name;
    }

    public double getOrbitalVelocity(double currDistance, double targetHeight){
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

    public double getRadius() {
        return this.radius;
    }

    public void setPosition(Vector vector) {
        this.position = vector;
    }
}
