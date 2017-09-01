package com.j10max.client.api.gui.panel;

import com.j10max.client.api.gui.panel.components.IPanelComponent;

import java.util.List;

public interface IPanel {

    void init();

    void draw(int mouseX, int mouseY, float partialTicks);

    void mouseClicked(int mouseX, int mouseY, int mouseButton);

    void keyTyped(char typedChar, int keyCode);

    void mouseDragged(int mouseX, int mouseY);

    void registerComponent(IPanelComponent panelComponent);

    int getPosX();

    int getPosY();

    void setPosX(int posX);

    void setPosY(int posY);

    int getWidth();

    int getHeight();

    void setWidth(int width);

    void setHeight(int height);

    int getDragX();

    int getDragY();

    void setDragX(int dragX);

    void setDragY(int dragY);

    void setVisible(boolean visible);

    void setPinned(boolean pinned);

    void setExtended(boolean extended);

    void setDragging(boolean dragging);

    boolean isVisible();

    boolean isPinned();

    boolean isExtended();

    boolean isDragged();

    List<IPanelComponent> getLoadedComponents();

    String getTitle();

}
