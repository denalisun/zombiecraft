package me.denalisun.zombiecraft.Weapons;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.denalisun.zombiecraft.ZombieCraft;

public class Gun {
    private final ItemStack gunStack;

    public Gun(Material gunMat, String gunName, String gunId, GunStats stats) {
        gunStack = new ItemStack(gunMat);
        ItemMeta gunMeta = gunStack.getItemMeta();
        assert gunMeta != null;
        gunMeta.setDisplayName(gunName);
        gunMeta.getPersistentDataContainer().set(new NamespacedKey(ZombieCraft.getPlugin(ZombieCraft.class), "weaponid"), PersistentDataType.INTEGER, gunId);
        
        // Sync all stats to the persistent data container
        gunMeta.getPersistentDataContainer().set(new NamespacedKey(ZombieCraft.getPlugin(ZombieCraft.class), "gunStat.Damage"), PersistentDataType.INTEGER, stats.Damage);
        gunMeta.getPersistentDataContainer().set(new NamespacedKey(ZombieCraft.getPlugin(ZombieCraft.class), "gunStat.FireRate"), PersistentDataType.FLOAT, stats.FireRate);
        gunMeta.getPersistentDataContainer().set(new NamespacedKey(ZombieCraft.getPlugin(ZombieCraft.class), "gunStat.Range"), PersistentDataType.INTEGER, stats.Range);
        gunMeta.getPersistentDataContainer().set(new NamespacedKey(ZombieCraft.getPlugin(ZombieCraft.class), "gunStat.Spread"), PersistentDataType.FLOAT, stats.Spread);
        gunMeta.getPersistentDataContainer().set(new NamespacedKey(ZombieCraft.getPlugin(ZombieCraft.class), "gunStat.ReloadTime"), PersistentDataType.FLOAT, stats.ReloadTime);
        gunMeta.getPersistentDataContainer().set(new NamespacedKey(ZombieCraft.getPlugin(ZombieCraft.class), "gunStat.Recoil"), PersistentDataType.FLOAT, stats.Recoil);
        gunMeta.getPersistentDataContainer().set(new NamespacedKey(ZombieCraft.getPlugin(ZombieCraft.class), "gunStat.MaxClip"), PersistentDataType.INTEGER, stats.MaxClip);
        gunMeta.getPersistentDataContainer().set(new NamespacedKey(ZombieCraft.getPlugin(ZombieCraft.class), "gunStat.MaxStock"), PersistentDataType.INTEGER, stats.MaxStock);
        gunMeta.getPersistentDataContainer().set(new NamespacedKey(ZombieCraft.getPlugin(ZombieCraft.class), "gunStat.FireMode"), PersistentDataType.INTEGER, stats.FireMode.getFireMode());
    }
}