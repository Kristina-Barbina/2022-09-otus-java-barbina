/*
 * Copyright (c) 2022. Created by Kristina Barbina.
 */

package ru.otus.processor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.model.Message;

import static org.assertj.core.api.Assertions.assertThat;

class ProcessorSwapFieldsTest {

    @Test
    @DisplayName("Swap fields test")
    void processTest() {
        var processor = new ProcessorSwapFields();
        String field11 = "field11";
        String field12 = "field12";
        var message = (new Message.Builder(1)).field11(field11).field12(field12).build();

        //when
        var processedMessage = processor.process(message);

        //then
        assertThat(processedMessage.getField12()).isEqualTo(field11);
        assertThat(processedMessage.getField11()).isEqualTo(field12);





    }
}