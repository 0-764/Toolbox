package com.j10max.client.impl.panel.component.impl;

import com.j10max.client.impl.panel.component.PanelComponent;
import net.minecraft.client.gui.Gui;

public class TextComponent extends PanelComponent {

    private String text;

    public TextComponent(String text) {
        super("Text", 120, 10);
        this.text = text;
    }

    @Override
    public void onDraw(int mouseX, int mouseY, float partialTicks) {
        Gui.drawRect(this.getPosX(), this.getPosY(), this.getPosX() + this.getWidth(), this.getPosY() + this.getHeight(), 0xff33FF33);
        mcInstance.fontRendererObj.drawStringWithShadow(this.text, this.getPosX(), this.getPosY(), 0xffFFFFFF);
    }

    @Override
    public void onMouseClicked(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void onKeyTyped(char typedChar, int keyCode) {

    }

    @Override
    public void onMouseDragged(int mouseX, int mouseY) {

    }

}
