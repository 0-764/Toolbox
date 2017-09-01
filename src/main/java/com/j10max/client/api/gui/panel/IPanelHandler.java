package com.j10max.client.api.gui.panel;

import com.j10max.client.api.Initializer;
import com.j10max.client.api.exception.panel.PanelException;

import java.util.List;

public interface IPanelHandler extends Initializer {

    void registerPanel(IPanel panelInstance) throws PanelException;

    boolean containsPanelByTitle(String panelTitle);

    IPanel getPanelByTitle(String panelTitle);

    List<IPanel> getLoadedPanels();

}
