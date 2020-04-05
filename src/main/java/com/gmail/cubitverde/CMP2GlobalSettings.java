package com.gmail.cubitverde;


import org.bukkit.World;

import java.util.ArrayList;

public class CMP2GlobalSettings {
    private boolean LootingMultiplier;
    private boolean FortuneMultiplier;
    private boolean LootingAll;
    private boolean FortuneAll;
    private  int LootingM;
    private  int FortuneM;
    private  int LootingA;
    private  int FortuneA;
    private ArrayList<World> GlobalWorlds;

    public boolean getLootingMultiplier() {
        return this.LootingMultiplier;
    }
    public void setLootingMultiplier(boolean temp) {
        this.LootingMultiplier = temp;
    }

    public boolean getFortuneMultiplier() {
        return this.FortuneMultiplier;
    }
    public void setFortuneMultiplier(boolean temp) {
        this.FortuneMultiplier = temp;
    }

    public boolean getLootingAll() {
        return this.LootingAll;
    }
    public void setLootingAll(boolean temp) {
        this.LootingAll = temp;
    }

    public boolean getFortuneAll() {
        return this.FortuneAll;
    }
    public void setFortuneAll(boolean temp) {
        this.FortuneAll = temp;
    }

    public int getLootingM() {
        return this.LootingM;
    }
    public void setLootingM(int temp) {
        this.LootingM = temp;
    }

    public int getFortuneM() {
        return this.FortuneM;
    }
    public void setFortuneM(int temp) {
        this.FortuneM = temp;
    }

    public int getLootingA() {
        return this.LootingA;
    }
    public void setLootingA(int temp) {
        this.LootingA = temp;
    }

    public int getFortuneA() {
        return this.FortuneA;
    }
    public void setFortuneA(int temp) {
        this.FortuneA = temp;
    }

    public ArrayList<World> getWorlds() {
        return this.GlobalWorlds;
    }
    public void setWorlds(ArrayList<World> temp) {
        this.GlobalWorlds = temp;
    }
}