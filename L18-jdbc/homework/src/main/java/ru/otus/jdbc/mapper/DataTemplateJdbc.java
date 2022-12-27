/*
 * Copyright (c) 2022. Created by Kristina Barbina.
 */

package ru.otus.jdbc.mapper;

import ru.otus.core.repository.DataTemplate;
import ru.otus.core.repository.DataTemplateException;
import ru.otus.core.repository.executor.DbExecutor;
import ru.otus.core.repository.executor.DbExecutorImpl;
import ru.otus.crm.model.Client;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Сохратяет объект в базу, читает объект из базы
 */
public class DataTemplateJdbc<T> implements DataTemplate<T> {

    private final DbExecutor dbExecutor;
    private final EntityClassMetaData<T> entityClassMetaData;
    private final EntitySQLMetaData entitySQLMetaData;

    public DataTemplateJdbc(DbExecutor dbExecutor, EntityClassMetaData<T> entityClassMetaData, EntitySQLMetaData entitySQLMetaData) {
        this.dbExecutor = dbExecutor;
        this.entityClassMetaData = entityClassMetaData;
        this.entitySQLMetaData = entitySQLMetaData;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectByIdSql(), List.of(id),
                rs -> {
            try {
                if (rs.next()) {
                    T result = entityClassMetaData.getConstructor().newInstance();
                    for(var field: entityClassMetaData.getAllFields()){
                        field.setAccessible(true);
                        field.set(result, rs.getObject(field.getName(), field.getType()));
                    }
                    return result; //new Client(rs.getLong("id"), rs.getString("name"));
                }
                return null;
            } catch (Exception e) {
                throw new DataTemplateException(e);
            }
        });
    }

    @Override
    public List<T> findAll(Connection connection) {
        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectAllSql(), List.of(),
                rs -> {
                    var dataObjectsArray = new ArrayList<T>();
                    try {
                        if (rs.next()) {
                            T dataObject = entityClassMetaData.getConstructor().newInstance();
                            for(var field: entityClassMetaData.getAllFields()){
                                field.setAccessible(true);
                                field.set(dataObjectsArray, rs.getObject(field.getName(), field.getType()));
                            }
                            dataObjectsArray.add(dataObject);
                        }
                        return dataObjectsArray;
                    } catch (Exception e) {
                        throw new DataTemplateException(e);
                    }
                }).get();
    }

    @Override
    public long insert(Connection connection, T dataObj) {
        var executor = new DbExecutorImpl();
        var dataFields = new ArrayList<Object>();
        for(var field: entityClassMetaData.getFieldsWithoutId()){
            field.setAccessible(true);
            try {
                dataFields.add(field.get(dataObj));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return executor.executeStatement(connection,
                entitySQLMetaData.getInsertSql(),
                dataFields);
    }

    @Override
    public void update(Connection connection, T dataObj) {
        var executor = new DbExecutorImpl();
        var dataFields = new ArrayList<Object>();
        for(var field: entityClassMetaData.getAllFields()){
            field.setAccessible(true);
            try {
                dataFields.add(field.get(dataObj));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        //Id
        Field field = entityClassMetaData.getIdField();
        field.setAccessible(true);
        try {
            dataFields.add(field.get(dataObj));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        executor.executeStatement(connection,
                entitySQLMetaData.getUpdateSql(),
                dataFields);
    }
}
