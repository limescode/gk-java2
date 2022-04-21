package pl.limescode.lesson3;

import java.util.*;

public class Phonebook {

    private Map<String, Set<String>> book = new HashMap<>();

    public void add(String surname, String telephone) {
        Set<String> phones = book.getOrDefault(surname, new HashSet<>());
        phones.add(telephone);
        book.put(surname, phones);
    }

    public Set<String> get(String surname) {
        return book.getOrDefault(surname, Set.of("No data"));
    }

}