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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;

/**
 * This class represents a poly line in the plot.
 */
final class Line extends Shape {

    /**
     * The stroke for solid line.
     */
    private static final BasicStroke SOLID_STROKE = new BasicStroke(1F, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);

    /**
     * The stroke for dotted line.
     */
    private static final BasicStroke DOT_STROKE = new BasicStroke(1F, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1F,
            new float[] { 2.0F }, 0.0F);

    /**
     * The stroke for dash line.
     */
    private static final BasicStroke DASH_STROKE = new BasicStroke(1F, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1F,
            new float[] { 10.0F }, 0.0F);

    /**
     * The stroke for dot-dash line.
     */
    private static final BasicStroke DOT_DASH_STROKE = new BasicStroke(1F, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND,
            1F, new float[] { 10.0F, 2.0F, 2.0F, 2.0F }, 0.0F);

    /**
     * The stroke for long dash line.
     */
    private static final BasicStroke LONG_DASH_STROKE = new BasicStroke(1F, BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_ROUND, 1F, new float[] { 20.0F }, 0.0F);

    /**
     * The stroke object to rendering the line.
     */
    private final Stroke stroke;

    /**
     * The end points of the line.
     */
    final double[][] points;

    /**
     * The style of line.
     */
    final LineStyle style;

    /**
     * The mark of points.
     */
    final char mark;

    /**
     * Constructor.
     * 
     * @param points a n-by-2 or n-by-3 matrix that are the coordinates of points
     * @param style  the style of line
     * @param mark   the mark of points
     *               <ul>
     *               <li>white space : don't draw the points.
     *               <li>. : dot
     *               <li>+ : +
     *               <li>- : -
     *               <li>| : |
     *               <li>* : star
     *               <li>x : x
     *               <li>o : circle
     *               <li>O : large circle
     *               <li>@ : solid circle
     *               <li># : large solid circle
     *               <li>s : square
     *               <li>S : large square
     *               <li>q : solid square
     *               <li>Q : large solid square
     *               <li>others : dot
     *               </ul>
     * @param color  the color of line
     */
    Line(double[][] points, LineStyle style, char mark, Color color) {
        super(color);
        this.points = points;
        this.style = style;
        this.mark = mark;

        switch (style) {
        case SOLID:
            stroke = SOLID_STROKE;
            break;
        case DOT:
            stroke = DOT_STROKE;
            break;
        case DASH:
            stroke = DASH_STROKE;
            break;
        case DOT_DASH:
            stroke = DOT_DASH_STROKE;
            break;
        case LONG_DASH:
            stroke = LONG_DASH_STROKE;
            break;
        default:
            throw new IllegalArgumentException("Unknown style: " + style);
        }
    }

    @Override
    void paint(Graphics g) {
        g.setColor(color);

        Stroke s = g.getStroke();
        g.setStroke(stroke);

        g.drawLine(points);

        if (mark != ' ') {
            for (double[] point : points) {
                g.drawPoint(mark, point);
            }
        }

        g.setStroke(s);
    }
}
