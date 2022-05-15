package timisimys.beacons.events;



import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import timisimys.beacons.Main;
import timisimys.beacons.game.EndGame;
import timisimys.beacons.game.ScoreBoards;
import timisimys.beacons.game.ReadyMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mapsChanges implements Listener {

    public static List<Location> listPlace = new ArrayList<>();
    private static final Map<Location, String> listRemove = new HashMap<>();
    public static World world;
    FileConfiguration config = Main.getInstence().getConfig();

    private static List<Player> BlueTeam = joinServer.getBlueTeam();
    private static List<Player> RedTeam = joinServer.getRedTeam();

    private static Map<String, Boolean> redBeaconStatus = ReadyMap.getRedBeaconStatus();
    private static Map<String, Boolean> blueBeaconStatus = ReadyMap.getBlueBeaconStatus();

    String[] red_right_beacon = config.getString("location.red_beacons.right").split("_");
    String[] red_left_beacon = config.getString("location.red_beacons.left").split("_");

    String[] blue_right_beacon = config.getString("location.blue_beacons.right").split("_");
    String[] blue_left_beacon = config.getString("location.blue_beacons.left").split("_");

    Location location_red_right = new Location(Bukkit.getWorld(config.getString("location.world_game")), Double.parseDouble(red_right_beacon[0]), Double.parseDouble(red_right_beacon[1]), Double.parseDouble(red_right_beacon[2]));
    Location location_blue_right = new Location(Bukkit.getWorld(config.getString("location.world_game")), Double.parseDouble(blue_right_beacon[0]), Double.parseDouble(blue_right_beacon[1]), Double.parseDouble(blue_right_beacon[2]));

    Location location_red_left = new Location(Bukkit.getWorld(config.getString("location.world_game")), Double.parseDouble(red_left_beacon[0]), Double.parseDouble(red_left_beacon[1]), Double.parseDouble(red_left_beacon[2]));
    Location location_blue_left = new Location(Bukkit.getWorld(config.getString("location.world_game")), Double.parseDouble(blue_left_beacon[0]), Double.parseDouble(blue_left_beacon[1]), Double.parseDouble(blue_left_beacon[2]));

    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {


        Player player = event.getPlayer();
        Location loc = event.getBlockPlaced().getLocation();
        world = event.getBlockPlaced().getWorld();
        Material block = event.getBlock().getType();


        if(player.getGameMode() == GameMode.CREATIVE) return;
        String[] blue = Main.getInstence().getConfig().getString("location.spawn_blue").split("_");
        String[] red = Main.getInstence().getConfig().getString("location.spawn_red").split("_");
        World world = Bukkit.getWorld(Main.getInstence().getConfig().getString("location.world_game"));

        Location spawn_red = new Location(world, Double.parseDouble(red[0]), Double.parseDouble(red[1]), Double.parseDouble(red[2]));
        Location spawn_blue = new Location(world, Double.parseDouble(blue[0]), Double.parseDouble(blue[1]), Double.parseDouble(blue[2]));

        if((Math.abs(loc.getBlockX() - spawn_red.getBlockX()) + Math.abs(loc.getBlockZ() - spawn_red.getBlockZ()) <= 6) & (Math.abs(loc.getBlockX() - spawn_red.getBlockX()) < 5 & Math.abs(loc.getBlockZ() - spawn_red.getBlockZ()) < 5)) {
            player.sendMessage(ChatColor.DARK_RED + "Здесь нельзя ставить блоки");
            event.setCancelled(true);
        }
        if((Math.abs(loc.getBlockX() - spawn_blue.getBlockX()) + Math.abs(loc.getBlockZ() - spawn_blue.getBlockZ()) <= 6) & (Math.abs(loc.getBlockX() - spawn_blue.getBlockX()) < 5 & Math.abs(loc.getBlockZ() - spawn_blue.getBlockZ()) < 5)) {
            player.sendMessage(ChatColor.DARK_RED + "Здесь нельзя ставить блоки");
            event.setCancelled(true);
        }
        if(loc.getBlockY() > config.getInt("limits.max_build_height")) {
            player.sendMessage(ChatColor.DARK_RED + "Ты не можешь ставить блоки так высоко!");
            event.setCancelled(true);
        }

        if(!listPlace.contains(loc)) {
            listPlace.add(loc);
        }

    }


    @EventHandler
    public void blockRemove(BlockBreakEvent event) {

        Player player = event.getPlayer();
        Location loc = event.getBlock().getLocation();

        world = event.getBlock().getWorld();
        byte blockData = event.getBlock().getData();
        Material block = event.getBlock().getType();

        if(player.getGameMode() == GameMode.CREATIVE) return;

        String[] blue = Main.getInstence().getConfig().getString("location.spawn_blue").split("_");
        String[] red = Main.getInstence().getConfig().getString("location.spawn_red").split("_");
        World world = Bukkit.getWorld(Main.getInstence().getConfig().getString("location.world_game"));

        Location spawn_red = new Location(world, Double.parseDouble(red[0]), Double.parseDouble(red[1]), Double.parseDouble(red[2]));
        Location spawn_blue = new Location(world, Double.parseDouble(blue[0]), Double.parseDouble(blue[1]), Double.parseDouble(blue[2]));

        if((Math.abs(loc.getBlockX() - spawn_red.getBlockX()) + Math.abs(loc.getBlockZ() - spawn_red.getBlockZ()) <= 6) & (Math.abs(loc.getBlockX() - spawn_red.getBlockX()) < 5 & Math.abs(loc.getBlockZ() - spawn_red.getBlockZ()) < 5)) {
            player.sendMessage(ChatColor.DARK_RED + "Здесь нельзя ломать блоки");
            event.setCancelled(true);
        }
        if((Math.abs(loc.getBlockX() - spawn_blue.getBlockX()) + Math.abs(loc.getBlockZ() - spawn_blue.getBlockZ()) <= 6) & (Math.abs(loc.getBlockX() - spawn_blue.getBlockX()) < 5 & Math.abs(loc.getBlockZ() - spawn_blue.getBlockZ()) < 5)) {
            player.sendMessage(ChatColor.DARK_RED + "Здесь нельзя ломать блоки");
            event.setCancelled(true);
        }

        if(block == Material.GOLD_BLOCK) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.DARK_RED + "Ты не можешь сломать этот блок!");
        }

        if(block == Material.CHEST) {
            if(listPlace.contains(loc)) return;
            event.setCancelled(true);
            player.sendMessage(ChatColor.DARK_RED + "Ты не можешь сломать этот блок!");
        }

        if(loc.getBlock().getType() == Material.BEACON && ((loc.getBlockX() == location_red_right.getBlockX()) && (loc.getBlockY() == location_red_right.getBlockY()) && (loc.getBlockZ() == location_red_right.getBlockZ()))){
            event.setCancelled(true);
            if(RedTeam.contains(player)){
                player.sendMessage(ChatColor.RED + "Ты не можешь ломать свой маяк!");
                return;
            }
            Bukkit.getWorld(config.getString("location.world_game")).getBlockAt(loc).setType(Material.BEDROCK);

            if(redBeaconStatus.get("right") && redBeaconStatus.get("left")) {
                redBeaconStatus.put("right", false);
                ReadyMap.setRedBeaconStatus(redBeaconStatus);

                for (Player pl : RedTeam) {
                    pl.sendTitle("Правый маяк был сломан!", null, 20, 50, 20);
                }
                player.sendTitle(" ", "§l§aВы сломали маяк §4Красной §aкоманды", 10, 35,10);
                ScoreBoards.RedBoard();
                ScoreBoards.BlueBoard();
                ScoreBoards.MainBoard();
            } else {
                redBeaconStatus.put("right", false);
                ReadyMap.setRedBeaconStatus(redBeaconStatus);
                ScoreBoards.RedBoard();
                ScoreBoards.BlueBoard();
                ScoreBoards.MainBoard();
                for(Player ap : Bukkit.getOnlinePlayers()) {
                    ap.sendTitle("Победила команда " + ChatColor.BLUE + "Синих", "Спасибо за игру!", 20, 75, 20);
                }
                EndGame.onEnd();
            }
        }
        if(loc.getBlock().getType() == Material.BEACON && ((loc.getBlockX() == location_red_left.getBlockX()) && (loc.getBlockY() == location_red_left.getBlockY()) && (loc.getBlockZ() == location_red_left.getBlockZ()))){
            event.setCancelled(true);
            if(RedTeam.contains(player)){
                player.sendMessage(ChatColor.RED + "Ты не можешь ломать свой маяк!");
                return;
            }
            Bukkit.getWorld(config.getString("location.world_game")).getBlockAt(loc).setType(Material.BEDROCK);

            if(redBeaconStatus.get("right") && redBeaconStatus.get("left")) {
                redBeaconStatus.put("left", false);
                ReadyMap.setRedBeaconStatus(redBeaconStatus);

                for (Player pl : RedTeam) {
                    pl.sendTitle("Левый маяк был сломан!", null, 20, 50, 20);
                }
                player.sendTitle(" ", "§l§aВы сломали маяк §4Красной §aкоманды", 10, 35,10);
                ScoreBoards.RedBoard();
                ScoreBoards.BlueBoard();
                ScoreBoards.MainBoard();
            } else {
                redBeaconStatus.put("left", false);
                ReadyMap.setRedBeaconStatus(redBeaconStatus);
                ScoreBoards.RedBoard();
                ScoreBoards.BlueBoard();
                ScoreBoards.MainBoard();
                for(Player ap : Bukkit.getOnlinePlayers()) {
                    ap.sendTitle("Победила команда " + ChatColor.BLUE + "Синих", "Спасибо за игру!", 20, 75, 20);
                }
                EndGame.onEnd();
            }
        }




        if(loc.getBlock().getType() == Material.BEACON && ((loc.getBlockX() == location_blue_right.getBlockX()) && (loc.getBlockY() == location_blue_right.getBlockY()) && (loc.getBlockZ() == location_blue_right.getBlockZ()))){
            event.setCancelled(true);
            if(BlueTeam.contains(player)){
                player.sendMessage(ChatColor.RED + "Ты не можешь ломать свой маяк!");
                return;
            }
            Bukkit.getWorld(config.getString("location.world_game")).getBlockAt(loc).setType(Material.BEDROCK);

            if(blueBeaconStatus.get("right") && blueBeaconStatus.get("left")) {
                blueBeaconStatus.put("right", false);
                ReadyMap.setBlueBeaconStatus(blueBeaconStatus);

                for (Player pl : BlueTeam) {
                    pl.sendTitle("Правый маяк был сломан!", null, 20, 50, 20);
                }
                player.sendTitle(" ", "§l§aВы сломали маяк §9Синий §aкоманды", 10, 35,10);
                ScoreBoards.RedBoard();
                ScoreBoards.BlueBoard();
                ScoreBoards.MainBoard();
            } else {
                blueBeaconStatus.put("right", false);
                ReadyMap.setBlueBeaconStatus(blueBeaconStatus);
                ScoreBoards.RedBoard();
                ScoreBoards.BlueBoard();
                ScoreBoards.MainBoard();
                for(Player ap : Bukkit.getOnlinePlayers()) {
                    ap.sendTitle("Победила команда " + ChatColor.RED + "Красныых", "Спасибо за игру!", 20, 75, 20);
                    EndGame.onEnd();
                }
            }
        }

        if(loc.getBlock().getType() == Material.BEACON && ((loc.getBlockX() == location_blue_left.getBlockX()) && (loc.getBlockY() == location_blue_left.getBlockY()) && (loc.getBlockZ() == location_blue_left.getBlockZ()))){
            event.setCancelled(true);
            if(BlueTeam.contains(player)){
                player.sendMessage(ChatColor.RED + "Ты не можешь ломать свой маяк!");
                return;
            }
            Bukkit.getWorld(config.getString("location.world_game")).getBlockAt(loc).setType(Material.BEDROCK);

            if(blueBeaconStatus.get("right") && blueBeaconStatus.get("left")) {
                blueBeaconStatus.put("left", false);
                ReadyMap.setBlueBeaconStatus(blueBeaconStatus);

                for (Player pl : BlueTeam) {
                    pl.sendTitle("Левый маяк был сломан!", null, 20, 50, 20);
                }
                player.sendTitle(" ", "§l§aВы сломали маяк §9Синий §aкоманды", 10, 35,10);
                ScoreBoards.RedBoard();
                ScoreBoards.BlueBoard();
                ScoreBoards.MainBoard();
            } else {
                blueBeaconStatus.put("left", false);
                ReadyMap.setBlueBeaconStatus(blueBeaconStatus);
                ScoreBoards.RedBoard();
                ScoreBoards.BlueBoard();
                ScoreBoards.MainBoard();
                for(Player ap : Bukkit.getOnlinePlayers()) {
                    ap.sendTitle("Победила команда " + ChatColor.RED + "Красных", "Спасибо за игру!", 20, 75, 20);
                    EndGame.onEnd();
                }
            }
        }

        String fullBlock = block + ":" + blockData;

        if(listPlace.contains(loc)) {
            listPlace.remove(loc);
            return;
        }
        if(block == Material.BEACON) return;
        listRemove.put(loc, fullBlock);

    }

    public static List<Location> getList() {
        return listPlace;
    }

    public static Map<Location, String> getListRemove() {
        return listRemove;
    }
}
