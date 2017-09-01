package com.j10max.client.api.file;

import com.j10max.client.api.Initializer;
import com.j10max.client.api.mod.IMod;

import java.io.File;

public interface IFileHandler extends Initializer {

    void initModFiles();

    void readFromModFile(IMod modInstance);

    void writeToModFile(IMod modInstance);

    File getModsFolder();

}
