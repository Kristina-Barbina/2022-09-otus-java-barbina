/*
 * Copyright (c) 2022. Created by Kristina Barbina.
 */

package ru.otus.processor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.model.Message;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class ProcessorEvenSecondsThrowTest {

    @Test
    @DisplayName("test")
    void process() {
        var message = mock(Message.class);

        //throw exception
        var dateTime = new CustomDateTime(LocalDateTime.of(
                1, 1, 1, 0, 1, 2));

        var processor1 = new ProcessorEvenSecondsThrow(dateTime::get);
        assertThrows(ProcessorEvenSecondsThrow.EvenSecondsException.class, ()-> processor1.process(message));
        assertThrows(ProcessorEvenSecondsThrow.EvenSecondsException.class, ()-> processor1.process(message));
        assertThrows(ProcessorEvenSecondsThrow.EvenSecondsException.class, ()-> processor1.process(message));
        assertThrows(ProcessorEvenSecondsThrow.EvenSecondsException.class, ()-> processor1.process(message));

        //not throw exception
        var processor2 = new ProcessorEvenSecondsThrow(
                new CustomDateTime(LocalDateTime.of(
                        1, 1, 1, 0, 1, 1))
                        ::get);

        assertThat(processor2.process(message)).isEqualTo(message);
        assertThat(processor2.process(message)).isEqualTo(message);
        assertThat(processor2.process(message)).isEqualTo(message);

        //throw exception
        var processor3 = new ProcessorEvenSecondsThrow(
                new CustomDateTime(LocalDateTime.of(
                        1, 1, 1, 0, 1, 0))
                        ::get);
        assertThrows(ProcessorEvenSecondsThrow.EvenSecondsException.class, ()-> processor3.process(message));

    }

    static class CustomDateTime{
        LocalDateTime dateTime;
        CustomDateTime(LocalDateTime dateTime){
            this.dateTime = dateTime;
        }
        public LocalDateTime get(){
            return dateTime;
        }
    }
}