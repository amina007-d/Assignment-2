package algorithms;

import metrics.PerformanceTracker;

public class InsertionSort {
    private final PerformanceTracker tracker;

    public InsertionSort(PerformanceTracker tracker) {
        this.tracker = tracker;
    }

    public void sort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            tracker.read();
            tracker.read();
            tracker.cmp();
            if (arr[i - 1] <= key) {
                continue;
            }

            int pos = binarySearch(arr, key, 0, i - 1);

            for (int j = i - 1; j >= pos; j--) {
                tracker.read();
                tracker.write();
                tracker.move();
                arr[j + 1] = arr[j];
            }

            tracker.write();
            arr[pos] = key;
        }
    }

    private int binarySearch(int[] arr, int key, int low, int high) {
        while (low <= high) {
            int mid = (low + high) >>> 1;

            tracker.read();
            tracker.cmp();
            if (arr[mid] > key) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
