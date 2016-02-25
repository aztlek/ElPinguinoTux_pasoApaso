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
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

/**
 * Los peces que captura Tux pata llevarle a su familia.
 *
 * @author Luis Alejandro Bernal Romero (Aztlek)
 */
public class Pez {
    
    private final double totalWidth = 280.0d;
    private final double totalHeight = 110.0d;
    private final double x, y;
    private final double width, height;
    private final double escalaX, escalaY;

    /**
     * Construye un pez que nada.
     *
     * @param x posición x del pez en pixels.
     * @param y posición y del pez en pixels.
     * @param width ancho del pez en pixels.
     * @param height alto del pez en pixels.
     */
    public Pez(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.escalaX = width / totalWidth;
        this.escalaY = height / totalHeight;
    }

    
    /**
     * Dibuja el pez sin ninguna transformación
     * @param graphics2D 
     */
    public void paint(Graphics2D graphics2D) {
        // Transladar y escalar
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(getX(), getY());
        graphics2D.scale(getEscalaX(), getEscalaY());
        // Color del pez
        graphics2D.setColor(Color.BLUE);

        // Cuerpo
        graphics2D.fill(new Ellipse2D.Double(51, 23, 229, 77));

        // Aleta caudal
        int[] aletaCaudalX = {0, 73, 0};
        int[] aletaCaudalY = {20, 62, 105};
        graphics2D.fillPolygon(aletaCaudalX, aletaCaudalY, aletaCaudalX.length);

        // Aleta dorzal
        int[] aletaDorzalX = {147, 190, 140};
        int[] aletaDorzalY = {0, 25, 25};
        graphics2D.fillPolygon(aletaDorzalX, aletaDorzalY, aletaDorzalX.length);

        // Aleta ventral
        int[] aletaVentralX = {145, 185, 150};
        int[] aletaVentralY = {98, 98, 110};
        graphics2D.fillPolygon(aletaVentralX, aletaVentralY, aletaVentralX.length);

        // Ojo
        graphics2D.setColor(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(227, 41, 17, 17));

        // Rejilla de referencia
        new Grid(getTotalWidth(), getTotalHeight()).paint(graphics2D);

        // Volver a la translación y escalación anterior
        graphics2D.setTransform(affineTransform);
    } // dibujar()

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


} // class Pez

