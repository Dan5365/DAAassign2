package algorithms;

import metrics.PerformanceTracker;

public class SelectionSort {

    private final PerformanceTracker tracker;

    public SelectionSort(PerformanceTracker tracker) {
        this.tracker = tracker;
    }

    /**
     * Sorts the array using Selection Sort algorithm.
     * Optimized with early termination (if no swaps are needed, algorithm stops).
     *
     * @param arr array of integers to sort
     */

    public void sort(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        int n = arr.length;


        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            boolean swapped = false;

            for (int j = i + 1; j < n; j++) {
                tracker.incrementComparisons();
                tracker.incrementArrayAccesses(2);

                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }


            if (minIndex != i) {
                swap(arr, i, minIndex);
                swapped = true;
            }


            if (!swapped) break;

        }
    }

    private void swap(int[] arr, int i, int j) {
        tracker.incrementSwaps();
        tracker.incrementArrayAccesses(4);

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

