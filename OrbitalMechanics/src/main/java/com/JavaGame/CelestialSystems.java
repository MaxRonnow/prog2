package com.JavaGame;

import java.util.ArrayList;
import java.util.List;

/**
 * This file creates systems that could be simulated by changing the Main file at line 157.
 * Create your own simulated systems by studying the provided ones below and creating them in a similar manner.
 * Some values for distances in this file are made simply by trial and error.
 *
 * @author Jesper Andersson, Kursutvecklare 2026
 */

public class CelestialSystems {

    public static List<CelestialObject> EARTH_MOON_SYSTEM() {
        List<CelestialObject> bodies = new ArrayList<>();

        CelestialObject earth = Planets.EARTH.getNew(new Vector());
        CelestialObject moon = Planets.MOON.getNew(new Vector(Planets.MOON.getSemiMajorAxis(), 0));
        moon.setCircularOrbitVelocity(earth);
        bodies.add(earth);
        bodies.add(moon);

        return bodies;
    }

    public static List<CelestialObject> INNER_SOLAR_SYSTEM() {
        CelestialSystems systems = new CelestialSystems();
        List<CelestialObject> bodies = new ArrayList<>();

        CelestialObject sun = systems.getSun();
        CelestialObject earth = systems.getEarth();
        earth.setCircularOrbitVelocity(sun);
        bodies.add(sun);
        bodies.add(earth);
        // add other planets here :)
        CelestialObject merc = systems.getMercury();
        merc.setCircularOrbitVelocity(sun);
        bodies.add(merc);

        CelestialObject ven = systems.getVenus();
        ven.setCircularOrbitVelocity(sun);
        bodies.add(ven);

        CelestialObject mars = systems.getMars();
        mars.setCircularOrbitVelocity(sun);
        bodies.add(mars);

        return bodies;
    }

    public static List<CelestialObject> TRIPLE_SUN_SYSTEM(){
        CelestialSystems systems = new CelestialSystems();
        List<CelestialObject> bodies = new ArrayList<>();

        CelestialObject sun1 = systems.getSun();
        CelestialObject sun2 = systems.getSun();
        sun2.setPosition(systems.getVector(219098450e3, -49098450e3));
        sun2.setAffectedByGravity(true);
        sun1.setAffectedByGravity(true);
        sun2.setCircularOrbitVelocity(sun1);

        bodies.add(sun1);
        bodies.add(sun2);

        CelestialObject sun3 = systems.getSun();
        sun3.setPosition(systems.getVector(-179098450e3, 0));
        sun3.setAffectedByGravity(true);
        sun3.setCircularOrbitVelocity(sun1);
        bodies.add(sun3);

        CelestialObject merc = systems.getMercury();
        merc.setCircularOrbitVelocity(sun1);
        bodies.add(merc);

        CelestialObject mars = systems.getMars();
        mars.setCircularOrbitVelocity(sun2);
        bodies.add(mars);

        return bodies;
    }

    // Helper methods
    private CelestialObject getEarth(){
        return Planets.EARTH.getNew();
    }

    private CelestialObject getMoon(){
        return Planets.MOON.getNew();
    }

    private CelestialObject getSun(){
        return Planets.SUN.getNew();
    }

    private CelestialObject getMercury(){
        return Planets.MERCURY.getNew();
    }

    private CelestialObject getVenus(){
        return Planets.VENUS.getNew();
    }

    private CelestialObject getMars(){
        return Planets.MARS.getNew();
    }

    private Vector getVector(double x, double y){
        return new Vector(x, y);
    }
}
