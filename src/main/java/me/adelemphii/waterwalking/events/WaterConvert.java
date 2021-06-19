package me.adelemphii.waterwalking.events;

import me.adelemphii.waterwalking.WaterWalking;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class WaterConvert implements Listener {

    WaterWalking plugin;
    public WaterConvert(WaterWalking plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWalkNearWater(PlayerMoveEvent event) {

        String path = "players." + event.getPlayer().getUniqueId() + ".water-walk";

        if(!plugin.data.getConfig().getBoolean(path)) {
            return;
        }

        if(event.getPlayer().getGameMode() == GameMode.SPECTATOR) { return; }

        if(event.getPlayer().isInWater()) { return; }

        if(event.getTo() == event.getFrom()) { return; }

        int radius = plugin.getConfig().getInt("freeze-radius");

        Location to = event.getTo().clone().subtract(0, 1, 0);

        for(int x = (radius * -1); x <= radius; x++) {
            for(int z = (radius * -1); z <= radius; z++) {

                Block block = to.getWorld().getBlockAt(to.getBlockX() + x, to.getBlockY(), to.getBlockZ() + z);
                if(block.getType() == Material.WATER) {
                    block.setType(Material.ICE);
                }

            }
        }

    }

}
