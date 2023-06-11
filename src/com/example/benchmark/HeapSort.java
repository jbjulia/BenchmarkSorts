/**
 * Author: Joseph Julian
 * Date: 10 June 2023
 * Class: CMSC 451
 */

package com.example.benchmark;


/**
 * This class implements the Heap Sort algorithm and is part of the benchmarking suite.
 */
public class HeapSort extends AbstractSort {

    /**
     * Recursive sort is not supported for Heap Sort as it is inherently iterative.
     *
     * @param list The array to be sorted.
     */
    @Override
    public void recursiveSort(int[] list) {
        throw new UnsupportedOperationException("HeapSort does not support recursive sort.");
    }

    /**
     * Iteratively sorts an array using the Heap Sort algorithm and measures the time taken.
     *
     * @param list The array to be sorted.
     * @throws UnsortedException if the final array is not sorted.
     */
    @Override
    public void iterativeSort(int[] list) throws UnsortedException {
        startSort();

        int n = list.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(list, n, i);
        }

        // Extract elements from heap one by one
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            int temp = list[0];
            list[0] = list[i];
            list[i] = temp;

            // Call max heapify on the reduced heap
            heapify(list, i, 0);
        }

        endSort();

        if (isNotSorted(list)) {
            throw new UnsortedException();
        }
    }

    /**
     * Invokes the iterative sort as the primary sorting method for Heap Sort.
     *
     * @param list The array to be sorted.
     * @throws UnsortedException if the final array is not sorted.
     */
    @Override
    public void sort(int[] list) throws UnsortedException {
        iterativeSort(list);
    }

    /**
     * Converts an array into a max heap.
     *
     * @param arr The array to be heapified.
     * @param n   Size of the array.
     * @param i   Index to heapify from.
     */
    private void heapify(int[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        // If left child is larger than root
        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }

        // If right child is larger than the largest so far
        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }

        // If largest is not root, swap and heapify the affected subtree
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            incrementCount(); // Increment critical operation count

            // Recursively heapify the affected subtree
            heapify(arr, n, largest);
        }
    }

    /**
     * Checks if the array is not sorted.
     *
     * @param list The array to check.
     * @return true if the array is not sorted, false otherwise.
     */
    private boolean isNotSorted(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            if (list[i] > list[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
