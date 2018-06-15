/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.business;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kuuhaku
 */
public class QuestionsConverter {
    
    public static List<String> getStringQuestions() {
        ClassLoader classLoader = new RandomChoiceCreator().getClass().getClassLoader();
        String path = classLoader.getResource("repo.json").getFile();
        JsonObject object;
        try {
            object = (JsonObject) new JsonParser().parse(new FileReader(path));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        JsonArray questions = object.getAsJsonArray("questions");
        List<String> list = new ArrayList<>();
        for (JsonElement question : questions) {
            list.add(question.getAsString());
        }
        return list;
    }
    
}
