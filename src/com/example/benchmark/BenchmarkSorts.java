/**
 * Author: Joseph Julian
 * Date: 10 June 2023
 * Class: CMSC 451
 */

package com.example.benchmark;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * This class performs benchmark tests on different sorting algorithms.
 */
class BenchmarkSorts {

    private static final int[] DATA_SIZES = {10, 20, 40, 80, 160, 320, 640, 1280, 2560, 5120, 10240, 20480};
    private static final int NUM_OF_DATA_SETS = 40;
    private final MergeSort mergeSort = new MergeSort();
    private final HeapSort heapSort = new HeapSort();
    private final Random random = new Random();

    // Data arrays for merge sort and heap sort
    private final double[] mergeSortCountData = new double[NUM_OF_DATA_SETS];
    private final double[] mergeSortTimeData = new double[NUM_OF_DATA_SETS];
    private final double[] heapSortCountData = new double[NUM_OF_DATA_SETS];
    private final double[] heapSortTimeData = new double[NUM_OF_DATA_SETS];

    /**
     * Runs the sorting algorithms on randomly generated data and records the performance.
     *
     * @throws UnsortedException If the sorting algorithms do not correctly sort the input data.
     */
    void runSorts() throws UnsortedException {
        try (BufferedWriter mergeSortBw = new BufferedWriter(new FileWriter("tests/merge-sort-benchmark.txt"));
             BufferedWriter heapSortBw = new BufferedWriter(new FileWriter("tests/heap-sort-benchmark.txt"))) {

            for (int dataSize : DATA_SIZES) {
                StringBuilder mergeSortLine = new StringBuilder();
                StringBuilder heapSortLine = new StringBuilder();
                mergeSortLine.append(dataSize);
                heapSortLine.append(dataSize);

                for (int setNum = 0; setNum < NUM_OF_DATA_SETS; setNum++) {
                    // Populate the arrays with random data
                    int[] data = new int[dataSize];
                    for (int j = 0; j < dataSize; j++) {
                        int r = random.nextInt(dataSize + 1);
                        data[j] = r;
                    }

                    // Run Merge Sort
                    mergeSort.sort(data.clone());
                    mergeSortCountData[setNum] = mergeSort.getCount();
                    mergeSortTimeData[setNum] = mergeSort.getTime();

                    // Run Heap Sort
                    heapSort.sort(data.clone());
                    heapSortCountData[setNum] = heapSort.getCount();
                    heapSortTimeData[setNum] = heapSort.getTime();

                    // Append this run's data to the lines
                    mergeSortLine.append(" ").append(mergeSortCountData[setNum]).append(" ").append(mergeSortTimeData[setNum]);
                    heapSortLine.append(" ").append(heapSortCountData[setNum]).append(" ").append(heapSortTimeData[setNum]);
                }

                // Calculate averages and coefficients of variance using StatisticsUtils
                double avgMergeCount = StatisticsUtils.getMean(mergeSortCountData);
                double coefVarMergeCount = StatisticsUtils.getCoefficientOfVariance(mergeSortCountData, avgMergeCount);
                double avgMergeTime = StatisticsUtils.getMean(mergeSortTimeData);
                double coefVarMergeTime = StatisticsUtils.getCoefficientOfVariance(mergeSortTimeData, avgMergeTime);
                double avgHeapCount = StatisticsUtils.getMean(heapSortCountData);
                double coefVarHeapCount = StatisticsUtils.getCoefficientOfVariance(heapSortCountData, avgHeapCount);
                double avgHeapTime = StatisticsUtils.getMean(heapSortTimeData);
                double coefVarHeapTime = StatisticsUtils.getCoefficientOfVariance(heapSortTimeData, avgHeapTime);

                // Append calculated values to the lines
                mergeSortLine.append(" ").append(avgMergeCount).append(" ").append(coefVarMergeCount).append(" ").append(avgMergeTime).append(" ").append(coefVarMergeTime);
                heapSortLine.append(" ").append(avgHeapCount).append(" ").append(coefVarHeapCount).append(" ").append(avgHeapTime).append(" ").append(coefVarHeapTime);

                // Write this data size's lines to the files
                mergeSortBw.write(mergeSortLine.toString());
                mergeSortBw.newLine();
                heapSortBw.write(heapSortLine.toString());
                heapSortBw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
