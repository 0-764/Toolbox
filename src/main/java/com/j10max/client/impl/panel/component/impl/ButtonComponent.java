package com.j10max.client.impl.panel.component.impl;

import com.j10max.client.Client;
import com.j10max.client.impl.panel.*;
import com.j10max.client.impl.panel.Panel;
import com.j10max.client.impl.panel.component.PanelComponent;
import net.minecraft.client.gui.Gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ButtonComponent extends PanelComponent {

    private String displayText;
    private List<ButtonListener> buttonListeners;

    public ButtonComponent(String displayText) {
        this(displayText, Panel.DEFAULT_WIDTH, 15);
    }

    public ButtonComponent(String displayText, int width, int height) {
        super("button", width, height);
        this.displayText = displayText;
        this.buttonListeners = new ArrayList<ButtonListener>();
    }

    @Override
    public void onDraw(int mouseX, int mouseY, float partialTicks) {
        int textPos = this.getPosX() + (this.getWidth() - mcInstance.fontRendererObj.getStringWidth(displayText)) / 2;
        /* */
        Gui.drawRect(this.getPosX(), this.getPosY(), this.getPosX() + this.getWidth() - 4, this.getPosY() + (this.getHeight()), 0xff3c3c3c);
        if (isHovering(mouseX, mouseY, getWidth(), getHeight())) {
            mcInstance.fontRendererObj.drawStringWithShadow(this.displayText, textPos, this.getPosY() + 3, 0xffEEEEEE);
        } else {
            mcInstance.fontRendererObj.drawStringWithShadow(this.displayText, textPos, this.getPosY() + 3, 0xaaFFFFFF);
        }
    }

    @Override
    public void onMouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isHovering(mouseX, mouseY, getWidth(), getHeight())) {
            for (ButtonListener buttonListener : this.buttonListeners) {
                buttonListener.onButtonClick(mouseX, mouseY, mouseButton);
            }
        }
    }

    @Override
    public void onKeyTyped(char typedChar, int keyCode) {

    }

    @Override
    public void onMouseDragged(int mouseX, int mouseY) {

    }

    public void registerButtonListener(ButtonListener buttonListener) {
        this.buttonListeners.add(buttonListener);
    }

    public interface ButtonListener {

        void onButtonClick(int mouseX, int mouseY, int mouseButton);

    }

}
