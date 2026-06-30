package com.JavaGame;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Math.pow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestImplementedOrbitalMechanics {
    public static double errorMargin = 0.05;

    private CelestialObjectInterface sun;
    private CelestialObjectInterface earth;
    private CelestialObjectInterface moon;

    private ImplementedVector a;
    private ImplementedVector b;
    private ImplementedVector c;

    @BeforeEach
    public void createPlanets(){
        sun = new ImplementedCelestialObject(1.988e30, 695700e3, new ImplementedVector(), "Sun");
        earth = new ImplementedCelestialObject(5.972e24, 6371e3, new ImplementedVector(149098450e3, 0), "Earth");
        moon = new ImplementedCelestialObject(7.346e22, 1737e3, new ImplementedVector(149098450e3 + 384399e3, 0), "Moon");

        a = new ImplementedVector(3,4);
        b = new ImplementedVector(1,2);
        c = new ImplementedVector(-1,-2);
    }

    @AfterEach
    public void resetSpeeds(){
        // Not needed maybe
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
        // TODO: look into this
        double moonEarthForce = 1.971 * pow(10, 20);
        double meF = earth.getGravityForce(moon);
        double emF = moon.getGravityForce(earth);
        // assertEquals(0.0, emF);
        assertTrue(moonEarthForce * (1-errorMargin) < emF && emF < moonEarthForce * (1+errorMargin), "Measuring earth-moon force, expected 1.97e20, was " + emF);
        assertTrue(moonEarthForce * (1-errorMargin) < meF && meF < moonEarthForce * (1+errorMargin), "Measuring moon-earth force, expected 1.97e20, was " + meF);

        double earthSunForce = 3.54e22;
        double esF = earth.getGravityForce(sun);
        assertTrue(earthSunForce * (1-errorMargin) < esF && esF < earthSunForce * (1+errorMargin), "Measuring earth-sun force, expected 3.54e22, was " + esF);
    }

    @Test
    public void testVectorInit(){
        ImplementedVector zero = new ImplementedVector();
        assertEquals(0.0, zero.getX(), "Zero vector X");
        assertEquals(0.0, zero.getY(), "Zero vector Y");
    }

    @Test
    public void testVectorAmplitude(){
        ImplementedVector zero = new ImplementedVector();
        assertEquals(0.0, zero.getAmplitude(), "Zero vector amplitude");

        assertTrue(Math.abs(a.getAmplitude() - 5.0) < 1e-9, "Amplitude 5");
    }

    @Test
    public void testVectorSum(){
        ImplementedVector sum = a.vectorSum(b);
        assertTrue(sum.getDistance(new ImplementedVector(4,6)) < 1e-9, "Addition");
    }

    @Test
    public void testVectorTo(){
        ImplementedVector to = a.vectorTo(b);
        assertTrue(to.getDistance(new ImplementedVector(-2,-2)) < 1e-9, "To");
    }

    @Test
    public void testVectorScalarMult(){
        ImplementedVector scaled = a.scalarMult(2);
        assertTrue(scaled.getDistance(new ImplementedVector(6,8)) < 1e-9, "Mult");
    }

    @Test
    public void testVectorNormalize(){
        ImplementedVector norm = a.normalize();
        assertTrue(Math.abs(norm.getAmplitude()-1.0) < 1e-9, "Normalize amplitude 1");
        // signs are correct still
        assertTrue(a.getX() * norm.getX() > 0 && b.getY() * norm.getY() > 0, "Normalized signs");
        assertTrue(norm.getX() < norm.getY(), "Preserve vector direction");
    }

    @Test
    public void testVectorDot(){
        assertTrue(Math.abs(a.dotProduct(b) - 11.0) < 1e-9, "Dot product");
        assertEquals(5.0, Math.abs(b.dotProduct(c)), "Dot product");
    }

    @Test
    public void testVectorNormal(){
        ImplementedVector normal = a.getNormal();
        assertTrue(Math.abs(normal.dotProduct(a)) < 1e-9, "Normal orthogonal");
        assertTrue(Math.abs(normal.getAmplitude() - a.getAmplitude()) < 1e-9, "Normal amplitude same");
    }
    @Test
    public void testVectorFlip(){
        ImplementedVector flippedB = a.flip();
        assertTrue(Math.abs(flippedB.vectorSum(a).getAmplitude()) < 1e-9, "Flipped vector same");
    }

    @Test
    public void testCircularOrbitSpeed(){
        IO.println("earth object " + earth);
        moon.setCircularOrbitVelocity(earth);
        double lunarOrbitSpeed = moon.getVelocity().getAmplitude();
        double targetSpeed = 1022;
        // assertEquals(1022, lunarOrbitSpeed);
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
