package com.aboundedskull.minigames.data;

public class Coordinate extends Tile {

    private double z;

    public Coordinate(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }


    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
