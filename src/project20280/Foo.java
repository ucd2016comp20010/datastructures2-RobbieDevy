package project20280;

public class Foo {
    public static void foo(int x) {
        if (x / 2 == 0) {
            System.out.print(x);
            return;
        }
        foo(x / 2);
        System.out.print(x % 2);
    }

    public static void main(String[] args) {
        int x = 2468;
        foo(x);
        System.out.println();
    }
}
