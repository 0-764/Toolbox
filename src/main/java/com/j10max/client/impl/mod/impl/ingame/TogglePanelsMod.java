package com.j10max.client.impl.mod.impl.ingame;

import com.j10max.client.Client;
import com.j10max.client.api.mod.ModType;
import com.j10max.client.impl.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

public class TogglePanelsMod extends Mod {

    private Minecraft mcInstance;

    public TogglePanelsMod() {
        super("Toggle Panels", "1.0", ModType.INGAME);
        this.setKeybind(Keyboard.KEY_RSHIFT);
        this.mcInstance = Minecraft.getMinecraft();
    }


    @Override
    public void onInit() {

    }

    @Override
    public void onEnable() {
        mcInstance.displayGuiScreen((GuiScreen) Client.instance().getPanelHandler());
    }

    @Override
    public void onDisable() {
        mcInstance.displayGuiScreen((GuiScreen) Client.instance().getPanelHandler());
    }

}
