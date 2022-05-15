package timisimys.beacons.game;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import timisimys.beacons.Main;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class TimersGame {

    private static double seconds = -1;
    private static double start_seconds = -1;

    public static void onTimer() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstence(), () -> {
            FileConfiguration config = Main.getInstence().getConfig();
            if(config.getString("game.status").equalsIgnoreCase("end")) {
                BossBarGame.editBossBar("Игра окончена", 0.0);
                if(seconds > 0) {
                    seconds--;
                    if(seconds == 0) {
                        for(Player player : Bukkit.getOnlinePlayers()) {
                            player.kickPlayer("Игра окончена!");
                        }
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"kill @e");
                        Bukkit.getServer().spigot().restart();
                    }
                }
            }
            if(config.getString("game.status").equalsIgnoreCase("wait")) {
                DecimalFormat df = new DecimalFormat("#.###");
                df.setRoundingMode(RoundingMode.CEILING);
                Double progress = Double.valueOf(df.format(seconds / start_seconds));
                String time;
                String min;
                String sec;

                if(seconds >= 0) {
                    double m = Math.round(Math.floor(seconds / 60));
                    double s = seconds - (m * 60);
                    if(seconds > 60) {
                        min = "0" + m;
                    } else {
                        min = "00";
                    }
                    if(s > 10) {
                        sec = String.valueOf(s);
                    } else {
                        sec = "0" + s;
                    }

                    time = min.substring(0, 2) + ":" + sec.substring(0, 2);
                } else {
                    time = "--:--";
                }


                if(seconds > 0) {
                    seconds--;
                    if(seconds == 0) {
                        StartGame.quickStart();
                    }
                    BossBarGame.editBossBar("Начало игры через " + time, progress);
                }
            }
            if(config.getString("game.status").equalsIgnoreCase("play")) {
                seconds--;

                if (seconds == 0) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendTitle("§6Ничья", "§3Игра окончена по истечению времени!", 20, 140, 20);
                    }
                    BossBarGame.editBossBar("Игра окончена", 0.0);
                    EndGame.onEnd();
                } else if (seconds < 0) {
                    BossBarGame.editBossBar("Игра окончена", 1.0);
                } else {
                    String time;
                    String min = null;
                    String sec = null;
                    DecimalFormat df = new DecimalFormat("#.###");
                    df.setRoundingMode(RoundingMode.CEILING);

                    Double progress = Double.valueOf(df.format(seconds / start_seconds));

                    if (seconds >= 60) {
                        double m = Math.round(Math.floor(seconds / 60));
                        double s = seconds - (m * 60);
                        if (m < 10) {
                            min = "0" + m;
                        } else {
                            min = String.valueOf(m);
                        }
                        if (s < 10) {
                            sec = "0" + s;
                        } else {
                            sec = String.valueOf(s);
                        }
                    } else if (seconds < 60) {
                        min = "00";
                        if(seconds > 10) {
                            sec = String.valueOf(seconds);
                        } else {
                            sec = "0" + seconds;
                        }
                    }

                    assert min != null;
                    time = min.substring(0, 2) + ":" + sec.substring(0, 2);
                    BossBarGame.editBossBar("Осталось: " + time, progress);
                }
            }
        }, 20, 20);

    }

    public static double getSeconds() {
        return seconds;
    }

    public static void setSeconds(double seconds) {
        TimersGame.seconds = seconds;
    }

    public static double getStart_seconds() {
        return start_seconds;
    }

    public static void setStart_seconds(double start_seconds) {
        TimersGame.start_seconds = start_seconds;
    }
}
