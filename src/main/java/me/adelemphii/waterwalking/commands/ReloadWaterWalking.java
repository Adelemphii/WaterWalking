package me.adelemphii.waterwalking.commands;

import me.adelemphii.waterwalking.WaterWalking;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadWaterWalking implements CommandExecutor {

    WaterWalking plugin;

    public ReloadWaterWalking(WaterWalking plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase("reloadwaterwalking")) {
            plugin.reloadConfig();
            sender.sendMessage("[WW] Config reloaded!");
            return true;
        }

        return false;
    }
}
