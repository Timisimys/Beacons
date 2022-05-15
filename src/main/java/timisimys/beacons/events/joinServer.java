package timisimys.beacons.events;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import timisimys.beacons.Main;
import timisimys.beacons.game.BossBarGame;
import timisimys.beacons.game.ScoreBoards;
import timisimys.beacons.game.TimersGame;
import timisimys.beacons.game.WaitingLobby;

import java.util.ArrayList;
import java.util.List;

public class joinServer implements Listener {

    private static List<Player> RedTeam = new ArrayList<>();
    private static List<Player> BlueTeam = new ArrayList<>();

    static FileConfiguration config = Main.getInstence().getConfig();
    static String[] center = config.getString("location.game_center").split("_");

    static World world = Bukkit.getWorld(config.getString("location.world_game"));

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if(config.getString("game.status").equalsIgnoreCase("lock")){
            if(player.isOp()) return;
            player.kickPlayer("Сервер закрыт на разработку");
        } else if(config.getString("game.status").equalsIgnoreCase("wait")) {


            if(Bukkit.getOnlinePlayers().size() > config.getInt("game.max_players")) {
                player.kickPlayer("Арена заполнена!");
                return;
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
            } else if(Bukkit.getOnlinePlayers().size() >= config.getInt("game.min_players")) {
                if(TimersGame.getSeconds() > 120 || TimersGame.getSeconds() < 0) {
                    TimersGame.setSeconds(120);
                    TimersGame.setStart_seconds(120);
                }
            }

            event.setJoinMessage(player.getName() + ChatColor.GOLD + " присоединился к игре!");
            WaitingLobby.Join(player);
            BossBarGame.showBossBar(String.valueOf(player.getUniqueId()));
        } else if(config.getString("game.status").equalsIgnoreCase("end")) {

            player.kickPlayer("Игра окончена!");

        } else {

//            Location loc = new Location(world, Double.parseDouble(center[0]), Double.parseDouble(center[1]), Double.parseDouble(center[2]));
//            player.teleport(loc);
            event.setJoinMessage(null);
            player.sendTitle("Игра уже идет!", "Вы были перемещены в режим наблюдателя", 10, 60, 20);
            player.setGameMode(GameMode.SPECTATOR);
            player.getInventory().clear();
            BossBarGame.showBossBar(String.valueOf(player.getUniqueId()));
            ScoreBoards.MainBoard();
        }

    }


    public static List<Player> getRedTeam() {
        return RedTeam;
    }

    public static void setRedTeam(List<Player> redTeam) {
        RedTeam = redTeam;
    }

    public static List<Player> getBlueTeam() {
        return BlueTeam;
    }

    public static void setBlueTeam(List<Player> blueTeam) {
        BlueTeam = blueTeam;
    }



}
