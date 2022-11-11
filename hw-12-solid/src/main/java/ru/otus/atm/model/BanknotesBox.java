package ru.otus.atm.model;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Stack;

public class BanknotesBox {
    private final Currency currency;
    private final int note; //note of banknote
    private int quantity;
    private final Stack<Banknote> banknotes;

    private final static int maximumLimitOfBanknotes = 10000;

    public BanknotesBox(Currency currency, int note, int quantity, Stack<Banknote> banknotes) {
        this.currency = currency;
        this.note = note;
        this.quantity = quantity;
        this.banknotes = banknotes;
    }

    public Currency getCurrency() {
        return currency;
    }

    public int getNote() {
        return note;
    }

    public int getQuantity() {
        return quantity;
    }

    public List<Banknote> takeOffBanknotes(int quantity){
        if (this.quantity < quantity){
            throw new RuntimeException("Not enough banknotes in box " + this);
        }
        this.quantity-=quantity;
        List<Banknote> withdrawnBanknotes = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            withdrawnBanknotes.add(banknotes.pop());
        }
        return withdrawnBanknotes;
    }

    public void putBanknotes(List<Banknote> banknotes){
        if (this.quantity+banknotes.size() > maximumLimitOfBanknotes)
            throw new RuntimeException("Box is overflowed " + this);
        this.quantity+=banknotes.size();
        for (Banknote banknote: banknotes) {
            this.banknotes.push(banknote);
        }
    }

}
