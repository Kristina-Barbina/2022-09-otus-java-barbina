package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import ru.otus.model.Measurement;

import java.io.*;
import java.util.List;

public class ResourcesFileLoader implements Loader {

    private final ObjectMapper mapper = new ObjectMapper();
    private final String fileName;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        //читает файл, парсит и возвращает результат
        var file = new File(fileName);
        var gson = new Gson();
        try {
            //return gson.fromJson(new JsonReader(new FileReader(fileName)), new TypeToken<List<Measurement>>() {}.getType());
            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
            assert resourceAsStream != null;
            return gson.fromJson(new JsonReader(new InputStreamReader(resourceAsStream)), new TypeToken<List<Measurement>>() {}.getType());

        } catch (Exception e) {
            throw new FileProcessException(e);
        }


   }
}
