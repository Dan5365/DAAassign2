package algorithms;

import metrics.PerformanceTracker;

public class SelectionSortOptimized {
    private PerformanceTracker tracker;

    public SelectionSortOptimized(PerformanceTracker tracker) {
        this.tracker = tracker;
    }

    public void sort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            swapped = false;


            for (int j = i + 1; j < n; j++) {
                tracker.incrementComparisons();
                tracker.incrementArrayAccesses(2);
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                    swapped = true;
                }
            }


            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                tracker.incrementSwaps();
                tracker.incrementArrayAccesses(3);
            }


            if (!swapped) {
                break;
            }
        }
    }
}
