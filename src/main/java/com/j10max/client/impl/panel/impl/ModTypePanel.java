package com.j10max.client.impl.panel.impl;

import com.j10max.client.Client;
import com.j10max.client.api.mod.IMod;
import com.j10max.client.api.mod.ModType;
import com.j10max.client.impl.mod.impl.ingame.TogglePanelsMod;
import com.j10max.client.impl.panel.Panel;
import com.j10max.client.impl.panel.component.impl.ModButtonComponent;

public class ModTypePanel extends Panel {

    private ModType modType;

    public ModTypePanel(ModType modType) {
        super(modType.getName());
        this.modType = modType;
    }

    @Override
    public void init() {
        /* Add mods to Panel */
        for (IMod modInstance : Client.instance().getModHandler().getModsByType(this.modType)) {
            if (modInstance instanceof TogglePanelsMod) {
                continue;
            }
            registerComponent(new ModButtonComponent(modInstance));
        }
    }

}
