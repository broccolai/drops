package com.gmail.cubitverde;

import org.bukkit.World;

import java.util.ArrayList;

public class CMP2Mob {
    private String Name;
    private ArrayList<World> NoVanillaWorlds;
    private ArrayList<CMP2CustomDrop> CustomDrops;

    public String getName() {
        return this.Name;
    }
    public void setName(String temp) {
        this.Name = temp;
    }

    public ArrayList<World> getVanillaDrops() { return this.NoVanillaWorlds; }
    public void setVanillaDrops(ArrayList<World> temp) {
        this.NoVanillaWorlds = temp;
    }

    public ArrayList<CMP2CustomDrop> getCustomDrops() {
        return this.CustomDrops;
    }
    public void setCustomDrops(ArrayList<CMP2CustomDrop> temp) {
        this.CustomDrops = temp;
    }
}
