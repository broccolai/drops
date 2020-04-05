package com.gmail.cubitverde;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CMP2ListenersSpawnmob implements Listener {
    @EventHandler
    private void entitySpawn (CreatureSpawnEvent spawn) {
        if (spawn.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.SPAWNER)) {
            CubMainPlugin2.spawnerMobs.add(spawn.getEntity().getUniqueId());
        }
        if (spawn.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.SPAWNER_EGG)) {
            CubMainPlugin2.eggMobs.add(spawn.getEntity().getUniqueId());
        }
        if (spawn.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.BREEDING)) {
            CubMainPlugin2.bredMobs.add(spawn.getEntity().getUniqueId());
        }
        if (spawn.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.NATURAL) || spawn.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.DEFAULT)) {
            CubMainPlugin2.naturalMobs.add(spawn.getEntity().getUniqueId());
        }
    }
}
