package com.j10max.client.impl.mod.impl.player;

import com.j10max.client.api.mod.ModType;
import com.j10max.client.impl.mod.Mod;
import com.j10max.client.impl.mod.value.impl.FloatModValue;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class StepMod extends Mod {

    private FloatModValue stepHeightValue;

    public StepMod() {
        super("Step", "1.0", ModType.PLAYER);
        this.stepHeightValue = new FloatModValue("Step height", 2.0f);
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
        mcInstance.thePlayer.stepHeight = 1;
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (getState()) {
            mcInstance.thePlayer.stepHeight = this.stepHeightValue.getValue();
        }
    }

}
