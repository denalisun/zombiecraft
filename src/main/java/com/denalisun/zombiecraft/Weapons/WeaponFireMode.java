package com.denalisun.zombiecraft.Weapons;

public enum WeaponFireMode {
    SEMI_AUTO(0), FULLY_AUTO(1), BURST_FIRE(2);

    private final int FireMode;
    private WeaponFireMode(int fireMode) {
        this.FireMode = fireMode;
    }

    public int GetFireMode() {
        return this.FireMode;
    }
}