package com.j10max.client.impl.mod;

import com.j10max.client.api.IClient;
import com.j10max.client.api.exception.mod.ModException;
import com.j10max.client.api.mod.IMod;
import com.j10max.client.api.mod.IModHandler;
import com.j10max.client.api.mod.ModType;
import com.j10max.client.impl.util.ReflectionUtil;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ModHandler implements IModHandler {

    private List<IMod> modList;

    public ModHandler() {
        this.modList = new CopyOnWriteArrayList<IMod>();
    }

    @Override
    public void init(IClient clientInstance) {
        FMLCommonHandler.instance().bus().register(this);
        try {
            // Get classes
            Class[] classes = ReflectionUtil.getClasses(getClass().getPackage().getName());
            for (Class clazz : classes) {
                registerMod((IMod) clazz.newInstance());
            }
        } catch (ModException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onKeyInputEvent(InputEvent.KeyInputEvent event) {
        for (IMod modInstance : this.modList) {
            if (modInstance.getKeybind() != -1) {
                Mod tempModInstance = (Mod) modInstance;
                if (tempModInstance.getMCKeybinding() != null && tempModInstance.getMCKeybinding().isPressed()) {
                    modInstance.toggleState();
                }
            }
        }
    }

    @Override
    public void registerMod(IMod modInstance) throws ModException {
        if (!containsModByName(modInstance.getName())) {
            modInstance.initMod();
            this.getModList().add(modInstance);
            if (modInstance.getKeybind() != -1) {
                /* Register Forge Keybind TODO - temp fix */
                Mod tempModInstance = (Mod) modInstance;
                if (tempModInstance.getMCKeybinding() != null) {
                    ClientRegistry.registerKeyBinding(tempModInstance.getMCKeybinding());
                }
            }
        } else {
            throw new ModException(modInstance, String.format("Mod already exists with Name `%s`", modInstance.getName()));
        }
    }

    @Override
    public boolean containsModByName(String modName) {
        return this.getModByName(modName) != null;
    }

    @Override
    public IMod getModByName(String modName) {
        for (IMod indexModInstance : this.getModList()) {
            if (indexModInstance.getName().equalsIgnoreCase(modName)) { // ignore caps
                return indexModInstance;
            }
        }
        return null;
    }

    @Override
    public List<IMod> getModsByType(ModType modType) {
        List<IMod> modsByType = new ArrayList<IMod>();
        for (IMod indexModInstance : this.getModList()) {
            if (indexModInstance.getType() == modType) {
                modsByType.add(indexModInstance);
            }
        }
        return modsByType;
    }

    @Override
    public List<IMod> getModList() {
        return modList;
    }

}
