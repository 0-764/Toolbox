package com.j10max.client.api.gui;

import com.j10max.client.api.Initializer;
import com.j10max.client.api.gui.panel.IPanelHandler;

public interface IGuiHandler extends Initializer {

    IPanelHandler getPanelHandler();

}
