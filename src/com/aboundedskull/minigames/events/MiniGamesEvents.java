package com.aboundedskull.minigames.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class MiniGamesEvents implements Listener {

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.BLUE + "Welcome to the Server!");
    }

    private static long lastPlayerBedEnter = 0;

    @EventHandler
    public static void onPlayerBedEnterEvent(PlayerBedEnterEvent event){
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastPlayerBedEnter > 60_000) {
            Player itself = event.getPlayer();
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!player.equals(itself)) {
                    player.sendMessage(ChatColor.RED + "Get yo ass in bed bitch!");
                } else {
                    player.sendMessage(ChatColor.GREEN + "Tellin dem bitches to sleep!");
                }
            }
            lastPlayerBedEnter = currentTime;
        }
    }

}
