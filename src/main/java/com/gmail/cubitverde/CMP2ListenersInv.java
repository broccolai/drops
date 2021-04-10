package com.gmail.cubitverde;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class CMP2ListenersInv implements Listener {
    @EventHandler
    private void inventoryClick (InventoryClickEvent click) {
        Player player = (Player) click.getWhoClicked();
        if (click.getCurrentItem() == null || click.getCurrentItem().getAmount() == 0) {
            return;
        }

        switch(click.getView().getTitle()) {
            case "§2[CustomDrops] Main menu":
                click.setCancelled(true);
                switch (click.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "§aEdit a mob":
                        Inventory mobSelection = CMP2Utilities.CreateMobSelection(1);
                        player.openInventory(mobSelection);
                        break;
                    case "§aEdit a block":
                        Inventory blockSelection = CMP2Utilities.CreateBlockSelection(1);
                        player.openInventory(blockSelection);
                        break;
                    case "§aGlobal settings":
                        Inventory globalSettings = CMP2Utilities.ListGlobalSettings();
                        player.openInventory(globalSettings);
                        break;
                }
                break;
            case "§2[CustomDrops] Mob selection":
                click.setCancelled(true);
                switch (click.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "§aBack to main menu":
                        player.performCommand("cdrops");
                        break;
                    case "§aNext page":
                        Inventory mobSelection1 = CMP2Utilities.CreateMobSelection(2);
                        player.openInventory(mobSelection1);
                        break;
                    case "§aPrevious page":
                        Inventory mobSelection2 = CMP2Utilities.CreateMobSelection(1);
                        player.openInventory(mobSelection2);
                        break;
                    default:
                        for (String tempStr : CubMainPlugin2.eggsNames) {
                            if (click.getCurrentItem().getItemMeta().getDisplayName().equals(tempStr)) {
                                if (CubMainPlugin2.mobNames.get(tempStr) == null) {
                                    CMP2Mob tempMob = new CMP2Mob();
                                    tempMob.setName(tempStr);
                                    CubMainPlugin2.mobNames.put(tempStr, tempMob);
                                }

                                CMP2Mob chosenMob = CubMainPlugin2.mobNames.get(tempStr);
                                Inventory mobInventory = CMP2Utilities.CreateMobInfo(chosenMob);

                                player.openInventory(mobInventory);
                            }
                        }
                        break;
                }
                break;
            case "§2[CustomDrops] Edit a mob":
                click.setCancelled(true);
                for (String tempStr : CubMainPlugin2.mobNames.keySet()) {
                    if (tempStr.equals(click.getInventory().getItem(0).getItemMeta().getDisplayName().substring(15))) {
                        CMP2Mob tempMob = CubMainPlugin2.mobNames.get(tempStr);
                        switch (click.getCurrentItem().getItemMeta().getDisplayName()) {
                            case "§aBack to main menu":
                                player.performCommand("cdrops");
                                break;
                            case "§aBack to previous menu":
                                Inventory mobInfo = CMP2Utilities.CreateMobSelection(1);
                                player.openInventory(mobInfo);
                                break;
                            case "§aToggle vanilla drops":
                                Inventory listVanillaWorlds = CMP2Utilities.ListVanillaWorlds(player, tempMob, 1);
                                player.openInventory(listVanillaWorlds);
                                break;
                            case "§aCustom drops":
                                CubMainPlugin2.lastPage.put(player.getUniqueId(), 1);
                                Inventory customDropsInv = CMP2Utilities.ListCustomDrops(tempMob, player.getUniqueId());
                                player.openInventory(customDropsInv);
                                break;
                        }
                    }
                }
                break;
            case "§2[CustomDrops] Custom drops":
                click.setCancelled(true);
                String mobName = click.getInventory().getItem(0).getItemMeta().getDisplayName().substring(15);
                CMP2Mob tempMob = CubMainPlugin2.mobNames.get(mobName);
                switch (click.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "§aBack to main menu":
                        player.performCommand("cdrops");
                        break;
                    case "§aBack to previous menu":
                        Inventory mobInventory1 = CMP2Utilities.CreateMobInfo(tempMob);
                        player.openInventory(mobInventory1);
                        break;
                    case "§aNext page":
                        CubMainPlugin2.lastPage.put(player.getUniqueId(), CubMainPlugin2.lastPage.get(player.getUniqueId()) + 1);
                        Inventory customDropsInv1 = CMP2Utilities.ListCustomDrops(tempMob, player.getUniqueId());
                        player.openInventory(customDropsInv1);
                        break;
                    case "§aPrevious page":
                        CubMainPlugin2.lastPage.put(player.getUniqueId(), CubMainPlugin2.lastPage.get(player.getUniqueId()) - 1);
                        Inventory customDropsInv2 = CMP2Utilities.ListCustomDrops(tempMob, player.getUniqueId());
                        player.openInventory(customDropsInv2);
                        break;
                    case "§aAdd new item":
                        Inventory addCustomDrop = CMP2Utilities.AddCustomDropInventory(player, tempMob);
                        player.openInventory(addCustomDrop);
                        break;
                    default:
                        if (click.getSlot() > 8 && click.getSlot() < 45 && click.getCurrentItem().getAmount() != 0) {
                            CMP2CustomDrop tempDrop = null;
                            int tempInt = 0;
                            for (int i = 0; i < 28; i++) {
                                if (click.getSlot() == CMP2Utilities.InventoryInside(54).get(i)) {
                                    if (i  + (28 * (CubMainPlugin2.lastPage.get(player.getUniqueId()) - 1)) < tempMob.getCustomDrops().size()) {
                                        tempDrop = tempMob.getCustomDrops().get(i + (28 * (CubMainPlugin2.lastPage.get(player.getUniqueId()) - 1)));
                                        tempInt = i;
                                        break;
                                    }
                                }
                            }
                            if (tempDrop == null) {
                                return;
                            }
                            Inventory customDropInfo = CMP2Utilities.ShowCustomDropInfo(tempMob, tempDrop, tempInt  + (28 * (CubMainPlugin2.lastPage.get(player.getUniqueId()) - 1)));
                            player.openInventory(customDropInfo);
                        }
                        break;
                }
                break;
            case "§2[CustomDrops] Choose an item":
                click.setCancelled(true);
                String mobName1 = click.getInventory().getItem(0).getItemMeta().getDisplayName().substring(15);
                CMP2Mob tempMob1 = CubMainPlugin2.mobNames.get(mobName1);
                switch (click.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "§aBack to main menu":
                        player.performCommand("cdrops");
                        break;
                    case "§aBack to previous menu":
                        Inventory listDrops = CMP2Utilities.ListCustomDrops(tempMob1, player.getUniqueId());
                        player.openInventory(listDrops);
                        break;
                    default:
                        if (click.getSlot() > 8 && click.getSlot() < 45) {
                            CMP2CustomDrop tempDrop = new CMP2CustomDrop();
                            tempDrop.setDrop(click.getCurrentItem());
                            tempDrop.setDropChance(0);
                            tempDrop.setEffect(false);
                            tempDrop.setEffectColor(Color.LIME);
                            tempDrop.setSpawnerDrop(false);
                            tempDrop.setEggDrop(false);
                            tempDrop.setNaturalDrop(true);
                            tempDrop.setBredDrop(false);
                            tempDrop.setWorldOverride(false);
                            if (tempMob1.getCustomDrops() == null) {
                                ArrayList<CMP2CustomDrop> tempArr = new ArrayList<>();
                                tempArr.add(tempDrop);
                                tempMob1.setCustomDrops(tempArr);
                            } else {
                                ArrayList<CMP2CustomDrop> tempArr = tempMob1.getCustomDrops();
                                tempArr.add(tempDrop);
                                tempMob1.setCustomDrops(tempArr);
                            }
                            CubMainPlugin2.mobNames.put(mobName1, tempMob1);

                            CubMainPlugin2.lastPage.put(player.getUniqueId(), 1);
                            Inventory customDropsInv1 = CMP2Utilities.ListCustomDrops(tempMob1, player.getUniqueId());
                            player.openInventory(customDropsInv1);
                        }
                        break;
                }
                break;
            case "§2[CustomDrops] Drop settings": {
                click.setCancelled(true);
                String mobName2 = click.getInventory().getItem(0).getItemMeta().getDisplayName().substring(15);
                CMP2Mob tempMob2 = CubMainPlugin2.mobNames.get(mobName2);
                ArrayList<CMP2CustomDrop> tempDropArr = tempMob2.getCustomDrops();
                String dropNumberString = click.getInventory().getItem(0).getItemMeta().getLore().get(0).substring(17);
                int dropNumber = Integer.parseInt(dropNumberString);
                CMP2CustomDrop tempDrop = tempMob2.getCustomDrops().get(dropNumber);
                switch (click.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "§aBack to main menu":
                        player.performCommand("cdrops");
                        break;
                    case "§aBack to previous menu":
                        Inventory listDrops = CMP2Utilities.ListCustomDrops(tempMob2, player.getUniqueId());
                        player.openInventory(listDrops);
                        break;
                    case "§aEdit commands":
                        Inventory commandsList = CMP2Utilities.CommandsList(tempMob2, tempDrop, dropNumber);
                        player.openInventory(commandsList);
                        break;
                    case "§aMore settings":
                        Inventory moreDropsOpts = CMP2Utilities.MoreDropOptions(tempMob2, tempDrop, dropNumber);
                        player.openInventory(moreDropsOpts);
                        break;
                    case "§aDrop worlds list":
                        if (click.isShiftClick() && click.getCurrentItem().getItemMeta().getLore().get(0).equals("§aEnabled: Overrides global settings worlds")) {
                            Inventory listWorlds = CMP2Utilities.ListDropWorlds(player, tempMob2, tempDrop, dropNumber, 1);
                            player.openInventory(listWorlds);
                        } else {
                            if (click.getCurrentItem().getItemMeta().getLore().get(0).equals("§cDisabled: Using global settings worlds")) {
                                tempDrop.setWorldOverride(true);
                            } else {
                                tempDrop.setWorldOverride(false);
                            }
                            tempDropArr.set(dropNumber, tempDrop);
                            tempMob2.setCustomDrops(tempDropArr);
                            CubMainPlugin2.mobNames.put(mobName2, tempMob2);
                            Inventory customDropsInv2 = CMP2Utilities.ShowCustomDropInfo(tempMob2, tempDrop, dropNumber);
                            player.openInventory(customDropsInv2);
                        }
                        break;
                    case "§aDelete drop":
                        if (click.isRightClick() && click.isShiftClick()) {
                            tempDropArr.remove(dropNumber);
                            tempMob2.setCustomDrops(tempDropArr);
                            CubMainPlugin2.mobNames.put(mobName2, tempMob2);
                            Inventory customDropsInv2 = CMP2Utilities.ListCustomDrops(tempMob2, player.getUniqueId());
                            player.openInventory(customDropsInv2);
                        }
                        break;
                    case "§cDecrease chance by 0.001%":
                        tempDropArr.get(dropNumber).setDropChance(tempDrop.getDropChance() - 1);
                        tempMob2.setCustomDrops(tempDropArr);
                        CubMainPlugin2.mobNames.put(mobName2, tempMob2);
                        Inventory customDropInfo0 = CMP2Utilities.ShowCustomDropInfo(tempMob2, tempDrop, dropNumber);
                        player.openInventory(customDropInfo0);
                        break;
                    case "§cDecrease chance by 0.01%":
                        tempDropArr.get(dropNumber).setDropChance(tempDrop.getDropChance() - 10);
                        tempMob2.setCustomDrops(tempDropArr);
                        CubMainPlugin2.mobNames.put(mobName2, tempMob2);
                        Inventory customDropInfo01 = CMP2Utilities.ShowCustomDropInfo(tempMob2, tempDrop, dropNumber);
                        player.openInventory(customDropInfo01);
                        break;
                    case "§cDecrease chance by 0.1%":
                        tempDropArr.get(dropNumber).setDropChance(tempDrop.getDropChance() - 100);
                        tempMob2.setCustomDrops(tempDropArr);
                        CubMainPlugin2.mobNames.put(mobName2, tempMob2);
                        Inventory customDropInfo1 = CMP2Utilities.ShowCustomDropInfo(tempMob2, tempDrop, dropNumber);
                        player.openInventory(customDropInfo1);
                        break;
                    case "§cDecrease chance by 1%":
                        tempDropArr.get(dropNumber).setDropChance(tempDrop.getDropChance() - 1000);
                        tempMob2.setCustomDrops(tempDropArr);
                        CubMainPlugin2.mobNames.put(mobName2, tempMob2);
                        Inventory customDropInfo2 = CMP2Utilities.ShowCustomDropInfo(tempMob2, tempDrop, dropNumber);
                        player.openInventory(customDropInfo2);
                        break;
                    case "§aIncrease chance by 0.001%":
                        tempDropArr.get(dropNumber).setDropChance(tempDrop.getDropChance() + 1);
                        tempMob2.setCustomDrops(tempDropArr);
                        CubMainPlugin2.mobNames.put(mobName2, tempMob2);
                        Inventory customDropInfo15 = CMP2Utilities.ShowCustomDropInfo(tempMob2, tempDrop, dropNumber);
                        player.openInventory(customDropInfo15);
                        break;
                    case "§aIncrease chance by 0.01%":
                        tempDropArr.get(dropNumber).setDropChance(tempDrop.getDropChance() + 10);
                        tempMob2.setCustomDrops(tempDropArr);
                        CubMainPlugin2.mobNames.put(mobName2, tempMob2);
                        Inventory customDropInfo151 = CMP2Utilities.ShowCustomDropInfo(tempMob2, tempDrop, dropNumber);
                        player.openInventory(customDropInfo151);
                        break;
                    case "§aIncrease chance by 0.1%":
                        tempDropArr.get(dropNumber).setDropChance(tempDrop.getDropChance() + 100);
                        tempMob2.setCustomDrops(tempDropArr);
                        CubMainPlugin2.mobNames.put(mobName2, tempMob2);
                        Inventory customDropInfo3 = CMP2Utilities.ShowCustomDropInfo(tempMob2, tempDrop, dropNumber);
                        player.openInventory(customDropInfo3);
                        break;
                    case "§aIncrease chance by 1%":
                        tempDropArr.get(dropNumber).setDropChance(tempDrop.getDropChance() + 1000);
                        tempMob2.setCustomDrops(tempDropArr);
                        CubMainPlugin2.mobNames.put(mobName2, tempMob2);
                        Inventory customDropInfo4 = CMP2Utilities.ShowCustomDropInfo(tempMob2, tempDrop, dropNumber);
                        player.openInventory(customDropInfo4);
                        break;
                    case "§aSpawner drops":
                        if (click.getCurrentItem().getType().equals(Material.LIME_CONCRETE)) {
                            tempDrop.setSpawnerDrop(false);
                        }
                        if (click.getCurrentItem().getType().equals(Material.RED_CONCRETE)) {
                            tempDrop.setSpawnerDrop(true);
                        }
                        tempDropArr.set(dropNumber, tempDrop);
                        tempMob2.setCustomDrops(tempDropArr);
                        CubMainPlugin2.mobNames.put(mobName2, tempMob2);
                        Inventory customDropInfo5 = CMP2Utilities.ShowCustomDropInfo(tempMob2, tempDrop, dropNumber);
                        player.openInventory(customDropInfo5);
                        break;
                    case "§aEgg drops":
                        if (click.getCurrentItem().getType().equals(Material.LIME_CONCRETE)) {
                            tempDrop.setEggDrop(false);
                        }
                        if (click.getCurrentItem().getType().equals(Material.RED_CONCRETE)) {
                            tempDrop.setEggDrop(true);
                        }
                        tempDropArr.set(dropNumber, tempDrop);
                        tempMob2.setCustomDrops(tempDropArr);
                        CubMainPlugin2.mobNames.put(mobName2, tempMob2);
                        Inventory customDropInfo6 = CMP2Utilities.ShowCustomDropInfo(tempMob2, tempDrop, dropNumber);
                        player.openInventory(customDropInfo6);
                        break;
                    case "§aNatural drops":
                        if (click.getCurrentItem().getType().equals(Material.LIME_CONCRETE)) {
                            tempDrop.setNaturalDrop(false);
                        }
                        if (click.getCurrentItem().getType().equals(Material.RED_CONCRETE)) {
                            tempDrop.setNaturalDrop(true);
                        }
                        tempDropArr.set(dropNumber, tempDrop);
                        tempMob2.setCustomDrops(tempDropArr);
                        CubMainPlugin2.mobNames.put(mobName2, tempMob2);
                        Inventory customDropInfo7 = CMP2Utilities.ShowCustomDropInfo(tempMob2, tempDrop, dropNumber);
                        player.openInventory(customDropInfo7);
                        break;
                    case "§aBreeding drops":
                        if (click.getCurrentItem().getType().equals(Material.LIME_CONCRETE)) {
                            tempDrop.setBredDrop(false);
                        }
                        if (click.getCurrentItem().getType().equals(Material.RED_CONCRETE)) {
                            tempDrop.setBredDrop(true);
                        }
                        tempDropArr.set(dropNumber, tempDrop);
                        tempMob2.setCustomDrops(tempDropArr);
                        CubMainPlugin2.mobNames.put(mobName2, tempMob2);
                        Inventory customDropInfo71 = CMP2Utilities.ShowCustomDropInfo(tempMob2, tempDrop, dropNumber);
                        player.openInventory(customDropInfo71);
                        break;
                    case "§aDrop effect":
                        if (click.getCurrentItem().getType().equals(Material.LIME_CONCRETE)) {
                            tempDrop.setEffect(false);
                        }
                        if (click.getCurrentItem().getType().equals(Material.RED_CONCRETE)) {
                            tempDrop.setEffect(true);
                        }
                        tempDropArr.set(dropNumber, tempDrop);
                        tempMob2.setCustomDrops(tempDropArr);
                        CubMainPlugin2.mobNames.put(mobName2, tempMob2);
                        Inventory customDropInfo8 = CMP2Utilities.ShowCustomDropInfo(tempMob2, tempDrop, dropNumber);
                        player.openInventory(customDropInfo8);
                        break;
                    case "§aCurrent color: Green":
                        tempDrop.setEffectColor(Color.AQUA);
                        tempDropArr.set(dropNumber, tempDrop);
                        tempMob2.setCustomDrops(tempDropArr);
                        CubMainPlugin2.mobNames.put(mobName2, tempMob2);
                        Inventory customDropInfo9 = CMP2Utilities.ShowCustomDropInfo(tempMob2, tempDrop, dropNumber);
                        player.openInventory(customDropInfo9);
                        break;
                    case "§bCurrent color: Blue":
                        tempDrop.setEffectColor(Color.PURPLE);
                        tempDropArr.set(dropNumber, tempDrop);
                        tempMob2.setCustomDrops(tempDropArr);
                        CubMainPlugin2.mobNames.put(mobName2, tempMob2);
                        Inventory customDropInfo10 = CMP2Utilities.ShowCustomDropInfo(tempMob2, tempDrop, dropNumber);
                        player.openInventory(customDropInfo10);
                        break;
                    case "§5Current color: Purple":
                        tempDrop.setEffectColor(Color.BLACK);
                        tempDropArr.set(dropNumber, tempDrop);
                        tempMob2.setCustomDrops(tempDropArr);
                        CubMainPlugin2.mobNames.put(mobName2, tempMob2);
                        Inventory customDropInfo17 = CMP2Utilities.ShowCustomDropInfo(tempMob2, tempDrop, dropNumber);
                        player.openInventory(customDropInfo17);
                        break;
                    case "§8Current color: Black":
                        tempDrop.setEffectColor(Color.WHITE);
                        tempDropArr.set(dropNumber, tempDrop);
                        tempMob2.setCustomDrops(tempDropArr);
                        CubMainPlugin2.mobNames.put(mobName2, tempMob2);
                        Inventory customDropInfo11 = CMP2Utilities.ShowCustomDropInfo(tempMob2, tempDrop, dropNumber);
                        player.openInventory(customDropInfo11);
                        break;
                    case "§7Current color: White":
                        tempDrop.setEffectColor(Color.RED);
                        tempDropArr.set(dropNumber, tempDrop);
                        tempMob2.setCustomDrops(tempDropArr);
                        CubMainPlugin2.mobNames.put(mobName2, tempMob2);
                        Inventory customDropInfo12 = CMP2Utilities.ShowCustomDropInfo(tempMob2, tempDrop, dropNumber);
                        player.openInventory(customDropInfo12);
                        break;
                    case "§4Current color: Red":
                        tempDrop.setEffectColor(Color.ORANGE);
                        tempDropArr.set(dropNumber, tempDrop);
                        tempMob2.setCustomDrops(tempDropArr);
                        CubMainPlugin2.mobNames.put(mobName2, tempMob2);
                        Inventory customDropInfo13 = CMP2Utilities.ShowCustomDropInfo(tempMob2, tempDrop, dropNumber);
                        player.openInventory(customDropInfo13);
                        break;
                    case "§6Current color: Orange":
                        tempDrop.setEffectColor(Color.YELLOW);
                        tempDropArr.set(dropNumber, tempDrop);
                        tempMob2.setCustomDrops(tempDropArr);
                        CubMainPlugin2.mobNames.put(mobName2, tempMob2);
                        Inventory customDropInfo16 = CMP2Utilities.ShowCustomDropInfo(tempMob2, tempDrop, dropNumber);
                        player.openInventory(customDropInfo16);
                        break;
                    case "§eCurrent color: Yellow":
                        tempDrop.setEffectColor(Color.LIME);
                        tempDropArr.set(dropNumber, tempDrop);
                        tempMob2.setCustomDrops(tempDropArr);
                        CubMainPlugin2.mobNames.put(mobName2, tempMob2);
                        Inventory customDropInfo14 = CMP2Utilities.ShowCustomDropInfo(tempMob2, tempDrop, dropNumber);
                        player.openInventory(customDropInfo14);
                        break;
                }
                break; }
            case "§2[CustomDrops] Commands list": {
                click.setCancelled(true);
                String mobName3 = click.getInventory().getItem(0).getItemMeta().getDisplayName().substring(15);
                CMP2Mob tempMob3 = CubMainPlugin2.mobNames.get(mobName3);
                ArrayList<CMP2CustomDrop> tempDropArr1 = tempMob3.getCustomDrops();
                String dropNumberString1 = click.getInventory().getItem(0).getItemMeta().getLore().get(0).substring(17);
                int dropNumber1 = Integer.parseInt(dropNumberString1);
                CMP2CustomDrop tempDrop1 = tempMob3.getCustomDrops().get(dropNumber1);
                switch (click.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "§aBack to main menu":
                        player.performCommand("cdrops");
                        break;
                    case "§aBack to previous menu":
                        Inventory customDropInfo = CMP2Utilities.ShowCustomDropInfo(tempMob3, tempDrop1, dropNumber1);
                        player.openInventory(customDropInfo);
                        break;
                    case "§aClick to add a command":
                        player.closeInventory();
                        player.sendMessage("§2[CustomDrops] §cPlease type the command you want to add.");
                        player.sendMessage("§2[CustomDrops] §cType §a%p% §cto add the player that killed the mob, type §a%a% §cto add all online players, type §a%c% §cto add the coordinates of the mob.");
                        player.sendMessage("§2[CustomDrops] §cYou do not need to type the initial '/'");
                        CubMainPlugin2.addingCommand.put(player.getUniqueId(), dropNumber1);
                        CubMainPlugin2.addingCommandMob.put(player.getUniqueId(), tempMob3);
                        break;
                    default:
                        if (click.getCurrentItem().getType().equals(Material.COMMAND_BLOCK) && click.getCurrentItem().getItemMeta().getLore().get(0).equals("§cShift right click to delete")) {
                            if (click.isShiftClick() && click.isRightClick()) {

                                ArrayList<String> tempArr = tempDrop1.getCommands();
                                int i = 0;
                                for (int j = 0; j < 28; j++) {
                                    if (click.getSlot() == CMP2Utilities.InventoryInside(54).get(j)) {
                                        i = j;
                                        break;
                                    }
                                }
                                tempArr.remove(i);
                                tempDrop1.setCommands(tempArr);

                                ArrayList<CMP2CustomDrop> tempArr1 = tempMob3.getCustomDrops();
                                tempArr1.set(dropNumber1, tempDrop1);
                                tempMob3.setCustomDrops(tempArr1);
                                CubMainPlugin2.mobNames.put(tempMob3.getName(), tempMob3);

                                Inventory commandsList = CMP2Utilities.CommandsList(tempMob3, tempDrop1, dropNumber1);
                                player.openInventory(commandsList);
                            }
                        }
                        break; }
                }
                break;
            case "§2[CustomDrops] Block selection":
                click.setCancelled(true);
                switch (click.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "§aBack to main menu":
                        player.performCommand("cdrops");
                        break;
                    case "§aAdd new block":
                        player.openInventory(CMP2Utilities.AddNewBlock(player));
                        break;
                    case "§aNext page":
                        String pageStr1 = click.getInventory().getItem(52).getItemMeta().getDisplayName().substring(16);
                        int page1 = Integer.parseInt(pageStr1);
                        Inventory inv1 = CMP2Utilities.CreateBlockSelection(page1 + 1);
                        player.openInventory(inv1);
                        break;
                    case "§aPrevious page":
                        String pageStr2 = click.getInventory().getItem(52).getItemMeta().getDisplayName().substring(16);
                        int page2 = Integer.parseInt(pageStr2);
                        Inventory inv2 = CMP2Utilities.CreateBlockSelection(page2 - 1);
                        player.openInventory(inv2);
                        break;
                    default:
                        if (click.getSlot() > 9 && click.getSlot() < 44 && click.getSlot() % 9 > 0 && click.getSlot() % 9 < 8) {
                            if (CubMainPlugin2.editedBlocks.keySet().contains(click.getCurrentItem().getType())) {
                                CMP2Block block = CubMainPlugin2.editedBlocks.get(click.getCurrentItem().getType());

                                Inventory blockInfo = CMP2Utilities.CreateBlockInfo(block);
                                player.openInventory(blockInfo);
                            } else {
                                Material material = click.getCurrentItem().getType();
                                if (material.equals(Material.CARROT)) {
                                    material = Material.CARROTS;
                                } else if (material.equals(Material.BEETROOT)) {
                                    material = Material.BEETROOTS;
                                } else if (material.equals(Material.COCOA_BEANS)) {
                                    material = Material.COCOA;
                                } else if (material.equals(Material.POTATO)) {
                                    material = Material.POTATOES;
                                }
                                if (CubMainPlugin2.editedBlocks.keySet().contains(material)) {
                                    CMP2Block block = CubMainPlugin2.editedBlocks.get(material);

                                    Inventory blockInfo = CMP2Utilities.CreateBlockInfo(block);
                                    player.openInventory(blockInfo);
                                }
                            }
                        }
                        break;
                }
                break;
            case "§2[CustomDrops] Global settings":
                click.setCancelled(true);
                switch (click.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "§aBack to main menu":
                        player.performCommand("cdrops");
                        break;
                    case "§aAdd a multiplier per level":
                        if (click.isRightClick() && click.getSlot() == 12) {
                            CubMainPlugin2.globalSettings.setLootingMultiplier(false);
                            Inventory globalSettings = CMP2Utilities.ListGlobalSettings();
                            player.openInventory(globalSettings);
                        }
                        if (click.isRightClick() && click.getSlot() == 21) {
                            CubMainPlugin2.globalSettings.setFortuneMultiplier(false);
                            Inventory globalSettings = CMP2Utilities.ListGlobalSettings();
                            player.openInventory(globalSettings);
                        }
                        break;
                    case "§aAdd a percentage per level":
                        if (click.isRightClick() && click.getSlot() == 12) {
                            CubMainPlugin2.globalSettings.setLootingMultiplier(true);
                            Inventory globalSettings = CMP2Utilities.ListGlobalSettings();
                            player.openInventory(globalSettings);
                        }
                        if (click.isRightClick() && click.getSlot() == 21) {
                            CubMainPlugin2.globalSettings.setFortuneMultiplier(true);
                            Inventory globalSettings = CMP2Utilities.ListGlobalSettings();
                            player.openInventory(globalSettings);
                        }
                        break;
                    case "§aLooting allowed, click to deny":
                        CubMainPlugin2.globalSettings.setLootingAll(false);
                        Inventory globalSettings = CMP2Utilities.ListGlobalSettings();
                        player.openInventory(globalSettings);
                        break;
                    case "§cLooting denied, click to allow":
                        CubMainPlugin2.globalSettings.setLootingAll(true);
                        Inventory globalSettings1 = CMP2Utilities.ListGlobalSettings();
                        player.openInventory(globalSettings1);
                        break;
                    case "§aFortune allowed, click to deny":
                        CubMainPlugin2.globalSettings.setFortuneAll(false);
                        Inventory globalSettings10 = CMP2Utilities.ListGlobalSettings();
                        player.openInventory(globalSettings10);
                        break;
                    case "§cFortune denied, click to allow":
                        CubMainPlugin2.globalSettings.setFortuneAll(true);
                        Inventory globalSettings11 = CMP2Utilities.ListGlobalSettings();
                        player.openInventory(globalSettings11);
                        break;
                    case "§cDecrease chance by 0.1":
                        if (click.getSlot() == 15) {
                            if (CubMainPlugin2.globalSettings.getLootingMultiplier()) {
                                CubMainPlugin2.globalSettings.setLootingM(CubMainPlugin2.globalSettings.getLootingM() - 1);
                            } else {
                                CubMainPlugin2.globalSettings.setLootingA(CubMainPlugin2.globalSettings.getLootingA() - 1);
                            }
                        }
                        if (click.getSlot() == 24) {
                            if (CubMainPlugin2.globalSettings.getFortuneMultiplier()) {
                                CubMainPlugin2.globalSettings.setFortuneM(CubMainPlugin2.globalSettings.getFortuneM() - 1);
                            } else {
                                CubMainPlugin2.globalSettings.setFortuneA(CubMainPlugin2.globalSettings.getFortuneA() - 1);
                            }
                        }
                        Inventory globalSettings2 = CMP2Utilities.ListGlobalSettings();
                        player.openInventory(globalSettings2);
                        break;
                    case "§cDecrease chance by 1":
                        if (click.getSlot() == 16) {
                            if (CubMainPlugin2.globalSettings.getLootingMultiplier()) {
                                CubMainPlugin2.globalSettings.setLootingM(CubMainPlugin2.globalSettings.getLootingM() - 10);
                            } else {
                                CubMainPlugin2.globalSettings.setLootingA(CubMainPlugin2.globalSettings.getLootingA() - 10);
                            }
                        }
                        if (click.getSlot() == 25) {
                            if (CubMainPlugin2.globalSettings.getFortuneMultiplier()) {
                                CubMainPlugin2.globalSettings.setFortuneM(CubMainPlugin2.globalSettings.getFortuneM() - 10);
                            } else {
                                CubMainPlugin2.globalSettings.setFortuneA(CubMainPlugin2.globalSettings.getFortuneA() - 10);
                            }
                        }
                        Inventory globalSettings3 = CMP2Utilities.ListGlobalSettings();
                        player.openInventory(globalSettings3);
                        break;
                    case "§aIncrease chance by 0.1":
                        if (click.getSlot() == 14) {
                            if (CubMainPlugin2.globalSettings.getLootingMultiplier()) {
                                CubMainPlugin2.globalSettings.setLootingM(CubMainPlugin2.globalSettings.getLootingM() + 1);
                            } else {
                                CubMainPlugin2.globalSettings.setLootingA(CubMainPlugin2.globalSettings.getLootingA() + 1);
                            }
                        }
                        if (click.getSlot() == 23) {
                            if (CubMainPlugin2.globalSettings.getFortuneMultiplier()) {
                                CubMainPlugin2.globalSettings.setFortuneM(CubMainPlugin2.globalSettings.getFortuneM() + 1);
                            } else {
                                CubMainPlugin2.globalSettings.setFortuneA(CubMainPlugin2.globalSettings.getFortuneA() + 1);
                            }
                        }
                        Inventory globalSettings4 = CMP2Utilities.ListGlobalSettings();
                        player.openInventory(globalSettings4);
                        break;
                    case "§aIncrease chance by 1":
                        if (click.getSlot() == 13) {
                            if (CubMainPlugin2.globalSettings.getLootingMultiplier()) {
                                CubMainPlugin2.globalSettings.setLootingM(CubMainPlugin2.globalSettings.getLootingM() + 10);
                            } else {
                                CubMainPlugin2.globalSettings.setLootingA(CubMainPlugin2.globalSettings.getLootingA() + 10);
                            }
                        }
                        if (click.getSlot() == 22) {
                            if (CubMainPlugin2.globalSettings.getFortuneMultiplier()) {
                                CubMainPlugin2.globalSettings.setFortuneM(CubMainPlugin2.globalSettings.getFortuneM() + 10);
                            } else {
                                CubMainPlugin2.globalSettings.setFortuneA(CubMainPlugin2.globalSettings.getFortuneA() + 10);
                            }
                        }
                        Inventory globalSettings5 = CMP2Utilities.ListGlobalSettings();
                        player.openInventory(globalSettings5);
                        break;
                    case "§aList of worlds, click to manage":
                        Inventory globalWorlds = CMP2Utilities.ListGlobalWorlds(player,1);
                        player.openInventory(globalWorlds);
                        break;
                    case "§aAfk farms: Currently enabled":
                        CubMainPlugin2.afkFarm = false;
                        Inventory globalSettings55 = CMP2Utilities.ListGlobalSettings();
                        player.openInventory(globalSettings55);
                        break;
                    case "§cAfk farms: Currently disabled":
                        CubMainPlugin2.afkFarm = true;
                        Inventory globalSettings56 = CMP2Utilities.ListGlobalSettings();
                        player.openInventory(globalSettings56);
                        break;
                    case "§aAdd new booster":
                        player.closeInventory();
                        player.sendMessage("§2[CustomDrops] Adding a new booster:");
                        player.sendMessage("§2Format: §apermission - time - boost");
                        player.sendMessage("§2Example: §aluckyperms.novice - 15m - 1.5");
                        player.sendMessage("§2Time unit must be seconds (§as§2), minutes (§am§2) or hours (§ah§2)");
                        player.sendMessage("§2Permission can be '§aglobal§2' to target all players");
                        player.sendMessage("§2Type §acancel §2to cancel");
                        CubMainPlugin2.addingBooster.add(player.getUniqueId());
                        break;
                    case "§aList current boosters":
                        player.closeInventory();
                        for (String perm : CubMainPlugin2.boosterTime.keySet()) {
                            int time = CubMainPlugin2.boosterTime.get(perm);
                            double boost = CubMainPlugin2.boosterBoost.get(perm);
                            player.sendMessage("§a" + perm + " - " + time / 3600 + "h, " + time % 3600 / 60 + "m, " + time % 60 + "s - " + boost + "x");
                        }
                        break;
                }
                break;
            case "§2[CustomDrops] Worlds list":
                click.setCancelled(true);
                switch (click.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "§aBack to main menu":
                        player.performCommand("cdrops");
                        break;
                    case "§aBack to previous menu":
                        Inventory globalSettings = CMP2Utilities.ListGlobalSettings();
                        player.openInventory(globalSettings);
                        break;
                    case "§aNext page":
                        String pageStr = click.getInventory().getItem(52).getItemMeta().getDisplayName().substring(16);
                        int page = Integer.parseInt(pageStr);
                        Inventory globalWorlds = CMP2Utilities.ListGlobalWorlds(player, page + 1);
                        player.openInventory(globalWorlds);
                        break;
                    case "§aPrevious page":
                        String pageStr1 = click.getInventory().getItem(52).getItemMeta().getDisplayName().substring(16);
                        int page1 = Integer.parseInt(pageStr1);
                        Inventory globalWorlds1 = CMP2Utilities.ListGlobalWorlds(player, page1 - 1);
                        player.openInventory(globalWorlds1);
                        break;
                    default:
                        if (click.getCurrentItem().getType().equals(Material.LIME_BANNER) || click.getCurrentItem().getType().equals(Material.RED_BANNER)) {
                            ArrayList<World> tempArr;
                            if (CubMainPlugin2.globalSettings.getWorlds() != null) {
                                tempArr = CubMainPlugin2.globalSettings.getWorlds();
                            } else {
                                tempArr = new ArrayList<>();
                            }
                            World world = player.getServer().getWorld(click.getCurrentItem().getItemMeta().getDisplayName());
                            if (click.getCurrentItem().getType().equals(Material.LIME_BANNER)) {
                                tempArr.remove(world);
                            } else {
                                tempArr.add(world);
                            }
                            CubMainPlugin2.globalSettings.setWorlds(tempArr);
                            String pageStr2 = click.getInventory().getItem(52).getItemMeta().getDisplayName().substring(16);
                            int page2 = Integer.parseInt(pageStr2);
                            Inventory globalWorlds2 = CMP2Utilities.ListGlobalWorlds(player, page2);
                            player.openInventory(globalWorlds2);
                        }
                        break;
                }
                break;
            case "§2[CustomDrops] Drop worlds": {
                click.setCancelled(true);
                String mobName4 = click.getInventory().getItem(0).getItemMeta().getDisplayName().substring(15);
                CMP2Mob tempMob4 = CubMainPlugin2.mobNames.get(mobName4);
                ArrayList<CMP2CustomDrop> tempDropArr2 = tempMob4.getCustomDrops();
                String dropNumberString2 = click.getInventory().getItem(0).getItemMeta().getLore().get(0).substring(17);
                int dropNumber2 = Integer.parseInt(dropNumberString2);
                CMP2CustomDrop tempDrop2 = tempMob4.getCustomDrops().get(dropNumber2);
                switch (click.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "§aBack to main menu":
                        player.performCommand("cdrops");
                        break;
                    case "§aBack to previous menu":
                        Inventory dropInfo = CMP2Utilities.ShowCustomDropInfo(tempMob4, tempDrop2, dropNumber2);
                        player.openInventory(dropInfo);
                        break;
                    case "§aNext page":
                        String pageStr = click.getInventory().getItem(52).getItemMeta().getDisplayName().substring(16);
                        int page = Integer.parseInt(pageStr);
                        Inventory dropWorlds = CMP2Utilities.ListDropWorlds(player, tempMob4, tempDrop2, dropNumber2, page + 1);
                        player.openInventory(dropWorlds);
                        break;
                    case "§aPrevious page":
                        String pageStr1 = click.getInventory().getItem(52).getItemMeta().getDisplayName().substring(16);
                        int page1 = Integer.parseInt(pageStr1);
                        Inventory dropWorlds1 = CMP2Utilities.ListDropWorlds(player, tempMob4, tempDrop2, dropNumber2, page1 - 1);
                        player.openInventory(dropWorlds1);
                        break;
                    default:
                        if (click.getCurrentItem().getType().equals(Material.LIME_BANNER) || click.getCurrentItem().getType().equals(Material.RED_BANNER)) {
                            ArrayList<World> tempArr;
                            if (tempDrop2.getWorlds() != null) {
                                tempArr = tempDrop2.getWorlds();
                            } else {
                                tempArr = new ArrayList<>();
                            }
                            World world = player.getServer().getWorld(click.getCurrentItem().getItemMeta().getDisplayName());
                            if (click.getCurrentItem().getType().equals(Material.LIME_BANNER)) {
                                tempArr.remove(world);
                            } else {
                                tempArr.add(world);
                            }
                            tempDrop2.setWorlds(tempArr);
                            tempDropArr2.set(dropNumber2, tempDrop2);
                            tempMob4.setCustomDrops(tempDropArr2);
                            CubMainPlugin2.mobNames.put(tempMob4.getName(), tempMob4);
                            String pageStr2 = click.getInventory().getItem(52).getItemMeta().getDisplayName().substring(16);
                            int page2 = Integer.parseInt(pageStr2);
                            Inventory globalWorlds2 = CMP2Utilities.ListDropWorlds(player, tempMob4, tempDrop2, dropNumber2, page2);
                            player.openInventory(globalWorlds2);
                        }
                        break;
                }
                break; }
            case "§2[CustomDrops] Vanilla drops":
                click.setCancelled(true);
                String mobName5 = click.getInventory().getItem(0).getItemMeta().getDisplayName().substring(15);
                CMP2Mob tempMob5 = CubMainPlugin2.mobNames.get(mobName5);
                switch (click.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "§aBack to main menu":
                        player.performCommand("cdrops");
                        break;
                    case "§aBack to previous menu":
                        Inventory mobInventory = CMP2Utilities.CreateMobInfo(tempMob5);
                        player.openInventory(mobInventory);
                        break;
                    case "§aNext page":
                        String pageStr = click.getInventory().getItem(52).getItemMeta().getDisplayName().substring(16);
                        int page = Integer.parseInt(pageStr);
                        Inventory listVanillaWorlds1 = CMP2Utilities.ListVanillaWorlds(player, tempMob5, page + 1);
                        player.openInventory(listVanillaWorlds1);
                        break;
                    case "§aPrevious page":
                        String pageStr1 = click.getInventory().getItem(52).getItemMeta().getDisplayName().substring(16);
                        int page1 = Integer.parseInt(pageStr1);
                        Inventory listVanillaWorlds2 = CMP2Utilities.ListVanillaWorlds(player, tempMob5, page1 - 1);
                        player.openInventory(listVanillaWorlds2);
                        break;
                    default:
                        if (click.getCurrentItem().getType().equals(Material.LIME_BANNER) || click.getCurrentItem().getType().equals(Material.RED_BANNER)) {
                            ArrayList<World> tempArr;
                            if (tempMob5.getVanillaDrops() != null) {
                                tempArr = tempMob5.getVanillaDrops();
                            } else {
                                tempArr = new ArrayList<>();
                            }
                            World world = player.getServer().getWorld(click.getCurrentItem().getItemMeta().getDisplayName());
                            if (click.getCurrentItem().getType().equals(Material.LIME_BANNER)) {
                                tempArr.add(world);
                            } else {
                                tempArr.remove(world);
                            }
                            tempMob5.setVanillaDrops(tempArr);
                            CubMainPlugin2.mobNames.put(tempMob5.getName(), tempMob5);
                            String pageStr2 = click.getInventory().getItem(52).getItemMeta().getDisplayName().substring(16);
                            int page2 = Integer.parseInt(pageStr2);
                            Inventory listVanillaWorlds3 = CMP2Utilities.ListVanillaWorlds(player, tempMob5, page2);
                            player.openInventory(listVanillaWorlds3);
                        }
                        break;
                }
                break;
            case "§2[CustomDrops] Choose a block":
                click.setCancelled(true);
                switch (click.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "§aBack to main menu":
                        player.performCommand("cdrops");
                        break;
                    case "§aBack to previous menu":
                        Inventory blockSelection = CMP2Utilities.CreateBlockSelection(1);
                        player.openInventory(blockSelection);
                        break;
                    default:
                        if (click.getSlot() > 8 && click.getSlot() < 45) {
                            Material material = click.getCurrentItem().getType();
                            boolean weirdMaterial = false;
                            Material tempMaterial = null;
                            if (!material.isBlock() || CubMainPlugin2.editedBlocks.keySet().contains(material)) {
                                if (material.equals(Material.CARROT)) {
                                    tempMaterial = Material.CARROTS;
                                    weirdMaterial = true;
                                } else if (material.equals(Material.BEETROOT)) {
                                    tempMaterial = Material.BEETROOTS;
                                    weirdMaterial = true;
                                } else if (material.equals(Material.COCOA_BEANS)) {
                                    tempMaterial = Material.COCOA;
                                    weirdMaterial = true;
                                } else if (material.equals(Material.POTATO)) {
                                    tempMaterial = Material.POTATOES;
                                    weirdMaterial = true;
                                } else {
                                    break;
                                }
                            }
                            CMP2Block block = new CMP2Block();
                            if (weirdMaterial) {
                                block.setBlock(tempMaterial);
                            } else {
                                block.setBlock(material);
                            }
                            block.setCustomDrops(null);
                            block.setVanillaDrops(null);

                            if (weirdMaterial) {
                                CubMainPlugin2.editedBlocks.put(tempMaterial, block);
                            } else {
                                CubMainPlugin2.editedBlocks.put(material, block);
                            }

                            Inventory blockSelection1 = CMP2Utilities.CreateBlockSelection(1);
                            player.openInventory(blockSelection1);
                        }
                        break;
                }
                break;
            case "§2[CustomDrops] Edit a block":
                click.setCancelled(true);
                for (Material material : CubMainPlugin2.editedBlocks.keySet()) {
                    if (material.name().equals(click.getInventory().getItem(0).getItemMeta().getDisplayName().substring(17))) {
                        CMP2Block block = CubMainPlugin2.editedBlocks.get(material);
                        switch (click.getCurrentItem().getItemMeta().getDisplayName()) {
                            case "§aBack to main menu":
                                player.performCommand("cdrops");
                                break;
                            case "§aBack to previous menu":
                                Inventory blockSelection = CMP2Utilities.CreateBlockSelection(1);
                                player.openInventory(blockSelection);
                                break;
                            case "§aDelete block":
                                if (click.isRightClick() && click.isShiftClick()) {
                                    CubMainPlugin2.editedBlocks.remove(block.getBlock());
                                    Inventory blockSelection1 = CMP2Utilities.CreateBlockSelection(1);
                                    player.openInventory(blockSelection1);
                                }
                                break;
                            case "§aToggle vanilla drops":
                                Inventory listVanillaWorlds = CMP2Utilities.ListVanillaWorldsBlock(player, block, 1);
                                player.openInventory(listVanillaWorlds);
                                break;
                            case "§aCustom drops":
                                Inventory customDropsInv = CMP2Utilities.ListCustomDropsBlock(block, player.getUniqueId());
                                player.openInventory(customDropsInv);
                                break;
                        }
                    }
                }
                break;
            case "§2[CustomDrops] Vanilla drops B":
                click.setCancelled(true);
                Material material = Material.getMaterial(click.getInventory().getItem(0).getItemMeta().getDisplayName().substring(17));
                CMP2Block block = CubMainPlugin2.editedBlocks.get(material);
                switch (click.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "§aBack to main menu":
                        player.performCommand("cdrops");
                        break;
                    case "§aBack to previous menu":
                        Inventory blockInfo = CMP2Utilities.CreateBlockInfo(block);
                        player.openInventory(blockInfo);
                        break;
                    case "§aNext page":
                        String pageStr = click.getInventory().getItem(52).getItemMeta().getDisplayName().substring(16);
                        int page = Integer.parseInt(pageStr);
                        Inventory listVanillaWorlds1 = CMP2Utilities.ListVanillaWorldsBlock(player, block, page + 1);
                        player.openInventory(listVanillaWorlds1);
                        break;
                    case "§aPrevious page":
                        String pageStr1 = click.getInventory().getItem(52).getItemMeta().getDisplayName().substring(16);
                        int page1 = Integer.parseInt(pageStr1);
                        Inventory listVanillaWorlds2 = CMP2Utilities.ListVanillaWorldsBlock(player, block, page1 - 1);
                        player.openInventory(listVanillaWorlds2);
                        break;
                    default:
                        if (click.getCurrentItem().getType().equals(Material.LIME_BANNER) || click.getCurrentItem().getType().equals(Material.RED_BANNER)) {
                            ArrayList<World> tempArr;
                            if (block.getVanillaDrops() != null) {
                                tempArr = block.getVanillaDrops();
                            } else {
                                tempArr = new ArrayList<>();
                            }
                            World world = player.getServer().getWorld(click.getCurrentItem().getItemMeta().getDisplayName());
                            if (click.getCurrentItem().getType().equals(Material.LIME_BANNER)) {
                                tempArr.add(world);
                            } else {
                                tempArr.remove(world);
                            }
                            block.setVanillaDrops(tempArr);
                            CubMainPlugin2.editedBlocks.put(block.getBlock(), block);
                            String pageStr2 = click.getInventory().getItem(52).getItemMeta().getDisplayName().substring(16);
                            int page2 = Integer.parseInt(pageStr2);
                            Inventory listVanillaWorlds3 = CMP2Utilities.ListVanillaWorldsBlock(player, block, page2);
                            player.openInventory(listVanillaWorlds3);
                        }
                        break;
                }
                break;
            case "§2[CustomDrops] Custom drops B":
                click.setCancelled(true);
                Material material1 = Material.getMaterial(click.getInventory().getItem(0).getItemMeta().getDisplayName().substring(17));
                CMP2Block block1 = CubMainPlugin2.editedBlocks.get(material1);
                switch (click.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "§aBack to main menu":
                        player.performCommand("cdrops");
                        break;
                    case "§aBack to previous menu":
                        Inventory mobInventory1 = CMP2Utilities.CreateBlockInfo(block1);
                        player.openInventory(mobInventory1);
                        break;
                    case "§aNext page":
                        CubMainPlugin2.lastPage.put(player.getUniqueId(), CubMainPlugin2.lastPage.get(player.getUniqueId()) + 1);
                        Inventory customDropsInv1 = CMP2Utilities.ListCustomDropsBlock(block1, player.getUniqueId());
                        player.openInventory(customDropsInv1);
                        break;
                    case "§aPrevious page":
                        CubMainPlugin2.lastPage.put(player.getUniqueId(), CubMainPlugin2.lastPage.get(player.getUniqueId()) - 1);
                        Inventory customDropsInv2 = CMP2Utilities.ListCustomDropsBlock(block1, player.getUniqueId());
                        player.openInventory(customDropsInv2);
                        break;
                    case "§aAdd new item":
                        Inventory addCustomDrop = CMP2Utilities.AddCustomDropInventoryBlock(player, block1);
                        player.openInventory(addCustomDrop);
                        break;
                    default:
                        if (click.getSlot() > 8 && click.getSlot() < 45 && click.getCurrentItem().getAmount() != 0) {
                            CMP2CustomDrop tempDrop10 = null;
                            int tempInt = 0;
                            for (int i = 0; i < 28; i++) {
                                if (click.getSlot() == CMP2Utilities.InventoryInside(54).get(i)) {
                                    if (CubMainPlugin2.lastPage.get(player.getUniqueId()) == null) {
                                        CubMainPlugin2.lastPage.put(player.getUniqueId(), 1);
                                    }
                                    if (i + (28 * (CubMainPlugin2.lastPage.get(player.getUniqueId()) - 1)) < block1.getCustomDrops().size()) {
                                        tempDrop10 = block1.getCustomDrops().get(i + (28 * (CubMainPlugin2.lastPage.get(player.getUniqueId()) - 1)));
                                        tempInt = i;
                                        break;
                                    }
                                }
                            }
                            if (tempDrop10 == null) {
                                return;
                            }

                            Inventory customDropInfo = CMP2Utilities.ShowCustomDropInfoBlock(block1, tempDrop10, tempInt  + (28 * (CubMainPlugin2.lastPage.get(player.getUniqueId()) - 1)));
                            player.openInventory(customDropInfo);
                        }
                        break;
                }
                break;
            case "§2[CustomDrops] Choose an item B":
                click.setCancelled(true);
                Material material2 = Material.getMaterial(click.getInventory().getItem(0).getItemMeta().getDisplayName().substring(17));
                CMP2Block block2 = CubMainPlugin2.editedBlocks.get(material2);
                switch (click.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "§aBack to main menu":
                        player.performCommand("cdrops");
                        break;
                    case "§aBack to previous menu":
                        Inventory listDrops = CMP2Utilities.ListCustomDropsBlock(block2, player.getUniqueId());
                        player.openInventory(listDrops);
                        break;
                    default:
                        if (click.getSlot() > 8 && click.getSlot() < 45) {
                            CMP2CustomDrop tempDrop11 = new CMP2CustomDrop();
                            tempDrop11.setDrop(click.getCurrentItem());
                            tempDrop11.setDropChance(0);
                            tempDrop11.setEffect(false);
                            tempDrop11.setEffectColor(Color.LIME);
                            tempDrop11.setSpawnerDrop(false);
                            tempDrop11.setEggDrop(false);
                            tempDrop11.setNaturalDrop(true);
                            tempDrop11.setBredDrop(false);
                            tempDrop11.setWorldOverride(false);
                            if (block2.getCustomDrops() == null) {
                                ArrayList<CMP2CustomDrop> tempArr = new ArrayList<>();
                                tempArr.add(tempDrop11);
                                block2.setCustomDrops(tempArr);
                            } else {
                                ArrayList<CMP2CustomDrop> tempArr = block2.getCustomDrops();
                                tempArr.add(tempDrop11);
                                block2.setCustomDrops(tempArr);
                            }
                            CubMainPlugin2.editedBlocks.put(block2.getBlock(), block2);

                            CubMainPlugin2.lastPage.put(player.getUniqueId(), 1);
                            Inventory customDropsInv1 = CMP2Utilities.ListCustomDropsBlock(block2, player.getUniqueId());
                            player.openInventory(customDropsInv1);
                        }
                        break;
                }
                break;
            case "§2[CustomDrops] Drop settings B": {
                click.setCancelled(true);
                Material material3 = Material.getMaterial(click.getInventory().getItem(0).getItemMeta().getDisplayName().substring(17));
                CMP2Block block3 = CubMainPlugin2.editedBlocks.get(material3);
                ArrayList<CMP2CustomDrop> tempDropArr = block3.getCustomDrops();
                String dropNumberString = click.getInventory().getItem(0).getItemMeta().getLore().get(0).substring(17);
                int dropNumber = Integer.parseInt(dropNumberString);
                CMP2CustomDrop tempDrop = block3.getCustomDrops().get(dropNumber);
                switch (click.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "§aBack to main menu":
                        player.performCommand("cdrops");
                        break;
                    case "§aBack to previous menu":
                        Inventory listDrops = CMP2Utilities.ListCustomDropsBlock(block3, player.getUniqueId());
                        player.openInventory(listDrops);
                        break;
                    case "§aEdit commands":
                        Inventory commandsList = CMP2Utilities.CommandsListBlock(block3, tempDrop, dropNumber);
                        player.openInventory(commandsList);
                        break;
                    case "§aMore settings":
                        Inventory moreDropsOpts = CMP2Utilities.MoreDropOptionsBlock(block3, tempDrop, dropNumber);
                        player.openInventory(moreDropsOpts);
                        break;
                    case "§aDrop worlds list":
                        if (click.isShiftClick() && click.getCurrentItem().getItemMeta().getLore().get(0).equals("§aEnabled: Overrides global settings worlds")) {
                            Inventory listWorlds = CMP2Utilities.ListDropWorldsBlock(player, block3, tempDrop, dropNumber, 1);
                            player.openInventory(listWorlds);
                        } else {
                            if (click.getCurrentItem().getItemMeta().getLore().get(0).equals("§cDisabled: Using global settings worlds")) {
                                tempDrop.setWorldOverride(true);
                            } else {
                                tempDrop.setWorldOverride(false);
                            }
                            tempDropArr.set(dropNumber, tempDrop);
                            block3.setCustomDrops(tempDropArr);
                            CubMainPlugin2.editedBlocks.put(block3.getBlock(), block3);
                            Inventory customDropsInv2 = CMP2Utilities.ShowCustomDropInfoBlock(block3, tempDrop, dropNumber);
                            player.openInventory(customDropsInv2);
                        }
                        break;
                    case "§aDelete drop":
                        if (click.isRightClick() && click.isShiftClick()) {
                            tempDropArr.remove(dropNumber);
                            block3.setCustomDrops(tempDropArr);
                            CubMainPlugin2.editedBlocks.put(block3.getBlock(), block3);
                            Inventory customDropsInv2 = CMP2Utilities.ListCustomDropsBlock(block3, player.getUniqueId());
                            player.openInventory(customDropsInv2);
                        }
                        break;
                    case "§cDecrease chance by 0.01%":
                        tempDropArr.get(dropNumber).setDropChance(tempDrop.getDropChance() - 1);
                        block3.setCustomDrops(tempDropArr);
                        CubMainPlugin2.editedBlocks.put(block3.getBlock(), block3);
                        Inventory customDropInfo0 = CMP2Utilities.ShowCustomDropInfoBlock(block3, tempDrop, dropNumber);
                        player.openInventory(customDropInfo0);
                        break;
                    case "§cDecrease chance by 0.1%":
                        tempDropArr.get(dropNumber).setDropChance(tempDrop.getDropChance() - 10);
                        block3.setCustomDrops(tempDropArr);
                        CubMainPlugin2.editedBlocks.put(block3.getBlock(), block3);
                        Inventory customDropInfo01 = CMP2Utilities.ShowCustomDropInfoBlock(block3, tempDrop, dropNumber);
                        player.openInventory(customDropInfo01);
                        break;
                    case "§cDecrease chance by 1%":
                        tempDropArr.get(dropNumber).setDropChance(tempDrop.getDropChance() - 100);
                        block3.setCustomDrops(tempDropArr);
                        CubMainPlugin2.editedBlocks.put(block3.getBlock(), block3);
                        Inventory customDropInfo1 = CMP2Utilities.ShowCustomDropInfoBlock(block3, tempDrop, dropNumber);
                        player.openInventory(customDropInfo1);
                        break;
                    case "§cDecrease chance by 10%":
                        tempDropArr.get(dropNumber).setDropChance(tempDrop.getDropChance() - 1000);
                        block3.setCustomDrops(tempDropArr);
                        CubMainPlugin2.editedBlocks.put(block3.getBlock(), block3);
                        Inventory customDropInfo2 = CMP2Utilities.ShowCustomDropInfoBlock(block3, tempDrop, dropNumber);
                        player.openInventory(customDropInfo2);
                        break;
                    case "§aIncrease chance by 0.01%":
                        tempDropArr.get(dropNumber).setDropChance(tempDrop.getDropChance() + 1);
                        block3.setCustomDrops(tempDropArr);
                        CubMainPlugin2.editedBlocks.put(block3.getBlock(), block3);
                        Inventory customDropInfo15 = CMP2Utilities.ShowCustomDropInfoBlock(block3, tempDrop, dropNumber);
                        player.openInventory(customDropInfo15);
                        break;
                    case "§aIncrease chance by 0.1%":
                        tempDropArr.get(dropNumber).setDropChance(tempDrop.getDropChance() + 10);
                        block3.setCustomDrops(tempDropArr);
                        CubMainPlugin2.editedBlocks.put(block3.getBlock(), block3);
                        Inventory customDropInfo151 = CMP2Utilities.ShowCustomDropInfoBlock(block3, tempDrop, dropNumber);
                        player.openInventory(customDropInfo151);
                        break;
                    case "§aIncrease chance by 1%":
                        tempDropArr.get(dropNumber).setDropChance(tempDrop.getDropChance() + 100);
                        block3.setCustomDrops(tempDropArr);
                        CubMainPlugin2.editedBlocks.put(block3.getBlock(), block3);
                        Inventory customDropInfo3 = CMP2Utilities.ShowCustomDropInfoBlock(block3, tempDrop, dropNumber);
                        player.openInventory(customDropInfo3);
                        break;
                    case "§aIncrease chance by 10%":
                        tempDropArr.get(dropNumber).setDropChance(tempDrop.getDropChance() + 1000);
                        block3.setCustomDrops(tempDropArr);
                        CubMainPlugin2.editedBlocks.put(block3.getBlock(), block3);
                        Inventory customDropInfo4 = CMP2Utilities.ShowCustomDropInfoBlock(block3, tempDrop, dropNumber);
                        player.openInventory(customDropInfo4);
                        break;
                    case "§aPlaced drops":
                        if (click.getCurrentItem().getType().equals(Material.LIME_CONCRETE)) {
                            tempDrop.setSpawnerDrop(false);
                        }
                        if (click.getCurrentItem().getType().equals(Material.RED_CONCRETE)) {
                            tempDrop.setSpawnerDrop(true);
                        }
                        tempDropArr.set(dropNumber, tempDrop);
                        block3.setCustomDrops(tempDropArr);
                        CubMainPlugin2.editedBlocks.put(block3.getBlock(), block3);
                        Inventory customDropInfo5 = CMP2Utilities.ShowCustomDropInfoBlock(block3, tempDrop, dropNumber);
                        player.openInventory(customDropInfo5);
                        break;
                    case "§aNatural drops":
                        if (click.getCurrentItem().getType().equals(Material.LIME_CONCRETE)) {
                            tempDrop.setNaturalDrop(false);
                        }
                        if (click.getCurrentItem().getType().equals(Material.RED_CONCRETE)) {
                            tempDrop.setNaturalDrop(true);
                        }
                        tempDropArr.set(dropNumber, tempDrop);
                        block3.setCustomDrops(tempDropArr);
                        CubMainPlugin2.editedBlocks.put(block3.getBlock(), block3);
                        Inventory customDropInfo7 = CMP2Utilities.ShowCustomDropInfoBlock(block3, tempDrop, dropNumber);
                        player.openInventory(customDropInfo7);
                        break;
                    case "§aDrop effect":
                        if (click.getCurrentItem().getType().equals(Material.LIME_CONCRETE)) {
                            tempDrop.setEffect(false);
                        }
                        if (click.getCurrentItem().getType().equals(Material.RED_CONCRETE)) {
                            tempDrop.setEffect(true);
                        }
                        tempDropArr.set(dropNumber, tempDrop);
                        block3.setCustomDrops(tempDropArr);
                        CubMainPlugin2.editedBlocks.put(block3.getBlock(), block3);
                        Inventory customDropInfo8 = CMP2Utilities.ShowCustomDropInfoBlock(block3, tempDrop, dropNumber);
                        player.openInventory(customDropInfo8);
                        break;
                    case "§aCurrent color: Green":
                        tempDrop.setEffectColor(Color.AQUA);
                        tempDropArr.set(dropNumber, tempDrop);
                        block3.setCustomDrops(tempDropArr);
                        CubMainPlugin2.editedBlocks.put(block3.getBlock(), block3);
                        Inventory customDropInfo9 = CMP2Utilities.ShowCustomDropInfoBlock(block3, tempDrop, dropNumber);
                        player.openInventory(customDropInfo9);
                        break;
                    case "§bCurrent color: Blue":
                        tempDrop.setEffectColor(Color.PURPLE);
                        tempDropArr.set(dropNumber, tempDrop);
                        block3.setCustomDrops(tempDropArr);
                        CubMainPlugin2.editedBlocks.put(block3.getBlock(), block3);
                        Inventory customDropInfo10 = CMP2Utilities.ShowCustomDropInfoBlock(block3, tempDrop, dropNumber);
                        player.openInventory(customDropInfo10);
                        break;
                    case "§5Current color: Purple":
                        tempDrop.setEffectColor(Color.BLACK);
                        tempDropArr.set(dropNumber, tempDrop);
                        block3.setCustomDrops(tempDropArr);
                        CubMainPlugin2.editedBlocks.put(block3.getBlock(), block3);
                        Inventory customDropInfo17 = CMP2Utilities.ShowCustomDropInfoBlock(block3, tempDrop, dropNumber);
                        player.openInventory(customDropInfo17);
                        break;
                    case "§8Current color: Black":
                        tempDrop.setEffectColor(Color.WHITE);
                        tempDropArr.set(dropNumber, tempDrop);
                        block3.setCustomDrops(tempDropArr);
                        CubMainPlugin2.editedBlocks.put(block3.getBlock(), block3);
                        Inventory customDropInfo11 = CMP2Utilities.ShowCustomDropInfoBlock(block3, tempDrop, dropNumber);
                        player.openInventory(customDropInfo11);
                        break;
                    case "§7Current color: White":
                        tempDrop.setEffectColor(Color.RED);
                        tempDropArr.set(dropNumber, tempDrop);
                        block3.setCustomDrops(tempDropArr);
                        CubMainPlugin2.editedBlocks.put(block3.getBlock(), block3);
                        Inventory customDropInfo12 = CMP2Utilities.ShowCustomDropInfoBlock(block3, tempDrop, dropNumber);
                        player.openInventory(customDropInfo12);
                        break;
                    case "§4Current color: Red":
                        tempDrop.setEffectColor(Color.ORANGE);
                        tempDropArr.set(dropNumber, tempDrop);
                        block3.setCustomDrops(tempDropArr);
                        CubMainPlugin2.editedBlocks.put(block3.getBlock(), block3);
                        Inventory customDropInfo13 = CMP2Utilities.ShowCustomDropInfoBlock(block3, tempDrop, dropNumber);
                        player.openInventory(customDropInfo13);
                        break;
                    case "§6Current color: Orange":
                        tempDrop.setEffectColor(Color.YELLOW);
                        tempDropArr.set(dropNumber, tempDrop);
                        block3.setCustomDrops(tempDropArr);
                        CubMainPlugin2.editedBlocks.put(block3.getBlock(), block3);
                        Inventory customDropInfo16 = CMP2Utilities.ShowCustomDropInfoBlock(block3, tempDrop, dropNumber);
                        player.openInventory(customDropInfo16);
                        break;
                    case "§eCurrent color: Yellow":
                        tempDrop.setEffectColor(Color.LIME);
                        tempDropArr.set(dropNumber, tempDrop);
                        block3.setCustomDrops(tempDropArr);
                        CubMainPlugin2.editedBlocks.put(block3.getBlock(), block3);
                        Inventory customDropInfo14 = CMP2Utilities.ShowCustomDropInfoBlock(block3, tempDrop, dropNumber);
                        player.openInventory(customDropInfo14);
                        break;
                }
                break; }
            case "§2[CustomDrops] Commands list B":
                click.setCancelled(true);
                Material material3 = Material.getMaterial(click.getInventory().getItem(0).getItemMeta().getDisplayName().substring(17));
                CMP2Block block3 = CubMainPlugin2.editedBlocks.get(material3);
                ArrayList<CMP2CustomDrop> tempDropArr1 = block3.getCustomDrops();
                String dropNumberString1 = click.getInventory().getItem(0).getItemMeta().getLore().get(0).substring(17);
                int dropNumber1 = Integer.parseInt(dropNumberString1);
                CMP2CustomDrop tempDrop1 = block3.getCustomDrops().get(dropNumber1);
                switch (click.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "§aBack to main menu":
                        player.performCommand("cdrops");
                        break;
                    case "§aBack to previous menu":
                        Inventory customDropInfo = CMP2Utilities.ShowCustomDropInfoBlock(block3, tempDrop1, dropNumber1);
                        player.openInventory(customDropInfo);
                        break;
                    case "§aClick to add a command":
                        player.closeInventory();
                        player.sendMessage("§2[CustomDrops] §cPlease type the command you want to add.");
                        player.sendMessage("§2[CustomDrops] §cType §a%p% §cto add the player that broke the block, type §a%a% §cto add all online players, type §a%c% §cto add the coordinates of the block.");
                        player.sendMessage("§2[CustomDrops] §cYou do not need to type the initial '/'");
                        CubMainPlugin2.addingCommandB.put(player.getUniqueId(), dropNumber1);
                        CubMainPlugin2.addingCommandBlock.put(player.getUniqueId(), block3);
                        break;
                    default:
                        if (click.getCurrentItem().getType().equals(Material.COMMAND_BLOCK) && click.getCurrentItem().getItemMeta().getLore().get(0).equals("§cShift right click to delete")) {
                            if (click.isShiftClick() && click.isRightClick()) {

                                ArrayList<String> tempArr = tempDrop1.getCommands();
                                int i = 0;
                                for (int j = 0; j < 28; j++) {
                                    if (click.getSlot() == CMP2Utilities.InventoryInside(54).get(j)) {
                                        i = j;
                                        break;
                                    }
                                }
                                tempArr.remove(i);
                                tempDrop1.setCommands(tempArr);

                                ArrayList<CMP2CustomDrop> tempArr1 = block3.getCustomDrops();
                                tempArr1.set(dropNumber1, tempDrop1);
                                block3.setCustomDrops(tempArr1);
                                CubMainPlugin2.editedBlocks.put(block3.getBlock(), block3);

                                Inventory commandsList = CMP2Utilities.CommandsListBlock(block3, tempDrop1, dropNumber1);
                                player.openInventory(commandsList);
                            }
                        }
                        break;
                }
                break;
            case "§2[CustomDrops] Drop worlds B": {
                click.setCancelled(true);
                Material material4 = Material.getMaterial(click.getInventory().getItem(0).getItemMeta().getDisplayName().substring(17));
                CMP2Block block4 = CubMainPlugin2.editedBlocks.get(material4);
                ArrayList<CMP2CustomDrop> tempDropArr2 = block4.getCustomDrops();
                String dropNumberString2 = click.getInventory().getItem(0).getItemMeta().getLore().get(0).substring(17);
                int dropNumber2 = Integer.parseInt(dropNumberString2);
                CMP2CustomDrop tempDrop2 = block4.getCustomDrops().get(dropNumber2);
                switch (click.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "§aBack to main menu":
                        player.performCommand("cdrops");
                        break;
                    case "§aBack to previous menu":
                        Inventory dropInfo = CMP2Utilities.ShowCustomDropInfoBlock(block4, tempDrop2, dropNumber2);
                        player.openInventory(dropInfo);
                        break;
                    case "§aNext page":
                        String pageStr = click.getInventory().getItem(52).getItemMeta().getDisplayName().substring(16);
                        int page = Integer.parseInt(pageStr);
                        Inventory dropWorlds = CMP2Utilities.ListDropWorldsBlock(player, block4, tempDrop2, dropNumber2, page + 1);
                        player.openInventory(dropWorlds);
                        break;
                    case "§aPrevious page":
                        String pageStr1 = click.getInventory().getItem(52).getItemMeta().getDisplayName().substring(16);
                        int page1 = Integer.parseInt(pageStr1);
                        Inventory dropWorlds1 = CMP2Utilities.ListDropWorldsBlock(player, block4, tempDrop2, dropNumber2, page1 - 1);
                        player.openInventory(dropWorlds1);
                        break;
                    default:
                        if (click.getCurrentItem().getType().equals(Material.LIME_BANNER) || click.getCurrentItem().getType().equals(Material.RED_BANNER)) {
                            ArrayList<World> tempArr;
                            if (tempDrop2.getWorlds() != null) {
                                tempArr = tempDrop2.getWorlds();
                            } else {
                                tempArr = new ArrayList<>();
                            }
                            World world = player.getServer().getWorld(click.getCurrentItem().getItemMeta().getDisplayName());
                            if (click.getCurrentItem().getType().equals(Material.LIME_BANNER)) {
                                tempArr.remove(world);
                            } else {
                                tempArr.add(world);
                            }
                            tempDrop2.setWorlds(tempArr);
                            tempDropArr2.set(dropNumber2, tempDrop2);
                            block4.setCustomDrops(tempDropArr2);
                            CubMainPlugin2.editedBlocks.put(block4.getBlock(), block4);
                            String pageStr2 = click.getInventory().getItem(52).getItemMeta().getDisplayName().substring(16);
                            int page2 = Integer.parseInt(pageStr2);
                            Inventory globalWorlds2 = CMP2Utilities.ListDropWorldsBlock(player, block4, tempDrop2, dropNumber2, page2);
                            player.openInventory(globalWorlds2);
                        }
                        break;
                }
                break; }
        }
    }
}
