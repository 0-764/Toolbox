package com.j10max.client.api.exception.mod;

import com.j10max.client.api.mod.IMod;

public class ModException extends Exception {

    private IMod modInstance;

    public ModException(IMod modInstance, String exceptionMessage){
        super(exceptionMessage);
        this.modInstance = modInstance;
    }

    public IMod getModInstance() {
        return modInstance;
    }

}
