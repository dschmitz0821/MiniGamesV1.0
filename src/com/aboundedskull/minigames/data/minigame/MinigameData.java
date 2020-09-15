package com.aboundedskull.minigames.data.minigame;

import org.bukkit.entity.Player;

public class MinigameData {

    private final String[] data;
    private final Player sender;

    public MinigameData(Player sender, String... data) {
        this.sender = sender;
        this.data = data;
    }

    public String[] getData() {
        return data;
    }

    public Player getSender() {
        return sender;
    }
}
