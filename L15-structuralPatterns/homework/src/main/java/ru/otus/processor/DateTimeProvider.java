/*
 * Copyright (c) 2022. Created by Kristina Barbina.
 */

package ru.otus.processor;

import java.time.LocalDateTime;

@FunctionalInterface
public interface DateTimeProvider {
    LocalDateTime getDate();
}
