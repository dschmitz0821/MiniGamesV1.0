package com.aboundedskull.minigames.minigames;

import com.aboundedskull.minigames.data.locatable.Tile;
import com.aboundedskull.minigames.data.minigame.MinigameData;

public class TheWalls implements Minigame {

    private final Tile centerTile;
    private boolean started;

    public TheWalls() {
        this.centerTile = new Tile(0,0);
    }

    @Override
    public Tile getCenterTile() {
        return centerTile;
    }

    @Override
    public String getName() {
        return "The Walls";
    }

    @Override
    public String getCommandName() {
        return "TW";
    }

    @Override
    public boolean validateArguments(MinigameData data) {
        return true;
    }

    @Override
    public void startMinigame(MinigameData data) {
        started = true;
    }

    @Override
    public void endMinigame() {
        started = false;
    }

    @Override
    public boolean isStarted() {
        return started;
    }
}
