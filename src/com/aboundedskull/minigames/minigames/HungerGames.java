package com.aboundedskull.minigames.minigames;

import com.aboundedskull.minigames.data.locatable.Coordinate;
import com.aboundedskull.minigames.data.item.Items;
import com.aboundedskull.minigames.data.locatable.Tile;
import com.aboundedskull.minigames.data.minigame.MinigameData;
import com.aboundedskull.minigames.utils.random.Random;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class HungerGames implements Minigame {

    // TODO: 9/15/2020 Set the actual hunger games world name.
    private static final String HUNGER_GAMES_WORLD = "MiniGames";
    private final Coordinate centerTile;
    private boolean started;
    private final List<Coordinate> coordinateList = new ArrayList<Coordinate>(){
        {
            add(new Coordinate(-64, 74, -3));
            add(new Coordinate(-63, 74, 2));
            add(new Coordinate(-59, 74, 6));
            add(new Coordinate(-54, 74, 7));
            add(new Coordinate(-49, 74, 6));
            add(new Coordinate(-45, 74, 2));
            add(new Coordinate(-44, 74, -3));
            add(new Coordinate(-45, 74, -8));
            add(new Coordinate(-49, 74, -12));
            add(new Coordinate(-54, 74, -13));
            add(new Coordinate(-59, 74, -12));
            add(new Coordinate(-63, 74, -8));
        }
    };

    public HungerGames() {
        this.centerTile = new Coordinate(-53,74,-3);
    }

    @Override
    public Coordinate getCenterTile() {
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
    public boolean validateArguments(MinigameData data) {
        String[] args = data.getData();
        if (args.length == 1){
            getCenterTile().setX(-53);
            getCenterTile().setY(74);
            getCenterTile().setZ(-3);
            return true;
        } else if (args.length == 3){
            if (!getCenterTile().setX(args[1]) || !getCenterTile().setY(args[2])){
                sendErrorMessage(data.getSender(), "Failed to set the x or y argument, continuing anyways.");
            }
            return true;
        }
        return false;
    }

    @Override
    public void startMinigame(MinigameData data) {
        // Validate that the hunger games world exists.
        World world = Bukkit.getWorld(HUNGER_GAMES_WORLD);
        if (world == null) {
            sendErrorMessage(data.getSender(), "Could not find hunger games world name.");
            return;
        }

        // Hunger games start code
        started = true;

        // Set the initial world bounds
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "worldborder center " + getCenterTile().getX() + " " + getCenterTile().getY());
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "worldborder set 500 0");
        // Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "spreadplayers 0 0 50 200 false @a");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "worldborder set 50 900");

        // Teleport all the players.
        int max = coordinateList.size();
        int playerCount = 0;

        // Store the hunger games world.
        Collections.shuffle(coordinateList);
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (playerCount < max) {
                Coordinate currentCoordinate = coordinateList.get(playerCount++);
                System.out.println("Teleporting to: " + currentCoordinate.toString());
                player.teleport(new Location(world, currentCoordinate.getX() + 0.5, currentCoordinate.getY(), currentCoordinate.getZ() + 0.5));
            }
        }

        // Fill the chests with random items.
        loadChests();
    }

    private void loadChests() {
        World world = Bukkit.getWorld(HUNGER_GAMES_WORLD);

        List<Coordinate> nearbyCoordinates = new ArrayList<>();

        for (int i = -10; i < 10; i++) {
            for (int j = -10; j < 10; j++) {
                for (int k = -10; k < 10; k++) {
                    nearbyCoordinates.add(new Coordinate(centerTile.getX() + i, centerTile.getY() + j, centerTile.getZ() + k));
                }
            }
        }

        for (Coordinate coordinate : nearbyCoordinates){
            BlockState block = new Location(world, coordinate.getX(),  coordinate.getY(), coordinate.getZ()).getBlock().getState();

            if (block instanceof ShulkerBox){
                ShulkerBox shulkerBox = (ShulkerBox) block;

                Inventory inventory = shulkerBox.getInventory();

                loadInventoryWithItems(inventory);
            } else if (block instanceof Chest){
                Chest chest = (Chest) block;

                Inventory inventory = chest.getInventory();

                loadInventoryWithItems(inventory);
            }
            // Otherwise it is an invalid chest location.
        }
    }

    private void loadInventoryWithItems(Inventory inventory) {
        inventory.clear();

        for (int i = 0; i < inventory.getSize(); i++) {
            // 1/5 chance of stopping.
            if (i > 3 && ThreadLocalRandom.current().nextInt(4) == 0)
                break;
            else if (ThreadLocalRandom.current().nextInt(10) == 0)
                break;

            ItemStack itemStack = getRandomItem();
            inventory.addItem(itemStack);
        }
    }

    // TODO: 9/15/2020 Add some kind of weighting for the randomization.
    private ItemStack getRandomItem() {
        Material material;
        int amount = 1;
        switch (ThreadLocalRandom.current().nextInt(6)){
            case 1:
                material = Random.getRandomElement(Items.AXE_ARRAY);
                break;
            case 2:
                material = Random.getRandomElement(Items.SWORD_ARRAY);
                break;
            case 3:
                material = Random.getRandomElement(Items.FOOD_ARRAY);
                amount = ThreadLocalRandom.current().nextInt(1,16);
                break;
            default:
                material = Random.getRandomElement(Items.ARMOUR_ARRAY);
                break;
        }
        return new ItemStack(material, amount);
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
