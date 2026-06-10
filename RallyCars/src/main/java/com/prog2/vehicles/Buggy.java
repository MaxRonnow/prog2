package com.prog2.vehicles;

import com.prog2.roads.Road;

import java.util.List;

public class Buggy extends Vehicle {
    // takes an additional 3 turns to start after refueling

    private boolean refueledLastTurn;
    private boolean isPostRefueling;
    private int postRefuelingTurns;

    public Buggy(){
        super("Buggy");
        this.refueledLastTurn = false;
        this.isPostRefueling = false;
        this.postRefuelingTurns = 0;
    }

    @Override
    public void forward() {
        if ((!this.getIsRefueling() && this.refueledLastTurn) || this.isPostRefueling){
            // post refueling
            this.isPostRefueling = true;
            postRefuelingTurns++;
            this.refueledLastTurn = false;
            System.out.println(this + "has trouble starting up!");
            if (this.postRefuelingTurns >= 3){
                this.postRefuelingTurns = 0;
                this.isPostRefueling = false;
                System.out.println(this + "has now successfully started the engine!");
            }
        } else {
            this.refueledLastTurn = this.getIsRefueling();
            super.forward();
        }
    }

    @Override
    public int getTurnSpeed(Road road) {
        return getSurfaceSpeed(road);
    }

    @Override
    public int getSurfaceSpeed(Road road) {
        return 50;
    }

    @Override
    public int getFuelConsumption() {
        return 1;
    }

    @Override
    public int getTankSize() {
        return 30;
    }

    @Override
    public int getRefuelingRate() {
        return 10;
    }

    @Override
    public int getAccelerationRate() {
        return 6;
    }
}
