package project20280.priorityqueue;

import project20280.interfaces.Entry;

import java.util.Random;

/**
 * HeapPriorityQueue-based sorting and timing utilities.
 */
public class PQSort {

    /**
     * Sorts the input array in nondecreasing order using a min-oriented priority queue.
     */
    public static void sort(Integer[] data) {
        HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<>();

        for (Integer x : data) {
            pq.insert(x, x);
        }

        for (int i = 0; i < data.length; i++) {
            Entry<Integer, Integer> e = pq.removeMin();
            data[i] = e.getKey();
        }
    }

    private static Integer[] randomArray(int n, Random rnd) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = rnd.nextInt();
        }
        return a;
    }

    private static boolean isSorted(Integer[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Measures PQSort running time from n=1000 up to n=1,000,000 (x10 steps).
     */
    public static void main(String[] args) {
        int[] sizes = new int[]{1000, 10_000, 100_000, 1_000_000};
        Random rnd = new Random(20280);

        System.out.println("n,time_ms");
        for (int n : sizes) {
            Integer[] data = randomArray(n, rnd);

            long start = System.nanoTime();
            sort(data);
            long end = System.nanoTime();

            if (!isSorted(data)) {
                throw new IllegalStateException("PQSort failed to sort for n=" + n);
            }

            double ms = (end - start) / 1_000_000.0;
            System.out.printf("%d,%.3f%n", n, ms);
        }
    }
}
