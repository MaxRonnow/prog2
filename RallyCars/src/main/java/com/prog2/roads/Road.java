package com.prog2.roads;

import java.util.ArrayList;

public enum Road {

    ASPHALT("Asphalt"),
    DIRT("Dirt"),
    GRAVEL("Gravel"),
    SNOW("Snow");

    // implemented as a linked list
    private final String name;
    private int length;
    private final ArrayList<Turn> turns;
    private Road nextRoad;

    Road(String name){
        this.name = name;
        this.length = 100;
        this.turns = new ArrayList<>();
    }

    public void setNextRoad(Road road){
        this.nextRoad = road;
    }

    public Road getNextRoad(){
        return this.nextRoad;
    }

    public Road setLength(int length){
        this.length = length;
        return this;
    }

    public int getLength(){
        return length;
    }

    public boolean getTurnAt(final int distance){
        for (Turn t : turns){
            if (distance >= t.getAtRoadDistance() && distance < t.getAtRoadDistance() + t.getDistanceRequired()){
                return true;
            }
        }
        return false;
    }

    public String asciiPrint(int vehOnRoadDistance) {
        // road is length / 10 characters long, then the turns are added and finally the car position if its valid
        Character[] charList = new Character[this.length / 10];
        StringBuilder roadStr = new StringBuilder();
        roadStr.append("|");
        roadStr.append(this.getName());
        int currDist;
        for (int i=0; i<charList.length; i++){
            currDist = 10 * i;
            if (currDist <= vehOnRoadDistance && vehOnRoadDistance < currDist + 10 && vehOnRoadDistance >= 0) {
                roadStr.append(">");
            } else if (this.getTurnAt(currDist)){
                roadStr.append("~");
            } else {
                roadStr.append("-");
            }
        }
        return roadStr.toString();
    }

    public Road setTurn(final int distance){
        turns.add(new Turn(distance));
        return this;
    }

    public String getName(){
        return this.name;
    }
}