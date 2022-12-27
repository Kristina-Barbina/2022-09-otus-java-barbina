/*
 * Copyright (c) 2022. Created by Kristina Barbina.
 */

package ru.otus.core.sessionmanager;

import java.sql.Connection;
import java.util.function.Function;

public interface TransactionAction<T> extends Function<Connection, T> {
}
