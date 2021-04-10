package com.gmail.cubitverde;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;

public class CMP2ListenersChat implements Listener {
    @EventHandler
    private void playerChat (AsyncPlayerChatEvent talk) {
        Player player = talk.getPlayer();

        if (CubMainPlugin2.addingCommand.keySet().contains(player.getUniqueId())) {
            talk.setCancelled(true);

            CMP2Mob tempMob = CubMainPlugin2.addingCommandMob.get(player.getUniqueId());
            CMP2CustomDrop tempDrop = tempMob.getCustomDrops().get(CubMainPlugin2.addingCommand.get(player.getUniqueId()));

            if (tempDrop.getCommands() == null) {
                ArrayList<String> tempArr = new ArrayList<>();
                tempArr.add(talk.getMessage());
                tempDrop.setCommands(tempArr);
            } else {
                ArrayList<String> tempArr = tempDrop.getCommands();
                tempArr.add(talk.getMessage());
                tempDrop.setCommands(tempArr);
            }

            ArrayList<CMP2CustomDrop> tempArr = tempMob.getCustomDrops();
            tempArr.set(CubMainPlugin2.addingCommand.get(player.getUniqueId()), tempDrop);
            tempMob.setCustomDrops(tempArr);
            CubMainPlugin2.mobNames.put(tempMob.getName(), tempMob);

            player.sendMessage("§2[CustomDrops] §aAdded command: §f/" + talk.getMessage());

            CubMainPlugin2.addingCommand.remove(player.getUniqueId());
            CubMainPlugin2.addingCommandMob.remove(player.getUniqueId());
            player.openInventory(CMP2Utilities.CommandsList(tempMob, tempDrop, CubMainPlugin2.addingCommand.get(player.getUniqueId())));
        }

        if (CubMainPlugin2.addingCommandB.keySet().contains(player.getUniqueId())) {
            talk.setCancelled(true);

            CMP2Block block = CubMainPlugin2.addingCommandBlock.get(player.getUniqueId());
            CMP2CustomDrop tempDrop = block.getCustomDrops().get(CubMainPlugin2.addingCommandB.get(player.getUniqueId()));

            if (tempDrop.getCommands() == null) {
                ArrayList<String> tempArr = new ArrayList<>();
                tempArr.add(talk.getMessage());
                tempDrop.setCommands(tempArr);
            } else {
                ArrayList<String> tempArr = tempDrop.getCommands();
                tempArr.add(talk.getMessage());
                tempDrop.setCommands(tempArr);
            }

            ArrayList<CMP2CustomDrop> tempArr = block.getCustomDrops();
            tempArr.set(CubMainPlugin2.addingCommandB.get(player.getUniqueId()), tempDrop);
            block.setCustomDrops(tempArr);
            CubMainPlugin2.editedBlocks.put(block.getBlock(), block);

            player.sendMessage("§2[CustomDrops] §aAdded command: §f/" + talk.getMessage());

            CubMainPlugin2.addingCommandB.remove(player.getUniqueId());
            CubMainPlugin2.addingCommandBlock.remove(player.getUniqueId());
            player.openInventory(CMP2Utilities.CommandsListBlock(block, tempDrop, CubMainPlugin2.addingCommandB.get(player.getUniqueId())));
        }

        if (CubMainPlugin2.addingBooster.contains(player.getUniqueId())) {
            talk.setCancelled(true);

            String message = talk.getMessage();

            if (message.equals("cancel")) {
                player.sendMessage("§aBooster creation cancelled.");
                CubMainPlugin2.addingBooster.remove(player.getUniqueId());
                return;
            }

            int i = 0;
            int firstBar = -1;
            int secondBar = -1;
            for (int j = 0; j < message.length(); j++) {
                if (message.charAt(j) == '-') {
                    i++;
                    if (firstBar != -1) {
                        secondBar = j;
                    } else {
                        firstBar = j;
                    }
                }
            }
            if (i != 2) {
                player.sendMessage("§cIncorrect format. Use: §apermission - time - boost");
                player.sendMessage("§cExample: §aluckyperms.novice - 15m - 1.5");
                return;
            }
            if (message.length() < secondBar + 1 || firstBar + 2 > secondBar - 1 || firstBar - 1 < 0) {
                player.sendMessage("§cIncorrect format. Use: §apermission - time - boost");
                player.sendMessage("§cExample: §aluckyperms.novice - 15m - 1.5");
                return;
            }

            String boostPermission = message.substring(0, firstBar - 1);
            String boostTime = message.substring(firstBar + 2, secondBar - 1);
            String boostBoost = message.substring(secondBar + 2);

            if (boostPermission.length() == 0 || boostTime.length() == 0 || boostBoost.length() == 0) {
                player.sendMessage("§cIncorrect format. Use: §apermission - time - boost");
                player.sendMessage("§cExample: §aluckyperms.novice - 15m - 1.5");
                return;
            }

            if (boostTime.charAt(boostTime.length() - 1) != 'h' && boostTime.charAt(boostTime.length() - 1) != 'm' && boostTime.charAt(boostTime.length() - 1) != 's') {
                player.sendMessage("§cIncorrect time. Use: §ah§c, §am§c, or §as");
                return;
            }
            int time;
            try {
                time = Integer.parseInt(boostTime.substring(0, boostTime.length() - 1));
            } catch (NumberFormatException exception) {
                player.sendMessage("§cIncorrect time number.");
                return;
            }
            if (boostTime.charAt(boostTime.length() - 1) == 'h') {
                time *= 3600;
            }
            if (boostTime.charAt(boostTime.length() - 1) == 'm') {
                time *= 60;
            }
            if (boostTime.charAt(boostTime.length() - 1) == 's') {
                time *= 1;
            }

            double boost;
            try {
                boost = Double.parseDouble(boostBoost);
            } catch (NumberFormatException exception) {
                player.sendMessage("§cIncorrect boost number.");
                return;
            }

            CubMainPlugin2.boosterTime.put(boostPermission, time);
            CubMainPlugin2.boosterBoost.put(boostPermission, boost);
            player.sendMessage("§aSuccessfully created boost");
            CubMainPlugin2.addingBooster.remove(player.getUniqueId());
        }
    }
}
