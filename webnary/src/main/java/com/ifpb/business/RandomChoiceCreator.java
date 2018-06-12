/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.business;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author kuuhaku
 */
public class RandomChoiceCreator {

    public static List<String> getAlternatives() {
        Set<String> collectedMethods = new HashSet<>();
        ClassLoader classLoader = new RandomChoiceCreator().getClass().getClassLoader();
        String path = classLoader.getResource("repo.json").getFile();
        JsonObject object;
        try {
            object = (JsonObject) new JsonParser().parse(new FileReader(path));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        JsonArray method_types = object.getAsJsonArray("method_types");
        randomCollectMethod(collectedMethods, 5, method_types);
        while(collectedMethods.size() < 8)
            randomCollectMethod(collectedMethods, 8-collectedMethods.size(), method_types);
        return collectedMethods.stream().collect(Collectors.toList());
    }

    private static void randomCollectMethod(Set<String> collectedMethods, int numberOfChoices, JsonArray methodTypesJson) {
        if (numberOfChoices > methodTypesJson.size()) {
            throw new RuntimeException("number of choices is greater than methodsType array");
        }
        Set<Integer> visitedArrayIds = new HashSet<>();
        Random random = new Random();
        JsonObject tempObj;
        JsonArray tempArray;
        while (visitedArrayIds.size() != numberOfChoices) {
            int nextInt = random.nextInt(methodTypesJson.size());
            boolean addedId = visitedArrayIds.add(nextInt);
            if (!addedId) {
                continue;
            }
            tempObj = methodTypesJson.get(nextInt).getAsJsonObject();
            tempArray = tempObj.getAsJsonArray("methods");
            collectedMethods.add(tempObj.get("method_type")+":"+tempArray.get(random.nextInt(tempArray.size())).getAsString());
        }
    }
}
