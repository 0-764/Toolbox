package com.j10max.client.impl.panel.impl;

import com.j10max.client.Client;
import com.j10max.client.api.gui.panel.IPanel;
import com.j10max.client.impl.panel.Panel;
import com.j10max.client.impl.panel.component.impl.ButtonComponent;
import com.j10max.client.impl.panel.component.impl.TextComponent;

public class ListPanel extends Panel {

    public ListPanel() {
        super("List of Panels");
    }

    @Override
    public void init() {
        for (final IPanel panelInstance : Client.instance().getPanelHandler().getLoadedPanels()) {
            if (!(panelInstance instanceof ListPanel)) {
                ButtonComponent buttonComponent = new ButtonComponent(panelInstance.getTitle());
                buttonComponent.registerButtonListener(new ButtonComponent.ButtonListener() {
                    @Override
                    public void onButtonClick(int mouseX, int mouseY, int mouseButton) {
                        panelInstance.setVisible(!panelInstance.isVisible());
                    }
                });
                registerComponent(buttonComponent);
            }
        }
    }

}
