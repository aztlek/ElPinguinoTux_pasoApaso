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
import java.util.ArrayList;

/**
 * Tux es el protagonista del juego
 *
 * @author Luis Alejandro Bernal Romero (Aztlek)
 */
public class Tux extends ObjetoMovil implements KeyListener {

    private final Escenario escenario;

    public Tux(double x, double y, double width, double height, Escenario escenario) {
        super(x, y, width, height, 190, 237, TipoDireccion.parado, 5);
        this.escenario = escenario;
    }

    @Override
    public void dibujarParado(Graphics2D graphics2D) {
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
        int picox[] = {95, 111, 103, 95, 87, 79};
        int picoy[] = {64, 72, 84, 90, 84, 72};
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

    @Override
    public void dibujarArriba(Graphics2D graphics2D) {
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
        Area areaCuerpo = new Area(new Ellipse2D.Double(7d, 0d, 177d, 230d));
        int colax[] = {95, 115, 75};
        int colay[] = {180, 230, 230};
        Area areaCola = new Area(new Polygon(colax, colay, colax.length));
        areaCola.intersect(areaCuerpo);
        graphics2D.fill(areaCola);

//        // Rejilla de referencia
//        new Grid(getTotalWidth(), getTotalHeight()).paint(graphics2D);
        // Volver a la translación y escalación anterior
        graphics2D.setTransform(affineTransform);

    } // dibujarArriba()

    @Override
    public void dibujarAbajo(Graphics2D graphics2D) {
        dibujarParado(graphics2D);
    }

    @Override
    public void dibujarIzquierda(Graphics2D graphics2D) {
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

    @Override
    public void dibujarDerecha(Graphics2D graphics2D) {
        // Transladar y escalar
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(getX(), getY());
        graphics2D.scale(-getEscalaX(), getEscalaY());
        graphics2D.translate(-getTotalWidth(), 0d);

        dibujarIzquierdaSinEscalar(graphics2D);

        // Volver a la translación y escalación anterior
        graphics2D.setTransform(affineTransform);
    } // dibujarDerecha()

    private void dibujarIzquierdaSinEscalar(Graphics2D graphics2D) {
        // Cuerpo
        graphics2D.setPaint(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(13d, 0d, 177d, 230d));

        // Pecho
        graphics2D.setPaint(Color.WHITE);
        Area areaCuerpo = new Area(new Ellipse2D.Double(13d, 0d, 177d, 230d));
        Area areaPecho = new Area(new Ellipse2D.Double(-42d, 83d, 138d, 145d));
        areaPecho.intersect(areaCuerpo);
        graphics2D.fill(areaPecho);

        // Pico
        graphics2D.setPaint(new Color(255, 149, 0));
        int picox[] = {23, 43, 39, 0};
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

//        // Rejilla de referencia
//        new Grid(getTotalWidth(), getTotalHeight()).paint(graphics2D);
    } // dibujarIzquierdaSinEscalar()

    public void disparar() {
        double xPez = 0d, yPez = 0d;
        TipoDireccion direccion = getDireccion();
        switch (direccion) {
            case izquierda:
                xPez = x - getWidth();
                yPez = y;
                break;
            case derecha:
                xPez = x + getWidth();
                yPez = y;
                break;
        }
        if (direccion == TipoDireccion.izquierda || direccion == TipoDireccion.derecha) {
            PezBala pezBala = new PezBala(xPez, yPez, 6, 2.5d, escenario, direccion);
            escenario.add(pezBala);
            Thread thread = new Thread(pezBala);
            thread.start();
        }
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
                darPaso();
                break;
            case KeyEvent.VK_LEFT:
                setDireccion(TipoDireccion.izquierda);
                darPaso();
                break;
            case KeyEvent.VK_UP:
                setDireccion(TipoDireccion.arriba);
                darPaso();
                break;
            case KeyEvent.VK_DOWN:
                setDireccion(TipoDireccion.abajo);
                darPaso();
                break;
            case KeyEvent.VK_SPACE:
                disparar();
                break;
            default:
                setDireccion(TipoDireccion.parado);
                break;
        }
        Pez pez = null;
        Iceberg iceberg = null;
        ArrayList<ObjetoGrafico> quienes = escenario.conQuienesColisiona(this);
        for (ObjetoGrafico o : quienes) {
            if (o instanceof CuboDeHielo) {
                devolver(o);
            } else if (o instanceof Pez) {
                pez = (Pez) o;
            } else if (o instanceof Iceberg) {
                iceberg = (Iceberg) o;
            }
        }
        if (pez != null && iceberg == null) {
            pez.setColisionable(false);
            pez.setVisible(false);
        }
        escenario.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

} // class Tux
