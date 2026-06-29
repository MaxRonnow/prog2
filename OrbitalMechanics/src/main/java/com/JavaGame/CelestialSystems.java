package com.JavaGame;

import com.JavaGame.Planets.*;

import java.util.ArrayList;
import java.util.List;

public class CelestialSystems {

    public static List<CelestialObject> EARTH_MOON_SYSTEM() {
        List<CelestialObject> bodies = new ArrayList<>();

        Earth earth = new Earth();
        Moon moon = new Moon(earth);
        moon.setCircularOrbitVelocity();
        bodies.add(earth);
        bodies.add(moon);

        return bodies;
    }

    public static List<CelestialObject> INNER_SOLAR_SYSTEM() {
        List<CelestialObject> bodies = new ArrayList<>();

        Sun sun = new Sun();
        Earth earth = new Earth(sun);
        earth.setCircularOrbitVelocity();
        bodies.add(sun);
        bodies.add(earth);
        // add other planets here :)
        Mercury merc = new Mercury(sun);
        merc.setCircularOrbitVelocity();
        bodies.add(merc);

        Venus ven = new Venus(sun);
        ven.setCircularOrbitVelocity();
        bodies.add(ven);

        Mars mars = new Mars(sun);
        mars.setCircularOrbitVelocity();
        bodies.add(mars);

        return bodies;
    }

    public static List<CelestialObject> TRIPLE_SUN_SYSTEM(){
        List<CelestialObject> bodies = new ArrayList<>();

        Sun sun1 = new Sun();
        Sun sun2 = new Sun();
        sun2.setParent(sun1);
        sun2.setPosition(new Vector(219098450e3, -49098450e3));
        sun2.setAffectedByGravity(true);
        sun1.setAffectedByGravity(true);
        sun2.setCircularOrbitVelocity();
        bodies.add(sun1);
        bodies.add(sun2);

        Sun sun3 = new Sun();
        sun3.setParent(sun1);
        sun3.setPosition(new Vector(-179098450e3, 0));
        sun3.setAffectedByGravity(true);
        sun3.setCircularOrbitVelocity();
        bodies.add(sun3);

        Mercury merc = new Mercury(sun1);
        // merc.setParent(sun1);
        merc.setCircularOrbitVelocity();
        bodies.add(merc);

        Mars mars = new Mars(sun2);
        mars.setCircularOrbitVelocity();
        bodies.add(mars);

        return bodies;
    }
}
