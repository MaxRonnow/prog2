package com.JavaGame;

import java.awt.*;

public class Earth extends CelestialObject {
    // TODO:
    //  - Lookup the mass, radius of the earth and the semi-major axis distance around the sun
    //  - Three significant digits is enough
    //  - In the Earth constructor, create a CelestialObject (using the super() method) with the above information,
    //  and the name "Earth"


    public Earth() {
        // TODO: fill in the information in the super() call.
        // mass, radius, position, name
        super(0, 0, null, null);
    }

    @Override
    public Color getColor() {
        return new Color(109, 154, 255);
    }
}
