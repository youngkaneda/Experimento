
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ifpb.business.RandomChoiceCreator;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kuuhaku
 */
public class Loader {

    private static Random rng = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(getAlternatives(i));
        }
    }

    public static List<String> getAlternatives(int index) {
        Set<String> collectedMethods = new HashSet<>();
        List<Integer> visitedArrays = new ArrayList<>();
        ClassLoader classLoader = new RandomChoiceCreator().getClass().getClassLoader();
        String path = classLoader.getResource("repo.json").getFile();
        JsonObject object;
        try {
            object = (JsonObject) new JsonParser().parse(new FileReader(path));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        JsonArray methodTypes = object.getAsJsonArray("method_types");
        int methodTypesRngIndex;
        for (int i = 0; i < 5; i++) {
            if (i == 4 && !visitedArrays.contains(index)) {
                collectMethod(collectedMethods, methodTypes, index);
                visitedArrays.add(index);
            }
            methodTypesRngIndex = rng.nextInt(5);
            visitedArrays.add(methodTypesRngIndex);
            collectMethod(collectedMethods, methodTypes, methodTypesRngIndex);
        }
        return collectedMethods.stream().collect(Collectors.toList());
    }

    public static void collectMethod(Set<String> collectedMethods, JsonArray methodTypes, int index) {
        JsonArray methods = methodTypes.get(index)
                .getAsJsonObject()
                .get("methods")
                .getAsJsonArray();
        int methodRngIndex = rng.nextInt(methods.size());
        collectedMethods.add(methods.get(methodRngIndex).getAsString());
    }
}
