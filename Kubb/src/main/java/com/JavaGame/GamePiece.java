package com.JavaGame;

public abstract class GamePiece {
    boolean fallen;
    double x;
    double y;

    public GamePiece(double x, double  y) {
        this.fallen = false;
        this.x = x;
        this.y = y;
    }

    public boolean isFallen() {
        return fallen;
    }

    public void fall() {
        this.fallen = true;
    }

    public String statusToString() {
        if(fallen) {
            return "x";
        }
        else {
            return "■";
        }
    }

}
