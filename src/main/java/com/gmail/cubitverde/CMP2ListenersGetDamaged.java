package com.gmail.cubitverde;

import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class CMP2ListenersGetDamaged implements Listener {
    @EventHandler
    private void damaged (EntityDamageByEntityEvent damage) {
        if (damage.getDamager() instanceof Firework && damage.getEntity() instanceof Player) {
            Firework firework = (Firework) damage.getDamager();
            if (CubMainPlugin2.blockFireworks.contains(firework)) {
                damage.setCancelled(true);
            }
        }
    }
}
