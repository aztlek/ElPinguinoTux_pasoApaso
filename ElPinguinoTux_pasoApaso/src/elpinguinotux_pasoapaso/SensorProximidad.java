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

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * El sensor de proximidad de un ObjetoGrafico
 *
 * @author Luis Alejandro Bernal Romero (Aztlek)
 */
public class SensorProximidad {

    private final double x, y;
    private final double width, height;
    private boolean activo = true;
    private final ArrayList<ObjetoGrafico> objetosGraficos;

    public SensorProximidad(double x, double y, double width, double height, ArrayList<ObjetoGrafico> objetosGraficos) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.objetosGraficos = objetosGraficos;
    }

    public Rectangle2D getBounds2D() {
        return new Rectangle2D.Double(x, y, width, height);
    }

    public boolean colisionaCon(ObjetoGrafico otro) {
        if (otro == null
                || !this.isColisionable()
                || !otro.isColisionable()) {
            return false;
        }
        Rectangle2D rectanguloThis = this.getBounds2D();
        Rectangle2D rectanguloOtro = otro.getBounds2D();
        return rectanguloThis.intersects(rectanguloOtro);
    }

    public boolean isActivo() {
        return activo;
    }

    public boolean isColisionable() {
        return isActivo();
    }

    public void activar() {
        this.activo = true;
    }

    public void desactivar() {
        this.activo = false;
    }

}
