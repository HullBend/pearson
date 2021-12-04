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
 * Bars with heights proportional to the value.
 */
final class Bar extends Shape {

    /**
     * The bar location and height.
     */
    final double[][] data;

    /**
     * The width of bar.
     */
    final double width;

    /**
     * The left top coordinates of bar.
     */
    final double[][] leftTop;

    /**
     * The right top coordinates of bar.
     */
    final double[][] rightTop;

    /**
     * The left bottom coordinates of bar.
     */
    final double[][] leftBottom;

    /**
     * The right bottom coordinates of bar.
     */
    final double[][] rightBottom;

    /**
     * Constructor.
     * 
     * @param data  n x 2 array. data[][0] is the x coordinate of bar, data[][1] is
     *              the height of bar.
     * @param width the width of bars
     * @param color the color of bars
     */
    Bar(double[][] data, double width, Color color) {
        super(color);

        if (data[0].length != 2) {
            throw new IllegalArgumentException("Data is not 2-dimensional");
        }

        this.data = data;
        this.width = width;

        int n = data.length;
        leftTop = new double[n][2];
        rightTop = new double[n][2];
        leftBottom = new double[n][2];
        rightBottom = new double[n][2];

        for (int i = 0; i < n; i++) {
            leftTop[i][0] = data[i][0] - width / 2.0;
            leftTop[i][1] = data[i][1];

            rightTop[i][0] = data[i][0] + width / 2.0;
            rightTop[i][1] = data[i][1];

            leftBottom[i][0] = data[i][0] - width / 2.0;
            leftBottom[i][1] = 0.0;

            rightBottom[i][0] = data[i][0] + width / 2.0;
            rightBottom[i][1] = 0.0;
        }
    }

    @Override
    void paint(Graphics g) {
        g.setColor(Color.BLACK);
        for (int i = 0; i < data.length; i++) {
            g.drawLine(leftBottom[i], leftTop[i]);
            g.drawLine(leftTop[i], rightTop[i]);
            g.drawLine(rightTop[i], rightBottom[i]);
            g.drawLine(rightBottom[i], leftBottom[i]);
        }

        g.setColor(color);
        for (int i = 0; i < data.length; i++) {
            g.fillPolygon(0.2f, leftBottom[i], leftTop[i], rightTop[i], rightBottom[i]);
        }
    }
}
