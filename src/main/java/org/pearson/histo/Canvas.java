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
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import javax.swing.event.SwingPropertyChangeSupport;

/**
 * Canvas for mathematical plots.
 */
final class Canvas {

    /**
     * The default (one side) margin portion.
     */
    private static final double DEFAULT_MARGIN = 0.15;

    /**
     * The default font for rendering the title.
     */
    private static final Font DEFAULT_TITLE_FONT = new Font("Arial", Font.BOLD, 16);

    /**
     * The default color for rendering the title.
     */
    private static final Color DEFAULT_TITLE_COLOR = ColorPalette.DARK_RED;

    /**
     * The current coordinate base.
     */
    Base base;

    /**
     * The graphics object associated with this canvas.
     */
    Graphics graphics;

    /**
     * The portion of the canvas used for margin.
     */
    double margin = DEFAULT_MARGIN;

    /**
     * The axis objects.
     */
    Axis[] axis;

    /**
     * The shapes in the canvas, e.g. label, plots, etc.
     */
    ArrayList<Shape> shapes = new ArrayList<>();

    /**
     * Show legends if true.
     */
    private boolean isLegendVisible = true;

    /**
     * The main title of plot.
     */
    private String title;

    /**
     * The font for rendering the title.
     */
    private Font titleFont = DEFAULT_TITLE_FONT;

    /**
     * The color for rendering the title.
     */
    private Color titleColor = DEFAULT_TITLE_COLOR;

    /**
     * Notify Swing listeners when a property changes.
     */
    private SwingPropertyChangeSupport pcs = new SwingPropertyChangeSupport(this, true);

    /**
     * Constructor
     */
    Canvas(double[] lowerBound, double[] upperBound) {
        this(lowerBound, upperBound, true);
    }

    /**
     * Constructor
     */
    Canvas(double[] lowerBound, double[] upperBound, boolean extendBound) {
        initBase(lowerBound, upperBound, extendBound);
        initGraphics();
    }

    /**
     * Exports the plot to an image.
     *
     * @param width  the width of image
     * @param height the height of image
     */
    BufferedImage toBufferedImage(int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        paint(g2d, width, height);
        return bi;
    }

    /**
     * Initialize the Graphics object.
     */
    private void initGraphics() {
        if (base.dimension == 2) {
            graphics = new Graphics(new Projection(this));
        }
    }

    /**
     * Initialize a coordinate base.
     */
    private void initBase(double[] lowerBound, double[] upperBound, boolean extendBound) {
        base = new Base(lowerBound, upperBound, extendBound);
        axis = new Axis[base.getDimension()];
        for (int i = 0; i < base.getDimension(); i++) {
            axis[i] = new Axis(base, i);
        }
    }

    /**
     * Reset the grid (when the base changes).
     */
    void resetAxis() {
        for (int i = 0; i < axis.length; i++) {
            axis[i].reset();
        }
    }

    Canvas setTitle(String title) {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "title", this.title, title);
        this.title = title;
        pcs.firePropertyChange(event);
        return this;
    }

    Canvas setTitleFont(Font titleFont) {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "titleFont", this.titleFont, titleFont);
        this.titleFont = titleFont;
        pcs.firePropertyChange(event);
        return this;
    }

    Canvas setTitleColor(Color titleColor) {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "titleColor", this.titleColor, titleColor);
        this.titleColor = titleColor;
        pcs.firePropertyChange(event);
        return this;
    }

    /**
     * Returns the i-<i>th</i> axis.
     */
    Axis getAxis(int i) {
        return axis[i];
    }

    /**
     * Returns the labels/legends of axes.
     */
    String[] getAxisLabels() {
        String[] labels = new String[base.dimension];
        for (int i = 0; i < base.dimension; i++) {
            labels[i] = axis[i].getLabel();
        }
        return labels;
    }

    Canvas setXAxisLabelText(String label) {
        return setAxisLabel(0, label);
    }

    Canvas setXAxisLabelFont(Font font) {
        return setAxisFont(0, font);
    }

    Canvas setXAxisLabelColor(Color color) {
        return setAxisColor(0, color);
    }

    Canvas setYAxisLabelText(String label) {
        return setAxisLabel(1, label);
    }

    Canvas setYAxisLabelFont(Font font) {
        return setAxisFont(1, font);
    }

    Canvas setYAxisLabelColor(Color color) {
        return setAxisColor(1, color);
    }

    /**
     * Sets the label/legend of an axis.
     */
    private Canvas setAxisLabel(int i, String label) {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "axisLabel", axis[i].getLabel(), label);
        axis[i].setLabel(label);
        pcs.firePropertyChange(event);
        return this;
    }

    private Canvas setAxisFont(int i, Font font) {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "axisFont", axis[i].getFont(), font);
        axis[i].setFont(font);
        pcs.firePropertyChange(event);
        return this;
    }

    private Canvas setAxisColor(int i, Color color) {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "axisColor", axis[i].getColor(), color);
        axis[i].setColor(color);
        pcs.firePropertyChange(event);
        return this;
    }

    /**
     * Add a graphical shape to the canvas.
     */
    void add(Plot p) {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "addPlot", this, p);
        shapes.add(p);
        extendBound(p.getLowerBound(), p.getUpperBound());
        pcs.firePropertyChange(event);
    }

    /**
     * Extend lower and upper bounds.
     */
    void extendBound(double[] lowerBound, double[] upperBound) {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "extendBound", this,
                new double[][] { lowerBound, upperBound });
        base.extendBound(lowerBound, upperBound);
        resetAxis();
        pcs.firePropertyChange(event);
    }

    /**
     * Paints the canvas.
     */
    void paint(java.awt.Graphics2D g2d, int width, int height) {
        graphics.setGraphics(g2d, width, height);

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        for (int i = 0; i < axis.length; i++) {
            axis[i].paint(graphics);
        }

        // draw plot
        graphics.clip();
        // with for-each loop, we will get a ConcurrentModificationException.
        // Use for loop instead.
        for (int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            graphics.setColor(shape.color);
            shape.paint(graphics);
        }
        graphics.clearClip();

        // draw legends
        if (isLegendVisible) {
            Font font = g2d.getFont();
            int x = (int) (width * (1.0 - margin) + 20.0);
            int y = (int) (height * margin + 50.0);
            int fontWidth = font.getSize();
            int fontHeight = font.getSize();

            for (int i = 0; i < shapes.size(); i++) {
                Shape s = shapes.get(i);
                if (s instanceof Plot) {
                    Plot p = (Plot) s;
                    if (p.legends().isPresent()) {
                        for (Legend legend : p.legends().get()) {
                            g2d.setColor(legend.color);
                            g2d.fillRect(x, y, fontWidth, fontHeight);
                            g2d.drawRect(x, y, fontWidth, fontHeight);
                            g2d.drawString(legend.text, x + 2 * fontWidth, y + fontHeight);
                            y += 2 * fontWidth;
                        }
                    }
                }
            }
        }

        if (title != null) {
            g2d.setFont(titleFont);
            g2d.setColor(titleColor);
            FontMetrics fm = g2d.getFontMetrics();
            int x = (width - fm.stringWidth(title)) / 2;
            int y = (int) (height * margin) / 2;
            g2d.drawString(title, x, y);
        }
    }
}
