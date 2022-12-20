package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import ru.otus.model.Measurement;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
            return gson.fromJson(new JsonReader(new FileReader(fileName)), new TypeToken<List<Measurement>>() {
            }.getType());
        } catch (IOException e) {
            throw new FileProcessException(e);
        }


   }
}
