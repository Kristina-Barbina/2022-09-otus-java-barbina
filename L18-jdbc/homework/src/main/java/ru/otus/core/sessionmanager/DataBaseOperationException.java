/*
 * Copyright (c) 2022. Created by Kristina Barbina.
 */

package ru.otus.core.sessionmanager;

public class DataBaseOperationException extends RuntimeException {
    public DataBaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
