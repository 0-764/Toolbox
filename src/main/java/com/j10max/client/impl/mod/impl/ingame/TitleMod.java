package com.j10max.client.impl.mod.impl.ingame;

import com.j10max.client.Client;
import com.j10max.client.api.mod.ModType;
import com.j10max.client.impl.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;

public class TitleMod extends Mod {

    private Minecraft mcInstance;

    public TitleMod() {
        super("Title", "1.0", ModType.INGAME);
        this.mcInstance = Minecraft.getMinecraft();
    }

    @Override
    public void onInit() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @SubscribeEvent
    public void onRenderInGameEvent(RenderGameOverlayEvent.Text event) {
        if (getState()) {
            mcInstance.fontRendererObj.drawStringWithShadow(String.format("%s (%s fps)", Client.ID, mcInstance.getDebugFPS()), 3, 3, 0xffFFFFFF);
        }
    }

}
