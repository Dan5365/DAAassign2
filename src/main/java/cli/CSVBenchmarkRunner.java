package cli;

import algorithms.SelectionSort;
import algorithms.SelectionSortOptimized;
import metrics.PerformanceTracker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class CSVBenchmarkRunner {

    private static final int[] SIZES = {100, 1000, 10000, 100000};

    public static void main(String[] args) {
        String[] types = {"Random", "Sorted", "Reversed", "NearlySorted"};
        String fileName = "performance_results.csv";

        try (FileWriter csvWriter = new FileWriter(fileName)) {

            csvWriter.append("Algorithm,ArrayType,Size,TimeMs,Comparisons,Swaps,ArrayAccesses\n");

            for (int n : SIZES) {
                for (String type : types) {
                    int[] arr = generateArray(n, type);

                    // --- SelectionSort ---
                    runAndWrite(csvWriter, "SelectionSort", arr.clone());

                    // --- SelectionSortOptimized ---
                    runAndWrite(csvWriter, "SelectionSortOptimized", arr.clone());
                }
            }

            System.out.println("CSV benchmark completed: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runAndWrite(FileWriter csvWriter, String algorithm, int[] arr) throws IOException {
        PerformanceTracker tracker = new PerformanceTracker();
        long startTime = System.nanoTime();

        if (algorithm.equals("SelectionSort")) {
            new SelectionSort(tracker).sort(arr);
        } else if (algorithm.equals("SelectionSortOptimized")) {
            new SelectionSortOptimized(tracker).sort(arr);
        }

        long endTime = System.nanoTime();

        csvWriter.append(String.format("%s,%s,%d,%.4f,%d,%d,%d\n",
                algorithm,
                detectArrayType(arr),
                arr.length,
                (endTime - startTime) / 1_000_000.0,
                tracker.getComparisons(),
                tracker.getSwaps(),
                tracker.getArrayAccesses()
        ));
        csvWriter.flush();
    }

    private static int[] generateArray(int n, String type) {
        Random rand = new Random();
        int[] arr = new int[n];
        switch (type) {
            case "Random":
                for (int i = 0; i < n; i++) arr[i] = rand.nextInt(n);
                break;
            case "Sorted":
                for (int i = 0; i < n; i++) arr[i] = i;
                break;
            case "Reversed":
                for (int i = 0; i < n; i++) arr[i] = n - i;
                break;
            case "NearlySorted":
                for (int i = 0; i < n; i++) arr[i] = i;

                int swaps = n / 20;
                for (int i = 0; i < swaps; i++) {
                    int a = rand.nextInt(n);
                    int b = rand.nextInt(n);
                    int temp = arr[a];
                    arr[a] = arr[b];
                    arr[b] = temp;
                }
                break;
        }
        return arr;
    }

    private static String detectArrayType(int[] arr) {
        if (arr.length < 2) return "Sorted";
        boolean asc = true, desc = true;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) asc = false;
            if (arr[i] > arr[i - 1]) desc = false;
        }
        return asc ? "Sorted" : desc ? "Reversed" : "Random";
    }
}
