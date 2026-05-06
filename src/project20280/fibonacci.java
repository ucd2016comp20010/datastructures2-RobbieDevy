package project20280;

public class fibonacci {
    static long calls = 0;

    static long fib(int n) {
        calls++;
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        int n = 5;
        calls = 0;
        long ans = fib(n);
        System.out.println("F(" + n + ") = " + ans);
        System.out.println("Calls = " + calls);
    }
}
