# BenchmarkSorts

BenchmarkSorts is a Java program that performs benchmarking on two selected sorting algorithms: Merge Sort and Heap
Sort. It measures the critical operation count and the runtime of each algorithm for various data set sizes. The program
generates benchmark data files for each sorting algorithm and provides a graphical report of the results.

## Table of Contents

- [Usage](#usage)
- [Benchmarking](#benchmarking)
- [Report Generation](#report-generation)
- [Package Structure](#package-structure)
- [Compilation](#compilation)
- [Authors](#authors)
- [Sources](#sources)

## Usage

To use the BenchmarkSorts program, follow the steps below:

1. Clone the repository or download the source code.

2. Make sure you have Java Development Kit (JDK) installed on your system.

3. Compile the source code using the provided Makefile or manually compile the Java files.

4. Run the program by executing the Main class:

```
java com.example.benchmark.Main
```

5. Follow the on-screen instructions to perform the benchmarking and generate the report.

## Benchmarking

The BenchmarkSorts program benchmarks the Merge Sort and Heap Sort algorithms using randomly generated data sets. It
measures the critical operation count and the runtime for each data set size. The program generates benchmark data
files (`merge-sort-benchmark.txt` and `heap-sort-benchmark.txt`) that contain the critical operation counts and runtimes
for each run.

## Report Generation

The BenchmarkSorts program also includes a report generation feature. After the benchmarking is complete, you can
generate a report that summarizes the benchmark data in a tabular format. The report is displayed using a graphical user
interface (GUI) and shows the average critical counts and runtimes for each data set size. The report helps analyze the
performance of the sorting algorithms and their data sensitivity.

To run it standalone, execute the following:

```
java com.example.benchmark.ReportGenerator
```

## Package Structure

The BenchmarkSorts program has the following package structure:

```
com.example.benchmark
├── BenchmarkSorts.java
├── MergeSort.java
├── HeapSort.java
├── AbstractSort.java
├── StatisticsUtils.java
├── ReportGenerator.java
└── Main.java
```

The `com.example.benchmark` package contains all the classes related to the benchmarking and report generation.

## Compilation

To compile the BenchmarkSorts program, follow these steps:

1. Open a terminal or command prompt.

2. Navigate to the parent directory of the `benchmark` directory where the source files are located.

3. Compile the Java files using the appropriate package structure:

```
javac benchmark/*.java
```

This command compiles all the Java files in the `benchmark` package and creates the corresponding class files.

4. After successful compilation, you can run the program as mentioned in the [Usage](#usage) section.

## Authors

- Joseph Julian

## Sources

The sorting algorithms used in the BenchmarkSorts program are adapted from the following sources:

- Merge Sort: [GeeksforGeeks - Merge Sort](https://www.geeksforgeeks.org/merge-sort/)
- Heap Sort: [GeeksforGeeks - Heap Sort](https://www.geeksforgeeks.org/heap-sort/)