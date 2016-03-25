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
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/**
 * Tux es el protagonista del juego
 *
 * @author Luis Alejandro Bernal Romero (Aztlek)
 */
public class Tux extends ObjetoGrafico implements KeyListener {
    private final double totalWidth = 190.0d;
    private final double totalHeight = 237.0d;
    private TipoDireccion direccion = TipoDireccion.parado;
    private final double longitudPaso = 5;
    private final Escenario escenario;

    public Tux(double x, double y, double width, double height, Escenario escenario) {
        super(x, y, width, height, 190, 237);
        this.escenario = escenario;
    }
    
    @Override
    public void paint(Graphics2D graphics2D){
        switch (direccion) {
            case parado:
                dibujarParado(graphics2D);
                break;
            case derecha:
                dibujarDerecha(graphics2D);
                break;
            case izquierda:
                dibujarIzquierda(graphics2D);
                break;
            case arriba:
                dibujarArriba(graphics2D);
                break;
            case abajo:
                dibujarAbajo(graphics2D);
                break;
        } // switch
    }

    private void dibujarParado(Graphics2D graphics2D) {
        // Transladar y escalar
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(getX(), getY());
        graphics2D.scale(getEscalaX(), getEscalaY());

        // Cuerpo
        graphics2D.setPaint(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(7d, 0d, 176d, 230d));

        // Pecho
        graphics2D.setPaint(Color.WHITE);
        graphics2D.fill(new Ellipse2D.Double(26d, 83d, 138d, 145d));

        // Pico
        graphics2D.setPaint(new Color(255, 149, 0));
        int picox[] = { 95, 111, 103, 95, 87, 79};
        int picoy[] = { 64,  72,  84, 90, 84, 72};
        graphics2D.fill(new Polygon(picox, picoy, picox.length));

        // Aleta derecha
        graphics2D.setPaint(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(0d, 80d, 44d, 127d));

        // Aleta izquierda
        graphics2D.setPaint(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(146d, 80d, 44d, 127d));

        // Ojo derecho
        graphics2D.setPaint(Color.WHITE);
        graphics2D.fill(new Ellipse2D.Double(49d, 13d, 42d, 51d));

        // Pupila derecha
        graphics2D.setPaint(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(72d, 45d, 12d, 15d));

        // Ojo izquierdo
        graphics2D.setPaint(Color.WHITE);
        graphics2D.fill(new Ellipse2D.Double(99d, 13d, 42d, 51d));

        // Pupila izquierda
        graphics2D.setPaint(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(105d, 45d, 12d, 15d));

        // Pata derecha
        graphics2D.setPaint(new Color(255, 149, 0));
        graphics2D.fill(new Ellipse2D.Double(27d, 210d, 50d, 27d));

        // Pata  izquierda
        graphics2D.setPaint(new Color(255, 149, 0));
        graphics2D.fill(new Ellipse2D.Double(113d, 210d, 50d, 27d));

//        // Rejilla de referencia
//        new Grid(getTotalWidth(), getTotalHeight()).paint(graphics2D);
        
        // Volver a la translación y escalación anterior
        graphics2D.setTransform(affineTransform);

    } // dibujarParado()
    
    private void dibujarArriba(Graphics2D graphics2D) {
        // Transladar y escalar
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(getX(), getY());
        graphics2D.scale(getEscalaX(), getEscalaY());

        // Pata derecha
        graphics2D.setPaint(new Color(255, 149, 0));
        graphics2D.fill(new Ellipse2D.Double(27d, 210d, 50d, 27d));

        // Pata  izquierda
        graphics2D.setPaint(new Color(255, 149, 0));
        graphics2D.fill(new Ellipse2D.Double(113d, 210d, 50d, 27d));

        // Cuerpo
        graphics2D.setPaint(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(7d, 0d, 177d, 230d));

        // Aleta derecha
        graphics2D.setPaint(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(0d, 80d, 44d, 127d));

        // Aleta izquierda
        graphics2D.setPaint(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(146d, 80d, 44d, 127d));
        
        // Cola
        graphics2D.setPaint(Color.darkGray);
        Area areaCuerpo = new Area(new Ellipse2D.Double( 7d,  0d, 177d, 230d));
        int colax[] = { 95, 115,  75};
        int colay[] = {180, 230, 230};
        Area areaCola =  new Area(new Polygon(colax, colay, colax.length));
        areaCola.intersect(areaCuerpo);
        graphics2D.fill(areaCola);

//        // Rejilla de referencia
//        new Grid(getTotalWidth(), getTotalHeight()).paint(graphics2D);
        
        // Volver a la translación y escalación anterior
        graphics2D.setTransform(affineTransform);

    } // dibujarArriba()
    
    private void dibujarAbajo(Graphics2D graphics2D){
        dibujarParado(graphics2D);
    }
    
    private void dibujarIzquierda(Graphics2D graphics2D){
        // Transladar y escalar
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(getX(), getY());
        graphics2D.scale(getEscalaX(), getEscalaY());
        
        dibujarIzquierdaSinEscalar(graphics2D);
        
//        // Rejilla de referencia
//        new Grid(getTotalWidth(), getTotalHeight()).paint(graphics2D);
        
        // Volver a la translación y escalación anterior
        graphics2D.setTransform(affineTransform);
    }
    
    private void dibujarDerecha(Graphics2D graphics2D){
        // Transladar y escalar
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(getX(), getY());
        graphics2D.scale(-getEscalaX(), getEscalaY());
        graphics2D.translate(-getTotalWidth(), 0d);
        
        dibujarIzquierdaSinEscalar(graphics2D);
        
//        // Rejilla de referencia
//        new Grid(getTotalWidth(), getTotalHeight()).paint(graphics2D);
        
        // Volver a la translación y escalación anterior
        graphics2D.setTransform(affineTransform);
    } // dibujarDerecha()
    
    private void dibujarIzquierdaSinEscalar(Graphics2D graphics2D) {
        // Cuerpo
        graphics2D.setPaint(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(13d, 0d, 177d, 230d));

        // Pecho
        graphics2D.setPaint(Color.WHITE);
        Area areaCuerpo = new Area(new Ellipse2D.Double( 13d,  0d, 177d, 230d));
        Area areaPecho =  new Area(new Ellipse2D.Double(-42d, 83d, 138d, 145d));
        areaPecho.intersect(areaCuerpo);
        graphics2D.fill(areaPecho);

        // Pico
        graphics2D.setPaint(new Color(255, 149, 0));
        int picox[] = {23, 43, 39,  0};
        int picoy[] = {64, 72, 84, 90};
        graphics2D.fill(new Polygon(picox, picoy, picox.length));

        // Aleta
        graphics2D.setPaint(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(58d, 80d, 44d, 127d));
        
        // Ojo
        graphics2D.setPaint(Color.WHITE);
        graphics2D.fill(new Ellipse2D.Double(47d, 13d, 42d, 51d));

        // Pupila
        graphics2D.setPaint(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(50d, 45d, 12d, 15d));

        // Pata
        graphics2D.setPaint(new Color(255, 149, 0));
        graphics2D.fill(new Ellipse2D.Double(55d, 210d, 50d, 27d));
        
        // Cola
        graphics2D.setPaint(Color.BLACK);
        int colax[] = {190, 164, 164};
        int colay[] = {180, 195, 183};
        graphics2D.fill(new Polygon(colax, colay, colax.length));
    } // dibujarIzquierdaSinEscalar()
    
    public void darPaso() {
        switch (direccion) {
            case derecha:
                x += longitudPaso;
                break;
            case izquierda:
                x -= longitudPaso;
                break;
            case arriba:
                y -= longitudPaso;
                break;
            case abajo:
                y += longitudPaso;
                break;
        }
    }

    public TipoDireccion getDireccion() {
        return direccion;
    }

    public void setDireccion(TipoDireccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();

        switch (tecla) {
            case KeyEvent.VK_RIGHT:
                setDireccion(TipoDireccion.derecha);
                break;
            case KeyEvent.VK_LEFT:
                setDireccion(TipoDireccion.izquierda);
                break;
            case KeyEvent.VK_UP:
                setDireccion(TipoDireccion.arriba);
                break;
            case KeyEvent.VK_DOWN:
                setDireccion(TipoDireccion.abajo);
                break;
            default:
                setDireccion(TipoDireccion.parado);
                break;
        }
        darPaso();
        escenario.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

} // class Tux
