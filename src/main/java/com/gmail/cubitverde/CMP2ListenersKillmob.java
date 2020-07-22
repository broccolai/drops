package com.gmail.cubitverde;

import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;

public class CMP2ListenersKillmob implements Listener {
    @EventHandler
    private void entityDeath (EntityDeathEvent death) {
        LivingEntity deadEntity = death.getEntity();
        Player player = death.getEntity().getKiller();

        for (String tempStr : CubMainPlugin2.mobNames.keySet()) {
            String newString1 = tempStr;
            newString1 = newString1.substring(2).toLowerCase();
            String newString2;
            if (CubMainPlugin2.plugin.getServer().getVersion().contains("1.16") && newString1.contains("zombie pigman")) {
                newString2 = "zombified_piglin";
            } else if (CubMainPlugin2.plugin.getServer().getVersion().contains("1.16") && newString1.contains("snowman")) {
                newString2 = "snow_golem";
            } else {
                newString2 = newString1.replace(' ', '_');
            }
            if (deadEntity.getType().getName() != null && deadEntity.getType().getName().equals(newString2)) {
                CMP2Mob tempMob = CubMainPlugin2.mobNames.get(tempStr);
                ArrayList<ItemStack> Drops = new ArrayList<>();

                if (tempMob.getVanillaDrops() != null && tempMob.getVanillaDrops().contains(deadEntity.getWorld())) {
                    death.getDrops().clear();
                }

                if (!CubMainPlugin2.afkFarm && player == null) {
                    return;
                }

                if (tempMob.getCustomDrops() != null) {
                    for (CMP2CustomDrop tempDrop : tempMob.getCustomDrops()) {
                        if (!(tempDrop.getSpawnerDrop()) && CubMainPlugin2.spawnerMobs.contains(deadEntity.getUniqueId())) {
                            continue;
                        }
                        if (!(tempDrop.getEggDrop()) && CubMainPlugin2.eggMobs.contains(deadEntity.getUniqueId())) {
                            continue;
                        }
                        if (!(tempDrop.getBredDrop()) && CubMainPlugin2.bredMobs.contains(deadEntity.getUniqueId())) {
                            continue;
                        }
                        if (!(tempDrop.getNaturalDrop()) && !(CubMainPlugin2.spawnerMobs.contains(deadEntity.getUniqueId()) || CubMainPlugin2.eggMobs.contains(deadEntity.getUniqueId()) || CubMainPlugin2.bredMobs.contains(deadEntity.getUniqueId()))) {
                            continue;
                        }

                        double tempDouble = Math.random();
                        double chance = tempDrop.getDropChance();
                        if (player != null) {
                            for (String boostPerm : CubMainPlugin2.boosterTime.keySet()) {
                                if (player.hasPermission(boostPerm) || boostPerm.equals("global")) {
                                    if (CubMainPlugin2.boosterBoost.get(boostPerm) > 0) {
                                        chance = chance * CubMainPlugin2.boosterBoost.get(boostPerm);
                                    }
                                }
                            }
                        }
                        if (player != null) {
                            if (CubMainPlugin2.globalSettings.getLootingAll()) {
                                if (player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_MOBS) > 0) {
                                    int level = player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_MOBS);
                                    if (CubMainPlugin2.globalSettings.getLootingMultiplier()) {
                                        for (int i = 0; i < level; i++) {
                                            chance = chance * (((double) CubMainPlugin2.globalSettings.getLootingM()) / 10);
                                        }
                                    } else {
                                        chance = chance + (CubMainPlugin2.globalSettings.getLootingA() * level * 10);
                                    }
                                }
                            }
                        }
                        if ((tempDouble * 10000) < chance) {
                            if (tempDrop.getWorldOverride()) {
                                if (tempDrop.getWorlds() != null && tempDrop.getWorlds().contains(death.getEntity().getWorld())) {

                                } else {
                                    continue;
                                }
                            } else {
                                if (CubMainPlugin2.globalSettings.getWorlds() != null && CubMainPlugin2.globalSettings.getWorlds().contains(death.getEntity().getWorld())) {

                                } else {
                                    continue;
                                }
                            }
                            if (tempDrop.getDrop().getType().equals(Material.BARRIER)) {

                            } else {
                                Drops.add(tempDrop.getDrop());
                            }
                            if (tempDrop.getEffect()) {
                                Firework firework = (Firework) deadEntity.getLocation().getWorld().spawnEntity(deadEntity.getLocation(), EntityType.FIREWORK);
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
                                        if (tempStr1Final.charAt(i) == '%' && tempStr1Final.charAt(i + 1) == 'p' && tempStr1Final.charAt(i + 2) == '%') {
                                            String tempStrA = tempStr1Final.substring(0, i);
                                            String tempStrB = "";
                                            if (tempStr1Final.length() > i + 2) {
                                                tempStrB = tempStr1Final.substring(i + 3);
                                            }
                                            tempStr1Final = tempStrA + player.getName() + tempStrB;
                                        }
                                    }
                                    for (int i = 0; i < tempStr1Final.length() - 2; i++) {
                                        if (tempStr1Final.charAt(i) == '%' && tempStr1Final.charAt(i + 1) == 'c' && tempStr1Final.charAt(i + 2) == '%') {
                                            String tempStrA = tempStr1Final.substring(0, i);
                                            String tempStrB = "";
                                            if (tempStr1Final.length() > i + 2) {
                                                tempStrB = tempStr1Final.substring(i + 3);
                                            }
                                            tempStr1Final = tempStrA + deadEntity.getLocation().getX() + " " + deadEntity.getLocation().getY() + " " + deadEntity.getLocation().getZ() + tempStrB;
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
                    for (ItemStack item : Drops) {
                        deadEntity.getWorld().dropItem(deadEntity.getLocation(), item);
                    }
                }
            }
        }
    }
}
