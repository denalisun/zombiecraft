package me.denalisun.zombiecraft;
import org.bukkit.plugin.java.JavaPlugin;

import me.denalisun.zombiecraft.Commands.GiveWeaponCommand;
import me.denalisun.zombiecraft.Weapons.GunManager;
import me.denalisun.zombiecraft.Weapons.ShootingListener;

public class ZombieCraft extends JavaPlugin
{
    @Override
    public void onEnable() {
        getLogger().info("Plugin Loaded!");
        getLogger().info("Data Folder: " + getDataFolder().getAbsolutePath());
        GunManager gunManager = new GunManager(getDataFolder());

        this.getCommand("giveweapon").setExecutor(new GiveWeaponCommand(gunManager));

        // Register events
        getServer().getPluginManager().registerEvents(new ShootingListener(gunManager, this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled!");
    }
}
