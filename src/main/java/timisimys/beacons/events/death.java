package timisimys.beacons.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import timisimys.beacons.Main;
import timisimys.beacons.game.ScoreBoards;
import timisimys.beacons.game.StartGame;
import timisimys.beacons.kits.defaultKit;

import java.util.List;
import java.util.Map;

public class death implements Listener {
    @EventHandler
    public void playerHp(PlayerDeathEvent event) {
        Player player = event.getEntity();
        FileConfiguration config = Main.getInstence().getConfig();
        Location loc;
        World world;

        List<Player> BlueTeam = joinServer.getBlueTeam();
        List<Player> RedTeam = joinServer.getRedTeam();
        Map<Player, Integer> killsPlayers = StartGame.getKillsPlayers();
        Map<Player, Integer> deathPlayers = StartGame.getDeathPlayers();

        String[] lobby = Main.getInstence().getConfig().getString("location.lobby").split("_");
        String[] red_spawn = Main.getInstence().getConfig().getString("location.spawn_red").split("_");
        String[] blue_spawn = Main.getInstence().getConfig().getString("location.spawn_blue").split("_");
        String[] game_center = Main.getInstence().getConfig().getString("location.world_center").split("_");

        if(config.getString("game.status").equalsIgnoreCase("play")) {
            world = Bukkit.getWorld(config.getString("location.world_game"));
            if(BlueTeam.contains(player)) {
                loc = new Location(world, Double.parseDouble(blue_spawn[0]), Double.parseDouble(blue_spawn[1]), Double.parseDouble(blue_spawn[2]));
            } else if(RedTeam.contains(player)){
                loc = new Location(world, Double.parseDouble(red_spawn[0]), Double.parseDouble(red_spawn[1]), Double.parseDouble(red_spawn[2]));
            } else {
                loc = new Location(world, Double.parseDouble(game_center[0]), Double.parseDouble(game_center[1]), Double.parseDouble(game_center[2]));
            }

            for(PotionEffect effect : player.getActivePotionEffects()) {
                player.removePotionEffect((effect.getType()));
            }

            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 120, 100, true));
            player.setFoodLevel(20);
            player.setHealth(20);
            player.setLevel(0);

            player.teleport(loc);
            event.setCancelled(true);

            player.getInventory().clear();
            defaultKit.giveKit(player);
            player.sendTitle("Вы Умерли", null, 15, 50, 10);

            if(player.getKiller() != null) {
                if (killsPlayers.containsKey(player.getKiller())) {
                    killsPlayers.put(player.getKiller(), killsPlayers.get(player.getKiller()) + 1);
                } else {
                    killsPlayers.put(player.getKiller(), 1);
                }

            }
            if (deathPlayers.containsKey(player)) {
                deathPlayers.put(player, deathPlayers.get(player) + 1);
            } else {
                deathPlayers.put(player, 1);
            }

            StartGame.setKillsPlayers(killsPlayers);
            StartGame.setDeathPlayers(deathPlayers);

            ScoreBoards.RedBoard();
            ScoreBoards.BlueBoard();

            for(Player player1 : Bukkit.getOnlinePlayers()) {
                if(player.getKiller() != null) {
                    player1.sendMessage(player.getKiller().getDisplayName() + " убил " + player.getDisplayName());
                } else {
                    player1.sendMessage(player.getDisplayName() + " упал в пустоту");
                }
            }

        } else if(config.getString("game.status").equalsIgnoreCase("end")) {

            world = Bukkit.getWorld(config.getString("location.world_game"));
            loc = new Location(world, Double.parseDouble(game_center[0]), Double.parseDouble(game_center[1]), Double.parseDouble(game_center[2]));

            player.teleport(loc);
            event.setCancelled(true);

        } else {
            world = Bukkit.getWorld(config.getString("location.world_lobby"));
            loc = new Location(world, Double.parseDouble(lobby[0]), Double.parseDouble(lobby[1]), Double.parseDouble(lobby[2]));

            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 120, 100, true));
            player.setFoodLevel(20);
            player.setHealth(20);
            player.setLevel(0);


            player.teleport(loc);
            event.setCancelled(true);
        }

    }
}
