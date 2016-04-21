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
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author aztlek
 */
public class Bomba extends ObjetoGrafico implements Runnable {
    private final Escenario escenario;

    public Bomba(double x, double y, double width, double height, Escenario escenario) {
        super(x, y, width, height, 190, 260);
        this.escenario = escenario;
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(getX(), getY());
        graphics2D.scale(getEscalaX(), getEscalaY());

        // Esfera
        graphics2D.setColor(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(0, 70, 190, 190));

        // Rectángulo
        graphics2D.fill(new Rectangle2D.Double(60, 60, 70, 20));

        // Mecha
        graphics2D.setColor(new Color(30, 30, 30));
        GeneralPath poligono = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
        poligono.moveTo(92d, 60d);
        poligono.lineTo(125d, 0d);
        poligono.lineTo(130d, 0d);
        poligono.lineTo(98d, 60d);
        poligono.closePath();
        graphics2D.fill(poligono);

//        // La grilla de referencia
//        new Grid(totalWidth, totalHeight).paint(graphics2D);
        graphics2D.setTransform(affineTransform);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) { }
        Estallido estallido = new Estallido(x - width / 4d, y - height / 4d, width * 2, height * 2, escenario);
        escenario.add(estallido);
        estallido.explotar();
        escenario.remove(this);
    }
}
