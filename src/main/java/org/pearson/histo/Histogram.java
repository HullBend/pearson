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

import java.awt.Color;

/**
 * A histogram is a graphical display of tabulated frequencies, shown as bars.
 * It shows what proportion of cases fall into each of several categories: it is
 * a form of data binning. The categories are usually specified as
 * non-overlapping intervals of some variable. The categories (bars) must be
 * adjacent. The intervals (or bands, or bins) are generally of the same size,
 * and are most easily interpreted if they are.
 */
final class Histogram {

    /**
     * Creates a histogram plot.
     * 
     * @param data     a sample set
     * @param binCount the number of bins
     * @param relative if true, the y-axis will be in the probability scale.
     *                 Otherwise, y-axis will be in the frequency scale
     */
    static BarPlot of(int[] data, int binCount, boolean relative) {
        return of(data, binCount, relative, ColorPalette.TURQUOISE);
    }

    /**
     * Creates a histogram plot.
     * 
     * @param data     a sample set
     * @param binCount the number of bins
     * @param relative if true, the y-axis will be in the probability scale.
     *                 Otherwise, y-axis will be in the frequency scale
     */
    static BarPlot of(long[] data, int binCount, boolean relative) {
        return of(data, binCount, relative, ColorPalette.TURQUOISE);
    }

    /**
     * Creates a histogram plot.
     * 
     * @param data     a sample set
     * @param binCount the number of bins
     * @param relative if true, the y-axis will be in the probability scale.
     *                 Otherwise, y-axis will be in the frequency scale.
     */
    static BarPlot of(int[] data, int binCount, boolean relative, Color color) {
        double[][] hist = HistoMath.of(data, binCount);

        // The number of bins may be extended to cover all data.
        binCount = hist[0].length;
        double[][] freq = new double[binCount][2];
        for (int i = 0; i < binCount; i++) {
            freq[i][0] = (hist[0][i] + hist[1][i]) / 2.0;
            freq[i][1] = hist[2][i];
        }

        if (relative) {
            double n = data.length;
            for (int i = 0; i < binCount; i++) {
                freq[i][1] /= n;
            }
        }

        return new BarPlot(new Bar(freq, width(freq), color));
    }

    /**
     * Creates a histogram plot.
     * 
     * @param data     a sample set
     * @param binCount the number of bins
     * @param relative if true, the y-axis will be in the probability scale.
     *                 Otherwise, y-axis will be in the frequency scale.
     */
    static BarPlot of(long[] data, int binCount, boolean relative, Color color) {
        double[][] hist = HistoMath.of(data, binCount);

        // The number of bins may be extended to cover all data.
        binCount = hist[0].length;
        double[][] freq = new double[binCount][2];
        for (int i = 0; i < binCount; i++) {
            freq[i][0] = (hist[0][i] + hist[1][i]) / 2.0;
            freq[i][1] = hist[2][i];
        }

        if (relative) {
            double n = data.length;
            for (int i = 0; i < binCount; i++) {
                freq[i][1] /= n;
            }
        }

        return new BarPlot(new Bar(freq, width(freq), color));
    }

    /**
     * Creates a histogram plot.
     * 
     * @param data       a sample set
     * @param boundaries an array of size {@code binCount + 1} giving the boundaries
     *                   between histogram cells. Must be in ascending order
     * @param relative   if true, the y-axis will be in the probability scale.
     *                   Otherwise, y-axis will be in the frequency scale.
     */
    static BarPlot of(int[] data, double[] boundaries, boolean relative) {
        return of(data, boundaries, relative, ColorPalette.TURQUOISE);
    }

    /**
     * Creates a histogram plot.
     * 
     * @param data       a sample set
     * @param boundaries an array of size {@code binCount + 1} giving the boundaries
     *                   between histogram cells. Must be in ascending order
     * @param relative   if true, the y-axis will be in the probability scale.
     *                   Otherwise, y-axis will be in the frequency scale.
     */
    static BarPlot of(long[] data, double[] boundaries, boolean relative) {
        return of(data, boundaries, relative, ColorPalette.TURQUOISE);
    }

    /**
     * Creates a histogram plot.
     * 
     * @param data       a sample set
     * @param boundaries an array of size {@code binCount + 1} giving the boundaries
     *                   between histogram cells. Must be in ascending order
     * @param relative   if true, the y-axis will be in the probability scale.
     *                   Otherwise, y-axis will be in the frequency scale.
     */
    static BarPlot of(int[] data, double[] boundaries, boolean relative, Color color) {
        int binCount = HistoMath.checkBinCount(boundaries.length - 1);
        double[][] hist = HistoMath.of(data, boundaries);

        double[][] freq = new double[binCount][2];
        for (int i = 0; i < binCount; i++) {
            freq[i][0] = (hist[0][i] + hist[1][i]) / 2.0;
            freq[i][1] = hist[2][i];
        }

        if (relative) {
            double n = data.length;
            for (int i = 0; i < binCount; i++) {
                freq[i][1] /= n;
            }
        }

        return new BarPlot(new Bar(freq, width(freq), color));
    }

    /**
     * Creates a histogram plot.
     * 
     * @param data       a sample set
     * @param boundaries an array of size {@code binCount + 1} giving the boundaries
     *                   between histogram cells. Must be in ascending order
     * @param relative   if true, the y-axis will be in the probability scale.
     *                   Otherwise, y-axis will be in the frequency scale.
     */
    static BarPlot of(long[] data, double[] boundaries, boolean relative, Color color) {
        int binCount = HistoMath.checkBinCount(boundaries.length - 1);
        double[][] hist = HistoMath.of(data, boundaries);

        double[][] freq = new double[binCount][2];
        for (int i = 0; i < binCount; i++) {
            freq[i][0] = (hist[0][i] + hist[1][i]) / 2.0;
            freq[i][1] = hist[2][i];
        }

        if (relative) {
            double n = data.length;
            for (int i = 0; i < binCount; i++) {
                freq[i][1] /= n;
            }
        }

        return new BarPlot(new Bar(freq, width(freq), color));
    }

    /**
     * Creates a histogram plot.
     * 
     * @param data     a sample set
     * @param binCount the number of bins
     * @param relative if true, the y-axis will be in the probability scale.
     *                 Otherwise, y-axis will be in the frequency scale.
     */
    static BarPlot of(double[] data, int binCount, boolean relative) {
        return of(data, binCount, relative, ColorPalette.TURQUOISE);
    }

    /**
     * Creates a histogram plot.
     * 
     * @param data     a sample set
     * @param binCount the number of bins
     * @param relative if true, the y-axis will be in the probability scale.
     *                 Otherwise, y-axis will be in the frequency scale.
     */
    static BarPlot of(double[] data, int binCount, boolean relative, Color color) {
        double[][] hist = HistoMath.of(data, binCount);

        // The number of bins may be extended to cover all data.
        binCount = hist[0].length;
        double[][] freq = new double[binCount][2];
        for (int i = 0; i < binCount; i++) {
            freq[i][0] = (hist[0][i] + hist[1][i]) / 2.0;
            freq[i][1] = hist[2][i];
        }

        if (relative) {
            double n = data.length;
            for (int i = 0; i < binCount; i++) {
                freq[i][1] /= n;
            }
        }

        return new BarPlot(new Bar(freq, width(freq), color));
    }

    /**
     * Creates a histogram plot.
     * 
     * @param data       a sample set
     * @param boundaries an array of size {@code binCount + 1} giving the boundaries
     *                   between histogram cells. Must be in ascending order.
     * @param relative   if true, the y-axis will be in the probability scale.
     *                   Otherwise, y-axis will be in the frequency scale.
     */
    static BarPlot of(double[] data, double[] boundaries, boolean relative) {
        return of(data, boundaries, relative, ColorPalette.TURQUOISE);
    }

    /**
     * Creates a histogram plot.
     * 
     * @param data       a sample set
     * @param boundaries an array of size {@code binCount + 1} giving the boundaries
     *                   between histogram cells. Must be in ascending order.
     * @param relative   if true, the y-axis will be in the probability scale.
     *                   Otherwise, y-axis will be in the frequency scale.
     */
    static BarPlot of(double[] data, double[] boundaries, boolean relative, Color color) {
        int binCount = HistoMath.checkBinCount(boundaries.length - 1);
        double[][] hist = HistoMath.of(data, boundaries);

        double[][] freq = new double[binCount][2];
        for (int i = 0; i < binCount; i++) {
            freq[i][0] = (hist[0][i] + hist[1][i]) / 2.0;
            freq[i][1] = hist[2][i];
        }

        if (relative) {
            double n = data.length;
            for (int i = 0; i < binCount; i++) {
                freq[i][1] /= n;
            }
        }

        return new BarPlot(new Bar(freq, width(freq), color));
    }

    /** Calculates the width of bins. */
    private static double width(double[][] freq) {
        double width = Double.MAX_VALUE;
        for (int i = 1; i < freq.length; i++) {
            double w = Math.abs(freq[i][0] - freq[i - 1][0]);
            if (width > w) {
                width = w;
            }
        }
        return width;
    }
}
