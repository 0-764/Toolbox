package com.j10max.client.impl.panel;

import com.j10max.client.Client;
import com.j10max.client.api.gui.panel.IPanel;
import com.j10max.client.api.gui.panel.components.IPanelComponent;
import com.j10max.client.impl.util.GuiUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public abstract class Panel implements IPanel {

    public static int DEFAULT_WIDTH = 120;

    private final Minecraft mcInstance = Minecraft.getMinecraft();

    private List<IPanelComponent> loadedComponents;

    private String title;

    private int posX;
    private int posY;
    private int width;
    private int height;
    private int dragX;
    private int dragY;

    private boolean visible;
    private boolean extended = true;
    private boolean pinned;
    private boolean dragged;

    private int componentsSize;

    public Panel(String title) {
        this(title, 0, 0, Panel.DEFAULT_WIDTH, 15);
    }

    private Panel(String title, int posX, int posY, int width, int height) {
        this.title = title;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.loadedComponents = new ArrayList<IPanelComponent>();
    }

    @Override
    public void registerComponent(IPanelComponent panelComponent) {
        this.loadedComponents.add(panelComponent);
        /* Update Panel components size */
        this.componentsSize = 0;
        int componentSpacing = 1;
        for (IPanelComponent indexPanelComponent : getLoadedComponents()) {
            this.componentsSize += indexPanelComponent.getHeight() + componentSpacing;
        }
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTicks) {
        this.mouseDragged(mouseX, mouseY);
        /* Panel background */
        Gui.drawRect(this.posX - 1, this.posY - 1, this.posX + this.width + 1, this.posY + this.height + 6 + (this.isExtended() ? this.componentsSize : 0), 0x99000000);
        /* Draw Upper Panel */
        Gui.drawRect(this.posX, this.posY, this.posX + this.width, this.posY + this.height, 0xff000000);
        mcInstance.fontRendererObj.drawStringWithShadow(getTitle() + "(" + loadedComponents.size() + ")", this.posX + 3, this.posY + 3, 0xffFFFFFF);
        /* Upper buttons */
        Gui.drawRect(this.posX + this.width - 11, this.posY + 2, this.posX + this.width - 2, this.posY + 10, 0xff0962bb);
        /* Draw Lower Panel */
        if (isExtended()) {
            Gui.drawRect(this.posX, this.posY + 15, this.posX + this.width, this.posY + 23 + this.componentsSize, 0x99000000);
            int componentPos = 0;
            for (IPanelComponent panelComponent : getLoadedComponents()) {
                panelComponent.setPosX(this.posX + 2);
                panelComponent.setPosY(this.posY + 16 + componentPos);
                panelComponent.draw(mouseX, mouseY, partialTicks);
                componentPos += panelComponent.getHeight() + 2;
            }
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        this.dragged = false;
        if (mouseX >= this.posX && mouseY >= this.posY && mouseX <= (this.posX + this.getWidth()) && mouseY <= (this.posY + this.getHeight())) {
            this.dragX = mouseX - this.posX;
            this.dragY = mouseY - this.posY;
            this.dragged = true;
        }
        if (isExtended()) {
            for (IPanelComponent panelComponent : getLoadedComponents()) {
                panelComponent.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }
        // Extended
        if (mouseX >= this.posX + this.width - 11 && mouseY >= this.posY + 2 && mouseX <= this.posX + this.width - 2 && mouseY <= this.posY + 10) {
            this.extended = !this.extended;
        }
    }

    @Override
    public void mouseDragged(int mouseX, int mouseY) {
        /* Interact with Window */
        if (Mouse.isButtonDown(0) && isDragged()) {
            setPosX(mouseX - getDragX());
            setPosY(mouseY - getDragY());
        } else {
            this.dragged = false;
        }
        /* Coordinates - Zero */
        if (this.posX <= 0) {
            this.posX = 0;
        }
        if (this.posY <= 0) {
            this.posY = 0;
        }
        /* Move Panel with Mouse dragging */
        ScaledResolution scaledResolution = new ScaledResolution(mcInstance);

        int scaledHeight = scaledResolution.getScaledHeight();
        int scaledWidth = scaledResolution.getScaledWidth();

        if (this.posY >= (scaledHeight - this.height) && !this.extended) {
            this.posY = scaledHeight - this.height;
        }
        if (this.posY >= (scaledHeight - this.height) && this.extended) {
            this.posY = scaledHeight - this.height;
            // TODO - componentsSize
        }
        if (this.posX >= (scaledWidth - this.width)) {
            this.posX = scaledWidth - this.width;
        }
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        if (isExtended()) {
            for (IPanelComponent panelComponent : getLoadedComponents()) {
                panelComponent.keyTyped(typedChar, keyCode);
            }
        }
    }

    public boolean isHovering(int mouseX, int mouseY, int width, int height) {
        return mouseX >= (this.posX) && mouseY >= (this.posY) && mouseX <= (this.posX + width) && mouseY <= (this.posY + height);
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public void setPosX(int posX) {
        this.posX = posX;
    }

    @Override
    public void setPosY(int posY) {
        this.posY = posY;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getDragX() {
        return dragX;
    }

    @Override
    public int getDragY() {
        return dragY;
    }

    @Override
    public void setDragX(int dragX) {
        this.dragX = dragX;
    }

    @Override
    public void setDragY(int dragY) {
        this.dragY = dragY;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    @Override
    public void setExtended(boolean extended) {
        this.extended = extended;
    }

    @Override
    public void setDragging(boolean dragging) {
        this.dragged = dragging;
    }

    @Override
    public boolean isDragged() {
        return dragged;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public boolean isPinned() {
        return pinned;
    }

    @Override
    public boolean isExtended() {
        return extended;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List<IPanelComponent> getLoadedComponents() {
        return loadedComponents;
    }

}
