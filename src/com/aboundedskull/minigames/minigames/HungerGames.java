package com.aboundedskull.minigames.minigames;

import com.aboundedskull.minigames.data.Coordinate;
import com.aboundedskull.minigames.data.Items;
import com.aboundedskull.minigames.data.Tile;
import com.aboundedskull.minigames.utils.Random;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class HungerGames implements Minigame {

    private static final String HUNGER_GAMES_WORLD = "Hunger games";
    private final Tile centerTile;
    private boolean started;
    private final List<Coordinate> coordinateList = new ArrayList<Coordinate>(){
        {
            add(new Coordinate(-64, 74, -3));
            add(new Coordinate(-63, 74, -8));
            add(new Coordinate(-59, 74, -12));
            add(new Coordinate(-54, 74, -13));
            add(new Coordinate(-49, 74, -12));
            add(new Coordinate(-23, 74, -9));
            add(new Coordinate(-44, 74, -3));
            add(new Coordinate(-45, 74, 3));
            add(new Coordinate(-49, 74, 7));
            add(new Coordinate(-54, 74, 8));
            add(new Coordinate(-59, 74, 7));
            add(new Coordinate(-63, 74, 3));
        }
    };

    private final List<Coordinate> chestCoordinates = new ArrayList<Coordinate>(){
        {
            add(new Coordinate(0,0,0));
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

        loadChests();

    }

    private void loadChests() {
        World world = Bukkit.getWorld(HUNGER_GAMES_WORLD);
        for (Coordinate coordinate : chestCoordinates){
            Block block = new Location(world, coordinate.getX(),  coordinate.getY(), coordinate.getZ()).getBlock();

            if (block instanceof Chest){
                Chest chest = (Chest) block;

                Inventory inventory = chest.getBlockInventory();

                // Clear all the items.
                inventory.clear();

                for (int i = 0; i < inventory.getSize(); i++) {
                    // 1/5 chance of stopping.
                    if (ThreadLocalRandom.current().nextInt(5) == 0)
                        break;

                    ItemStack itemStack = getRandomItem();
                    inventory.setItem(i, itemStack);
                }
            } else {
                System.out.println("Invalid chest position: " + coordinate.toString());
            }
        }
    }

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
