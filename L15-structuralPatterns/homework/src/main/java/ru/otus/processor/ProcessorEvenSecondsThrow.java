package ru.otus.processor;

import ru.otus.model.Message;

public class ProcessorEvenSecondsThrow implements Processor {

    DateTimeProvider dateTimeProvider;
    public ProcessorEvenSecondsThrow(DateTimeProvider dateTimeProvider){
        this.dateTimeProvider = dateTimeProvider;
    }
    @Override
    public Message process(Message message) {

        if (dateTimeProvider.getDate().getSecond()%2 == 0)
            throw new EvenSecondsException("Even second");

        return message;
    }

    static class EvenSecondsException extends RuntimeException {
        public EvenSecondsException(String msg) {
            super(msg);
        }
    }
}
