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

import java.awt.Graphics2D;

/**
 *
 * @author Luis Alejandro Bernal Romero (Aztlek)
 */
public abstract class ObjetoMovil extends ObjetoGrafico {
    private TipoDireccion direccion;
    private final double longitudPaso;

    public ObjetoMovil(double x, double y, double width, double height, double totalWidth, double totalHeight, TipoDireccion direccion, double longitudPaso) {
        super(x, y, width, height, totalWidth, totalHeight);
        this.direccion = direccion;
        this.longitudPaso = longitudPaso;
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

    public abstract void dibujarParado(Graphics2D graphics2D);
    public abstract void dibujarDerecha(Graphics2D graphics2D);
    public abstract void dibujarIzquierda(Graphics2D graphics2D);
    public abstract void dibujarArriba(Graphics2D graphics2D);
    public abstract void dibujarAbajo(Graphics2D graphics2D);

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
    
    public void voltear(){
        switch (direccion) {
            case derecha:
                direccion = TipoDireccion.izquierda;
                break;
            case izquierda:
                direccion = TipoDireccion.derecha;
                break;
            case arriba:
                direccion = TipoDireccion.abajo;
                break;
            case abajo:
                direccion = TipoDireccion.arriba;
                break;
        }
    }
    
    public void devolver(ObjetoGrafico objetoGrafico){
        switch(direccion){
            case derecha:
                x = objetoGrafico.x - getWidth();
                break;
            case izquierda:
                x = objetoGrafico.x + objetoGrafico.getWidth();
                break;
            case arriba:
                y = objetoGrafico.y + objetoGrafico.getHeight();
                break;
            case abajo:
                y = objetoGrafico.y - getHeight();
                break;
        }
    }
    
    public TipoDireccion getDireccion() {
        return direccion;
    }
    
    public void setDireccion(TipoDireccion direccion) {
        this.direccion = direccion;
    }

    public double getLongitudPaso() {
        return longitudPaso;
    }
}
