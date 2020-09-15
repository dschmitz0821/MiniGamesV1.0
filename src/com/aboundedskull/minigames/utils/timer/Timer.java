package com.aboundedskull.minigames.utils.timer;

public class Timer {

    private long startMS;

    public Timer() {
        reset();
    }

    public long elapsed(){
        return System.currentTimeMillis() - this.startMS;
    }

    public void reset(){
        this.startMS = System.currentTimeMillis();
    }
}
