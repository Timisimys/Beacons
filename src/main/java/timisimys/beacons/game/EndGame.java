package timisimys.beacons.game;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import timisimys.beacons.Main;
import timisimys.beacons.events.joinServer;
import timisimys.beacons.events.mapsChanges;

import java.util.*;

public class EndGame {

    private static final List<Player> RedTeam = joinServer.getRedTeam();
    private static final List<Player> BlueTeam = joinServer.getBlueTeam();

    static FileConfiguration config = Main.getInstence().getConfig();
    static String[] center = config.getString("location.game_center").split("_");

    static World world = Bukkit.getWorld(config.getString("location.world_game"));
    public static void onEnd() {


        TimersGame.setSeconds(-100);

        config.set("game.status", "end");
        Main.getInstence().saveConfig();
        TimersGame.setSeconds(20);

        for(Player player : RedTeam) {
            player.getInventory().clear();
            player.setLevel(0);
            player.setHealth(20);
            player.setFoodLevel(20);
            player.setGameMode(GameMode.ADVENTURE);
            player.setAllowFlight(true);
            player.setFlying(true);
        }
        for(Player player : BlueTeam) {
            player.getInventory().clear();
            player.setLevel(0);
            player.setHealth(20);
            player.setFoodLevel(20);
            player.setGameMode(GameMode.ADVENTURE);
            player.setAllowFlight(true);
            player.setFlying(true);
        }

        Location loc = new Location(world, Double.parseDouble(center[0]), Double.parseDouble(center[1]), Double.parseDouble(center[2]));

        FinishTop.onFinish();

        for(Player player : Bukkit.getOnlinePlayers()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 125, 125, true));
            player.teleport(loc);
        }
        if(mapsChanges.getList().size() > 0) {
            for (Location location : mapsChanges.getList()) {
                mapsChanges.world.getBlockAt(location).setType(Material.AIR);
                mapsChanges.world.spawnParticle(Particle.SWEEP_ATTACK, location.getX(), location.getY(), location.getZ(), 50);
            }
        }

        if(mapsChanges.getListRemove().size() > 0) {
            for (Map.Entry<Location, String> entry : mapsChanges.getListRemove().entrySet()) {
                Location location = entry.getKey();
                String material = entry.getValue();
                String[] parts = material.split(":");

                mapsChanges.world.getBlockAt(location).setType(Material.valueOf(parts[0]));
                mapsChanges.world.getBlockAt(location).setData(Byte.parseByte(parts[1]));
            }
        }

    }
}
