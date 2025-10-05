package metrics;

public class PerformanceTracker {

    private long comparisons = 0;
    private long swaps = 0;
    private long arrayAccesses = 0;

    public void incrementComparisons() {
        comparisons++;
    }

    public void incrementSwaps() {
        swaps++;
    }

    public void incrementArrayAccesses(int count) {
        arrayAccesses += count;
    }

    public void reset() {
        comparisons = swaps = arrayAccesses = 0;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getSwaps() {
        return swaps;
    }

    public long getArrayAccesses() {
        return arrayAccesses;
    }

    @Override
    public String toString() {
        return String.format("Comparisons: %d, Swaps: %d, ArrayAccesses: %d",
                comparisons, swaps, arrayAccesses);
    }
}

