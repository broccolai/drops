package com.gmail.cubitverde;

import org.bukkit.Material;
import org.bukkit.World;

import java.util.ArrayList;

public class CMP2Block {
    private Material block;
    private ArrayList<World> VanillaDrops;
    private ArrayList<CMP2CustomDrop> CustomDrops;

    public Material getBlock() {
        return this.block;
    }
    public void setBlock(Material temp) {
        this.block = temp;
    }

    public ArrayList<World> getVanillaDrops() {
        return this.VanillaDrops;
    }
    public void setVanillaDrops(ArrayList<World> temp) {
        this.VanillaDrops = temp;
    }

    public ArrayList<CMP2CustomDrop> getCustomDrops() {
        return this.CustomDrops;
    }
    public void setCustomDrops(ArrayList<CMP2CustomDrop> temp) {
        this.CustomDrops = temp;
    }
}
