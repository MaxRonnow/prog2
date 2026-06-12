package com.JavaGame;

import com.JavaGame.Planets.Earth;
import com.JavaGame.Planets.Moon;
import com.JavaGame.Planets.Sun;
import org.testng.annotations.Test;

import static java.lang.Math.pow;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Tests {
    public static double errorMargin = 0.02;
    @Test
    public void testCelestialObjectProperties(){
        CelestialObject sun = new Sun();
        CelestialObject earth = new Earth();
        CelestialObject moon = new Moon(earth);

        double sunDensity = 1408;
        double earthDensity = 5513;
        double moonDensity = 3344;

        double eDen = earth.getDensity();
        assertTrue("Measuring earth density, expected 5513, was " + eDen, earthDensity * (1-errorMargin) < eDen && eDen < earthDensity * (1+errorMargin));
        double mDen = moon.getDensity();
        assertTrue("Measuring moon density, expected 3344, was " + mDen, moonDensity * (1-errorMargin) < mDen && mDen < moonDensity * (1+errorMargin));
        double sDen = sun.getDensity();
        assertTrue("Measuring sun density, expected 1408, was " + sDen, sunDensity * (1-errorMargin) < sDen && sDen < sunDensity * (1+errorMargin));

        double earthSurfaceGravity = 9.806;
        double moonSurfaceGravity = 1.622;
        double sunSurfaceGravity = 274;

        double eG = earth.getSurfaceGravity();
        assertTrue("Measuring earth gravity, expected 9.806, was " + eG, earthSurfaceGravity * (1-errorMargin) < eG && eG < earthSurfaceGravity * (1+errorMargin));
        double mG = moon.getSurfaceGravity();
        assertTrue("Measuring moon gravity, expected 1.622, was " + mG, moonSurfaceGravity * (1-errorMargin) < mG && mG < moonSurfaceGravity * (1+errorMargin));
        double sG = sun.getSurfaceGravity();
        assertTrue("Measuring sun gravity, expected 274, was " + sG, sunSurfaceGravity * (1-errorMargin) < sG && sG < sunSurfaceGravity * (1+errorMargin));

        double moonEarthForce = 1.971 * pow(10, 20);
        double meF = earth.getGravityForce(moon);
        double emF = moon.getGravityForce(earth);
        assertTrue("Measuring earth-moon force, expected 1.97e20, was " + emF, moonEarthForce * (1-errorMargin) < emF && emF < moonEarthForce * (1+errorMargin));
        assertTrue("Measuring moon-earth force, expected 1.97e20, was " + meF, moonEarthForce * (1-errorMargin) < meF && meF < moonEarthForce * (1+errorMargin));

        double earthSunForce = 3.54e22;
        double esF = earth.getGravityForce(sun);
        assertTrue("Measuring sun-earth force, expected , was " + meF, earthSunForce * (1-errorMargin) < esF && esF < earthSunForce * (1+errorMargin));
    }

    @Test
    public void testVectorProperties(){
        Vector zero = new Vector();
        assertEquals("Zero vector amplitude", 0.0, zero.getAmplitude());

        Vector a = new Vector(3,4);
        assertTrue("Amplitude 5", Math.abs(a.getAmplitude() - 5.0) < 1e-9);

        Vector b = new Vector(1,2);
        Vector sum = a.add(b);
        assertTrue("Addition", sum.getDistance(new Vector(4,6)) < 1e-9);

        Vector to = a.to(b);
        assertTrue("To", to.getDistance(new Vector(-2,-2)) < 1e-9);

        Vector scaled = a.mult(2);
        assertTrue("Mult", scaled.getDistance(new Vector(6,8)) < 1e-9);

        Vector norm = a.normalize();
        assertTrue("Normalize amplitude 1", Math.abs(norm.getAmplitude()-1.0) < 1e-9);

        assertTrue("Dot product", Math.abs(a.dot(b) - 11.0) < 1e-9);

        Vector normal = a.getNormal();
        assertTrue("Normal orthogonal", Math.abs(normal.dot(a)) < 1e-9);
        assertTrue("Normal amplitude same", Math.abs(normal.getAmplitude() - a.getAmplitude()) < 1e-9);

        Vector flippedB = a.flip();
        assertTrue("Flipped vector same", Math.abs(flippedB.add(a).getAmplitude()) < 1e-9);

        Vector doubleVec = new Vector(3.141516, 4.2);
        assertEquals("(3,142; 4,200)", doubleVec.toString());
    }

    @Test
    public void testOrbitalMechanics(){
        CelestialObject earth = new Earth();
        CelestialObject moon = new Moon(earth);

        moon.setCircularOrbitVelocity();

        double lunarOrbitSpeed = moon.getVelocity().getAmplitude();
        double targetSpeed = 1022;
        assertTrue("Lunar orbital speed", targetSpeed * (1-errorMargin) < lunarOrbitSpeed && lunarOrbitSpeed < targetSpeed * (1+errorMargin));

        double satelliteHeight = 100000 + earth.getRadius();
        double minimalVel = earth.getOrbitalVelocity(satelliteHeight, 0);
        targetSpeed = 7818;
        assertTrue("Satellite minimum velocity: ", targetSpeed * (1-errorMargin) < minimalVel && minimalVel < targetSpeed * (1+errorMargin));

        double circularVelocity = earth.getOrbitalVelocity(satelliteHeight, satelliteHeight - earth.getRadius());
        targetSpeed = 7848;
        assertTrue("Satellite circular velocity: ", targetSpeed * (1-errorMargin) < circularVelocity && circularVelocity < targetSpeed * (1+errorMargin));

        double escapeVel = earth.getEscapeVelocity(satelliteHeight);
        targetSpeed = 11.186e3;
        assertTrue("Satellite escape velocity: ", targetSpeed * (1-errorMargin) < escapeVel && escapeVel < targetSpeed * (1+errorMargin));

        double moonCurrDist = 100000 + moon.getRadius();
        double moonMinVel = moon.getOrbitalVelocity(moonCurrDist, 0);
        double moonCircVel = moon.getOrbitalVelocity(moonCurrDist, moonCurrDist - moon.getRadius());
        double moonEscVel = moon.getEscapeVelocity(moonCurrDist);

        targetSpeed = 1625;
        assertTrue("Satellite lunar velocity: ", targetSpeed * (1-errorMargin) < moonMinVel && moonCircVel < targetSpeed * (1+errorMargin) && moonMinVel < moonCircVel);
        targetSpeed = 2311;
        assertTrue("Satellite lunar escape velocity: ", targetSpeed * (1-errorMargin) < moonEscVel && moonEscVel < targetSpeed * (1+errorMargin));


    }
}
