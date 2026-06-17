package com.JavaGame;

public class King extends GamePiece{
    public King(double x, double y) {
        super(x, y);
    }

    @Override
    public String statusToString() {
        if(fallen) {
            return "x";
        }
        else {
            return "╬";
        }
    }
}
