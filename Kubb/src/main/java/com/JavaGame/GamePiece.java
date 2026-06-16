package com.JavaGame;

public abstract class GamePiece {
    boolean fallen;

    public GamePiece() {
        this.fallen = false;
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
