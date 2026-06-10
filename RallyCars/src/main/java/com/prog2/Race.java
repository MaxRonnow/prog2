package com.prog2;

import com.prog2.roads.*;
import com.prog2.vehicles.*;

import java.util.ArrayList;
import java.util.List;

public class Race {

    private int timestep;
    private final List<Road> roads;
    private List<Vehicle> vehicles;

    public Race() {
        this.timestep = 0;
        this.roads = new ArrayList<>();
        this.vehicles = new ArrayList<>();

        roads.add(new DirtRoad());
        roads.add(new AsphaltRoad());
        roads.add(new SandRoad());
        roads.add(new SnowRoad());

        vehicles.add(new DirtCar());
        vehicles.add(new SandCar());
        vehicles.add(new AsphaltCar());
        vehicles.add(new SnowCar());
    }

    public Race(List<Road> roads, List<Vehicle> vehicles) {
        this.timestep = 0;
        this.roads = roads;
        this.vehicles = vehicles;
    }

    public void forward(){
        timestep++;
        for (Vehicle vehicle : vehicles) {
            vehicle.forward();
        }
    }




}
