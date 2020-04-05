package com.gmail.cubitverde;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class CMP2ListenersPlaceBlock implements Listener {
    @EventHandler
    private void placeBlock (BlockPlaceEvent blockPlace) {
        CubMainPlugin2.spawnerBlocks.add(blockPlace.getBlock());
    }
}
