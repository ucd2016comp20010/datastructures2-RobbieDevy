package project20280;

import java.util.Arrays;

public class fibmemo {
    static long calls = 0;

    static long fib(int n, long[] memo) {
        calls++;
        if (n <= 1) return n;
        if (memo[n] != -1) return memo[n];
        memo[n] = fib(n - 1, memo) + fib(n - 2, memo);
        return memo[n];
    }

    public static void main(String[] args) {
        int n = 92; // max that fits in signed 64-bit long
        long[] memo = new long[n + 1];
        Arrays.fill(memo, -1);

        calls = 0;
        long ans = fib(n, memo);
        System.out.println("F(" + n + ") = " + ans);
        System.out.println("Calls = " + calls);
    }
}
