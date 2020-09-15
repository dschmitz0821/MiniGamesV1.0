package com.aboundedskull.minigames.data.locatable;

public class Tile {

    private int x;
    private int y;

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean setX(String x){
        try {
            this.x = Integer.parseInt(x);
            return true;
        } catch (NumberFormatException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean setY(String y){
        try {
            this.y = Integer.parseInt(y);
            return true;
        } catch (NumberFormatException e){
            e.printStackTrace();
            return false;
        }
    }
}
