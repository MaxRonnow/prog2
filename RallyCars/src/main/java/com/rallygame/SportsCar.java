package com.rallygame;

import java.awt.*;

public class SportsCar extends Vehicle {
    // breaks down every 10 turns in snow and takes 2 turns to repair

    private int turnsInSnow;
    private int breakDownTurns;

    public SportsCar(){
        super("SportsCar");
        this.turnsInSnow = 0;
        this.breakDownTurns = 0;
        setColor(new Color(255, 0,0));
    }

    @Override
    public void forward(){

        if (this.getCurrentRoad() != null && this.getCurrentRoad().getName().equals("Snow")){
            this.turnsInSnow++;
            if (this.turnsInSnow >= 10) {
                if (this.breakDownTurns >= 2){
                    System.out.println(this + " has repaired snow damages");
                    this.turnsInSnow = 0;
                    this.breakDownTurns = 0;
                } else {
                    this.breakDownTurns++;
                    System.out.println(this + " has broken down in snow!");
                    return;
                }
            }
        }
        super.forward();
    }

    @Override
    public int getSurfaceSpeed(Road road) {
        return switch (road.getName()) {
            case "Asphalt" -> 120;
            case "Snow" -> 30;
            case "Gravel" -> 60;
            default -> 50;
        };
    }

    @Override
    public int getFuelConsumption() {
        return 2;
    }

    @Override
    public int getTankSize() {
        return 50;
    }

    @Override
    public int getRefuelingRate() {
        return 10;
    }

    @Override
    public int getAccelerationRate() {
        return 10;
    }

    @Override
    public int getTurnSpeed(Road road) {
        return (int) (getSurfaceSpeed(road) * 0.6);
    }
}
