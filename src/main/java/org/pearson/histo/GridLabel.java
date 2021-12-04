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

/**
 * This is specialized label for axis grid labels.
 */
final class GridLabel extends Label {

    /**
     * The font for axis label.
     */
    private static final Font BitStreamVeraSans = new Font("BitStream Vera Sans", Font.PLAIN, 12);

    /**
     * Constructor.
     */
    GridLabel(String text, double[] coordinates, double horizontalReference, double verticalReference,
            double rotation) {
        super(text, coordinates, horizontalReference, verticalReference, rotation, BitStreamVeraSans, Color.BLACK);
    }
}
