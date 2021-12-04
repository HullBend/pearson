/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pearson.histo;

import java.util.Arrays;

final class MathExt {

    /**
     * Round a double vale to given digits such as 10^n, where n is a positive or
     * negative integer.
     * 
     * @param x      a real number
     * @param digits the number of digits to round to
     * @return the rounded value
     */
    static double round(double x, int digits) {
        if (digits < 0) {
            return Math.round(x / Math.pow(10.0, -digits)) * Math.pow(10.0, -digits);
        } else {
            return Math.round(x * Math.pow(10.0, digits)) / Math.pow(10.0, digits);
        }
    }

    /**
     * Returns the minimum value of an array.
     * 
     * @param x the array
     * @return the minimum value
     */
    static int min(int[] x) {
        int min = x[0];
        for (int n : x) {
            if (n < min) {
                min = n;
            }
        }
        return min;
    }

    /**
     * Returns the minimum value of an array.
     * 
     * @param x the array
     * @return the minimum value
     */
    static long min(long[] x) {
        long min = x[0];
        for (long n : x) {
            if (n < min) {
                min = n;
            }
        }
        return min;
    }

    /**
     * Returns the minimum value of an array.
     * 
     * @param x the array
     * @return the minimum value
     */
    static double min(double[] x) {
        double min = Double.POSITIVE_INFINITY;
        for (double n : x) {
            if (n < min) {
                min = n;
            }
        }
        return min;
    }

    /**
     * Returns the maximum value of an array.
     * 
     * @param x the array
     * @return the maximum value
     */
    static int max(int[] x) {
        int max = x[0];
        for (int n : x) {
            if (n > max) {
                max = n;
            }
        }
        return max;
    }

    /**
     * Returns the maximum value of an array.
     * 
     * @param x the array
     * @return the maximum value
     */
    static long max(long[] x) {
        long max = x[0];
        for (long n : x) {
            if (n > max) {
                max = n;
            }
        }
        return max;
    }

    /**
     * Returns the maximum value of an array.
     * 
     * @param x the array
     * @return the maximum value
     */
    static double max(double[] x) {
        double max = Double.NEGATIVE_INFINITY;
        for (double n : x) {
            if (n > max) {
                max = n;
            }
        }
        return max;
    }

    /**
     * Returns the column minimum of a matrix.
     * 
     * @param matrix the matrix
     * @return the column minimums
     */
    static double[] colMin(double[][] matrix) {
        double[] x = new double[matrix[0].length];
        Arrays.fill(x, Double.POSITIVE_INFINITY);

        for (double[] row : matrix) {
            for (int j = 0; j < x.length; j++) {
                if (x[j] > row[j]) {
                    x[j] = row[j];
                }
            }
        }
        return x;
    }

    /**
     * Returns the column maximum of a matrix.
     * 
     * @param matrix the matrix
     * @return the column maximums
     */
    static double[] colMax(double[][] matrix) {
        double[] x = new double[matrix[0].length];
        Arrays.fill(x, Double.NEGATIVE_INFINITY);

        for (double[] row : matrix) {
            for (int j = 0; j < x.length; j++) {
                if (x[j] < row[j]) {
                    x[j] = row[j];
                }
            }
        }
        return x;
    }

    private MathExt() {
    }
}
