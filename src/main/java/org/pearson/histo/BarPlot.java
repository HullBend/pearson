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
import java.util.Optional;

/**
 * A barplot draws bars with heights proportional to the value.
 */
final class BarPlot extends Plot {

    /**
     * The bar groups which may have different colors.
     */
    final Bar[] bars;

    /**
     * The legends of each bar group.
     */
    final Optional<Legend[]> legends;

    /**
     * Constructor.
     */
    BarPlot(Bar... bars) {
        this.bars = bars;
        legends = Optional.empty();
    }

    /**
     * Constructor.
     */
    BarPlot(Bar[] bars, Legend[] legends) {
        this.bars = bars;
        this.legends = Optional.of(legends);
    }

    /**
     * Creates a barplot.
     * 
     * @param coord    n x 2 array, coord[][0] is the x coordinate of a bar,
     *                 coord[][1] is the height of a bar.
     * @param barWidth the width of bars
     */
    static BarPlot of(double[][] coord, double barWidth) {
        return of(coord, barWidth, ColorPalette.TURQUOISE);
    }

    /**
     * Creates a barplot.
     * 
     * @param coord    n x 2 array, coord[][0] is the x coordinate of a bar,
     *                 coord[][1] is the height of a bar.
     * @param barWidth the width of bars
     * @param barColor the color of bars
     */
    static BarPlot of(double[][] coord, double barWidth, Color barColor) {
        return new BarPlot(new Bar(coord, barWidth, barColor));
    }

    @Override
    void paint(Graphics g) {
        for (Bar bar : bars) {
            bar.paint(g);
        }
    }

    @Override
    double[] getLowerBound() {
        double[] bound = MathExt.colMin(bars[0].data);
        bound[0] -= bars[0].width / 2;

        for (int k = 1; k < bars.length; k++) {
            for (double[] x : bars[k].data) {
                if (bound[0] > x[0] - bars[k].width / 2.0) {
                    bound[0] = x[0] - bars[k].width / 2.0;
                }
                if (bound[1] > x[1]) {
                    bound[1] = x[1];
                }
            }
        }

        return bound;
    }

    @Override
    double[] getUpperBound() {
        double[] bound = MathExt.colMax(bars[0].data);
        bound[0] += bars[0].width / 2.0;

        for (int k = 1; k < bars.length; k++) {
            for (double[] x : bars[k].data) {
                if (bound[0] > x[0] + bars[k].width / 2.0) {
                    bound[0] = x[0] + bars[k].width / 2.0;
                }
                if (bound[1] > x[1]) {
                    bound[1] = x[1];
                }
            }
        }

        return bound;
    }

    @Override
    Optional<Legend[]> legends() {
        return legends;
    }

    @Override
    Canvas canvas() {
        Canvas canvas = new Canvas(getLowerBound(), getUpperBound());
        canvas.add(this);
        canvas.getAxis(0).setGridVisible(false);
        return canvas;
    }
}
