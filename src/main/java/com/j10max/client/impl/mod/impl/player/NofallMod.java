package com.j10max.client.impl.mod.impl.player;

import com.j10max.client.api.mod.ModType;
import com.j10max.client.impl.mod.Mod;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class NofallMod extends Mod {

    private int nofallOption = 1;

    public NofallMod() {
        super("Nofall", "1.0", ModType.PLAYER);
        setKeybind(Keyboard.KEY_N);
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
            switch (nofallOption) {
                case 1: {
                    mcInstance.thePlayer.onGround = true;
                    break;
                }
                case 2: {
                    break;
                }
            }
        }
    }


}
