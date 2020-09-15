package com.aboundedskull.minigames.minigames;

import com.aboundedskull.minigames.data.Coordinate;
import com.aboundedskull.minigames.data.Tile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HungerGames implements Minigame {

    private final Tile centerTile;
    private boolean started;
    private final List<Coordinate> coordinateList = new ArrayList<Coordinate>(){
        {
            add(new Coordinate(-63.5, 74, -2.5));
            add(new Coordinate(-62.5, 74, -7.5));
            add(new Coordinate(-58.5, 74, -11.5));
            add(new Coordinate(-53.5, 74, -12.5));
            add(new Coordinate(-48.5, 74, -11.5));
            add(new Coordinate(-22.5, 74, -7.5));
            add(new Coordinate(-43.5, 74, -2.5));
            add(new Coordinate(-44.5, 74, 2.5));
            add(new Coordinate(-48.5, 74, 6.5));
            add(new Coordinate(-53.5, 74, 7.5));
            add(new Coordinate(-58.5, 74, 6.5));
            add(new Coordinate(-62.5, 74, 2.5));
        }
    };

    public HungerGames() {
        this.centerTile = new Tile(0,0);
    }

    @Override
    public Tile getCenterTile() {
        return centerTile;
    }

    @Override
    public String getName() {
        return "Hunger Games";
    }

    @Override
    public String getCommandName() {
        return "HG";
    }

    @Override
    public void startMinigame() {
        // Hunger games start code
        started = true;

        // Set the initial world bounds
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "worldborder center " + getCenterTile().getX() + " " + getCenterTile().getY());
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "worldborder set 500 0");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "spreadplayers 0 0 50 200 false @a");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "worldborder set 50 900");

        // Teleport all the players.
        int max = coordinateList.size();
        int playerCount = 0;

        Collections.shuffle(coordinateList);
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (playerCount < max){
                Coordinate currentCoordinate = coordinateList.get(playerCount++);
                player.teleport(new Location(player.getWorld(), currentCoordinate.getX(), currentCoordinate.getY(), currentCoordinate.getZ()));
            }
        }
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
