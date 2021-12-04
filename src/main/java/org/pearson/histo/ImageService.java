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
import java.awt.Font;
import java.awt.image.BufferedImage;

/**
 * The {@literal ImageService} is the entry point for the creation of histogram
 * images.
 */
public final class ImageService {

    /**
     * Creates a histogram image given the histogram coordinates and a bin width
     * using defaults for fonts and colors.
     * 
     * @param histogramCoord n x 2 array, histogramCoord[][0] is the x coordinate
     *                       (mid point) of a bin, histogramCoord[][1] is the height
     *                       of a bin.
     * @param binWidth       the width of the bins
     * @param imgWidth       the width of the image
     * @param imgHeight      the height of the image
     * @param imgTitle       the title of the image
     * @param xAxisLabelText the text label of the x axis
     * @param yAxisLabelText the text label of the y axis
     * @return a BufferedImage representation of the histogram coordinates
     */
    public static BufferedImage createHistogramFromCoords(double[][] histogramCoord, double binWidth, int imgWidth,
            int imgHeight, String imgTitle, String xAxisLabelText, String yAxisLabelText) {

        BarPlot plot = BarPlot.of(histogramCoord, binWidth);
        return createImage(plot, imgWidth, imgHeight, imgTitle, xAxisLabelText, yAxisLabelText, null, null, null, null,
                null, null);
    }

    /**
     * Creates a histogram image given the histogram coordinates, a bin width and
     * bin color using defaults for fonts.
     * 
     * @param histogramCoord n x 2 array, histogramCoord[][0] is the x coordinate
     *                       (mid point) of a bin, histogramCoord[][1] is the height
     *                       of a bin.
     * @param binWidth       the width of the bins
     * @param imgWidth       the width of the image
     * @param imgHeight      the height of the image
     * @param imgTitle       the title of the image
     * @param xAxisLabelText the text label of the x axis
     * @param yAxisLabelText the text label of the y axis
     * @param binColor       the color of the bins
     * @return a BufferedImage representation of the histogram coordinates
     */
    public static BufferedImage createHistogramFromCoords(double[][] histogramCoord, double binWidth, int imgWidth,
            int imgHeight, String imgTitle, String xAxisLabelText, String yAxisLabelText, Color binColor) {

        BarPlot plot = BarPlot.of(histogramCoord, binWidth, binColor);
        return createImage(plot, imgWidth, imgHeight, imgTitle, xAxisLabelText, yAxisLabelText, null, null, null, null,
                null, null);
    }

    /**
     * Creates a histogram image given the histogram coordinates, a bin width, bin
     * color, title font and title color using defaults for the x axis and y axis
     * fonts and colors.
     * 
     * @param histogramCoord n x 2 array, histogramCoord[][0] is the x coordinate
     *                       (mid point) of a bin, histogramCoord[][1] is the height
     *                       of a bin.
     * @param binWidth       the width of the bins
     * @param imgWidth       the width of the image
     * @param imgHeight      the height of the image
     * @param imgTitle       the title of the image
     * @param xAxisLabelText the text label of the x axis
     * @param yAxisLabelText the text label of the y axis
     * @param binColor       the color of the bins
     * @param titleFont      the title font
     * @param titleColor     the title color
     * @return a BufferedImage representation of the histogram coordinates
     */
    public static BufferedImage createHistogramFromCoords(double[][] histogramCoord, double binWidth, int imgWidth,
            int imgHeight, String imgTitle, String xAxisLabelText, String yAxisLabelText, Color binColor,
            Font titleFont, Color titleColor) {

        BarPlot plot = BarPlot.of(histogramCoord, binWidth, binColor);
        return createImage(plot, imgWidth, imgHeight, imgTitle, xAxisLabelText, yAxisLabelText, titleFont, titleColor,
                null, null, null, null);
    }

    /**
     * Creates a histogram image given the histogram coordinates, a bin width, bin
     * color, title font, title color and fonts and colors for the x axis and y
     * axis.
     * 
     * @param histogramCoord  n x 2 array, histogramCoord[][0] is the x coordinate
     *                        (mid point) of a bin, histogramCoord[][1] is the
     *                        height of a bin.
     * @param binWidth        the width of the bins
     * @param imgWidth        the width of the image
     * @param imgHeight       the height of the image
     * @param imgTitle        the title of the image
     * @param xAxisLabelText  the text label of the x axis
     * @param yAxisLabelText  the text label of the y axis
     * @param binColor        the color of the bins
     * @param titleFont       the title font
     * @param titleColor      the title color
     * @param xAxisLabelFont  the font of the x axis
     * @param xAxisLabelColor the color of the x axis
     * @param yAxisLabelFont  the font of the y axis
     * @param yAxisLabelColor the color of the y axis
     * @return a BufferedImage representation of the histogram coordinates
     */
    public static BufferedImage createHistogramFromCoords(double[][] histogramCoord, double binWidth, int imgWidth,
            int imgHeight, String imgTitle, String xAxisLabelText, String yAxisLabelText, Color binColor,
            Font titleFont, Color titleColor, Font xAxisLabelFont, Color xAxisLabelColor, Font yAxisLabelFont,
            Color yAxisLabelColor) {

        BarPlot plot = BarPlot.of(histogramCoord, binWidth, binColor);
        return createImage(plot, imgWidth, imgHeight, imgTitle, xAxisLabelText, yAxisLabelText, titleFont, titleColor,
                xAxisLabelFont, xAxisLabelColor, yAxisLabelFont, yAxisLabelColor);
    }

    /**
     * Creates a histogram image from a data sample of doubles given a count of bins
     * using defaults for fonts and colors.
     * 
     * @param histogramData  the double data sample
     * @param binCount       the number of bins to partition the histogram data
     * @param scaleRelative  if {@code true} the y axis will be scaled in proportion
     *                       to the share of the bin data
     * @param imgWidth       the width of the image
     * @param imgHeight      the height of the image
     * @param imgTitle       the title of the image
     * @param xAxisLabelText the text label of the x axis
     * @param yAxisLabelText the text label of the y axis
     * @return a BufferedImage representation of the histogram data
     */
    public static BufferedImage createHistogramFromData(double[] histogramData, int binCount, boolean scaleRelative,
            int imgWidth, int imgHeight, String imgTitle, String xAxisLabelText, String yAxisLabelText) {

        BarPlot plot = Histogram.of(histogramData, binCount, scaleRelative);
        return createImage(plot, imgWidth, imgHeight, imgTitle, xAxisLabelText, yAxisLabelText, null, null, null, null,
                null, null);
    }

    /**
     * Creates a histogram image from a data sample of ints given a count of bins
     * using defaults for fonts and colors.
     * 
     * @param histogramData  the int data sample
     * @param binCount       the number of bins to partition the histogram data
     * @param scaleRelative  if {@code true} the y axis will be scaled in proportion
     *                       to the share of the bin data
     * @param imgWidth       the width of the image
     * @param imgHeight      the height of the image
     * @param imgTitle       the title of the image
     * @param xAxisLabelText the text label of the x axis
     * @param yAxisLabelText the text label of the y axis
     * @return a BufferedImage representation of the histogram data
     */
    public static BufferedImage createHistogramFromData(int[] histogramData, int binCount, boolean scaleRelative,
            int imgWidth, int imgHeight, String imgTitle, String xAxisLabelText, String yAxisLabelText) {

        BarPlot plot = Histogram.of(histogramData, binCount, scaleRelative);
        return createImage(plot, imgWidth, imgHeight, imgTitle, xAxisLabelText, yAxisLabelText, null, null, null, null,
                null, null);
    }

    /**
     * Creates a histogram image from a data sample of longs given a count of bins
     * using defaults for fonts and colors.
     * 
     * @param histogramData  the long data sample
     * @param binCount       the number of bins to partition the histogram data
     * @param scaleRelative  if {@code true} the y axis will be scaled in proportion
     *                       to the share of the bin data
     * @param imgWidth       the width of the image
     * @param imgHeight      the height of the image
     * @param imgTitle       the title of the image
     * @param xAxisLabelText the text label of the x axis
     * @param yAxisLabelText the text label of the y axis
     * @return a BufferedImage representation of the histogram data
     */
    public static BufferedImage createHistogramFromData(long[] histogramData, int binCount, boolean scaleRelative,
            int imgWidth, int imgHeight, String imgTitle, String xAxisLabelText, String yAxisLabelText) {

        BarPlot plot = Histogram.of(histogramData, binCount, scaleRelative);
        return createImage(plot, imgWidth, imgHeight, imgTitle, xAxisLabelText, yAxisLabelText, null, null, null, null,
                null, null);
    }

    /**
     * Creates a histogram image from a data sample of doubles given a count of bins
     * and bin color using defaults for fonts.
     * 
     * @param histogramData  the double data sample
     * @param binCount       the number of bins to partition the histogram data
     * @param scaleRelative  if {@code true} the y axis will be scaled in proportion
     *                       to the share of the bin data
     * @param imgWidth       the width of the image
     * @param imgHeight      the height of the image
     * @param imgTitle       the title of the image
     * @param xAxisLabelText the text label of the x axis
     * @param yAxisLabelText the text label of the y axis
     * @param binColor       the color of the bins
     * @return a BufferedImage representation of the histogram data
     */
    public static BufferedImage createHistogramFromData(double[] histogramData, int binCount, boolean scaleRelative,
            int imgWidth, int imgHeight, String imgTitle, String xAxisLabelText, String yAxisLabelText,
            Color binColor) {

        BarPlot plot = Histogram.of(histogramData, binCount, scaleRelative, binColor);
        return createImage(plot, imgWidth, imgHeight, imgTitle, xAxisLabelText, yAxisLabelText, null, null, null, null,
                null, null);
    }

    /**
     * Creates a histogram image from a data sample of ints given a count of bins
     * and bin color using defaults for fonts.
     * 
     * @param histogramData  the int data sample
     * @param binCount       the number of bins to partition the histogram data
     * @param scaleRelative  if {@code true} the y axis will be scaled in proportion
     *                       to the share of the bin data
     * @param imgWidth       the width of the image
     * @param imgHeight      the height of the image
     * @param imgTitle       the title of the image
     * @param xAxisLabelText the text label of the x axis
     * @param yAxisLabelText the text label of the y axis
     * @param binColor       the color of the bins
     * @return a BufferedImage representation of the histogram data
     */
    public static BufferedImage createHistogramFromData(int[] histogramData, int binCount, boolean scaleRelative,
            int imgWidth, int imgHeight, String imgTitle, String xAxisLabelText, String yAxisLabelText,
            Color binColor) {

        BarPlot plot = Histogram.of(histogramData, binCount, scaleRelative, binColor);
        return createImage(plot, imgWidth, imgHeight, imgTitle, xAxisLabelText, yAxisLabelText, null, null, null, null,
                null, null);
    }

    /**
     * Creates a histogram image from a data sample of longs given a count of bins
     * and bin color using defaults for fonts.
     * 
     * @param histogramData  the long data sample
     * @param binCount       the number of bins to partition the histogram data
     * @param scaleRelative  if {@code true} the y axis will be scaled in proportion
     *                       to the share of the bin data
     * @param imgWidth       the width of the image
     * @param imgHeight      the height of the image
     * @param imgTitle       the title of the image
     * @param xAxisLabelText the text label of the x axis
     * @param yAxisLabelText the text label of the y axis
     * @param binColor       the color of the bins
     * @return a BufferedImage representation of the histogram data
     */
    public static BufferedImage createHistogramFromData(long[] histogramData, int binCount, boolean scaleRelative,
            int imgWidth, int imgHeight, String imgTitle, String xAxisLabelText, String yAxisLabelText,
            Color binColor) {

        BarPlot plot = Histogram.of(histogramData, binCount, scaleRelative, binColor);
        return createImage(plot, imgWidth, imgHeight, imgTitle, xAxisLabelText, yAxisLabelText, null, null, null, null,
                null, null);
    }

    /**
     * Creates a histogram image from a data sample of doubles given a count of
     * bins, bin color, title font and title color using defaults for the x axis and
     * y axis fonts and colors.
     * 
     * @param histogramData  the double data sample
     * @param binCount       the number of bins to partition the histogram data
     * @param scaleRelative  if {@code true} the y axis will be scaled in proportion
     *                       to the share of the bin data
     * @param imgWidth       the width of the image
     * @param imgHeight      the height of the image
     * @param imgTitle       the title of the image
     * @param xAxisLabelText the text label of the x axis
     * @param yAxisLabelText the text label of the y axis
     * @param binColor       the color of the bins
     * @param titleFont      the title font
     * @param titleColor     the title color
     * @return a BufferedImage representation of the histogram data
     */
    public static BufferedImage createHistogramFromData(double[] histogramData, int binCount, boolean scaleRelative,
            int imgWidth, int imgHeight, String imgTitle, String xAxisLabelText, String yAxisLabelText, Color binColor,
            Font titleFont, Color titleColor) {

        BarPlot plot = Histogram.of(histogramData, binCount, scaleRelative, binColor);
        return createImage(plot, imgWidth, imgHeight, imgTitle, xAxisLabelText, yAxisLabelText, titleFont, titleColor,
                null, null, null, null);
    }

    /**
     * Creates a histogram image from a data sample of ints given a count of bins,
     * bin color, title font and title color using defaults for the x axis and y
     * axis fonts and colors.
     * 
     * @param histogramData  the int data sample
     * @param binCount       the number of bins to partition the histogram data
     * @param scaleRelative  if {@code true} the y axis will be scaled in proportion
     *                       to the share of the bin data
     * @param imgWidth       the width of the image
     * @param imgHeight      the height of the image
     * @param imgTitle       the title of the image
     * @param xAxisLabelText the text label of the x axis
     * @param yAxisLabelText the text label of the y axis
     * @param binColor       the color of the bins
     * @param titleFont      the title font
     * @param titleColor     the title color
     * @return a BufferedImage representation of the histogram data
     */
    public static BufferedImage createHistogramFromData(int[] histogramData, int binCount, boolean scaleRelative,
            int imgWidth, int imgHeight, String imgTitle, String xAxisLabelText, String yAxisLabelText, Color binColor,
            Font titleFont, Color titleColor) {

        BarPlot plot = Histogram.of(histogramData, binCount, scaleRelative, binColor);
        return createImage(plot, imgWidth, imgHeight, imgTitle, xAxisLabelText, yAxisLabelText, titleFont, titleColor,
                null, null, null, null);
    }

    /**
     * Creates a histogram image from a data sample of longs given a count of bins,
     * bin color, title font and title color using defaults for the x axis and y
     * axis fonts and colors.
     * 
     * @param histogramData  the long data sample
     * @param binCount       the number of bins to partition the histogram data
     * @param scaleRelative  if {@code true} the y axis will be scaled in proportion
     *                       to the share of the bin data
     * @param imgWidth       the width of the image
     * @param imgHeight      the height of the image
     * @param imgTitle       the title of the image
     * @param xAxisLabelText the text label of the x axis
     * @param yAxisLabelText the text label of the y axis
     * @param binColor       the color of the bins
     * @param titleFont      the title font
     * @param titleColor     the title color
     * @return a BufferedImage representation of the histogram data
     */
    public static BufferedImage createHistogramFromData(long[] histogramData, int binCount, boolean scaleRelative,
            int imgWidth, int imgHeight, String imgTitle, String xAxisLabelText, String yAxisLabelText, Color binColor,
            Font titleFont, Color titleColor) {

        BarPlot plot = Histogram.of(histogramData, binCount, scaleRelative, binColor);
        return createImage(plot, imgWidth, imgHeight, imgTitle, xAxisLabelText, yAxisLabelText, titleFont, titleColor,
                null, null, null, null);
    }

    /**
     * Creates a histogram image from a data sample of doubles given a count of
     * bins, bin color, title font, title color and fonts and colors for the x axis
     * and y axis.
     * 
     * @param histogramData   the double data sample
     * @param binCount        the number of bins to partition the histogram data
     * @param scaleRelative   if {@code true} the y axis will be scaled in
     *                        proportion to the share of the bin data
     * @param imgWidth        the width of the image
     * @param imgHeight       the height of the image
     * @param imgTitle        the title of the image
     * @param xAxisLabelText  the text label of the x axis
     * @param yAxisLabelText  the text label of the y axis
     * @param binColor        the color of the bins
     * @param titleFont       the title font
     * @param titleColor      the title color
     * @param xAxisLabelFont  the font of the x axis
     * @param xAxisLabelColor the color of the x axis
     * @param yAxisLabelFont  the font of the y axis
     * @param yAxisLabelColor the color of the y axis
     * @return a BufferedImage representation of the histogram data
     */
    public static BufferedImage createHistogramFromData(double[] histogramData, int binCount, boolean scaleRelative,
            int imgWidth, int imgHeight, String imgTitle, String xAxisLabelText, String yAxisLabelText, Color binColor,
            Font titleFont, Color titleColor, Font xAxisLabelFont, Color xAxisLabelColor, Font yAxisLabelFont,
            Color yAxisLabelColor) {

        BarPlot plot = Histogram.of(histogramData, binCount, scaleRelative, binColor);
        return createImage(plot, imgWidth, imgHeight, imgTitle, xAxisLabelText, yAxisLabelText, titleFont, titleColor,
                xAxisLabelFont, xAxisLabelColor, yAxisLabelFont, yAxisLabelColor);
    }

    /**
     * Creates a histogram image from a data sample of ints given a count of bins,
     * bin color, title font, title color and fonts and colors for the x axis and y
     * axis.
     * 
     * @param histogramData   the int data sample
     * @param binCount        the number of bins to partition the histogram data
     * @param scaleRelative   if {@code true} the y axis will be scaled in
     *                        proportion to the share of the bin data
     * @param imgWidth        the width of the image
     * @param imgHeight       the height of the image
     * @param imgTitle        the title of the image
     * @param xAxisLabelText  the text label of the x axis
     * @param yAxisLabelText  the text label of the y axis
     * @param binColor        the color of the bins
     * @param titleFont       the title font
     * @param titleColor      the title color
     * @param xAxisLabelFont  the font of the x axis
     * @param xAxisLabelColor the color of the x axis
     * @param yAxisLabelFont  the font of the y axis
     * @param yAxisLabelColor the color of the y axis
     * @return a BufferedImage representation of the histogram data
     */
    public static BufferedImage createHistogramFromData(int[] histogramData, int binCount, boolean scaleRelative,
            int imgWidth, int imgHeight, String imgTitle, String xAxisLabelText, String yAxisLabelText, Color binColor,
            Font titleFont, Color titleColor, Font xAxisLabelFont, Color xAxisLabelColor, Font yAxisLabelFont,
            Color yAxisLabelColor) {

        BarPlot plot = Histogram.of(histogramData, binCount, scaleRelative, binColor);
        return createImage(plot, imgWidth, imgHeight, imgTitle, xAxisLabelText, yAxisLabelText, titleFont, titleColor,
                xAxisLabelFont, xAxisLabelColor, yAxisLabelFont, yAxisLabelColor);
    }

    /**
     * Creates a histogram image from a data sample of longs given a count of bins,
     * bin color, title font, title color and fonts and colors for the x axis and y
     * axis.
     * 
     * @param histogramData   the long data sample
     * @param binCount        the number of bins to partition the histogram data
     * @param scaleRelative   if {@code true} the y axis will be scaled in
     *                        proportion to the share of the bin data
     * @param imgWidth        the width of the image
     * @param imgHeight       the height of the image
     * @param imgTitle        the title of the image
     * @param xAxisLabelText  the text label of the x axis
     * @param yAxisLabelText  the text label of the y axis
     * @param binColor        the color of the bins
     * @param titleFont       the title font
     * @param titleColor      the title color
     * @param xAxisLabelFont  the font of the x axis
     * @param xAxisLabelColor the color of the x axis
     * @param yAxisLabelFont  the font of the y axis
     * @param yAxisLabelColor the color of the y axis
     * @return a BufferedImage representation of the histogram data
     */
    public static BufferedImage createHistogramFromData(long[] histogramData, int binCount, boolean scaleRelative,
            int imgWidth, int imgHeight, String imgTitle, String xAxisLabelText, String yAxisLabelText, Color binColor,
            Font titleFont, Color titleColor, Font xAxisLabelFont, Color xAxisLabelColor, Font yAxisLabelFont,
            Color yAxisLabelColor) {

        BarPlot plot = Histogram.of(histogramData, binCount, scaleRelative, binColor);
        return createImage(plot, imgWidth, imgHeight, imgTitle, xAxisLabelText, yAxisLabelText, titleFont, titleColor,
                xAxisLabelFont, xAxisLabelColor, yAxisLabelFont, yAxisLabelColor);
    }

    private static BufferedImage createImage(BarPlot plot, int imgWidth, int imgHeight, String imgTitle,
            String xAxisLabelText, String yAxisLabelText, Font titleFont, Color titleColor, Font xAxisLabelFont,
            Color xAxisLabelColor, Font yAxisLabelFont, Color yAxisLabelColor) {

        Canvas canvas = plot.canvas();

        canvas.setTitle(imgTitle);
        canvas.setXAxisLabelText(xAxisLabelText);
        canvas.setYAxisLabelText(yAxisLabelText);

        if (titleFont != null) {
            canvas.setTitleFont(titleFont);
        }
        if (titleColor != null) {
            canvas.setTitleColor(titleColor);
        }
        if (xAxisLabelFont != null) {
            canvas.setXAxisLabelFont(xAxisLabelFont);
        }
        if (xAxisLabelColor != null) {
            canvas.setXAxisLabelColor(xAxisLabelColor);
        }
        if (yAxisLabelFont != null) {
            canvas.setYAxisLabelFont(yAxisLabelFont);
        }
        if (yAxisLabelColor != null) {
            canvas.setYAxisLabelColor(yAxisLabelColor);
        }

        return canvas.toBufferedImage(imgWidth, imgHeight);
    }

    private ImageService() {
    }
}
