package timisimys.beacons.game;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import timisimys.beacons.Main;
import timisimys.beacons.events.joinServer;

import java.util.List;

public class BeaconEffect {

    private static final List<Player> BlueTeam = joinServer.getBlueTeam();
    private static final List<Player> RedTeam = joinServer.getRedTeam();

    static FileConfiguration config = Main.getInstence().getConfig();

    static String[] red_right_beacon = config.getString("location.red_beacons.right").split("_");
    static String[] red_left_beacon = config.getString("location.red_beacons.left").split("_");

    static String[] blue_right_beacon = config.getString("location.blue_beacons.right").split("_");
    static String[] blue_left_beacon = config.getString("location.blue_beacons.left").split("_");

    static Location location_red_right = new Location(Bukkit.getWorld(config.getString("location.world_game")), Double.parseDouble(red_right_beacon[0]), Double.parseDouble(red_right_beacon[1]), Double.parseDouble(red_right_beacon[2]));
    static Location location_blue_right = new Location(Bukkit.getWorld(config.getString("location.world_game")), Double.parseDouble(blue_right_beacon[0]), Double.parseDouble(blue_right_beacon[1]), Double.parseDouble(blue_right_beacon[2]));

    static Location location_red_left = new Location(Bukkit.getWorld(config.getString("location.world_game")), Double.parseDouble(red_left_beacon[0]), Double.parseDouble(red_left_beacon[1]), Double.parseDouble(red_left_beacon[2]));
    static Location location_blue_left = new Location(Bukkit.getWorld(config.getString("location.world_game")), Double.parseDouble(blue_left_beacon[0]), Double.parseDouble(blue_left_beacon[1]), Double.parseDouble(blue_left_beacon[2]));

    public static void nearBeacon() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstence(), () -> {
            for(Player player : RedTeam) {
                Location loc = player.getLocation();

                if (Math.abs(Math.abs(loc.getBlockX()) - Math.abs(location_blue_left.getBlockX())) <= 5 && Math.abs(Math.abs(loc.getBlockZ()) - Math.abs(location_blue_left.getBlockZ())) <= 5) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 1, true));
                } else if (Math.abs(Math.abs(loc.getBlockX()) - Math.abs(location_blue_right.getBlockX())) <= 5 && Math.abs(Math.abs(loc.getBlockZ()) - Math.abs(location_blue_right.getBlockZ())) <= 5) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 1, true));
                }
            }

            for(Player player : BlueTeam) {
                Location loc = player.getLocation();

                if (Math.abs((loc.getBlockX()) - Math.abs(location_red_left.getBlockX())) <= 5 && Math.abs(Math.abs(loc.getBlockZ()) - Math.abs(location_red_left.getBlockZ())) <= 5) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 1, true));
                } else if(Math.abs((loc.getBlockX()) - Math.abs(location_red_right.getBlockX())) <= 5 && Math.abs(Math.abs(loc.getBlockZ()) - Math.abs(location_red_right.getBlockZ())) <= 5) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 1, true));
                }
            }
        }, 5, 5);
    }
}
