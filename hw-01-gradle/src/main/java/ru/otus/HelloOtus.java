package ru.otus;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class HelloOtus {
    public static void Hello(){
        String[] words = new String[]{"1","2","3"};
        Multiset<String> counts = HashMultiset.create();
        for (String word : words) {
            counts.add(word);
        }
    }

}
