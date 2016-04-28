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
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Una escalera por la cual Tux puede pasar por arreba de los cubos de hielo
 *
 * @author Luis Alejandro Bernal Romero(Aztlek)
 */
public class Escalera extends ObjetoGrafico {

    private final double radioX = 10d, radioY = radioX;

    public Escalera(double x, double y, double width, double height) {
        super(x, y, width, height, 190, 190);
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        // Transladar y escalar
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(getX(), getY());
        graphics2D.scale(getEscalaX(), getEscalaY());
        Color oldColor = graphics2D.getColor();

//        // Fondo
//        graphics2D.setColor(new Color(41, 60, 255, 234));
//        graphics2D.fill(new Rectangle2D.Double(0, 0, 190, 190));
//        
        // Travesaños
        graphics2D.setColor(new Color(41, 60, 255));
        double inicioY = 14d, separaciónTravesaños = 22d;
        for (int i = 0; i < 8; i++) {
            graphics2D.fill(new Rectangle2D.Double(0, inicioY + i * separaciónTravesaños, 190, 10));
        }
        
        // Largeros
        graphics2D.setColor(new Color(41 - 20, 60 - 20, 255 - 20));
        graphics2D.fill(new RoundRectangle2D.Double(0, 0, 10, 190, radioX, radioY));
        graphics2D.fill(new RoundRectangle2D.Double(180, 0, 10, 190, radioX, radioY));

//        // Rejilla de referencia
//        new Grid(getTotalWidth(), getTotalHeight()).paint(graphics2D);

        // Volver a la translación y escalación anterior
        graphics2D.setTransform(affineTransform);
        graphics2D.setColor(oldColor);
    }
}
