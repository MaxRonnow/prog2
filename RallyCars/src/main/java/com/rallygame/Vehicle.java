package com.rallygame;

import java.awt.*;

public abstract class Vehicle {
    private int totalDistanceTravelled;// km
    private int roadDistanceTravelled;
    private Road currentRoad;
    private int fuelLeft;
    private boolean isRefueling;
    private int currentSpeed;
    private final String name;
    private int doneInTurns;
    private Color color;

    public Vehicle(String name){
        this.totalDistanceTravelled = 0;
        this.isRefueling = false;
        this.fuelLeft = this.getTankSize();
        this.name = name;
        this.doneInTurns = 9999999;
        this.color = new Color(0, 0, 0);
    }

    public Color getColor(){ return this.color; }
    public void setColor(Color color){ this.color = color; }
    public abstract int getTurnSpeed(Road road);
    public abstract int getSurfaceSpeed(Road road);
    public abstract int getFuelConsumption();
    public abstract int getTankSize();
    public abstract int getRefuelingRate();
    public abstract int getAccelerationRate();
    public int getRoadDistanceTravelled(){
        return this.roadDistanceTravelled;
    }

    public void nextRoad(){
        this.roadDistanceTravelled = 0;
        this.currentRoad = this.currentRoad.getNextRoad();
    }

    public void setCurrentRoad(Road road){
        this.currentRoad = road;
    }

    private void addDistance(final int distance){
        this.totalDistanceTravelled += distance;
        this.roadDistanceTravelled += distance;
    }

    public boolean isFinnished(){
        // we are done if there is no road to go to
        return this.currentRoad == null;
    }

    public void setDoneInTurns(int turns){
        this.doneInTurns = turns;
    }

    public int getDoneInTurns(){
        return this.doneInTurns;
    }

    public void forward(){
        if (this.getCurrentRoad() == null){
            // no road to go forward on, exit
            return;
        }

        // 1. Check tank
        if (this.fuelLeft <= 0) {
            this.isRefueling = true;
            this.fuelLeft = 0;
            System.out.println(this + " ran out of fuel!");
        }
        if (this.isRefueling){
            this.fuelLeft += this.getRefuelingRate();
            if (this.fuelLeft >= this.getTankSize()){
                this.fuelLeft = this.getTankSize();
                this.isRefueling = false;
                System.out.println(this + " is done refueling");
            }
            // cannot do anything else on timestep when refueling
            this.currentSpeed = 0;
            return;
        }
        // 2. Check if we should be in the next road
        if (this.currentRoad.getLength() <= this.roadDistanceTravelled){
            this.nextRoad();
            if (this.currentRoad == null){
                // We are finnished I guess
                System.out.println(this.name + " finnished the race!");
                return;
            }
            System.out.println(this + " is going to next road! New surface: " + this.getCurrentRoad().getName());
        }
        // 3. Accelerate up to speed or snap speed down to lower speed
        if (currentSpeed < this.getSurfaceSpeed(this.currentRoad)){
            this.currentSpeed += this.getAccelerationRate();
            // System.out.println(this + " is accelerating");
            if (this.currentSpeed > this.getSurfaceSpeed(this.currentRoad)){
                // System.out.println(this + " reached top speed");
                this.currentSpeed = this.getSurfaceSpeed(this.currentRoad);
            }
        }

        // 4. Check if in a turn
        if (this.currentRoad.getTurnAt(this.getRoadDistanceTravelled()) != null){
            // System.out.println(this + " is in a turn");
            this.currentSpeed = this.getTurnSpeed(this.currentRoad);
        }

        // 5. Move one second timestep
        this.addDistance((int) (this.currentSpeed / 3.6));
        // 6. remove fuel
        this.fuelLeft -= this.getFuelConsumption();

        // (7. Vehicle implementations might do other stuff here or before 1.)
    }

    public Road getCurrentRoad(){
        return this.currentRoad;
    }

    public boolean getIsRefueling(){
        return this.isRefueling;
    }

    public String toString(){
        return this.name;
    }

    public String getStats(){
        return this.toString() + " total distance: " + this.totalDistanceTravelled + "m, current speed: " + this.currentSpeed + "km/h, fuel left: " + this.fuelLeft;
    }
}
