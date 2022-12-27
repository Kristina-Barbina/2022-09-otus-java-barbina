/*
 * Copyright (c) 2022. Created by Kristina Barbina.
 */

package ru.otus.jdbc.mapper;

import ru.otus.annotations.DbIdField;
import ru.otus.annotations.DbTableName;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * "Разбирает" объект на составные части
 */
public class EntityClassMetaDataImpl<T> implements EntityClassMetaData {

    Class<T> clazz;
    public EntityClassMetaDataImpl(Class<T> c){
        clazz = c;
    }

    @Override
    public String getName() {
        return clazz.getAnnotation(DbTableName.class).Name();
    }


    @Override
    public Constructor<T> getConstructor() {
        try {
            return clazz.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Field getIdField() {
        var idAnnotation = DbIdField.class;
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(idAnnotation)) {
                return field;
            }
        }
        return null;
    }

    @Override
    public List<Field> getAllFields() {
        return List.of(clazz.getDeclaredFields());
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        var idAnnotation = DbIdField.class;
        var result = new ArrayList<Field>();
        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(idAnnotation)) {
                result.add(field);
            }
        }
        return result;
    }
}
