package ru.otus.dataprocessor;

import ru.otus.model.Measurement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProcessorAggregator implements Processor {

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        //группирует выходящий список по name, при этом суммирует поля value
        var result = new HashMap<String, Double>();
        data.forEach((item)->{
            if (result.containsKey(item.getName())) {
                result.put(item.getName(), result.get(item.getName()) + item.getValue());
            }else {
                result.put(item.getName(), item.getValue());
            }
        });
        return new TreeMap<>(result);
    }
}
