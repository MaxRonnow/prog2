package com.JavaGame;

import java.util.ArrayList;
import java.util.List;

public class CelestialSystems {

    public static List<CelestialObjectInterface> EARTH_MOON_SYSTEM() {
        CelestialSystems systems = new CelestialSystems();
        List<CelestialObjectInterface> bodies = new ArrayList<>();

        CelestialObjectInterface earth = systems.getEarth();
        CelestialObjectInterface moon = systems.getMoon();
        moon.setCircularOrbitVelocity(earth);
        bodies.add(earth);
        bodies.add(moon);

        return bodies;
    }

    public static List<CelestialObjectInterface> INNER_SOLAR_SYSTEM() {
        CelestialSystems systems = new CelestialSystems();
        List<CelestialObjectInterface> bodies = new ArrayList<>();

        CelestialObjectInterface sun = systems.getSun();
        CelestialObjectInterface earth = systems.getEarth();
        earth.setCircularOrbitVelocity(sun);
        bodies.add(sun);
        bodies.add(earth);
        // add other planets here :)
        CelestialObjectInterface merc = systems.getMercury();
        merc.setCircularOrbitVelocity(sun);
        bodies.add(merc);

        CelestialObjectInterface ven = systems.getVenus();
        ven.setCircularOrbitVelocity(sun);
        bodies.add(ven);

        CelestialObjectInterface mars = systems.getMars();
        mars.setCircularOrbitVelocity(sun);
        bodies.add(mars);

        return bodies;
    }

    public static List<CelestialObjectInterface> TRIPLE_SUN_SYSTEM(){
        CelestialSystems systems = new CelestialSystems();
        List<CelestialObjectInterface> bodies = new ArrayList<>();

        // TODO: change to CelestialObject here
        ImplementedCelestialObject sun1 = (ImplementedCelestialObject) systems.getSun();
        ImplementedCelestialObject sun2 = (ImplementedCelestialObject) systems.getSun();
        sun2.setPosition(systems.getVector(219098450e3, -49098450e3));
        sun2.setAffectedByGravity(true);
        sun1.setAffectedByGravity(true);
        sun2.setCircularOrbitVelocity(sun1);

        bodies.add(sun1);
        bodies.add(sun2);

        ImplementedCelestialObject sun3 = (ImplementedCelestialObject) systems.getSun();
        sun3.setPosition(systems.getVector(-179098450e3, 0));
        sun3.setAffectedByGravity(true);
        sun3.setCircularOrbitVelocity(sun1);
        bodies.add(sun3);

        CelestialObjectInterface merc = systems.getMercury();
        merc.setCircularOrbitVelocity(sun1);
        bodies.add(merc);

        CelestialObjectInterface mars = systems.getMars();
        mars.setCircularOrbitVelocity(sun2);
        bodies.add(mars);

        return bodies;
    }

    // TODO change these once testing is complete
    private CelestialObjectInterface getEarth(){
        return new ImplementedEarth();
    }

    private CelestialObjectInterface getMoon(){
        return new ImplementedMoon();
    }

    private CelestialObjectInterface getSun(){
        return new ImplementedSun();
    }

    private CelestialObjectInterface getMercury(){
        return new ImplementedMercury();
    }

    private CelestialObjectInterface getVenus(){
        return new ImplementedVenus();
    }

    private CelestialObjectInterface getMars(){
        return new ImplementedMars();
    }

    private VectorInterface getVector(double x, double y){
        return new ImplementedVector(x, y);
    }
}
