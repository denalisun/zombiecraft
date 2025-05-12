package me.denalisun.zombiecraft.Config;

import com.google.gson.annotations.SerializedName;
import me.denalisun.zombiecraft.Weapons.WeaponFireMode;

public class GunConfig {
    @SerializedName("name")
    public String Name;

    @SerializedName("material")
    public String Material;

    @SerializedName("damage")
    public int Damage;

    @SerializedName("fireRate")
    public float FireRate;

    @SerializedName("range")
    public int Range;

    @SerializedName("spread")
    public float Spread;

    @SerializedName("reloadTime")
    public float ReloadTime;

    @SerializedName("recoil")
    public float Recoil;

    @SerializedName("maxClip")
    public int MaxClip;

    @SerializedName("maxStock")
    public int MaxStock;

    @SerializedName("fireMode")
    public String FireMode;

    public GunConfig(String name, String materialString, int damage, int firerate, float spread, float reloadtime, float recoil, int maxclip, int maxstock, String firemode) {
        this.Name = name;
        this.Material = materialString;
        this.Damage = damage;
        this.FireRate = firerate;
        this.Spread = spread;
        this.ReloadTime = reloadtime;
        this.Recoil = recoil;
        this.MaxClip = maxclip;
        this.MaxStock = maxstock;
        this.FireMode = firemode;
    }

    public String getName() {
        return Name;
    }

    public String getMaterialString() {
        return Material;
    }

    public int getDamage() {
        return Damage;
    }

    public float getFireRate() {
        return FireRate;
    }

    public int getRange() {
        return Range;
    }

    public float getSpread() {
        return Spread;
    }

    public float getReloadTime() {
        return ReloadTime;
    }

    public float getRecoil() {
        return Recoil;
    }

    public int getMaxClip() {
        return MaxClip;
    }

    public int getMaxStock() {
        return MaxStock;
    }

    public String getFireMode() {
        return FireMode;
    }
}
