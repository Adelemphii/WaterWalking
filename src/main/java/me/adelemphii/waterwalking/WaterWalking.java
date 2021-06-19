package me.adelemphii.waterwalking;

import me.adelemphii.waterwalking.commands.SettingsWaterWalking;
import me.adelemphii.waterwalking.commands.ToggleWaterWalking;
import me.adelemphii.waterwalking.events.PlayerJoin;
import me.adelemphii.waterwalking.events.WaterConvert;
import me.adelemphii.waterwalking.util.DataManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class WaterWalking extends JavaPlugin {

    public DataManager data;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        registerCE();

        this.data = new DataManager(this);

    }

    @Override
    public void onDisable() {
    }

    public void registerCE() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new WaterConvert(this), this);
        pm.registerEvents(new PlayerJoin(this), this);

        Objects.requireNonNull(getCommand("togglewaterwalking")).setExecutor(new ToggleWaterWalking(this));
        Objects.requireNonNull(getCommand("settings")).setExecutor(new SettingsWaterWalking(this));
    }
}
