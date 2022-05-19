package com.fezmlg.utils;

import com.fezmlg.Logger;
import com.fezmlg.menu.MenuItem;
import com.fezmlg.order.Order;
import com.fezmlg.staff.Staff;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class JSONSaver {

    private final Gson gson;

    public JSONSaver() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        this.gson = gsonBuilder.setPrettyPrinting().create();
    }

    public void saveToFile(Object toSave, String file) {
        String filename = file + ".json";
        String path = "./";
        try {
            Writer writer = new FileWriter(path + filename);
            gson.toJson(toSave, writer);
            writer.flush();
            writer.close();
            new Logger(toSave + "object was saved to file" + filename);
        } catch (IOException e) {
            new Logger("Failed to save to file " + e);
        }
    }

    public ArrayList<Order> loadFromFileOrder(String file) {
        String filename = file + ".json";
        String path = "./";
        try {
            JsonReader reader = new JsonReader(new FileReader(path + filename));
            Type listType = new TypeToken<ArrayList<Order>>() {
            }.getType();
            return gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<MenuItem> loadFromFileMenu(String file) {
        String filename = file + ".json";
        String path = "./";
        try {
            JsonReader reader = new JsonReader(new FileReader(path + filename));
            Type listType = new TypeToken<ArrayList<MenuItem>>() {
            }.getType();
            return gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Staff> loadFromFileStaff(String file) {
        String filename = file + ".json";
        String path = "./";
        try {
            JsonReader reader = new JsonReader(new FileReader(path + filename));
            Type listType = new TypeToken<ArrayList<Staff>>() {
            }.getType();
            return gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
