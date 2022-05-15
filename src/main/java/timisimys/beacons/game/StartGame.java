package timisimys.beacons.game;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import timisimys.beacons.Main;
import timisimys.beacons.events.joinServer;
import timisimys.beacons.kits.defaultKit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StartGame {

    static Map<Player, Integer> killsPlayers = new HashMap<>();
    static Map<Player, Integer> deathPlayers = new HashMap<>();

    public static void quickStart() {

        TeamAutoChoose.onStart();

        List<Player> BlueTeam = joinServer.getBlueTeam();
        List<Player> RedTeam = joinServer.getRedTeam();

        FileConfiguration config = Main.getInstence().getConfig();

        String[] red_args = config.getString("location.spawn_red").split("_");
        String[] blue_args = config.getString("location.spawn_blue").split("_");
        String[] center = config.getString("location.game_center").split("_");


        if(!center[0].equalsIgnoreCase("none")) {
            Bukkit.createWorld(new WorldCreator(config.getString("location.world_game")));
        }
        ReadyMap.Chests();
        BeaconEffect.nearBeacon();

        Location BlueSpawn = new Location(Bukkit.getWorld(config.getString("location.world_game")), Double.parseDouble(blue_args[0]), Double.parseDouble(blue_args[1]), Double.parseDouble(blue_args[2]));
        Location RedSpawn = new Location(Bukkit.getWorld(config.getString("location.world_game")), Double.parseDouble(red_args[0]), Double.parseDouble(red_args[1]), Double.parseDouble(red_args[2]));
        Location GameCenter = new Location(Bukkit.getWorld(config.getString("location.world_game")), Double.parseDouble(center[0]), Double.parseDouble(center[1]), Double.parseDouble(center[2]));

        config.set("game.status", "play");
        Main.getInstence().saveConfig();

        for(Player player : RedTeam) {
            player.getInventory().clear();
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 30, 125, true));
            player.teleport(RedSpawn);
            player.sendTitle("§aИгра началась", "§eПодсказка: Сломай маяки противника", 20, 100, 20);
            player.setGameMode(GameMode.SURVIVAL);
            player.setLevel(0);
            defaultKit.giveKit(player);
        }
        for(Player player : BlueTeam) {
            player.getInventory().clear();
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 30, 125, true));
            player.teleport(BlueSpawn);
            player.setLevel(0);
            player.sendTitle("§aИгра началась", "§eПодсказка: Сломай маяки противника", 20, 100, 20);
            player.setGameMode(GameMode.SURVIVAL);
            defaultKit.giveKit(player);
        }
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(!RedTeam.contains(player) && !BlueTeam.contains(player)) {
                player.setGameMode(GameMode.SPECTATOR);
                player.getInventory().clear();
                player.teleport(GameCenter);
            }
        }

        ScoreBoards.RedBoard();
        ScoreBoards.BlueBoard();
        ScoreBoards.MainBoard();

        TimersGame.setSeconds(600);
        TimersGame.setStart_seconds(600);

        Bukkit.unloadWorld("world", false);
        Bukkit.getWorld(config.getString("location.world_game")).getWorldBorder().setCenter(Double.parseDouble(center[0]), Double.parseDouble(center[2]));
        Bukkit.getWorld(config.getString("location.world_game")).getWorldBorder().setSize(200);
        Bukkit.getWorld(config.getString("location.world_game")).setDifficulty(Difficulty.PEACEFUL);
    }

    public static Map<Player, Integer> getKillsPlayers() {
        return killsPlayers;
    }

    public static void setKillsPlayers(Map<Player, Integer> killsPlayer) {
        killsPlayers = killsPlayer;
    }

    public static Map<Player, Integer> getDeathPlayers() {
        return deathPlayers;
    }

    public static void setDeathPlayers(Map<Player, Integer> deathPlayers) {
        StartGame.deathPlayers = deathPlayers;
    }
}
