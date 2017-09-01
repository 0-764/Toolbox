package com.j10max.client.impl.mod.impl.player;

import com.j10max.client.api.mod.ModType;
import com.j10max.client.impl.mod.Mod;
import com.j10max.client.impl.mod.value.impl.BooleanModValue;

public class SneakMod extends Mod {

    public SneakMod() {
        super("Sneak", "1.0", ModType.PLAYER);
        registerValue(new BooleanModValue("Packet Sneak", true));
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
