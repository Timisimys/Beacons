package timisimys.beacons.game;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import timisimys.beacons.Main;
import timisimys.beacons.events.joinServer;

import java.util.List;

public class WaitingLobby {

    private List<Player> RedTeam = joinServer.getRedTeam();
    private List<Player> BlueTeam = joinServer.getBlueTeam();

    public static void Join(Player player) {

        FileConfiguration config = Main.getInstence().getConfig();
        World world = Bukkit.getWorld(config.getString("location.world_lobby"));
        String[] loc = Main.getInstence().getConfig().getString("location.lobby").split("_");

        Location location = new Location(world, Double.parseDouble(loc[0]), Double.parseDouble(loc[1]), Double.parseDouble(loc[2]));

        ItemStack red_block = new ItemStack(Material.WOOL, 1,(short) 14);
        ItemStack blue_block = new ItemStack(Material.WOOL,1,(short) 11);
        ItemStack admin_block = new ItemStack(Material.BEDROCK,1);

        ItemMeta red_meta = red_block.getItemMeta();
        ItemMeta blue_meta = blue_block.getItemMeta();
        ItemMeta admin_meta = admin_block.getItemMeta();

        red_meta.setDisplayName("Команда " + ChatColor.BOLD +  "" + ChatColor.RED + "Красных");
        blue_meta.setDisplayName("Команда " + ChatColor.BOLD +  "" + ChatColor.BLUE + "Синих");
        admin_meta.setDisplayName("Старт игры");

        red_meta.setUnbreakable(true);
        red_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        blue_meta.setUnbreakable(true);
        blue_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        admin_meta.setUnbreakable(true);
        admin_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        red_block.setItemMeta(red_meta);
        blue_block.setItemMeta(blue_meta);
        admin_block.setItemMeta(admin_meta);

        player.setGameMode(GameMode.ADVENTURE);
        player.teleport(location);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.getInventory().setItem(0, red_block);
        player.getInventory().setItem(1, blue_block);
        if(player.isOp()) {
            player.getInventory().setItem(8, admin_block);
        }
        player.updateInventory();

        player.sendTitle(ChatColor.GREEN + "" + ChatColor.BOLD + "Добро пожаловать в игру!", null, 20, 50 ,20);
        BossBarGame.showBossBar("none");
    }
}
