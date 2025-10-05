package algorithms;
import metrics.PerformanceTracker;

public class InsertionSort {
    private final PerformanceTracker tracker;

    public InsertionSort(PerformanceTracker tracker) {
        this.tracker = tracker;
    }

    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            tracker.read();
            return;
        }

        // Early exit if already sorted
        boolean sorted = true;
        for (int i = 1; i < arr.length; i++) {
            tracker.cmp();
            tracker.read(2);
            if (arr[i] < arr[i - 1]) {
                sorted = false;
                break;
            }
        }
        if (sorted) return;

        // Main insertion sort with binary search
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            tracker.read();
            int left = 0;
            int right = i - 1;

            while (left <= right) {
                int mid = (left + right) >>> 1;
                tracker.read();
                tracker.cmp();
                if (key < arr[mid]) right = mid - 1;
                else left = mid + 1;
            }

            for (int j = i - 1; j >= left; j--) {
                tracker.move();
                tracker.write();
                arr[j + 1] = arr[j];
            }

            tracker.write();
            arr[left] = key;
        }
    }
}