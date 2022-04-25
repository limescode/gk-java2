package pl.limescode.lesson3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lesson3 {

    public static void main(String[] args) {
        List<String> list = List.of(
                "ab", "bc", "cd", "de", "ef", "fg", "gh", "hi", "ik", "kl", "lm", "mn",
                "bc", "ef", "hi");
        System.out.println("Size: " + list.size() + ", given list=" + list);

        printUniqueElements(list);

        Phonebook phonebook = new Phonebook();
        phonebook.add("Ivanov", "2222");
        phonebook.add("Petrov", "1111");
        phonebook.add("Stepanov", "3333");
        phonebook.add("Ivanov", "4444");
        System.out.println("Telephones of Ivanov: " + phonebook.get("Ivanov"));
        System.out.println("Telephones of Sidorov: " + phonebook.get("Sidorov"));
    }

    public static void printUniqueElements(List<String> list) {
        Set<String> set = new HashSet<>();
        set.addAll(list);
        System.out.println("Size: " + set.size() + ", unique set=" + set);
    }

}
