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

/**
 * Project 2D logical coordinates to Java2D coordinates.
 */
final class Projection {

    /**
     * The canvas associated with this projection. The base object of canvas
     * provides logical coordinate space and the Java2D coordinate space of canvas
     * is the projection target.
     */
    protected final Canvas canvas;

    /**
     * The base coordinates on Java2D screen.
     */
    private int[][] baseScreenCoords;

    /**
     * The width of canvas in Java2D coordinate space.
     */
    protected int width = 1;

    /**
     * The height of canvas in Java2D coordinate space.
     */
    protected int height = 1;

    /**
     * Constructor.
     */
    Projection(Canvas canvas) {
        this.canvas = canvas;
        init();
    }

    /**
     * Reset the base coordinates on Java2D screen.
     */
    void reset() {
        init();
    }

    /**
     * Sets the size of physical plot area.
     */
    void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        reset();
    }

    /**
     * Initialize base coordinates on Java2D screen.
     */
    private void init() {
        Base base = canvas.base;
        double margin = canvas.margin;

        baseScreenCoords = new int[base.baseCoords.length][2];
        for (int i = 0; i < base.dimension + 1; i++) {
            double[] ratio = baseCoordsScreenProjectionRatio(base.baseCoords[i]);
            baseScreenCoords[i][0] = (int) (width * (margin + (1.0 - 2.0 * margin) * ratio[0]));
            baseScreenCoords[i][1] = (int) (height - height * (margin + (1.0 - 2.0 * margin) * ratio[1]));
        }
    }

    /**
     * Project logical coordinates to Java2D coordinates.
     */
    int[] screenProjection(double... coord) {
        Base base = canvas.base;

        double[] sc = new double[2];
        sc[0] = baseScreenCoords[0][0];
        sc[1] = baseScreenCoords[0][1];

        for (int i = 0; i < base.dimension; i++) {
            sc[0] += ((coord[i] - base.baseCoords[0][i]) / (base.baseCoords[i + 1][i] - base.baseCoords[0][i]))
                    * (baseScreenCoords[i + 1][0] - baseScreenCoords[0][0]);
            sc[1] += ((coord[i] - base.baseCoords[0][i]) / (base.baseCoords[i + 1][i] - base.baseCoords[0][i]))
                    * (baseScreenCoords[i + 1][1] - baseScreenCoords[0][1]);
        }

        return new int[] { (int) sc[0], (int) sc[1] };
    }

    /**
     * Project logical coordinates in base ratio to Java2D coordinates.
     */
    int[] screenProjectionBaseRatio(double... coord) {
        Base base = canvas.base;

        double[] sc = new double[2];
        sc[0] = baseScreenCoords[0][0];
        sc[1] = baseScreenCoords[0][1];

        for (int i = 0; i < base.dimension; i++) {
            sc[0] += coord[i] * (baseScreenCoords[i + 1][0] - baseScreenCoords[0][0]);
            sc[1] += coord[i] * (baseScreenCoords[i + 1][1] - baseScreenCoords[0][1]);
        }

        return new int[] { (int) sc[0], (int) sc[1] };
    }

    private double[] baseCoordsScreenProjectionRatio(double[] xy) {
        double[] ratio = new double[2];
        ratio[0] = (xy[0] - canvas.base.lowerBound[0]) / (canvas.base.upperBound[0] - canvas.base.lowerBound[0]);
        ratio[1] = (xy[1] - canvas.base.lowerBound[1]) / (canvas.base.upperBound[1] - canvas.base.lowerBound[1]);
        return ratio;
    }
}
