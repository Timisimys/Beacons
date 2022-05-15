package timisimys.beacons.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import timisimys.beacons.Main;

import java.util.List;


public class moving implements Listener {

    FileConfiguration config = Main.getInstence().getConfig();
    List<Player> BlueTeam = joinServer.getBlueTeam();
    List<Player> RedTeam = joinServer.getRedTeam();

    @EventHandler
    public void move(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if(config.getString("game.dev").equalsIgnoreCase("true")) return;

        if(player.getLocation().getY() < Main.getInstence().getConfig().getInt("limits.min_build_height")){
            if(player.getGameMode() == GameMode.CREATIVE) return;
            if(player.getGameMode() == GameMode.SPECTATOR) {
                Location up = new Location(player.getWorld(), player.getLocation().getBlockX(), player.getLocation().getBlockY() + 10, player.getLocation().getBlockZ());
                player.teleport(up);
                return;
            }
            player.setHealth(0);
        }
    }


    @EventHandler
    private void pvp(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player) {
            if(event.getDamager() instanceof Player) {
                if(BlueTeam.contains(event.getDamager()) && BlueTeam.contains(event.getEntity())) {

                    event.setCancelled(true);
                    event.getDamager().sendMessage(ChatColor.RED + "Вы не можете атаковать своих тиммейтов");

                } else if(RedTeam.contains(event.getDamager()) && RedTeam.contains(event.getEntity())) {

                    event.setCancelled(true);
                    event.getDamager().sendMessage(ChatColor.RED + "Вы не можете атаковать своих тиммейтов");

                }
            }
        }
        if(config.getString("game.status").equalsIgnoreCase("play")) return;
        event.setCancelled(true);
    }
}
