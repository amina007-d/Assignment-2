package benchmarks;

import algorithms.InsertionSort;
import metrics.PerformanceTracker;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 1)
@Warmup(iterations = 3, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class SortBenchmark {

    @Param({"100", "1000", "10000", "100000"})
    private int n;

    @Param({"random", "sorted", "reversed", "nearly"})
    private String dist;

    private int[] arr;
    private InsertionSort sorter;
    private PerformanceTracker tracker;

    @Setup(Level.Invocation)
    public void setup() {
        Random rand = new Random(42);
        arr = new int[n];

        switch (dist) {
            case "random":
                for (int i = 0; i < n; i++) arr[i] = rand.nextInt();
                break;
            case "sorted":
                for (int i = 0; i < n; i++) arr[i] = i;
                break;
            case "reversed":
                for (int i = 0; i < n; i++) arr[i] = n - i;
                break;
            case "nearly":
                for (int i = 0; i < n; i++) arr[i] = i;
                for (int i = 0; i < n / 20; i++) {
                    int a = rand.nextInt(n);
                    int b = rand.nextInt(n);
                    int tmp = arr[a];
                    arr[a] = arr[b];
                    arr[b] = tmp;
                }
                break;
        }

        tracker = new PerformanceTracker();
        sorter = new InsertionSort(tracker);
    }

    @Benchmark
    public void benchmarkInsertionSort() {
        int[] copy = Arrays.copyOf(arr, arr.length);
        sorter.sort(copy);
    }
}
