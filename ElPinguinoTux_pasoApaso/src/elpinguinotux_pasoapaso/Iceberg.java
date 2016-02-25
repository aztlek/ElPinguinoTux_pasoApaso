/*
 * Copyright (C) 2015 Luis Alejandro Bernal Romero (Aztlek)
 * Contact: aztlek@gmail.com
 *
 * This file is part of El Pingüino Tux Refactorizado.
 *
 * El Pingüino Tux Refactorizado is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * El Pingüino Tux Refactorizado is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with El Pingüino Tux Refactorizado.  If not, see <http://www.gnu.org/licenses/>.
 */
package elpinguinotux_pasoapaso;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;

/**
 * El Iceberg donde Tux se puede esconder de las orcas.
 *
 * @author Liuis Alejandro Bernal Romero (Aztlek)
 */
public class Iceberg  {
    private final double totalWidth = 190.0d;
    private final double totalHeight = 190.0d;
    private final double x, y;
    private final double width, height;
    private final double escalaX, escalaY;
    private final float radio = (float)totalWidth / 2.0f;
    private final float anchoLinea = 5.0f;

    /**
     * Crea un nuevo Iceberg.
     *
     * @param x posición x en pixels.
     * @param y posición y en pixels.
     * @param width ancho en pixels.
     * @param height alto en pixels.
     */
    public Iceberg(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.escalaX = width / totalWidth;
        this.escalaY = height / totalHeight;
    }

    /**
     * Dibuja el iceberg.
     *
     * @param graphics2D donde se dibuja el villano
     */
    public void paint(Graphics2D graphics2D) {
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(getX(), getY());
        graphics2D.scale(getEscalaX(), getEscalaY());
        
        // Fondo
        graphics2D.setColor(new Color(41, 60, 255, 234));        
        graphics2D.fill(new RoundRectangle2D.Double(0, 0, 190, 190, radio, radio));
        
        // El borde
        graphics2D.setColor(new Color(128, 255, 163));
        Stroke oldStroke =  graphics2D.getStroke();
        BasicStroke newStroke = new BasicStroke(anchoLinea);
        graphics2D.setStroke(newStroke);
        graphics2D.draw(new RoundRectangle2D.Double(0 + (anchoLinea / 2), 0  + (anchoLinea / 2), 190 - (anchoLinea), 190 - (anchoLinea), radio -anchoLinea, radio - anchoLinea));
        graphics2D.setStroke(oldStroke);

        // Línea 0
        // Puntos línea 0             [0]                              [1]                              [2]                              [3]
        int[] linea0x = {80, 84, 14, 10};
        int[] linea0y = {25, 28, (107), (104)};
        graphics2D.fillPolygon(linea0x, linea0y, linea0x.length);

        // Línea 1
        // Puntos línea 1             [0]                              [1]                              [2]                              [3]
        int[] linea1x = {(132), (137), (53), (48)};
        int[] linea1y = {(42), (47), (142), (138)};
        graphics2D.fillPolygon(linea1x, linea1y, linea1x.length);

        // Línea 2
        // Puntos línea 2             [0]                              [1]                              [2]                              [3]
        int[] linea2x = {(176), (180), (115), (111)};
        int[] linea2y = {(76), 80, (162), (158)};
        graphics2D.fillPolygon(linea2x, linea2y, linea2x.length);

//        // Rejilla de referencia
//        new Grid(getTotalWidth(), getTotalHeight()).paint(graphics2D);

        graphics2D.setTransform(affineTransform);

    } // paint()

    public double getTotalWidth() {
        return totalWidth;
    }

    public double getTotalHeight() {
        return totalHeight;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getEscalaX() {
        return escalaX;
    }

    public double getEscalaY() {
        return escalaY;
    }

} // class Iceberg
