package cli;

import algorithms.SelectionSort;
import metrics.PerformanceTracker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class BenchmarkRunner {

    private static final int[] SIZES = {100, 1000, 10000, 100000};

    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("benchmark_results.csv")) {
            writer.write("Type,Size,Time(ms),Comparisons,Swaps,ArrayAccesses\n");

            for (int size : SIZES) {
                runBenchmark(writer, "Random", generateRandomArray(size));
                runBenchmark(writer, "Sorted", generateSortedArray(size));
                runBenchmark(writer, "Reversed", generateReversedArray(size));
                runBenchmark(writer, "NearlySorted", generateNearlySortedArray(size));
            }

            System.out.println("Benchmark complete! Results saved to benchmark_results.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runBenchmark(FileWriter writer, String type, int[] arr) throws IOException {
        int[] copy = Arrays.copyOf(arr, arr.length);
        PerformanceTracker tracker = new PerformanceTracker();
        SelectionSort sorter = new SelectionSort(tracker);

        long start = System.nanoTime();
        sorter.sort(copy);
        long end = System.nanoTime();

        double timeMs = (end - start) / 1_000_000.0;

        writer.write(String.format("%s,%d,%.4f,%d,%d,%d\n",
                type, arr.length, timeMs,
                tracker.getComparisons(), tracker.getSwaps(), tracker.getArrayAccesses()));

        System.out.printf("%s | n=%d | %.4f ms | C=%d | S=%d | A=%d%n",
                type, arr.length, timeMs,
                tracker.getComparisons(), tracker.getSwaps(), tracker.getArrayAccesses());
    }

    // ---------- Array generators ----------
    private static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = rand.nextInt(10000);
        return arr;
    }

    private static int[] generateSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = i;
        return arr;
    }

    private static int[] generateReversedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = size - i;
        return arr;
    }

    private static int[] generateNearlySortedArray(int size) {
        int[] arr = generateSortedArray(size);
        Random rand = new Random();

        for (int i = 0; i < size / 10; i++) {
            int idx1 = rand.nextInt(size);
            int idx2 = rand.nextInt(size);
            int tmp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = tmp;
        }
        return arr;
    }
}
