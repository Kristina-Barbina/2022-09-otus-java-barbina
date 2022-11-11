package ru.otus.atm.model;

import java.util.Currency;

public class Banknote {
    private final Currency currency;
    private final int note;
    private final String serial;

    public Banknote(Currency currency, Integer note, String serial){
        this.currency = currency;
        this.note = note;
        this.serial = serial;
    }

    public Currency getCurrency() {
        return currency;
    }

    public int getNote() {
        return note;
    }

    public String getSerial() {
        return serial;
    }
}
