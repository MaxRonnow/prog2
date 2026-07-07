package com.rallygame;

import java.util.ArrayList;
import java.util.List;

public class Race {

    private int timestep;
    private final Road startRoad;
    private final List<Vehicle> vehicles;

    public Race() {
        this.timestep = 0;
        this.startRoad = Road.DIRT.setLength(100).setTurn(10).setTurn(60);
        this.startRoad.setNextRoad(Road.ASPHALT.setLength(120).setTurn(60));
        this.startRoad.getNextRoad().setNextRoad(Road.GRAVEL.setLength(80).setTurn(30));
        this.startRoad.getNextRoad().getNextRoad().setNextRoad(Road.SNOW.setLength(140).setTurn(10).setTurn(50));
        this.vehicles = new ArrayList<>();

        vehicles.add(new RallyCar());
        vehicles.add(new Buggy());
        vehicles.add(new SportsCar());
        vehicles.add(new SnowMobile());
    }

    public Race(Road startRoad, List<Vehicle> vehicles) {
        this.timestep = 0;
        this.startRoad = startRoad;
        this.vehicles = vehicles;
    }

    public List<Vehicle> getVehicles(){
        return this.vehicles;
    }

    public void start(){
        // sets the starting road as the current road for all vehicles
        for (Vehicle veh : this.vehicles){
            veh.setCurrentRoad(this.startRoad);
        }
    }

    private boolean forward(int timestep){
        // returns true if one vehicle is finished
        boolean atleastOneCarRunning = false;
        for (Vehicle vehicle : vehicles) {
            vehicle.forward();
            if (vehicle.isFinnished()){
                if (vehicle.getDoneInTurns() > timestep + 1){
                    vehicle.setDoneInTurns(timestep + 1);
                }
            } else {
                atleastOneCarRunning = true;
            }
        }
        return atleastOneCarRunning;
    }

    private void run(){
        assert startRoad != null;
        assert vehicles != null;
        start();
        while (forward(timestep)){
            System.out.println("Timestep " + timestep + " completed!");
            timestep++;
            if (timestep % 10 == 0){
                for (Vehicle veh : vehicles){
                    this.printCourse(veh);
                    System.out.println(veh.getStats());
                    System.out.println();
                }
            }
        }
        System.out.println("Done in " + timestep + " timesteps!");
        for (Vehicle veh : vehicles){
            System.out.println(veh + " finnished the race in " + veh.getDoneInTurns() + " turns");
        }
    }

    public void update(){
        forward(timestep);
        timestep++;
    }

    private void printCourse(Vehicle veh) {
        Road currentPrintRoad = this.startRoad;
        int vehOnRoadDistance;
        StringBuilder courseString = new StringBuilder();
        System.out.println("=========== " + veh + " ===========");
        // courseString.append(veh);
        while (currentPrintRoad != null){
            if (veh.getCurrentRoad() != null && veh.getCurrentRoad().equals(currentPrintRoad)){
                vehOnRoadDistance = veh.getRoadDistanceTravelled();
            } else {
                // we are not on this road, set to a negative value so that we dont get printed
                vehOnRoadDistance = -999;
            }
            courseString.append(currentPrintRoad.asciiPrint(vehOnRoadDistance));
            currentPrintRoad = currentPrintRoad.getNextRoad();
        }

        if (veh.isFinnished()){
            courseString.append("F!");
        }

        System.out.println(courseString);
    }

    public Road getStartRoad(){
        return this.startRoad;
    }
}
