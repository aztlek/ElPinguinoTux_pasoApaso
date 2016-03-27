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
public class Tiempo extends ObjetoGrafico{
    private int horas = 24;
    private int minutos = 60;
    private final int paso = 1;
    private final String titulo = "Tiempo";
    private static final float altoTituloContadores = 50.0f;
    private static final double escalaTextos = 1.3d;

    public Tiempo(double x, double y, double width, double height) {
        super(x, y, width, height, 290, 160);
    } // Tiempo()

    @Override
    public void paint(Graphics2D graphics2D) {
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(getX(), getY());
        graphics2D.scale(getEscalaX(), getEscalaY());

        // Poner el color, el nuevo tipo de letra y obtener las métricas
        graphics2D.setColor(new Color(0, 128, 128));
        graphics2D.setFont(new Font("sans", Font.BOLD, (int) Math.round((altoTituloContadores) * escalaTextos)));
        FontMetrics fontMetrics = graphics2D.getFontMetrics();

        // Título
        int widthFont = fontMetrics.stringWidth(titulo);
        graphics2D.drawString(
                titulo,
                (float)((getTotalWidth() - widthFont) / 2f),
                altoTituloContadores
        );

        //Conteo
        graphics2D.setColor(new Color(0, 128, 128));
        String stringConteo = toString();
        double altoConteo = altoTituloContadores * 4d / 5d;
        graphics2D.setFont(new Font("sans", Font.BOLD, (int) Math.round(altoConteo * escalaTextos)));
        fontMetrics = graphics2D.getFontMetrics();
        int widthFontConteo = fontMetrics.stringWidth(stringConteo);
        graphics2D.drawString(
                stringConteo,
                (float) ((getTotalWidth() - widthFontConteo) / 2f),
                (float) getTotalHeight()
        );

        graphics2D.setTransform(affineTransform);
    }

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
    }

    public int toSeconds() {
        return minutos * 60 + horas * 60 * 60;
    }

    @Override
    public String toString() {
        return ((horas <= 9 && horas >= 0) ? "0" : "") + horas + ":" + ((minutos <= 9) ? "0" : "") + minutos + "";
    }
} // class Tiempo
