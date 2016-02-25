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
 * El tiempo que tiene el protagonista para alcanzar la meta.
 *
 * @author Luis Alejandro Bernal Romero (Aztlek)
 */
public class Tiempo  {
    private final double totalWidth = 290.0d;
    private final double totalHeight = 160.0d;
    private final double x, y;
    private final double width, height;
    private final double escalaX, escalaY;

    private static final int TIEMPO_INICIAL = 24;
    private final String titulo = "Tiempo";
    private boolean parar = false;
    private int horas = TIEMPO_INICIAL;
    private int minutos = 60;
    private final int paso = 1;
    private final int horaInicial = horas;
    private final int minutosInicial = minutos;
    private final Color colorConteo = new Color(0, 128, 128);
    private static final double altoTituloContadores = 50.0d;
    private static final double escalaTextos = 1.3;

    /**
     * Inicializa los atributos
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Tiempo(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.escalaX = width / totalWidth;
        this.escalaY = height / totalHeight;
    } // Tiempo()

    public void paint(Graphics2D graphics2D) {
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(getX(), getY());
        graphics2D.scale(getEscalaX(), getEscalaY());

        // Averiguar el tipo de letra actual
        Font oldFont = graphics2D.getFont();

        // Poner el color, el nuevo tipo de letra y obtener las métricas
        graphics2D.setColor(colorConteo);
        graphics2D.setFont(new Font("sans", Font.BOLD, (int) Math.round((altoTituloContadores) * escalaTextos)));
        FontMetrics fontMetrics = graphics2D.getFontMetrics();

        // Título
        int widthFont = fontMetrics.stringWidth(titulo);
        graphics2D.drawString(
                titulo,
                (int) Math.round(((getTotalWidth() - widthFont) / 2.0d)),
                (int) Math.round(altoTituloContadores)
        );

        //Conteo
        graphics2D.setColor(colorConteo);
        String stringConteo = toString();
        double altoConteo = altoTituloContadores * 8d / 10d; // En milímetros.
        double inicioYconteo = getTotalHeight(); //117d; // En milímetros.
        graphics2D.setFont(new Font("sans", Font.BOLD, (int) Math.round((altoConteo) * escalaTextos)));
        fontMetrics = graphics2D.getFontMetrics();
        double widthFontConteo = fontMetrics.stringWidth(stringConteo);
        graphics2D.drawString(
                stringConteo,
                (int) Math.round(((getTotalWidth() - widthFontConteo) / 2.0d)),
                (int) Math.round(inicioYconteo)
        );

        // Restablecer el anterior tipo de letra
        graphics2D.setFont(oldFont);

        graphics2D.setTransform(affineTransform);

//        // Rejilla de referencia
//        new Grid(getX(), getY(), getWidth(), getHeight(), getTotalWidth(), getTotalHeight()).paint(graphics2D);
////         Rectángulo de referencia
//        graphics2D.setColor(Color.RED);
//        graphics2D.draw(new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight()));
    }

    /**
     * Decrementa el tiempo
     */
    public void dec() {
        if (horas > 0) {
            minutos -= paso;
            if (minutos < 0) {
                horas--;
                minutos = 60;
            }
        } else {
            horas = 0;
            minutos = 0;
        } // if
    } // dec()

    /**
     * Para el tiempo
     */
    public void parar() {
        parar = true;
    }

    /**
     * Convierte a segundos. Es usado en el compareTo.
     *
     * @return el tiempo en segundos.
     */
    public int toSeconds() {
        return minutos * 60 + horas * 60 * 60;
    }

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

    public int getPaso() {
        return paso;
    }

} // class Tiempo
