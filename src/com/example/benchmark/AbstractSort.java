/**
 * Author: Joseph Julian
 * Date: 10 June 2023
 * Class: CMSC 451
 */

package com.example.benchmark;


/**
 * Abstract class representing the common behavior of sorting algorithms.
 * Subclasses are required to implement recursiveSort, iterativeSort and sort methods.
 */
public abstract class AbstractSort {

    // Counter to keep track of the number of operations performed during the sort
    protected int count;

    // Variable to keep track of the time taken to perform the sort
    protected long time;

    // Variable to store the start time of the sort
    protected long startTime;

    /**
     * Sorts the given array using the recursive version of the sorting algorithm.
     *
     * @param list The array to be sorted.
     * @throws UnsortedException If the sorting algorithm does not correctly sort the input data.
     */
    public abstract void recursiveSort(int[] list) throws UnsortedException;

    /**
     * Sorts the given array using the iterative version of the sorting algorithm.
     *
     * @param list The array to be sorted.
     * @throws UnsortedException If the sorting algorithm does not correctly sort the input data.
     */
    public abstract void iterativeSort(int[] list) throws UnsortedException;

    /**
     * Sorts the given array using a default version of the sorting algorithm.
     *
     * @param list The array to be sorted.
     * @throws UnsortedException If the sorting algorithm does not correctly sort the input data.
     */
    public abstract void sort(int[] list) throws UnsortedException;

    /**
     * Called at the beginning of the sort method to initialize the count and startTime variables.
     */
    protected void startSort() {
        count = 0;
        startTime = System.nanoTime();
    }

    /**
     * Called at the end of the sort method to calculate the total time taken for the sort.
     */
    protected void endSort() {
        time = System.nanoTime() - startTime;
    }

    /**
     * Increments the operation count by one.
     */
    protected void incrementCount() {
        count++;
    }

    /**
     * Returns the number of operations performed during the last sort.
     *
     * @return The operation count.
     */
    public int getCount() {
        return count;
    }

    /**
     * Returns the time taken to perform the last sort in nanoseconds.
     *
     * @return The time taken for the last sort in nanoseconds.
     */
    public long getTime() {
        return time;
    }
}
