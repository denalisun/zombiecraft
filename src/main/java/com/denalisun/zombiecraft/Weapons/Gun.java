package com.denalisun.zombiecraft.Weapons;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import com.denalisun.zombiecraft.App;

public class Gun {
    private final ItemStack gunStack;

    public Gun(Material gunMat, String gunName, int gunId, GunStats stats) {
        gunStack = new ItemStack(gunMat);
        ItemMeta gunMeta = gunStack.getItemMeta();
        assert gunMeta != null;
        gunMeta.setDisplayName(gunName);
        gunMeta.getPersistentDataContainer().set(new NamespacedKey(App.getPlugin(App.class), "weaponid"), PersistentDataType.INTEGER, gunId);
        
        // Sync all stats to the persistent data container
        gunMeta.getPersistentDataContainer().set(new NamespacedKey(App.getPlugin(App.class), "gunStat.Damage"), PersistentDataType.INTEGER, stats.Damage);
        gunMeta.getPersistentDataContainer().set(new NamespacedKey(App.getPlugin(App.class), "gunStat.FireRate"), PersistentDataType.INTEGER, stats.FireRate);
        gunMeta.getPersistentDataContainer().set(new NamespacedKey(App.getPlugin(App.class), "gunStat.Range"), PersistentDataType.INTEGER, stats.Range);
        gunMeta.getPersistentDataContainer().set(new NamespacedKey(App.getPlugin(App.class), "gunStat.Spread"), PersistentDataType.FLOAT, stats.Spread);
        gunMeta.getPersistentDataContainer().set(new NamespacedKey(App.getPlugin(App.class), "gunStat.ReloadTime"), PersistentDataType.FLOAT, stats.ReloadTime);
        gunMeta.getPersistentDataContainer().set(new NamespacedKey(App.getPlugin(App.class), "gunStat.Recoil"), PersistentDataType.FLOAT, stats.Recoil);
        gunMeta.getPersistentDataContainer().set(new NamespacedKey(App.getPlugin(App.class), "gunStat.MaxClip"), PersistentDataType.INTEGER, stats.MaxClip);
        gunMeta.getPersistentDataContainer().set(new NamespacedKey(App.getPlugin(App.class), "gunStat.MaxStock"), PersistentDataType.INTEGER, stats.MaxStock);
        gunMeta.getPersistentDataContainer().set(new NamespacedKey(App.getPlugin(App.class), "gunStat.FireMode"), PersistentDataType.INTEGER, stats.FireMode.GetFireMode());
    }
}