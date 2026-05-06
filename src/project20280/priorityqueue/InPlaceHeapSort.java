package project20280.priorityqueue;

import java.util.Random;

public class  InPlaceHeapSort {

    private static int left(int j) {
        return 2 * j + 1;
    }

    private static int right(int j) {
        return 2 * j + 2;
    }

    private static void swap(Integer[] a, int i, int j) {
        Integer t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void downheap(Integer[] a, int j, int heapSize) {
        while (left(j) < heapSize) {
            int leftIndex = left(j);
            int largeChild = leftIndex;
            int rightIndex = right(j);

            if (rightIndex < heapSize && a[rightIndex] > a[leftIndex]) {
                largeChild = rightIndex;
            }

            if (a[largeChild] <= a[j]) {
                break;
            }

            swap(a, j, largeChild);
            j = largeChild;
        }
    }

    private static void heapify(Integer[] a) {
        for (int j = (a.length - 2) / 2; j >= 0; j--) {
            downheap(a, j, a.length);
        }
    }

    public static void sort(Integer[] a) {
        heapify(a);
        for (int end = a.length - 1; end > 0; end--) {
            swap(a, 0, end);
            downheap(a, 0, end);
        }
    }

    private static boolean isSorted(Integer[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) {
                return false;
            }
        }
        return true;
    }

    private static Integer[] randomArray(int n, Random rnd) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = rnd.nextInt();
        }
        return a;
    }

    public static void main(String[] args) {
        int[] sizes = new int[]{1000, 10_000, 100_000, 1_000_000};
        Random rnd = new Random(20280);

        for (int n : sizes) {
            Integer[] base = randomArray(n, rnd);

            Integer[] forPQSort = base.clone();
            long t1 = System.nanoTime();
            PQSort.sort(forPQSort);
            long t2 = System.nanoTime();

            Integer[] forHeapSort = base.clone();
            long t3 = System.nanoTime();
            sort(forHeapSort);
            long t4 = System.nanoTime();

            if (!isSorted(forPQSort) || !isSorted(forHeapSort)) {
                throw new IllegalStateException("Sorting failed at n=" + n);
            }

            double pqMs = (t2 - t1) / 1_000_000.0;
            double hsMs = (t4 - t3) / 1_000_000.0;
            System.out.printf("n = %d, pqSort = %.3f, heapSort = %.3f%n", n, pqMs, hsMs);
        }
    }
}
