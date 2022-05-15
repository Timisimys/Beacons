package timisimys.beacons.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class messageSend implements Listener {

    List<Player> BlueTeam = joinServer.getBlueTeam();
    List<Player> RedTeam = joinServer.getRedTeam();

    @EventHandler
    private void messageSend(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        ChatColor color;

        if(BlueTeam.contains(player)) {
            color = ChatColor.BLUE;
        } else if(RedTeam.contains(player)) {
            color = ChatColor.RED;
        } else {
            color = ChatColor.RESET;
        }

        if(event.getMessage().startsWith("!")) {
            for(Player player1 : Bukkit.getOnlinePlayers()) {
                player1.sendMessage(color + player.getName() + ChatColor.RESET +  " » " + event.getMessage().substring(1));
            }
        } else {
            if(BlueTeam.contains(player)) {
                for(Player player1 : BlueTeam) {
                    player1.sendMessage("[" + ChatColor.GOLD + "Команда" + ChatColor.RESET + "] " + color + player.getName() + ChatColor.RESET +  " » " + event.getMessage());
                }
            } else if(RedTeam.contains(player)) {
                for(Player player1 : RedTeam) {
                    player1.sendMessage("[" + ChatColor.GOLD + "Команда" + ChatColor.RESET + "] " + color + player.getName() + ChatColor.RESET +  " » " + event.getMessage());
                }
            } else {
                for(Player player1 : Bukkit.getOnlinePlayers()) {
                    player1.sendMessage(color + player.getName() + ChatColor.RESET +  " » " + event.getMessage());
                }
            }
        }
        event.setCancelled(true);
    }
}
