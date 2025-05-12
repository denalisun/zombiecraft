package me.denalisun.zombiecraft.Weapons;

public enum WeaponFireMode {
    SEMI_AUTO(0), FULLY_AUTO(1), BURST_FIRE(2);

    private final int FireMode;
    private WeaponFireMode(int fireMode) {
        this.FireMode = fireMode;
    }

    public int getFireMode() {
        return this.FireMode;
    }

    public static WeaponFireMode fromInt(int mode) {
        for (WeaponFireMode f : values()) {
            if (f.getFireMode() == mode) return f;
        }
        return WeaponFireMode.SEMI_AUTO; // default fallback
    }
}