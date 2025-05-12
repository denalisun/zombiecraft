package me.denalisun.zombiecraft.Weapons;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.denalisun.zombiecraft.ZombieCraft;
import net.md_5.bungee.api.ChatColor;

public class Gun {
    private final ItemStack gunStack;

    private static final NamespacedKey DAMAGE_KEY = new NamespacedKey(ZombieCraft.getPlugin(ZombieCraft.class), "gunStat.Damage");
    private static final NamespacedKey FIRERATE_KEY = new NamespacedKey(ZombieCraft.getPlugin(ZombieCraft.class), "gunStat.FireRate");
    private static final NamespacedKey RANGE_KEY = new NamespacedKey(ZombieCraft.getPlugin(ZombieCraft.class), "gunStat.Range");
    private static final NamespacedKey SPREAD_KEY = new NamespacedKey(ZombieCraft.getPlugin(ZombieCraft.class), "gunStat.Spread");
    private static final NamespacedKey RELOADTIME_KEY = new NamespacedKey(ZombieCraft.getPlugin(ZombieCraft.class), "gunStat.ReloadTime");
    private static final NamespacedKey RECOIL_KEY = new NamespacedKey(ZombieCraft.getPlugin(ZombieCraft.class), "gunStat.Recoil");
    private static final NamespacedKey MAXCLIP_KEY = new NamespacedKey(ZombieCraft.getPlugin(ZombieCraft.class), "gunStat.MaxClip");
    private static final NamespacedKey MAXSTOCK_KEY = new NamespacedKey(ZombieCraft.getPlugin(ZombieCraft.class), "gunStat.MaxStock");
    private static final NamespacedKey FIREMODE_KEY = new NamespacedKey(ZombieCraft.getPlugin(ZombieCraft.class), "gunStat.FireMode");

    public Gun(Material gunMat, String gunName, String gunId, GunStats stats) {
        gunStack = new ItemStack(gunMat);
        ItemMeta gunMeta = gunStack.getItemMeta();
        assert gunMeta != null;
        gunMeta.setDisplayName(ChatColor.RESET + gunName);
        gunMeta.getPersistentDataContainer().set(new NamespacedKey(ZombieCraft.getPlugin(ZombieCraft.class), "weaponid"), PersistentDataType.STRING, gunId);
        
        // Sync all stats to the persistent data container
        gunMeta.getPersistentDataContainer().set(DAMAGE_KEY, PersistentDataType.INTEGER, stats.Damage);
        gunMeta.getPersistentDataContainer().set(FIRERATE_KEY, PersistentDataType.FLOAT, stats.FireRate);
        gunMeta.getPersistentDataContainer().set(RANGE_KEY, PersistentDataType.INTEGER, stats.Range);
        gunMeta.getPersistentDataContainer().set(SPREAD_KEY, PersistentDataType.FLOAT, stats.Spread);
        gunMeta.getPersistentDataContainer().set(RELOADTIME_KEY, PersistentDataType.FLOAT, stats.ReloadTime);
        gunMeta.getPersistentDataContainer().set(RECOIL_KEY, PersistentDataType.FLOAT, stats.Recoil);
        gunMeta.getPersistentDataContainer().set(MAXCLIP_KEY, PersistentDataType.INTEGER, stats.MaxClip);
        gunMeta.getPersistentDataContainer().set(MAXSTOCK_KEY, PersistentDataType.INTEGER, stats.MaxStock);
        gunMeta.getPersistentDataContainer().set(FIREMODE_KEY, PersistentDataType.INTEGER, stats.FireMode.getFireMode());

        gunStack.setItemMeta(gunMeta);
    }

    private PersistentDataContainer getContainer() {
        if (!gunStack.hasItemMeta()) return null;
        return gunStack.getItemMeta().getPersistentDataContainer();
    }

    public ItemStack getGunStack() {
        return gunStack;
    }

    public int getDamage() {
        return getContainer().get(DAMAGE_KEY, PersistentDataType.INTEGER);
    }

    public float getFireRate() {
        return getContainer().get(FIRERATE_KEY, PersistentDataType.FLOAT);
    }

    public int getRange() {
        return getContainer().get(RANGE_KEY, PersistentDataType.INTEGER);
    }

    public float getSpread() {
        return getContainer().get(SPREAD_KEY, PersistentDataType.FLOAT);
    }

    public float getReloadTime() {
        return getContainer().get(RELOADTIME_KEY, PersistentDataType.FLOAT);
    }

    public float getRecoil() {
        return getContainer().get(RECOIL_KEY, PersistentDataType.FLOAT);
    }

    public int getMaxClip() {
        return getContainer().get(MAXCLIP_KEY, PersistentDataType.INTEGER);
    }

    public int getMaxStock() {
        return getContainer().get(MAXSTOCK_KEY, PersistentDataType.INTEGER);
    }

    public WeaponFireMode getFireMode() {
        Integer value = getContainer().get(FIREMODE_KEY, PersistentDataType.INTEGER);
        return value != null ? WeaponFireMode.fromInt(value) : WeaponFireMode.SEMI_AUTO;
    }

    public static Gun fromItemStack(ItemStack item) {
        if (item == null || item.getItemMeta() == null) return null;
        ItemMeta meta = item.getItemMeta();
        var pdc = meta.getPersistentDataContainer();
        var plugin = ZombieCraft.getPlugin(ZombieCraft.class);

        // try to get required weapon id
        String weaponId = pdc.get(new NamespacedKey(plugin, "weaponid"), PersistentDataType.STRING);
        if (weaponId == null) return null;

        // read all stats
        Integer damage = pdc.get(new NamespacedKey(plugin, "gunStat.Damage"), PersistentDataType.INTEGER);
        Float fireRate = pdc.get(new NamespacedKey(plugin, "gunStat.FireRate"), PersistentDataType.FLOAT);
        Integer range = pdc.get(new NamespacedKey(plugin, "gunStat.Range"), PersistentDataType.INTEGER);
        Float spread = pdc.get(new NamespacedKey(plugin, "gunStat.Spread"), PersistentDataType.FLOAT);
        Float reloadTime = pdc.get(new NamespacedKey(plugin, "gunStat.ReloadTime"), PersistentDataType.FLOAT);
        Float recoil = pdc.get(new NamespacedKey(plugin, "gunStat.Recoil"), PersistentDataType.FLOAT);
        Integer maxClip = pdc.get(new NamespacedKey(plugin, "gunStat.MaxClip"), PersistentDataType.INTEGER);
        Integer maxStock = pdc.get(new NamespacedKey(plugin, "gunStat.MaxStock"), PersistentDataType.INTEGER);
        Integer fireModeVal = pdc.get(new NamespacedKey(plugin, "gunStat.FireMode"), PersistentDataType.INTEGER);

        // make sure all necessary data exists
        if (damage == null || fireRate == null || range == null || spread == null || reloadTime == null ||
            recoil == null || maxClip == null || maxStock == null || fireModeVal == null) {
            return null;
        }

        // construct stats and gun
        GunStats stats = new GunStats(damage, fireRate, range, spread, reloadTime, recoil, maxClip, maxStock, WeaponFireMode.fromInt(fireModeVal));
        String displayName = meta.hasDisplayName() ? ChatColor.stripColor(meta.getDisplayName()) : "Unknown";

        return new Gun(item.getType(), displayName, weaponId, stats);
    }
}