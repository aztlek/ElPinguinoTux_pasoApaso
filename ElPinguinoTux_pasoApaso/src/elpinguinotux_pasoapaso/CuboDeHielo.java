/*
 * Copyright (C) 2015 Luis Alejandro Bernal Romero (Aztlek)
 * Contact: aztlek@gmail.com
 *
 * This file is part of El Pingüino Tu(int)((int)(x +Refactorizado.
 *
 * El Pingüino Tu(int)((int)(x +Refactorizado is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * El Pingüino Tu(int)((int)(x +Refactorizado is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with El Pingüino Tu(int)((int)(x +Refactorizado.  If not, see <http://www.gnu.org/licenses/>.
 */
package elpinguinotux_pasoapaso;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 * El cubo de hielo forma las paredes del laberinto por donde Tux tiene que
 * pasar.
 *
 * @author Luis Alejandro Bernal Romero (Aztlek)
 */
public class CuboDeHielo {
    private final double totalWidth = 190.0d;
    private final double totalHeight = 190.0d;
    private final double x, y;
    private final double width, height;
    private final double escalaX, escalaY;

    /**
     * Crea un cubo de hielo.
     *
     * @param x posición x en el escenario
     * @param y posición y en el escenario
     * @param width ancho
     * @param height alto
     */
    public CuboDeHielo(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.escalaX = width / totalWidth;
        this.escalaY = height / totalHeight;
    }

    /**
     * Dibuja el cubo de hielo que hace las paredes del laberinto.
     *
     * @param graphics2D donde se dibuja
     */
    public void paint(Graphics2D graphics2D) {
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(getX(), getY());
        graphics2D.scale(getEscalaX(), getEscalaY());

        // Fondo
        graphics2D.setColor(new Color(164, 238, 238));
        graphics2D.fill(new Rectangle2D.Double(0, 0, 190, 190));

        // Marco superior
        graphics2D.setColor(new Color(0, 128, 128));
        graphics2D.fill(new Rectangle2D.Double(0, 0, 190, 5));

        // Marco Inferior
        graphics2D.setColor(new Color(0, 128, 128));
        graphics2D.fill(new Rectangle2D.Double(0, 185, 190, 5.5));

        // Marco derecho
        graphics2D.setColor(new Color(0, 128, 128));
        graphics2D.fill(new Rectangle2D.Double(0, 0, 5, 190));

        // Marco izquierdo
        graphics2D.setColor(new Color(0, 128, 128));
        graphics2D.fill(
                new Rectangle2D.Double(185, 0, 5.5, 190));

        // Línea 0
        graphics2D.setColor(new Color(0, 128, 128));

        // Puntos línea 0
        int[] linea0x = {80, 84, 14, (10)};
        int[] linea0y = {(25), (28), (107), (104)};
        graphics2D.fillPolygon(linea0x, linea0y, linea0x.length);

        // Línea 1
        graphics2D.setColor(new Color(0, 128, 128));
        // Puntos línea 1
        int[] linea1x = {(132), (137), (53), (48)};
        int[] linea1y = {(42), (47), (142), (138)};
        graphics2D.fillPolygon(linea1x, linea1y, linea1x.length);

        // Línea 2
        graphics2D.setColor(new Color(0, 128, 128));
        // Puntos línea 2
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

} // class CuboDeHielo
