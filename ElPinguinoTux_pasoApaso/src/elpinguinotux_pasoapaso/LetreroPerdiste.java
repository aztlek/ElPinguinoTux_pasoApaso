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
 * El letrero cuando se pierde el juego.
 *
 * @author Luis Alejandro Bernal Romero (Aztlek)
 */
public class LetreroPerdiste extends ObjetoGrafico {

    private final String titulo = "¡Perdiste!";
    private final double altoTitulo = 40.0d;
    private final double anchoOrca = 255.0d;

    public LetreroPerdiste(double x, double y, double width, double height) {
        super(x, y, width, height, 280, 190);
        setVisible(false);
        setColisionable(false);
    } // LetreroPerdiste()

    @Override
    public void paint(Graphics2D graphics2D) {
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(getX(), getY());
        graphics2D.scale(getEscalaX(), getEscalaY());

        // Averiguar el tipo de letra actual
        Font oldFont = graphics2D.getFont();
        graphics2D.setColor(Color.RED);
        graphics2D.fillRect(
                0,
                0,
                (int) Math.round(getTotalWidth()),
                (int) Math.round(getTotalHeight())
        );
        
        // Poner el color, el nuevo tipo de letra xx obtener las métricas
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(new Font("sans", Font.BOLD, (int) Math.round(altoTitulo * 1.25d)));
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        int heightFont = fontMetrics.getHeight();
        int widthFont = fontMetrics.stringWidth(titulo);

        // Título
        graphics2D.drawString(
                titulo,
                (int) Math.round((getTotalWidth() - widthFont) / 2.0d),
                (int) Math.round(altoTitulo)
        );

        new Orca(
                (int) Math.round((getTotalWidth() - anchoOrca) / 2.0d),
                (int) Math.round(altoTitulo + 5d),
                anchoOrca,
                140
        ).paint(graphics2D);
        // Restablecer el anterior tipo de letra
        graphics2D.setFont(oldFont);

        graphics2D.setTransform(affineTransform);

    }// Paint()
}// class LetreroPerdiste
