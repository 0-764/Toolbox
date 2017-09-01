package com.j10max.client;

import com.j10max.client.api.IClient;
import com.j10max.client.api.file.IFileHandler;
import com.j10max.client.api.friend.IFriendHandler;
import com.j10max.client.api.gui.panel.IPanelHandler;
import com.j10max.client.api.mod.IModHandler;
import com.j10max.client.impl.file.FileHandler;
import com.j10max.client.impl.mod.ModHandler;
import com.j10max.client.impl.panel.PanelHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

@Mod(modid = Client.ID, name = Client.NAME, version = Client.VERSION)
@SideOnly(Side.CLIENT)
public class Client implements IClient {

    public static final String ID = "toolbox";
    public static final String NAME = "Toolbox";
    public static final String VERSION = "1.0";

    @Mod.Instance
    private static Client INSTANCE;

    private IModHandler modHandler;
    private IPanelHandler panelHandler;
    private IFileHandler fileHandler;

    private File modFolder;

    public Client() {
        INSTANCE = this;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        if (event.getSide() == Side.SERVER) return;
        /* Mod Folder */
        this.modFolder = new File(event.getModConfigurationDirectory(), NAME + "/");
        this.modFolder.mkdirs();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        this.fileHandler = new FileHandler();
        /* List of Handlers */
        // Mods
        this.modHandler = new ModHandler();
        this.modHandler.init(this);
        // Panels
        this.panelHandler = new PanelHandler();
        this.panelHandler.init(this);
        // Load files
        this.fileHandler.init(this);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
    }

    @Override
    public IFriendHandler getFriendHandler() {
        return null;
    }

    @Override
    public IModHandler getModHandler() {
        return modHandler;
    }

    @Override
    public IPanelHandler getPanelHandler() {
        return panelHandler;
    }

    @Override
    public IFileHandler getFileHandler() {
        return fileHandler;
    }

    @Override
    public File getModFolder() {
        return modFolder;
    }

    public static Client instance(){
        return INSTANCE;
    }

}
