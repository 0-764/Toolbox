package com.j10max.client.impl.mod.impl.world;

import com.j10max.client.api.mod.ModType;
import com.j10max.client.impl.mod.Mod;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class DaytimeMod extends Mod {

    private long previousWorldTime;

    public DaytimeMod() {
        super("Daytime", "1.0", ModType.WORLD);
    }

    @Override
    public void onInit() {

    }

    @Override
    public void onEnable() {
        this.previousWorldTime = mcInstance.theWorld.getWorldTime();
        mcInstance.theWorld.setWorldTime(0);
    }

    @Override
    public void onDisable() {
        mcInstance.theWorld.setWorldTime(this.previousWorldTime);
    }
}
