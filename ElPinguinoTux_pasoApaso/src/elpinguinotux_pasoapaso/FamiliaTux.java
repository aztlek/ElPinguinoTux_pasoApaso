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

import java.awt.Graphics2D;

/**
 * La familia de Tux
 *
 * @author Luis Alejandro Bernal Romero (Aztlek)
 */
public class FamiliaTux {
    private final double totalWidth = 223.0d;
    private final double totalHeight = 190.0d;
    private final double x, y;
    private final double width, height;
    private final double escalaX, escalaY;
    
    private final Tux bebe;
    private final Tux pareja;

    /**
     * Construye a la familia de Tux.
     *
     * @param x posición x en pixels
     * @param y posición y en pixels.
     * @param width ancho en pixels.
     * @param height alto en pixels.
     */
    public FamiliaTux(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.escalaX = width / totalWidth;
        this.escalaY = height / totalHeight;
        bebe = new Tux(
                x + width * 0 / getTotalWidth(),
                y + height * 110 / getTotalHeight(),
                width * 62 / getTotalWidth(),
                height * 77 / getTotalHeight()
        );
        pareja = new Tux(
                x + width * 60 / getTotalWidth(),
                y + height * 0 / getTotalHeight(),
                width * 154 / getTotalWidth(),
                height * 190 / getTotalHeight()
        );
    } // FamiliaTux()

    /**
     * Dibuja la familia
     *
     * @param graphics2D El contexto gráfico donde se dibuja.
     */
    public void paint(Graphics2D graphics2D) {
        bebe.paint(graphics2D);
        pareja.paint(graphics2D);
    }

    public final double getTotalWidth() {
        return totalWidth;
    }

    public final double getTotalHeight() {
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

} // class FamiliaTux
