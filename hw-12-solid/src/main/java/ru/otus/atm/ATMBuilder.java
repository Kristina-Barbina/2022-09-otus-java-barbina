package ru.otus.atm;

import ru.otus.atm.model.ATM;
import ru.otus.atm.model.Banknote;
import ru.otus.atm.model.BanknotesBox;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Stack;

public class ATMBuilder {

    private final List<BanknotesBox> banknotesBoxes = new ArrayList<BanknotesBox>();

    public ATMBuilder(){
        for (Currency currency: ATM.getSupportedCurrencies()) {
            for (int note: ATM.getCurrencyNotes(currency)) {
                addBanknotesBox(currency, note, 1000);
            }
        }

    }

    public ATM Build() {
        ATM atm = new ATM();
         for (BanknotesBox box : banknotesBoxes) {
            atm.loadBanknotesBox(box);
         }
         return atm;
    }

    public ATMBuilder addBanknotesBox(Currency currency, Integer note, Integer quantity) {
        Stack<Banknote> banknotesStack = new Stack<>();
        Integer StartSerialFrom = MathHelper.randInt(1000, 100000);
        for (int i = 0; i < quantity; i++) {
            banknotesStack.add(new Banknote(currency, note, "" + StartSerialFrom+i));
        }
        banknotesBoxes.add(new BanknotesBox(currency, note, quantity, banknotesStack));
        return this;
    }
}
