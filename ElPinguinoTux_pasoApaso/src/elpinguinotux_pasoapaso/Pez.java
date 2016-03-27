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
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.Random;

/**
 * Los peces que captura Tux pata llevarle a su familia.
 *
 * @author Luis Alejandro Bernal Romero (Aztlek)
 */
public class Pez extends ObjetoMovil implements Runnable{
    private final Escenario escenario;

    public Pez(double x, double y, double width, double height, Escenario escenario) {
        super(x, y, width, height, 280, 110, TipoDireccion.derecha, 0.1 * new Random().nextDouble());
        this.escenario = escenario;
    }

    private void dibujar(Graphics2D graphics2D) {
        // Color del pez
        graphics2D.setColor(Color.BLUE);

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
    public void dibujarDerecha(Graphics2D graphics2D){
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
    public void run() {
        for(;;){
            darPaso();
            if(x <= 0 || x + getWidth() >= escenario.getTotalWidth()){
                voltear();
            }
            escenario.repaint();
            try {
                Thread.sleep(2);
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
} // class Pez

