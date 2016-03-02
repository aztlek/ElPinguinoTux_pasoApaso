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
    private final ContadorPeces contadorPeces;
    private final ContadorVidas contadorVidas;
    private final FamiliaTux familiaTux;
    private final Tiempo tiempo;
    private final Titulo titulo;
    
    private final Orca[] orcas;
    private final Iceberg[][] icebergs;

//    private final CuboDeHielo[] hilera0;
//    private final CuboDeHielo[] hilera10, hilera11;
//    private final CuboDeHielo[] hilera20, hilera21, hilera22;
//    private final CuboDeHielo[] hilera30, hilera31;
//    private final CuboDeHielo[] hilera4;
//    private final CuboDeHielo[] columna0;
//    private final Iceberg iceberg;
    public Escenario(int x, int y, int width, int height, JFrame marco) {
        setBounds(x, y, width, height);
        this.x = x;
        this.y = y;
        this.marco = marco;
        this.escalaX = (double) width / totalWidth;
        this.escalaY = (double) height / totalHeight;

        // Instanciar los objetos que son únicos
        tux = new Tux(214, 129, 11, 14);
        contadorPeces = new ContadorPeces(244, 88, 35, 20);
        contadorVidas = new ContadorVidas(244, 127, 35, 20);
        familiaTux = new FamiliaTux(249, 17, 16, 14);
        tiempo = new Tiempo(244, 52, 35, 20);
        titulo = new Titulo(0, 0, 16, totalHeight);

        // Los villanos
        orcas = new Orca[4];
        Random random = new Random();
        double orcasInicioX = 27.0d;
        double orcasInicioY = 12.0d;
        double orcaSeparaciónAlto = 37.0d;
        double orcaAnchoPosicion = 163;

        // Instanciar las orcas
        for (int j = 0; j < orcas.length; j++) {
            orcas[j] = new Orca(
                    orcasInicioX /* + random.nextDouble() * orcaAnchoPosicion*/,
                    orcasInicioY + j * orcaSeparaciónAlto,
                    43.0d,
                    23.0d
            );
        }
        
        // Los icebergs
        final double inicioXIceberg = 86;
        final double inicioYIceberg = 16;
        final double anchoIceberg = 14;
        final double altoIceberg = 14;
        final double separacionAnchoIceberg = 70;
        final double separacionAltoIceberg = 37;
        final int numFilIcebergs = 4;
        final int numColIcebergs = 2;
        icebergs = new Iceberg[numFilIcebergs][numColIcebergs];
        
        for (int i = 0; i < icebergs.length; i++) {
            for (int j = 0; j < icebergs[i].length; j++) {
                icebergs[i][j] = new Iceberg(
                                        inicioXIceberg + j * separacionAnchoIceberg, 
                                        inicioYIceberg + i * separacionAltoIceberg, 
                                        anchoIceberg, 
                                        altoIceberg
                                    );
            }
        }

        // Constantes de las teselas
        double inicioxBloques = 17.0d;
        double inicioyBloques = 0.0d;
        int numBloquesX = 28;
        int numBloquesY = 17;
        double widthCuboDeHielo = (totalWidth - inicioxBloques)
                / (double) numBloquesX;
        double heightCuboDeHielo = (totalHeight - inicioyBloques)
                / (double) numBloquesY;

//        // hilera0
//        hilera0 = new CuboDeHielo[numBloquesX];
//        for (int i = 0; i < hilera0.length; i++) {
//            hilera0[i] = new CuboDeHielo(
//                            inicioxBloques + 0 * widthCuboDeHielo + i * widthCuboDeHielo,
//                            inicioyBloques + 0 * heightCuboDeHielo,
//                            widthCuboDeHielo,
//                            heightCuboDeHielo
//                    );
//        }
//        
//        // hilera10
//        hilera10 = new CuboDeHielo[10];
//        for (int i = 0; i < hilera10.length; i++) {
//             hilera10[i] = new CuboDeHielo(
//                            inicioxBloques + 1 * widthCuboDeHielo + i * widthCuboDeHielo,
//                            inicioyBloques + 4 * heightCuboDeHielo,
//                            widthCuboDeHielo,
//                            heightCuboDeHielo
//                    );
//        }
//        
//        // hilera11
//        hilera11 = new CuboDeHielo[15];
//        for (int i = 0; i < hilera11.length; i++) {
//            hilera11[i] = new CuboDeHielo(
//                            inicioxBloques + 13 * widthCuboDeHielo + i * widthCuboDeHielo,
//                            4 * heightCuboDeHielo + 0 * heightCuboDeHielo,
//                            widthCuboDeHielo,
//                            heightCuboDeHielo
//                    );
//        }
//        
//        // hilera20
//        hilera20 = new CuboDeHielo[3];
//        for (int i = 0; i < hilera20.length; i++) {
//            hilera20[i] = new CuboDeHielo(
//                            inicioxBloques + 1 * widthCuboDeHielo + i * widthCuboDeHielo,
//                            8 * heightCuboDeHielo + 0 * heightCuboDeHielo,
//                            widthCuboDeHielo,
//                            heightCuboDeHielo
//                    );
//        }
//        
//        // hilera21
//        hilera21 = new CuboDeHielo[12];
//        for (int i = 0; i < hilera21.length; i++) {
//            hilera21[i] = new CuboDeHielo(
//                            inicioxBloques + 6 * widthCuboDeHielo + i * widthCuboDeHielo,
//                            8 * heightCuboDeHielo + 0 * heightCuboDeHielo,
//                            widthCuboDeHielo,
//                            heightCuboDeHielo
//                    );
//        }
//        
//        // hilera22
//        hilera22 = new CuboDeHielo[2];
//        for (int i = 0; i < hilera22.length; i++) {
//            hilera22[i] = new CuboDeHielo(
//                            inicioxBloques + 20 * widthCuboDeHielo + i * widthCuboDeHielo,
//                            8 * heightCuboDeHielo + 0 * heightCuboDeHielo,
//                            widthCuboDeHielo,
//                            heightCuboDeHielo
//                    );
//        }
//        
//        // hilera30
//        hilera30 = new CuboDeHielo[10];
//        for (int i = 0; i < hilera30.length; i++) {
//            hilera30[i] = new CuboDeHielo(
//                            inicioxBloques + 1 * widthCuboDeHielo + i * widthCuboDeHielo,
//                            12 * heightCuboDeHielo + 0 * heightCuboDeHielo,
//                            widthCuboDeHielo,
//                            heightCuboDeHielo
//                    );
//        }
//        
//        // hilera31
//        hilera31 = new CuboDeHielo[10];
//        for (int i = 0; i < hilera31.length; i++) {
//            hilera31[i] = new CuboDeHielo(
//                            inicioxBloques + 13 * widthCuboDeHielo + i * widthCuboDeHielo,
//                            12 * heightCuboDeHielo + 0 * heightCuboDeHielo,
//                            widthCuboDeHielo,
//                            heightCuboDeHielo
//                    );
//        }
//        
//        // hilera4
//        hilera4 = new CuboDeHielo[24];
//        for (int i = 0; i < hilera4.length; i++) {
//            hilera4[i] = new CuboDeHielo(
//                            inicioxBloques + 0 * widthCuboDeHielo + i * widthCuboDeHielo,
//                            16 * heightCuboDeHielo + 0 * heightCuboDeHielo,
//                            widthCuboDeHielo,
//                            heightCuboDeHielo
//                    );
//        }
//        
//        // columna0
//        columna0 = new CuboDeHielo[numBloquesY - 2];
//        for (int j = 0; j < columna0.length; j++) {
//            columna0[j] = new CuboDeHielo(
//                            inicioxBloques + 0 * widthCuboDeHielo,
//                            inicioyBloques + 1 * heightCuboDeHielo + j * heightCuboDeHielo,
//                            widthCuboDeHielo,
//                            heightCuboDeHielo
//                    );
//        }
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
        contadorPeces.paint(graphics2D);
        contadorVidas.paint(graphics2D);
        familiaTux.paint(graphics2D);
        tiempo.paint(graphics2D);
        titulo.paint(graphics2D);
        
        for (Orca orca : orcas) {
            orca.paint(graphics2D);
        }
//        for (CuboDeHielo hilera : hilera0) {
//            hilera.paint(graphics2D);
//        }
//        for (CuboDeHielo hilera : hilera10) {
//            hilera.paint(graphics2D);
//        }
//        for (CuboDeHielo hilera : hilera11) {
//            hilera.paint(graphics2D);
//        }
//        for (CuboDeHielo hilera : hilera20) {
//            hilera.paint(graphics2D);
//        }
//        for (CuboDeHielo hilera : hilera21) {
//            hilera.paint(graphics2D);
//        }
//        for (CuboDeHielo hilera : hilera22) {
//            hilera.paint(graphics2D);
//        }
//        for (CuboDeHielo hilera : hilera30) {
//            hilera.paint(graphics2D);
//        }
//        for (CuboDeHielo hilera : hilera31) {
//            hilera.paint(graphics2D);
//        }
//        for (CuboDeHielo hilera : hilera4) {
//            hilera.paint(graphics2D);
//        }
//        for (CuboDeHielo columna : columna0) {
//            columna.paint(graphics2D);
//        }

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
