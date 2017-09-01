package com.j10max.client.api.exception.panel;

import com.j10max.client.api.gui.panel.IPanel;

import java.awt.*;

public class PanelException extends Exception {

    private IPanel panelInstance;

    public PanelException(IPanel panelInstance, String exceptionMessage){
        super(exceptionMessage);
        this.panelInstance = panelInstance;
    }

    public IPanel getPanelInstance(){
        return panelInstance;
    }

}
