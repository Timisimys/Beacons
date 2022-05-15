package timisimys.beacons.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

public class FinishTop {

    static Map<Player, Integer> killsPlayers = StartGame.getKillsPlayers();


    public static void onFinish() {

        for(Player pl : Bukkit.getOnlinePlayers()) {
            pl.sendMessage(ChatColor.GOLD + " " +ChatColor.BOLD + "Топ по убийствам");
        }
        killsPlayers.entrySet().stream()
                .sorted(Map.Entry.<Player, Integer>comparingByValue().reversed());

        int i = 1;
        for (Map.Entry<Player, Integer> entry : killsPlayers.entrySet()) {
            Player player = entry.getKey();
            int kills = entry.getValue();
            if(i > 3) return;

            for(Player pl : Bukkit.getOnlinePlayers()) {
                pl.sendMessage(i + ") " + player.getDisplayName() + " - " + kills );
            }
            i++;
        }
    }
}
