package timisimys.beacons.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import timisimys.beacons.Main;
import timisimys.beacons.game.EndGame;

public class stopgame implements CommandExecutor {

    static FileConfiguration config = Main.getInstence().getConfig();
    public stopgame(Main settings) {
        settings.getCommand("stopgame").setExecutor(this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("Данная команда может быть использована только в игре");
            return true;
        }
        Player player = (Player) sender;

        if (player.isOp()) {
            if(config.getString("game.status").equalsIgnoreCase("play")) {
                EndGame.onEnd();
                player.sendMessage(ChatColor.GREEN + "Игра была успешно остановлена!");
            } else {
                player.sendMessage(ChatColor.RED + "Игра еще не запущена!");
            }
        } else {
            player.sendMessage(ChatColor.DARK_RED +  "У вас нет прав!");
        }
        return true;
    }
}
