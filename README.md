# DAA Assignment 2: Selection Sort

## Project Overview
This project implements Selection Sort algorithm with performance tracking. It includes:
- Baseline Selection Sort
- Optimized Selection Sort
- Performance measurements
- Unit tests

## Usage Instructions

### Running Benchmarks
1. Compile the project:
```bash
mvn compile
java -cp target/classes cli.BenchmarkRunner [array_size]
mvn test
```
Performance Analysis

Tracks comparisons, swaps, and array accesses.

Supports different array types: Random, Sorted, Reversed, NearlySorted.

Outputs empirical measurements to console or CSV.

Complexity Analysis

Time Complexity:

Worst Case: O(n²)

Average Case: Θ(n²)

Best Case: Ω(n²) comparisons, Ω(0) swaps

Space Complexity: O(1) (in-place)

Stability: Not stable

Optimizations: Minimizing unnecessary swaps, caching values for fewer array accesses


После этого можно закоммитить:  
```bash
git add README.md
git commit -m "docs(readme): usage instructions and complexity analysis"
git push origin main
