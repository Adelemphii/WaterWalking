package me.adelemphii.waterwalking.gui;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.adelemphii.waterwalking.WaterWalking;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SettingsGUI {

    WaterWalking plugin;
    Player player;
    public SettingsGUI(WaterWalking plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    public void openGUI() {

        ChestGui gui = new ChestGui(1, ChatColor.DARK_GREEN + "Water-Walking Settings");

        gui.setOnGlobalClick(event -> {
            event.setCancelled(true);
        });

        StaticPane pane = new StaticPane(0, 0, 9, 1);

        String path = "players." + player.getUniqueId() + ".water-walk";

        ItemStack greenWool = new ItemStack(Material.LIME_WOOL);

        ItemMeta green = greenWool.getItemMeta();
        green.setDisplayName(ChatColor.GREEN + "ENABLED");
        greenWool.setItemMeta(green);

        ItemStack redWool = new ItemStack(Material.RED_WOOL);

        ItemMeta red = greenWool.getItemMeta();
        red.setDisplayName(ChatColor.RED + "DISABLED");
        redWool.setItemMeta(red);

        if(plugin.data.getConfig().getBoolean(path)) {
            pane.addItem(new GuiItem(greenWool, event -> {
                greenWool.setType(Material.RED_WOOL);

                ItemMeta green2 = greenWool.getItemMeta();
                green2.setDisplayName(ChatColor.RED + "DISABLED");
                greenWool.setItemMeta(green2);

                plugin.data.getConfig().set(path, false);

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cWater-Walking disabled."));
                gui.update();
                player.closeInventory();
            }), 0, 0);
        } else {
            pane.addItem(new GuiItem(redWool, event -> {
                redWool.setType(Material.LIME_WOOL);

                ItemMeta red2 = redWool.getItemMeta();
                red2.setDisplayName(ChatColor.GREEN + "ENABLED");
                redWool.setItemMeta(red2);

                plugin.data.getConfig().set(path, true);

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aWater-Walking enabled."));
                gui.update();
                player.closeInventory();
            }), 0, 0);
        }

        gui.addPane(pane);

        gui.show(player);

    }

}
