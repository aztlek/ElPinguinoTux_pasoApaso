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
    private final Iceberg icebergInicial, icebergFinal;
    private final Orca[] orcas;
    private final Iceberg[][] icebergs;
    private final Pez[][] peces;
    private final CuboDeHielo[][] columnas;
    private final CuboDeHielo[][][] hileras;

    public Escenario(int x, int y, int width, int height, JFrame marco) {
        setBounds(x, y, width, height);
        this.x = x;
        this.y = y;
        this.marco = marco;
        this.escalaX = (double) width / totalWidth;
        this.escalaY = (double) height / totalHeight;


        // Los villanos
        orcas = new Orca[4];
        Random random = new Random();
        double orcasInicioX = 27.0d;
        double orcasInicioY = 12.0d;
        double orcaSeparaciónAlto = 37.0d;
        double orcaAnchoPosicion = 163;

        // Los peces
        final double inicioXpeces = 12;
        final double inicioYpeces = 21;
        final double anchoPosXpeces = 194;
        final double separacionYpeces = 37;
        final int[] lonFilPeces = {2, 3, 2, 1};
        peces = new Pez[4][];

        for (int i = 0; i < peces.length; i++) {
            peces[i] = new Pez[lonFilPeces[i]];
        }

        for (int i = 0; i < peces.length; i++) {
            for (int j = 0; j < peces[i].length; j++) {
                peces[i][j] = new Pez(
                        inicioXpeces + random.nextDouble() * anchoPosXpeces,
                        inicioYpeces + i * separacionYpeces,
                        12,
                        5
                );
            }
        }

        // Instanciar las orcas
        for (int j = 0; j < orcas.length; j++) {
            orcas[j] = new Orca(
                    orcasInicioX + random.nextDouble() * orcaAnchoPosicion,
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
        final double inicioxBloques = 17.0d;
        final double inicioyBloques = 0.0d;
        final int numBloquesX = 28;
        final int numBloquesY = 17;
        final double widthCuboDeHielo = (totalWidth - inicioxBloques)
                / (double) numBloquesX;
        final double heightCuboDeHielo = (totalHeight - inicioyBloques)
                / (double) numBloquesY;
        
        icebergInicial = new Iceberg(
                        inicioxBloques + 20 * widthCuboDeHielo,
                        13 * heightCuboDeHielo,
                        3 * widthCuboDeHielo,
                        3 * heightCuboDeHielo
                );
        icebergFinal = new Iceberg(
                inicioxBloques + 24 * widthCuboDeHielo,
                1 * heightCuboDeHielo,
                3 * widthCuboDeHielo,
                3 * heightCuboDeHielo
        );
        
        final int[] lonColumnas = {15, 11, 3};
        final double[] iniYColumnas = {
            heightCuboDeHielo,
            5 * heightCuboDeHielo,
            heightCuboDeHielo
        };
        final double[] iniXcolumnas = {
            inicioxBloques,
            inicioxBloques + 23 * widthCuboDeHielo,
            inicioxBloques + 27 * widthCuboDeHielo
        };
        columnas = new CuboDeHielo[lonColumnas.length][];
        for (int j = 0; j < columnas.length; j++) {
            columnas[j] = new CuboDeHielo[lonColumnas[j]];
        }
        for (int j = 0; j < columnas.length; j++) {
            for (int i = 0; i < columnas[j].length; i++) {
                columnas[j][i] = new CuboDeHielo(
                        iniXcolumnas[j],
                        iniYColumnas[j] + i * heightCuboDeHielo,
                        widthCuboDeHielo,
                        heightCuboDeHielo
                );
            }
        }
        final int[][] lonHileras = {
            {28},
            {10, 15},
            {3, 12, 3},
            {10, 10},
            {24}
        };
        final double[][] iniXhileras = {
            {inicioxBloques},
            {inicioxBloques + widthCuboDeHielo, inicioxBloques + widthCuboDeHielo * 13},
            {inicioxBloques + widthCuboDeHielo, inicioxBloques + widthCuboDeHielo *  6, inicioxBloques + widthCuboDeHielo * 20},
            {inicioxBloques + widthCuboDeHielo, inicioxBloques + widthCuboDeHielo * 13},
            {inicioxBloques}
        };
        hileras = new CuboDeHielo[lonHileras.length][][];
        for (int i = 0; i < hileras.length; i++) {
            hileras[i] = new CuboDeHielo[lonHileras[i].length][];
            for (int j = 0; j < hileras[i].length; j++) {
                hileras[i][j] = new CuboDeHielo[lonHileras[i][j]];
                for (int k = 0; k < hileras[i][j].length; k++) {
                    hileras[i][j][k] = new CuboDeHielo(
                            iniXhileras[i][j] + widthCuboDeHielo * k, 
                            inicioyBloques + heightCuboDeHielo * i * 4, 
                            widthCuboDeHielo, 
                            heightCuboDeHielo
                    );
                }
            }
        }
        tux = new Tux(214, 129, 11, 14);
        contadorPeces = new ContadorPeces(244, 88, 35, 20);
        contadorVidas = new ContadorVidas(244, 127, 35, 20);
        familiaTux = new FamiliaTux(249, 17, 16, 14);
        tiempo = new Tiempo(244, 52, 35, 20);
        titulo = new Titulo(0, 0, 16, totalHeight);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;

        // Transformaciones
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(x, y);
        graphics2D.scale(escalaX, escalaY);

        for (Pez[] filaPeces : peces) {
            for (Pez pez : filaPeces) {
                pez.paint(graphics2D);
            }
        }
        for (Orca orca : orcas) {
            orca.paint(graphics2D);
        }
        icebergInicial.paint(graphics2D);
        icebergFinal.paint(graphics2D);
        for (Iceberg[] filaIcebergs : icebergs) {
            for (Iceberg iceberg : filaIcebergs) {
                iceberg.paint(graphics2D);
            }
        }
        for (CuboDeHielo[] cs : columnas) {
            for (CuboDeHielo c : cs) {
                c.paint(graphics2D);
            }
        }
        for (CuboDeHielo[][] hss : hileras) {
            for (CuboDeHielo[] hs : hss) {
                for (CuboDeHielo hilera : hs) {
                    hilera.paint(graphics2D);
                }
            }
        }
        tux.paint(graphics2D);
        contadorPeces.paint(graphics2D);
        contadorVidas.paint(graphics2D);
        familiaTux.paint(graphics2D);
        tiempo.paint(graphics2D);
        titulo.paint(graphics2D);

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
