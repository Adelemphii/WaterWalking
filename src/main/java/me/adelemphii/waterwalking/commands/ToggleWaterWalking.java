package me.adelemphii.waterwalking.commands;

import me.adelemphii.waterwalking.WaterWalking;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleWaterWalking implements CommandExecutor {

    WaterWalking plugin;
    public ToggleWaterWalking(WaterWalking plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) { return false; }

        Player player = (Player) sender;

        String path = "players." + player.getUniqueId() + ".water-walk";

        if(cmd.getName().equalsIgnoreCase("togglewaterwalking")) {

            // If water-walk is set to true in database.yml, set it to false.
            // IF water-walk is set to false in database.yml, set it to true.
            if (plugin.data.getConfig().getBoolean(path)) {
                plugin.data.getConfig().set(path, false);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cWater-walking disabled."));
            } else {
                plugin.data.getConfig().set(path, true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aWater-walking enabled."));
            }
            return true;
        }

        return false;
    }

}
