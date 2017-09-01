package com.j10max.client.impl.mod.impl.ingame;

import com.j10max.client.Client;
import com.j10max.client.api.mod.IMod;
import com.j10max.client.api.mod.ModType;
import com.j10max.client.impl.mod.Mod;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModsArrayMod extends Mod {

    public ModsArrayMod() {
        super("Mods Array", "1.0", ModType.INGAME);
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
            int count = 0;
            for (IMod mod : Client.instance().getModHandler().getModList()) {
                if (!mod.getState()) continue;
                mcInstance.fontRendererObj.drawStringWithShadow(mod.getName(), mcInstance.displayWidth / 2 - mcInstance.fontRendererObj.getStringWidth(mod.getName()) - 3, count + 3, 0xffFFFFFF);
                count += 10;
            }
        }
    }

}
