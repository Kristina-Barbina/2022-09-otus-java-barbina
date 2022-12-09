package ru.otus.dataprocessor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import ru.otus.model.Measurement;

import java.io.File;
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
        try {
            return mapper.readValue(file, new TypeReference<List<MeasurementLoader>>() {})
                    .stream().map(MeasurementLoader::getMeasurement).toList();
        } catch (IOException e) {
            throw new FileProcessException(e);
        }


   }

   static class MeasurementLoader {
       @JsonProperty("name")
       private String name;
       @JsonProperty("value")
       private double value;

       public MeasurementLoader(){}
       public Measurement getMeasurement(){
           return new Measurement(name, value);
       }
   }

}
