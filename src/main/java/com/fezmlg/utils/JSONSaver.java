package com.fezmlg.utils;

import com.fezmlg.Logger;
import com.fezmlg.order.Order;
import com.fezmlg.order.OrderController;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class JSONSaver {

    private Gson gson;

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

    public ArrayList<Order> loadFromFile(String file) {
        String filename = file + ".json";
        String path = "./";
        try {
            JsonReader reader = new JsonReader(new FileReader(path + filename));
            Type orderListType = new TypeToken<ArrayList<Order>>() {
            }.getType();
            return gson.fromJson(reader, orderListType);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
