package com.j10max.client.api;

import com.j10max.client.api.file.IFileHandler;
import com.j10max.client.api.friend.IFriendHandler;
import com.j10max.client.api.gui.panel.IPanelHandler;
import com.j10max.client.api.mod.IModHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public interface IClient {

    void preInit(FMLPreInitializationEvent event);

    void init(FMLInitializationEvent event);

    void postInit(FMLPostInitializationEvent event);

    IFriendHandler getFriendHandler();

    IModHandler getModHandler();

    IPanelHandler getPanelHandler();

    IFileHandler getFileHandler();

    File getModFolder();

}
