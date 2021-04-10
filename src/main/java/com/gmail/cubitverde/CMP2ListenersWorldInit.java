package com.gmail.cubitverde;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldUnloadEvent;

public class CMP2ListenersWorldInit implements Listener {
    @EventHandler
    private void worldInit (WorldInitEvent worldInit) {
        World world = worldInit.getWorld();
        CubMainPlugin2.globalSettings.getWorlds().add(world);
    }

    @EventHandler
    private void worldUnload (WorldUnloadEvent worldUnload) {
        World world = worldUnload.getWorld();
        if (!CubMainPlugin2.plugin.getServer().getWorlds().contains(world)) {
            CubMainPlugin2.globalSettings.getWorlds().remove(world);
        }
    }
}
