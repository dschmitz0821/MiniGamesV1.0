package com.aboundedskull.minigames.commands;

import com.aboundedskull.minigames.data.minigame.MinigameData;
import com.aboundedskull.minigames.minigames.HungerGames;
import com.aboundedskull.minigames.minigames.Minigame;
import com.aboundedskull.minigames.minigames.TheWalls;
import com.aboundedskull.minigames.utils.handler.MessageHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MiniGameCommands implements CommandExecutor, MessageHandler {

    private List<Minigame> minigameEvents = new ArrayList<Minigame>(){
        {
            add(new HungerGames());
            add(new TheWalls());
        }
    };

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;
        String cmdName = cmd.getName();
        if (cmdName.equals("start")){
            /*
            Format for the command is /start gametype or /start gametype x y
            Where the first command will default to 0,0
            Example of command is /start HG or /start HG 50 50
             */
            if (args.length >= 1){
                String minigameType = args[0];
                boolean foundGame = false;

                for (Minigame minigame : minigameEvents) {
                    // Find matching minigame type.
                    if (!minigame.getCommandName().equalsIgnoreCase(minigameType))
                        continue;

                    foundGame = true;

                    // Hunger games type of event
                    if (minigameType.equalsIgnoreCase("HG")){
                        if (!minigame.isStarted()) {
                            // Create a new minigame data object.
                            MinigameData data = new MinigameData(player, args);

                            // Validate the arguments
                            if (minigame.validateArguments(data)) {
                                // Send starting message.
                                minigame.sendStartingMessage();

                                // Start the event
                                minigame.startMinigame(data);
                            }
                        } else {
                            // Tell the player to stop the current hunger games event first.
                            sendErrorMessage(player, "Unable to start " + minigame.getName() + " event, stop current event first.");
                            sendErrorMessage(player, "Example: /end " + minigame.getCommandName());
                        }
                    }
                }

                if (!foundGame){
                    sendErrorMessage(player, "Failed to find game with the name input.");
                }

            } else {
                sendErrorMessage(player, "Unable to start event, incorrect argument length.");
                sendErrorMessage(player, "/start HG or /start HG 0 0");
            }
        } else if (cmdName.equals("end")){
            if (args.length == 1){
                String minigameType = args[0];
                boolean found = false;
                for (Minigame event : minigameEvents) {
                    if (!minigameType.equalsIgnoreCase(event.getCommandName()))
                        continue;

                    found = true;
                    if (event.isStarted()){
                        // Send ending message.
                        event.sendEndingMessage();

                        // Run the stop hunger games event code.
                        event.endMinigame();
                    } else {
                        sendErrorMessage(player, "Unable to stop the current Hunger Games event, as there is no active event.");
                    }
                }

                if (!found){
                    sendErrorMessage(player, "Failed to find game with the name input.");
                }
            } else {
                sendErrorMessage(player, "Incorrect command length.");
                sendErrorMessage(player, "Example: /end HG");
            }
        }
        return true;
    }
}
