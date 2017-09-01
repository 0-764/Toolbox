package com.j10max.client.api.gui.panel.components;

public interface IPanelComponent {

    void draw(int mouseX, int mouseY, float partialTicks);

    void mouseClicked(int mouseX, int mouseY, int mouseButton);

    void keyTyped(char typedChar, int keyCode);

    void mouseDragged(int mouseX, int mouseY);

    int getPosX();

    int getPosY();

    void setPosX(int posX);

    void setPosY(int posY);

    int getWidth();

    int getHeight();

    void setWidth(int width);

    void setHeight(int height);

    String getTitle();

}
