package com.j10max.client.api.mod;

import com.j10max.client.api.mod.value.IModValue;
import net.minecraft.client.settings.KeyBinding;

import java.util.List;

public interface IMod {

    void initMod();

    void registerValue(IModValue modValue);

    IModValue getValueByName(String modValueName);

    void setKeybind(int modKeybind);

    int getKeybind();

    void toggleState();

    void setState(boolean modState);

    boolean getState();

    ModType getType();

    String getName();

    void setDesc(String modDesc);

    String getDesc();

    String getVersion();

    List<IModValue> getValues();

}
