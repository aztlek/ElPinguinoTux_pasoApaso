/*
 * Copyright (C) 2016 aztlek
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package elpinguinotux_pasoapaso;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

/**
 * Tux es el protagonista del juego
 *
 * @author Luis Alejandro Bernal Romero (Aztlek)
 */
public class Tux {

    // Ancho y alto total de la figura

    private final double totalWidth = 190.0d;
    private final double totalHeight = 237.0d;

    // Posición, ancho y alto en el Escenario
    private final double x, y;
    private final double width, height;

    // Escalas
    private final double escalaX, escalaY;

    public Tux(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.escalaX = width / totalWidth;
        this.escalaY = height / totalHeight;
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

    public double getTotalWidth() {
        return totalWidth;
    }

    public double getTotalHeight() {
        return totalHeight;
    }

    public void paint(Graphics2D graphics2D) {
        // Transladar y escalar
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(getX(), getY());
        graphics2D.scale(getEscalaX(), getEscalaY());

        // Cuerpo
        graphics2D.setPaint(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(7d, 0d, 176d, 230d));

        // Pecho
        graphics2D.setPaint(Color.WHITE);
        graphics2D.fill(new Ellipse2D.Double(26d, 83d, 138d, 145d));

        // Pico
        graphics2D.setPaint(new Color(255, 149, 0));
        int picox[] = {80, 110, 95};
        int picoy[] = {65, 65, 90};
        graphics2D.fill(new Polygon(picox, picoy, picox.length));

        // Aleta derecha
        graphics2D.setPaint(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(0d, 80d, 44d, 127d));

        // Aleta izquierda
        graphics2D.setPaint(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(146d, 80d, 44d, 127d));

        // Ojo derecho
        graphics2D.setPaint(Color.WHITE);
        graphics2D.fill(new Ellipse2D.Double(49d, 13d, 41d, 51d));

        // Pupila derecha
        graphics2D.setPaint(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(71d, 44d, 13d, 15d));

        // Ojo izquierdo
        graphics2D.setPaint(Color.WHITE);
        graphics2D.fill(new Ellipse2D.Double(100d, 13d, 41d, 51d));

        // Pupila izquierda
        graphics2D.setPaint(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(107d, 44d, 13d, 15d));

        // Pata derecha
        graphics2D.setPaint(new Color(255, 149, 0));
        graphics2D.fill(new Ellipse2D.Double(27d, 209d, 50d, 28d));

        // Pata  izquierda
        graphics2D.setPaint(new Color(255, 149, 0));
        graphics2D.fill(new Ellipse2D.Double(113d, 209d, 50d, 28d));

//        // Rejilla de referencia
//        new Grid(getTotalWidth(), getTotalHeight()).paint(graphics2D);

        // Volver a la translación y escalación anterior
        graphics2D.setTransform(affineTransform);

    } // paint()

} // class Tux
