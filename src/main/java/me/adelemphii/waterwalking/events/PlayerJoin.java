package me.adelemphii.waterwalking.events;

import me.adelemphii.waterwalking.WaterWalking;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    WaterWalking plugin;
    public PlayerJoin(WaterWalking plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        String path = "players." + event.getPlayer().getUniqueId() + ".water-walk";

        if(!plugin.data.getConfig().isSet(path)) {
            plugin.data.getConfig().set("path", plugin.getConfig().getBoolean("enabled-by-default"));
            plugin.data.saveConfig();
        }

    }
}
