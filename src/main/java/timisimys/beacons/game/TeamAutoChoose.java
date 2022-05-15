package timisimys.beacons.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import timisimys.beacons.events.joinServer;

import java.util.ArrayList;
import java.util.List;

public class TeamAutoChoose {

    static List<Player> BlueTeam = joinServer.getBlueTeam();
    static List<Player> RedTeam = joinServer.getRedTeam();
    static List<Player> FreePlayers = new ArrayList<>();
    private static int free_player = 0;
    public static void onStart() {
        Filter();
        if (RedTeam.size() > BlueTeam.size()) {
            if (free_player != 0) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (RedTeam.contains(player)) return;
                    if (BlueTeam.contains(player)) return;
                    BlueTeam.add(player);
                    player.setDisplayName(ChatColor.BLUE + player.getName() + ChatColor.RESET);
                    player.setPlayerListName(ChatColor.BLUE + player.getName() + ChatColor.RESET);
                }
            } else {
                while (RedTeam.size() > BlueTeam.size() + 1) {
                    Player player = RedTeam.get(0);
                    RedTeam.remove(player);
                    BlueTeam.add(player);
                    player.setDisplayName(ChatColor.BLUE + player.getName() + ChatColor.RESET);
                    player.setPlayerListName(ChatColor.BLUE + player.getName() + ChatColor.RESET);
                }
            }
        } else if (RedTeam.size() < BlueTeam.size()) {
            if (free_player != 0) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (RedTeam.contains(player)) return;
                    if (BlueTeam.contains(player)) return;
                    RedTeam.add(player);
                    player.setDisplayName(ChatColor.RED + player.getName() + ChatColor.RESET);
                    player.setPlayerListName(ChatColor.RED + player.getName() + ChatColor.RESET);
                }
            } else {
                while (RedTeam.size() > BlueTeam.size() + 1) {
                    Player player = BlueTeam.get(0);
                    BlueTeam.remove(player);
                    RedTeam.add(player);
                    player.setDisplayName(ChatColor.RED + player.getName() + ChatColor.RESET);
                    player.setPlayerListName(ChatColor.RED + player.getName() + ChatColor.RESET);
                }
            }
        } else {
            if(free_player != 0) {
                for( int i = 0; i < free_player; i++){
                    Player player = FreePlayers.get(i);
                    if(i % 2 == 0) {
                        RedTeam.add(player);
                        player.setDisplayName(ChatColor.RED + player.getName() + ChatColor.RESET);
                        player.setPlayerListName(ChatColor.RED + player.getName() + ChatColor.RESET);
                    } else {
                        BlueTeam.add(FreePlayers.get(i));
                        player.setDisplayName(ChatColor.BLUE + player.getName() + ChatColor.RESET);
                        player.setPlayerListName(ChatColor.BLUE + player.getName() + ChatColor.RESET);
                    }
                }
            }
        }
    }

    public static void Filter() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(RedTeam.contains(player)) return;
            if(BlueTeam.contains(player)) return;
            free_player++;
            FreePlayers.add(player);
        }
    }
}
