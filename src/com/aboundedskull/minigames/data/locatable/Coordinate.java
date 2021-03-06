package com.aboundedskull.minigames.data.locatable;

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

    public boolean setZ(String z){
        try {
            this.z = Integer.parseInt(z);
            return true;
        } catch (NumberFormatException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String toString() {
        return "Coordinate " + "(" + super.getX() + "," + super.getY() + "," + getZ() + ")";
    }
}
