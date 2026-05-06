package project20280;
import java.math.BigInteger;
import java.util.Arrays;

public class fibtiming {
    static long calls = 0;

    static long fibonacci(int n) {
        calls++;
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    static long memoCalls = 0;

    static long fibmemo(int n, long[] memo) {
        memoCalls++;
        if (n <= 1) return n;
        if (memo[n] != -1) return memo[n];
        memo[n] = fibmemo(n - 1, memo) + fibmemo(n - 2, memo);
        return memo[n];
    }

    // Optional: BigInteger memo (lets you go beyond n=92; time won't be the limit)
    static long memoBigCalls = 0;

    public static void main(String[] args) {
        final double LIMIT_SECONDS = 60.0;

        int n = 0;
        long lastValue = 0;
        long lastCalls = 0;

        while (true) {
            calls = 0;
            long start = System.nanoTime();
            long val = fibonacci(n);
            double elapsed = (System.nanoTime() - start) / 1e9;

            if (elapsed > LIMIT_SECONDS) break;

            lastValue = val;
            lastCalls = calls;
            n++;
        }

        int bestRecursiveN = n - 1;
        System.out.println("=== Naive recursion ===");
        System.out.println("Largest n under 60s: " + bestRecursiveN);
        System.out.println("F(" + bestRecursiveN + ") = " + lastValue);
        System.out.println("Recursive calls: " + lastCalls);
        System.out.println();

        int bestMemoN = 0;
        long bestMemoVal = 0;
        long bestMemoCalls = 0;

        for (int m = 0; m <= 92; m++) {
            long[] memo = new long[m + 1];
            Arrays.fill(memo, -1);

            memoCalls = 0;
            long start = System.nanoTime();
            long val = fibmemo(m, memo);
            double elapsed = (System.nanoTime() - start) / 1e9;

            if (elapsed > LIMIT_SECONDS) break;

            bestMemoN = m;
            bestMemoVal = val;
            bestMemoCalls = memoCalls;
        }

        System.out.println("=== Memoized recursion (long) ===");
        System.out.println("Largest n under 60s (and within long): " + bestMemoN);
        System.out.println("F(" + bestMemoN + ") = " + bestMemoVal);
        System.out.println("Recursive calls: " + bestMemoCalls);
        System.out.println("(Note: long overflows after n=92)");
        System.out.println();
    }
}
