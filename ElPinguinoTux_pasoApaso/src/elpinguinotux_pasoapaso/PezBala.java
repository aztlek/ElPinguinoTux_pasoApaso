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

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author aztlek
 */
public class PezBala extends ObjetoMovil implements Runnable {

    private final Escenario escenario;
    private final double longitudRecorridoBala = 20;

    public PezBala(double x, double y, double width, double height, Escenario escenario, TipoDireccion direccion) {
        super(x, y, width, height, 280, 110, direccion, 0.1 * new Random().nextDouble());
        this.escenario = escenario;
    }

    private void dibujar(Graphics2D graphics2D) {
        // Color del pez
        graphics2D.setColor(Color.CYAN);

        // Cuerpo
        graphics2D.fill(new Ellipse2D.Double(51, 23, 229, 77));

        // Aleta caudal
        int[] aletaCaudalX = {0, 73, 0};
        int[] aletaCaudalY = {20, 62, 105};
        graphics2D.fillPolygon(aletaCaudalX, aletaCaudalY, aletaCaudalX.length);

        // Aleta dorzal
        int[] aletaDorzalX = {147, 190, 140};
        int[] aletaDorzalY = {0, 25, 25};
        graphics2D.fillPolygon(aletaDorzalX, aletaDorzalY, aletaDorzalX.length);

        // Aleta ventral
        int[] aletaVentralX = {145, 185, 150};
        int[] aletaVentralY = {98, 98, 110};
        graphics2D.fillPolygon(aletaVentralX, aletaVentralY, aletaVentralX.length);

        // Ojo
        graphics2D.setColor(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(227, 41, 17, 17));

//        // Rejilla de referencia
//        new Grid(getTotalWidth(), getTotalHeight()).paint(graphics2D);
    } // dibujar()

    @Override
    public void dibujarDerecha(Graphics2D graphics2D) {
        // Transladar y escalar
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(getX(), getY());
        graphics2D.scale(getEscalaX(), getEscalaY());

        dibujar(graphics2D);

        // Volver a la translación y escalación anterior
        graphics2D.setTransform(affineTransform);
    }

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

    @Override
    public void dibujarParado(Graphics2D graphics2D) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void run() {
        double xInicial = x;
        do {
            darPaso();
            ArrayList<ObjetoGrafico> quienes = escenario.conQuienesColisiona(this);
            if (quienes.size() >= 1) {
                ObjetoGrafico quien = quienes.get(0);
                quien.setVisible(false);
                quien.setColisionable(false);
            }
            escenario.repaint();
            try {
                Thread.sleep(2);
            } catch (InterruptedException ex) {
            }
        } while (Math.abs(xInicial - x) - longitudRecorridoBala <= 0.000001d);
        escenario.remove(this);
    }

    @Override
    public void dibujarArriba(Graphics2D graphics2D) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dibujarAbajo(Graphics2D graphics2D) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
