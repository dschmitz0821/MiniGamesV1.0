package com.aboundedskull.minigames.minigames;

import com.aboundedskull.minigames.data.locatable.Coordinate;
import com.aboundedskull.minigames.data.locatable.Tile;
import com.aboundedskull.minigames.data.minigame.MinigameData;
import com.aboundedskull.minigames.utils.handler.MessageHandler;
import org.bukkit.ChatColor;

public interface Minigame extends MessageHandler {

    Coordinate getCenterTile();

    String getName();

    String getCommandName();

    boolean validateArguments(MinigameData data);

    void startMinigame(MinigameData data);

    void endMinigame();

    boolean isStarted();

    default void sendStartingMessage(){
        // Send start message
        messageAllPlayers(ChatColor.GREEN + "Starting a " + getName() + " event!");
    }

    default void sendEndingMessage(){
        messageAllPlayers(ChatColor.RED + "Stopping " + getName() + " event!");
    }
}
