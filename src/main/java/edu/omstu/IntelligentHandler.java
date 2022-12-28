package edu.omstu;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class IntelligentHandler {
    private Map<Pair<String, Integer>, Map<Pair<String, Integer>, String>> tree;

    public IntelligentHandler(String config) {
        tree = new HashMap<>();
        InputStream in = getClass().getClassLoader().getResourceAsStream(config);
        List<String> lines = new BufferedReader(new InputStreamReader(in)).lines().toList();
        for (String line : lines) {
            try (Scanner scanner = new Scanner(line)) {
                String filename = scanner.next();
                int number = scanner.nextInt();
                Pair<String, Integer> key = new Pair<String, Integer>(filename, number);
                tree.put(key, new HashMap<Pair<String, Integer>, String>());
                String exception = scanner.next();
                int hash = scanner.nextInt();
                String message = scanner.nextLine();
                Map<Pair<String, Integer>, String> subtree = tree.get(key);
                subtree.put(new Pair<String, Integer>(exception, hash), message);
            }
        }
    }

    public void handle(Exception e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        int number = stackTrace[0].getLineNumber();
        String filename = stackTrace[0].getFileName();
        String exception = e.getClass().getSimpleName();
        int hash = Arrays.hashCode(stackTrace);
        String message = tree.get(new Pair<String, Integer>(filename, number))
                .get(new Pair<String, Integer>(exception, hash));
        System.err.println(String.format(
                "The exception %s was caught at line %d in file %s with given stack trace hash code %d: %s.", exception,
                number, filename, hash, message));
    }
}
