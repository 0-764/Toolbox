package com.j10max.client.impl.panel;

import com.j10max.client.api.IClient;
import com.j10max.client.api.exception.panel.PanelException;
import com.j10max.client.api.gui.panel.IPanel;
import com.j10max.client.api.gui.panel.IPanelHandler;
import com.j10max.client.api.mod.ModType;
import com.j10max.client.impl.panel.impl.ListPanel;
import com.j10max.client.impl.panel.impl.ModTypePanel;
import com.j10max.client.impl.panel.impl.SearchPanel;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PanelHandler extends GuiScreen implements IPanelHandler {

    private List<IPanel> loadedPanels;

    public PanelHandler() {
        super();
        this.loadedPanels = new ArrayList<IPanel>();
    }

    @Override
    public void init(IClient clientInstance) {
        try {
            for (ModType modType : ModType.values()) {
                registerPanel(new ModTypePanel(modType));
            }
            /* CC */
            registerPanel(new SearchPanel());
            /* List panel - register last */
            ListPanel listPanel;
            registerPanel(listPanel = new ListPanel());
            listPanel.setVisible(true);
        } catch (PanelException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        /* Draw Panels */
        for (IPanel panelInstance : this.loadedPanels) {
            if (panelInstance.isVisible()) {
                panelInstance.draw(mouseX, mouseY, partialTicks);
            }
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        for (IPanel panelInstance : this.loadedPanels) {
            if (panelInstance.isVisible()) {
                panelInstance.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }
    }


    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        for (IPanel panelInstance : this.loadedPanels) {
            if (panelInstance.isVisible()) {
                panelInstance.keyTyped(typedChar, keyCode);
            }
        }
    }

    @Override
    public void registerPanel(IPanel panelInstance) throws PanelException {
        if (!this.containsPanelByTitle(panelInstance.getTitle())) {
            panelInstance.init();
            getLoadedPanels().add(panelInstance);
        } else {
            throw new PanelException(panelInstance, String.format("Panel already exists with Title `%s`", panelInstance.getTitle()));
        }
    }

    @Override
    public boolean containsPanelByTitle(String panelTitle) {
        return this.getPanelByTitle(panelTitle) != null;
    }

    @Override
    public IPanel getPanelByTitle(String panelTitle) {
        for (IPanel panelInstance : this.loadedPanels) {
            if (panelInstance.getTitle().equalsIgnoreCase(panelTitle)) {
                return panelInstance;
            }
        }
        return null;
    }

    @Override
    public List<IPanel> getLoadedPanels() {
        return loadedPanels;
    }

}
