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
 * You should have received arreglo copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package elpinguinotux_pasoapaso;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import javax.swing.JFrame;

public class Escenario extends Canvas {
    private final int x;
    private final int y;
    private final double totalWidth = 280.0d;
    private final double totalHeight = 160.0d;
    private final JFrame marco;
    private final double escalaX;
    private final double escalaY;
    private final ArrayList<ObjetoGrafico> objetosGraficos;
    private final CuentaRegresiva cuentaRegresiva;

    public Escenario(int x, int y, int width, int height, JFrame marco) {
        setBounds(x, y, width, height);
        this.x = x;
        this.y = y;
        this.marco = marco;
        this.escalaX = (double) width / totalWidth;
        this.escalaY = (double) height / totalHeight;
        
        objetosGraficos = new ArrayList<>();
        
        objetosGraficos.add( new Titulo(0, 0, 16, totalHeight) );
//        objetosGraficos.add( new Tiempo(244, 52, 35, 20) );
        cuentaRegresiva= new CuentaRegresiva(244, 52, 35, 20, this);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(cuentaRegresiva, 10, 100);
        objetosGraficos.add( new ContadorPeces(244, 88, 35, 20) );
        objetosGraficos.add( new ContadorVidas(244, 127, 35, 20) );

        // Constantes de las teselas
        final double inicioxBloques = 17.0d;
        final double inicioyBloques = 0.0d;
        final int numBloquesX = 28;
        final int numBloquesY = 17;
        final double widthCuboDeHielo = (totalWidth - inicioxBloques)
                / (double) numBloquesX;
        final double heightCuboDeHielo = (totalHeight - inicioyBloques)
                / (double) numBloquesY;

        // Los villanos
        int orcasLength = 4;
        Random random = new Random();
        double orcasInicioX = 27.0d;
        double orcasInicioY = 12.0d;
        double orcaSeparaciónAlto = 4 * heightCuboDeHielo;
        double orcaAnchoPosicion = 163;

        // Instanciar las orcas
        for (int j = 0; j < orcasLength; j++) {
            Orca orca = new Orca(
                    orcasInicioX + random.nextDouble() * orcaAnchoPosicion,
                    orcasInicioY + j * orcaSeparaciónAlto,
                    43.0d,
                    23.0d,
                    this
            );
            objetosGraficos.add(orca);
            Thread threadOrca = new Thread(orca);
            threadOrca.start();
        }

        // Los peces
        final double inicioXpeces = 12 + inicioxBloques;
        final double inicioYpeces = 21;
        final double anchoPosXpeces = 194;
        final double separacionYpeces = orcaSeparaciónAlto;
        final int[] lonFilPeces = {2, 3, 2, 1};

        for (int i = 0; i < lonFilPeces.length; i++) {
            for (int j = 0; j < lonFilPeces[i]; j++) {
                Pez pez = new Pez(
                        inicioXpeces + random.nextDouble() * anchoPosXpeces,
                        inicioYpeces + i * separacionYpeces,
                        12,
                        5,
                        this
                );
                objetosGraficos.add(pez);
                Thread threadPez = new Thread(pez);
                threadPez.start();
            }
        }

        // Los icebergs
        final double inicioXIceberg = inicioxBloques + widthCuboDeHielo;
        final double inicioYIceberg = 16;
        final double anchoIceberg = 14;
        final double altoIceberg = 14;
        final double separacionAnchoIceberg = 70;
        final double separacionAltoIceberg = orcaSeparaciónAlto;
        final int numFilIcebergs = 4;
        final int numColIcebergs = 2;

        for (int i = 0; i < numFilIcebergs; i++) {
            for (int j = 0; j < numColIcebergs; j++) {
                objetosGraficos.add(new Iceberg(
                        inicioXIceberg + j * separacionAnchoIceberg + separacionAnchoIceberg * random.nextDouble(),
                        inicioYIceberg + i * separacionAltoIceberg,
                        anchoIceberg,
                        altoIceberg
                    )
                );
            }
        }

        objetosGraficos.add( new Iceberg(
                inicioxBloques + 20 * widthCuboDeHielo,
                13 * heightCuboDeHielo,
                3 * widthCuboDeHielo,
                3 * heightCuboDeHielo
            )
        );
        
        Iceberg icebergFinal = new Iceberg(
                inicioxBloques + 24 * widthCuboDeHielo,
                1 * heightCuboDeHielo,
                3 * widthCuboDeHielo,
                3 * heightCuboDeHielo
        );
        objetosGraficos.add(icebergFinal);
        
        // Familia Tux
        objetosGraficos.add( new FamiliaTux(249, 17, 16, 14) );

        // Columnas
        
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
        
        for (int j = 0; j < lonColumnas.length; j++) {
            for (int i = 0; i < lonColumnas[j]; i++) {
                objetosGraficos.add(new CuboDeHielo(
                        iniXcolumnas[j],
                        iniYColumnas[j] + i * heightCuboDeHielo,
                        widthCuboDeHielo,
                        heightCuboDeHielo
                    )
                );
            }
        }
        
        // Hileras
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
            {inicioxBloques + widthCuboDeHielo, inicioxBloques + widthCuboDeHielo * 6, inicioxBloques + widthCuboDeHielo * 20},
            {inicioxBloques + widthCuboDeHielo, inicioxBloques + widthCuboDeHielo * 13},
            {inicioxBloques}
        };
        for (int i = 0; i < lonHileras.length; i++) {
            for (int j = 0; j < lonHileras[i].length; j++) {
                for (int k = 0; k < lonHileras[i][j]; k++) {
                    objetosGraficos.add( new CuboDeHielo(
                            iniXhileras[i][j] + widthCuboDeHielo * k,
                            inicioyBloques + heightCuboDeHielo * i * 4,
                            widthCuboDeHielo,
                            heightCuboDeHielo
                        )
                    );
                }
            }
        }
        
        // Tux
        Tux tux = new Tux(214, 129, 11, 14, this);
        objetosGraficos.add(tux);
        marco.addKeyListener(tux);
    }

    @Override
    public void paint(Graphics g) {
        Image imagenSegundoBuffer = createImage(marco.getWidth(), marco.getHeight());
        Graphics2D graphics2D = (Graphics2D) imagenSegundoBuffer.getGraphics();

        // Transformaciones
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(x, y);
        graphics2D.scale(escalaX, escalaY);

        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        for (ObjetoGrafico objetoGrafico : objetosGraficos) {
            if (objetoGrafico.isVisible()) {
                objetoGrafico.paint(graphics2D);
            }
        }
        cuentaRegresiva.paint(graphics2D);
        
        // Reestrablece las transformaciones
        graphics2D.setTransform(affineTransform);
        
        g.drawImage(imagenSegundoBuffer, 0, 0, null);
    } // paint()

    @Override
    public void update(Graphics g) {
        paint(g);
    }
    
    public ArrayList<ObjetoGrafico> conQuienesColisiona(ObjetoGrafico objetoGrafico) {
        ArrayList<ObjetoGrafico> quienes = new ArrayList<>();
        Object[] arreglo = objetosGraficos.toArray();
        for (Object a : arreglo) {
            ObjetoGrafico o = (ObjetoGrafico) a;
            if (objetoGrafico.colisionaCon(o)) {
                quienes.add(o);
            }
        }
        return quienes;
    }
    
    public void add(ObjetoGrafico objetoGrafico){
        objetosGraficos.add(objetoGrafico);
    }

    public double getTotalWidth() {
        return totalWidth;
    }

    public double getTotalHeight() {
        return totalHeight;
    }

} // class Escenario
