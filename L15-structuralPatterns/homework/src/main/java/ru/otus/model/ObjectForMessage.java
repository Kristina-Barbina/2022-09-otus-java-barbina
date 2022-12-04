package ru.otus.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class ObjectForMessage {
    private List<String> data = new CopyOnWriteArrayList<>();

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = (data == null) ? null : new CopyOnWriteArrayList<>(data);
    }

    public ObjectForMessage clone () {
        ObjectForMessage clone = new ObjectForMessage();
        clone.data = (data == null) ? null : new CopyOnWriteArrayList<>(data);
        return clone;
    }

}
