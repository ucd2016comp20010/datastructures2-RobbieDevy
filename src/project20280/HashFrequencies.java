package project20280;

import project20280.hashtable.ChainHashMap;
import project20280.interfaces.Entry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class HashFrequencies {
    private static final String FALLBACK_TEXT =
            "This is sample text. This sample text is only for testing word frequency counting. " +
            "Words repeat, and repeated words should get higher counts. " +
            "This file fallback helps when sample_text.txt is missing.";

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("sample.txt");
        if (!f.exists()) f = new File("sample_text.txt");
        if (!f.exists()) f = new File("src/project20280/sample_text.txt");
        ChainHashMap<String, Integer> counter = new ChainHashMap<>();

        Scanner scanner;
        if (f.exists()) {
            scanner = new Scanner(f);
            System.out.println("Reading words from " + f.getPath());
        } else {
            scanner = new Scanner(FALLBACK_TEXT);
            System.out.println("No sample file found. Using built-in sample text.");
        }

        while (scanner.hasNext()) {
            String word = normalizeWord(scanner.next());
            if (word.isEmpty()) {
                continue;
            }
            Integer count = counter.get(word);
            if (count == null) {
                counter.put(word, 1);
            } else {
                counter.put(word, count + 1);
            }
        }
        scanner.close();

        ArrayList<Entry<String, Integer>> entries = new ArrayList<>();
        for (Entry<String, Integer> e : counter.entrySet()) {
            entries.add(e);
        }

        entries.sort(new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> a, Entry<String, Integer> b) {
                int byCount = Integer.compare(b.getValue(), a.getValue()); // descending count
                if (byCount != 0) {
                    return byCount;
                }
                return a.getKey().compareTo(b.getKey()); // tie-break alphabetically
            }
        });

        int limit = Math.min(10, entries.size());
        System.out.println("\nTop " + limit + " words:");
        for (int i = 0; i < limit; i++) {
            Entry<String, Integer> e = entries.get(i);
            System.out.println((i + 1) + ". " + e.getKey() + " -> " + e.getValue());
        }
    }

    private static String normalizeWord(String raw) {
        return raw.toLowerCase().replaceAll("[^a-z0-9']", "");
    }
}
