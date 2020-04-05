package com.gmail.cubitverde;

import org.bukkit.Color;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class CMP2CustomDrop {
    private ItemStack Drop;
    private boolean SpawnerDrop;
    private boolean EggDrop;
    private boolean NaturalDrop;
    private boolean BredDrop;
    private boolean Effect;
    private Color EffectColor;
    private int DropChance;
    private ArrayList<String> Commands;
    private boolean WorldOverride;
    private ArrayList<World> Worlds;

    public ItemStack getDrop() {
        return this.Drop;
    }
    public void setDrop(ItemStack temp) {
        this.Drop = temp;
    }

    public boolean getSpawnerDrop() {
        return this.SpawnerDrop;
    }
    public void setSpawnerDrop(boolean temp) {
        this.SpawnerDrop = temp;
    }

    public boolean getEggDrop() {
        return this.EggDrop;
    }
    public void setEggDrop(boolean temp) {
        this.EggDrop = temp;
    }

    public boolean getNaturalDrop() {
        return this.NaturalDrop;
    }
    public void setNaturalDrop(boolean temp) {
        this.NaturalDrop = temp;
    }

    public boolean getBredDrop() {
        return this.BredDrop;
    }
    public void setBredDrop(boolean temp) {
        this.BredDrop = temp;
    }

    public boolean getEffect() {
        return this.Effect;
    }
    public void setEffect(boolean temp) {
        this.Effect = temp;
    }

    public Color getEffectColor() {
        return this.EffectColor;
    }
    public void setEffectColor(Color temp) {
        this.EffectColor = temp;
    }

    public int getDropChance() {
        return this.DropChance;
    }
    public void setDropChance(int temp) {
        this.DropChance = temp;
    }

    public ArrayList<String> getCommands() {
        return this.Commands;
    }
    public void setCommands(ArrayList<String> temp) {
        this.Commands = temp;
    }

    public boolean getWorldOverride() {
        return this.WorldOverride;
    }
    public void setWorldOverride(boolean temp) {
        this.WorldOverride = temp;
    }

    public ArrayList<World> getWorlds() {
        return this.Worlds;
    }
    public void setWorlds(ArrayList<World> temp) {
        this.Worlds = temp;
    }
}
