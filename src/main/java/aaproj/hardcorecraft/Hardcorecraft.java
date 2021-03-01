package aaproj.hardcorecraft;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Hardcorecraft extends JavaPlugin {

    public static FileConfiguration config = null;

    @Override
    public void onEnable() {
        // Plugin startup logic
        // config.ymlが存在しない場合はファイルに出力します。
        saveDefaultConfig();
        config = getConfig();
        saveConfig();
        this.getServer().getPluginManager().registerEvents(new EventListener(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();
    }
}
