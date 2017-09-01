package com.j10max.client.impl.panel.component.impl;

import com.j10max.client.api.mod.IMod;
import com.j10max.client.impl.panel.Panel;
import com.j10max.client.impl.panel.component.PanelComponent;
import com.j10max.client.impl.util.GuiUtil;
import net.minecraft.client.gui.Gui;

public class ModButtonComponent extends PanelComponent {

    private IMod modInstance;

    public ModButtonComponent(IMod modInstance) {
        super(modInstance.getName(), Panel.DEFAULT_WIDTH, 15);
        this.modInstance = modInstance;
    }

    @Override
    public void onDraw(int mouseX, int mouseY, float partialTicks) {
        setWidth(115);
        int textPos = this.getPosX() + (this.getWidth() - mcInstance.fontRendererObj.getStringWidth(modInstance.getName())) / 2;
        // Draw
        Gui.drawRect(this.getPosX(), this.getPosY(), this.getPosX() + this.getWidth(), this.getPosY() + (this.getHeight()), modInstance.getState() ? 0xff0962bb : 0xff3c3c3c);

        // Text
        if (isHovering(mouseX, mouseY, getWidth(), getHeight())) {
            mcInstance.fontRendererObj.drawStringWithShadow(modInstance.getName(), textPos, this.getPosY() + 3, 0xffEEEEEE);
        } else {
            mcInstance.fontRendererObj.drawStringWithShadow(modInstance.getName(), textPos, this.getPosY() + 3, modInstance.getState() ? 0xffEEEEEE : 0xff959595);
        }
    }

    @Override
    public void onMouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isHovering(mouseX, mouseY, getWidth(), getHeight())) {
            modInstance.toggleState();
        }
    }

    @Override
    public void onKeyTyped(char typedChar, int keyCode) {

    }

    @Override
    public void onMouseDragged(int mouseX, int mouseY) {

    }

}
