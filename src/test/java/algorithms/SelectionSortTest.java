package algorithms;

import metrics.PerformanceTracker;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;


public class SelectionSortTest {

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        SelectionSort sorter = new SelectionSort(new PerformanceTracker());
        sorter.sort(arr);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    public void testSingleElement() {
        int[] arr = {5};
        SelectionSort sorter = new SelectionSort(new PerformanceTracker());
        sorter.sort(arr);
        assertArrayEquals(new int[]{5}, arr);
    }

    @Test
    public void testDuplicates() {
        int[] arr = {3, 1, 3, 2, 1};
        SelectionSort sorter = new SelectionSort(new PerformanceTracker());
        sorter.sort(arr);
        assertArrayEquals(new int[]{1, 1, 2, 3, 3}, arr);
    }

    @Test
    public void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        SelectionSort sorter = new SelectionSort(new PerformanceTracker());
        sorter.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    public void testReverseSorted() {
        int[] arr = {5, 4, 3, 2, 1};
        SelectionSort sorter = new SelectionSort(new PerformanceTracker());
        sorter.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }
}
