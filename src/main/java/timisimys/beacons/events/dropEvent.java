package timisimys.beacons.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class dropEvent implements Listener {

    @EventHandler
    private static void onDrop(BlockBreakEvent e) {
        Block block = e.getBlock();

        if(block.getType() == Material.LEAVES) {
            e.setCancelled(true);
            block.setType(Material.AIR);
        }
    }
}
