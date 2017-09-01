package com.j10max.client.impl.mod.impl.player;

import com.j10max.client.api.mod.ModType;
import com.j10max.client.impl.mod.Mod;
import com.j10max.client.impl.mod.value.impl.IntModValue;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class FlyMod extends Mod {

    private IntModValue flyOptionValue;

    public FlyMod() {
        super("Fly", "1.0", ModType.PLAYER);
        setKeybind(Keyboard.KEY_F);
        registerValue(flyOptionValue = new IntModValue("Fly Option", 1));
    }

    @Override
    public void onInit() {
        FMLCommonHandler.instance().bus().register(this);
    }

    @Override
    public void onEnable() {
        if (this.flyOptionValue.getValue() == 1) mcInstance.thePlayer.capabilities.isFlying = true;
    }

    @Override
    public void onDisable() {
        if (this.flyOptionValue.getValue() == 1) mcInstance.thePlayer.capabilities.isFlying = false;
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (getState()) {
            switch (this.flyOptionValue.getValue()) {
                case 1: {
                    mcInstance.thePlayer.capabilities.isFlying = true;
                    break;
                }
                case 2: {
                    mcInstance.thePlayer.jumpMovementFactor = 0.5F;
                    mcInstance.thePlayer.capabilities.isFlying = false;
                    mcInstance.thePlayer.setSneaking(false);
                    mcInstance.thePlayer.motionX = 0;
                    mcInstance.thePlayer.motionY = 0;
                    mcInstance.thePlayer.motionZ = 0;
                    if (mcInstance.currentScreen == null) {
                        if (mcInstance.gameSettings.keyBindJump.isPressed()) {
                            mcInstance.thePlayer.motionY += 1;
                        }
                        if (mcInstance.gameSettings.keyBindSneak.isPressed()) {
                            mcInstance.thePlayer.motionY -= 1;
                        }
                    }
                    mcInstance.thePlayer.jumpMovementFactor *= 2.1D;
                    break;
                }
            }
        }
    }

}
