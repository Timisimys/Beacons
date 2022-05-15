package timisimys.beacons.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import timisimys.beacons.Main;
import timisimys.beacons.events.joinServer;

import java.util.List;
import java.util.Map;

public class ScoreBoards {

    private static final List<Player> BlueTeam = joinServer.getBlueTeam();
    private static final List<Player> RedTeam = joinServer.getRedTeam();
    static FileConfiguration config = Main.getInstence().getConfig();

    public static void MainBoard() {

        int seconds = (int) TimersGame.getSeconds();
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        Objective obj;


        if(board.getObjective("MainBeacons") != null) {
            board.getObjective("MainBeacons").unregister();
        }
        obj = board.registerNewObjective("MainBeacons", "dummy");

        obj.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Маяки");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);


        if(config.getString("game.status").equalsIgnoreCase("wait")) {

            obj.getScore( ChatColor.BOLD +"          Команды      ").setScore(8);
            obj.getScore(ChatColor.RED + "" + ChatColor.BOLD + "Красные: " + ChatColor.RESET + RedTeam.size() + " игроков").setScore(7);
            obj.getScore(ChatColor.BLUE + "" + ChatColor.BOLD + "Синии: " + ChatColor.RESET + BlueTeam.size() + " игроков").setScore(6);
        } else if(config.getString("game.status").equalsIgnoreCase("lock")) {
            obj.getScore("Технические работы").setScore(9);
        } else {
            Map<String, Boolean> blueBeaconStatus = ReadyMap.getBlueBeaconStatus();
            Map<String, Boolean> redBeaconStatus = ReadyMap.getRedBeaconStatus();

            String red_status_right;
            String red_status_left;

            String blue_status_right;
            String blue_status_left;

            if(blueBeaconStatus.get("right")) {
                blue_status_right = ChatColor.GREEN + "✔";
            } else {
                blue_status_right = ChatColor.RED + "✘";
            }

            if(blueBeaconStatus.get("left")) {
                blue_status_left = ChatColor.GREEN + "✔";
            } else {
                blue_status_left = ChatColor.RED + "✘";
            }

            if(redBeaconStatus.get("right")) {
                red_status_right = ChatColor.GREEN + "✔";
            } else {
                red_status_right = ChatColor.RED + "✘";
            }

            if(redBeaconStatus.get("left")) {
                red_status_left = ChatColor.GREEN + "✔";
            } else {
                red_status_left = ChatColor.RED + "✘";
            }

            obj.getScore( ChatColor.BOLD +"         Информация     ").setScore(7);
            obj.getScore(ChatColor.RED + "" + ChatColor.BOLD + "Красные" + ChatColor.RESET + "[" + RedTeam.size() + "]").setScore(6);
            obj.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "Правый мaяк: " + ChatColor.RESET + "" + red_status_right).setScore(5);
            obj.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "Левый мaяк: " + ChatColor.RESET + "" + red_status_left).setScore(4);
            obj.getScore(" ").setScore(3);

            obj.getScore(ChatColor.BLUE + "" + ChatColor.BOLD + "Синии" + ChatColor.RESET + "[" + BlueTeam.size() + "]").setScore(2);
            obj.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "Правый маяк: " + ChatColor.RESET + "" + blue_status_right).setScore(1);
            obj.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "Левый маяк: " + ChatColor.RESET + "" + blue_status_left).setScore(0);

        }

    }

    public static void BlueBoard() {
        Scoreboard board;
        Objective obj;
        Map<String, Boolean> blueBeaconStatus = ReadyMap.getBlueBeaconStatus();
        Map<Player, Integer> killsPlayers = StartGame.getKillsPlayers();
        Map<Player, Integer> deathPlayers = StartGame.getDeathPlayers();

        int kills_size;
        int death_size;

        String status_right;
        String status_left;

        if(blueBeaconStatus.get("right")) {
            status_right = ChatColor.GREEN + "✔";
        } else {
            status_right = ChatColor.RED + "✘";
        }

        if(blueBeaconStatus.get("left")) {
            status_left = ChatColor.GREEN + "✔";
        } else {
            status_left = ChatColor.RED + "✘";
        }


        board = Bukkit.getScoreboardManager().getNewScoreboard();
        if(board.getObjective("BlueBeacons") != null) {
            board.getObjective("BlueBeacons").unregister();
        }

        obj = board.registerNewObjective("BlueBeacons", "dummy");
        obj.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Маяки");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        obj.getScore( ChatColor.BOLD +"         Информация     ").setScore(12);
        obj.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "Правый маяк: " + ChatColor.RESET + "" + status_right).setScore(11);
        obj.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "Левый маяк: " + ChatColor.RESET + "" + status_left).setScore(10);
        obj.getScore(" ").setScore(9);

        obj.getScore( ChatColor.BOLD +"     Игроки в командах ").setScore(8);
        obj.getScore(ChatColor.GRAY + "• " +ChatColor.RED + "" + ChatColor.BOLD + "Красные: " + ChatColor.RESET + RedTeam.size()).setScore(7);
        obj.getScore(ChatColor.GREEN + "• " + ChatColor.BLUE + "" + ChatColor.BOLD + "Синии: " + ChatColor.RESET + BlueTeam.size()).setScore(6);
        obj.getScore( ChatColor.BOLD +" ").setScore(5);

        obj.getScore( ChatColor.BOLD +"         Статистика     ").setScore(4);



        if(BlueTeam.size() > 0) {
            for (Player player : BlueTeam) {
                kills_size = killsPlayers.getOrDefault(player, 0);
                death_size = deathPlayers.getOrDefault(player, 0);

                obj.getScore( ChatColor.AQUA + "" + ChatColor.BOLD + "Убийств: " + ChatColor.RESET + kills_size).setScore(3);
                obj.getScore( ChatColor.AQUA + "" + ChatColor.BOLD + "Смертей: " + ChatColor.RESET + death_size).setScore(2);
                player.setScoreboard(board);
            }
        }

    }

    public static void RedBoard() {
        Scoreboard board;
        Objective obj;

        Map<String, Boolean> redBeaconStatus = ReadyMap.getRedBeaconStatus();
        Map<Player, Integer> killsPlayers = StartGame.getKillsPlayers();
        Map<Player, Integer> deathPlayers = StartGame.getDeathPlayers();

        int kills_size;
        int death_size;

        String status_right;
        String status_left;

        if(redBeaconStatus.get("right")) {
            status_right = ChatColor.GREEN + "✔";
        } else {
            status_right = ChatColor.RED + "✘";
        }

        if(redBeaconStatus.get("left")) {
            status_left = ChatColor.GREEN + "✔";
        } else {
            status_left = ChatColor.RED + "✘";
        }

        board = Bukkit.getScoreboardManager().getNewScoreboard();
        if(board.getObjective("RedBeacons") != null) {
            board.getObjective("RedBeacons").unregister();
        }

        obj = board.registerNewObjective("RedBeacons", "dummy");
        obj.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Маяки");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        obj.getScore( ChatColor.BOLD +"         Информация     ").setScore(12);
        obj.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "Правый маяк: " + ChatColor.RESET + "" + status_right).setScore(11);
        obj.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "Левый маяк: " + ChatColor.RESET + "" + status_left).setScore(10);
        obj.getScore(" ").setScore(9);

        obj.getScore( ChatColor.BOLD +"     Игроки в командах ").setScore(8);
        obj.getScore(ChatColor.GREEN + "• " +ChatColor.RED + "" + ChatColor.BOLD + "Красные: " + ChatColor.RESET + RedTeam.size()).setScore(7);
        obj.getScore(ChatColor.GRAY + "• " + ChatColor.BLUE + "" + ChatColor.BOLD + "Синии: " + ChatColor.RESET + BlueTeam.size()).setScore(6);
        obj.getScore( ChatColor.BOLD +" ").setScore(5);

        obj.getScore( ChatColor.BOLD +"         Статистика     ").setScore(4);

        if(RedTeam.size() > 0) {
            for (Player player : RedTeam) {
                kills_size = killsPlayers.getOrDefault(player, 0);
                death_size = deathPlayers.getOrDefault(player, 0);

                obj.getScore( ChatColor.AQUA + "" + ChatColor.BOLD + "Убийств: " + ChatColor.RESET + kills_size).setScore(3);
                obj.getScore( ChatColor.AQUA + "" + ChatColor.BOLD + "Смертей: " + ChatColor.RESET + death_size).setScore(2);
                player.setScoreboard(board);
            }
        }

    }
}
