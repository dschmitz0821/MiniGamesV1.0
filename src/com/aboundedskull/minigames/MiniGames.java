package com.aboundedskull.minigames;

import com.aboundedskull.minigames.commands.MiniGameCommands;
import com.aboundedskull.minigames.events.MiniGamesEvents;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.loot.LootContext;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class MiniGames extends JavaPlugin {

    @Override
    public void onEnable() {
        // MiniGames commands
        MiniGameCommands miniGameCommands = new MiniGameCommands();
        Objects.requireNonNull(getCommand("start")).setExecutor(miniGameCommands);

        getServer().getPluginManager().registerEvents(new MiniGamesEvents(), this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Mini Games]: Plugin is enabled.");

    }
    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Mini Games]: Plugin is disabled.");

    }
}
