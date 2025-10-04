package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class InsertionSortTest {

    private void runAndCheck(int[] arr) {
        PerformanceTracker tracker = new PerformanceTracker();
        InsertionSort sorter = new InsertionSort(tracker);

        int[] copy = arr.clone();
        Arrays.sort(copy);
        sorter.sort(arr);

        assertArrayEquals(copy, arr, "Arrays should match after sorting");
    }

    @Test
    void testEmptyArray() {
        runAndCheck(new int[]{});
    }

    @Test
    void testSingleElement() {
        runAndCheck(new int[]{42});
    }

    @Test
    void testDuplicates() {
        runAndCheck(new int[]{5, 1, 1, 3, 3, 2});
    }

    @Test
    void testAlreadySorted() {
        runAndCheck(new int[]{1, 2, 3, 4, 5});
    }

    @Test
    void testReverseSorted() {
        runAndCheck(new int[]{5, 4, 3, 2, 1});
    }

    @Test
    void testRandomSmall() {
        Random rnd = new Random(42);
        int[] arr = rnd.ints(20, -50, 50).toArray();
        runAndCheck(arr);
    }

    @Test
    void testRandomLarge() {
        Random rnd = new Random(123);
        int[] arr = rnd.ints(1000, -10000, 10000).toArray();
        runAndCheck(arr);
    }

    @Test
    void testNearlySorted() {
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        arr[5] = 100;
        arr[6] = 4;
        arr[15] = -10;

        runAndCheck(arr);
    }
}
