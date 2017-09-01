package com.j10max.client.impl.mod.impl.player;

import com.j10max.client.api.mod.ModType;
import com.j10max.client.impl.mod.Mod;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AntiKnockbackMod extends Mod {

    public AntiKnockbackMod() {
        super("Anti Knockback", "1.0", ModType.PLAYER);
    }

    @Override
    public void onInit() {
        FMLCommonHandler.instance().bus().register(this);
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (getState()) {
            if (mcInstance.thePlayer.hurtTime > 0 && mcInstance.thePlayer.hurtResistantTime > 0) {
                mcInstance.thePlayer.motionX = 0;
                mcInstance.thePlayer.motionZ = 0;
            }
        }
    }

}
