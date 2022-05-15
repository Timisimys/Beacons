package timisimys.beacons.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import timisimys.beacons.Main;
import timisimys.beacons.game.EndGame;
import timisimys.beacons.game.ScoreBoards;
import timisimys.beacons.game.TimersGame;

import java.util.List;

public class leaveServer implements Listener {

    private static List<Player> BlueTeam = joinServer.getBlueTeam();
    private static List<Player> RedTeam = joinServer.getRedTeam();
    FileConfiguration config = Main.getInstence().getConfig();
    @EventHandler
    private void leaveServer(PlayerQuitEvent event) {

        event.setQuitMessage(null);

        Player player = event.getPlayer();

        if(config.getString("game.status").equalsIgnoreCase("play")) {
            if (BlueTeam.contains(player)) {
                BlueTeam.remove(player);
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    pl.sendMessage("Игрок " + player.getDisplayName() + " вышел!");
                }
                if (BlueTeam.size() < 1) {
                    for (Player ap : Bukkit.getOnlinePlayers()) {
                        ap.sendTitle("Победила команда " + ChatColor.RED + "Крвсных", "Спасибо за игру!", 20, 75, 20);
                    }
                    EndGame.onEnd();
                }
            }
            if (RedTeam.contains(player)) {
                RedTeam.remove(player);
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    pl.sendMessage("Игрок " + player.getDisplayName() + " вышел!");
                }
                if (RedTeam.size() < 1) {
                    for (Player ap : Bukkit.getOnlinePlayers()) {
                        ap.sendTitle("Победила команда " + ChatColor.BLUE + "Синих", "Спасибо за игру!", 20, 75, 20);
                    }
                    EndGame.onEnd();
                }
            }

            ScoreBoards.RedBoard();
            ScoreBoards.BlueBoard();

        } else if(config.getString("game.status").equalsIgnoreCase("wait")) {
            if (BlueTeam.contains(player)) {
                BlueTeam.remove(player);
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    pl.sendMessage("Игрок " + player.getDisplayName() + " вышел!");
                }
            }
            if (RedTeam.contains(player)) {
                RedTeam.remove(player);
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    pl.sendMessage("Игрок " + player.getDisplayName() + " вышел!");
                }
            }


            if(Bukkit.getOnlinePlayers().size() == config.getInt("game.max_players")) {
                if(TimersGame.getSeconds() > 10) {
                    TimersGame.setSeconds(10);
                    TimersGame.setStart_seconds(120);
                }
            } else if(Bukkit.getOnlinePlayers().size() > (config.getInt("game.max_players") - 4)) {
                if(TimersGame.getSeconds() > 60) {
                    TimersGame.setSeconds(60);
                    TimersGame.setStart_seconds(120);
                }
            } else if(Bukkit.getOnlinePlayers().size() > config.getInt("game.min_players")) {
                if(TimersGame.getSeconds() > 120 || TimersGame.getSeconds() < 0) {
                    TimersGame.setSeconds(120);
                    TimersGame.setStart_seconds(120);
                }
            } else {
                if(TimersGame.getSeconds() > -1) {
                    TimersGame.setSeconds(-1);
                    TimersGame.setStart_seconds(-1);
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        pl.sendTitle(" ", "Начало игры отменено", 10 , 30, 10);
                    }
                }
            }

        }

        joinServer.setBlueTeam(BlueTeam);
        joinServer.setRedTeam(RedTeam);


        ScoreBoards.MainBoard();
    }
}
