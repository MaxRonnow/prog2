package com.JavaGame;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestOrbitalMechanics {
    public static double errorMargin = 0.05;

    private CelestialObjectInterface sun;
    private CelestialObjectInterface earth;
    private CelestialObjectInterface moon;

    private Vector a;
    private Vector b;
    private Vector c;

    @BeforeEach
    public void createPlanets(){
        sun = Planets.SUN.getNew();
        earth = Planets.EARTH.getNew();
        moon = Planets.MOON.getNew();

        a = new Vector(3,4);
        b = new Vector(1,2);
        c = new Vector(-1,-2);
    }

    @AfterEach
    public void resetSpeeds(){
        // Not needed maybe
    }

    @Test
    public void testEarth(){
        assertTrue(5.9e24 < earth.getMass() && earth.getMass() < 6.0e24);
        assertTrue(6340e3 < earth.getRadius() && earth.getRadius() < 6380e3);
        assertTrue(140098450e3 < earth.getPosition().getAmplitude() && earth.getPosition().getAmplitude() < 154098450e3);
    }

    @Test
    public void testVolume(){
        double moonVolume = 2.1958e10 * 1e9;
        double cMV = moon.getVolume();
        assertTrue(moonVolume * (1-errorMargin) < cMV && cMV < moonVolume * (1+errorMargin), "Measuring moon volume");
    }

    @Test
    public void testSurfaceGravity(){
        double earthSurfaceGravity = 9.806;
        double moonSurfaceGravity = 1.622;
        double sunSurfaceGravity = 274;

        double eG = earth.getSurfaceGravity();
        assertTrue(earthSurfaceGravity * (1-errorMargin) < eG && eG < earthSurfaceGravity * (1+errorMargin), "Measuring earth gravity, expected 9.806, was " + eG);
        double mG = moon.getSurfaceGravity();
        assertTrue(moonSurfaceGravity * (1-errorMargin) < mG && mG < moonSurfaceGravity * (1+errorMargin), "Measuring moon gravity, expected 1.622, was " + mG);
        double sG = sun.getSurfaceGravity();
        assertTrue(sunSurfaceGravity * (1-errorMargin) < sG && sG < sunSurfaceGravity * (1+errorMargin), "Measuring sun gravity, expected 274, was " + sG);

    }

    @Test
    public void testDensity(){
        double sunDensity = 1.408;
        double earthDensity = 5.513;
        double moonDensity = 3.344;

        double eDen = earth.getDensity();
        assertTrue(earthDensity * (1.0-errorMargin) < eDen && eDen < earthDensity * (1.0+errorMargin), "Measuring earth density, expected 5.513, was " + eDen);
        double mDen = moon.getDensity();
        assertTrue((moonDensity * (1.0-errorMargin) < mDen) && (mDen < moonDensity * (1.0+errorMargin)), "Measuring moon density, expected 3.344, was " + mDen);
        double sDen = sun.getDensity();
        assertTrue(sunDensity * (1.0-errorMargin) < sDen && sDen < sunDensity * (1.0+errorMargin), "Measuring sun density, expected 1.408, was " + sDen);
    }

    @Test
    public void testGravityForce(){
        double moonEarthForce = 1.971e20;
        double meF = earth.getGravityForce(moon);
        double emF = moon.getGravityForce(earth);
        double earthMoonDistance = 3.84e8;
        double calcDistance = earth.getPosition().getDistance(moon.getPosition());
        assertTrue(calcDistance * (1-errorMargin) < earthMoonDistance && earthMoonDistance < calcDistance * (1+errorMargin), "Calculated distance between moon and earth wrong!");
        assertTrue(moonEarthForce * (1-errorMargin) < emF && emF < moonEarthForce * (1+errorMargin), "Measuring earth-moon force, expected 1.97e20, was " + emF);
        assertTrue(moonEarthForce * (1-errorMargin) < meF && meF < moonEarthForce * (1+errorMargin), "Measuring moon-earth force, expected 1.97e20, was " + meF);

        double earthSunForce = 3.54e22;
        double esF = earth.getGravityForce(sun);
        assertTrue(earthSunForce * (1-errorMargin) < esF && esF < earthSunForce * (1+errorMargin), "Measuring earth-sun force, expected 3.54e22, was " + esF);
    }

    @Test
    public void testVectorInit(){
        Vector zero = new Vector();
        assertEquals(0.0, zero.getX(), "Zero vector X");
        assertEquals(0.0, zero.getY(), "Zero vector Y");

        Vector v = new Vector(-4, 4);
        assertEquals(-4.0, v.getX());
        assertEquals(4, v.getY());
    }

    @Test
    public void testVectorAmplitude(){
        Vector zero = new Vector();
        assertEquals(0.0, zero.getAmplitude(), "Zero vector amplitude");

        assertTrue(abs(a.getAmplitude() - 5.0) < 1e-9, "Amplitude 5");
    }

    @Test
    public void testVectorSum(){
        Vector sum = a.vectorSum(b);
        assertTrue(abs(sum.getDistance(new Vector(4,6))) < 1e-9, "Addition");
        assertTrue(sum.getAmplitude() > 0);
    }

    @Test
    public void testVectorTo(){
        Vector to = a.vectorTo(b);
        assertTrue(abs(to.getDistance(new Vector(-2,-2))) < 1e-9, "To");
        assertTrue(to.getAmplitude() > 0);
    }

    @Test
    public void testVectorScalarMult(){
        Vector scaled = a.scalarMult(2);
        assertTrue(abs(scaled.getDistance(new Vector(6,8))) < 1e-9, "Mult");
        assertTrue(scaled.getAmplitude() > 0);
    }

    @Test
    public void testVectorNormalize(){
        Vector norm = a.normalize();
        assertTrue(abs(norm.getAmplitude()-1.0) < 1e-9, "Normalize amplitude 1");
        // signs are correct still
        assertTrue(a.getX() * norm.getX() > 0 && b.getY() * norm.getY() > 0, "Normalized signs");
        assertTrue(norm.getX() < norm.getY(), "Preserve vector direction");
    }

    @Test
    public void testVectorDot(){
        assertTrue(abs(a.dotProduct(b) - 11.0) < 1e-9, "Dot product");
        assertTrue(abs(b.dotProduct(c) + 5) < 1e-9, "Dot product");
    }

    @Test
    public void testVectorNormal(){
        Vector normal = a.getNormal();
        assertTrue(abs(normal.dotProduct(a)) < 1e-9, "Normal orthogonal");
        assertTrue(abs(normal.getAmplitude() - a.getAmplitude()) < 1e-9, "Normal amplitude same");
        assertTrue(normal.getAmplitude() > 0);
    }
    @Test
    public void testVectorFlip(){
        Vector flippedB = a.flip();
        assertTrue(abs(flippedB.vectorSum(a).getAmplitude()) < 1e-9, "Flipped vector same");
        assertTrue(flippedB.getAmplitude() > 0);
    }

    @Test
    public void testCircularOrbitSpeed(){
        moon.setCircularOrbitVelocity(earth);
        double lunarOrbitSpeed = moon.getCircularSpeed(earth.getMass(), moon.getPosition().getDistance(earth.getPosition()));
        double targetSpeed = 1022;
        assertTrue(targetSpeed * (1-errorMargin) < lunarOrbitSpeed
                        && lunarOrbitSpeed < targetSpeed * (1+errorMargin),
                "Lunar orbital speed, expected 1022, was " + lunarOrbitSpeed);
    }

    @Test
    public void testSatelliteOrbitSpeeds(){
        double targetSpeed;
        double satelliteHeight = 100000 + earth.getRadius();
        double minimalVel = earth.getOrbitalSpeed(satelliteHeight, 0);
        targetSpeed = 7818;
        assertTrue(targetSpeed * (1-errorMargin) < minimalVel && minimalVel < targetSpeed * (1+errorMargin), "Satellite minimum velocity: ");

        double circularVelocity = earth.getOrbitalSpeed(satelliteHeight, satelliteHeight - earth.getRadius());
        targetSpeed = 7848;
        assertTrue(targetSpeed * (1-errorMargin) < circularVelocity && circularVelocity < targetSpeed * (1+errorMargin), "Satellite circular velocity: ");
    }

    @Test
    public void testEscapeVelocity(){
        double satelliteHeight = 100000 + earth.getRadius();
        double escapeVel = earth.getEscapeSpeed(satelliteHeight);
        double targetSpeed = 11.186e3;
        assertTrue(targetSpeed * (1-errorMargin) < escapeVel && escapeVel < targetSpeed * (1+errorMargin), "Satellite escape velocity: ");

    }

    @Test
    public void testOrbitalMechanics(){
        double moonCurrDist = 100000 + moon.getRadius();
        double moonMinVel = moon.getOrbitalSpeed(moonCurrDist, 0);
        double moonCircVel = moon.getOrbitalSpeed(moonCurrDist, moonCurrDist - moon.getRadius());
        double moonEscVel = moon.getEscapeSpeed(moonCurrDist);
        assertTrue(moonMinVel < moonCircVel && moonCircVel < moonEscVel);
        double targetSpeed = 1625;
        assertTrue(targetSpeed * (1-errorMargin) < moonMinVel && moonMinVel < targetSpeed * (1+errorMargin), "Satellite lunar velocity: ");
        targetSpeed = 2311;
        assertTrue(targetSpeed * (1-errorMargin) < moonEscVel && moonEscVel < targetSpeed * (1+errorMargin), "Satellite lunar escape velocity: ");
    }
}
