package com.gmail.cubitverde;

import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class CMP2Loop1s extends BukkitRunnable {
    @Override
    public void run() {
        HashMap<String, Integer> oldBoosterTime = CubMainPlugin2.boosterTime;
        for (String perm : oldBoosterTime.keySet()) {
            int time = CubMainPlugin2.boosterTime.get(perm);
            if (time < 1) {
                CubMainPlugin2.boosterTime.remove(perm);
                CubMainPlugin2.boosterBoost.remove(perm);
            } else {
                time--;
                CubMainPlugin2.boosterTime.put(perm, time);
            }
        }
    }
}
