package com.rallygame;

import java.awt.*;

public class RallyCar extends Vehicle {
    public RallyCar() {
        super("RallyCar");
        setColor(new Color(0, 0, 255));
    }

    @Override
    public int getTurnSpeed(Road road) {
        // 90% of normal speed if gravel, dirt or snow, else 50%
        return switch (road.getName()) {
            case "Dirt", "Gravel", "Snow" -> (int) (getSurfaceSpeed(road) * 0.9);
            default -> (int) (getSurfaceSpeed(road) * 0.5);
        };
    }

    @Override
    public int getSurfaceSpeed(Road road) {
        return switch (road.getName()) {
            case "Asphalt", "Dirt", "Gravel" -> 60;
            case "Snow" -> 50;
            default -> 40;
        };
    }

    @Override
    public int getFuelConsumption() {
        return 3;
    }

    @Override
    public int getTankSize() {
        return 70;
    }

    @Override
    public int getRefuelingRate() {
        return 8;
    }

    @Override
    public int getAccelerationRate() {
        return 12;
    }
}
