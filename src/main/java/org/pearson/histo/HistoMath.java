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

/**
 * Histogram utilities.
 */
final class HistoMath {

    /**
     * Generate the histogram of k bins.
     * 
     * @param data     the data points
     * @param binCount the number of bins
     * @return a 3-by-k bins array of which first row is the lower bound of bins,
     *         second row is the upper bound of bins, and the third row is the
     *         frequency count
     */
    static double[][] of(int[] data, int binCount) {
        binCount = checkBinCount(binCount);
        int min = MathExt.min(data);
        int max = MathExt.max(data);
        int span = max - min + 1;

        int width = 1;
        int residual = 1;
        while (residual > 0) {
            width = span / binCount;
            if (width == 0) {
                width = 1;
            }

            residual = span - binCount * width;
            if (residual > 0) {
                binCount += 1;
            }
        }

        double center = width / 2.0;

        double[] boundaries = new double[binCount + 1];
        boundaries[0] = min - center;
        for (int i = 1; i <= binCount; i++) {
            boundaries[i] = boundaries[i - 1] + width;
        }

        return of(data, boundaries);
    }

    /**
     * Generate the histogram of k bins.
     * 
     * @param data     the data points
     * @param binCount the number of bins
     * @return a 3-by-k bins array of which first row is the lower bound of bins,
     *         second row is the upper bound of bins, and the third row is the
     *         frequency count
     */
    static double[][] of(long[] data, int binCount) {
        binCount = checkBinCount(binCount);
        long min = MathExt.min(data);
        long max = MathExt.max(data);
        long span = max - min + 1L;

        long width = 1L;
        long residual = 1L;
        while (residual > 0L) {
            width = span / binCount;
            if (width == 0L) {
                width = 1L;
            }

            residual = span - binCount * width;
            if (residual > 0L) {
                binCount += 1;
            }
        }

        double center = width / 2.0;

        double[] boundaries = new double[binCount + 1];
        boundaries[0] = min - center;
        for (int i = 1; i <= binCount; i++) {
            boundaries[i] = boundaries[i - 1] + width;
        }

        return of(data, boundaries);
    }

    /**
     * Generate the histogram of n bins.
     * 
     * @param data       the data points
     * @param boundaries an array of size {@code binCount + 1} giving the boundaries
     *                   between histogram cells. Must be in ascending order
     * @return a 3-by-n bins array of which first row is the lower bound of bins,
     *         second row is the upper bound of bins, and the third row is the
     *         frequency count
     */
    static double[][] of(int[] data, double[] boundaries) {
        int binCount = checkBinCount(boundaries.length - 1);
        double[][] freq = new double[3][binCount];
        for (int i = 0; i < binCount; i++) {
            freq[0][i] = boundaries[i];
            freq[1][i] = boundaries[i + 1];
            freq[2][i] = 0;
        }

        for (int d : data) {
            int j = Arrays.binarySearch(boundaries, d);

            if (j >= binCount) {
                j = binCount - 1;
            }

            if (j < -1 && j >= -boundaries.length) {
                j = -j - 2;
            }

            if (j >= 0) {
                freq[2][j]++;
            }
        }

        return freq;
    }

    /**
     * Generate the histogram of n bins.
     * 
     * @param data       the data points
     * @param boundaries an array of size {@code binCount + 1} giving the boundaries
     *                   between histogram cells. Must be in ascending order
     * @return a 3-by-n bins array of which first row is the lower bound of bins,
     *         second row is the upper bound of bins, and the third row is the
     *         frequency count
     */
    static double[][] of(long[] data, double[] boundaries) {
        int binCount = checkBinCount(boundaries.length - 1);
        double[][] freq = new double[3][binCount];
        for (int i = 0; i < binCount; i++) {
            freq[0][i] = boundaries[i];
            freq[1][i] = boundaries[i + 1];
            freq[2][i] = 0;
        }

        for (long d : data) {
            int j = Arrays.binarySearch(boundaries, d);

            if (j >= binCount) {
                j = binCount - 1;
            }

            if (j < -1 && j >= -boundaries.length) {
                j = -j - 2;
            }

            if (j >= 0) {
                freq[2][j]++;
            }
        }

        return freq;
    }

    /**
     * Generate the histogram of n bins.
     * 
     * @param data     the data points
     * @param binCount the number of bins
     * @return a 3-by-k array of which first row is the lower bound of bins, second
     *         row is the upper bound of bins, and the third row is the frequency
     *         count
     */
    static double[][] of(double[] data, int binCount) {
        double min = MathExt.min(data);
        double max = MathExt.max(data);
        double span = max - min;
        if (span == 0) {
            span = binCount;
        }
        double width = span / binCount;

        double[] boundaries = new double[binCount + 1];
        boundaries[0] = min;
        for (int i = 1; i < binCount; i++) {
            boundaries[i] = boundaries[i - 1] + width;
        }
        boundaries[binCount] = max;

        return of(data, boundaries);
    }

    /**
     * Generate the histogram of n bins.
     * 
     * @param data       the data points
     * @param boundaries an array of size {@code binCount + 1} giving the boundaries
     *                   between histogram cells. Must be in ascending order
     * @return a 3-by-k bins array of which first row is the lower bound of bins,
     *         second row is the upper bound of bins, and the third row is the
     *         frequency count
     */
    static double[][] of(double[] data, double[] boundaries) {
        int binCount = checkBinCount(boundaries.length - 1);
        double[][] freq = new double[3][binCount];
        for (int i = 0; i < binCount; i++) {
            freq[0][i] = boundaries[i];
            freq[1][i] = boundaries[i + 1];
            freq[2][i] = 0.0d;
        }

        for (double d : data) {
            int j = Arrays.binarySearch(boundaries, d);

            if (j >= binCount) {
                j = binCount - 1;
            }

            if (j < -1 && j >= -boundaries.length) {
                j = -j - 2;
            }

            if (j >= 0) {
                freq[2][j]++;
            }
        }

        return freq;
    }

    static int checkBinCount(int binCount) {
        if (binCount <= 1) {
            throw new IllegalArgumentException("Invalid number of bins: " + binCount);
        }
        return binCount;
    }

    private HistoMath() {
    }
}
