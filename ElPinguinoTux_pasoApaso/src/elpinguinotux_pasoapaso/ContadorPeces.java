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
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 * Cuenta el número de peces pescados por Tux.
 *
 * @author Luis Alejandro Bernal Romero (Aztlek)
 */
public class ContadorPeces {

    private final double totalWidth = 290.0d;
    private final double totalHeight = 160.0d;
    private final double x, y;
    private final double width, height;
    private final double escalaX, escalaY;

    private int numeroDePeces = 8;

    private final String titulo = "Peces";
    private final double anchoPez = 65;
    private final double altoPez = 25;
    private final double separacionXEntrePeces = 7;
    private final double separacionYEntrePeces = 14;
    private final double inicioYPeces = 96;
    private final Color colorConteo = new Color(0, 128, 128);
    private static final double altoTituloContadores = 50.0d;

    /**
     * Crea un gráfico para contar los peces atrapados.
     *
     * @param x posición x en pixels.
     * @param y posición y en pixels.
     * @param width ancho en pixels.
     * @param height alto en pixels.
     */
    public ContadorPeces(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.escalaX = width / totalWidth;
        this.escalaY = height / totalHeight;
    }

    /**
     * @return el número de pescados.
     */
    public int getNumeroDePeces() {
        return numeroDePeces;
    }

    public void paint(Graphics2D graphics2D) {
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(getX(), getY());
        graphics2D.scale(getEscalaX(), getEscalaY());
        double factorEscala = 1.35;

        // Averiguar el tipo de letra actual
        Font oldFont = graphics2D.getFont();

        // Poner el color, el nuevo tipo de letra obtener las métricas
        graphics2D.setColor(colorConteo);
        graphics2D.setFont(new Font("sans", Font.BOLD, (int) Math.round((altoTituloContadores) * factorEscala)));

        // Título
        int widthFont = graphics2D.getFontMetrics().stringWidth(titulo);
        graphics2D.drawString(
                titulo,
                (int) Math.round((getTotalWidth() - widthFont) / 2.0d),
                (int) Math.round(altoTituloContadores)
        );

        // Pintar los pescados
        double anchoPeces = (3 * (anchoPez + separacionXEntrePeces)) + anchoPez;
        double inicioX = (getTotalWidth() - anchoPeces) / 2.0d;
        double inicioY = inicioYPeces;
        for (int i = 0; i < numeroDePeces;) {

            new Pez(
                    (inicioX + (i % 4) * (anchoPez + separacionXEntrePeces)),
                    inicioY,
                    anchoPez,
                    altoPez,
                    null
            ).paint(graphics2D);

            i++;
            if (i % 4 == 0) {
                inicioY += (altoPez + separacionYEntrePeces);
            } // if
        }

        // Restablecer el anterior tipo de letra
        graphics2D.setFont(oldFont);  
        
        // Rejilla de referencia
//        new Grid(getTotalWidth(), getTotalHeight()).paint(graphics2D);

        // Volver a las transformaciones anteriores.
        graphics2D.setTransform(affineTransform);
      
    } // paint()

    /**
     * Incrementa el numeroDePeces
     */
    public void inc() {
        numeroDePeces++;
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
} // class Peces
