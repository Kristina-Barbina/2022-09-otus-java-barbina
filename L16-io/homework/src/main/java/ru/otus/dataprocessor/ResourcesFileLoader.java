package ru.otus.dataprocessor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.google.gson.Gson;
import ru.otus.model.Measurement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
//        HandlerInstantiator handlerInstantiator = ;
//        mapper.setHandlerInstantiator(handlerInstantiator);
        try {
//           //TODO
//                gson.fromJson()
            //return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Measurement.class));
//            return mapper.readValue(file, new TypeReference<List<Measurement>>() {});
            return null;
        } catch (IOException e) {
            throw new FileProcessException(e);
        }
    }

//    class HandlerInstantiator extends com.fasterxml.jackson.databind.cfg.HandlerInstantiator{
//
//    }

}
