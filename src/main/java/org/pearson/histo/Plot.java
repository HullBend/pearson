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
 * The abstract base class of plots.
 */
abstract class Plot extends Shape {

    /** Constructor. */
    Plot() {
        this(Color.BLACK);
    }

    /** Constructor. */
    Plot(Color color) {
        super(color);
    }

    /** Returns the lower bound of data. */
    abstract double[] getLowerBound();

    /** Returns the upper bound of data. */
    abstract double[] getUpperBound();

    /** Returns a canvas of the plot. */
    Canvas canvas() {
        Canvas canvas = new Canvas(getLowerBound(), getUpperBound());
        canvas.add(this);
        return canvas;
    }

    /**
     * Returns the optional name of shape, which will be used to draw a legend
     * outside the box.
     */
    Optional<Legend[]> legends() {
        return Optional.empty();
    }
}
