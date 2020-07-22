package com.gmail.cubitverde;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CMP2Utilities {

    static ArrayList<Integer> InventoryFrame (int int1) {
        ArrayList<Integer> arrayInt = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            arrayInt.add(i);
        }
        for (int i = 0; i < int1; i += 9) {
            arrayInt.add(i);
            arrayInt.add(i + 8);
        }
        for (int i = int1 - 1; i > int1 - 9; i--) {
            arrayInt.add(i);
        }
        return arrayInt;
    }

    static ArrayList<Integer> InventoryInside (int int1) {
        ArrayList<Integer> arrayInt = new ArrayList<>();
        for (int i = 0; i < int1; i++) {
            if (!(InventoryFrame(int1).contains(i))) {
                arrayInt.add(i);
            }
        }
        return arrayInt;
    }

    static Inventory GreenFrame (Inventory inv1) {
        ItemStack frameMaterial = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta frameMaterialMeta = frameMaterial.getItemMeta();
        frameMaterialMeta.setDisplayName(" ");
        frameMaterial.setItemMeta(frameMaterialMeta);
        for (int i : InventoryFrame(inv1.getSize())) {
            inv1.setItem(i, frameMaterial);
        }

        ItemStack frameItem0 = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta frameItem0Meta = frameItem0.getItemMeta();
        frameItem0Meta.setDisplayName("§a[CustomDrops]");
        List<String> list1 = new ArrayList<>();
        list1.add("§2Made by: §acubito_verde");
        frameItem0Meta.setLore(list1);
        frameItem0.setItemMeta(frameItem0Meta);
        inv1.setItem(4, frameItem0);
        return inv1;
    }

    static Inventory CreateMainMenu () {
        Inventory mainMenu = Bukkit.createInventory(null, 27, "§2[CustomDrops] Main menu");

        mainMenu = GreenFrame(mainMenu);

        ItemStack frameItem1 = new ItemStack(Material.SKELETON_SKULL);
        ItemMeta frameItem1Meta = frameItem1.getItemMeta();
        frameItem1Meta.setDisplayName("§aEdit a mob");
        frameItem1.setItemMeta(frameItem1Meta);
        mainMenu.setItem(10, frameItem1);

        ItemStack frameItem2 = new ItemStack(Material.DIAMOND_ORE);
        ItemMeta frameItem2Meta = frameItem2.getItemMeta();
        frameItem2Meta.setDisplayName("§aEdit a block");
        frameItem2.setItemMeta(frameItem2Meta);
        mainMenu.setItem(12, frameItem2);

        ItemStack frameItem3 = new ItemStack(Material.LIGHT_GRAY_CONCRETE);
        ItemMeta frameItem3Meta = frameItem2.getItemMeta();
        frameItem3Meta.setDisplayName("§aMaybe soon?");
        frameItem3.setItemMeta(frameItem3Meta);
        mainMenu.setItem(14, frameItem3);

        ItemStack frameItem4 = new ItemStack(Material.COMMAND_BLOCK);
        ItemMeta frameItem4Meta = frameItem4.getItemMeta();
        frameItem4Meta.setDisplayName("§aGlobal settings");
        frameItem4.setItemMeta(frameItem4Meta);
        mainMenu.setItem(16, frameItem4);

        return mainMenu;
    }

    static Inventory CreateMobSelection (int pageNumber) {
        Inventory mobSelection = Bukkit.createInventory(null, 54, "§2[CustomDrops] Mob selection");

        mobSelection = GreenFrame(mobSelection);

        for (int i = 9; i < 45; i += 9) {
            mobSelection.setItem(i, new ItemStack(Material.AIR));
            mobSelection.setItem(i + 8, new ItemStack(Material.AIR));
        }

        if (pageNumber == 1) {
            for (int i = 0; i < 36; i++) {
                ItemStack tempItem = new ItemStack(CubMainPlugin2.eggsMaterials.get(i));
                ItemMeta tempItemMeta = tempItem.getItemMeta();
                tempItemMeta.setDisplayName(CubMainPlugin2.eggsNames.get(i));
                tempItem.setItemMeta(tempItemMeta);

                mobSelection.setItem(i + 9, tempItem);
            }

            ItemStack frameItem3 = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
            ItemMeta frameItem3Meta = frameItem3.getItemMeta();
            frameItem3Meta.setDisplayName("§aNext page");
            frameItem3.setItemMeta(frameItem3Meta);
            mobSelection.setItem(53, frameItem3);
        }
        if (pageNumber == 2) {
            for (int i = 36; i < CubMainPlugin2.eggsMaterials.size(); i++) {
                ItemStack tempItem = new ItemStack(CubMainPlugin2.eggsMaterials.get(i));
                ItemMeta tempItemMeta = tempItem.getItemMeta();
                tempItemMeta.setDisplayName(CubMainPlugin2.eggsNames.get(i));
                tempItem.setItemMeta(tempItemMeta);

                mobSelection.setItem(i - 27, tempItem);
            }

            ItemStack frameItem3 = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
            ItemMeta frameItem3Meta = frameItem3.getItemMeta();
            frameItem3Meta.setDisplayName("§aPrevious page");
            frameItem3.setItemMeta(frameItem3Meta);
            mobSelection.setItem(51, frameItem3);
        }

        ItemStack frameItem3 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem3Meta = frameItem3.getItemMeta();
        frameItem3Meta.setDisplayName("§aCurrent page: " + pageNumber);
        frameItem3.setItemMeta(frameItem3Meta);
        mobSelection.setItem(52, frameItem3);

        ItemStack frameItem4 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta frameItem4Meta = frameItem4.getItemMeta();
        frameItem4Meta.setDisplayName("§aBack to main menu");
        frameItem4.setItemMeta(frameItem4Meta);
        mobSelection.setItem(45, frameItem4);

        return mobSelection;
    }

    static Inventory CreateMobInfo (CMP2Mob tempMob) {
        Inventory mobInfo = Bukkit.createInventory(null, 27, "§2[CustomDrops] Edit a mob");

        mobInfo = GreenFrame(mobInfo);

        ItemStack frameItem1 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta frameItem1Meta = frameItem1.getItemMeta();
        frameItem1Meta.setDisplayName("§aBack to main menu");
        frameItem1.setItemMeta(frameItem1Meta);
        mobInfo.setItem(18, frameItem1);

        ItemStack frameItem2 = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta frameItem2Meta = frameItem2.getItemMeta();
        frameItem2Meta.setDisplayName("§aBack to previous menu");
        frameItem2.setItemMeta(frameItem2Meta);
        mobInfo.setItem(19, frameItem2);

        ItemStack frameItem4 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem4Meta = frameItem4.getItemMeta();
        frameItem4Meta.setDisplayName("§2Editing mob: " + tempMob.getName());
        frameItem4.setItemMeta(frameItem4Meta);
        mobInfo.setItem(0, frameItem4);

        ItemStack greenPane = new ItemStack(Material.LIME_CONCRETE);
        ItemMeta greenPaneMeta = greenPane.getItemMeta();
        List<String> tempList1 = new ArrayList<>();
        tempList1.add("§aAllowed, click to deny");
        greenPaneMeta.setLore(tempList1);
        ItemStack redPane = new ItemStack(Material.RED_CONCRETE);
        ItemMeta redPaneMeta = redPane.getItemMeta();
        List<String> tempList2 = new ArrayList<>();
        tempList2.add("§cDenied, click to allow");
        redPaneMeta.setLore(tempList2);

        ItemStack tempStack1 = new ItemStack(Material.BOOKSHELF);
        ItemMeta tempStackMeta1 = tempStack1.getItemMeta();
        tempStackMeta1.setDisplayName("§aToggle vanilla drops");
        tempStack1.setItemMeta(tempStackMeta1);
        mobInfo.setItem(11, tempStack1);

        ItemStack invItem1 = new ItemStack(Material.GOLD_INGOT);
        ItemMeta invItem1Meta = invItem1.getItemMeta();
        invItem1Meta.setDisplayName("§aCustom drops");
        invItem1.setItemMeta(invItem1Meta);
        mobInfo.setItem(13, invItem1);

        ItemStack invItem2 = new ItemStack(Material.LIGHT_GRAY_CONCRETE);
        ItemMeta invItem2Meta = invItem2.getItemMeta();
        invItem2Meta.setDisplayName("§aMaybe someday?");
        invItem2.setItemMeta(invItem2Meta);
        mobInfo.setItem(15, invItem2);

        return  mobInfo;
    }

    static Inventory ListCustomDrops (CMP2Mob tempMob, UUID tempUUID) {
        Inventory customDropsInv = Bukkit.createInventory(null, 54, "§2[CustomDrops] Custom drops");

        customDropsInv = GreenFrame(customDropsInv);

        ItemStack frameItem0 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta frameItem0Meta = frameItem0.getItemMeta();
        frameItem0Meta.setDisplayName("§aBack to main menu");
        frameItem0.setItemMeta(frameItem0Meta);
        customDropsInv.setItem(45, frameItem0);

        ItemStack frameItem1 = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta frameItem1Meta = frameItem1.getItemMeta();
        frameItem1Meta.setDisplayName("§aBack to previous menu");
        frameItem1.setItemMeta(frameItem1Meta);
        customDropsInv.setItem(46, frameItem1);

        ItemStack frameItem2 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem2Meta = frameItem2.getItemMeta();
        frameItem2Meta.setDisplayName("§2Editing mob: " + tempMob.getName());
        frameItem2.setItemMeta(frameItem2Meta);
        customDropsInv.setItem(0, frameItem2);

        ItemStack frameItem3 = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        ItemMeta frameItem3Meta = frameItem3.getItemMeta();
        frameItem3Meta.setDisplayName("§aAdd new item");
        frameItem3.setItemMeta(frameItem3Meta);
        customDropsInv.setItem(8, frameItem3);

        ItemStack frameItem4 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem4Meta = frameItem4.getItemMeta();
        frameItem4Meta.setDisplayName("§aCurrent page: " + CubMainPlugin2.lastPage.get(tempUUID));
        frameItem4.setItemMeta(frameItem4Meta);
        customDropsInv.setItem(52, frameItem4);

        if (tempMob.getCustomDrops() != null) {
            if (tempMob.getCustomDrops().size() > 28 * (CubMainPlugin2.lastPage.get(tempUUID))) {
                ItemStack nextPageMaterial = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
                ItemMeta nextPageMaterialMeta = nextPageMaterial.getItemMeta();
                nextPageMaterialMeta.setDisplayName("§aNext page");
                nextPageMaterial.setItemMeta(nextPageMaterialMeta);
                customDropsInv.setItem(53, nextPageMaterial);
            }
            if (CubMainPlugin2.lastPage.get(tempUUID) > 1) {
                ItemStack previousPageMaterial = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
                ItemMeta previousPageMaterialMeta = previousPageMaterial.getItemMeta();
                previousPageMaterialMeta.setDisplayName("§aPrevious page");
                previousPageMaterial.setItemMeta(previousPageMaterialMeta);
                customDropsInv.setItem(51, previousPageMaterial);
            }

            int tempInt1 = 0;
            int tempInt2 = 0;
            for (CMP2CustomDrop customDrop : tempMob.getCustomDrops()) {
                if (tempInt2 > 27) {
                    break;
                }
                if (tempInt1 < 28 * (CubMainPlugin2.lastPage.get(tempUUID) - 1)) {
                    tempInt1++;
                    continue;
                }
                ItemStack dropItemStack = customDrop.getDrop();
                customDropsInv.setItem(InventoryInside(54).get(tempInt2), dropItemStack);
                tempInt2++;
            }
        }

        return customDropsInv;
    }

    static Inventory AddCustomDropInventory (Player player, CMP2Mob tempMob) {
        Inventory chooseDropInv = Bukkit.createInventory(null, 54, "§2[CustomDrops] Choose an item");

        chooseDropInv = GreenFrame(chooseDropInv);

        ItemStack frameItem0 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta frameItem0Meta = frameItem0.getItemMeta();
        frameItem0Meta.setDisplayName("§aBack to main menu");
        frameItem0.setItemMeta(frameItem0Meta);
        chooseDropInv.setItem(45, frameItem0);

        ItemStack frameItem1 = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta frameItem1Meta = frameItem1.getItemMeta();
        frameItem1Meta.setDisplayName("§aBack to previous menu");
        frameItem1.setItemMeta(frameItem1Meta);
        chooseDropInv.setItem(46, frameItem1);

        ItemStack frameItem2 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem2Meta = frameItem2.getItemMeta();
        frameItem2Meta.setDisplayName("§2Editing mob: " + tempMob.getName());
        frameItem2.setItemMeta(frameItem2Meta);
        chooseDropInv.setItem(0, frameItem2);

        Inventory playerInv = player.getInventory();

        for (int i = 0; i < 36; i++) {
            if (playerInv.getItem(i) == null) {
                chooseDropInv.setItem(i + 9, null);
            } else {
                chooseDropInv.setItem(i + 9, playerInv.getItem(i));
            }
        }

        return chooseDropInv;
    }

    static Inventory ShowCustomDropInfo (CMP2Mob tempMob, CMP2CustomDrop tempDrop, int tempInt) {
        Inventory customDropInfo = Bukkit.createInventory(null, 45, "§2[CustomDrops] Drop settings");

        customDropInfo = GreenFrame(customDropInfo);

        ItemStack frameItem0 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta frameItem0Meta = frameItem0.getItemMeta();
        frameItem0Meta.setDisplayName("§aBack to main menu");
        frameItem0.setItemMeta(frameItem0Meta);
        customDropInfo.setItem(36, frameItem0);

        ItemStack frameItem1 = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta frameItem1Meta = frameItem1.getItemMeta();
        frameItem1Meta.setDisplayName("§aBack to previous menu");
        frameItem1.setItemMeta(frameItem1Meta);
        customDropInfo.setItem(37, frameItem1);

        ItemStack frameItem2 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem2Meta = frameItem2.getItemMeta();
        frameItem2Meta.setDisplayName("§2Editing mob: " + tempMob.getName());
        List<String> tempList = new ArrayList<>();
        tempList.add("§2Drop number: §a" + tempInt);
        frameItem2Meta.setLore(tempList);
        frameItem2.setItemMeta(frameItem2Meta);
        customDropInfo.setItem(0, frameItem2);

        ItemStack frameItem3 = new ItemStack(Material.BARRIER);
        ItemMeta frameItem3Meta = frameItem0.getItemMeta();
        frameItem3Meta.setDisplayName("§aDelete drop");
        List<String> tempList4 = new ArrayList<>();
        tempList4.add("§aShift right click this to delete this item");
        frameItem3Meta.setLore(tempList4);
        frameItem3.setItemMeta(frameItem3Meta);
        customDropInfo.setItem(44, frameItem3);

        ItemStack frameItem4 = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        ItemMeta frameItem4Meta = frameItem4.getItemMeta();
        frameItem4Meta.setDisplayName("§aEdit commands");
        frameItem4.setItemMeta(frameItem4Meta);
        customDropInfo.setItem(8, frameItem4);

        /*ItemStack frameItem45 = new ItemStack(Material.MAGENTA_STAINED_GLASS_PANE);
        ItemMeta frameItem45Meta = frameItem45.getItemMeta();
        frameItem45Meta.setDisplayName("§aMore settings");
        frameItem45.setItemMeta(frameItem45Meta);
        customDropInfo.setItem(7, frameItem45);*/

        ItemStack customDrop = tempDrop.getDrop();
        customDropInfo.setItem(10, customDrop);

        ItemStack dropSettings = new ItemStack(Material.BOOKSHELF);
        ItemMeta dropSettingsMeta = dropSettings.getItemMeta();
        dropSettingsMeta.setDisplayName("§aDrop worlds list");
        List<String> tempList5 = new ArrayList<>();
        if (tempDrop.getWorldOverride()) {
            tempList5.add("§aEnabled: Overrides global settings worlds");
            tempList5.add("§cClick to disable");
            tempList5.add("§aShift click to edit worlds");
        } else {
            tempList5.add("§cDisabled: Using global settings worlds");
            tempList5.add("§aClick to enable");
        }
        dropSettingsMeta.setLore(tempList5);
        dropSettings.setItemMeta(dropSettingsMeta);
        customDropInfo.setItem(11, dropSettings);

        if (tempDrop.getDropChance() > 0) {
            ItemStack dropSettings0 = new ItemStack(Material.RED_CONCRETE);
            ItemMeta dropSettings0Meta = dropSettings0.getItemMeta();
            dropSettings0Meta.setDisplayName("§cDecrease chance by 0.01%");
            dropSettings0.setItemMeta(dropSettings0Meta);
            customDropInfo.setItem(22, dropSettings0);
            if (tempDrop.getDropChance() > 9) {
                ItemStack dropSettings1 = new ItemStack(Material.RED_CONCRETE);
                ItemMeta dropSettings1Meta = dropSettings1.getItemMeta();
                dropSettings1Meta.setDisplayName("§cDecrease chance by 0.1%");
                dropSettings1.setItemMeta(dropSettings1Meta);
                customDropInfo.setItem(23, dropSettings1);
                if (tempDrop.getDropChance() > 99) {
                    ItemStack dropSettings2 = new ItemStack(Material.RED_CONCRETE);
                    ItemMeta dropSettings2Meta = dropSettings2.getItemMeta();
                    dropSettings2Meta.setDisplayName("§cDecrease chance by 1%");
                    dropSettings2.setItemMeta(dropSettings2Meta);
                    customDropInfo.setItem(24, dropSettings2);
                    if (tempDrop.getDropChance() > 999) {
                        ItemStack dropSettings3 = new ItemStack(Material.RED_CONCRETE);
                        ItemMeta dropSettings3Meta = dropSettings3.getItemMeta();
                        dropSettings3Meta.setDisplayName("§cDecrease chance by 10%");
                        dropSettings3.setItemMeta(dropSettings3Meta);
                        customDropInfo.setItem(25, dropSettings3);
                    }
                }
            }
        }
        if (tempDrop.getDropChance() < 10000) {
            ItemStack dropSettings0 = new ItemStack(Material.GREEN_CONCRETE);
            ItemMeta dropSettings0Meta = dropSettings0.getItemMeta();
            dropSettings0Meta.setDisplayName("§aIncrease chance by 0.01%");
            dropSettings0.setItemMeta(dropSettings0Meta);
            customDropInfo.setItem(13, dropSettings0);
            if (tempDrop.getDropChance() < 9991) {
                ItemStack dropSettings1 = new ItemStack(Material.GREEN_CONCRETE);
                ItemMeta dropSettings1Meta = dropSettings1.getItemMeta();
                dropSettings1Meta.setDisplayName("§aIncrease chance by 0.1%");
                dropSettings1.setItemMeta(dropSettings1Meta);
                customDropInfo.setItem(14, dropSettings1);
                if (tempDrop.getDropChance() < 9901) {
                    ItemStack dropSettings2 = new ItemStack(Material.GREEN_CONCRETE);
                    ItemMeta dropSettings2Meta = dropSettings2.getItemMeta();
                    dropSettings2Meta.setDisplayName("§aIncrease chance by 1%");
                    dropSettings2.setItemMeta(dropSettings2Meta);
                    customDropInfo.setItem(15, dropSettings2);
                    if (tempDrop.getDropChance() < 9001) {
                        ItemStack dropSettings3 = new ItemStack(Material.GREEN_CONCRETE);
                        ItemMeta dropSettings3Meta = dropSettings3.getItemMeta();
                        dropSettings3Meta.setDisplayName("§aIncrease chance by 10%");
                        dropSettings3.setItemMeta(dropSettings3Meta);
                        customDropInfo.setItem(16, dropSettings3);
                    }
                }
            }
        }

        ItemStack dropSettings1 = new ItemStack(Material.PAPER);
        ItemMeta dropSettings1Meta = dropSettings1.getItemMeta();
        if (tempDrop.getDropChance() % 100 < 10) {
            dropSettings1Meta.setDisplayName("§2Current chance: §a" + (tempDrop.getDropChance() / 100) + ".0" + (tempDrop.getDropChance() % 100) + "%");
        } else {
            dropSettings1Meta.setDisplayName("§2Current chance: §a" + (tempDrop.getDropChance() / 100) + "." + (tempDrop.getDropChance() % 100) + "%");
        }
        dropSettings1.setItemMeta(dropSettings1Meta);
        customDropInfo.setItem(12, dropSettings1);

        ItemStack greenPane = new ItemStack(Material.LIME_CONCRETE);
        ItemMeta greenPaneMeta = greenPane.getItemMeta();
        List<String> tempList1 = new ArrayList<>();
        tempList1.add("§aAllowed, click to deny");
        greenPaneMeta.setLore(tempList1);
        ItemStack redPane = new ItemStack(Material.RED_CONCRETE);
        ItemMeta redPaneMeta = redPane.getItemMeta();
        List<String> tempList2 = new ArrayList<>();
        tempList2.add("§cDenied, click to allow");
        redPaneMeta.setLore(tempList2);

        if (tempDrop.getSpawnerDrop()) {
            greenPaneMeta.setDisplayName("§aSpawner drops");
            greenPane.setItemMeta(greenPaneMeta);
            customDropInfo.setItem(28, greenPane);
        } else {
            redPaneMeta.setDisplayName("§aSpawner drops");
            redPane.setItemMeta(redPaneMeta);
            customDropInfo.setItem(28, redPane);
        }

        if (tempDrop.getEggDrop()) {
            greenPaneMeta.setDisplayName("§aEgg drops");
            greenPane.setItemMeta(greenPaneMeta);
            customDropInfo.setItem(29, greenPane);
        } else {
            redPaneMeta.setDisplayName("§aEgg drops");
            redPane.setItemMeta(redPaneMeta);
            customDropInfo.setItem(29, redPane);
        }

        if (tempDrop.getNaturalDrop()) {
            greenPaneMeta.setDisplayName("§aNatural drops");
            greenPane.setItemMeta(greenPaneMeta);
            customDropInfo.setItem(31, greenPane);
        } else {
            redPaneMeta.setDisplayName("§aNatural drops");
            redPane.setItemMeta(redPaneMeta);
            customDropInfo.setItem(31, redPane);
        }

        if (tempDrop.getBredDrop()) {
            greenPaneMeta.setDisplayName("§aBreeding drops");
            greenPane.setItemMeta(greenPaneMeta);
            customDropInfo.setItem(30, greenPane);
        } else {
            redPaneMeta.setDisplayName("§aBreeding drops");
            redPane.setItemMeta(redPaneMeta);
            customDropInfo.setItem(30, redPane);
        }

        if (tempDrop.getEffect()) {
            greenPaneMeta.setDisplayName("§aDrop effect");
            greenPane.setItemMeta(greenPaneMeta);
            customDropInfo.setItem(33, greenPane);
        } else {
            redPaneMeta.setDisplayName("§aDrop effect");
            redPane.setItemMeta(redPaneMeta);
            customDropInfo.setItem(33, redPane);
        }

        ItemStack dropSettings2 = null;
        ItemMeta dropSettings2Meta = null;

        if (tempDrop.getEffectColor().equals(Color.RED)) {
            dropSettings2 = new ItemStack(Material.RED_WOOL);
            dropSettings2Meta = dropSettings2.getItemMeta();
            dropSettings2Meta.setDisplayName("§4Current color: Red");
        }
        if (tempDrop.getEffectColor().equals(Color.ORANGE)) {
            dropSettings2 = new ItemStack(Material.ORANGE_WOOL);
            dropSettings2Meta = dropSettings2.getItemMeta();
            dropSettings2Meta.setDisplayName("§6Current color: Orange");
        }
        if (tempDrop.getEffectColor().equals(Color.YELLOW)) {
            dropSettings2 = new ItemStack(Material.YELLOW_WOOL);
            dropSettings2Meta = dropSettings2.getItemMeta();
            dropSettings2Meta.setDisplayName("§eCurrent color: Yellow");
        }
        if (tempDrop.getEffectColor().equals(Color.LIME)) {
            dropSettings2 = new ItemStack(Material.LIME_WOOL);
            dropSettings2Meta = dropSettings2.getItemMeta();
            dropSettings2Meta.setDisplayName("§aCurrent color: Green");
        }
        if (tempDrop.getEffectColor().equals(Color.AQUA)) {
            dropSettings2 = new ItemStack(Material.LIGHT_BLUE_WOOL);
            dropSettings2Meta = dropSettings2.getItemMeta();
            dropSettings2Meta.setDisplayName("§bCurrent color: Blue");
        }
        if (tempDrop.getEffectColor().equals(Color.PURPLE)) {
            dropSettings2 = new ItemStack(Material.PURPLE_WOOL);
            dropSettings2Meta = dropSettings2.getItemMeta();
            dropSettings2Meta.setDisplayName("§5Current color: Purple");
        }
        if (tempDrop.getEffectColor().equals(Color.BLACK)) {
            dropSettings2 = new ItemStack(Material.BLACK_WOOL);
            dropSettings2Meta = dropSettings2.getItemMeta();
            dropSettings2Meta.setDisplayName("§8Current color: Black");
        }
        if (tempDrop.getEffectColor().equals(Color.WHITE)) {
            dropSettings2 = new ItemStack(Material.WHITE_WOOL);
            dropSettings2Meta = dropSettings2.getItemMeta();
            dropSettings2Meta.setDisplayName("§7Current color: White");
        }

        List<String> tempList3 = new ArrayList<>();
        tempList3.add("§aClick to change color");
        dropSettings2Meta.setLore(tempList3);
        dropSettings2.setItemMeta(dropSettings2Meta);
        customDropInfo.setItem(34, dropSettings2);

        return customDropInfo;
    }

    static Inventory CreateBlockSelection (int pageNumber) {
        Inventory blockSelect = Bukkit.createInventory(null, 54, "§2[CustomDrops] Block selection");

        blockSelect = GreenFrame(blockSelect);

        ItemStack frameItem1 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta frameItem1Meta = frameItem1.getItemMeta();
        frameItem1Meta.setDisplayName("§aBack to main menu");
        frameItem1.setItemMeta(frameItem1Meta);
        blockSelect.setItem(45, frameItem1);

        ItemStack frameItem2 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem2Meta = frameItem2.getItemMeta();
        frameItem2Meta.setDisplayName("§aCurrent page: " + pageNumber);
        frameItem2.setItemMeta(frameItem2Meta);
        blockSelect.setItem(52, frameItem2);

        ItemStack frameItem3 = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        ItemMeta frameItem3Meta = frameItem3.getItemMeta();
        frameItem3Meta.setDisplayName("§aAdd new block");
        frameItem3.setItemMeta(frameItem3Meta);
        blockSelect.setItem(8, frameItem3);

        if (CubMainPlugin2.editedBlocks != null) {
            if (CubMainPlugin2.editedBlocks.size() > 28 * pageNumber) {
                ItemStack nextPageMaterial = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
                ItemMeta nextPageMaterialMeta = nextPageMaterial.getItemMeta();
                nextPageMaterialMeta.setDisplayName("§aNext page");
                nextPageMaterial.setItemMeta(nextPageMaterialMeta);
                blockSelect.setItem(53, nextPageMaterial);
            }
            if (pageNumber > 1) {
                ItemStack previousPageMaterial = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
                ItemMeta previousPageMaterialMeta = previousPageMaterial.getItemMeta();
                previousPageMaterialMeta.setDisplayName("§aPrevious page");
                previousPageMaterial.setItemMeta(previousPageMaterialMeta);
                blockSelect.setItem(51, previousPageMaterial);
            }

            int tempInt1 = 0;
            int tempInt2 = 0;
            for (Material tempBlockMaterial : CubMainPlugin2.editedBlocks.keySet()) {
                if (tempInt2 > 27) {
                    break;
                }
                if (tempInt1 < 28 * (pageNumber - 1)) {
                    tempInt1++;
                    continue;
                }
                Material material = tempBlockMaterial;
                if (material.equals(Material.CARROTS)) {
                    tempBlockMaterial = Material.CARROT;
                } else if (material.equals(Material.BEETROOTS)) {
                    tempBlockMaterial = Material.BEETROOT;
                } else if (material.equals(Material.COCOA)) {
                    tempBlockMaterial = Material.COCOA_BEANS;
                } else if (material.equals(Material.POTATOES)) {
                    tempBlockMaterial = Material.POTATO;
                }
                ItemStack tempBlock = new ItemStack(tempBlockMaterial);
                blockSelect.setItem(InventoryInside(54).get(tempInt2), tempBlock);
                tempInt2++;
            }
        }

        return  blockSelect;
    }

    static Inventory CommandsList (CMP2Mob tempMob, CMP2CustomDrop tempDrop, int tempInt) {
        Inventory commandsList = Bukkit.createInventory(null, 54, "§2[CustomDrops] Commands list");

        commandsList = GreenFrame(commandsList);

        ItemStack frameItem0 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta frameItem0Meta = frameItem0.getItemMeta();
        frameItem0Meta.setDisplayName("§aBack to main menu");
        frameItem0.setItemMeta(frameItem0Meta);
        commandsList.setItem(45, frameItem0);

        ItemStack frameItem1 = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta frameItem1Meta = frameItem1.getItemMeta();
        frameItem1Meta.setDisplayName("§aBack to previous menu");
        frameItem1.setItemMeta(frameItem1Meta);
        commandsList.setItem(46, frameItem1);

        ItemStack frameItem2 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem2Meta = frameItem2.getItemMeta();
        frameItem2Meta.setDisplayName("§2Editing mob: " + tempMob.getName());
        List<String> tempList = new ArrayList<>();
        tempList.add("§2Drop number: §a" + tempInt);
        frameItem2Meta.setLore(tempList);
        frameItem2.setItemMeta(frameItem2Meta);
        commandsList.setItem(0, frameItem2);

        ItemStack commandItem = new ItemStack(Material.COMMAND_BLOCK);
        ItemMeta commandItemMeta = commandItem.getItemMeta();
        List<String> tempList1 = new ArrayList<>();
        tempList1.add("§cShift right click to delete");
        commandItemMeta.setLore(tempList1);

        int i = 0;
        if (tempDrop.getCommands() != null) {
            for (String tempCommand : tempDrop.getCommands()) {
                commandItemMeta.setDisplayName("§a/" + tempCommand);
                commandItem.setItemMeta(commandItemMeta);
                commandsList.setItem(InventoryInside(54).get(i), commandItem);
                i++;
            }
        }
        if (tempDrop.getCommands() == null || tempDrop.getCommands().size() < 28) {
            ItemStack addCommand = new ItemStack(Material.LIME_DYE);
            ItemMeta addCommandMeta = addCommand.getItemMeta();
            addCommandMeta.setDisplayName("§aClick to add a command");
            addCommand.setItemMeta(addCommandMeta);
            commandsList.setItem(InventoryInside(54).get(i), addCommand);
        }

        return commandsList;
    }

    static Inventory ListGlobalSettings () {
        Inventory settingsList = Bukkit.createInventory(null, 54, "§2[CustomDrops] Global settings");

        settingsList = GreenFrame(settingsList);

        ItemStack frameItem0 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta frameItem0Meta = frameItem0.getItemMeta();
        frameItem0Meta.setDisplayName("§aBack to main menu");
        frameItem0.setItemMeta(frameItem0Meta);
        settingsList.setItem(45, frameItem0);

        ItemStack tempStack1 = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta tempStackMeta1 = tempStack1.getItemMeta();
        tempStackMeta1.addEnchant(Enchantment.LOOT_BONUS_MOBS, 1, true);
        tempStackMeta1.setDisplayName("§aAdd extra chance per looting level");
        tempStack1.setItemMeta(tempStackMeta1);
        settingsList.setItem(10, tempStack1);

        ItemStack tempStack2 = new ItemStack(Material.GOLDEN_PICKAXE);
        ItemMeta tempStackMeta2 = tempStack2.getItemMeta();
        tempStackMeta2.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, true);
        tempStackMeta2.setDisplayName("§aAdd extra chance per fortune level");
        tempStack2.setItemMeta(tempStackMeta2);
        settingsList.setItem(19, tempStack2);

        ItemStack greenConcrete = new ItemStack(Material.LIME_CONCRETE);
        ItemMeta greenConcreteMeta = greenConcrete.getItemMeta();

        ItemStack redConcrete = new ItemStack(Material.RED_CONCRETE);
        ItemMeta redConcreteMeta = redConcrete.getItemMeta();

        {
            if (CubMainPlugin2.globalSettings.getLootingAll()) {
                greenConcreteMeta.setDisplayName("§aLooting allowed, click to deny");
                greenConcrete.setItemMeta(greenConcreteMeta);
                settingsList.setItem(11, greenConcrete);
            } else {
                redConcreteMeta.setDisplayName("§cLooting denied, click to allow");
                redConcrete.setItemMeta(redConcreteMeta);
                settingsList.setItem(11, redConcrete);
            }

            ItemStack chanceStack1 = new ItemStack(Material.PAPER);
            ItemMeta chanceStackMeta1 = chanceStack1.getItemMeta();
            if (CubMainPlugin2.globalSettings.getLootingMultiplier()) {
                chanceStackMeta1.setDisplayName("§aAdd a multiplier per level");
                List<String> tempList1 = new ArrayList<>();
                tempList1.add("§2Current chance per lvl: §ax" + CubMainPlugin2.globalSettings.getLootingM() / 10 + "." + CubMainPlugin2.globalSettings.getLootingM() % 10);
                tempList1.add("§cRight click to switch mode");
                chanceStackMeta1.setLore(tempList1);
                chanceStack1.setItemMeta(chanceStackMeta1);
                settingsList.setItem(12, chanceStack1);
            } else {
                chanceStackMeta1.setDisplayName("§aAdd a percentage per level");
                List<String> tempList1 = new ArrayList<>();
                tempList1.add("§2Current chance per lvl: §a+" + CubMainPlugin2.globalSettings.getLootingA() / 10 + "." + CubMainPlugin2.globalSettings.getLootingA() % 10 + "%");
                tempList1.add("§cRight click to switch mode");
                chanceStackMeta1.setLore(tempList1);
                chanceStack1.setItemMeta(chanceStackMeta1);
                settingsList.setItem(12, chanceStack1);
            }

            if (CubMainPlugin2.globalSettings.getLootingMultiplier()) {
                if (CubMainPlugin2.globalSettings.getLootingM() > 0) {
                    ItemStack tempStack3 = new ItemStack(Material.RED_CONCRETE);
                    ItemMeta tempStackMeta3 = tempStack3.getItemMeta();
                    tempStackMeta3.setDisplayName("§cDecrease chance by 0.1");
                    tempStack3.setItemMeta(tempStackMeta3);
                    settingsList.setItem(15, tempStack3);
                    if (CubMainPlugin2.globalSettings.getLootingM() > 9) {
                        ItemStack tempStack4 = new ItemStack(Material.RED_CONCRETE);
                        ItemMeta tempStackMeta4 = tempStack3.getItemMeta();
                        tempStackMeta4.setDisplayName("§cDecrease chance by 1");
                        tempStack4.setItemMeta(tempStackMeta4);
                        settingsList.setItem(16, tempStack4);
                    }
                }
                if (CubMainPlugin2.globalSettings.getLootingM() < 1000) {
                    ItemStack tempStack3 = new ItemStack(Material.LIME_CONCRETE);
                    ItemMeta tempStackMeta3 = tempStack3.getItemMeta();
                    tempStackMeta3.setDisplayName("§aIncrease chance by 0.1");
                    tempStack3.setItemMeta(tempStackMeta3);
                    settingsList.setItem(14, tempStack3);
                    if (CubMainPlugin2.globalSettings.getLootingM() < 991) {
                        ItemStack tempStack4 = new ItemStack(Material.LIME_CONCRETE);
                        ItemMeta tempStackMeta4 = tempStack3.getItemMeta();
                        tempStackMeta4.setDisplayName("§aIncrease chance by 1");
                        tempStack4.setItemMeta(tempStackMeta4);
                        settingsList.setItem(13, tempStack4);
                    }
                }
            } else {
                if (CubMainPlugin2.globalSettings.getLootingA() > 0) {
                    ItemStack tempStack3 = new ItemStack(Material.RED_CONCRETE);
                    ItemMeta tempStackMeta3 = tempStack3.getItemMeta();
                    tempStackMeta3.setDisplayName("§cDecrease chance by 0.1");
                    tempStack3.setItemMeta(tempStackMeta3);
                    settingsList.setItem(15, tempStack3);
                    if (CubMainPlugin2.globalSettings.getLootingA() > 9) {
                        ItemStack tempStack4 = new ItemStack(Material.RED_CONCRETE);
                        ItemMeta tempStackMeta4 = tempStack3.getItemMeta();
                        tempStackMeta4.setDisplayName("§cDecrease chance by 1");
                        tempStack4.setItemMeta(tempStackMeta4);
                        settingsList.setItem(16, tempStack4);
                    }
                }
                if (CubMainPlugin2.globalSettings.getLootingA() < 1000) {
                    ItemStack tempStack3 = new ItemStack(Material.LIME_CONCRETE);
                    ItemMeta tempStackMeta3 = tempStack3.getItemMeta();
                    tempStackMeta3.setDisplayName("§aIncrease chance by 0.1");
                    tempStack3.setItemMeta(tempStackMeta3);
                    settingsList.setItem(14, tempStack3);
                    if (CubMainPlugin2.globalSettings.getLootingA() < 991) {
                        ItemStack tempStack4 = new ItemStack(Material.LIME_CONCRETE);
                        ItemMeta tempStackMeta4 = tempStack3.getItemMeta();
                        tempStackMeta4.setDisplayName("§aIncrease chance by 1");
                        tempStack4.setItemMeta(tempStackMeta4);
                        settingsList.setItem(13, tempStack4);
                    }
                }
            }
        }

        {
            if (CubMainPlugin2.globalSettings.getFortuneAll()) {
                greenConcreteMeta.setDisplayName("§aFortune allowed, click to deny");
                greenConcrete.setItemMeta(greenConcreteMeta);
                settingsList.setItem(20, greenConcrete);
            } else {
                redConcreteMeta.setDisplayName("§cFortune denied, click to allow");
                redConcrete.setItemMeta(redConcreteMeta);
                settingsList.setItem(20, redConcrete);
            }

            ItemStack chanceStack1 = new ItemStack(Material.PAPER);
            ItemMeta chanceStackMeta1 = chanceStack1.getItemMeta();
            if (CubMainPlugin2.globalSettings.getFortuneMultiplier()) {
                chanceStackMeta1.setDisplayName("§aAdd a multiplier per level");
                List<String> tempList1 = new ArrayList<>();
                tempList1.add("§2Current chance per lvl: §ax" + CubMainPlugin2.globalSettings.getFortuneM() / 10 + "." + CubMainPlugin2.globalSettings.getFortuneM() % 10);
                tempList1.add("§cRight click to switch mode");
                chanceStackMeta1.setLore(tempList1);
                chanceStack1.setItemMeta(chanceStackMeta1);
                settingsList.setItem(21, chanceStack1);
            } else {
                chanceStackMeta1.setDisplayName("§aAdd a percentage per level");
                List<String> tempList1 = new ArrayList<>();
                tempList1.add("§2Current chance per lvl: §a+" + CubMainPlugin2.globalSettings.getFortuneA() / 10 + "." + CubMainPlugin2.globalSettings.getFortuneA() % 10 + "%");
                tempList1.add("§cRight click to switch mode");
                chanceStackMeta1.setLore(tempList1);
                chanceStack1.setItemMeta(chanceStackMeta1);
                settingsList.setItem(21, chanceStack1);
            }

            if (CubMainPlugin2.globalSettings.getFortuneMultiplier()) {
                if (CubMainPlugin2.globalSettings.getFortuneM() > 0) {
                    ItemStack tempStack3 = new ItemStack(Material.RED_CONCRETE);
                    ItemMeta tempStackMeta3 = tempStack3.getItemMeta();
                    tempStackMeta3.setDisplayName("§cDecrease chance by 0.1");
                    tempStack3.setItemMeta(tempStackMeta3);
                    settingsList.setItem(24, tempStack3);
                    if (CubMainPlugin2.globalSettings.getFortuneM() > 9) {
                        ItemStack tempStack4 = new ItemStack(Material.RED_CONCRETE);
                        ItemMeta tempStackMeta4 = tempStack3.getItemMeta();
                        tempStackMeta4.setDisplayName("§cDecrease chance by 1");
                        tempStack4.setItemMeta(tempStackMeta4);
                        settingsList.setItem(25, tempStack4);
                    }
                }
                if (CubMainPlugin2.globalSettings.getFortuneM() < 1000) {
                    ItemStack tempStack3 = new ItemStack(Material.LIME_CONCRETE);
                    ItemMeta tempStackMeta3 = tempStack3.getItemMeta();
                    tempStackMeta3.setDisplayName("§aIncrease chance by 0.1");
                    tempStack3.setItemMeta(tempStackMeta3);
                    settingsList.setItem(23, tempStack3);
                    if (CubMainPlugin2.globalSettings.getFortuneM() < 991) {
                        ItemStack tempStack4 = new ItemStack(Material.LIME_CONCRETE);
                        ItemMeta tempStackMeta4 = tempStack3.getItemMeta();
                        tempStackMeta4.setDisplayName("§aIncrease chance by 1");
                        tempStack4.setItemMeta(tempStackMeta4);
                        settingsList.setItem(22, tempStack4);
                    }
                }
            } else {
                if (CubMainPlugin2.globalSettings.getFortuneA() > 0) {
                    ItemStack tempStack3 = new ItemStack(Material.RED_CONCRETE);
                    ItemMeta tempStackMeta3 = tempStack3.getItemMeta();
                    tempStackMeta3.setDisplayName("§cDecrease chance by 0.1");
                    tempStack3.setItemMeta(tempStackMeta3);
                    settingsList.setItem(24, tempStack3);
                    if (CubMainPlugin2.globalSettings.getFortuneA() > 9) {
                        ItemStack tempStack4 = new ItemStack(Material.RED_CONCRETE);
                        ItemMeta tempStackMeta4 = tempStack3.getItemMeta();
                        tempStackMeta4.setDisplayName("§cDecrease chance by 1");
                        tempStack4.setItemMeta(tempStackMeta4);
                        settingsList.setItem(25, tempStack4);
                    }
                }
                if (CubMainPlugin2.globalSettings.getFortuneA() < 1000) {
                    ItemStack tempStack3 = new ItemStack(Material.LIME_CONCRETE);
                    ItemMeta tempStackMeta3 = tempStack3.getItemMeta();
                    tempStackMeta3.setDisplayName("§aIncrease chance by 0.1");
                    tempStack3.setItemMeta(tempStackMeta3);
                    settingsList.setItem(23, tempStack3);
                    if (CubMainPlugin2.globalSettings.getFortuneA() < 991) {
                        ItemStack tempStack4 = new ItemStack(Material.LIME_CONCRETE);
                        ItemMeta tempStackMeta4 = tempStack3.getItemMeta();
                        tempStackMeta4.setDisplayName("§aIncrease chance by 1");
                        tempStack4.setItemMeta(tempStackMeta4);
                        settingsList.setItem(22, tempStack4);
                    }
                }
            }
        }

        ItemStack tempStack3 = new ItemStack(Material.BOOKSHELF);
        ItemMeta tempStackMeta3 = tempStack3.getItemMeta();
        tempStackMeta3.setDisplayName("§aList of worlds, click to manage");
        tempStack3.setItemMeta(tempStackMeta3);
        settingsList.setItem(37, tempStack3);

        ItemStack tempStack35 = new ItemStack(Material.SPAWNER);
        ItemMeta tempStackMeta35 = tempStack35.getItemMeta();
        if (CubMainPlugin2.afkFarm) {
            tempStackMeta35.setDisplayName("§aAfk farms: Currently enabled");
            List<String> tempLore = new ArrayList<>();
            tempLore.add("§aMob drops will spawn when killed by anything.");
            tempStackMeta35.setLore(tempLore);
        } else {
            tempStackMeta35.setDisplayName("§cAfk farms: Currently disabled");
            List<String> tempLore = new ArrayList<>();
            tempLore.add("§cMob drops will spawn only when killed by a player.");
            tempStackMeta35.setLore(tempLore);
        }
        tempStack35.setItemMeta(tempStackMeta35);
        settingsList.setItem(38, tempStack35);

        ItemStack tempStack4 = new ItemStack(Material.ENCHANTING_TABLE);
        ItemMeta tempStackMeta4 = tempStack4.getItemMeta();
        tempStackMeta4.setDisplayName("§aAdd new booster");
        tempStack4.setItemMeta(tempStackMeta4);
        settingsList.setItem(28, tempStack4);

        ItemStack tempStack5 = new ItemStack(Material.PAPER);
        ItemMeta tempStackMeta5 = tempStack5.getItemMeta();
        tempStackMeta5.setDisplayName("§aList current boosters");
        tempStack5.setItemMeta(tempStackMeta5);
        settingsList.setItem(29, tempStack5);

        return settingsList;
    }

    static Inventory ListGlobalWorlds (Player player, int lastPage) {
        Inventory listWorlds = Bukkit.createInventory(null, 54, "§2[CustomDrops] Worlds list");

        listWorlds = GreenFrame(listWorlds);

        ItemStack frameItem0 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta frameItem0Meta = frameItem0.getItemMeta();
        frameItem0Meta.setDisplayName("§aBack to main menu");
        frameItem0.setItemMeta(frameItem0Meta);
        listWorlds.setItem(45, frameItem0);

        ItemStack frameItem1 = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta frameItem1Meta = frameItem1.getItemMeta();
        frameItem1Meta.setDisplayName("§aBack to previous menu");
        frameItem1.setItemMeta(frameItem1Meta);
        listWorlds.setItem(46, frameItem1);

        ItemStack frameItem4 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem4Meta = frameItem4.getItemMeta();
        frameItem4Meta.setDisplayName("§aCurrent page: " + lastPage);
        frameItem4.setItemMeta(frameItem4Meta);
        listWorlds.setItem(52, frameItem4);

        if (player.getServer().getWorlds().size() > 28 * lastPage) {
            ItemStack nextPageMaterial = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
            ItemMeta nextPageMaterialMeta = nextPageMaterial.getItemMeta();
            nextPageMaterialMeta.setDisplayName("§aNext page");
            nextPageMaterial.setItemMeta(nextPageMaterialMeta);
            listWorlds.setItem(53, nextPageMaterial);
        }
        if (lastPage > 1) {
            ItemStack previousPageMaterial = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
            ItemMeta previousPageMaterialMeta = previousPageMaterial.getItemMeta();
            previousPageMaterialMeta.setDisplayName("§aPrevious page");
            previousPageMaterial.setItemMeta(previousPageMaterialMeta);
            listWorlds.setItem(51, previousPageMaterial);
        }

        int tempInt1 = 0;
        int tempInt2 = 0;
        for (World tempWorld : player.getServer().getWorlds()) {
            if (tempInt2 > 27) {
                break;
            }
            if (tempInt1 < 28 * (lastPage - 1)) {
                tempInt1++;
                continue;
            }

            ItemStack worldItemStack;
            ItemMeta worldItemStackMeta;
            if (CubMainPlugin2.globalSettings.getWorlds() != null && CubMainPlugin2.globalSettings.getWorlds().contains(tempWorld)) {
                worldItemStack = new ItemStack(Material.LIME_BANNER);
                worldItemStackMeta = worldItemStack.getItemMeta();
                worldItemStackMeta.setDisplayName(tempWorld.getName());
                List<String> tempList = new ArrayList<>();
                tempList.add("§aEnabled, click to disable");
                worldItemStackMeta.setLore(tempList);
            } else {
                worldItemStack = new ItemStack(Material.RED_BANNER);
                worldItemStackMeta = worldItemStack.getItemMeta();
                worldItemStackMeta.setDisplayName(tempWorld.getName());
                List<String> tempList = new ArrayList<>();
                tempList.add("§cDisabled, click to enable");
                worldItemStackMeta.setLore(tempList);
            }
            worldItemStack.setItemMeta(worldItemStackMeta);

            listWorlds.setItem(InventoryInside(54).get(tempInt2), worldItemStack);
            tempInt2++;
        }

        return listWorlds;
    }

    static Inventory ListDropWorlds (Player player, CMP2Mob tempMob, CMP2CustomDrop tempDrop, int dropInt, int lastPage) {
        Inventory listWorlds = Bukkit.createInventory(null, 54, "§2[CustomDrops] Drop worlds");

        listWorlds = GreenFrame(listWorlds);

        ItemStack frameItem0 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta frameItem0Meta = frameItem0.getItemMeta();
        frameItem0Meta.setDisplayName("§aBack to main menu");
        frameItem0.setItemMeta(frameItem0Meta);
        listWorlds.setItem(45, frameItem0);

        ItemStack frameItem1 = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta frameItem1Meta = frameItem1.getItemMeta();
        frameItem1Meta.setDisplayName("§aBack to previous menu");
        frameItem1.setItemMeta(frameItem1Meta);
        listWorlds.setItem(46, frameItem1);

        ItemStack frameItem4 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem4Meta = frameItem4.getItemMeta();
        frameItem4Meta.setDisplayName("§aCurrent page: " + lastPage);
        frameItem4.setItemMeta(frameItem4Meta);
        listWorlds.setItem(52, frameItem4);

        ItemStack frameItem2 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem2Meta = frameItem2.getItemMeta();
        frameItem2Meta.setDisplayName("§2Editing mob: " + tempMob.getName());
        List<String> tempList = new ArrayList<>();
        tempList.add("§2Drop number: §a" + dropInt);
        frameItem2Meta.setLore(tempList);
        frameItem2.setItemMeta(frameItem2Meta);
        listWorlds.setItem(0, frameItem2);

        if (player.getServer().getWorlds().size() > 28 * lastPage) {
            ItemStack nextPageMaterial = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
            ItemMeta nextPageMaterialMeta = nextPageMaterial.getItemMeta();
            nextPageMaterialMeta.setDisplayName("§aNext page");
            nextPageMaterial.setItemMeta(nextPageMaterialMeta);
            listWorlds.setItem(53, nextPageMaterial);
        }
        if (lastPage > 1) {
            ItemStack previousPageMaterial = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
            ItemMeta previousPageMaterialMeta = previousPageMaterial.getItemMeta();
            previousPageMaterialMeta.setDisplayName("§aPrevious page");
            previousPageMaterial.setItemMeta(previousPageMaterialMeta);
            listWorlds.setItem(51, previousPageMaterial);
        }

        int tempInt1 = 0;
        int tempInt2 = 0;
        for (World tempWorld : player.getServer().getWorlds()) {
            if (tempInt2 > 27) {
                break;
            }
            if (tempInt1 < 28 * (lastPage - 1)) {
                tempInt1++;
                continue;
            }

            ItemStack worldItemStack;
            ItemMeta worldItemStackMeta;
            if (tempDrop.getWorlds() != null && tempDrop.getWorlds().contains(tempWorld)) {
                worldItemStack = new ItemStack(Material.LIME_BANNER);
                worldItemStackMeta = worldItemStack.getItemMeta();
                worldItemStackMeta.setDisplayName(tempWorld.getName());
                List<String> tempList1 = new ArrayList<>();
                tempList1.add("§aEnabled, click to disable");
                worldItemStackMeta.setLore(tempList1);
            } else {
                worldItemStack = new ItemStack(Material.RED_BANNER);
                worldItemStackMeta = worldItemStack.getItemMeta();
                worldItemStackMeta.setDisplayName(tempWorld.getName());
                List<String> tempList1 = new ArrayList<>();
                tempList1.add("§cDisabled, click to enable");
                worldItemStackMeta.setLore(tempList1);
            }
            worldItemStack.setItemMeta(worldItemStackMeta);

            listWorlds.setItem(InventoryInside(54).get(tempInt2), worldItemStack);
            tempInt2++;
        }

        return listWorlds;
    }

    static Inventory ListVanillaWorlds (Player player, CMP2Mob tempMob, int lastPage) {
        Inventory listWorlds = Bukkit.createInventory(null, 54, "§2[CustomDrops] Vanilla drops");

        listWorlds = GreenFrame(listWorlds);

        ItemStack frameItem0 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta frameItem0Meta = frameItem0.getItemMeta();
        frameItem0Meta.setDisplayName("§aBack to main menu");
        frameItem0.setItemMeta(frameItem0Meta);
        listWorlds.setItem(45, frameItem0);

        ItemStack frameItem1 = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta frameItem1Meta = frameItem1.getItemMeta();
        frameItem1Meta.setDisplayName("§aBack to previous menu");
        frameItem1.setItemMeta(frameItem1Meta);
        listWorlds.setItem(46, frameItem1);

        ItemStack frameItem4 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem4Meta = frameItem4.getItemMeta();
        frameItem4Meta.setDisplayName("§aCurrent page: " + lastPage);
        frameItem4.setItemMeta(frameItem4Meta);
        listWorlds.setItem(52, frameItem4);

        ItemStack frameItem2 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem2Meta = frameItem2.getItemMeta();
        frameItem2Meta.setDisplayName("§2Editing mob: " + tempMob.getName());
        frameItem2.setItemMeta(frameItem2Meta);
        listWorlds.setItem(0, frameItem2);

        if (player.getServer().getWorlds().size() > 28 * lastPage) {
            ItemStack nextPageMaterial = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
            ItemMeta nextPageMaterialMeta = nextPageMaterial.getItemMeta();
            nextPageMaterialMeta.setDisplayName("§aNext page");
            nextPageMaterial.setItemMeta(nextPageMaterialMeta);
            listWorlds.setItem(53, nextPageMaterial);
        }
        if (lastPage > 1) {
            ItemStack previousPageMaterial = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
            ItemMeta previousPageMaterialMeta = previousPageMaterial.getItemMeta();
            previousPageMaterialMeta.setDisplayName("§aPrevious page");
            previousPageMaterial.setItemMeta(previousPageMaterialMeta);
            listWorlds.setItem(51, previousPageMaterial);
        }

        int tempInt1 = 0;
        int tempInt2 = 0;
        for (World tempWorld : player.getServer().getWorlds()) {
            if (tempInt2 > 27) {
                break;
            }
            if (tempInt1 < 28 * (lastPage - 1)) {
                tempInt1++;
                continue;
            }

            ItemStack worldItemStack;
            ItemMeta worldItemStackMeta;
            if (tempMob.getVanillaDrops() != null && tempMob.getVanillaDrops().contains(tempWorld)) {
                worldItemStack = new ItemStack(Material.RED_BANNER);
                worldItemStackMeta = worldItemStack.getItemMeta();
                worldItemStackMeta.setDisplayName(tempWorld.getName());
                List<String> tempList1 = new ArrayList<>();
                tempList1.add("§cDisabled, click to enable");
                worldItemStackMeta.setLore(tempList1);
            } else {
                worldItemStack = new ItemStack(Material.LIME_BANNER);
                worldItemStackMeta = worldItemStack.getItemMeta();
                worldItemStackMeta.setDisplayName(tempWorld.getName());
                List<String> tempList1 = new ArrayList<>();
                tempList1.add("§aEnabled, click to disable");
                worldItemStackMeta.setLore(tempList1);
            }
            worldItemStack.setItemMeta(worldItemStackMeta);

            listWorlds.setItem(InventoryInside(54).get(tempInt2), worldItemStack);
            tempInt2++;
        }

        return listWorlds;
    }

    static Inventory AddNewBlock (Player player) {
        Inventory chooseDropInv = Bukkit.createInventory(null, 54, "§2[CustomDrops] Choose a block");

        chooseDropInv = GreenFrame(chooseDropInv);

        ItemStack frameItem0 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta frameItem0Meta = frameItem0.getItemMeta();
        frameItem0Meta.setDisplayName("§aBack to main menu");
        frameItem0.setItemMeta(frameItem0Meta);
        chooseDropInv.setItem(45, frameItem0);

        ItemStack frameItem1 = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta frameItem1Meta = frameItem1.getItemMeta();
        frameItem1Meta.setDisplayName("§aBack to previous menu");
        frameItem1.setItemMeta(frameItem1Meta);
        chooseDropInv.setItem(46, frameItem1);

        Inventory playerInv = player.getInventory();

        for (int i = 0; i < 36; i++) {
            if (playerInv.getItem(i) == null) {
                chooseDropInv.setItem(i + 9, null);
            } else {
                chooseDropInv.setItem(i + 9, playerInv.getItem(i));
            }
        }

        return chooseDropInv;
    }

    static Inventory CreateBlockInfo (CMP2Block block) {
        Inventory mobInfo = Bukkit.createInventory(null, 27, "§2[CustomDrops] Edit a block");

        mobInfo = GreenFrame(mobInfo);

        ItemStack frameItem1 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta frameItem1Meta = frameItem1.getItemMeta();
        frameItem1Meta.setDisplayName("§aBack to main menu");
        frameItem1.setItemMeta(frameItem1Meta);
        mobInfo.setItem(18, frameItem1);

        ItemStack frameItem2 = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta frameItem2Meta = frameItem2.getItemMeta();
        frameItem2Meta.setDisplayName("§aBack to previous menu");
        frameItem2.setItemMeta(frameItem2Meta);
        mobInfo.setItem(19, frameItem2);

        ItemStack frameItem25 = new ItemStack(Material.BARRIER);
        ItemMeta frameItem25Meta = frameItem25.getItemMeta();
        frameItem25Meta.setDisplayName("§aDelete block");
        List<String> frameItem25Lore = new ArrayList<>();
        frameItem25Lore.add("§aShift right click to delete this block");
        frameItem25Meta.setLore(frameItem25Lore);
        frameItem25.setItemMeta(frameItem25Meta);
        mobInfo.setItem(26, frameItem25);

        ItemStack frameItem4 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem4Meta = frameItem4.getItemMeta();
        frameItem4Meta.setDisplayName("§2Editing block: " + block.getBlock().name());
        frameItem4.setItemMeta(frameItem4Meta);
        mobInfo.setItem(0, frameItem4);

        ItemStack tempStack1 = new ItemStack(Material.BOOKSHELF);
        ItemMeta tempStackMeta1 = tempStack1.getItemMeta();
        tempStackMeta1.setDisplayName("§aToggle vanilla drops");
        tempStack1.setItemMeta(tempStackMeta1);
        mobInfo.setItem(11, tempStack1);

        ItemStack invItem1 = new ItemStack(Material.GOLD_INGOT);
        ItemMeta invItem1Meta = invItem1.getItemMeta();
        invItem1Meta.setDisplayName("§aCustom drops");
        invItem1.setItemMeta(invItem1Meta);
        mobInfo.setItem(13, invItem1);

        ItemStack invItem2 = new ItemStack(Material.LIGHT_GRAY_CONCRETE);
        ItemMeta invItem2Meta = invItem2.getItemMeta();
        invItem2Meta.setDisplayName("§aMaybe someday?");
        invItem2.setItemMeta(invItem2Meta);
        mobInfo.setItem(15, invItem2);

        return  mobInfo;
    }

    static Inventory ListVanillaWorldsBlock (Player player, CMP2Block block, int lastPage) {
        Inventory listWorlds = Bukkit.createInventory(null, 54, "§2[CustomDrops] Vanilla drops B");

        listWorlds = GreenFrame(listWorlds);

        ItemStack frameItem0 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta frameItem0Meta = frameItem0.getItemMeta();
        frameItem0Meta.setDisplayName("§aBack to main menu");
        frameItem0.setItemMeta(frameItem0Meta);
        listWorlds.setItem(45, frameItem0);

        ItemStack frameItem1 = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta frameItem1Meta = frameItem1.getItemMeta();
        frameItem1Meta.setDisplayName("§aBack to previous menu");
        frameItem1.setItemMeta(frameItem1Meta);
        listWorlds.setItem(46, frameItem1);

        ItemStack frameItem4 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem4Meta = frameItem4.getItemMeta();
        frameItem4Meta.setDisplayName("§aCurrent page: " + lastPage);
        frameItem4.setItemMeta(frameItem4Meta);
        listWorlds.setItem(52, frameItem4);

        ItemStack frameItem2 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem2Meta = frameItem2.getItemMeta();
        frameItem2Meta.setDisplayName("§2Editing block: " + block.getBlock().name());
        frameItem2.setItemMeta(frameItem2Meta);
        listWorlds.setItem(0, frameItem2);

        if (player.getServer().getWorlds().size() > 28 * lastPage) {
            ItemStack nextPageMaterial = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
            ItemMeta nextPageMaterialMeta = nextPageMaterial.getItemMeta();
            nextPageMaterialMeta.setDisplayName("§aNext page");
            nextPageMaterial.setItemMeta(nextPageMaterialMeta);
            listWorlds.setItem(53, nextPageMaterial);
        }
        if (lastPage > 1) {
            ItemStack previousPageMaterial = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
            ItemMeta previousPageMaterialMeta = previousPageMaterial.getItemMeta();
            previousPageMaterialMeta.setDisplayName("§aPrevious page");
            previousPageMaterial.setItemMeta(previousPageMaterialMeta);
            listWorlds.setItem(51, previousPageMaterial);
        }

        int tempInt1 = 0;
        int tempInt2 = 0;
        for (World tempWorld : player.getServer().getWorlds()) {
            if (tempInt2 > 27) {
                break;
            }
            if (tempInt1 < 28 * (lastPage - 1)) {
                tempInt1++;
                continue;
            }

            ItemStack worldItemStack;
            ItemMeta worldItemStackMeta;
            if (block.getVanillaDrops() != null && block.getVanillaDrops().contains(tempWorld)) {
                worldItemStack = new ItemStack(Material.RED_BANNER);
                worldItemStackMeta = worldItemStack.getItemMeta();
                worldItemStackMeta.setDisplayName(tempWorld.getName());
                List<String> tempList1 = new ArrayList<>();
                tempList1.add("§cDisabled, click to enable");
                worldItemStackMeta.setLore(tempList1);
            } else {
                worldItemStack = new ItemStack(Material.LIME_BANNER);
                worldItemStackMeta = worldItemStack.getItemMeta();
                worldItemStackMeta.setDisplayName(tempWorld.getName());
                List<String> tempList1 = new ArrayList<>();
                tempList1.add("§aEnabled, click to disable");
                worldItemStackMeta.setLore(tempList1);
            }
            worldItemStack.setItemMeta(worldItemStackMeta);

            listWorlds.setItem(InventoryInside(54).get(tempInt2), worldItemStack);
            tempInt2++;
        }

        return listWorlds;
    }

    static Inventory ListCustomDropsBlock (CMP2Block block, UUID tempUUID) {
        Inventory customDropsInv = Bukkit.createInventory(null, 54, "§2[CustomDrops] Custom drops B");

        customDropsInv = GreenFrame(customDropsInv);

        ItemStack frameItem0 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta frameItem0Meta = frameItem0.getItemMeta();
        frameItem0Meta.setDisplayName("§aBack to main menu");
        frameItem0.setItemMeta(frameItem0Meta);
        customDropsInv.setItem(45, frameItem0);

        ItemStack frameItem1 = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta frameItem1Meta = frameItem1.getItemMeta();
        frameItem1Meta.setDisplayName("§aBack to previous menu");
        frameItem1.setItemMeta(frameItem1Meta);
        customDropsInv.setItem(46, frameItem1);

        ItemStack frameItem2 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem2Meta = frameItem2.getItemMeta();
        frameItem2Meta.setDisplayName("§2Editing block: " + block.getBlock().name());
        frameItem2.setItemMeta(frameItem2Meta);
        customDropsInv.setItem(0, frameItem2);

        ItemStack frameItem3 = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        ItemMeta frameItem3Meta = frameItem3.getItemMeta();
        frameItem3Meta.setDisplayName("§aAdd new item");
        frameItem3.setItemMeta(frameItem3Meta);
        customDropsInv.setItem(8, frameItem3);

        ItemStack frameItem4 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem4Meta = frameItem4.getItemMeta();
        frameItem4Meta.setDisplayName("§aCurrent page: " + CubMainPlugin2.lastPage.get(tempUUID));
        frameItem4.setItemMeta(frameItem4Meta);
        customDropsInv.setItem(52, frameItem4);

        if (block.getCustomDrops() != null) {
            if (CubMainPlugin2.lastPage.get(tempUUID) == null || block.getCustomDrops().size() > 28 * (CubMainPlugin2.lastPage.get(tempUUID))) {
                ItemStack nextPageMaterial = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
                ItemMeta nextPageMaterialMeta = nextPageMaterial.getItemMeta();
                nextPageMaterialMeta.setDisplayName("§aNext page");
                nextPageMaterial.setItemMeta(nextPageMaterialMeta);
                customDropsInv.setItem(53, nextPageMaterial);
            }
            if (CubMainPlugin2.lastPage.get(tempUUID) != null && CubMainPlugin2.lastPage.get(tempUUID) > 1) {
                ItemStack previousPageMaterial = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
                ItemMeta previousPageMaterialMeta = previousPageMaterial.getItemMeta();
                previousPageMaterialMeta.setDisplayName("§aPrevious page");
                previousPageMaterial.setItemMeta(previousPageMaterialMeta);
                customDropsInv.setItem(51, previousPageMaterial);
            }

            int tempInt1 = 0;
            int tempInt2 = 0;
            for (CMP2CustomDrop customDrop : block.getCustomDrops()) {
                if (tempInt2 > 27) {
                    break;
                }
                if (tempInt1 < 28 * (CubMainPlugin2.lastPage.get(tempUUID) - 1)) {
                    tempInt1++;
                    continue;
                }
                ItemStack dropItemStack = customDrop.getDrop();
                customDropsInv.setItem(InventoryInside(54).get(tempInt2), dropItemStack);
                tempInt2++;
            }
        }

        return customDropsInv;
    }

    static Inventory AddCustomDropInventoryBlock (Player player, CMP2Block block) {
        Inventory chooseDropInv = Bukkit.createInventory(null, 54, "§2[CustomDrops] Choose an item B");

        chooseDropInv = GreenFrame(chooseDropInv);

        ItemStack frameItem0 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta frameItem0Meta = frameItem0.getItemMeta();
        frameItem0Meta.setDisplayName("§aBack to main menu");
        frameItem0.setItemMeta(frameItem0Meta);
        chooseDropInv.setItem(45, frameItem0);

        ItemStack frameItem1 = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta frameItem1Meta = frameItem1.getItemMeta();
        frameItem1Meta.setDisplayName("§aBack to previous menu");
        frameItem1.setItemMeta(frameItem1Meta);
        chooseDropInv.setItem(46, frameItem1);

        ItemStack frameItem2 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem2Meta = frameItem2.getItemMeta();
        frameItem2Meta.setDisplayName("§2Editing block: " + block.getBlock().name());
        frameItem2.setItemMeta(frameItem2Meta);
        chooseDropInv.setItem(0, frameItem2);

        Inventory playerInv = player.getInventory();

        for (int i = 0; i < 36; i++) {
            if (playerInv.getItem(i) == null) {
                chooseDropInv.setItem(i + 9, null);
            } else {
                chooseDropInv.setItem(i + 9, playerInv.getItem(i));
            }
        }

        return chooseDropInv;
    }

    static Inventory ShowCustomDropInfoBlock (CMP2Block block, CMP2CustomDrop tempDrop, int tempInt) {
        Inventory customDropInfo = Bukkit.createInventory(null, 45, "§2[CustomDrops] Drop settings B");

        customDropInfo = GreenFrame(customDropInfo);

        ItemStack frameItem0 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta frameItem0Meta = frameItem0.getItemMeta();
        frameItem0Meta.setDisplayName("§aBack to main menu");
        frameItem0.setItemMeta(frameItem0Meta);
        customDropInfo.setItem(36, frameItem0);

        ItemStack frameItem1 = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta frameItem1Meta = frameItem1.getItemMeta();
        frameItem1Meta.setDisplayName("§aBack to previous menu");
        frameItem1.setItemMeta(frameItem1Meta);
        customDropInfo.setItem(37, frameItem1);

        ItemStack frameItem2 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem2Meta = frameItem2.getItemMeta();
        frameItem2Meta.setDisplayName("§2Editing block: " + block.getBlock().name());
        List<String> tempList = new ArrayList<>();
        tempList.add("§2Drop number: §a" + tempInt);
        frameItem2Meta.setLore(tempList);
        frameItem2.setItemMeta(frameItem2Meta);
        customDropInfo.setItem(0, frameItem2);

        ItemStack frameItem3 = new ItemStack(Material.BARRIER);
        ItemMeta frameItem3Meta = frameItem0.getItemMeta();
        frameItem3Meta.setDisplayName("§aDelete drop");
        List<String> tempList4 = new ArrayList<>();
        tempList4.add("§aShift right click this to delete this item");
        frameItem3Meta.setLore(tempList4);
        frameItem3.setItemMeta(frameItem3Meta);
        customDropInfo.setItem(44, frameItem3);

        ItemStack frameItem4 = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        ItemMeta frameItem4Meta = frameItem4.getItemMeta();
        frameItem4Meta.setDisplayName("§aEdit commands");
        frameItem4.setItemMeta(frameItem4Meta);
        customDropInfo.setItem(8, frameItem4);

        /*ItemStack frameItem45 = new ItemStack(Material.MAGENTA_STAINED_GLASS_PANE);
        ItemMeta frameItem45Meta = frameItem45.getItemMeta();
        frameItem45Meta.setDisplayName("§aMore settings");
        frameItem45.setItemMeta(frameItem45Meta);
        customDropInfo.setItem(7, frameItem45);*/

        ItemStack customDrop = tempDrop.getDrop();
        customDropInfo.setItem(10, customDrop);

        ItemStack dropSettings = new ItemStack(Material.BOOKSHELF);
        ItemMeta dropSettingsMeta = dropSettings.getItemMeta();
        dropSettingsMeta.setDisplayName("§aDrop worlds list");
        List<String> tempList5 = new ArrayList<>();
        if (tempDrop.getWorldOverride()) {
            tempList5.add("§aEnabled: Overrides global settings worlds");
            tempList5.add("§cClick to disable");
            tempList5.add("§aShift click to edit worlds");
        } else {
            tempList5.add("§cDisabled: Using global settings worlds");
            tempList5.add("§aClick to enable");
        }
        dropSettingsMeta.setLore(tempList5);
        dropSettings.setItemMeta(dropSettingsMeta);
        customDropInfo.setItem(11, dropSettings);

        if (tempDrop.getDropChance() > 0) {
            ItemStack dropSettings0 = new ItemStack(Material.RED_CONCRETE);
            ItemMeta dropSettings0Meta = dropSettings0.getItemMeta();
            dropSettings0Meta.setDisplayName("§cDecrease chance by 0.01%");
            dropSettings0.setItemMeta(dropSettings0Meta);
            customDropInfo.setItem(22, dropSettings0);
            if (tempDrop.getDropChance() > 9) {
                ItemStack dropSettings1 = new ItemStack(Material.RED_CONCRETE);
                ItemMeta dropSettings1Meta = dropSettings1.getItemMeta();
                dropSettings1Meta.setDisplayName("§cDecrease chance by 0.1%");
                dropSettings1.setItemMeta(dropSettings1Meta);
                customDropInfo.setItem(23, dropSettings1);
                if (tempDrop.getDropChance() > 99) {
                    ItemStack dropSettings2 = new ItemStack(Material.RED_CONCRETE);
                    ItemMeta dropSettings2Meta = dropSettings2.getItemMeta();
                    dropSettings2Meta.setDisplayName("§cDecrease chance by 1%");
                    dropSettings2.setItemMeta(dropSettings2Meta);
                    customDropInfo.setItem(24, dropSettings2);
                    if (tempDrop.getDropChance() > 999) {
                        ItemStack dropSettings3 = new ItemStack(Material.RED_CONCRETE);
                        ItemMeta dropSettings3Meta = dropSettings3.getItemMeta();
                        dropSettings3Meta.setDisplayName("§cDecrease chance by 10%");
                        dropSettings3.setItemMeta(dropSettings3Meta);
                        customDropInfo.setItem(25, dropSettings3);
                    }
                }
            }
        }
        if (tempDrop.getDropChance() < 10000) {
            ItemStack dropSettings0 = new ItemStack(Material.GREEN_CONCRETE);
            ItemMeta dropSettings0Meta = dropSettings0.getItemMeta();
            dropSettings0Meta.setDisplayName("§aIncrease chance by 0.01%");
            dropSettings0.setItemMeta(dropSettings0Meta);
            customDropInfo.setItem(13, dropSettings0);
            if (tempDrop.getDropChance() < 9991) {
                ItemStack dropSettings1 = new ItemStack(Material.GREEN_CONCRETE);
                ItemMeta dropSettings1Meta = dropSettings1.getItemMeta();
                dropSettings1Meta.setDisplayName("§aIncrease chance by 0.1%");
                dropSettings1.setItemMeta(dropSettings1Meta);
                customDropInfo.setItem(14, dropSettings1);
                if (tempDrop.getDropChance() < 9901) {
                    ItemStack dropSettings2 = new ItemStack(Material.GREEN_CONCRETE);
                    ItemMeta dropSettings2Meta = dropSettings2.getItemMeta();
                    dropSettings2Meta.setDisplayName("§aIncrease chance by 1%");
                    dropSettings2.setItemMeta(dropSettings2Meta);
                    customDropInfo.setItem(15, dropSettings2);
                    if (tempDrop.getDropChance() < 9001) {
                        ItemStack dropSettings3 = new ItemStack(Material.GREEN_CONCRETE);
                        ItemMeta dropSettings3Meta = dropSettings3.getItemMeta();
                        dropSettings3Meta.setDisplayName("§aIncrease chance by 10%");
                        dropSettings3.setItemMeta(dropSettings3Meta);
                        customDropInfo.setItem(16, dropSettings3);
                    }
                }
            }
        }

        ItemStack dropSettings1 = new ItemStack(Material.PAPER);
        ItemMeta dropSettings1Meta = dropSettings1.getItemMeta();
        if (tempDrop.getDropChance() % 100 < 10) {
            dropSettings1Meta.setDisplayName("§2Current chance: §a" + (tempDrop.getDropChance() / 100) + ".0" + (tempDrop.getDropChance() % 100) + "%");
        } else {
            dropSettings1Meta.setDisplayName("§2Current chance: §a" + (tempDrop.getDropChance() / 100) + "." + (tempDrop.getDropChance() % 100) + "%");
        }
        dropSettings1.setItemMeta(dropSettings1Meta);
        customDropInfo.setItem(12, dropSettings1);

        ItemStack greenPane = new ItemStack(Material.LIME_CONCRETE);
        ItemMeta greenPaneMeta = greenPane.getItemMeta();
        List<String> tempList1 = new ArrayList<>();
        tempList1.add("§aAllowed, click to deny");
        greenPaneMeta.setLore(tempList1);
        ItemStack redPane = new ItemStack(Material.RED_CONCRETE);
        ItemMeta redPaneMeta = redPane.getItemMeta();
        List<String> tempList2 = new ArrayList<>();
        tempList2.add("§cDenied, click to allow");
        redPaneMeta.setLore(tempList2);

        if (tempDrop.getSpawnerDrop()) {
            greenPaneMeta.setDisplayName("§aPlaced drops");
            greenPane.setItemMeta(greenPaneMeta);
            customDropInfo.setItem(28, greenPane);
        } else {
            redPaneMeta.setDisplayName("§aPlaced drops");
            redPane.setItemMeta(redPaneMeta);
            customDropInfo.setItem(28, redPane);
        }

        if (tempDrop.getNaturalDrop()) {
            greenPaneMeta.setDisplayName("§aNatural drops");
            greenPane.setItemMeta(greenPaneMeta);
            customDropInfo.setItem(29, greenPane);
        } else {
            redPaneMeta.setDisplayName("§aNatural drops");
            redPane.setItemMeta(redPaneMeta);
            customDropInfo.setItem(29, redPane);
        }

        if (tempDrop.getEffect()) {
            greenPaneMeta.setDisplayName("§aDrop effect");
            greenPane.setItemMeta(greenPaneMeta);
            customDropInfo.setItem(33, greenPane);
        } else {
            redPaneMeta.setDisplayName("§aDrop effect");
            redPane.setItemMeta(redPaneMeta);
            customDropInfo.setItem(33, redPane);
        }

        ItemStack dropSettings2 = null;
        ItemMeta dropSettings2Meta = null;

        if (tempDrop.getEffectColor().equals(Color.RED)) {
            dropSettings2 = new ItemStack(Material.RED_WOOL);
            dropSettings2Meta = dropSettings2.getItemMeta();
            dropSettings2Meta.setDisplayName("§4Current color: Red");
        }
        if (tempDrop.getEffectColor().equals(Color.ORANGE)) {
            dropSettings2 = new ItemStack(Material.ORANGE_WOOL);
            dropSettings2Meta = dropSettings2.getItemMeta();
            dropSettings2Meta.setDisplayName("§6Current color: Orange");
        }
        if (tempDrop.getEffectColor().equals(Color.YELLOW)) {
            dropSettings2 = new ItemStack(Material.YELLOW_WOOL);
            dropSettings2Meta = dropSettings2.getItemMeta();
            dropSettings2Meta.setDisplayName("§eCurrent color: Yellow");
        }
        if (tempDrop.getEffectColor().equals(Color.LIME)) {
            dropSettings2 = new ItemStack(Material.LIME_WOOL);
            dropSettings2Meta = dropSettings2.getItemMeta();
            dropSettings2Meta.setDisplayName("§aCurrent color: Green");
        }
        if (tempDrop.getEffectColor().equals(Color.AQUA)) {
            dropSettings2 = new ItemStack(Material.LIGHT_BLUE_WOOL);
            dropSettings2Meta = dropSettings2.getItemMeta();
            dropSettings2Meta.setDisplayName("§bCurrent color: Blue");
        }
        if (tempDrop.getEffectColor().equals(Color.PURPLE)) {
            dropSettings2 = new ItemStack(Material.PURPLE_WOOL);
            dropSettings2Meta = dropSettings2.getItemMeta();
            dropSettings2Meta.setDisplayName("§5Current color: Purple");
        }
        if (tempDrop.getEffectColor().equals(Color.BLACK)) {
            dropSettings2 = new ItemStack(Material.BLACK_WOOL);
            dropSettings2Meta = dropSettings2.getItemMeta();
            dropSettings2Meta.setDisplayName("§8Current color: Black");
        }
        if (tempDrop.getEffectColor().equals(Color.WHITE)) {
            dropSettings2 = new ItemStack(Material.WHITE_WOOL);
            dropSettings2Meta = dropSettings2.getItemMeta();
            dropSettings2Meta.setDisplayName("§7Current color: White");
        }

        List<String> tempList3 = new ArrayList<>();
        tempList3.add("§aClick to change color");
        dropSettings2Meta.setLore(tempList3);
        dropSettings2.setItemMeta(dropSettings2Meta);
        customDropInfo.setItem(34, dropSettings2);

        return customDropInfo;
    }

    static Inventory CommandsListBlock (CMP2Block block, CMP2CustomDrop tempDrop, int tempInt) {
        Inventory commandsList = Bukkit.createInventory(null, 54, "§2[CustomDrops] Commands list B");

        commandsList = GreenFrame(commandsList);

        ItemStack frameItem0 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta frameItem0Meta = frameItem0.getItemMeta();
        frameItem0Meta.setDisplayName("§aBack to main menu");
        frameItem0.setItemMeta(frameItem0Meta);
        commandsList.setItem(45, frameItem0);

        ItemStack frameItem1 = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta frameItem1Meta = frameItem1.getItemMeta();
        frameItem1Meta.setDisplayName("§aBack to previous menu");
        frameItem1.setItemMeta(frameItem1Meta);
        commandsList.setItem(46, frameItem1);

        ItemStack frameItem2 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem2Meta = frameItem2.getItemMeta();
        frameItem2Meta.setDisplayName("§2Editing block: " + block.getBlock());
        List<String> tempList = new ArrayList<>();
        tempList.add("§2Drop number: §a" + tempInt);
        frameItem2Meta.setLore(tempList);
        frameItem2.setItemMeta(frameItem2Meta);
        commandsList.setItem(0, frameItem2);

        ItemStack commandItem = new ItemStack(Material.COMMAND_BLOCK);
        ItemMeta commandItemMeta = commandItem.getItemMeta();
        List<String> tempList1 = new ArrayList<>();
        tempList1.add("§cShift right click to delete");
        commandItemMeta.setLore(tempList1);

        int i = 0;
        if (tempDrop.getCommands() != null) {
            for (String tempCommand : tempDrop.getCommands()) {
                commandItemMeta.setDisplayName("§a/" + tempCommand);
                commandItem.setItemMeta(commandItemMeta);
                commandsList.setItem(InventoryInside(54).get(i), commandItem);
                i++;
            }
        }
        if (tempDrop.getCommands() == null || tempDrop.getCommands().size() < 28) {
            ItemStack addCommand = new ItemStack(Material.LIME_DYE);
            ItemMeta addCommandMeta = addCommand.getItemMeta();
            addCommandMeta.setDisplayName("§aClick to add a command");
            addCommand.setItemMeta(addCommandMeta);
            commandsList.setItem(InventoryInside(54).get(i), addCommand);
        }

        return commandsList;
    }

    static Inventory ListDropWorldsBlock (Player player, CMP2Block block, CMP2CustomDrop tempDrop, int dropInt, int lastPage) {
        Inventory listWorlds = Bukkit.createInventory(null, 54, "§2[CustomDrops] Drop worlds B");

        listWorlds = GreenFrame(listWorlds);

        ItemStack frameItem0 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta frameItem0Meta = frameItem0.getItemMeta();
        frameItem0Meta.setDisplayName("§aBack to main menu");
        frameItem0.setItemMeta(frameItem0Meta);
        listWorlds.setItem(45, frameItem0);

        ItemStack frameItem1 = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta frameItem1Meta = frameItem1.getItemMeta();
        frameItem1Meta.setDisplayName("§aBack to previous menu");
        frameItem1.setItemMeta(frameItem1Meta);
        listWorlds.setItem(46, frameItem1);

        ItemStack frameItem4 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem4Meta = frameItem4.getItemMeta();
        frameItem4Meta.setDisplayName("§aCurrent page: " + lastPage);
        frameItem4.setItemMeta(frameItem4Meta);
        listWorlds.setItem(52, frameItem4);

        ItemStack frameItem2 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem2Meta = frameItem2.getItemMeta();
        frameItem2Meta.setDisplayName("§2Editing block: " + block.getBlock());
        List<String> tempList = new ArrayList<>();
        tempList.add("§2Drop number: §a" + dropInt);
        frameItem2Meta.setLore(tempList);
        frameItem2.setItemMeta(frameItem2Meta);
        listWorlds.setItem(0, frameItem2);

        if (player.getServer().getWorlds().size() > 28 * lastPage) {
            ItemStack nextPageMaterial = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
            ItemMeta nextPageMaterialMeta = nextPageMaterial.getItemMeta();
            nextPageMaterialMeta.setDisplayName("§aNext page");
            nextPageMaterial.setItemMeta(nextPageMaterialMeta);
            listWorlds.setItem(53, nextPageMaterial);
        }
        if (lastPage > 1) {
            ItemStack previousPageMaterial = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
            ItemMeta previousPageMaterialMeta = previousPageMaterial.getItemMeta();
            previousPageMaterialMeta.setDisplayName("§aPrevious page");
            previousPageMaterial.setItemMeta(previousPageMaterialMeta);
            listWorlds.setItem(51, previousPageMaterial);
        }

        int tempInt1 = 0;
        int tempInt2 = 0;
        for (World tempWorld : player.getServer().getWorlds()) {
            if (tempInt2 > 27) {
                break;
            }
            if (tempInt1 < 28 * (lastPage - 1)) {
                tempInt1++;
                continue;
            }

            ItemStack worldItemStack;
            ItemMeta worldItemStackMeta;
            if (tempDrop.getWorlds() != null && tempDrop.getWorlds().contains(tempWorld)) {
                worldItemStack = new ItemStack(Material.LIME_BANNER);
                worldItemStackMeta = worldItemStack.getItemMeta();
                worldItemStackMeta.setDisplayName(tempWorld.getName());
                List<String> tempList1 = new ArrayList<>();
                tempList1.add("§aEnabled, click to disable");
                worldItemStackMeta.setLore(tempList1);
            } else {
                worldItemStack = new ItemStack(Material.RED_BANNER);
                worldItemStackMeta = worldItemStack.getItemMeta();
                worldItemStackMeta.setDisplayName(tempWorld.getName());
                List<String> tempList1 = new ArrayList<>();
                tempList1.add("§cDisabled, click to enable");
                worldItemStackMeta.setLore(tempList1);
            }
            worldItemStack.setItemMeta(worldItemStackMeta);

            listWorlds.setItem(InventoryInside(54).get(tempInt2), worldItemStack);
            tempInt2++;
        }

        return listWorlds;
    }

    static Inventory MoreDropOptions (CMP2Mob tempMob, CMP2CustomDrop tempDrop, int dropInt) {
        Inventory customDropInfo = Bukkit.createInventory(null, 45, "§2[CustomDrops] More settings");

        customDropInfo = GreenFrame(customDropInfo);

        ItemStack frameItem0 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta frameItem0Meta = frameItem0.getItemMeta();
        frameItem0Meta.setDisplayName("§aBack to main menu");
        frameItem0.setItemMeta(frameItem0Meta);
        customDropInfo.setItem(36, frameItem0);

        ItemStack frameItem1 = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta frameItem1Meta = frameItem1.getItemMeta();
        frameItem1Meta.setDisplayName("§aBack to previous menu");
        frameItem1.setItemMeta(frameItem1Meta);
        customDropInfo.setItem(37, frameItem1);

        ItemStack frameItem2 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta frameItem2Meta = frameItem2.getItemMeta();
        frameItem2Meta.setDisplayName("§2Editing mob: " + tempMob.getName());
        List<String> tempList = new ArrayList<>();
        tempList.add("§2Drop number: §a" + dropInt);
        frameItem2Meta.setLore(tempList);
        frameItem2.setItemMeta(frameItem2Meta);
        customDropInfo.setItem(0, frameItem2);



        return customDropInfo;
    }

    static Inventory MoreDropOptionsBlock (CMP2Block tempBlock, CMP2CustomDrop tempDrop, int dropInt) {
        Inventory customDropInfo = Bukkit.createInventory(null, 45, "§2[CustomDrops] More settings B");

        return customDropInfo;
    }
}
