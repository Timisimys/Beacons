package timisimys.beacons.kits;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import timisimys.beacons.events.joinServer;

import java.util.List;

public class defaultKit {

    private static List<Player> RedTeam = joinServer.getRedTeam();
    private static List<Player> BlueTeam = joinServer.getBlueTeam();

    public static void giveKit(Player player) {

        ItemStack kit_1 = new ItemStack(Material.STONE_SWORD,1);
        ItemStack kit_2 = new ItemStack(Material.BOW,1);
        ItemStack kit_3 = new ItemStack(Material.STONE_PICKAXE,1);
        ItemStack kit_4 = new ItemStack(Material.STONE_AXE,1);
        ItemStack kit_5 = new ItemStack(Material.WOOD,64);
        ItemStack kit_6 = new ItemStack(Material.WOOD,64);
        ItemStack kit_7 = new ItemStack(Material.GOLDEN_APPLE,3);
        ItemStack kit_9 = new ItemStack(Material.ARROW,16);

        ItemStack arm_1 = new ItemStack(Material.LEATHER_HELMET,1, (short) 14);
        ItemStack arm_2 = new ItemStack(Material.LEATHER_CHESTPLATE,1, (short) 14);
        ItemStack arm_3 = new ItemStack(Material.LEATHER_LEGGINGS,1, (short) 14);
        ItemStack arm_4 = new ItemStack(Material.LEATHER_BOOTS,1, (short) 14);

        LeatherArmorMeta arm_1_meta = (LeatherArmorMeta) arm_1.getItemMeta();
        LeatherArmorMeta arm_2_meta = (LeatherArmorMeta) arm_2.getItemMeta();
        LeatherArmorMeta arm_3_meta = (LeatherArmorMeta) arm_3.getItemMeta();
        LeatherArmorMeta arm_4_meta = (LeatherArmorMeta) arm_4.getItemMeta();

        if(RedTeam.contains(player)) {
            arm_1_meta.setColor(Color.RED);
            arm_2_meta.setColor(Color.RED);
            arm_3_meta.setColor(Color.RED);
            arm_4_meta.setColor(Color.RED);
        } else {
            arm_1_meta.setColor(Color.BLUE);
            arm_2_meta.setColor(Color.BLUE);
            arm_3_meta.setColor(Color.BLUE);
            arm_4_meta.setColor(Color.BLUE);
        }




        arm_1_meta.setUnbreakable(true);
        arm_1_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        arm_2_meta.setUnbreakable(true);
        arm_2_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        arm_3_meta.setUnbreakable(true);
        arm_3_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        arm_4_meta.setUnbreakable(true);
        arm_4_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        arm_1.setItemMeta(arm_1_meta);
        arm_2.setItemMeta(arm_2_meta);
        arm_3.setItemMeta(arm_3_meta);
        arm_4.setItemMeta(arm_4_meta);


        player.getInventory().setItem(0, kit_1);
        player.getInventory().setItem(1, kit_2);
        player.getInventory().setItem(2, kit_3);
        player.getInventory().setItem(3, kit_4);
        player.getInventory().setItem(4, kit_5);
        player.getInventory().setItem(5, kit_6);
        player.getInventory().setItem(6, kit_7);
        player.getInventory().setItem(9, kit_9);

        player.getInventory().setHelmet(arm_1);
        player.getInventory().setChestplate(arm_2);
        player.getInventory().setLeggings(arm_3);
        player.getInventory().setBoots(arm_4);
    }
}
