package me.denalisun.zombiecraft.Weapons;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import org.bukkit.Material;

import com.google.gson.Gson;

import me.denalisun.zombiecraft.Config.GunConfig;
import me.denalisun.zombiecraft.Utils;

public class GunManager {
    private final HashMap<String, Gun> gunPool = new HashMap<>();
    private final File gunsFolder;
    private final Gson gson = new Gson();

    public GunManager(File dataFolder) {
        this.gunsFolder = new File(dataFolder, "guns");
        if (!gunsFolder.exists()) gunsFolder.mkdirs();
        loadGuns();
    }

    public static Material getSafeMaterial(String materialString, Material fallbackMaterial) {
        try {
            return Material.valueOf(materialString.toUpperCase());
        } catch (Exception e) {
            return fallbackMaterial;
        }
    }

    private void loadGuns() {
        File[] files = gunsFolder.listFiles((dir, name) -> name.endsWith(".json"));
        if (files == null) return;

        for (File file : files) {
            try (FileReader reader = new FileReader(file)) {
                GunConfig gunConfig = gson.fromJson(reader, GunConfig.class);

                Material gunMat = getSafeMaterial(gunConfig.Material, Material.WOODEN_SHOVEL);
                WeaponFireMode fireMode = WeaponFireMode.valueOf(gunConfig.getFireMode());

                GunStats gunStats = new GunStats(gunConfig.getDamage(), gunConfig.getFireRate(), gunConfig.getRange(), gunConfig.getSpread(), gunConfig.getReloadTime(), gunConfig.getRecoil(), gunConfig.getMaxClip(), gunConfig.getMaxStock(), fireMode);
                Gun gun = new Gun(gunMat, gunConfig.getName(), Utils.removeFileExtension(file.getName()), gunStats);

                gunPool.put(Utils.removeFileExtension(file.getName()), gun);
                System.out.println("Loaded gun: " + gunConfig.getName());
            } catch (Exception e) {
                System.err.println("Failed to load " + file.getName());
                e.printStackTrace();
            }
        }
    }

    public HashMap<String, Gun> getGunPool() {
        return gunPool;
    }

    public Gun getGunByID(String gunID) {
        return gunPool.get(gunID.toLowerCase());
    }
}
