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
 * This is specialized label for axis labels. Coordinates used here are
 * proportional to the base coordinates.
 */
final class BaseLabel extends Label {

    /**
     * The font for axis label.
     */
    private static final Font Arial = new Font("Arial", Font.BOLD | Font.ITALIC, 13);

    /**
     * Constructor.
     */
    BaseLabel(String text, double[] coordinates, double horizontalReference, double verticalReference,
            double rotation) {
        super(text, coordinates, horizontalReference, verticalReference, rotation, Arial, Color.BLACK);
    }

    @Override
    void paint(Graphics g) {
        Font f = g.getFont();
        g.setFont(font);

        Color c = g.getColor();
        g.setColor(color);

        g.drawTextBaseRatio(text, coordinates, horizontalReference, verticalReference, rotation);

        g.setColor(c);
        g.setFont(f);
    }
}
