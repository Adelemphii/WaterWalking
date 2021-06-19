package me.adelemphii.waterwalking.commands;

import me.adelemphii.waterwalking.WaterWalking;
import me.adelemphii.waterwalking.gui.SettingsGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SettingsWaterWalking implements CommandExecutor {
    WaterWalking plugin;
    public SettingsWaterWalking(WaterWalking plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) { return false; }

        Player player = (Player) sender;

        SettingsGUI gui = new SettingsGUI(plugin, player);

        if(cmd.getName().equalsIgnoreCase("settings")) {
            gui.openGUI();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aOpening Settings!"));
            return true;
        }

        return false;
    }
}
