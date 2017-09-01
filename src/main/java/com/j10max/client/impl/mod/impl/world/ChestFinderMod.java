package com.j10max.client.impl.mod.impl.world;

import com.j10max.client.api.mod.ModType;
import com.j10max.client.impl.mod.Mod;
import org.lwjgl.input.Keyboard;

public class ChestFinderMod extends Mod {

    public ChestFinderMod() {
        super("Chest Finder", "1.0", ModType.WORLD);
        this.setKeybind(Keyboard.KEY_C);
        this.setDesc("Finds chests");
    }

    @Override
    public void onInit() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

}
