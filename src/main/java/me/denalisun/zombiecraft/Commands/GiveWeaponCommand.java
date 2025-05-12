package me.denalisun.zombiecraft.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.denalisun.zombiecraft.Weapons.Gun;
import me.denalisun.zombiecraft.Weapons.GunManager;

public class GiveWeaponCommand implements CommandExecutor {
    private GunManager _gunManager;
    
    public GiveWeaponCommand(GunManager _gunManager) {
        this._gunManager = _gunManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        System.out.println(args.length);
        if (sender instanceof Player && args.length >= 1) {
            Player player = (Player)sender;

            ItemStack gunStack;
            Gun gun = this._gunManager.getGunByID(args[0]);
            if (gun == null)
                return true;

            gunStack = gun.getGunStack();

            player.getInventory().addItem(gunStack);
        }
        
        return true;
    }
}
