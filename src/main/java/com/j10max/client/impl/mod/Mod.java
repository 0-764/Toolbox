package com.j10max.client.impl.mod;

import com.j10max.client.Client;
import com.j10max.client.api.mod.IMod;
import com.j10max.client.api.mod.ModType;
import com.j10max.client.api.mod.value.IModValue;
import com.j10max.client.impl.mod.value.ModValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import java.util.ArrayList;
import java.util.List;

public abstract class Mod implements IMod {

    public final Minecraft mcInstance = Minecraft.getMinecraft();

    /* final */
    private final String name;
    private final String version;
    private final ModType type;

    /* variable */
    private int keybind;
    private boolean state;

    /* optional */
    private String desc;
    private KeyBinding keybinding;

    private List<IModValue> values;

    public Mod(String modName, String modVersion, ModType modType) {
        this.name = modName;
        this.version = modVersion;
        this.type = modType;
        this.keybind = -1;
        this.state = false;
        this.desc = "";
        this.values = new ArrayList<>();
    }

    public abstract void onInit();

    public abstract void onEnable();

    public abstract void onDisable();

    @Override
    public void initMod() {
        this.onInit();
    }

    @Override
    public void registerValue(IModValue modValue) {
        this.getValues().add(modValue);
    }

    @Override
    public IModValue getValueByName(String modValueName) {
        for (IModValue modValue : this.values) {
            if(modValue.getName().equalsIgnoreCase(modValueName)){
                return modValue;
            }
        }
        return null;
    }

    @Override
    public void toggleState() {
        this.state = !this.state;
        /* Update config */
        Client.instance().getFileHandler().writeToModFile(this);
        /* Event update */
        if (this.getState()) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    @Override
    public void setKeybind(int modKeybind) {
        this.keybind = modKeybind;
        this.keybinding = new KeyBinding(this.name, this.keybind, Client.ID);
        /* Update config */
        Client.instance().getFileHandler().writeToModFile(this);
    }

    @Override
    public int getKeybind() {
        return keybind;
    }

    public KeyBinding getMCKeybinding() {
        return keybinding;
    }

    @Override
    public void setState(boolean modState) {
        this.state = modState;
    }

    @Override
    public boolean getState() {
        return state;
    }

    @Override
    public ModType getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setDesc(String modDesc) {
        this.desc = modDesc;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public List<IModValue> getValues() {
        return values;
    }

}
