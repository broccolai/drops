package com.gmail.cubitverde;

import org.bstats.bukkit.Metrics;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class CubMainPlugin2 extends JavaPlugin {
    public static CubMainPlugin2 plugin;
    static ArrayList<Material> eggsMaterials = new ArrayList<>();
    static ArrayList<String> eggsNames = new ArrayList<>();
    static HashMap<String, CMP2Mob> mobNames = new HashMap<>();
    static HashMap<UUID, Integer> lastPage = new HashMap<>();
    static ArrayList<UUID> spawnerMobs = new ArrayList<>();
    static ArrayList<UUID> eggMobs = new ArrayList<>();
    static ArrayList<UUID> naturalMobs = new ArrayList<>();
    static ArrayList<UUID> bredMobs = new ArrayList<>();
    static HashMap<Material, CMP2Block> editedBlocks = new HashMap<>();
    static HashMap<UUID, Integer> addingCommand = new HashMap<>();
    static HashMap<UUID, CMP2Mob> addingCommandMob = new HashMap<>();
    static HashMap<UUID, Integer> addingCommandB = new HashMap<>();
    static HashMap<UUID, CMP2Block> addingCommandBlock = new HashMap<>();
    static CMP2GlobalSettings globalSettings = new CMP2GlobalSettings();
    static ArrayList<Block> spawnerBlocks = new ArrayList<>();
    static ArrayList<Firework> blockFireworks = new ArrayList<>();
    static HashMap<String, Integer> boosterTime = new HashMap<>();
    static HashMap<String, Double> boosterBoost = new HashMap<>();
    static ArrayList<UUID> addingBooster = new ArrayList<>();
    @Override
    public void onEnable() {
        plugin = this;
        int pluginId = 6508;
        Metrics metrics = new Metrics(this, pluginId);
        BukkitTask task1 = new CMP2Loop1s().runTaskTimer(this, 20, 20);
        CMP2ListenersInv listener1 = new CMP2ListenersInv();
        getServer().getPluginManager().registerEvents(listener1, this);
        CMP2ListenersKillmob listener2 = new CMP2ListenersKillmob();
        getServer().getPluginManager().registerEvents(listener2, this);
        CMP2ListenersSpawnmob listener3 = new CMP2ListenersSpawnmob();
        getServer().getPluginManager().registerEvents(listener3, this);
        CMP2ListenersChat listener4 = new CMP2ListenersChat();
        getServer().getPluginManager().registerEvents(listener4, this);
        CMP2ListenersBreakBlock listener5 = new CMP2ListenersBreakBlock();
        getServer().getPluginManager().registerEvents(listener5, this);
        CMP2ListenersPlaceBlock listener6 = new CMP2ListenersPlaceBlock();
        getServer().getPluginManager().registerEvents(listener6, this);
        CMP2ListenersGetDamaged listener7 = new CMP2ListenersGetDamaged();
        getServer().getPluginManager().registerEvents(listener7, this);

        globalSettings.setFortuneMultiplier(false);
        globalSettings.setLootingMultiplier(false);
        globalSettings.setFortuneAll(false);
        globalSettings.setLootingAll(false);
        globalSettings.setFortuneM(15);
        globalSettings.setLootingM(15);
        globalSettings.setFortuneA(10);
        globalSettings.setLootingA(10);

        eggsMaterials.add(Material.BAT_SPAWN_EGG);
        if (plugin.getServer().getVersion().contains("1.15")) {
            eggsMaterials.add(Material.BEE_SPAWN_EGG);
        }
        eggsMaterials.add(Material.BLAZE_SPAWN_EGG);
        if (plugin.getServer().getVersion().contains("1.15") || plugin.getServer().getVersion().contains("1.14")) {
            eggsMaterials.add(Material.CAT_SPAWN_EGG);
        }
        eggsMaterials.add(Material.CAVE_SPIDER_SPAWN_EGG);
        eggsMaterials.add(Material.CHICKEN_SPAWN_EGG);
        eggsMaterials.add(Material.COD_SPAWN_EGG);
        eggsMaterials.add(Material.COW_SPAWN_EGG);
        eggsMaterials.add(Material.CREEPER_SPAWN_EGG);
        eggsMaterials.add(Material.DOLPHIN_SPAWN_EGG);
        eggsMaterials.add(Material.DONKEY_SPAWN_EGG);
        eggsMaterials.add(Material.DROWNED_SPAWN_EGG);
        eggsMaterials.add(Material.ELDER_GUARDIAN_SPAWN_EGG);
        eggsMaterials.add(Material.DRAGON_BREATH);
        eggsMaterials.add(Material.ENDERMAN_SPAWN_EGG);
        eggsMaterials.add(Material.ENDERMITE_SPAWN_EGG);
        eggsMaterials.add(Material.EVOKER_SPAWN_EGG);
        if (plugin.getServer().getVersion().contains("1.15") || plugin.getServer().getVersion().contains("1.14")) {
            eggsMaterials.add(Material.FOX_SPAWN_EGG);
        }
        eggsMaterials.add(Material.GHAST_SPAWN_EGG);
        eggsMaterials.add(Material.GUARDIAN_SPAWN_EGG);
        eggsMaterials.add(Material.HORSE_SPAWN_EGG);
        eggsMaterials.add(Material.HUSK_SPAWN_EGG);
        eggsMaterials.add(Material.IRON_INGOT);
        eggsMaterials.add(Material.LLAMA_SPAWN_EGG);
        eggsMaterials.add(Material.MAGMA_CUBE_SPAWN_EGG);
        eggsMaterials.add(Material.MOOSHROOM_SPAWN_EGG);
        eggsMaterials.add(Material.MULE_SPAWN_EGG);
        eggsMaterials.add(Material.OCELOT_SPAWN_EGG);
        if (plugin.getServer().getVersion().contains("1.15") || plugin.getServer().getVersion().contains("1.14")) {
            eggsMaterials.add(Material.PANDA_SPAWN_EGG);
        }
        eggsMaterials.add(Material.PARROT_SPAWN_EGG);
        eggsMaterials.add(Material.PHANTOM_SPAWN_EGG);
        eggsMaterials.add(Material.PIG_SPAWN_EGG);
        if (plugin.getServer().getVersion().contains("1.15") || plugin.getServer().getVersion().contains("1.14")) {
            eggsMaterials.add(Material.PILLAGER_SPAWN_EGG);
        }
        eggsMaterials.add(Material.POLAR_BEAR_SPAWN_EGG);
        eggsMaterials.add(Material.PUFFERFISH_SPAWN_EGG);
        eggsMaterials.add(Material.RABBIT_SPAWN_EGG);
        if (plugin.getServer().getVersion().contains("1.15") || plugin.getServer().getVersion().contains("1.14")) {
            eggsMaterials.add(Material.RAVAGER_SPAWN_EGG);
        }
        eggsMaterials.add(Material.SALMON_SPAWN_EGG);
        eggsMaterials.add(Material.SHEEP_SPAWN_EGG);
        eggsMaterials.add(Material.SHULKER_SPAWN_EGG);
        eggsMaterials.add(Material.SILVERFISH_SPAWN_EGG);
        eggsMaterials.add(Material.SKELETON_SPAWN_EGG);
        eggsMaterials.add(Material.SKELETON_HORSE_SPAWN_EGG);
        eggsMaterials.add(Material.SLIME_SPAWN_EGG);
        eggsMaterials.add(Material.SNOWBALL);
        eggsMaterials.add(Material.SPIDER_SPAWN_EGG);
        eggsMaterials.add(Material.SQUID_SPAWN_EGG);
        eggsMaterials.add(Material.STRAY_SPAWN_EGG);
        if (plugin.getServer().getVersion().contains("1.15") || plugin.getServer().getVersion().contains("1.14")) {
            eggsMaterials.add(Material.TRADER_LLAMA_SPAWN_EGG);
        }
        eggsMaterials.add(Material.TROPICAL_FISH_SPAWN_EGG);
        eggsMaterials.add(Material.TURTLE_SPAWN_EGG);
        eggsMaterials.add(Material.VEX_SPAWN_EGG);
        eggsMaterials.add(Material.VILLAGER_SPAWN_EGG);
        eggsMaterials.add(Material.VINDICATOR_SPAWN_EGG);
        if (plugin.getServer().getVersion().contains("1.15") || plugin.getServer().getVersion().contains("1.14")) {
            eggsMaterials.add(Material.WANDERING_TRADER_SPAWN_EGG);
        }
        eggsMaterials.add(Material.WITCH_SPAWN_EGG);
        eggsMaterials.add(Material.NETHER_STAR);
        eggsMaterials.add(Material.WITHER_SKELETON_SPAWN_EGG);
        eggsMaterials.add(Material.WOLF_SPAWN_EGG);
        eggsMaterials.add(Material.ZOMBIE_SPAWN_EGG);
        eggsMaterials.add(Material.ZOMBIE_HORSE_SPAWN_EGG);
        eggsMaterials.add(Material.ZOMBIE_PIGMAN_SPAWN_EGG);
        eggsMaterials.add(Material.ZOMBIE_VILLAGER_SPAWN_EGG);

        eggsNames.add("§aBat");
        if (plugin.getServer().getVersion().contains("1.15")) {
            eggsNames.add("§aBee");
        }
        eggsNames.add("§aBlaze");
        if (plugin.getServer().getVersion().contains("1.15") || plugin.getServer().getVersion().contains("1.14")) {
            eggsNames.add("§aCat");
        }
        eggsNames.add("§aCave Spider");
        eggsNames.add("§aChicken");
        eggsNames.add("§aCod");
        eggsNames.add("§aCow");
        eggsNames.add("§aCreeper");
        eggsNames.add("§aDolphin");
        eggsNames.add("§aDonkey");
        eggsNames.add("§aDrowned");
        eggsNames.add("§aElder Guardian");
        eggsNames.add("§aEnder Dragon");
        eggsNames.add("§aEnderman");
        eggsNames.add("§aEndermite");
        eggsNames.add("§aEvoker");
        if (plugin.getServer().getVersion().contains("1.15") || plugin.getServer().getVersion().contains("1.14")) {
            eggsNames.add("§aFox");
        }
        eggsNames.add("§aGhast");
        eggsNames.add("§aGuardian");
        eggsNames.add("§aHorse");
        eggsNames.add("§aHusk");
        eggsNames.add("§aIron Golem");
        eggsNames.add("§aLlama");
        eggsNames.add("§aMagma Cube");
        eggsNames.add("§aMooshroom");
        eggsNames.add("§aMule");
        eggsNames.add("§aOcelot");
        if (plugin.getServer().getVersion().contains("1.15") || plugin.getServer().getVersion().contains("1.14")) {
            eggsNames.add("§aPanda");
        }
        eggsNames.add("§aParrot");
        eggsNames.add("§aPhantom");
        eggsNames.add("§aPig");
        if (plugin.getServer().getVersion().contains("1.15") || plugin.getServer().getVersion().contains("1.14")) {
            eggsNames.add("§aPillager");
        }
        eggsNames.add("§aPolar Bear");
        eggsNames.add("§aPufferfish");
        eggsNames.add("§aRabbit");
        if (plugin.getServer().getVersion().contains("1.15") || plugin.getServer().getVersion().contains("1.14")) {
            eggsNames.add("§aRavager");
        }
        eggsNames.add("§aSalmon");
        eggsNames.add("§aSheep");
        eggsNames.add("§aShulker");
        eggsNames.add("§aSilverfish");
        eggsNames.add("§aSkeleton");
        eggsNames.add("§aSkeleton Horse");
        eggsNames.add("§aSlime");
        eggsNames.add("§aSnowman");
        eggsNames.add("§aSpider");
        eggsNames.add("§aSquid");
        eggsNames.add("§aStray");
        if (plugin.getServer().getVersion().contains("1.15") || plugin.getServer().getVersion().contains("1.14")) {
            eggsNames.add("§aTrader Llama");
        }
        eggsNames.add("§aTropical Fish");
        eggsNames.add("§aTurtle");
        eggsNames.add("§aVex");
        eggsNames.add("§aVillager");
        eggsNames.add("§aVindicator");
        if (plugin.getServer().getVersion().contains("1.15") || plugin.getServer().getVersion().contains("1.14")) {
            eggsNames.add("§aWandering trader");
        }
        eggsNames.add("§aWitch");
        eggsNames.add("§aWither");
        eggsNames.add("§aWither skeleton");
        eggsNames.add("§aWolf");
        eggsNames.add("§aZombie");
        eggsNames.add("§aZombie Horse");
        eggsNames.add("§aZombie Pigman");
        eggsNames.add("§aZombie Villager");

        FileConfiguration config = getConfig();

        if (config.getConfigurationSection("CubCustomDrops") != null) {

            if (config.getConfigurationSection("CubCustomDrops.lastPage") != null) {
                for (int i = 0; i < config.getConfigurationSection("CubCustomDrops.lastPage.key").getKeys(false).size(); i++) {
                    String temp = config.getString("CubCustomDrops.lastPage.key." + i);
                    UUID temp1 = UUID.fromString(temp);
                    int temp2 = config.getInt("CubCustomDrops.lastPage.get." + i);
                    lastPage.put(temp1, temp2);
                }
            }

            if (config.getConfigurationSection("CubCustomDrops.mobNames") != null) {
                for (int i = 0; i < config.getConfigurationSection("CubCustomDrops.mobNames.key").getKeys(false).size(); i++) {
                    CMP2Mob tempMob = new CMP2Mob();
                    ArrayList<CMP2CustomDrop> tempArr = new ArrayList<>();
                    ArrayList<World> tempArr1 = new ArrayList<>();
                    String temp = config.getString("CubCustomDrops.mobNames.key." + i);
                    String temp1 = config.getString("CubCustomDrops.mobNames.get." + i + ".Name");
                    if (config.getConfigurationSection("CubCustomDrops.mobNames.get." + i + ".VanillaDrops") != null) {
                        for (int j = 0; j < config.getConfigurationSection("CubCustomDrops.mobNames.get." + i + ".VanillaDrops").getKeys(false).size(); j++) {
                            String temp2 = config.getString("CubCustomDrops.mobNames.get." + i + ".VanillaDrops." + j);
                            World temp3 = plugin.getServer().getWorld(temp2);
                            tempArr1.add(temp3);
                        }
                    }
                    if (config.getConfigurationSection("CubCustomDrops.mobNames.get." + i + ".CustomDrops") != null) {
                        for (int j = 0; j < config.getConfigurationSection("CubCustomDrops.mobNames.get." + i + ".CustomDrops").getKeys(false).size(); j++) {
                            CMP2CustomDrop tempDrop = new CMP2CustomDrop();
                            ItemStack temp3 = config.getItemStack("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".Drop");
                            boolean temp4 = config.getBoolean("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".SpawnerDrop");
                            boolean temp5 = config.getBoolean("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".EggDrop");
                            boolean temp6 = config.getBoolean("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".NaturalDrop");
                            boolean temp61 = config.getBoolean("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".BredDrop");
                            boolean temp7 = config.getBoolean("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".Effect");
                            Color temp8 = config.getColor("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".EffectColor");
                            int temp9 = config.getInt("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".DropChance");
                            boolean temp13 = config.getBoolean("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".WorldOverride");
                            if (config.getConfigurationSection("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".Commands") != null) {
                                ArrayList<String> temp10 = new ArrayList<>();
                                for (int k = 0; k < config.getConfigurationSection("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".Commands").getKeys(false).size(); k++) {
                                    String temp11 = config.getString("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".Commands." + k);
                                    temp10.add(temp11);
                                }
                                tempDrop.setCommands(temp10);
                            }
                            if (config.getConfigurationSection("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".Worlds") != null) {
                                ArrayList<World> temp10 = new ArrayList<>();
                                for (int k = 0; k < config.getConfigurationSection("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".Worlds").getKeys(false).size(); k++) {
                                    String temp11 = config.getString("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".Worlds." + k);
                                    World temp12 = plugin.getServer().getWorld(temp11);
                                    temp10.add(temp12);
                                }
                                tempDrop.setWorlds(temp10);
                            }
                            tempDrop.setDrop(temp3);
                            tempDrop.setSpawnerDrop(temp4);
                            tempDrop.setEggDrop(temp5);
                            tempDrop.setNaturalDrop(temp6);
                            tempDrop.setBredDrop(temp61);
                            tempDrop.setEffect(temp7);
                            tempDrop.setEffectColor(temp8);
                            tempDrop.setDropChance(temp9);
                            tempDrop.setWorldOverride(temp13);
                            tempArr.add(tempDrop);
                        }
                    }
                    tempMob.setName(temp1);
                    tempMob.setVanillaDrops(tempArr1);
                    tempMob.setCustomDrops(tempArr);
                    mobNames.put(temp, tempMob);
                }
            }
            if (config.getConfigurationSection("CubCustomDrops.editedBlocks") != null) {
                for (int i = 0; i < config.getConfigurationSection("CubCustomDrops.editedBlocks.key").getKeys(false).size(); i++) {
                    CMP2Block tempBlock = new CMP2Block();
                    ArrayList<CMP2CustomDrop> tempArr = new ArrayList<>();
                    ArrayList<World> tempArr1 = new ArrayList<>();
                    String temp = config.getString("CubCustomDrops.editedBlocks.key." + i);
                    String temp1 = config.getString("CubCustomDrops.editedBlocks.get." + i + ".Block");
                    if (config.getConfigurationSection("CubCustomDrops.editedBlocks.get." + i + ".VanillaDrops") != null) {
                        for (int j = 0; j < config.getConfigurationSection("CubCustomDrops.editedBlocks.get." + i + ".VanillaDrops").getKeys(false).size(); j++) {
                            String temp2 = config.getString("CubCustomDrops.editedBlocks.get." + i + ".VanillaDrops." + j);
                            World temp3 = plugin.getServer().getWorld(temp2);
                            tempArr1.add(temp3);
                        }
                    }
                    if (config.getConfigurationSection("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops") != null) {
                        for (int j = 0; j < config.getConfigurationSection("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops").getKeys(false).size(); j++) {
                            CMP2CustomDrop tempDrop = new CMP2CustomDrop();
                            ItemStack temp3 = config.getItemStack("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops." + j + ".Drop");
                            boolean temp4 = config.getBoolean("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops." + j + ".SpawnerDrop");
                            boolean temp6 = config.getBoolean("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops." + j + ".NaturalDrop");
                            boolean temp7 = config.getBoolean("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops." + j + ".Effect");
                            Color temp8 = config.getColor("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops." + j + ".EffectColor");
                            int temp9 = config.getInt("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops." + j + ".DropChance");
                            boolean temp13 = config.getBoolean("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops." + j + ".WorldOverride");
                            if (config.getConfigurationSection("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops." + j + ".Commands") != null) {
                                ArrayList<String> temp10 = new ArrayList<>();
                                for (int k = 0; k < config.getConfigurationSection("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops." + j + ".Commands").getKeys(false).size(); k++) {
                                    String temp11 = config.getString("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops." + j + ".Commands." + k);
                                    temp10.add(temp11);
                                }
                                tempDrop.setCommands(temp10);
                            }
                            if (config.getConfigurationSection("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops." + j + ".Worlds") != null) {
                                ArrayList<World> temp10 = new ArrayList<>();
                                for (int k = 0; k < config.getConfigurationSection("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops." + j + ".Worlds").getKeys(false).size(); k++) {
                                    String temp11 = config.getString("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops." + j + ".Worlds." + k);
                                    World temp12 = plugin.getServer().getWorld(temp11);
                                    temp10.add(temp12);
                                }
                                tempDrop.setWorlds(temp10);
                            }
                            tempDrop.setDrop(temp3);
                            tempDrop.setSpawnerDrop(temp4);
                            tempDrop.setNaturalDrop(temp6);
                            tempDrop.setEffect(temp7);
                            tempDrop.setEffectColor(temp8);
                            tempDrop.setDropChance(temp9);
                            tempDrop.setWorldOverride(temp13);
                            tempArr.add(tempDrop);
                        }
                    }
                    tempBlock.setBlock(Material.getMaterial(temp1));
                    tempBlock.setVanillaDrops(tempArr1);
                    tempBlock.setCustomDrops(tempArr);
                    editedBlocks.put(tempBlock.getBlock(), tempBlock);
                }
            }

            globalSettings.setLootingMultiplier(config.getBoolean("CubCustomDrops.globalSettings.LootingMultiplier"));
            globalSettings.setLootingAll(config.getBoolean("CubCustomDrops.globalSettings.LootingAll"));
            globalSettings.setLootingM(config.getInt("CubCustomDrops.globalSettings.LootingM"));
            globalSettings.setLootingA(config.getInt("CubCustomDrops.globalSettings.LootingA"));
            globalSettings.setFortuneMultiplier(config.getBoolean("CubCustomDrops.globalSettings.FortuneMultiplier"));
            globalSettings.setFortuneAll(config.getBoolean("CubCustomDrops.globalSettings.FortuneAll"));
            globalSettings.setFortuneM(config.getInt("CubCustomDrops.globalSettings.FortuneM"));
            globalSettings.setFortuneA(config.getInt("CubCustomDrops.globalSettings.FortuneA"));
            if (config.getConfigurationSection("CubCustomDrops.globalSettings.GlobalWorlds") != null) {
                ArrayList<World> temp1 = new ArrayList<>();
                for (int k = 0; k < config.getConfigurationSection("CubCustomDrops.globalSettings.GlobalWorlds").getKeys(false).size(); k++) {
                    String temp2 = config.getString("CubCustomDrops.globalSettings.GlobalWorlds." + k);
                    World temp3 = plugin.getServer().getWorld(temp2);
                    temp1.add(temp3);
                }
                globalSettings.setWorlds(temp1);
            }
            if (!(config.getBoolean("CubCustomDrops.globalSettings.LoadedOnce"))) {
                ArrayList<World> temp1 = new ArrayList<>();
                for (World world : plugin.getServer().getWorlds()) {
                    temp1.add(world);
                }
                globalSettings.setWorlds(temp1);
            }
            if (config.getConfigurationSection("CubCustomDrops.boosterTime") != null) {
                for (int j = 0; j < config.getConfigurationSection("CubCustomDrops.boosterTime").getKeys(false).size(); j++) {
                    String temp2 = config.getString("CubCustomDrops.boosterTime.key." + j);
                    int temp3 = config.getInt("CubCustomDrops.boosterTime.get." + j);
                    boosterTime.put(temp2, temp3);
                }
            }
            if (config.getConfigurationSection("CubCustomDrops.boosterBoost") != null) {
                for (int j = 0; j < config.getConfigurationSection("CubCustomDrops.boosterBoost").getKeys(false).size(); j++) {
                    String temp2 = config.getString("CubCustomDrops.boosterBoost.key." + j);
                    double temp3 = config.getDouble("CubCustomDrops.boosterBoost.get." + j);
                    boosterBoost.put(temp2, temp3);
                }
            }
        } else {
            ArrayList<World> temp1 = new ArrayList<>();
            for (World world : plugin.getServer().getWorlds()) {
                temp1.add(world);
            }
            globalSettings.setWorlds(temp1);
        }
    }

    @Override
    public void onDisable() {
        FileConfiguration config = getConfig();
        config.set("CubCustomDrops", null);

        if (lastPage != null) {
            int i = 0;
            for (UUID temp : lastPage.keySet()) {
                config.set("CubCustomDrops.lastPage.key." + i, temp.toString());
                config.set("CubCustomDrops.lastPage.get." + i, lastPage.get(temp));
                i++;
            }
        }

        if (mobNames != null && mobNames.keySet().size() > 0) {
            int i = 0;
            for (String temp : mobNames.keySet()) {
                config.set("CubCustomDrops.mobNames.key." + i, temp);
                config.set("CubCustomDrops.mobNames.get." + i + ".Name", mobNames.get(temp).getName());
                int j = 0;
                if (mobNames.get(temp).getVanillaDrops() != null && mobNames.get(temp).getVanillaDrops().size() != 0) {
                    for (World tempWorld : mobNames.get(temp).getVanillaDrops()) {
                        config.set("CubCustomDrops.mobNames.get." + i + ".VanillaDrops." + j, tempWorld.getName());
                        j++;
                    }
                }
                j = 0;
                if (mobNames.get(temp).getCustomDrops() != null && mobNames.get(temp).getCustomDrops().size() != 0) {
                    for (CMP2CustomDrop tempDrop : mobNames.get(temp).getCustomDrops()) {
                        config.set("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".Drop", tempDrop.getDrop());
                        config.set("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".SpawnerDrop", tempDrop.getSpawnerDrop());
                        config.set("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".EggDrop", tempDrop.getEggDrop());
                        config.set("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".NaturalDrop", tempDrop.getNaturalDrop());
                        config.set("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".BredDrop", tempDrop.getBredDrop());
                        config.set("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".Effect", tempDrop.getEffect());
                        config.set("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".EffectColor", tempDrop.getEffectColor());
                        config.set("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".DropChance", tempDrop.getDropChance());
                        config.set("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".WorldOverride", tempDrop.getWorldOverride());
                        if (tempDrop.getCommands() != null) {
                            int k = 0;
                            for (String tempStr : tempDrop.getCommands()) {
                                config.set("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".Commands." + k, tempStr);
                                k++;
                            }
                        }
                        if (tempDrop.getWorlds() != null) {
                            int k = 0;
                            for (World tempWorld : tempDrop.getWorlds()) {
                                config.set("CubCustomDrops.mobNames.get." + i + ".CustomDrops." + j + ".Worlds." + k, tempWorld.getName());
                                k++;
                            }
                        }
                        j++;
                    }
                }
                i++;
            }
        }

        if (editedBlocks != null && editedBlocks.keySet().size() > 0) {
            int i = 0;
            for (Material temp : editedBlocks.keySet()) {
                config.set("CubCustomDrops.editedBlocks.key." + i, temp.name());
                config.set("CubCustomDrops.editedBlocks.get." + i + ".Block", editedBlocks.get(temp).getBlock().name());
                int j = 0;
                if (editedBlocks.get(temp).getVanillaDrops() != null && editedBlocks.get(temp).getVanillaDrops().size() != 0) {
                    for (World tempWorld : editedBlocks.get(temp).getVanillaDrops()) {
                        config.set("CubCustomDrops.editedBlocks.get." + i + ".VanillaDrops." + j, tempWorld.getName());
                        j++;
                    }
                }
                j = 0;
                if (editedBlocks.get(temp).getCustomDrops() != null && editedBlocks.get(temp).getCustomDrops().size() != 0) {
                    for (CMP2CustomDrop tempDrop : editedBlocks.get(temp).getCustomDrops()) {
                        config.set("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops." + j + ".Drop", tempDrop.getDrop());
                        config.set("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops." + j + ".SpawnerDrop", tempDrop.getSpawnerDrop());
                        config.set("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops." + j + ".NaturalDrop", tempDrop.getNaturalDrop());
                        config.set("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops." + j + ".Effect", tempDrop.getEffect());
                        config.set("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops." + j + ".EffectColor", tempDrop.getEffectColor());
                        config.set("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops." + j + ".DropChance", tempDrop.getDropChance());
                        config.set("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops." + j + ".WorldOverride", tempDrop.getWorldOverride());
                        if (tempDrop.getCommands() != null) {
                            int k = 0;
                            for (String tempStr : tempDrop.getCommands()) {
                                config.set("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops." + j + ".Commands." + k, tempStr);
                                k++;
                            }
                        }
                        if (tempDrop.getWorlds() != null) {
                            int k = 0;
                            for (World tempWorld : tempDrop.getWorlds()) {
                                config.set("CubCustomDrops.editedBlocks.get." + i + ".CustomDrops." + j + ".Worlds." + k, tempWorld.getName());
                                k++;
                            }
                        }
                        j++;
                    }
                }
                i++;
            }
        }

        config.set("CubCustomDrops.globalSettings.LootingMultiplier", globalSettings.getLootingMultiplier());
        config.set("CubCustomDrops.globalSettings.LootingAll", globalSettings.getLootingAll());
        config.set("CubCustomDrops.globalSettings.LootingM", globalSettings.getLootingM());
        config.set("CubCustomDrops.globalSettings.LootingA", globalSettings.getLootingA());
        config.set("CubCustomDrops.globalSettings.FortuneMultiplier", globalSettings.getFortuneMultiplier());
        config.set("CubCustomDrops.globalSettings.FortuneAll", globalSettings.getFortuneAll());
        config.set("CubCustomDrops.globalSettings.FortuneM", globalSettings.getFortuneM());
        config.set("CubCustomDrops.globalSettings.FortuneA", globalSettings.getFortuneA());
        if (globalSettings.getWorlds() != null && globalSettings.getWorlds().size() > 0) {
            int k = 0;
            for (World tempWorld : globalSettings.getWorlds()) {
                config.set("CubCustomDrops.globalSettings.GlobalWorlds." + k, tempWorld.getName());
                k++;
            }
        }
        if (boosterTime != null && boosterBoost.size() > 0) {
            int k = 0;
            for (String tempStr : boosterTime.keySet()) {
                int tempInt = boosterTime.get(tempStr);
                config.set("CubCustomDrops.boosterTime.key." + k, tempStr);
                config.set("CubCustomDrops.boosterTime.get." + k, tempInt);
                k++;
            }
        }
        if (boosterBoost != null && boosterBoost.size() > 0) {
            int k = 0;
            for (String tempStr : boosterBoost.keySet()) {
                double tempDouble = boosterBoost.get(tempStr);
                config.set("CubCustomDrops.boosterBoost.key." + k, tempStr);
                config.set("CubCustomDrops.boosterBoost.get." + k, tempDouble);
                k++;
            }
        }
        config.set("CubCustomDrops.globalSettings.LoadedOnce", true);


        saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§2[CustomDrops] §4ERROR: §cCommand only usable by players.");
            return true;
        }
        Player player = (Player) sender;

        if (!(player.hasPermission("customdrops.admin"))) {
            player.sendMessage("§2[CustomDrops] §cYou do not have permission to use this command.");
            return true;
        }

        player.openInventory(CMP2Utilities.CreateMainMenu());

        return true;
    }
}
