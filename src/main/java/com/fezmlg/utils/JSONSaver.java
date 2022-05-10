package com.fezmlg.utils;

import com.fezmlg.Logger;
import com.fezmlg.menu.Menu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class JSONSaver {
    public void saveToFile(Object toSave, String file){
        String filename = file + ".json";
        String path = "./";
        try {
            Writer writer = new FileWriter(path + filename);
            Gson gson = new GsonBuilder().create();
            gson.toJson(toSave, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            new Logger("Failed to save to file " + e);
        }

    }

}
