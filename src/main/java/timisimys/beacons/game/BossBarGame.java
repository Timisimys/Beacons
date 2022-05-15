package timisimys.beacons.game;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import timisimys.beacons.Main;

import java.util.UUID;


public class BossBarGame {

    static BossBar bar;
    static FileConfiguration config = Main.getInstence().getConfig();


    public static void createBossBar() {
        Integer AllPlayers = Bukkit.getOnlinePlayers().size();
        String nameBar = "Ожидание игроков: " + AllPlayers + "/" + config.getString("game.max_players");
        bar = Bukkit.createBossBar(nameBar, BarColor.BLUE, BarStyle.SOLID);
    }

    public static void editBossBar(String name, Double progress) {
        String nameBar = name;
        bar.setProgress(progress);
        bar.setTitle(nameBar);
    }

    public static void showBossBar(String n) {
        Integer AllPlayers = Bukkit.getOnlinePlayers().size();
        String nameBar = "Ожидание игроков: " + AllPlayers + "/" + config.getString("game.max_players");
        bar.setTitle(nameBar);

        if(n == "none") {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (bar.getPlayers().contains(player)) return;
                bar.addPlayer(player);
            }
        } else {
            UUID uuid = UUID.fromString(n);
            Player p = Bukkit.getPlayer(uuid);
            bar.addPlayer(p);
        }
    }
}
