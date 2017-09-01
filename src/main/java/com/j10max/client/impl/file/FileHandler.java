package com.j10max.client.impl.file;

import com.j10max.client.Client;
import com.j10max.client.api.IClient;
import com.j10max.client.api.file.IFileHandler;
import com.j10max.client.api.mod.IMod;
import com.j10max.client.api.mod.value.IModValue;
import com.j10max.client.impl.mod.value.ModValue;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FileHandler implements IFileHandler {

    private File modsFolder;

    @Override
    public void init(IClient clientInstance) {
        /* Mods folder */
        this.modsFolder = new File(clientInstance.getModFolder(), "mods/");
        this.modsFolder.mkdir();
        // Check mods file
        this.initModFiles();
    }

    @Override
    public void initModFiles() {
        for (IMod modInstance : Client.instance().getModHandler().getModList()) {
            File modFile = new File(this.modsFolder, String.format("%s.cfg", modInstance.getName()));
            if (modFile.exists()) {
                readFromModFile(modInstance);
            } else {
                writeToModFile(modInstance);
            }
        }
    }

    @Override
    public void readFromModFile(IMod modInstance) {
        File modFile = new File(this.modsFolder, String.format("%s.cfg", modInstance.getName()));
        /* Json */
        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(new FileReader(modFile));
            /* State */
            modInstance.setState((Boolean) obj.get("state"));
            /* Values */
            JSONArray jsonArray = (JSONArray) obj.get("values");
            Iterator it = jsonArray.iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                /* Mod value */
                IModValue modValue = modInstance.getValueByName((String) pair.getKey());
                if (modValue != null) {
                    modValue.parseValue((String) pair.getValue());
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeToModFile(IMod modInstance) {
        File modFile = new File(this.modsFolder, String.format("%s.cfg", modInstance.getName()));
        if (!modFile.exists()) try {
            modFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* JSON writer */
        JSONObject obj = new JSONObject();
        obj.put("id", modInstance.getName());
        obj.put("state", modInstance.getState());
        /* Mod values */
        JSONArray jsonArray = new JSONArray();
        for (IModValue modValue : modInstance.getValues()) {
            JSONObject jsonModValue = new JSONObject();
            /* Write json mod-value */
            jsonModValue.put(modValue.getName(), modValue.writeValue());
            /* Add to Module values Json Array */
            jsonArray.add(jsonModValue);
        }
        obj.put("values", jsonArray);
        /* Write file */
        try (FileWriter file = new FileWriter(modFile)) {
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File getModsFolder() {
        return modsFolder;
    }

}
