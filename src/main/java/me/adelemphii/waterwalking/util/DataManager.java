package me.adelemphii.waterwalking.util;

import me.adelemphii.waterwalking.WaterWalking;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class DataManager {

    WaterWalking plugin;
    public DataManager(WaterWalking plugin) {
        this.plugin = plugin;
        saveDefaultConfig();
    }

    private FileConfiguration dataConfig = null;

    private File configFile = null;

    public void reloadConfig() {
        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "database.yml");
        this.dataConfig = (FileConfiguration) YamlConfiguration.loadConfiguration(this.configFile);
        InputStream defaultStream = this.plugin.getResource("database.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig() {
        if (this.dataConfig == null)
            reloadConfig();
        return this.dataConfig;
    }

    public void saveConfig() {
        if (this.dataConfig == null || this.configFile == null)
            return;
        try {
            getConfig().save(this.configFile);
        } catch (IOException e) {
            this.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, e);
        }
    }

    public void saveDefaultConfig() {
        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "database.yml");
        if (!this.configFile.exists())
            this.plugin.saveResource("database.yml", false);
    }

    public void deleteConfig() {
        if (this.configFile != null)
            this.configFile.delete();
    }

}
