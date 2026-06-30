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

        CelestialObjectInterface sun1 = systems.getSun();
        CelestialObjectInterface sun2 = systems.getSun();
        sun2.setPosition(systems.getVector(219098450e3, -49098450e3));
        sun2.setAffectedByGravity(true);
        sun1.setAffectedByGravity(true);
        sun2.setCircularOrbitVelocity(sun1);

        bodies.add(sun1);
        bodies.add(sun2);

        CelestialObjectInterface sun3 = systems.getSun();
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

    // Helper methods - update these with student implementations once they're complete
    private CelestialObjectInterface getEarth(){
        return new Earth();
    }

    private CelestialObjectInterface getMoon(){
        return new Moon();
    }

    private CelestialObjectInterface getSun(){
        return new Sun();
    }

    private CelestialObjectInterface getMercury(){
        return new Mercury();
    }

    private CelestialObjectInterface getVenus(){
        return new Venus();
    }

    private CelestialObjectInterface getMars(){
        return new Mars();
    }

    private VectorInterface getVector(double x, double y){
        return new Vector(x, y);
    }
}
