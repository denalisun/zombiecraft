package com.denalisun.zombiecraft.WeaponSystem;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Gun {
    public Gun(Material gunMat, String gunName, GunStats stats, NamespacedKey itemModel) {
        ItemStack gunStack = new ItemStack(gunMat);
        ItemMeta gunMeta = gunStack.getItemMeta();
        gunMeta.setItemModel(itemModel);
    }
}
