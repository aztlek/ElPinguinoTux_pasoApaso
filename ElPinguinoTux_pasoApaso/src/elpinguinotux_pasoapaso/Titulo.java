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

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 * El título del juego.
 *
 * @author Luis Alejandro Bernal Romero (Aztlek)
 */
public class Titulo {

    private final double totalWidth = 16.0d;
    private final double totalHeight = 150.0d;
    private final double x, y;
    private final double width, height;
    private final double escalaX, escalaY;
    
    private static final double altoTituloContadores = 50.0d;
    private static final double escalaTextos = 1.3;
    private static final String titulo = "El Pingüino Tux";
    /**
     * Construye el título del juego.
     *
     * @param x posición x en pixels.
     * @param y posición y en pixels.
     * @param width ancho en pixels.
     * @param height alto en pixels.
     */
    public Titulo(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.escalaX = width / totalWidth;
        this.escalaY = height / totalHeight;
    } // Título()

    public void paint(Graphics2D graphics2D) {
        // Obtener las transformaciones actales.
        AffineTransform affineTransform = graphics2D.getTransform();

        graphics2D.setColor(new Color(0, 128, 128));
        graphics2D.setFont(new Font("sans", Font.BOLD, (int) Math.round(12d * escalaTextos)));
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        int heightFont = fontMetrics.getHeight();
        int widthFont = fontMetrics.stringWidth(titulo);

        // Transformaciones
        graphics2D.translate(getX(), getY());
        graphics2D.scale(getEscalaX(), getEscalaY());
        graphics2D.rotate(-Math.PI / 2.0d, getTotalWidth() / 2.0d, getY() + getTotalHeight() / 2.0d);

        // Título
        graphics2D.drawString(
                titulo,
                (int) Math.round((getTotalWidth() - widthFont) / 2.0d),
                (int) Math.round((getTotalHeight() + heightFont / 2.0d) / 2.0d)
        );
        
        // Volver a las anteriores transformaciones.
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

} // class Titulo
