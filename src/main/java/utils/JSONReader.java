package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Laureates;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JSONReader {

    @Nullable
    public File fileReader(String name) {
        URL resource = getClass().getClassLoader().getResource(name);
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        } else {
            try {
                return new File(resource.toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Laureates> JSONParser(File file) {
        Gson gson = new Gson();
        List<Laureates> laureates = new ArrayList<>();
        try (Reader reader = new FileReader(file)) {

            laureates = gson.fromJson(reader, new TypeToken<List<Laureates>>() {
            }.getType());


        } catch (IOException e) {
            e.printStackTrace();
        }
        return laureates;
    }
}
