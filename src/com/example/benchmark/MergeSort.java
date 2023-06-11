/**
 * Author: Joseph Julian
 * Date: 10 June 2023
 * Class: CMSC 451
 */

package com.example.benchmark;


/**
 * This class implements the Merge Sort algorithm and is part of the benchmarking suite.
 */
public class MergeSort extends AbstractSort {

    /**
     * Recursively sorts an array using the Merge Sort algorithm and measures the time taken.
     *
     * @param list The array to be sorted.
     * @throws UnsortedException if the final array is not sorted.
     */
    @Override
    public void recursiveSort(int[] list) throws UnsortedException {
        startSort();

        mergeSort(list, 0, list.length - 1);

        endSort();

        if (isNotSorted(list)) {
            throw new UnsortedException();
        }
    }

    /**
     * Helper method that recursively divides the array and then merges them in sorted order.
     *
     * @param list  The array to be sorted.
     * @param left  The left index of the sub-array.
     * @param right The right index of the sub-array.
     */
    private void mergeSort(int[] list, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(list, left, middle);
            mergeSort(list, middle + 1, right);
            merge(list, left, middle, right);
        }
    }

    /**
     * Merges two sub-arrays of the original array.
     *
     * @param list   The original array.
     * @param left   The left index of the sub-array.
     * @param middle The middle index to divide the two sub-arrays.
     * @param right  The right index of the sub-array.
     */
    private void merge(int[] list, int left, int middle, int right) {
        int[] leftArray = new int[middle - left + 1];
        int[] rightArray = new int[right - middle];

        // Copy data to temporary arrays
        System.arraycopy(list, left, leftArray, 0, leftArray.length);
        System.arraycopy(list, middle + 1, rightArray, 0, rightArray.length);

        // Indices for left and right sub-arrays
        int i = 0, j = 0;

        // Index for merged sub-array
        int k = left;

        // Merge the temporary arrays back into the original array
        while (i < leftArray.length && j < rightArray.length) {
            incrementCount(); // Increment critical operation count
            if (leftArray[i] <= rightArray[j]) {
                list[k] = leftArray[i];
                i++;
            } else {
                list[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements, if any
        while (i < leftArray.length) {
            list[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < rightArray.length) {
            list[k] = rightArray[j];
            j++;
            k++;
        }
    }

    /**
     * Merge Sort does not have an iterative variant, so this method is unsupported.
     *
     * @param list The array to be sorted.
     * @throws UnsupportedOperationException because Merge Sort does not support iterative sort.
     */
    @Override
    public void iterativeSort(int[] list) {
        throw new UnsupportedOperationException("Merge Sort does not support iterative sort.");
    }

    /**
     * Invokes the recursive sort as the primary sorting method for Merge Sort.
     *
     * @param list The array to be sorted.
     * @throws UnsortedException if the final array is not sorted.
     */
    @Override
    public void sort(int[] list) throws UnsortedException {
        recursiveSort(list);
    }

    /**
     * Checks if the array is not sorted in ascending order.
     *
     * @param list The array to check.
     * @return true if the array is not sorted in ascending order, false otherwise.
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
