package com.j10max.client.api.mod;

import com.j10max.client.api.Initializer;
import com.j10max.client.api.exception.mod.ModException;

import java.util.List;

public interface IModHandler extends Initializer {

    void registerMod(IMod modInstance) throws ModException;

    boolean containsModByName(String modName);

    IMod getModByName(String modName);

    List<IMod> getModsByType(ModType modType);

    List<IMod> getModList();

}
