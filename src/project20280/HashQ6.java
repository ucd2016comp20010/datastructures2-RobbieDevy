package project20280;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class HashQ6 {
    public int hashCode(String s) {
        int hash = 0;
        int skip = Math.max(1, s.length() / 8);
        for (int i = 0; i < s.length(); i += skip) {
            hash = (hash * 37) + s.charAt(i);
        }
        return hash;
    }

    public int hash_poly(String s, int a) {
        int h = 0;
        int n = s.length();
        for(int i=0; i<n; i++) {
            char s_i = (char) s.charAt(i);
            int v = s_i * ((int) Math.pow(a,n - i - 1));
            h += v;
            }
        return h;
        }

    public int hash_cyclic(String s, int shift) {
        int h = 0;
        for(int i = 0; i < s.length(); ++i) {
            h = (h << shift) | (h >>> (32 - shift));
            h += (int) s.charAt(i);
            }
        return h;
        }

    private static int collisionsForMode(File f, String mode, int param) throws FileNotFoundException {
        HashQ6 hq6 = new HashQ6();
        HashSet<Integer> seen = new HashSet<>();
        int collisions = 0;

        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNext()) {
                String word = scanner.next();
                int h;
                if ("poly".equals(mode)) {
                    h = hq6.hash_poly(word, param);
                } else if ("cyclic".equals(mode)) {
                    h = hq6.hash_cyclic(word, param);
                } else {
                    h = hq6.hashCode(word);
                }
                if (!seen.add(h)) {
                    collisions++;
                }
            }
        }
        return collisions;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("words.txt");
        if (!f.exists()) f = new File("src/project20280/words.txt");
        if (!f.exists()) {
            throw new FileNotFoundException("Could not find words.txt");
        }

        int poly41 = collisionsForMode(f, "poly", 41);
        int poly17 = collisionsForMode(f, "poly", 17);
        int cyclic7 = collisionsForMode(f, "cyclic", 7);
        int oldJava = collisionsForMode(f, "old", 0);

        int bestShift = -1;
        int bestCollisions = Integer.MAX_VALUE;
        for (int shift = 0; shift <= 31; shift++) {
            int c = collisionsForMode(f, "cyclic", shift);
            if (c < bestCollisions) {
                bestCollisions = c;
                bestShift = shift;
            }
        }

        System.out.println("(a) polynomial a=41 collisions: " + poly41);
        System.out.println("(b) polynomial a=17 collisions: " + poly17);
        System.out.println("(c) cyclic shift=7 collisions: " + cyclic7);
        System.out.println("(d) best cyclic shift in [0..31]: " + bestShift + " (collisions: " + bestCollisions + ")");
        System.out.println("(e) old Java hashCode collisions: " + oldJava);
    }
}
