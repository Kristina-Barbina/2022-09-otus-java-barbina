/*
 * Copyright (c) 2022. Created by Kristina Barbina.
 */

package ru.otus.crm.model;

import ru.otus.annotations.DbIdField;
import ru.otus.annotations.DbTableName;

@DbTableName(Name="client")
public class Client {
    @DbIdField
    private Long id;
    private String name;

    public Client() {
    }

    public Client(String name) {
        this.id = null;
        this.name = name;
    }

    public Client(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
