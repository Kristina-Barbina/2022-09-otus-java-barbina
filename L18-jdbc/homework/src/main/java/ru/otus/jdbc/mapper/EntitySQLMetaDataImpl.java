/*
 * Copyright (c) 2022. Created by Kristina Barbina.
 */

package ru.otus.jdbc.mapper;

import java.lang.reflect.Field;

/**
 * Создает SQL - запросы
 */
public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData {

    private final EntityClassMetaData<T> entityClassMetaData;
    public EntitySQLMetaDataImpl(EntityClassMetaData<T> entityClassMetaData) {
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public String getSelectAllSql() {
        return String.format("select * from \"%s\"", entityClassMetaData.getName());
    }

    @Override
    public String getSelectByIdSql() {
        //like: "select id, name from client where id  = ?"
        var fieldsBuilder = new StringBuilder();
        String splitter = "";
        for (Field field: entityClassMetaData.getAllFields()) {
            fieldsBuilder.append(splitter);
            fieldsBuilder.append(field.getName());
            splitter = ", ";
        }

        return String.format("select %s from \"%s\" where %s = ?"
                , fieldsBuilder
                , entityClassMetaData.getName()
                , entityClassMetaData.getIdField().getName());
    }

    @Override
    public String getInsertSql() {

        //"insert into client(name) values (?)"
        var fieldsBuilder = new StringBuilder();
        var fieldsValuesBuilder = new StringBuilder();
        String splitter = "";
        for (Field field: entityClassMetaData.getFieldsWithoutId()) {
            fieldsBuilder.append(splitter);
            fieldsBuilder.append(field.getName());

            fieldsValuesBuilder.append(splitter);
            fieldsValuesBuilder.append("?");
            splitter = ", ";
        }
        return String.format("insert into \"%s\"(%s) values (%s)"
                , entityClassMetaData.getName()
                , fieldsBuilder
                , fieldsValuesBuilder);

    }

    @Override
    public String getUpdateSql() {
        //"update client set name = ? where id = ?"
        var fieldsBuilder = new StringBuilder();

        String splitter = "";
        for (Field field: entityClassMetaData.getFieldsWithoutId()) {
            fieldsBuilder.append(splitter);
            fieldsBuilder.append(field.getName());
            fieldsBuilder.append(" = ?");
            splitter = ", ";
        }

        return String.format("update \"%s\" set %s where %s = ?"
                , entityClassMetaData.getName()
                , fieldsBuilder
                , entityClassMetaData.getIdField().getName());
    }
}
