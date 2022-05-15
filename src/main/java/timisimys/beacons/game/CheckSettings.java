package timisimys.beacons.game;

import timisimys.beacons.Main;
import org.bukkit.configuration.file.FileConfiguration;

public class CheckSettings {
    public static void onStart() {

        FileConfiguration config = Main.getInstence().getConfig();
        if(config.getString("game.dev").equalsIgnoreCase("true")) {
            config.set("game.status", "lock");
            Main.getInstence().saveConfig();
            return;
        }
        if(config.getString("location.lobby").equalsIgnoreCase("none") || config.getString("location.spawn_red").equalsIgnoreCase("none") || config.getString("location.spawn_blue").equalsIgnoreCase("none")) {
            config.set("game.status", "lock");
            Main.getInstence().saveConfig();
        } else {
            config.set("game.status", "wait");
            Main.getInstence().saveConfig();
        }

    }

}
