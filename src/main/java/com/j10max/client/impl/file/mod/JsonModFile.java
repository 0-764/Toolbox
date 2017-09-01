package com.j10max.client.impl.file.mod;

import com.j10max.client.api.mod.IMod;

public class JsonModFile {

    private String modId;
    private int modKeybind;
    private boolean modState;

    public JsonModFile(IMod modInstance){
        this.modId = modInstance.getName();
        this.modKeybind = modInstance.getKeybind();
        this.modState = modInstance.getState();
    }

}
