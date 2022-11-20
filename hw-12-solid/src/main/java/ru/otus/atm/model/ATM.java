package ru.otus.atm.model;

import java.util.*;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;


public class ATM {

    private final Table<Currency, Integer, BanknotesBox> banknoteBoxes = HashBasedTable.create();

    private static List<Currency> supportedCurrencies;
    public static List<Currency> getSupportedCurrencies(){
        if(supportedCurrencies == null) {
            supportedCurrencies = new ArrayList<>();
            for (String currencyCode : Arrays.asList("USD", "EUR", "RUR")) {
                supportedCurrencies.add(Currency.getInstance(currencyCode));
            }
        }
        return Collections.unmodifiableList(supportedCurrencies);
    }
    public static HashSet<Integer> getCurrencyNotes(Currency currency){
        if("USD".equals(currency.getCurrencyCode())){
            return new HashSet<>(Arrays.asList(1,2,5,20,50,100));
        } else if ("EUR".equals(currency.getCurrencyCode())) {
            return new HashSet<>(Arrays.asList(5,10,20,50,100,200,500));
        } else if ("RUR".equals(currency.getCurrencyCode())) {
            return new HashSet<>(Arrays.asList(50,100,200,500,1000,2000,5000));
        }
        throw new RuntimeException("Not supported currency!");
    }


    public void loadBanknotesBox(BanknotesBox box){
        banknoteBoxes.put(box.getCurrency(), box.getNote(), box);
    }

    /**
     * @param banknotes inserted banknotes
     * @return unaccepted banknotes
     */
    public List<Banknote> insertBanknotes(List<Banknote>banknotes){
        List<Banknote>unacceptedBanknotes = new ArrayList<>();
        for (Banknote banknote: banknotes) {
            BanknotesBox box = banknoteBoxes.get(banknote.getCurrency(), banknote.getNote());
            if(box==null) {
                unacceptedBanknotes.add(banknote);
            }else {
                box.putBanknotes(List.of(banknote));
            }

        }
        return unacceptedBanknotes;
    }

    public List<Banknote> withdrawMoney(Currency currency, double amount){
        List<Banknote> withdrawnBanknotes = new ArrayList<>();
        double remainAmount = amount;



        Map<Integer, BanknotesBox> boxes = banknoteBoxes.row(currency);
        for (BanknotesBox box: boxes.values()) {
            if(box.getNote() * box.getQuantity() < remainAmount){
                withdrawnBanknotes.addAll(box.takeOffBanknotes(box.getQuantity()));
                remainAmount -= box.getNote() * box.getQuantity();
            }else {
                int quantity = (int) (remainAmount / (box.getNote()));
                remainAmount -= box.getNote() * quantity;
                withdrawnBanknotes.addAll(box.takeOffBanknotes(quantity));
            }
            if (remainAmount == 0)
                break;

        }
        if (remainAmount != 0)
            throw new RuntimeException("unable to withdraw amount " + remainAmount);

        return withdrawnBanknotes;
    }

    public HashMap<Currency, Double> getCurrencyAmounts(){
        HashMap<Currency, Double> currencyAmount = new HashMap<>();
        for (BanknotesBox box : banknoteBoxes.values()){
            Double currentAmount = currencyAmount.get(box.getCurrency());
            if (currentAmount == null)
                currencyAmount.put(box.getCurrency(), (double)box.getNote() * box.getQuantity());
            else
                currencyAmount.put(box.getCurrency(), currentAmount + box.getNote()*box.getQuantity());

        }

        return currencyAmount;
    }


}
