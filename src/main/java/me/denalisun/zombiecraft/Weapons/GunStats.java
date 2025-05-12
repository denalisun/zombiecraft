package me.denalisun.zombiecraft.Weapons;

public class GunStats {
    public int Damage;
    public float FireRate;
    public int Range;
    public float Spread;
    public float ReloadTime;
    public float Recoil;
    public int MaxClip;
    public int MaxStock;
    public WeaponFireMode FireMode;

    public GunStats(int damage, float fireRate, int range, float spread, float reloadTime, float recoil, int maxClip,
            int maxStock, WeaponFireMode fireMode) {
        Damage = damage;
        FireRate = fireRate;
        Range = range;
        Spread = spread;
        ReloadTime = reloadTime;
        Recoil = recoil;
        MaxClip = maxClip;
        MaxStock = maxStock;
        FireMode = fireMode;
    }
}