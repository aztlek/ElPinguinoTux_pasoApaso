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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

/**
 * Rejilla de referencia para que puedas poner los objetos o partes de ellos con
 * mayor precisión.
 *
 * Para hacer uso de esta clase debes hacer:
 *
 * 1. Crear una nueva clase llamada Grid en tu proyecto. 
 * 
 * 2. Copiar y pegar entero este código, incluida la licencia. 
 * 
 * 3. Cambias el package al principio del código.
 * 
 * 4. Resuelve los problemas de import.
 * 
 * 4. Si quieres ponerlo en alguno de tus objetos gráficos tienes que hacer lo 
 * siguiente:
 * 
 * 4.1. Abre el archivo de la clase  del objeto gráfico en donde quieres usar 
 *      la grilla.
 * 4.2. Al final del método paint pones el siguiente código:
 * 
        // Rejilla de referencia
        new Grid(getTotalWidth(), getTotalHeight()).paint(graphics2D);
 *
 *  pero antes del código:
 * 
 *         // Volver a la translación y escalación anterior
 *         graphics2D.setTransform(affineTransform);
 * 
 * 5. Si es en otra clase gráfica haces lo mismo, pero debes comentariar las
 * demás rejillas (las del lienzo y las otras clases gráficas) para no
 * confundirte.
 *
 * @author Luis Alejandro Bernal Romero (Aztlek)
 */
public class Grid {

    // Medidas del objeto gráfico
    private final double totalWidth; // Ancho total del dibujo en la hoja milmetrada, en milímetros
    private final double totalHeight; // Alto total del dibujo en la hoja milimetrada, en milímetros
    private final double x; // Posición x, en pixels.
    private final double y; // Posición y, en pixels.
//    private final double width; // Ancho el objeto grafico, en pixels.
//    private final double height; // Alto del objeto grafico, en pixels.

    // Medida de la transparencia de la líneas, o canal alpha
    private static final int alphaCmLines = 255; // Transparencia de las líneas de centímetros, completamente sólida
    private static final int alpha05Lines = 127; // Transparencia de las líneas de 0.5 centímetros, algo transparente
    private static final int alphaMmLines = 50; // Transparencia de las líneas de milímetros, muy transparente
    private static final int alphaNumbers = 255; // Transparencia de los números de centímetros, completamente sólidos

    private boolean paint05 = true; // Dibujar las líneas de 0.5 centímetros
    private boolean paintMM = true; // Dibujar las líneas de milimetros

    private Color color; // Color de la rejilla
    private Color colorCmLines; // Color de las líneas de centimetros
    private Color color05Lines; // Color de las líneas de medio centímetro
    private Color colorMmLines; // Color de las líneas de milimetros
    private Color colorNumbers; // Color de los números de centímetros
    private Stroke strokeCmLine; // Ancho de las líneas de centímetros
    private Stroke stroke05Line; // Ancho de la línea de 0.5 centímetros
    private Stroke strokeMmLine; // Ancho de las líneas de milimetros

    private final int altoNumeros = 3;

    /**
     * Inicializa los valores
     *
     * @param totalWidth Ancho total
     * @param totalHeight Alto total
     */
    public Grid(double totalWidth, double totalHeight) {
        // Inicializando los atributos de objeto gráfico
        this.x = 0.0d;
        this.y = 0.0d;
//        this.width = totalWidth;
//        this.height = totalHeight;
        this.totalWidth = totalWidth;
        this.totalHeight = totalHeight;
        
        // Incicializando los atributos de propios de la regilla
        this.color = new Color(0, 128, 0);
        colorCmLines = new Color(color.getRed(), color.getGreen(), color.getBlue(), alphaCmLines);
        color05Lines = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha05Lines);
        colorMmLines = new Color(color.getRed(), color.getGreen(), color.getBlue(), alphaMmLines);
        colorNumbers = new Color(color.getRed(), color.getGreen(), color.getBlue(), alphaNumbers);
    } // Grid()

    
    /**
     * Cambia el color de la rejilla
     *
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
        colorCmLines = new Color(color.getRed(), color.getGreen(), color.getBlue(), alphaCmLines);
        color05Lines = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha05Lines);
        colorMmLines = new Color(color.getRed(), color.getGreen(), color.getBlue(), alphaMmLines);
        colorNumbers = new Color(color.getRed(), color.getGreen(), color.getBlue(), alphaNumbers);
    } // setColor()

    /**
     * Pinta o no las líneas de 5 mm
     *
     * @param paint05
     */
    public void setPaint05(boolean paint05) {
        this.paint05 = paint05;
    }

    /**
     * Pinta o no las líneas de milímetros
     *
     * @param paintMM
     */
    public void setPaintMM(boolean paintMM) {
        this.paintMM = paintMM;
    }

    /**
     * Dibuja la rejilla
     *
     * @param graphics2D contexto gráfico donde la dibuja.
     */
    public void paint(Graphics2D graphics2D) {
        float decimales = 100000f; // decimales para redondear al float

        // Obtener escala de la anterior transformación
        AffineTransform affineTransform = graphics2D.getTransform();
        float escala = Math.round(affineTransform.getScaleX() * decimales) / decimales;
//        System.err.println("Grid escala  = " + escala);

        strokeCmLine = new BasicStroke(Math.abs(1.0f / escala));
        stroke05Line = new BasicStroke(Math.abs(1.0f / escala));
        strokeMmLine = new BasicStroke(Math.abs(0.5f / escala));

        // Averiguar el tipo de letra actual
        Font oldFont = graphics2D.getFont();

        // Poner el nuevo tipo de letra y obtener las métricas
        graphics2D.setFont(new Font("sans", Font.PLAIN, altoNumeros));

        // La rejilla
        for (int yy = 0; yy <= getTotalHeight(); yy += 10) {

            // Líneas de los centímetros
            graphics2D.setColor(colorCmLines);
            graphics2D.setStroke(strokeCmLine);
            graphics2D.drawLine(
                    (int) Math.round(0),
                    (int) Math.round(yy),
                    (int) Math.round(getTotalWidth()),
                    (int) Math.round(yy)
            );

            // Línea de los 0.5
            graphics2D.setColor(color05Lines);
            if (yy < getTotalHeight() && paint05) {
                graphics2D.setStroke(stroke05Line);
                graphics2D.drawLine(
                        (int) Math.round(0),
                        (int) Math.round((yy + 5)),
                        (int) Math.round(getTotalWidth()),
                        (int) Math.round((yy + 5))
                );
            }

            // Líneas de los milimetros
            graphics2D.setColor(colorMmLines);
            if (yy < getTotalHeight() && paintMM) {
                for (int i = 1; i < 10; i++) {
                    graphics2D.setStroke(strokeMmLine);

                    // No dibujar la línea de los 0.5, pues ya está dibujada
                    if (i != 5) {
                        graphics2D.drawLine(
                                (int) Math.round(0),
                                (int) Math.round((yy + i)),
                                (int) Math.round(getTotalWidth()),
                                (int) Math.round((yy + i))
                        );
                    }
                }
            }

            // Los números
            graphics2D.setColor(colorNumbers);
            graphics2D.drawString("" + yy,
                    (int) Math.round(0),
                    (int) Math.round(yy)
            );
        }

        //
        for (int xx = 0; xx <= getTotalWidth(); xx += 10) {

            // Líneas de los centímetros
            graphics2D.setColor(colorCmLines);
            graphics2D.setStroke(strokeCmLine);
            graphics2D.drawLine(
                    (int) Math.round(xx),
                    (int) Math.round(0),
                    (int) Math.round(xx),
                    (int) Math.round(getTotalHeight())
            );

            // Línea de los 0.5
            graphics2D.setColor(color05Lines);
            if (xx < getTotalWidth() && paint05) {
                graphics2D.setStroke(stroke05Line);
                graphics2D.drawLine(
                        (int) Math.round((xx + 5)),
                        (int) Math.round(0),
                        (int) Math.round((xx + 5)),
                        (int) Math.round(getTotalHeight())
                );
            }

            // Líneas de los milimetros
            graphics2D.setColor(colorMmLines);
            if (xx < getTotalWidth() && paintMM) {
                for (int i = 1; i < 10; i++) {
                    graphics2D.setStroke(strokeMmLine);
                    // No dibujar la línea de los 0.5 por que ya está
                    if (i != 5) {
                        graphics2D.drawLine(
                                (int) Math.round((xx + i)),
                                (int) Math.round(0),
                                (int) Math.round((xx + i)),
                                (int) Math.round(getTotalHeight())
                        );
                    }
                }

            }

            // Los números
            graphics2D.setColor(colorNumbers);
            graphics2D.drawString(
                    "" + xx,
                    xx,
                    altoNumeros
            );
        }

        // Restablecer el anterior tipo de letra
        graphics2D.setFont(oldFont);

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

//    public double getWidth() {
//        return width;
//    }
//
//    public double getHeight() {
//        return height;
//    }

} // class Grid
