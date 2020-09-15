package com.aboundedskull.minigames.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public interface MessageHandler {

    default void messageAllPlayers(String message){
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(message);
        }
    }

    default void sendSuccessMessage(Player player, String message){
        sendMessage(player, ChatColor.GREEN + message);
    }

    default void sendErrorMessage(Player player, String message){
        sendMessage(player, ChatColor.RED + message);
    }

    default void sendMessage(Player player, String message){
        player.sendMessage(message);
    }
}
