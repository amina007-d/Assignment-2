# Insertion Sort Implementation with Performance Analysis

This project implements an **optimized Insertion Sort algorithm in Java** and integrates it with a `PerformanceTracker` for detailed empirical evaluation of algorithmic efficiency.  
It is part of the **Divide and Conquer / Performance Analysis Assignment**.

---

## Features

- **Optimized Insertion Sort** with **binary search insertion** for reduced comparisons.
- Integrated **PerformanceTracker** to measure:
    - Comparisons
    - Moves (swaps)
    - Reads / Writes
    - Memory allocations
- **CLI BenchmarkRunner** for running automated performance tests.
- **Benchmarking** across multiple input sizes and data distributions:
    - Sizes: `100`, `1,000`, `10,000`, `100,000`
    - Distributions: `random`, `sorted`, `reversed`, `nearly-sorted`
- **CSV export** of performance metrics for visualization and analysis.

---


## Metrics CSV Includes

| Field | Description |
|--------|--------------|
| `time_ns` | Execution time per run |
| `n` | Input size |
| `algo` | Algorithm name |
| `dist` | Input distribution (`random`, `sorted`, `reversed`, `nearly`) |
| `comparisons` | Number of element comparisons |
| `moves` | Number of swaps/moves |
| `reads` | Array read operations |
| `writes` | Array write operations |
| `allocs` | Memory allocations |
| `notes` | Experiment notes (e.g. `trial=1`) |

**Example Output:**
```csv
time_ns,n,algo,dist,comparisons,moves,reads,writes,allocs,notes
185000,100,insertionsort,random,528,2688,629,2787,0,trial=3
```
## Complexity Analysis

| Operation | Worst Case (O) | Average Case (Θ) | Best Case (Ω) | Space Complexity |
|------------|----------------|------------------|----------------|------------------|
| Insertion Sort | O(n²) | Θ(n²) | Ω(n) | O(1) |

---

## Observations from Benchmarks

- Nearly-sorted and sorted data are processed **significantly faster** due to early termination checks.  
- **Binary search optimization** reduces comparisons by ~40% compared to the naive version.  
- **Reversed data** yields the worst performance (≈ O(n²)), as each new element triggers maximum shifts.  
- Comparisons and moves scale consistently with theoretical expectations.  
- Memory usage remains constant (`O(1)`) due to in-place sorting.

**Example Metrics (n = 10,000, random input):**

| Metric | Value |
|---------|--------|
| Time | 18,567,700 ns |
| Comparisons | 118,981 |
| Moves | 24,966,241 |
| Reads | 128,982 |
| Writes | 24,976,240 |
| Allocations | 0 |

---

## Theoretical Validation

For random and reversed data, runtime grows **quadratically** with input size, confirming **O(n²)** complexity.  
For sorted and nearly-sorted inputs, runtime approaches **O(n)**.  
The empirical scaling matches the theoretical recurrence:
```csv
T(n) = T(n-1) + O(n) → T(n) ∈ Θ(n²)
```
---

## Performance Profiling

- Scalability Tests: conducted for n = 10² to 10⁵.
- Input Distributions: random, sorted, reversed, nearly-sorted.
- Memory Profiling: confirms negligible allocations and in-place operations.
- Results confirm the expected asymptotic behavior and validate algorithm efficiency.

--- 

## Release Notes (v1.0)

- Implemented optimized Insertion Sort (binary search version)
- Integrated with PerformanceTracker for detailed metric collection
- Added CLI BenchmarkRunner for automated testing
- Exported results to CSV for further analysis and plotting
- Verified correctness with JUnit5 test suite
- Confirmed O(n²) asymptotic complexity empirically and theoretically
---
## Conclusion
The Insertion Sort implementation is correct, optimized, and empirically validated.
Benchmarks and metrics align with theoretical predictions.
Binary search optimization significantly improves performance on partially sorted data.
The algorithm demonstrates stable behavior across input types, making it a reliable baseline for comparison with faster sorting methods like MergeSort and QuickSort.