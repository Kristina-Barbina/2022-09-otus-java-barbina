/*
 * Copyright (c) 2022. Created by Kristina Barbina.
 */

package ru.otus.core.sessionmanager;

public interface TransactionRunner {

    <T> T doInTransaction(TransactionAction<T> action);
}
