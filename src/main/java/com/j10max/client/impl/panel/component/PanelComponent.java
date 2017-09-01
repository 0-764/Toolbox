package com.j10max.client.impl.panel.component;

import com.j10max.client.api.gui.panel.components.IPanelComponent;
import net.minecraft.client.Minecraft;

public abstract class PanelComponent implements IPanelComponent {

    public final Minecraft mcInstance = Minecraft.getMinecraft();

    private String title;

    private int posX;
    private int posY;
    private int width;
    private int height;

    public PanelComponent(String title, int width, int height) {
        this(title, 0, 0, width, height);
    }

    public PanelComponent(String title, int posX, int posY, int width, int height) {
        this.title = title;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    public abstract void onDraw(int mouseX, int mouseY, float partialTicks);

    public abstract void onMouseClicked(int mouseX, int mouseY, int mouseButton);

    public abstract void onKeyTyped(char typedChar, int keyCode);

    public abstract void onMouseDragged(int mouseX, int mouseY);

    public boolean isHovering(int mouseX, int mouseY, int width, int height) {
        return mouseX >= (this.posX) && mouseY >= (this.posY) && mouseX <= (this.posX + width) && mouseY <= (this.posY + height);
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTicks) {
        this.onDraw(mouseX, mouseY, partialTicks);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        this.onMouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        this.onKeyTyped(typedChar, keyCode);
    }

    @Override
    public void mouseDragged(int mouseX, int mouseY) {
        this.onMouseDragged(mouseX, mouseY);
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
    public String getTitle() {
        return title;
    }

}
