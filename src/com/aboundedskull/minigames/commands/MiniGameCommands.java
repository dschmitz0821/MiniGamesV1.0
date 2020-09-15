package com.aboundedskull.minigames.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.util.Collection;
import java.util.Iterator;

public class MiniGameCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;
        String cmdName = cmd.getName();
        if (cmdName.equals("start")){
            if (args.length == 1){
                String minigameType = args[0];
                if (minigameType.equalsIgnoreCase("HG")){
                    // Hunger games code
                    messageAllPlayers(ChatColor.GREEN + "Starting a hunger games event!");

                    // Set the initial world bounds
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "worldborder center 0 0");
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "worldborder set 500 0");
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "spreadplayers 0 0 50 200 false @a");
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "worldborder set 50 600");

                    // Teleport players to a random location


                }
            }
        } else if (cmdName.equals("stop")){
            if (args.length == 1){
                String minigameType = args[0];
                if (minigameType.equalsIgnoreCase("HG")){
                    // Hunger games stopping code
                    messageAllPlayers(ChatColor.RED + "Stopping hunger games event!");
                }
            }
        }


        return true;
    }



    private static void messageAllPlayers(String message){
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(message);
        }
    }
}
