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
 * LLeva el número de vidas del protagonista.
 *
 * @author Luis Alejandro Bernal Romero (Aztlek)
 */
public class ContadorVidas {
    private final double totalWidth = 280.0d;
    private final double totalHeight = 184.0d;
    private final double x, y;
    private final double width, height;
    private final double escalaX, escalaY;

    private final String titulo = "Vidas";
    private final int vidasInicial = 3;
    private int vidas = vidasInicial;
    private final double anchoPinguino = 75;
    private final double altoPinguino = 110;
    private final double separacionEntrePinguinos = 9;
    private double totalAnchoPinguinos;
    private double inicioXPinquino;
    private final double inicioYPinguino = 74;
    private final Color colorConteo = new Color(0, 128, 128);
    public static final double escalaTextos = 1.3;
    
    public static final double altoTituloContadores = 50.0d;

    /**
     * Constructora de vidas.
     *
     * @param x Posición x
     * @param y Posición y
     * @param width Ancho
     * @param height alto
     */
    public ContadorVidas(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.escalaX = width / totalWidth;
        this.escalaY = height / totalHeight;
    }

    
    /**
     * Decrementa las vidas del protagonista
     */
    public void dec() {
        vidas--;
    }

    /**
     * @return el número de vidas.
     */
    public int getVidas() {
        return vidas;
    }

    public void paint(Graphics2D graphics2D) {
        AffineTransform transformacionesAnteriores = graphics2D.getTransform();

        // Trnasformaciones: transladar y escalar
        graphics2D.translate(getX(), getY());
        graphics2D.scale(getEscalaX(), getEscalaY());

        // Averiguar el tipo de letra actual
        Font oldFont = graphics2D.getFont();

        // Poner el color, el nuevo tipo de letra y obtene las métricas
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

        // Pintar las vidas
        for (int i = 0; i < vidas; i++) {
            totalAnchoPinguinos = (anchoPinguino * vidas + separacionEntrePinguinos * (vidas - 1));
            inicioXPinquino = (getTotalWidth() - totalAnchoPinguinos) / 2.0d;
            new Tux(
                    inicioXPinquino + i * (anchoPinguino + separacionEntrePinguinos),
                    inicioYPinguino,
                    anchoPinguino,
                    altoPinguino
            ).paint(graphics2D);
        }

        // Restablecer el anterior tipo de letra
        graphics2D.setFont(oldFont);

        // Rejilla de referencia
        new Grid(getTotalWidth(), getTotalHeight()).paint(graphics2D);

        graphics2D.setTransform(transformacionesAnteriores);
    }

    /**
     * Reinicia el contador de vidas.
     */
    public void reiniciar() {
        vidas = vidasInicial;
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

}
// class Vidas