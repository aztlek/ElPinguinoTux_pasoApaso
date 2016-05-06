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

/**
 *
 * @author aztlek
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Luis Alejandro Bernal Romero (Aztlek)
 */
public class Orca extends ObjetoMovil implements Runnable{
    private final Escenario escenario;
    private final ContadorVidas contadorVidas;

    public Orca(double x, double y, double width, double height, 
            Escenario escenario, ContadorVidas contadorVidas) {
        super(x, y, width, height, 280, 160, TipoDireccion.derecha, .05d * new Random().nextDouble());
        this.escenario = escenario;
        this.contadorVidas = contadorVidas;
    } // Orca()
    
    public  Orca(double x, double y, double width, double height) {
        this(x, y, width, height, null, null);
    }

    @Override
    public void dibujarDerecha(Graphics2D graphics2D) {
        // Transladar y escalar
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(getX(), getY());
        graphics2D.scale(getEscalaX(), getEscalaY());

        dibujar(graphics2D);

        // Volver a la translación y escalación anterior
        graphics2D.setTransform(affineTransform);
    } // dibujarDerecha()

    @Override
    public void dibujarIzquierda(Graphics2D graphics2D) {
        // Transladar y escalar
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(getX(), getY());
        graphics2D.scale(-getEscalaX(), getEscalaY());
        graphics2D.translate(-getTotalWidth(), 0d);

        dibujar(graphics2D);

        // Volver a la translación y escalación anterior
        graphics2D.setTransform(affineTransform);
    } // dibujarIzquierda()

    private void dibujar(Graphics2D graphics2D) {
        // Cuerpo
        graphics2D.setColor(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(50, 65, 230, 71));

        // Aleta caudal
        graphics2D.setColor(Color.BLACK);

        // Puntos aleta caudal
        int[] aletaCaudalx = {0, 79, 22};
        int[] aletaCaudaly = {63, 102, 111};
        graphics2D.fillPolygon(aletaCaudalx, aletaCaudaly, aletaCaudalx.length);

        // Puntos aleta dorsal
        int[] aletaDorsalx = {158, 186, 150};
        int[] aletaDorsaly = {0, 66, 66};
        graphics2D.fillPolygon(aletaDorsalx, aletaDorsaly, aletaDorsalx.length);

        // Mancha pos ocular
        graphics2D.setColor(Color.WHITE);
        graphics2D.fill(new Ellipse2D.Double(201, 76, 42, 20));

        // Ojo
        graphics2D.setColor(Color.RED);
        graphics2D.fill(new Ellipse2D.Double(245, 92, 3, 3));

        // Aleta pectoral
        graphics2D.setColor(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(177, 122, 26, 40));

        // Mancha ventral
        graphics2D.setColor(Color.WHITE);
        graphics2D.fill(new Ellipse2D.Double(74, 104, 58, 27));
        graphics2D.fill(new Ellipse2D.Double(172, 102, 88, 29));

//        // Rejilla de referencia
//        new Grid(getTotalWidth(), getTotalHeight()).paint(graphics2D);        
    }
    
    @Override
    public void run() {
        for(;;){
            darPaso();
            Tux tux = null;
            Iceberg iceberg = null;
            ArrayList<ObjetoGrafico> quienes = escenario.conQuienesColisiona(this);
            for (ObjetoGrafico o : quienes) {
                if (o instanceof CuboDeHielo) {
                    voltear();
                }
                else if (o instanceof Tux) {
                    tux = (Tux) o;
                }
                else if (o instanceof Iceberg) {
                    iceberg = (Iceberg) o;
                }
            }
            if (iceberg == null && tux != null) {
                int vidas = contadorVidas.decrement();
                tux.restart();
            }
            escenario.repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) { }
        }
    }

    @Override
    public void dibujarParado(Graphics2D graphics2D) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dibujarArriba(Graphics2D graphics2D) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dibujarAbajo(Graphics2D graphics2D) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
} // class Orca
