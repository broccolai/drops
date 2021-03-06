package com.gmail.cubitverde;

import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;

public class CMP2ListenersExplode implements Listener {
    @EventHandler
    private void blockExplode (BlockExplodeEvent blockExplode) {
        if (blockExplode.isCancelled()) {
            return;
        }

        Block brokenBlock = blockExplode.getBlock();
        ArrayList<ItemStack> drops = new ArrayList<>();

        if (brokenBlock.getBlockData() instanceof Ageable) {
            Ageable ageable = (Ageable) brokenBlock.getBlockData();
            if (ageable.getAge() == ageable.getMaximumAge()) {

            } else {
                if (brokenBlock.getType().equals(Material.SUGAR_CANE)) {

                } else {
                    return;
                }
            }
        }
        for (Material material : CubMainPlugin2.editedBlocks.keySet()) {
            if (brokenBlock.getType().equals(material)) {
                CMP2Block block = CubMainPlugin2.editedBlocks.get(material);

                if (block.getVanillaDrops() != null && block.getVanillaDrops().contains(brokenBlock.getWorld())) {
                    brokenBlock.setType(Material.GLASS);
                    ItemStack uselessTool = new ItemStack(Material.GLASS);
                    brokenBlock.breakNaturally(uselessTool);
                }

                if (block.getCustomDrops() != null) {
                    for (CMP2CustomDrop tempDrop : block.getCustomDrops()) {
                        if (!(tempDrop.getSpawnerDrop()) && CubMainPlugin2.spawnerBlocks.contains(brokenBlock)) {
                            continue;
                        }
                        if (!(tempDrop.getNaturalDrop()) && !(CubMainPlugin2.spawnerBlocks.contains(brokenBlock))) {
                            continue;
                        }

                        double tempDouble = Math.random();
                        double chance = tempDrop.getDropChance();

                        if ((tempDouble * 10000) < chance) {
                            if (tempDrop.getWorldOverride()) {
                                if (tempDrop.getWorlds() != null && tempDrop.getWorlds().contains(brokenBlock.getWorld())) {

                                } else {
                                    continue;
                                }
                            } else {
                                if (CubMainPlugin2.globalSettings.getWorlds() != null && CubMainPlugin2.globalSettings.getWorlds().contains(brokenBlock.getWorld())) {

                                } else {
                                    continue;
                                }
                            }
                            if (tempDrop.getDrop().getType().equals(Material.BARRIER)) {

                            } else {
                                drops.add(tempDrop.getDrop());
                            }
                            if (tempDrop.getEffect()) {
                                Firework firework = (Firework) brokenBlock.getLocation().getWorld().spawnEntity(brokenBlock.getLocation(), EntityType.FIREWORK);
                                FireworkMeta fireworkMeta = firework.getFireworkMeta();

                                fireworkMeta.setPower(0);
                                fireworkMeta.addEffect(FireworkEffect.builder().withColor(tempDrop.getEffectColor()).build());

                                firework.setFireworkMeta(fireworkMeta);
                                firework.detonate();
                                CubMainPlugin2.blockFireworks.add(firework);
                            }
                            if (tempDrop.getCommands() != null && tempDrop.getCommands().size() != 0) {
                                for (String tempStr1 : tempDrop.getCommands()) {
                                    String tempStr1Final = tempStr1;
                                    for (int i = 0; i < tempStr1Final.length() - 2; i++) {
                                        if (tempStr1Final.charAt(i) == '%' && tempStr1Final.charAt(i + 1) == 'c' && tempStr1Final.charAt(i + 2) == '%') {
                                            String tempStrA = tempStr1Final.substring(0, i);
                                            String tempStrB = "";
                                            if (tempStr1Final.length() > i + 2) {
                                                tempStrB = tempStr1Final.substring(i + 3);
                                            }
                                            tempStr1Final = tempStrA + blockExplode.getBlock().getX() + " " + blockExplode.getBlock().getY() + " " + blockExplode.getBlock().getZ() + tempStrB;
                                        }
                                    }
                                    boolean hasAll = false;
                                    for (int i = 0; i < tempStr1Final.length() - 2; i++) {
                                        if (tempStr1Final.charAt(i) == '%' && tempStr1Final.charAt(i + 1) == 'a' && tempStr1Final.charAt(i + 2) == '%') {
                                            hasAll = true;
                                            String tempStrA = tempStr1Final.substring(0, i);
                                            String tempStrB = "";
                                            if (tempStr1Final.length() > i + 2) {
                                                tempStrB = tempStr1Final.substring(i + 3);
                                            }
                                            for (Player onlinePlayer : CubMainPlugin2.plugin.getServer().getOnlinePlayers()) {
                                                tempStr1Final = tempStrA + onlinePlayer.getName() + tempStrB;
                                                CubMainPlugin2.plugin.getServer().dispatchCommand(CubMainPlugin2.plugin.getServer().getConsoleSender(), tempStr1Final);
                                            }
                                        }
                                    }
                                    if (!hasAll) {
                                        CubMainPlugin2.plugin.getServer().dispatchCommand(CubMainPlugin2.plugin.getServer().getConsoleSender(), tempStr1Final);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (ItemStack item : drops) {
            brokenBlock.getWorld().dropItemNaturally(brokenBlock.getLocation(), item);
        }
    }
}
