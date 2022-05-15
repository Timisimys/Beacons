package timisimys.beacons.events;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import timisimys.beacons.Main;
import timisimys.beacons.game.ScoreBoards;
import timisimys.beacons.game.StartGame;

import java.util.List;

public class inventoryClick implements Listener {

    private static final List<Player> BlueTeam = joinServer.getBlueTeam();
    private static final List<Player> RedTeam = joinServer.getRedTeam();


    @EventHandler
    private void TeamChoose(PlayerInteractEvent event) {
        FileConfiguration config = Main.getInstence().getConfig();
        Player player = event.getPlayer();

        if(config.getString("game.dev").equalsIgnoreCase("true")) return;

        if(config.getString("game.status").equalsIgnoreCase("play")) return;
        if(event.getItem() == null) return;
        if(event.getItem().getItemMeta().getDisplayName() == null) return;
        if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Команда " + ChatColor.BOLD +  "" + ChatColor.RED + "Красных")) {
            player.sendMessage("Вы выбрали команду " + ChatColor.RED + "Красных");
            player.setDisplayName(ChatColor.RED + player.getName() + ChatColor.RESET);
            player.setPlayerListName(ChatColor.RED + player.getName() + ChatColor.RESET);
            if(!RedTeam.contains(player)) {
                RedTeam.add(player);
            }
            if(BlueTeam.contains(player)) {
                BlueTeam.remove(player);
            }
            joinServer.setRedTeam(RedTeam);
            ScoreBoards.MainBoard();
            event.setCancelled(true);
            return;
        }
        if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Команда " + ChatColor.BOLD +  "" + ChatColor.BLUE + "Синих")) {
            player.sendMessage("Вы выбрали команду " + ChatColor.BLUE + "Синих");
            player.setDisplayName(ChatColor.BLUE + player.getName() + ChatColor.RESET);
            player.setPlayerListName(ChatColor.BLUE + player.getName() + ChatColor.RESET);
            if(!BlueTeam.contains(player)) {
                BlueTeam.add(player);
            }
            if(RedTeam.contains(player)) {
                RedTeam.remove(player);
            }
            joinServer.setBlueTeam(BlueTeam);
            ScoreBoards.MainBoard();
            event.setCancelled(true);
            return;
        }

        if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Старт игры")) {
            player.sendMessage("Игра будет начата в ближайшее время!");
            StartGame.quickStart();
            event.setCancelled(true);
        }

        }


}
