package cli;

import algorithms.InsertionSort;
import metrics.PerformanceTracker;

import java.io.IOException;
import java.util.Random;

public class BenchMarkRunner {

    public static void main(String[] args) throws IOException {
        String algo = "insertionsort";
        String out = "results_optimization.csv";
        int trials = 3;

        int[] sizes = {100, 1000, 10000, 100000};
        String[] distributions = {"random", "sorted", "reversed", "nearly"};

        Random rnd = new Random(42);

        for (String dist : distributions) {
            for (int n : sizes) {
                for (int t = 1; t <= trials; t++) {

                    int[] arr = generate(n, dist, rnd);
                    PerformanceTracker tracker = new PerformanceTracker();

                    long start = System.nanoTime();
                    new InsertionSort(tracker).sort(arr);
                    long time = System.nanoTime() - start;

                    tracker.writeRow(out, time, n, algo, dist, "trial=" + t);

                    System.out.printf(
                            "Dist=%-8s, n=%-6d, trial=%d, time=%-10d ns, %s%n",
                            dist, n, t, time, tracker
                    );
                }
            }
        }
    }

    private static int[] generate(int n, String dist, Random rnd) {
        int[] arr = new int[n];

        switch (dist.toLowerCase()) {
            case "sorted":
                for (int i = 0; i < n; i++) arr[i] = i;
                break;

            case "reversed":
                for (int i = 0; i < n; i++) arr[i] = n - i;
                break;

            case "nearly":
                for (int i = 0; i < n; i++) arr[i] = i;
                for (int i = 0; i < Math.max(1, n / 10); i++) {
                    int a = rnd.nextInt(n), b = rnd.nextInt(n);
                    int tmp = arr[a]; arr[a] = arr[b]; arr[b] = tmp;
                }
                break;

            default:
                for (int i = 0; i < n; i++) arr[i] = rnd.nextInt();
        }

        return arr;
    }
}
