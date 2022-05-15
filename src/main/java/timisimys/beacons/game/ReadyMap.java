package timisimys.beacons.game;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;
import timisimys.beacons.Main;


import java.util.HashMap;
import java.util.Map;

public class ReadyMap {

    private static Map<String, Boolean> redBeaconStatus = new HashMap<>();
    private static Map<String, Boolean> blueBeaconStatus = new HashMap<>();

    static FileConfiguration config = Main.getInstence().getConfig();
    static String world_game = config.getString("location.world_game");

    private static void Lapis() {
        String[] lapis_first = Main.getInstence().getConfig().getString("location.casts-latis.first").split("_");
        String[] lapis_second = Main.getInstence().getConfig().getString("location.casts-latis.second").split("_");
        if(!lapis_first[0].equals("none")) {
            Location loc = new Location(Bukkit.getWorld(world_game), Double.parseDouble(lapis_first[0]), Double.parseDouble(lapis_first[1]), Double.parseDouble(lapis_first[2]));
            Bukkit.getWorld(world_game).getBlockAt(loc).setType(Material.CHEST);
            Bukkit.getWorld(world_game).getBlockAt(loc).setData(Byte.parseByte(lapis_first[3]));

            if(Bukkit.getWorld(world_game).getBlockAt(loc).getType() == Material.CHEST) {

                Chest chest = (Chest) Bukkit.getWorld(world_game).getBlockAt(loc).getState();
                Inventory inventory = chest.getBlockInventory();
                ItemStack itemStack = new ItemStack(Material.INK_SACK, 64, DyeColor.BLUE.getDyeData());

                for (int i = 0; i < inventory.getSize(); i++) {
                    inventory.setItem(i, itemStack);
                }
            }
        }
        if(!lapis_second[0].equals("none")) {
            Location loc = new Location(Bukkit.getWorld(world_game), Double.parseDouble(lapis_second[0]), Double.parseDouble(lapis_second[1]), Double.parseDouble(lapis_second[2]));
            Bukkit.getWorld(world_game).getBlockAt(loc).setType(Material.CHEST);
            Bukkit.getWorld(world_game).getBlockAt(loc).setData(Byte.parseByte(lapis_second[3]));

            if(Bukkit.getWorld(world_game).getBlockAt(loc).getType() == Material.CHEST) {

                Chest chest = (Chest) Bukkit.getWorld(world_game).getBlockAt(loc).getState();
                Inventory inventory = chest.getBlockInventory();
                ItemStack itemStack = new ItemStack(Material.INK_SACK, 64, DyeColor.BLUE.getDyeData());

                for (int i = 0; i < inventory.getSize(); i++) {
                    inventory.setItem(i, itemStack);
                }
            }

        }
    }

    private static void Exp() {
        String[] exp_first = Main.getInstence().getConfig().getString("location.chest_exp.first").split("_");
        String[] exp_second = Main.getInstence().getConfig().getString("location.chest_exp.second").split("_");
        if(!exp_first[0].equals("none")) {
            Location loc = new Location(Bukkit.getWorld(world_game), Double.parseDouble(exp_first[0]), Double.parseDouble(exp_first[1]), Double.parseDouble(exp_first[2]));
            Bukkit.getWorld(world_game).getBlockAt(loc).setType(Material.CHEST);
            Bukkit.getWorld(world_game).getBlockAt(loc).setData(Byte.parseByte(exp_first[3]));

            if(Bukkit.getWorld(world_game).getBlockAt(loc).getType() == Material.CHEST) {

                Chest chest = (Chest) Bukkit.getWorld(world_game).getBlockAt(loc).getState();
                Inventory inventory = chest.getBlockInventory();
                ItemStack itemStack = new ItemStack(Material.EXP_BOTTLE, 64);

                for (int i = 0; i < inventory.getSize(); i++) {
                    inventory.setItem(i, itemStack);
                }
            }
        }
        if(!exp_second[0].equals("none")) {
            Location loc = new Location(Bukkit.getWorld(world_game), Double.parseDouble(exp_second[0]), Double.parseDouble(exp_second[1]), Double.parseDouble(exp_second[2]));
            Bukkit.getWorld(world_game).getBlockAt(loc).setType(Material.CHEST);
            Bukkit.getWorld(world_game).getBlockAt(loc).setData(Byte.parseByte(exp_second[3]));

            if(Bukkit.getWorld(world_game).getBlockAt(loc).getType() == Material.CHEST) {

                Chest chest = (Chest) Bukkit.getWorld(world_game).getBlockAt(loc).getState();
                Inventory inventory = chest.getBlockInventory();
                ItemStack itemStack = new ItemStack(Material.EXP_BOTTLE, 64);

                for (int i = 0; i < inventory.getSize(); i++) {
                    inventory.setItem(i, itemStack);
                }
            }

        }
    }

    private static void Poison() {
        String[] poison_first = Main.getInstence().getConfig().getString("location.chest_poison.first").split("_");
        String[] poison_second = Main.getInstence().getConfig().getString("location.chest_poison.second").split("_");
        if(!poison_first[0].equals("none")) {
            Location loc = new Location(Bukkit.getWorld(world_game), Double.parseDouble(poison_first[0]), Double.parseDouble(poison_first[1]), Double.parseDouble(poison_first[2]));
            Bukkit.getWorld(world_game).getBlockAt(loc).setType(Material.CHEST);
            Bukkit.getWorld(world_game).getBlockAt(loc).setData(Byte.parseByte(poison_first[3]));

            if(Bukkit.getWorld(world_game).getBlockAt(loc).getType() == Material.CHEST) {

                Chest chest = (Chest) Bukkit.getWorld(world_game).getBlockAt(loc).getState();
                Potion potion_heal = new Potion(PotionType.INSTANT_HEAL, 1);
                Potion potion_speed = new Potion(PotionType.SPEED, 1);
                potion_heal.setSplash(true);
                potion_speed.setSplash(true);

                Inventory inventory = chest.getBlockInventory();
                ItemStack poison_heal = new ItemStack(potion_heal.toItemStack(1));
                ItemStack poison_speed = new ItemStack(potion_speed.toItemStack(1));

                for (int i = 0; i < inventory.getSize(); i++) {
                    if(i%2 == 0) {
                        inventory.setItem(i, poison_heal);
                    } else {
                        inventory.setItem(i, poison_speed);
                    }
                }
            }
        }
        if(!poison_second[0].equals("none")) {
            Location loc = new Location(Bukkit.getWorld(world_game), Double.parseDouble(poison_second[0]), Double.parseDouble(poison_second[1]), Double.parseDouble(poison_second[2]));
            Bukkit.getWorld(world_game).getBlockAt(loc).setType(Material.CHEST);
            Bukkit.getWorld(world_game).getBlockAt(loc).setData(Byte.parseByte(poison_second[3]));

            if(Bukkit.getWorld(world_game).getBlockAt(loc).getType() == Material.CHEST) {

                Chest chest = (Chest) Bukkit.getWorld(world_game).getBlockAt(loc).getState();

                Potion potion_heal = new Potion(PotionType.INSTANT_HEAL, 1);
                Potion potion_speed = new Potion(PotionType.SPEED, 1);
                potion_heal.setSplash(true);
                potion_speed.setSplash(true);

                Inventory inventory = chest.getBlockInventory();
                ItemStack poison_heal = new ItemStack(potion_heal.toItemStack(1));
                ItemStack poison_speed = new ItemStack(potion_speed.toItemStack(1));

                for (int i = 0; i < inventory.getSize(); i++) {
                    if(i%2 == 0) {
                        inventory.setItem(i, poison_heal);
                    } else {
                        inventory.setItem(i, poison_speed);
                    }
                }
            }

        }
    }

    private static void red_beacons() {
        String[] beacon_left = Main.getInstence().getConfig().getString("location.red_beacons.left").split("_");
        String[] beacon_right = Main.getInstence().getConfig().getString("location.red_beacons.right").split("_");
        if(!beacon_left[0].equals("none")) {
            Location loc = new Location(Bukkit.getWorld(world_game), Double.parseDouble(beacon_left[0]), Double.parseDouble(beacon_left[1]), Double.parseDouble(beacon_left[2]));
            Bukkit.getWorld(world_game).getBlockAt(loc).setType(Material.BEACON);
            Bukkit.getWorld(world_game).getBlockAt(loc).setData(Byte.parseByte(beacon_left[3]));

        }
        if(!beacon_right[0].equals("none")) {
            Location loc = new Location(Bukkit.getWorld(world_game), Double.parseDouble(beacon_right[0]), Double.parseDouble(beacon_right[1]), Double.parseDouble(beacon_right[2]));
            Bukkit.getWorld(world_game).getBlockAt(loc).setType(Material.BEACON);
            Bukkit.getWorld(world_game).getBlockAt(loc).setData(Byte.parseByte(beacon_right[3]));
        }
    }
    private static void blue_beacons() {
        String[] beacon_left = Main.getInstence().getConfig().getString("location.blue_beacons.left").split("_");
        String[] beacon_right = Main.getInstence().getConfig().getString("location.blue_beacons.right").split("_");
        if(!beacon_left[0].equals("none")) {
            Location loc = new Location(Bukkit.getWorld(world_game), Double.parseDouble(beacon_left[0]), Double.parseDouble(beacon_left[1]), Double.parseDouble(beacon_left[2]));
            Bukkit.getWorld(world_game).getBlockAt(loc).setType(Material.BEACON);
            Bukkit.getWorld(world_game).getBlockAt(loc).setData(Byte.parseByte(beacon_left[3]));
        }
        if(!beacon_right[0].equals("none")) {
            Location loc = new Location(Bukkit.getWorld(world_game), Double.parseDouble(beacon_right[0]), Double.parseDouble(beacon_right[1]), Double.parseDouble(beacon_right[2]));
            Bukkit.getWorld(world_game).getBlockAt(loc).setType(Material.BEACON);
            Bukkit.getWorld(world_game).getBlockAt(loc).setData(Byte.parseByte(beacon_right[3]));
        }
    }

    public static void Chests(){
        Lapis();
        Exp();
        Poison();
        red_beacons();
        blue_beacons();
    }

    public static void BeaconsReset() {
        redBeaconStatus.put("right", true);
        blueBeaconStatus.put("right", true);

        redBeaconStatus.put("left", true);
        blueBeaconStatus.put("left", true);
    }

    public static Map<String, Boolean> getRedBeaconStatus() {
        return redBeaconStatus;
    }

    public static Map<String, Boolean> getBlueBeaconStatus() {
        return blueBeaconStatus;
    }

    public static void setRedBeaconStatus(Map<String, Boolean> redBeaconStatus) {
        ReadyMap.redBeaconStatus = redBeaconStatus;
    }

    public static void setBlueBeaconStatus(Map<String, Boolean> blueBeaconStatus) {
        ReadyMap.blueBeaconStatus = blueBeaconStatus;
    }
}
