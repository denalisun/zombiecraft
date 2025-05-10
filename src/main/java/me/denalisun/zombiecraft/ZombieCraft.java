package me.denalisun.zombiecraft;
import org.bukkit.plugin.java.JavaPlugin;

import me.denalisun.zombiecraft.Weapons.GunManager;

public class ZombieCraft extends JavaPlugin
{
    @Override
    public void onEnable() {
        getLogger().info("Plugin Enabled!");
        getLogger().info("Data Folder: " + getDataFolder().getAbsolutePath());
        GunManager gunManager = new GunManager(getDataFolder());
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled!");
    }
}
