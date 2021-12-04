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
 * Label is a single line text.
 */
class Label extends Shape {

    /**
     * The text of the label.
     */
    final String text;

    /**
     * The coordinates of label.
     */
    final double[] coordinates;

    /**
     * The reference position of coordinates respected to dimension of text. (0.5,
     * 0.5) is center, (0, 0) is lower left, (0, 1) is upper left, etc.
     */
    final double horizontalReference;

    /**
     * The reference position of coordinates respected to dimension of text. (0.5,
     * 0.5) is center, (0, 0) is lower left, (0, 1) is upper left, etc.
     */
    final double verticalReference;

    /**
     * The rotation angel of text.
     */
    final double rotation;

    /**
     * The font for rendering the text. Use the system default font if this is null.
     */
    Font font;

    /**
     * Constructor.
     */
    Label(String text, double[] coordinates, double horizontalReference, double verticalReference, double rotation,
            Font font, Color color) {
        super(color);
        this.text = text;
        this.coordinates = coordinates;
        this.horizontalReference = horizontalReference;
        this.verticalReference = verticalReference;
        this.rotation = rotation;
        this.font = font;
    }

    void setFont(Font font) {
        this.font = font;
    }

    @Override
    void paint(Graphics g) {
        Font f = g.getFont();
        if (font != null)
            g.setFont(font);

        Color c = g.getColor();
        g.setColor(color);

        g.drawText(text, coordinates, horizontalReference, verticalReference, rotation);

        g.setColor(c);
        if (font != null) {
            g.setFont(f);
        }
    }
}
