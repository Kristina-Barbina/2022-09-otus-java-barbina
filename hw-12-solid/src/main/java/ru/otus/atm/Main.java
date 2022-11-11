package ru.otus.atm;

import ru.otus.atm.model.ATM;
import ru.otus.atm.model.Banknote;

import java.util.*;

public class Main {
    public static void main(String[] args) {



        ATMBuilder atmBuilder = new ATMBuilder();
        ATM atm = atmBuilder.Build();

        printCurrencyAmounts(atm);

        insertRandomBanknotes(atm, 200);

        printCurrencyAmounts(atm);

        withdrawMoney(atm, "USD", 54325);

        printCurrencyAmounts(atm);

    }

    private static void printCurrencyAmounts(ATM atm) {
        HashMap<Currency, Double> currencyAmounts = atm.getCurrencyAmounts();
        for (Currency currency: currencyAmounts.keySet()) {
            System.out.printf("%s: %f%n", currency.getCurrencyCode(), currencyAmounts.get(currency));
        }
    }

    private static void withdrawMoney(ATM atm, String currencyCode, double amount) {
        Currency currency = Currency.getInstance(currencyCode);

        List<Banknote> banknotes = atm.withdrawMoney(currency, amount);

        System.out.println("Withdrawn cash from an ATM");
        for (Banknote banknote : banknotes) {
            System.out.printf("%1$d %2$s%n", banknote.getNote(), banknote.getCurrency().getCurrencyCode());
        }

    }

    private static void insertRandomBanknotes(ATM atm, int quantity){
        //quantity of random banknotes
        List<Banknote> banknoteList = new ArrayList<>();
        List<Currency> supportedCurrencies = ATM.getSupportedCurrencies();
        for (int i = 0; i < quantity; i++) {
            Currency currency = supportedCurrencies.get(MathHelper.randInt(0, supportedCurrencies.size()-1));

            HashSet<Integer> currencyNotes = ATM.getCurrencyNotes(currency);

            ArrayList<Integer> notesList = new ArrayList<>(currencyNotes);
            Integer note = notesList.get(MathHelper.randInt(0, notesList.size()-1));

            String serial = MathHelper.randInt(1000, 100000).toString();

            banknoteList.add(new Banknote(currency, note, serial));
        }

        List<Banknote> unacceptedBanknotes = atm.insertBanknotes(banknoteList);
        if (unacceptedBanknotes.size() == 0)
            System.out.println("All banknotes inserted in ATM are accepted");
        else
            System.out.printf("%d banknotes inserted in ATM are not accepted%n", unacceptedBanknotes.size());
    }

}