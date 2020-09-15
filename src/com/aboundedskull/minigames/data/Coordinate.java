package com.aboundedskull.minigames.data;

public class Coordinate extends Tile {

    private int z;

    public Coordinate(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }


    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "Coordinate " + "(" + super.getX() + "," + super.getY() + "," + getZ() + ")";
    }
}
