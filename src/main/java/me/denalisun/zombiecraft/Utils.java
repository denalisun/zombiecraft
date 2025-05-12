package me.denalisun.zombiecraft;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.denalisun.zombiecraft.Weapons.Gun;
import me.denalisun.zombiecraft.Weapons.GunStats;
import me.denalisun.zombiecraft.Weapons.WeaponFireMode;
import net.md_5.bungee.api.ChatColor;

public class Utils {
    public static String removeFileExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return filename;
        }

        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex == -1) {
            return filename;
        }

        return filename.substring(0, dotIndex);
    }
}
