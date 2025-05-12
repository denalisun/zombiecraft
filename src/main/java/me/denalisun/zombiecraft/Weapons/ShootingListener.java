package me.denalisun.zombiecraft.Weapons;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;

public class ShootingListener implements Listener {
    private final GunManager gunManager;
    private final JavaPlugin plugin;
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();
    private final HashSet<UUID> shootingPlayers = new HashSet<>();
    
    public ShootingListener(GunManager gunManager, JavaPlugin plugin) {
        this.gunManager = gunManager;
        this.plugin = plugin;
    }

    @EventHandler
    public void onShoot(PlayerInteractEvent e) {
        if (!e.hasItem()) return;
        if (!e.getAction().equals(Action.RIGHT_CLICK_AIR) && !e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        e.setCancelled(true);

        Player player = e.getPlayer();
        Gun gun = Gun.fromItemStack(e.getItem());
        if (gun == null) return;

        UUID uuid = player.getUniqueId();
        System.out.println(shootingPlayers.contains(uuid));
        if (shootingPlayers.contains(uuid)) return;

        if (gun.getFireMode() == WeaponFireMode.FULLY_AUTO) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    System.out.println("So sigma: " + player.isHandRaised());
                    if (!player.isOnline() || !player.isHandRaised() ||
                      !Gun.fromItemStack(player.getInventory().getItemInMainHand()).equals(gun)) {
                        shootingPlayers.remove(uuid);
                        cancel();
                        return;
                    }

                    long now = System.currentTimeMillis();
                    long lastShot = cooldowns.getOrDefault(uuid, 0L);
                    long delay = (long) (gun.getFireRate() * 1000f);

                    if (now - lastShot < delay) return;
                    cooldowns.put(uuid, now);
                }
            }.runTaskTimer(plugin, 0L, 1L);
        }
    }

    public void fireHitscan(Gun gun, Player player) {
        player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1f, 1.5f);
        player.getWorld().spawnParticle(Particle.SMOKE, player.getEyeLocation(), 1);

        // Recoil
        Location recoilLoc = player.getLocation();
        recoilLoc.setPitch(recoilLoc.getPitch() + (-gun.getRecoil()));
        player.teleport(recoilLoc); //TODO: Replace with Packets using NMS or ProtocolLib (if it gets updated)

        Location eye = player.getEyeLocation();
        RayTraceResult result = player.getWorld().rayTraceEntities(eye, eye.getDirection(), gun.getRange(), entity -> entity != player);
        Location loc = eye.clone();
        for (double i = 0; i < gun.getRange(); i += 0.5) {
            loc.add(eye.getDirection().clone().multiply(0.5));
            player.getWorld().spawnParticle(Particle.SMOKE, loc, 1, 0, 0, 0, 0);
            if (result != null && loc.distanceSquared(result.getHitPosition().toLocation(loc.getWorld())) < 0.5) {
                break;
            }
        }

        if (result != null && result.getHitEntity() instanceof LivingEntity target) {
            target.damage(gun.getDamage(), player);
            player.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, result.getHitPosition().toLocation(player.getWorld()), 1);
        }
    }
}
