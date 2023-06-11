/**
 * Author: Joseph Julian
 * Date: 10 June 2023
 * Class: CMSC 451
 */

package com.example.benchmark;


public class StatisticsUtils {

    /**
     * Calculates the mean of the given data.
     *
     * @param data Array of data.
     * @return The mean of the data.
     */
    public static double getMean(double[] data) {
        double sum = 0;
        for (double value : data) {
            sum += value;
        }
        return sum / data.length;
    }

    /**
     * Calculates the coefficient of variance of the given data.
     *
     * @param data Array of data.
     * @param mean The mean of the data.
     * @return The coefficient of variance of the data.
     */
    public static double getCoefficientOfVariance(double[] data, double mean) {
        double sum = 0;
        for (double value : data) {
            sum += Math.pow(value - mean, 2);
        }
        double standardDeviation = Math.sqrt(sum / data.length);
        return standardDeviation / mean;
    }
}
