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
 * The coordinate base of PlotCanvas. This support both 2D and 3D
 * device-independent logical space where graphics primitives are specified.
 * <p>
 * The user need supply the lower and upper bounds for each axis. These bounds
 * usually are extended (a little) internally for better view purpose.
 */
final class Base {

    private static final String BOUND_SIZE_DOESNT_MATCH_THE_DIMENSION = "Bound size doesn't match the dimension.";

    /**
     * The dimensionality of base. Should be 2 or 3.
     */
    final int dimension;

    /**
     * Coordinates of base.
     */
    double[][] baseCoords;

    /**
     * Rounded/extended lower bound of each axis.
     */
    double[] lowerBound;

    /**
     * Rounded/extended upper bound of each axis.
     */
    double[] upperBound;

    /**
     * Precision unit of each axis.
     */
    private double[] precisionUnit;

    /**
     * Precision unit digits of each axis.
     */
    private int[] precisionDigits;

    /**
     * Original lower bound of each axis.
     */
    private double[] originalLowerBound;

    /**
     * Original upper bound of each axis.
     */
    private double[] originalUpperBound;

    /**
     * True to round/extend bound of each axis to nearest precision units.
     */
    private boolean[] extendBound;

    /**
     * Constructor.
     */
    Base(double[] lowerBound, double[] upperBound, boolean extendBound) {
        if (lowerBound.length != upperBound.length) {
            throw new IllegalArgumentException("Lower bound and upper bound size don't match.");
        }

        if (lowerBound.length != 2 && lowerBound.length != 3) {
            throw new IllegalArgumentException("Invalid bound dimension: " + lowerBound.length);
        }

        dimension = lowerBound.length;

        for (int i = 0; i < dimension; i++) {
            if (lowerBound[i] > upperBound[i]) {
                throw new IllegalArgumentException("Lower bound is larger than upper bound.");
            }

            if (lowerBound[i] == upperBound[i]) {
                lowerBound[i] -= 1.0;
                upperBound[i] += 1.0;
            }
        }

        this.originalLowerBound = lowerBound;
        this.originalUpperBound = upperBound;
        this.extendBound = new boolean[dimension];
        Arrays.fill(this.extendBound, extendBound);

        precisionDigits = new int[dimension];
        precisionUnit = new double[dimension];
        this.lowerBound = new double[dimension];
        this.upperBound = new double[dimension];

        reset();
    }

    /**
     * Reset base coordinates. Round/extend lower and upper bounds if necessary.
     */
    void reset() {
        for (int i = 0; i < dimension; i++) {
            lowerBound[i] = originalLowerBound[i];
            upperBound[i] = originalUpperBound[i];
            setPrecisionUnit(i);
        }

        // setup rounded base
        for (int i = 0; i < dimension; i++) {
            if (extendBound[i]) {
                extendBound(i);
            }
        }

        initBaseCoord();
    }

    /**
     * Reset coordinates
     */
    void initBaseCoord() {
        baseCoords = new double[dimension + 1][];
        for (int i = 0; i < baseCoords.length; i++) {
            baseCoords[i] = lowerBound.clone();
            if (i > 0) {
                baseCoords[i][i - 1] = upperBound[i - 1];
            }
        }
    }

    /**
     * Returns the dimensionality of coordinates.
     */
    int getDimension() {
        return dimension;
    }

    /**
     * Set the precision unit for axis i.
     */
    void setPrecisionUnit(int i) {
        if (upperBound[i] > lowerBound[i]) {
            double digits = Math.log10(Math.abs(upperBound[i] - lowerBound[i]));
            double residual = digits - Math.floor(digits);
            if (residual < 0.2) {
                // If the range is less than 15 units, we reduce one level.
                digits -= 1.0;
            }

            precisionDigits[i] = (int) Math.floor(digits);
            precisionUnit[i] = Math.pow(10, precisionDigits[i]);

            if (residual >= 0.2 && residual <= 0.7) {
                // In case of too few grids, we use a half of precision unit.
                precisionUnit[i] /= 2.0;
                precisionDigits[i] -= 1;
            }
        } else {
            precisionUnit[i] = 0.1;
        }
    }

    /**
     * Rounds the bounds for axis i.
     */
    void extendBound(int i) {
        if (i < 0 || i >= dimension) {
            throw new IllegalArgumentException("Invalid bound index: " + i);
        }

        extendBound[i] = true;
        lowerBound[i] = precisionUnit[i] * (Math.floor(originalLowerBound[i] / precisionUnit[i]));
        upperBound[i] = precisionUnit[i] * (Math.ceil(originalUpperBound[i] / precisionUnit[i]));
    }

    /**
     * Returns the coordinates.
     */
    double[][] getCoordinateSpace() {
        return baseCoords;
    }

    /**
     * Returns the lower bounds.
     */
    double[] getLowerBounds() {
        return lowerBound;
    }

    /**
     * Returns the upper bounds.
     */
    double[] getUpperBounds() {
        return upperBound;
    }

    /**
     * Returns the precision units of axes.
     */
    double[] getPrecisionUnit() {
        return precisionUnit;
    }

    /**
     * Returns the precision unit digits of axes.
     */
    int[] getPrecisionDigits() {
        return precisionDigits;
    }

    /**
     * Extend lower and upper bounds.
     */
    void extendBound(double[] lowerBound, double[] upperBound) {
        if (lowerBound.length != dimension || upperBound.length != dimension) {
            throw new IllegalArgumentException(BOUND_SIZE_DOESNT_MATCH_THE_DIMENSION);
        }

        boolean extend = false;
        for (int i = 0; i < lowerBound.length; i++) {
            if (lowerBound[i] < this.originalLowerBound[i]) {
                this.originalLowerBound[i] = lowerBound[i];
                extend = true;
            }
        }

        for (int i = 0; i < upperBound.length; i++) {
            if (upperBound[i] > this.originalUpperBound[i]) {
                this.originalUpperBound[i] = upperBound[i];
                extend = true;
            }
        }

        if (extend) {
            reset();
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(String.format("Base[%d]{", dimension));
        for (int i = 0; i < baseCoords.length; i++) {
            s.append("[");
            for (int j = 0; j < baseCoords[i].length; j++) {
                s.append(baseCoords[i][j]).append(',');
            }
            s.setCharAt(s.length() - 1, ']');
        }
        s.append('}');
        return s.toString();
    }
}
