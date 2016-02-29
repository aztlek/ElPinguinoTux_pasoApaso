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

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author aztlek
 */
public class Escenario extends Canvas {

    private final int x;
    private final int y;
    private final double totalWidth = 280.0d;
    private final double totalHeight = 160.0d;
    private final JFrame marco;
    private final double escalaX;
    private final double escalaY;

    private final Tux tux;
    private final Orca[] orcas;
    
    // Constantes de las teselas
    private final double inicioxBloques = 17.0d; // Posición inicial de las hileras en x
    private final double inicioyBloques = 0.0d; // Posición inicial de las hileras en y
    private final int numBloquesX = 28; // Número de bloques en x
    private final int numBloquesY = 17; // Buneor de bloques en y
    private final double widthCuboDeHielo = (getTotalWidth() - inicioxBloques) / (double)numBloquesX; // Ancho de la tesela
    private final double heightCuboDeHielo = (getTotalHeight() - inicioyBloques) / (double)numBloquesY; // Alto de la tesela
    private final CuboDeHielo[] hilera0;
    
//    private final Pez pez;
//    private final ContadorPeces contadorPeces;
//    private final ContadorVidas contadorVidas;
//    private final Iceberg iceberg;
//    private final FamiliaTux familiaTux;
//    private final Tiempo tiempo;
//    private final Titulo titulo;

    public Escenario(int x, int y, int width, int height, JFrame marco) {
        setBounds(x, y, width, height);
        this.x = x;
        this.y = y;
        this.marco = marco;
        this.escalaX = (double) width / totalWidth;
        this.escalaY = (double) height / totalHeight;

        // El protagonista
        tux = new Tux(214, 129, 11, 14);

        // Los villanos
        orcas = new Orca[4];
        Random random = new Random();
        double orcasInicioX = 27.0d;
        double orcasInicioY = 12.0d;
        double orcaSeparaciónAlto = 37.0d;
        double orcaRecorridoX = 182;

        // Instanciar las orcas
        for (int i = 0; i < orcas.length; i++) {
            orcas[i] = new Orca(
                    orcasInicioX + random.nextDouble() * orcaRecorridoX,
                    orcasInicioY + i * orcaSeparaciónAlto,
                    43.0d,
                    23.0d
            );
        }
        
        // hilera0
        hilera0 = new CuboDeHielo[numBloquesX];
        for (int i = 0; i < hilera0.length; i++) {
            hilera0[i] = new CuboDeHielo(
                            inicioxBloques + 0 * widthCuboDeHielo + i * widthCuboDeHielo,
                            inicioyBloques + 0 * heightCuboDeHielo,
                            widthCuboDeHielo,
                            heightCuboDeHielo
                    );
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;

        // Transformaciones
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(x, y);
        graphics2D.scale(escalaX, escalaY);

        tux.paint(graphics2D);
        for (Orca orca : orcas) {
            orca.paint(graphics2D);
        }
        for (CuboDeHielo hilera : hilera0) {
            hilera.paint(graphics2D);
        }
//        cuboDeHielo.paint(graphics2D);
//        pez.paint(graphics2D);
//        contadorPeces.paint(graphics2D);
//        contadorVidas.paint(graphics2D);
//        iceberg.paint(graphics2D);
//        familiaTux.paint(graphics2D);
//        tiempo.paint(graphics2D);
//        titulo.paint(graphics2D);

        // Rejilla de referencia
        new Grid(getTotalWidth(), getTotalHeight()).paint(graphics2D);
        
        // Reestrablece las transformaciones
        graphics2D.setTransform(affineTransform);
    } // paint()

    public double getTotalWidth() {
        return totalWidth;
    }

    public double getTotalHeight() {
        return totalHeight;
    }

} // class Escenario
