package timisimys.beacons.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import timisimys.beacons.Main;

public class inventoryMove implements Listener {


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        FileConfiguration config = Main.getInstence().getConfig();
        if(config.getString("game.dev").equalsIgnoreCase("true")) return;
        if(config.getString("game.status").equalsIgnoreCase("play")) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventory(PlayerDropItemEvent event) {
        FileConfiguration config = Main.getInstence().getConfig();
        if(config.getString("game.dev").equalsIgnoreCase("true")) return;
        if(config.getString("game.status").equalsIgnoreCase("play")) return;
        event.setCancelled(true);
    }

    @EventHandler
    private void secondHand(PlayerSwapHandItemsEvent event) {
        FileConfiguration config = Main.getInstence().getConfig();
        if(config.getString("game.dev").equalsIgnoreCase("true")) return;
        if(config.getString("game.status").equalsIgnoreCase("play")) return;
        event.setCancelled(true);
    }
}
