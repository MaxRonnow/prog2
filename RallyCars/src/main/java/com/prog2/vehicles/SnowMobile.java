package com.prog2.vehicles;

import com.prog2.roads.Road;

import java.awt.*;
import java.util.Objects;

public class SnowMobile extends Vehicle {
    public SnowMobile() {
        super("SnowMobile");
        setColor(new Color(250, 250, 250));
    }
    // TODO: Can cut through the track in snow,
    //  so only needs to travel half distance of snow and can skip the turns (ignore any speed penalty)

    @Override
    public void forward(){
        super.forward();
        if (this.getCurrentRoad() != null) {
            if (this.getCurrentRoad().getLength() <= this.getRoadDistanceTravelled() && Objects.equals(this.getCurrentRoad().getName(), "Snow")) {
                this.nextRoad();
                System.out.println(this + "has cut the track in the snow!");
                if (this.getCurrentRoad() == null) {
                    // We are finnished I guess
                    System.out.println("Finnished!");
                }
            }
        }
    }

    @Override
    public int getTurnSpeed(Road road) {
        if (Objects.equals(road.getName(), "Snow")){
            return getSurfaceSpeed(road);
        } else {
            return 10;
        }
    }

    @Override
    public int getSurfaceSpeed(Road road) {
        return switch (road.getName()) {
            case "Asphalt" -> 20;
            case "Snow" -> 90;
            case "Dirt" -> 40;
            case "Gravel" -> 30;
            default -> 10;
        };
    }

    @Override
    public int getFuelConsumption() {
        return 2;
    }

    @Override
    public int getTankSize() {
        return 20;
    }

    @Override
    public int getRefuelingRate() {
        return 10;
    }

    @Override
    public int getAccelerationRate() {
        return 20;
    }
}
