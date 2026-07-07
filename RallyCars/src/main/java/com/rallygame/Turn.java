package com.rallygame;

public class Turn {
    private final int atRoadDistance;
    private int distanceRequired;

    public Turn(final int distance){
        this.atRoadDistance = distance;
        this.distanceRequired = 30;
    }

    public Turn(final int distance, int distanceRequired){
        this.atRoadDistance = distance;
        this.distanceRequired = distanceRequired;
    }

    public void setDistanceRequired(int distanceRequired) {
        this.distanceRequired = distanceRequired;
    }

    public int getDistanceRequired() {
        return distanceRequired;
    }

    public int getAtRoadDistance(){
        return this.atRoadDistance;
    }
}
