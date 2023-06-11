/**
 * Author: Joseph Julian
 * Date: 10 June 2023
 * Class: CMSC 451
 */

package com.example.benchmark;


import javax.swing.*;


public class Main {

    public static void main(String[] args) throws UnsortedException {
        // Warming up the JVM by running the benchmark 50 times
        for (int i = 0; i < 50; i++) {
            long startTime = System.nanoTime();
            BenchmarkSorts jvmWarmup = new BenchmarkSorts();
            jvmWarmup.runSorts();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println("JVM Warmup Iteration " + (i + 1) + ":\tTime (nanoseconds): " + duration);
        }

        // Running the actual benchmark
        BenchmarkSorts benchmark = new BenchmarkSorts();
        benchmark.runSorts();

        // Inform the user that the benchmark data has been written to files
        System.out.println("Benchmark data has been written to the tests directory");

        // Call the ReportGenerator on the EDT using SwingUtilities.invokeLater()
        SwingUtilities.invokeLater(ReportGenerator::generateReport);
    }
}
